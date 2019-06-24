package Logic;

import Logic.mp3agic.*;

import java.io.FileInputStream;
import java.io.IOException;

public class Song{
    private String songAddress;
    private String title;
    private String albumName;
    private String artistName;
    private byte[] artwork;

    public Song(String songAddress){
        this.songAddress = songAddress;
        try {
            AbstractID3v2Tag tag  = ID3v2TagFactory.createTag(new FileInputStream(songAddress).readAllBytes());
            this.title = tag.getTitle();
            this.albumName = tag.getAlbum();
            this.artistName = tag.getArtist();
            this.artwork = tag.getAlbumImage();

        } catch (NoSuchTagException | UnsupportedTagException | InvalidDataException | IOException e) {
            e.printStackTrace();
        }
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