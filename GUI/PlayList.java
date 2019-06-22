package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayList extends JPanel {
    private final int EMPTY_SPACE = 14;
    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.PAGE_AXIS);
    private ArrayList<JLabel> playList = new ArrayList<>();
    public PlayList(){
        super();
        this.setLayout(boxLayout);
        for(JLabel pl:playList){
            this.add(pl);
            this.add(Box.createRigidArea(new Dimension(0,EMPTY_SPACE)));
        }
    }
    public void addPlayList(String name){
        JLabel temp = new JLabel(name);
        this.add(temp);
        playList.add(temp);
    }
}
