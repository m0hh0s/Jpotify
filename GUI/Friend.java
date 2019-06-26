package GUI;

public class Friend {
    private String name;

    private boolean isOnline = true;

    private int time;

    //this should become Song in future
    private String song;

    public Friend(String name, String song) {
        this.name = name;
        this.song = song;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getSong() {
        return song;
    }

    public int getTime() {
        return time;
    }
}
