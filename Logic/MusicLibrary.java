package Logic;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MusicLibrary implements Serializable {
    private ArrayList<Song> songs;
    private transient ArrayList<Album> albums;
    private ArrayList<Playlist> playlists;

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

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
    }

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

    public void removeSong(Song song){
        for (Playlist playlist : playlists){
            playlist.removeSong(song);
        }
        for (Album album : albums){
            album.removeSong(song);
        }
        songs.remove(song);
    }

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
                songs.add(tempSong);
            }
        }
    }

    public void updateLibrary(){
        albums = new ArrayList<>();
        for (Song song : songs)
            song.updateTag(this);
    }
    
}
