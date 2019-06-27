package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LibraryAndPlayListArea extends JPanel {
    private PlayLists playLists = new PlayLists();
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private JButton plusButtonForSong = new JButton();
    private JButton plusLabelForPlayList = new JButton();
    private JButton recentlyPlayed = new JButton();
    private JButton songs = new JButton();
    private JButton albums = new JButton();
    private JButton artist = new JButton();
    private JButton playListButton = new JButton();
    private JLabel img = new JLabel();
    private JLabel headerLabel = new JLabel("PlayLists");
    private JScrollPane playListScroll = new JScrollPane(playLists);
    private JPanel libraryGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel addPlayListGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel playListHeaderGridPanel = new JPanel(new GridLayout(1,1));
    private JPanel imagePanel = new JPanel(new GridLayout(1,1));
    private Color NEAR_BLACK = new Color(28,28,28);
    public LibraryAndPlayListArea() throws IOException {
        super();
        this.setLayout(boxLayout);
        this.setBackground(NEAR_BLACK);
        setImageIconForLabel(new FileInputStream("Icons/SongImage.png"));
        prepareButtonToAdd(plusButtonForSong,"Icons/plusIconWhite2.png","Add Song");
        prepareButtonToAdd(plusLabelForPlayList,"Icons/plusIconWhite2.png","Add PlayList");
        prepareButtonToAdd(recentlyPlayed,"Recently Played");
        prepareButtonToAdd(songs,"Songs");
        prepareButtonToAdd(albums,"Albums");
        prepareButtonToAdd(artist,"Artists");
        prepareButtonToAdd(playListButton,"PlayLists");
        prepareScrollToAdd(playListScroll);
        preparePanelToAdd(libraryGridPanel,new Dimension(140,20),NEAR_BLACK);
        preparePanelToAdd(addPlayListGridPanel,new Dimension(140,20),NEAR_BLACK);
        preparePanelToAdd(imagePanel,new Dimension(140,140),NEAR_BLACK);
        preparePanelToAdd(playListHeaderGridPanel,new Dimension(140,10),NEAR_BLACK);
        prepareLibraryGridPanelToAdd();
        preparePlayListHeaderGridPanelToAdd();
        prepareAddPlayListGridPanelToAdd();
        prepareImagePanelToAdd();
        this.add(libraryGridPanel);
        this.add(playListHeaderGridPanel);
        this.add(playListScroll);
        this.add(addPlayListGridPanel);
        this.add(imagePanel);
    }

    private void prepareButtonToAdd(JButton btn , String path, String name) throws IOException {
        setImageIconForButton(path,btn,28);
        btn.setText(name);
        Font font = new Font("serif",Font.BOLD,12);
        btn.setFont(font);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setBackground(NEAR_BLACK);
        btn.setForeground(Color.LIGHT_GRAY);
//        btn.setHorizontalAlignment(SwingConstants.LEFT);
    }
    private void prepareButtonToAdd(JButton btn, String name)  {
        btn.setText(name);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        Font buttonFont = addFont("Fonts/garamond/garmndmi.ttf",15);
        btn.setFont(buttonFont);
        btn.setBackground(NEAR_BLACK);
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(70,25));
    }
    private void preparePanelToAdd(JPanel panel,Dimension dimension,Color color){
        panel.setBackground(color);
        panel.setMaximumSize(dimension);
    }
    private void prepareScrollToAdd(JScrollPane scrollPane) {
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(NEAR_BLACK));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        scrollPane.setBackground(NEAR_BLACK);
        scrollPane.setForeground(Color.WHITE);
    }
    private void prepareLibraryGridPanelToAdd(){
        libraryGridPanel.add(recentlyPlayed);
        libraryGridPanel.add(songs);
        libraryGridPanel.add(playListButton);
        libraryGridPanel.add(albums);
        libraryGridPanel.add(artist);
        libraryGridPanel.add(plusButtonForSong);
    }
    private void preparePlayListHeaderGridPanelToAdd(){
        headerLabel.setForeground(Color.WHITE);
        Font headerFont = addFont("Fonts/Proza_Libre/ProzaLibre-SemiBoldItalic.ttf",12);
        headerLabel.setFont(headerFont);
        playListHeaderGridPanel.add(headerLabel);
    }
    private void prepareAddPlayListGridPanelToAdd(){
        addPlayListGridPanel.add(plusLabelForPlayList);
    }
    private void prepareImagePanelToAdd(){
        imagePanel.add(img);
    }
    public void setImageIconForLabel(InputStream is) throws IOException {
        BufferedImage img = ImageIO.read(is);
        BufferedImage finalImg = new BufferedImage(140,140,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,140,140,null);
        graphics2D.dispose();
        this.img.setIcon(new ImageIcon(finalImg));
    }
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = new BufferedImage(size,size,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        btn.setIcon(new ImageIcon(finalImg));
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
    public PlayLists getPlayLists() {
        return playLists;
    }

    public JButton getPlusButtonForSong() {
        return plusButtonForSong;
    }

    public JButton getPlusLabelForPlayList() {
        return plusLabelForPlayList;
    }

    public JButton getRecentlyPlayed() {
        return recentlyPlayed;
    }

    public JButton getSongs() {
        return songs;
    }

    public JButton getAlbums() {
        return albums;
    }

    public JButton getArtist() {
        return artist;
    }

    public JButton getPlayListButton() {
        return playListButton;
    }
}
