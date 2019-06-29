package Logic;

import java.io.Serializable;
/**
 * holds the info about a user
 * @author Mohsen Hosseini and Sattar Noee
 * @version 1.0
 */
public class User implements Serializable {
    private static User instance = null;
    private String username;
    private MusicLibrary musicLibrary = new MusicLibrary();

    public static User getInstance(){
        return instance;
    }

    /**
     * sets the user instance(which is a user)
     * @param instance
     */
    public static void setInstance(User instance) {
        User.instance = instance;
    }
//    private ArrayList<Socket> friends = new ArrayList<>();
//
//    public ArrayList<Socket> getFriends() {
//        return friends;
//    }
//
//    public void addFriends(Socket friend) {
//        friends.add(friend);
//    }
//
//    public void setupNetwork(){
//        SendDataRunnable sdr = new SendDataRunnable();
//        ReceiveDataRunnable rdr = new ReceiveDataRunnable();
//        rdr.setUser(this);
//        sdr.setUser(this);
//        NetworkHandler.sendData(sdr);
//        NetworkHandler.recieveData(rdr);
//    }

    /**
     * sets the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }


}
