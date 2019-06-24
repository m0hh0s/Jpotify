package Logic;

import java.io.Serializable;

public class User implements Serializable {
    private static String username;
    private static MusicLibrary musicLibrary = new MusicLibrary();

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getUsername() {
        return username;
    }

    public static MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }
}
