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
    private ImageIcon plusIcon = new ImageIcon("plus.png");
    private JButton plusButtonForSong = new JButton();
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

    public LibraryAndPlayListArea() throws IOException, FontFormatException {
        super();
        setImageIconForLabel("SongImage.png",img);
        prepareButtonToAdd(plusButtonForSong,"plusWhite2.png","Add Song");
        prepareButtonToAdd(plusLabelForPlayList,"plusWhite2.png","Add PlayList");
        prepareButtonToAdd(recentlyPlayed,"Recently Played");
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
        secondGridPanel.add(plusButtonForSong);
        this.add(secondGridPanel);
        this.add(playListScroll);
        thirdGridPanel.add(plusLabelForPlayList);
        this.add(thirdGridPanel);
        imagePanel.add(img);
        this.add(imagePanel);
    }

    private void prepareButtonToAdd(JButton btn , String path, String name) throws IOException {
        setImageIconForButton(path,btn,28);
        btn.setText(name);
        Font font = new Font("serif",Font.BOLD,12);
        btn.setFont(font);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.LIGHT_GRAY);
//        btn.setHorizontalAlignment(SwingConstants.LEFT);
    }
    private void prepareButtonToAdd(JButton btn, String name){
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        Font font = new Font("Garamond",Font.BOLD,15);
        btn.setFont(font);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(70,25));
    }
    private void prepareLabelToAdd(JLabel label, String name){
        label.setText(name);
    }
    private void prepareScrollToAdd(JScrollPane scrollPane, String name) throws IOException, FontFormatException {
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JLabel headerLabel = new JLabel(name);
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("ProzaLibre-SemiBoldItalic.ttf")).deriveFont(12f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //register the font
        ge.registerFont(customFont);
        headerLabel.setFont(customFont);
        scrollPane.setColumnHeaderView(headerLabel);
    }
    private void setImageIconForLabel(String path, JLabel label) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = new BufferedImage(140,140,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,140,140,null);
        graphics2D.dispose();
        label.setIcon(new ImageIcon(finalImg));
    }
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = new BufferedImage(size,size,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        btn.setIcon(new ImageIcon(finalImg));
    }

    public PlayLists getPlayLists() {
        return playLists;
    }
}
//the icon site :
//<div>Icons made by <a href="https://www.flaticon.com/authors/chanut" title="Chanut">Chanut</a> from <a href="https://www.flaticon.com/"             title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/"             title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>