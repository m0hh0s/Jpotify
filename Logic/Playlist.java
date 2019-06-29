package Logic;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * the class holds informations of a playList
 * @author Mohsen Hosseini and Sattar Noee
 * @version 1.0
 */
public class Playlist implements Serializable {
    private String name;
    private ArrayList<Song> songs;

    /**
     * sets the name of the playList
     * and creats a new ArrayList of songs
     * @param name
     */
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

    /**
     * add song to playList
     * @param song
     */
    public void addSong(Song song){
        songs.add(song);
    }

    /**
     * remove song from array list
     * @param song
     */
    public void removeSong(Song song){
        songs.remove(song);
    }
}
