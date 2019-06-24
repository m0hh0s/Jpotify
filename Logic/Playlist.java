package Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Playlist implements Serializable {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        songs = (ArrayList<Song>) Collections.synchronizedList(new ArrayList<Song>());
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
