package Logic;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class User implements Serializable {
    private static User instance = null;
    private String username;
    private MusicLibrary musicLibrary = new MusicLibrary();

    public static User getInstance(){
        return instance;
    }

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
