package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FriendListArea extends JPanel {
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private ArrayList<JButton> friendsButtons = new ArrayList<>();
    private ArrayList<Friend> friends = new ArrayList<>();
    private ImageIcon onlineIcon = new ImageIcon("Icons/onlineIcon.png");
    private Color NEAR_BLACK = new Color(28,28,28);

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
    private void prepareButtonToAdd(ArrayList<Friend> friends) throws IOException {
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
            temp.setFocusPainted(false);
            temp.setBorderPainted(false);
            temp.setBackground(NEAR_BLACK);
            temp.setForeground(Color.WHITE);
            tmp.add(temp);
            this.add(tmp);
            friendsButtons.add(temp);
        }
    }
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        Image image = img.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(image));
    }
    private void addFriend(Friend friend){
        friends.add(friend);
    }
}
