package Logic;

import java.io.*;

public class SavedFilesHandler {
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

    public static User loadUserData(String username){
        User tempUser = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("saveFiles\\" + username + ".ser");
            ois = new ObjectInputStream(fis);
            tempUser = (User) ois.readObject();
            if (tempUser != null){
                tempUser.getMusicLibrary().updateLibrary();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
