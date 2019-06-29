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

/**
 * the library section in the west part of the panel
 * is being made here
 *
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class LibraryAndPlayListArea extends JPanel {
    private PlayLists playLists = new PlayLists();
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private JButton plusButtonForSong = new JButton();
    private JButton plusButtonForPlayList = new JButton();
    private JButton songs = new JButton();
    private JButton albums = new JButton();
    private JButton playListButton = new JButton();
    private JLabel img = new JLabel();
    private JLabel headerLabel = new JLabel("PlayLists");
    private JScrollPane playListScroll = new JScrollPane(playLists);
    private JPanel libraryGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel addPlayListGridPanel = new JPanel(new GridLayout(0,1));
    private JPanel playListHeaderGridPanel = new JPanel(new GridLayout(1,1));
    private JPanel imagePanel = new JPanel(new GridLayout(1,1));
    private Color NEAR_BLACK = new Color(28,28,28);

    /**
     * calls the method to make the library
     * part and also add the panels to
     * this section
     *
     * @throws IOException
     */
    public LibraryAndPlayListArea() throws IOException {
        super();
        this.setLayout(boxLayout);
        this.setBackground(NEAR_BLACK);
        setImageIconForLabel(new FileInputStream("Icons/SongImage.png"));
        prepareButtonToAdd(plusButtonForSong,"Icons/plusIconWhite2.png","Add Song");
        prepareButtonToAdd(plusButtonForPlayList,"Icons/plusIconWhite2.png","Add PlayList");
        prepareButtonToAdd(songs,"Songs");
        prepareButtonToAdd(albums,"Albums");
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

    /**
     * set the background color of button and
     * set its icon and name
     *
     * @param btn button
     * @param path the path of the button icon
     * @param name  the name written on the button
     * @throws IOException
     */
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

    /**
     * this one is for the button without icon
     *
     * @param btn button
     * @param name  the name written on the button
     */
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

    /**
     *  sets the panel background and size
     *
     * @param panel the panel
     * @param dimension the size of panel
     * @param color background color of panel
     */
    private void preparePanelToAdd(JPanel panel,Dimension dimension,Color color){
        panel.setBackground(color);
        panel.setMaximumSize(dimension);
    }

    /**
     * set the knob color
     *
     * @param scrollPane
     */
    private void prepareScrollToAdd(JScrollPane scrollPane) {
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(NEAR_BLACK));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        scrollPane.setBackground(NEAR_BLACK);
        scrollPane.setForeground(Color.WHITE);
    }

    /**
     * add the components to the library panel
     */
    private void prepareLibraryGridPanelToAdd(){
        libraryGridPanel.add(songs);
        libraryGridPanel.add(playListButton);
        libraryGridPanel.add(albums);
        libraryGridPanel.add(plusButtonForSong);
    }

    /**
     * set the font and the color for the header
     */
    private void preparePlayListHeaderGridPanelToAdd(){
        headerLabel.setForeground(Color.WHITE);
        Font headerFont = addFont("Fonts/Proza_Libre/ProzaLibre-SemiBoldItalic.ttf",12);
        headerLabel.setFont(headerFont);
        playListHeaderGridPanel.add(headerLabel);
    }

    /**
     * add component to playList panel
     */
    private void prepareAddPlayListGridPanelToAdd(){
        addPlayListGridPanel.add(plusButtonForPlayList);
    }

    /**
     * add image to image panel
     */
    private void prepareImagePanelToAdd(){
        imagePanel.add(img);
    }

    /**
     * set image icon for label
     *
     * @param is short for input stream
     * @throws IOException
     */
    public void setImageIconForLabel(InputStream is) throws IOException {
        BufferedImage img = ImageIO.read(is);
        BufferedImage finalImg = new BufferedImage(140,140,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,140,140,null);
        graphics2D.dispose();
        this.img.setIcon(new ImageIcon(finalImg));
    }

    /**
     * add icon to the button
     *
     * @param path path of image
     * @param btn
     * @param size size of image
     * @throws IOException
     */
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = new BufferedImage(size,size,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        btn.setIcon(new ImageIcon(finalImg));
    }

    /**
     * add Font to the program
     *
     * @param path
     * @param size size of font
     * @return
     */
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

    public JButton getPlusButtonForPlayList() {
        return plusButtonForPlayList;
    }

    public JButton getSongs() {
        return songs;
    }

    public JButton getAlbums() {
        return albums;
    }

    public JButton getPlayListButton() {
        return playListButton;
    }
}
