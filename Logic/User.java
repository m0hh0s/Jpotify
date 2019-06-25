package Logic;

import java.io.Serializable;

public class User implements Serializable {
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
