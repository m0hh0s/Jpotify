package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CenterArea extends JPanel{
    private ArrayList<PlayList> playLists ;
    private JPanel flowLayoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    public CenterArea(ArrayList<PlayList> playLists) throws IOException {
        super();
        this.setLayout(new BorderLayout());
        this.playLists = playLists;
        this.setBackground(VERY_DARK_GRAY);
        flowLayoutPanel.setBackground(VERY_DARK_GRAY);
        preparePlayListsToAdd();
        this.add(flowLayoutPanel);
    }
    private void preparePlayListsToAdd() throws IOException {
        for(PlayList playList:playLists) {
            JPanel motherGridPanel = new JPanel(new BorderLayout());
//            motherGridPanel.setLayout(new BoxLayout(motherGridPanel,BoxLayout.PAGE_AXIS));
            JPanel buttonPanel = new JPanel(new GridLayout(1,1));
            JPanel labelPanel = new JPanel(new GridLayout(1,1));
            JLabel label = new JLabel(playList.getName());
            JButton btn = new JButton();
            motherGridPanel.setBackground(VERY_DARK_GRAY);
            buttonPanel.setBackground(VERY_DARK_GRAY);
            labelPanel.setBackground(VERY_DARK_GRAY);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("optima",Font.BOLD,12));
            motherGridPanel.setMaximumSize(new Dimension(200,250));
            buttonPanel.setMaximumSize(new Dimension(200,200));
            btn.setPreferredSize(new Dimension(205,205));
            labelPanel.setMaximumSize(new Dimension(200,50));
            btn.setSize(new Dimension(200,200));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            this.setImageIcon("Icons/anonymousMusicIcon.jpg", btn,200);
            buttonPanel.add(btn);
            labelPanel.add(label);
            motherGridPanel.add(buttonPanel,BorderLayout.NORTH);
            motherGridPanel.add(labelPanel,BorderLayout.SOUTH);
            flowLayoutPanel.add(motherGridPanel,FlowLayout.LEFT);
        }

    }
    private void setImageIcon(String path, JButton button,int size)  {
        //duplicate code
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("the path is wrong and btw im in centerArea");
        }
        BufferedImage finalImg = new BufferedImage(size,size,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        button.setIcon(new ImageIcon(finalImg));
    }
}
