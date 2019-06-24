package Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MusicLibrary implements Serializable {
    private ArrayList<Song> songs;
    private transient ArrayList<Album> albums;
    private ArrayList<Playlist> playlists;

    public MusicLibrary() {
        this.songs = (ArrayList<Song>) Collections.synchronizedList(new ArrayList<Song>());
        this.albums = (ArrayList<Album>) Collections.synchronizedList(new ArrayList<Album>());
        this.playlists = (ArrayList<Playlist>) Collections.synchronizedList(new ArrayList<Playlist>());
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
}
