package GUI;

import javax.swing.*;
import java.awt.*;
/**
 * the Add playList page
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class AddPlayListPage extends JFrame {
    private final String WINDOWS_TITLE = "Add PlayList";
    private final int WIDTH = 500, HEIGHT = 400;
    private final int X = 500, Y = 250;
    private JTextField playListName = new JTextField();
    private JButton addPlayListButton = new JButton("Add");
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);
    private JPanel textPanel = new JPanel(new GridLayout(1,1));
    private JPanel buttonPanel = new JPanel(new GridLayout(1,1));
    private JPanel motherPanel = new JPanel();
    private BoxLayout boxLayout = new BoxLayout(motherPanel,BoxLayout.PAGE_AXIS);

    /**
     * set the layout ,size and title of the frame
     * set the settings for button and add components
     * to the panels
     */
    public AddPlayListPage() {
        super();
        this.setLayout(new BorderLayout());
        this.setTitle(WINDOWS_TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        motherPanel.setBackground(NEAR_VERY_DARK_GRAY);
        motherPanel.setLayout(boxLayout);
//        motherPanel.setMaximumSize(new Dimension(350,120));
        textPanel.setMaximumSize(new Dimension(250, 40));
        buttonPanel.setMaximumSize(new Dimension(250, 50));
        textPanel.add(playListName);
        buttonPanel.add(addPlayListButton);
        addPlayListButton.setBorderPainted(false);
        addPlayListButton.setFocusPainted(false);
        addPlayListButton.setBackground(VERY_DARK_GRAY);
        addPlayListButton.setForeground(Color.WHITE);
        addPlayListButton.setPreferredSize(new Dimension(350, 50));
        addPlayListButton.setSize(new Dimension(350, 50));
        motherPanel.add(Box.createRigidArea(new Dimension(5, 140)));
        motherPanel.add(textPanel);
        motherPanel.add(buttonPanel);
        this.add(motherPanel);
        this.setVisible(true);
    }

    public JTextField getPlayListName() {
        return playListName;
    }

    public JButton getAddPlayListButton() {
        return addPlayListButton;
    }
}
