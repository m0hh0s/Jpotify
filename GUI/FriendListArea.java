package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FriendListArea extends JPanel {
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private ArrayList<JButton> friendsButtons = new ArrayList<>();
    private ArrayList<Friend> friends = new ArrayList<>();
    private ImageIcon onlineIcon = new ImageIcon("onlineIcon.png");
    public FriendListArea(){
        super();
        this.setLayout(boxLayout);
        addFriend(new Friend("sattar","my lovely song"));
        addFriend(new Friend("m_hejrati","his lovely song"));
        Friend tmp = new Friend("m0hh0s","your lovely song");
        tmp.setTime(50);
        tmp.setOnline(false);
        addFriend(tmp);
        prepareButtonToAdd(friends);
    }
    private void prepareButtonToAdd(ArrayList<Friend> friends){
        for(Friend frnd:friends) {
            JButton temp = new JButton();
            JPanel tmp = new JPanel(new GridLayout(0,1));
            tmp.setMaximumSize(new Dimension(140,70));
            if (frnd.isOnline()) {
                String text = frnd.getName() + "\n" + frnd.getSong();
                temp.setText("<html>" + text.replaceAll("\\n", "<br>") + "</html>");
                temp.setIcon(onlineIcon);
            } else {
                String text = frnd.getName() + "  " + frnd.getTime() + "\n" + frnd.getSong();
                temp.setText("<html>" + text.replaceAll("\\n", "<br>") + "</html>");
            }
            temp.setFocusPainted(false);
            temp.setBorderPainted(false);
            temp.setBackground(Color.WHITE);
            tmp.add(temp);
            this.add(tmp);
            friendsButtons.add(temp);
        }
    }
    private void addFriend(Friend friend){
        friends.add(friend);
    }
}
