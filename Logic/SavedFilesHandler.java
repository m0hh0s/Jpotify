package Logic;

import java.io.*;
/**
 * this class saves and loads the info of a certain user
 * @author Mohsen Hosseini and Sattar Noee
 * @version 1.0
 */
public class SavedFilesHandler {
    /**
     * if the user is new it makes a File saving its data
     * @param user
     */
    public static void saveUserData(User user){
        if (user == null)
            return;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("SaveFiles\\" + user.getUsername() + ".ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (oos != null) {
                try {
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * loads the info of a certain user
     * @param username
     * @return the user
     * @throws IOException
     */
    public static User loadUserData (String username) throws IOException{
        User tempUser = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("SaveFiles\\" + username + ".ser");
            ois = new ObjectInputStream(fis);
            tempUser = (User) ois.readObject();
            if (tempUser != null){
                tempUser.getMusicLibrary().updateLibrary();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            throw new IOException();
        }finally {
            if (ois != null){
                try {
                    ois.close();
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return tempUser;
    }
}
