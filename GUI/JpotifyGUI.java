//بسم الله الرحمن الرحیم
package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class JpotifyGUI extends JFrame {
    private final String WINDOWS_TITLE = "Jpotify";
    private final int WIDTH = 1132, HEIGHT = 700;
    private final int X = 70, Y = 70;
    private LibraryAndPlayListArea libraryAndPlayListArea = new LibraryAndPlayListArea();
    private MusicPlayerArea musicPlayerArea = new MusicPlayerArea();
    private FriendListArea friendListArea = new FriendListArea();
    private JPanel centerEastAndSouthPanel = new JPanel(new BorderLayout());
    private JScrollPane friendScrollPane = new JScrollPane(friendListArea);
    private JScrollPane libraryScrollPane = new JScrollPane(libraryAndPlayListArea);
    public JpotifyGUI() throws IOException {
        super();
        friendScrollPane.setColumnHeaderView(new JLabel("Friend Activity"));
        friendScrollPane.setPreferredSize(new Dimension(140,500));
        friendScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        centerEastAndSouthPanel.add(musicPlayerArea,BorderLayout.SOUTH);
        centerEastAndSouthPanel.add(friendScrollPane,BorderLayout.EAST);
        this.add(libraryScrollPane,BorderLayout.WEST);
        this.add(centerEastAndSouthPanel,BorderLayout.CENTER);
        this.setVisible(true);
    }
}
