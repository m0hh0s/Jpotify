//بسم الله الرحمن الرحیم
package GUI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JpotifyGUI extends JFrame {
    private final String WINDOWS_TITLE = "Jpotify";
    private final int WIDTH = 1132, HEIGHT = 700;
    private final int X = 70, Y = 70;
    private LibraryAndPlayListArea libraryAndPlayListArea = new LibraryAndPlayListArea();
    private MusicPlayerArea musicPlayerArea = new MusicPlayerArea();
    private FriendListArea friendListArea = new FriendListArea();
    private JScrollPane friendScrollPane = new JScrollPane(friendListArea);
    private JScrollPane libraryScrollPane = new JScrollPane(libraryAndPlayListArea);
    private ArrayList<PlayList> playLists = new ArrayList<>();
    private CenterArea centerArea ;
    private JLabel friendAreaHeader = new JLabel("Friend Activity");
    private JPanel eastArea = new JPanel(new BorderLayout());
    private Color NEAR_BLACK = new Color(28,28,28);
    public JpotifyGUI() throws IOException, FontFormatException {
        super();
        playLists = libraryAndPlayListArea.getPlayLists().getPlayLists();
        centerArea = new CenterArea(playLists);
        friendScrollPane.setPreferredSize(new Dimension(140,500));
        friendScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        friendScrollPane.setBackground(NEAR_BLACK);
        libraryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        UIManager.put("ScrollBar.thumb", new ColorUIResource(NEAR_BLACK));
        libraryScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        friendScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        eastArea.setBackground(NEAR_BLACK);
        Font friendAreaHeaderfont = addFont("Fonts/Proza_Libre/ProzaLibre-SemiBoldItalic.ttf",12);
        friendAreaHeader.setForeground(Color.WHITE);
        friendAreaHeader.setFont(friendAreaHeaderfont);
        eastArea.add(friendAreaHeader,BorderLayout.NORTH);
        eastArea.add(friendScrollPane,BorderLayout.CENTER);
        this.setTitle(WINDOWS_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        this.setLayout(new BorderLayout());
        this.add(musicPlayerArea,BorderLayout.SOUTH);
        this.add(eastArea,BorderLayout.EAST);
        this.add(centerArea);
        this.add(libraryScrollPane,BorderLayout.WEST);
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

    public LibraryAndPlayListArea getLibraryAndPlayListArea() {
        return libraryAndPlayListArea;
    }

    public MusicPlayerArea getMusicPlayerArea() {
        return musicPlayerArea;
    }

    public FriendListArea getFriendListArea() {
        return friendListArea;
    }

    public JScrollPane getFriendScrollPane() {
        return friendScrollPane;
    }

    public JScrollPane getLibraryScrollPane() {
        return libraryScrollPane;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public CenterArea getCenterArea() {
        return centerArea;
    }

    public JLabel getFriendAreaHeader() {
        return friendAreaHeader;
    }

    public JPanel getEastArea() {
        return eastArea;
    }
}
