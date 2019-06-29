//بسم الله الرحمن الرحیم
package GUI;

import Logic.Playlist;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * the class JpotifyGUI makes the main frame of the
 * project and also keeps the data of the component
 * within
 *
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */

public class JpotifyGUI extends JFrame {
    private final String WINDOWS_TITLE = "Jpotify";
    private final int WIDTH = 1132, HEIGHT = 700;
    private final int X = 70, Y = 70;
    private ArrayList<Playlist> playLists;
    private LibraryAndPlayListArea libraryAndPlayListArea = new LibraryAndPlayListArea();
    private MusicPlayerArea musicPlayerArea = new MusicPlayerArea();
    private FriendListArea friendListArea = new FriendListArea();
    private CenterArea centerArea ;
    private JScrollPane friendScrollPane = new JScrollPane(friendListArea);
    private JScrollPane libraryScrollPane = new JScrollPane(libraryAndPlayListArea);
    private JScrollPane centerScrollPane = new JScrollPane();
    private JLabel friendAreaHeader = new JLabel("Friend Activity");

    //must add space after it
    private JLabel id = new JLabel("97310**  ");
    private JPanel eastArea = new JPanel(new BorderLayout());
    private JPanel centerAndWestArea = new JPanel(new BorderLayout());
    private JPanel idPanel = new JPanel(new BorderLayout());
    private Color NEAR_BLACK = new Color(28,28,28);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);

    /**
     * the constructor makes makes the components within and
     * set its sizes and background color also set the size of
     * the frame and the title
     *
     * @throws IOException
     * @throws FontFormatException
     */
    public JpotifyGUI() throws IOException, FontFormatException {
        super();
        //playLists = libraryAndPlayListArea.getPlayLists().getPlayLists();
        centerArea = new CenterArea();
        centerScrollPane.setViewportView(centerArea);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(NEAR_VERY_DARK_GRAY));
        centerScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        friendScrollPane.setPreferredSize(new Dimension(140,500));
        friendScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        friendScrollPane.setBackground(NEAR_BLACK);
        libraryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(NEAR_BLACK));
        libraryScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        friendScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        eastArea.setBackground(NEAR_BLACK);
        Font friendAreaHeaderFont = addFont("Fonts/Proza_Libre/ProzaLibre-SemiBoldItalic.ttf",12);
        friendAreaHeader.setForeground(Color.WHITE);
        friendAreaHeader.setFont(friendAreaHeaderFont);
        eastArea.add(friendAreaHeader,BorderLayout.NORTH);
        eastArea.add(friendScrollPane,BorderLayout.CENTER);

        id.setForeground(Color.WHITE);
        idPanel.add(id,BorderLayout.EAST);
        idPanel.setBackground(NEAR_BLACK);
        centerAndWestArea.add(idPanel,BorderLayout.NORTH);
        centerAndWestArea.add(centerScrollPane);
        centerAndWestArea.add(libraryScrollPane,BorderLayout.WEST);
        this.setTitle(WINDOWS_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        this.setLayout(new BorderLayout());
        this.add(musicPlayerArea,BorderLayout.SOUTH);
        this.add(eastArea,BorderLayout.EAST);
        this.add(centerAndWestArea);
//        this.setVisible(true);
    }

    /**
     * adds a font to the class
     *
     * @param path the path of the font FIle
     * @param size the size of the font which we want to make
     * @return the font with the size and the path
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

    /**
     * changes the centerArea
     *
     * @param centerArea the new centerArea
     */
    public void changeCenterArea (CenterArea centerArea){
        centerScrollPane.setViewportView(centerArea);
    }

    /**
     * set the id
     *
     * @param id the label of the north panel
     */
    public void setId (String id){
        this.id.setText(id+"   ");
    }

    /**
     *
     * @return the library section
     */
    public LibraryAndPlayListArea getLibraryAndPlayListArea() {
        return libraryAndPlayListArea;
    }

    /**
     *
     * @return the music player section
     */
    public MusicPlayerArea getMusicPlayerArea() {
        return musicPlayerArea;
    }


//    public FriendListArea getFriendListArea() {
//        return friendListArea;
//    }
//    public JScrollPane getFriendScrollPane() {
//        return friendScrollPane;
//    }
//    public JScrollPane getLibraryScrollPane() {
//        return libraryScrollPane;
//    }
//    public ArrayList<Playlist> getPlayLists() {
//        return playLists;
//    }
//    public CenterArea getCenterArea() {
//        return centerArea;
//    }
//    public JLabel getFriendAreaHeader() {
//        return friendAreaHeader;
//    }
//    public JPanel getEastArea() {
//        return eastArea;
//    }
}
