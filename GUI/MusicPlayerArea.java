package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JLabel img = new JLabel();
    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel southPanel = new JPanel(new BorderLayout());
    private JPanel westPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new BorderLayout());
    private JPanel southOfNorthPanel = new JPanel(new BorderLayout());
    public MusicPlayerArea() throws IOException {
        super();
        this.setLayout(new BorderLayout());
        soundSlider.setPreferredSize(new Dimension(70,0));
        setImageIcon("SongImage.png",img);
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
        westPanel.add(img);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(westPanel,BorderLayout.WEST);
    }
    private void prepareButtonToAdd(JButton btn ,ImageIcon icon){
        btn.setIcon(icon);
        btn.setPreferredSize(new Dimension(BUTTON_SIZE,BUTTON_SIZE));
    }
    private void setImageIcon(String path, JLabel label) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = new BufferedImage(140,140,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,140,140,null);
        graphics2D.dispose();
        label.setIcon(new ImageIcon(finalImg));
    }
}
