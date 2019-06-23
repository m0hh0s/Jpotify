package GUI;

import javax.swing.*;
import java.awt.*;

public class MusicPlayerArea extends JPanel{
    private JSlider timeSlider = new JSlider();
    private JButton play = new JButton();
    public MusicPlayerArea(){
        super();
//        timeSlider.set
        this.setLayout(new BorderLayout());
        this.add(timeSlider);
    }
}
