package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LibraryAndPlayListArea extends JPanel {
    private PlayLists playLists = new PlayLists();
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private ImageIcon plusIcon = new ImageIcon("plusIcon.png");
    private JButton plusLabelForSong = new JButton();
    private JButton plusLabelForPlayList = new JButton();
    private JButton recentlyPlayed = new JButton();
    private JButton songs = new JButton();
    private JButton albums = new JButton();
    private JButton artist = new JButton();
    private JButton playListButton = new JButton();
    private JLabel img = new JLabel();
    private JScrollPane playListScroll = new JScrollPane(playLists);
    private JPanel secondGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel thirdGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel imagePanel = new JPanel(new GridLayout(1,1));

    public LibraryAndPlayListArea() throws IOException {
        super();
        setImageIcon("SongImage.png",img);
        prepareButtonToAdd(plusLabelForSong,plusIcon,"Add Song");
        prepareButtonToAdd(plusLabelForPlayList,plusIcon,"Add PlayList");
        prepareButtonToAdd(recentlyPlayed,"RecentlyPlayed");
        prepareButtonToAdd(songs,"Songs");
        prepareButtonToAdd(albums,"Albums");
        prepareButtonToAdd(artist,"Artist");
        prepareButtonToAdd(playListButton,"PlayList");
        prepareScrollToAdd(playListScroll,"PLAYLIST");
        this.setLayout(boxLayout);
        secondGridPanel.setMaximumSize(new Dimension(140,20));
        thirdGridPanel.setMaximumSize(new Dimension(140,20));
        imagePanel.setMaximumSize(new Dimension(140,140));
        secondGridPanel.add(recentlyPlayed);
        secondGridPanel.add(songs);
        secondGridPanel.add(playListButton);
        secondGridPanel.add(albums);
        secondGridPanel.add(artist);
        secondGridPanel.add(plusLabelForSong);
        this.add(secondGridPanel);
        this.add(playListScroll);
        thirdGridPanel.add(plusLabelForPlayList);
        this.add(thirdGridPanel);
        imagePanel.add(img);
        this.add(imagePanel);
    }
    private void prepareButtonToAdd(JButton btn , ImageIcon icon, String name){
        btn.setIcon(icon);
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
//        btn.setHorizontalAlignment(SwingConstants.LEFT);
    }
    private void prepareButtonToAdd(JButton btn, String name){
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setPreferredSize(new Dimension(70,25));
    }
    private void prepareLabelToAdd(JLabel label, String name){
        label.setText(name);
    }
    private void prepareScrollToAdd(JScrollPane scrollPane, String name){
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setColumnHeaderView(new JLabel(name));
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
