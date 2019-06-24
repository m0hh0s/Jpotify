package GUI;

import javax.swing.*;
import java.awt.*;

public class MusicPlayerArea extends JPanel{
    private final int BUTTON_SIZE = 24;
    private JSlider timeSlider = new JSlider(0,1000,0);
    private JSlider soundSlider = new JSlider();
    private JButton playButton = new JButton();
    private JButton forwardButton = new JButton();
    private JButton backwardButton = new JButton();
    private JButton addToPlayList = new JButton();
    private ImageIcon backwardIcon = new ImageIcon("backwardIcon.png");
    private ImageIcon playIcon = new ImageIcon("playIcon.png");
    private ImageIcon forwardIcon = new ImageIcon("forwardIcon.png");
    private ImageIcon plusIcon = new ImageIcon("plusIcon.png");
    private JLabel timePassed = new JLabel("00:00:00");
    private JLabel timeRemaining = new JLabel("00:00:00");
    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel southPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new BorderLayout());
    private JPanel southOfNorthPanel = new JPanel(new BorderLayout());
    public MusicPlayerArea()  {
        super();
        this.setLayout(new BorderLayout());
        soundSlider.setPreferredSize(new Dimension(70,0));
        prepareButtonToAdd(backwardButton,backwardIcon);
        prepareButtonToAdd(playButton,playIcon);
        prepareButtonToAdd(forwardButton,forwardIcon);
        prepareButtonToAdd(addToPlayList,plusIcon);
        buttonPanel.add(backwardButton);
        buttonPanel.add(playButton);
        buttonPanel.add(forwardButton);
        buttonPanel.add(addToPlayList);
        northPanel.add(buttonPanel,BorderLayout.CENTER);
        northPanel.add(soundSlider,BorderLayout.EAST);
        southOfNorthPanel.add(northPanel,BorderLayout.SOUTH);
        southPanel.add(timeSlider,BorderLayout.CENTER);
        southPanel.add(timePassed,BorderLayout.WEST);
        southPanel.add(timeRemaining,BorderLayout.EAST);
        centerPanel.add(southOfNorthPanel,BorderLayout.CENTER);
        centerPanel.add(southPanel,BorderLayout.SOUTH);
        this.add(centerPanel,BorderLayout.CENTER);
    }
    private void prepareButtonToAdd(JButton btn ,ImageIcon icon){
        btn.setIcon(icon);
        btn.setPreferredSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
    }

}
