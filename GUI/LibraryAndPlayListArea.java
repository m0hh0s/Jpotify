package GUI;

import javax.swing.*;
import java.awt.*;

public class LibraryAndPlayListArea extends JPanel {
    private final int EMPTY_SPACE = 14;
    private PlayList playList = new PlayList();
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private ImageIcon homeIcon = new ImageIcon("homeIcon.png");
    private ImageIcon plusIcon = new ImageIcon("plusIcon.png");
    private JButton homeButton = new JButton();
    private JButton plusLabelForSong = new JButton();
    private JButton plusLabelForPlayList = new JButton();
    private JLabel yourLibraryHead = new JLabel();
    private JButton recentlyPlayed = new JButton();
    private JButton songs = new JButton();
    private JButton albums = new JButton();
    private JButton artist = new JButton();
    private JButton playListButton = new JButton();
    private JScrollPane playListScroll = new JScrollPane(playList);
    public LibraryAndPlayListArea(){
        super();
        prepareButtonToAdd(homeButton,homeIcon,"Home");
        prepareButtonToAdd(plusLabelForSong,plusIcon,"Add Song");
        prepareButtonToAdd(plusLabelForPlayList,plusIcon,"Add PlayList");
        prepareButtonToAdd(recentlyPlayed,"RecentlyPlayed");
        prepareLabelToAdd(yourLibraryHead,"YOUR LIBRARY");
        prepareButtonToAdd(songs,"Songs");
        prepareButtonToAdd(albums,"Albums");
        prepareButtonToAdd(artist,"Artist");
        prepareButtonToAdd(playListButton,"PlayList");
        prepareScrollToAdd(playListScroll,"PLAYLIST");

        this.setLayout(boxLayout);
        this.add(homeButton);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(yourLibraryHead);
        this.add(recentlyPlayed);
        this.add(songs);
        this.add(playListButton);
        this.add(albums);
        this.add(artist);
        this.add(plusLabelForSong);
        this.add(playListScroll);
        this.add(plusLabelForPlayList);
    }
    private void prepareButtonToAdd(JButton btn , ImageIcon icon, String name){
        btn.setIcon(icon);
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
    }
    private void prepareButtonToAdd(JButton btn, String name){
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
    }
    private void prepareLabelToAdd(JLabel label, String name){
        label.setText(name);
    }
    private void prepareScrollToAdd(JScrollPane scrollPane, String name){
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setColumnHeaderView(new JLabel(name));
    }
}
