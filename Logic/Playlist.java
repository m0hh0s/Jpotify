package Logic;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class Playlist implements Serializable {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public void removeSong(Song song){
        songs.remove(song);
    }
}
