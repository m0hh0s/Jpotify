package GUI;

import Logic.MusicPlayer;
import Logic.Playlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class PlayLists extends JPanel {
    private ArrayList<Playlist> playLists = new ArrayList<>();
    private Color NEAR_BLACK = new Color(28,28,28);
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private Playlist playlistToRemoveFrom = null;

    public PlayLists(){
        super();
        this.setLayout(boxLayout);
        this.setBackground(NEAR_BLACK);
    }
    public void addPlayList(Playlist playList){
        playLists.add(playList);
        addPlayListButton();
    }
    public void addPlayListButton(){
        this.removeAll();
        for(Playlist playList:playLists){
            JPanel gridPanel = new JPanel(new GridLayout(1,1));
            gridPanel.setMaximumSize(new Dimension(140,40));
            JButton btn = new JButton(playList.getName());
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(NEAR_BLACK);
            btn.setForeground(Color.WHITE);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (playList.getSongs().size() > 0) {
                        try {
                            playlistToRemoveFrom = playList;
                            CenterArea centerArea = new CenterArea(playlistToRemoveFrom);
                            centerArea.preparePlayListsToAdd(playList.getSongs() , "playlist");
                            MusicPlayer.getJpotifyGUI().changeCenterArea(centerArea);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            Font buttonFont = new Font("optima",Font.PLAIN,13);
            btn.setFont(buttonFont);
            gridPanel.add(btn);
            this.add(gridPanel);
        }
    }

    public void setPlayLists(ArrayList<Playlist> playLists) {
        this.playLists = playLists;
        addPlayListButton();
    }

    public int searchByName(String name){
        for(Playlist playlist:playLists){
            if(playlist.getName().equals(name)){
                return 1;
            }
        }
        return 0;
    }
}
