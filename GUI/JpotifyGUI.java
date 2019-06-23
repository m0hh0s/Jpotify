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
    private JScrollPane scrollPane = new JScrollPane(libraryAndPlayListArea);
    public JpotifyGUI() throws IOException {
        super();
        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        this.add(scrollPane,BorderLayout.WEST);
        this.add(musicPlayerArea,BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
