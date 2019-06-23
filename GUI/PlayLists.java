package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayLists extends JPanel {
    private final int EMPTY_SPACE = 14;
    private ArrayList<PlayList> playLists = new ArrayList<PlayList>();
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private ArrayList<JButton> songs = new ArrayList<>();
    public PlayLists(){
        super();
        this.setLayout(boxLayout);

    }
    public void addSongToPlayList(Song song){
        this.add();
    }
}
