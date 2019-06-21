package GUI;

import javax.swing.*;
import java.awt.*;

public class LibraryAndPlayListArea extends JPanel {
    private final int EMPTY_SPACE = 14;
    private ImageIcon icon = new ImageIcon("homeIcon.png");
    private JLabel homeLabel = new JLabel();
    private JLabel yourLibrary = new JLabel();
    private JLabel recentlyPlayed = new JLabel();
    private JLabel songs = new JLabel();
    private JLabel albums = new JLabel();
    private JLabel artist = new JLabel();

    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    public LibraryAndPlayListArea(){
        super();
        homeLabel.setIcon(icon);
        homeLabel.setText("Home");
        yourLibrary.setText("Your Library");
        recentlyPlayed.setText("RecentlyPlayed");
        songs.setText("Songs");
        albums.setText("Albums");
        artist.setText("Artist");
        this.setLayout(boxLayout);
        this.add(homeLabel);
        this.add(Box.createRigidArea(new Dimension(0,28)));
        this.add(yourLibrary);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(recentlyPlayed);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(songs);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(albums);
        this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        this.add(artist);
    }
}
