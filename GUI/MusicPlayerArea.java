package GUI;

import javax.swing.*;
import java.awt.*;

public class MusicPlayerArea extends JPanel{
    private final int BUTTON_SIZE = 24;
    private JSlider timeSlider = new JSlider();
    private JSlider soundSlider = new JSlider();
    private JButton playButton = new JButton();
    private JButton forwardButton = new JButton();
    private JButton backwardButton = new JButton();
    private ImageIcon backwardIcon = new ImageIcon("backwardIcon.png");
    private ImageIcon playIcon = new ImageIcon("playIcon.png");
    private ImageIcon forwardIcon = new ImageIcon("forwardIcon.png");
    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    public MusicPlayerArea(){
        super();
        this.setLayout(new BorderLayout());
        soundSlider.setPreferredSize(new Dimension(70,0));
        prepareButtonToAdd(backwardButton,backwardIcon);
        prepareButtonToAdd(playButton,playIcon);
        prepareButtonToAdd(forwardButton,forwardIcon);
        buttonPanel.add(backwardButton);
        buttonPanel.add(playButton);
        buttonPanel.add(forwardButton);
        northPanel.add(buttonPanel,BorderLayout.CENTER);
        northPanel.add(soundSlider,BorderLayout.EAST);
        this.add(northPanel,BorderLayout.CENTER);
        this.add(timeSlider,BorderLayout.SOUTH);
    }
    private void prepareButtonToAdd(JButton btn ,ImageIcon icon){
        btn.setIcon(icon);
        btn.setPreferredSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
    }
}
