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
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);

    public CenterArea(ArrayList<PlayList> playLists) throws IOException {
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.playLists = playLists;
        this.setBackground(VERY_DARK_GRAY);
        preparePlayListsToAdd();
    }
    private void preparePlayListsToAdd() throws IOException {

        //  btn artwork
        //  label title

        for(PlayList playList:playLists) {
            JButton listButton = new JButton();
            JButton deleteButton = new JButton();
            listButton.setLayout(new BorderLayout());

            JPanel labelPanel = new JPanel(new BorderLayout());
            JPanel motherPanel = new JPanel(new BorderLayout());
            JLabel topLabel = new JLabel();
            JLabel bottomLabel = new JLabel();
            JLabel artWork = new JLabel();
            Font topLabelFont = new Font("optima",Font.PLAIN,14);
            Font bottomLabelFont = new Font("optima",Font.BOLD,12);

            setImageIconForLabel("Icons/anonymousMusicIcon.png",artWork,50);

            listButton.setMaximumSize(new Dimension(700,70));
            listButton.setBorderPainted(false);
            listButton.setFocusPainted(false);

            setImageIconForButton("Icons/crossIcon.png",deleteButton,28);

            deleteButton.setMaximumSize(new Dimension(28,28));
            deleteButton.setBorderPainted(false);
            deleteButton.setFocusPainted(false);
            deleteButton.setBackground(NEAR_VERY_DARK_GRAY);

            //must add space before the song and artist name
            topLabel.setText("   Your Safe with me");
            topLabel.setFont(topLabelFont);
            bottomLabel.setText("   Sheriff");
            bottomLabel.setFont(bottomLabelFont);
            topLabel.setForeground(Color.WHITE);
            bottomLabel.setForeground(Color.WHITE);

            labelPanel.add(topLabel,BorderLayout.NORTH);
            labelPanel.add(bottomLabel,BorderLayout.SOUTH);

            motherPanel.add(labelPanel,BorderLayout.WEST);
            listButton.add(motherPanel);
            listButton.add(artWork,BorderLayout.WEST);
            listButton.add(deleteButton,BorderLayout.EAST);
            motherPanel.setBackground(NEAR_VERY_DARK_GRAY);
            labelPanel.setBackground(NEAR_VERY_DARK_GRAY);
            listButton.setBackground(NEAR_VERY_DARK_GRAY);
            this.add(Box.createRigidArea(new Dimension(40,10)));

            this.add(listButton);
        }

    }
    private void setImageIconForLabel(String path, JLabel label, int size)  {
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
        label.setIcon(new ImageIcon(finalImg));
    }
    private void setImageIconForButton(String path, JButton button,int size)  {
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
