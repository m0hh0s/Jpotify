package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayLists extends JPanel {
    private final int EMPTY_SPACE = 14;
    private final PlayList favourite = new PlayList("Favourite");
    private final PlayList sharedSongs = new PlayList("shared songs");
    private ArrayList<PlayList> playLists = new ArrayList<>();
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    public PlayLists(){
        super();
        this.setLayout(boxLayout);
        addPlayList(favourite);
        addPlayList(sharedSongs);
        addPlayListButton();
    }
    public void addPlayList(PlayList playList){
        playLists.add(playList);
    }
    private void addPlayListButton(){
        for(PlayList playList:playLists){
            JPanel gridPanel = new JPanel(new GridLayout(1,1));
            gridPanel.setMaximumSize(new Dimension(140,40));
            JButton temp = new JButton(playList.getName());
            temp.setFocusPainted(false);
            temp.setBorderPainted(false);
            temp.setBackground(Color.WHITE);
            gridPanel.add(temp);
            this.add(gridPanel);
        }
    }
}
