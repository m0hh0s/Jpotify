package Logic;

import java.util.ArrayList;
import java.util.Collections;

public class Album {
    private String title;
    private String artistName;
    private ArrayList<Song> songs;

    public Album(String title, String artistName) {
        this.title = title;
        this.artistName = artistName;
        this.songs = (ArrayList<Song>) Collections.synchronizedList(new ArrayList<Song>());
    }

    public String getTitle() {
        return title;
    }

    public String getArtistName() {
        return artistName;
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

    public byte[] getArtwork(){
        for (Song song : songs) {
            if (song.getArtwork() != null) {
                return song.getArtwork();
            }
        }
        return null;
    }
}
