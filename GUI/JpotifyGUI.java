//بسم الله الرحمن الرحیم
package GUI;

import javax.swing.*;
import java.awt.*;

public class JpotifyGUI extends JFrame {
    private final String WINDOWS_TITLE = "Jpotify";
    private final int WIDTH = 1132, HEIGHT = 700;
    private final int X = 70, Y = 70;
    private LibraryAndPlayListArea libraryAndPlayListArea = new LibraryAndPlayListArea();
    public JpotifyGUI(){
        super();
        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        this.add(libraryAndPlayListArea,BorderLayout.WEST);
        this.setVisible(true);
    }
}
