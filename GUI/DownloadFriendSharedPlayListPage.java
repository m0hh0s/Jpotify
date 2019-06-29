package GUI;

import Logic.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * the page to download the friends
 * shared playList
 */
public class DownloadFriendSharedPlayListPage extends JFrame {
    private final String WINDOWS_TITLE = "Friends";
    private final int WIDTH = 500, HEIGHT = 400;
    private final int X = 500, Y = 250;
    private ArrayList<User> users ;
    private JPanel motherPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(motherPanel);
    private BoxLayout boxLayout = new BoxLayout(motherPanel,BoxLayout.PAGE_AXIS);
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);

    /**
     * set the users and makes button for each friend
     * @param users
     */
    public DownloadFriendSharedPlayListPage(ArrayList<User> users){
        super();
        this.setLayout(new BorderLayout());
        this.setTitle(WINDOWS_TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(X, Y);
        this.users = users;
        motherPanel.setLayout(boxLayout);
        motherPanel.setBackground(VERY_DARK_GRAY);
        motherPanel.add(Box.createRigidArea(new Dimension(0, 14)));
        for(User user:users){
            JPanel buttonPanel = new JPanel(new GridLayout(1,1));
            JButton friendNameButton = new JButton(user.getUsername());
            friendNameButton.setForeground(Color.WHITE);
            friendNameButton.setMinimumSize(new Dimension(300,50));
            buttonPanel.setMaximumSize(new Dimension(300,50));
            friendNameButton.setBorderPainted(false);
            friendNameButton.setFocusPainted(false);
            friendNameButton.setBackground(NEAR_VERY_DARK_GRAY);
            buttonPanel.add(friendNameButton);
            motherPanel.add(buttonPanel);
        }
        this.add(scrollPane);
        this.setVisible(true);
    }
}
