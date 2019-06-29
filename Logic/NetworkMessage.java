package Logic;

import java.io.Serializable;

public class NetworkMessage {
    private String messageType;

    public NetworkMessage(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }
}

class RecentActivity extends NetworkMessage implements Serializable {
    private String userName;
    private String songTitle;
    private String artistName;
    private long timeListenedTo;

    public RecentActivity(String userName, String songTitle, String artistName, long time) {
        super("recent activity");
        this.userName = userName;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.timeListenedTo = time;
    }

    public String getUserName() {
        return userName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public long getTimeListenedTo() {
        return timeListenedTo;
    }

    public void update(RecentActivity ra){
        this.songTitle = ra.getSongTitle();
        this.artistName = ra.getArtistName();
        this.timeListenedTo = ra.getTimeListenedTo();
    }

    @Override
    public String toString() {
        return "RecentActivity{" +
                "userName='" + userName + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", artistName='" + artistName + '\'' +
                ", timeListenedTo=" + timeListenedTo +
                '}';
    }
}