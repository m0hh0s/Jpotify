package Logic;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * holds arrayList of songs ,albums and playLists
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class MusicLibrary implements Serializable {
    private ArrayList<Song> songs;
    private transient ArrayList<Album> albums;
    private ArrayList<Playlist> playlists;

    /**
     * make new arrayList for songs,albums
     * and playLists also add the two permenant
     * playList to its array
     */
    public MusicLibrary() {
        songs = new ArrayList<>();
        albums = new ArrayList<>();
        playlists = new ArrayList<>();
        playlists.add(new Playlist("Shared Playlist"));
        playlists.add(new Playlist("Favourites"));
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * add playList to the arrayList of playLists
     * @param playlist
     */
    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

    /**
     * adds the song to the album if it matches
     * @param song
     */
    public void handleAlbum(Song song){
        for (Album album : albums){
            if (song.getAlbumName().equalsIgnoreCase(album.getTitle()) && song.getArtistName().equalsIgnoreCase(album.getArtistName())){
                album.addSong(song);
                return;
            }
        }
        Album newAlbum = new Album(song.getAlbumName(),song.getArtistName());
        newAlbum.addSong(song);
        albums.add(newAlbum);
    }

    /**
     * sort songs by the time
     */
    public void sortSongs(){
        songs.sort(new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                long l1 = o1.getDateListenedTo();
                long l2 = o2.getDateListenedTo();
                return Long.compare(l2, l1);
            }
        });
    }

    /**
     * remove song from playLists ,albums and the
     * song arrayList
     * @param song
     */
    public void removeSong(Song song){
        for (Playlist playlist : playlists){
            playlist.removeSong(song);
        }
        for (Album album : albums){
            album.removeSong(song);
        }
        songs.remove(song);
    }

    /**
     * add song by the JFileChooser
     */
    public void addSong(){
        String path = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("musics" , "mp3");
        fileChooser.setFileFilter(filter);
        int res = fileChooser.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files){
                path = file.getAbsolutePath();
                Song tempSong = new Song(path , this);
                int result = compareSong(tempSong);
                if(result == 0)
                    songs.add(tempSong);
            }
        }
    }

    /**
     * update the tag of songs
     * sort songs
     */
    public void updateLibrary(){
        albums = new ArrayList<>();
        for (Song song : songs)
            song.updateTag(this);
        sortSongs();
    }

    /**
     * compares songs to see if we should add the new one
     * or it is repeated
     * @param newSong
     * @return 0 if the new song isnt exists in song arrayList and
     * 1 if sont already exists
     */
    private int compareSong (Song newSong){
        for(Song oldSong:songs){
            if(oldSong.equals(newSong))
                return 1;
        }
        return 0;
    }
    
}
