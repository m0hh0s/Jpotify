package GUI;

import Logic.Song;
import Logic.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class CenterArea extends JPanel{
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);

    public CenterArea() throws IOException {
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBackground(VERY_DARK_GRAY);
        this.add(Box.createRigidArea(new Dimension(40,10)));
//        this.setVisible(true);
    }
    public void preparePlayListsToAdd(ArrayList arrayList) throws IOException {
        //  btn artwork
        //  label title
        if (arrayList.get(0) instanceof Song){
            for (Object obj : arrayList){
                Song song = (Song) obj;
                songButtonCreator(song);
            }
        }
    }

    private void songButtonCreator(Song song){
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
        listButton.setMaximumSize(new Dimension(700,70));
        listButton.setBorderPainted(false);
        listButton.setFocusPainted(false);

        deleteButton.setIcon(new ImageIcon("Icons/crossIcon.png"));
        deleteButton.setMaximumSize(new Dimension(28,28));
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(NEAR_VERY_DARK_GRAY);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.getInstance().getMusicLibrary().removeSong(song);
            }
        });

        //must add space before the song and artist name
        topLabel.setText(song.getTitle());
        topLabel.setFont(topLabelFont);
        bottomLabel.setText(song.getArtistName());
        bottomLabel.setFont(bottomLabelFont);
        topLabel.setForeground(Color.WHITE);
        bottomLabel.setForeground(Color.WHITE);
        artWork.setIcon(new ImageIcon(song.getArtwork()));

        labelPanel.add(topLabel,BorderLayout.NORTH);
        labelPanel.add(bottomLabel,BorderLayout.SOUTH);

        motherPanel.add(labelPanel,BorderLayout.WEST);
        listButton.add(motherPanel);
        listButton.add(artWork,BorderLayout.WEST);
        listButton.add(deleteButton,BorderLayout.EAST);
        motherPanel.setBackground(NEAR_VERY_DARK_GRAY);
        labelPanel.setBackground(NEAR_VERY_DARK_GRAY);
        listButton.setBackground(NEAR_VERY_DARK_GRAY);
        this.add(listButton);
    }
}
