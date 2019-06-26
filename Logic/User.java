package Logic;

import java.io.Serializable;

public class User implements Serializable {
    private static User instance = null;

    private static User getInstance(){
        return instance;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }

    private String username;
    private MusicLibrary musicLibrary = new MusicLibrary();

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
