package GUI;

import javax.swing.*;
import java.awt.*;

public class LibraryAndPlayListArea extends JPanel {
    private ImageIcon icon = new ImageIcon("homeIcon.png");
    private JLabel homeLabel = new JLabel(icon);
    public LibraryAndPlayListArea(){
        super();
        this.setLayout(new BorderLayout());
        this.add(homeLabel,BorderLayout.NORTH);
    }
}
