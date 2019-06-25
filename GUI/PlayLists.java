package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayLists extends JPanel {
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
        this.setBackground(Color.DARK_GRAY);
    }
    public void addPlayList(PlayList playList){
        playLists.add(playList);
    }
    private void addPlayListButton(){
        for(PlayList playList:playLists){
            JPanel gridPanel = new JPanel(new GridLayout(1,1));
            gridPanel.setMaximumSize(new Dimension(140,40));
            JButton btn = new JButton(playList.getName());
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(Color.DARK_GRAY);
            btn.setForeground(Color.WHITE);
            Font buttonFont = new Font("optima",Font.PLAIN,13);
            btn.setFont(buttonFont);
            gridPanel.add(btn);
            this.add(gridPanel);
        }
    }
    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }
}
