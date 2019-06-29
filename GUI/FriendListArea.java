package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * the Friend section and its
 * component in the east side
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class FriendListArea extends JPanel {
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private ArrayList<JButton> friendsButtons = new ArrayList<>();
    private ArrayList<Friend> friends = new ArrayList<>();
    private ImageIcon onlineIcon = new ImageIcon("Icons/onlineIcon.png");
    private Color NEAR_BLACK = new Color(28,28,28);

    /**
     * sets the layout and calls to the methods
     * @throws IOException
     */
    public FriendListArea() throws IOException {
        super();
        this.setLayout(boxLayout);
        addFriend(new Friend("sattar","my lovely song"));
        addFriend(new Friend("m_hejrati","his lovely song"));
        Friend tmp = new Friend("m0hh0s","your lovely song");
        this.setBackground(NEAR_BLACK);
        tmp.setTime(50);
        tmp.setOnline(false);
        addFriend(tmp);
        prepareButtonToAdd(friends);
    }

    /**
     * make a button for the friend based
     * on being online or offline
     * @param friends
     * @throws IOException
     */
    private void prepareButtonToAdd(ArrayList<Friend> friends) throws IOException {
        Font buttonFont = new Font("optima",Font.PLAIN,12);
        for(Friend frnd:friends) {
            JButton temp = new JButton();
            JPanel tmp = new JPanel(new GridLayout(0,1));
            tmp.setMaximumSize(new Dimension(140,70));
            if (frnd.isOnline()) {
                String text = frnd.getName() + "\n" + frnd.getSong();
                temp.setText("<html>" + text.replaceAll("\\n", "<br>") + "</html>");
                setImageIconForButton("Icons/onlineIcon.png",temp,18);
            } else {
                String text = frnd.getName() + "  " + frnd.getTime() + "\n" + frnd.getSong();
                temp.setText("<html>" + text.replaceAll("\\n", "<br>") + "</html>");
            }
            temp.setFont(buttonFont);
            temp.setFocusPainted(false);
            temp.setBorderPainted(false);
            temp.setBackground(NEAR_BLACK);
            temp.setForeground(Color.WHITE);
            tmp.add(temp);
            this.add(tmp);
            friendsButtons.add(temp);
        }
    }

    /**
     * set the icon for the button
     * @param path path of icon
     * @param btn
     * @param size size of icon
     * @throws IOException
     */
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        Image image = img.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(image));
    }

    /**
     * add friend to the arrayList of friends
     * @param friend
     */
    private void addFriend(Friend friend){
        friends.add(friend);
    }
}
