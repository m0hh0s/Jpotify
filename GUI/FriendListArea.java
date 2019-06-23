package GUI;

import javax.swing.*;
import java.awt.*;

public class FriendListArea extends JPanel {
    private GridLayout gridLayout = new GridLayout(0,1);
    private JButton imaginaryFriend = new JButton();
    public FriendListArea(){
        super();
        this.setMaximumSize(new Dimension(140,50));
        this.setLayout(gridLayout);
    }
    private void prepareButtonToAdd(Friend friend){

    }
}
