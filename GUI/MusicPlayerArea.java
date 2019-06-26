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
    private JButton replayButton = new JButton();
    private JButton playButton = new JButton();
    private JButton forwardButton = new JButton();
    private JButton backwardButton = new JButton();
    private JButton addToPlayList = new JButton();
    private JLabel timePassed = new JLabel("00:00");
    private JLabel timeRemaining = new JLabel("00:00");
    private JLabel songName = new JLabel();
    private JLabel songSinger = new JLabel();
    private JPanel northPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel southPanel = new JPanel(new FlowLayout());
    private JPanel westPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new BorderLayout());
    private JPanel gridSongInfo = new JPanel(new GridLayout(2,1));
    private Color NEAR_VERY_DARK_GRAY = new Color(70,70,70);


    public MusicPlayerArea() throws IOException {
        super();
        this.setLayout(new BorderLayout());

        soundSlider.setPreferredSize(new Dimension(70,0));
        soundSlider.setBackground(NEAR_VERY_DARK_GRAY);
        timeSlider.setBackground(NEAR_VERY_DARK_GRAY);
        timeSlider.setForeground(Color.ORANGE);
        //still have problem with this line
        timeSlider.setPreferredSize(new Dimension(500,14));
        timePassed.setForeground(Color.WHITE);
        timeRemaining.setForeground(Color.WHITE);
        prepareButtonToAdd(replayButton,"Icons/replayIconWhite.png",20);
        prepareButtonToAdd(playButton,"Icons/playIconWhite.png",40);
        prepareButtonToAdd(backwardButton,"Icons/backwardIconWhite.png",20);
        prepareButtonToAdd(forwardButton,"Icons/forwardIconWhite.png",20);
        prepareButtonToAdd(addToPlayList,"Icons/listFreeIconWhite.png",20);
        buttonPanel.add(replayButton);
        buttonPanel.add(backwardButton);
        buttonPanel.add(playButton);
        buttonPanel.add(forwardButton);
        buttonPanel.add(addToPlayList);
       northPanel.add(buttonPanel,BorderLayout.CENTER);
//        northPanel.add(soundSlider,BorderLayout.EAST);
        southPanel.add(timeRemaining);
        southPanel.add(timeSlider);
        southPanel.add(timePassed);
        gridSongInfo.setMaximumSize(new Dimension(200,70));
        gridSongInfo.add(songName);
        gridSongInfo.add(songSinger);
        westPanel.add(gridSongInfo,BorderLayout.NORTH);
        centerPanel.add(southPanel,BorderLayout.SOUTH);
        centerPanel.add(northPanel,BorderLayout.NORTH);
        this.add(westPanel,BorderLayout.WEST);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(soundSlider,BorderLayout.EAST);
        buttonPanel.setBackground(NEAR_VERY_DARK_GRAY);
        northPanel.setBackground(NEAR_VERY_DARK_GRAY);
        southPanel.setBackground(NEAR_VERY_DARK_GRAY);
        gridSongInfo.setBackground(NEAR_VERY_DARK_GRAY);
        westPanel.setBackground(NEAR_VERY_DARK_GRAY);
        centerPanel.setBackground(NEAR_VERY_DARK_GRAY);
        Font songNameFont = addFont("Fonts/greataris_destain-alternative/destain.ttf",19);
        Font songSingerFont = addFont("Fonts/greataris_destain-alternative/destain.ttf",14);
        songName.setForeground(Color.WHITE);
        songName.setText("Your Safe With Me");
        songName.setFont(songNameFont);
        songSinger.setForeground(Color.WHITE);
        songSinger.setText("Sheriff");
        songSinger.setFont(songSingerFont);
    }
    private void prepareButtonToAdd(JButton btn,String path,int size) throws IOException {
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(size,size));
        setImageIconForButton(path,btn,size);
        btn.setBackground(NEAR_VERY_DARK_GRAY);
    }
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        Image image = img.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(image));
    }

    private Font addFont(String path,float size)  {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(size);
        } catch (FontFormatException e) {
            System.out.println("the font wan not there");
        } catch (IOException e) {
            System.out.println("the font wan not there");
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(customFont);
        return customFont;
    }

}
