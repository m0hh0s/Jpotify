package Logic;

import java.util.ArrayList;

/**
 * the class to hold the informations about the album
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class Album {
    private String title;
    private String artistName;
    private ArrayList<Song> songs;

    /**
     * set the title and artist name
     * @param title the title of album
     * @param artistName the artist name
     */
    public Album(String title, String artistName) {
        this.title = title;
        this.artistName = artistName;
        //this.songs = (ArrayList<Song>) Collections.synchronizedList(new ArrayList<Song>());
        songs = new ArrayList<>();
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

    /**
     * add song to the arrayList of songs
     * @param song
     */
    public void addSong(Song song){
        songs.add(song);
    }

    /**
     * remove song from array list songs
     * @param song
     */
    public void removeSong(Song song){
        songs.remove(song);
    }

    /**
     * return the album artwork
     * @return
     */
    public byte[] getArtwork(){
        for (Song song : songs) {
            if (song.getArtwork() != null) {
                return song.getArtwork();
            }
        }
        return null;
    }
}
