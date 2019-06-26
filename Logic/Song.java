package Logic;

import Logic.mp3agic.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Song implements Serializable {
    private String songAddress;
    private long dateListenedTo = 0;
    private transient String title;
    private transient String albumName;
    private transient String artistName;
    private transient byte[] artwork;
    private MusicLibrary musicLibrary;

    public Song(String songAddress , MusicLibrary musicLibrary){
        this.songAddress = songAddress;
        this.musicLibrary = musicLibrary;
        updateTag(musicLibrary);
    }

    public String getSongAddress() {
        return songAddress;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public byte[] getArtwork() {
        return artwork;
    }

    public void setDateListenedTo(long time){
        this.dateListenedTo = time;
    }

    public long getDateListenedTo() {
        return dateListenedTo;
    }

    public void updateTag(MusicLibrary musicLibrary){
        try {
            AbstractID3v2Tag tag  = ID3v2TagFactory.createTag(new FileInputStream(songAddress).readAllBytes());
            this.title = tag.getTitle();
            this.albumName = tag.getAlbum();
            this.artistName = tag.getArtist();
            this.artwork = tag.getAlbumImage();
            musicLibrary.handleAlbum(this);

        } catch (NoSuchTagException | UnsupportedTagException | InvalidDataException | IOException e) {
            e.printStackTrace();
            //musicLibrary.removeSong(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Song){
            Song tempSong = (Song)obj;
            if (this.getSongAddress().equals(tempSong.getSongAddress()))
                return true;
            return this.getTitle().equalsIgnoreCase(tempSong.getTitle()) && this.getArtistName().equalsIgnoreCase(tempSong.getArtistName());
        }
        return false;
    }
}