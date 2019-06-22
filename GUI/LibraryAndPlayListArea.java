package GUI;

import javax.swing.*;
import java.awt.*;

public class LibraryAndPlayListArea extends JPanel {
    private final int EMPTY_SPACE = 14;
    private PlayList playList = new PlayList();
    private ImageIcon homeIcon = new ImageIcon("homeIcon.png");
    private ImageIcon plusIcon = new ImageIcon("plusIcon.png");
    private JLabel homeLabel = new JLabel();
    private JLabel plusLabelForSong = new JLabel();
    private JLabel plusLabelForPlayList = new JLabel();
    private JLabel yourLibraryHead = new JLabel();
    private JLabel recentlyPlayed = new JLabel();
    private JLabel songs = new JLabel();
    private JLabel albums = new JLabel();
    private JLabel artist = new JLabel();
    private JLabel playListLabel = new JLabel();
    private JScrollPane playListScroll = new JScrollPane(playList);


    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    public LibraryAndPlayListArea(){
        super();
        homeLabel.setIcon(homeIcon);
        homeLabel.setText("Home");
        plusLabelForSong.setIcon(plusIcon);
        plusLabelForSong.setText("Add Song");
        plusLabelForPlayList.setIcon(plusIcon);
        plusLabelForPlayList.setText("Add PlayList");
        yourLibraryHead.setText("YOUR LIBRARY");
        recentlyPlayed.setText("RecentlyPlayed");
        songs.setText("Songs");
        albums.setText("Albums");
        artist.setText("Artist");
        playListLabel.setText("PlayList");

        // add 4 space to make them all align together
        playListScroll.setColumnHeaderView(new JLabel("    PLAYLIST"));
        this.setLayout(boxLayout);
        this.add(homeLabel);
        this.add(Box.createRigidArea(new Dimension(EMPTY_SPACE*2,EMPTY_SPACE*2)));
        this.add(yourLibraryHead);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE/2)));
        this.add(recentlyPlayed);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(songs);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(playListLabel);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(albums);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(artist);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(plusLabelForSong);
        this.add(playListScroll);
        this.add(plusLabelForPlayList);
    }
}
