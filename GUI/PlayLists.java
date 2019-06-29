package GUI;

import Logic.Playlist;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayLists extends JPanel {
    private ArrayList<Playlist> playLists = new ArrayList<>();
    private Color NEAR_BLACK = new Color(28,28,28);
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);

    public PlayLists(){
        super();
        this.setLayout(boxLayout);
        this.setBackground(NEAR_BLACK);
    }
    public void addPlayList(Playlist playList){
        playLists.add(playList);
        addPlayListButton(playList);
    }
    private void addPlayListButtonForFirstTime(){
        for(Playlist playList:playLists){
            JPanel gridPanel = new JPanel(new GridLayout(1,1));
            gridPanel.setMaximumSize(new Dimension(140,40));
            JButton btn = new JButton(playList.getName());
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(NEAR_BLACK);
            btn.setForeground(Color.WHITE);
            Font buttonFont = new Font("optima",Font.PLAIN,13);
            btn.setFont(buttonFont);
            gridPanel.add(btn);
            this.add(gridPanel);
        }
    }
    private void addPlayListButton(Playlist playlist){
        JPanel gridPanel = new JPanel(new GridLayout(1,1));
        gridPanel.setMaximumSize(new Dimension(140,40));
        JButton btn = new JButton(playlist.getName());
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBackground(NEAR_BLACK);
        btn.setForeground(Color.WHITE);
        Font buttonFont = new Font("optima",Font.PLAIN,13);
        btn.setFont(buttonFont);
        gridPanel.add(btn);
        this.add(gridPanel);
    }
    public void setPlatlists(ArrayList<Playlist> playlists) {
        this.playLists = playlists;
        addPlayListButtonForFirstTime();
    }
}
