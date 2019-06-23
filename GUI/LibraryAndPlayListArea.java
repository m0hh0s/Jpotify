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
    private JPanel firstGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel firstAndHalfGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel secondGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel thirdGridPanel = new JPanel(new GridLayout(0,1));

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
//        this.add(homeButton);
        firstGridPanel.setMaximumSize(new Dimension(140,20));
        firstAndHalfGridPanel.setMaximumSize(new Dimension(140,20));
        secondGridPanel.setMaximumSize(new Dimension(140,20));
        thirdGridPanel.setMaximumSize(new Dimension(140,20));
        firstGridPanel.add(homeButton);
        this.add(firstGridPanel);
//        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        firstAndHalfGridPanel.add(yourLibraryHead);
//        this.add(yourLibraryHead);
        this.add(firstAndHalfGridPanel);
        secondGridPanel.add(recentlyPlayed);
        secondGridPanel.add(songs);
        secondGridPanel.add(playListButton);
        secondGridPanel.add(albums);
        secondGridPanel.add(artist);
        secondGridPanel.add(plusLabelForSong);
        this.add(secondGridPanel);


//        this.add(recentlyPlayed);
//        this.add(songs);
//        this.add(playListButton);
//        this.add(albums);
//        this.add(artist);
//        this.add(plusLabelForSong);
        this.add(playListScroll);
//        this.add(plusLabelForPlayList);
        thirdGridPanel.add(plusLabelForPlayList);
        this.add(thirdGridPanel);

    }
    private void prepareButtonToAdd(JButton btn , ImageIcon icon, String name){
        btn.setIcon(icon);
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
    }
    private void prepareButtonToAdd(JButton btn, String name){
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(70,25));
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
