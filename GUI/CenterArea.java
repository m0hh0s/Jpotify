package GUI;

import Logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class CenterArea extends JPanel{
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);
    private Playlist playlistToRemoveFrom = null;

    public CenterArea() throws IOException {
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBackground(VERY_DARK_GRAY);
        this.add(Box.createRigidArea(new Dimension(40,10)));
    }

    public void preparePlayListsToAdd(ArrayList arrayList , String status) throws IOException {
        if (arrayList.get(0) instanceof Song){
            for (Object obj : arrayList){
                Song song = (Song) obj;
                songButtonCreator(song , (ArrayList<Song>)arrayList , status);
            }
        } else if (arrayList.get(0) instanceof Album){
            for (Object obj : arrayList){
                Album album = (Album) obj;
                albumButtonCreator(album);
            }
        } else if (arrayList.get(0) instanceof Playlist){
            for (Object obj : arrayList){
                Playlist playlist = (Playlist) obj;
                playlistButtonCreator(playlist);
            }
        }
    }


    private void albumButtonCreator(Album album) throws IOException {
        JButton listButton = new JButton();
        //JButton deleteButton = new JButton();

        listButton.setLayout(new BorderLayout());
        listButton.setMaximumSize(new Dimension(700,70));
        listButton.setBorderPainted(false);
        listButton.setFocusPainted(false);

        JPanel labelPanel = new JPanel(new BorderLayout());
        JPanel motherPanel = new JPanel(new BorderLayout());
        JLabel topLabel = new JLabel();
        JLabel bottomLabel = new JLabel();
        JLabel artWork = new JLabel();
        Font topLabelFont = new Font("optima",Font.PLAIN,14);
        Font bottomLabelFont = new Font("optima",Font.BOLD,12);


//        setImageIconForButton("Icons/crossIcon.png",deleteButton,28);
//        deleteButton.setMaximumSize(new Dimension(28,28));
//        deleteButton.setBorderPainted(false);
//        deleteButton.setFocusPainted(false);
//        deleteButton.setBackground(NEAR_VERY_DARK_GRAY);
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                User.getInstance().getMusicLibrary().removeSong(album);
//            }
//        });

        //must add space before the song and artist name
        topLabel.setText("  " + album.getTitle());
        topLabel.setFont(topLabelFont);
        bottomLabel.setText("  " + album.getArtistName());
        bottomLabel.setFont(bottomLabelFont);
        topLabel.setForeground(Color.WHITE);
        bottomLabel.setForeground(Color.WHITE);

        setImageIconForArtWork(album.getArtwork(),artWork,70);

        labelPanel.add(topLabel,BorderLayout.NORTH);
        labelPanel.add(bottomLabel,BorderLayout.SOUTH);

        motherPanel.add(labelPanel,BorderLayout.WEST);
        listButton.add(motherPanel);
        listButton.add(artWork,BorderLayout.WEST);
        //listButton.add(deleteButton,BorderLayout.EAST);
        motherPanel.setBackground(NEAR_VERY_DARK_GRAY);
        labelPanel.setBackground(NEAR_VERY_DARK_GRAY);
        listButton.setBackground(NEAR_VERY_DARK_GRAY);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (album.getSongs().size() > 0) {
                    try {
                        CenterArea centerArea = new CenterArea();
                        centerArea.preparePlayListsToAdd(album.getSongs() , "album");
                        MusicPlayer.getJpotifyGUI().changeCenterArea(centerArea);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(listButton);

    }
    private void songButtonCreator(Song song , ArrayList<Song> songs , String status) throws IOException {
        JButton listButton = new JButton();
        JButton deleteButton = new JButton();

        listButton.setLayout(new BorderLayout());
        listButton.setMaximumSize(new Dimension(700,70));
        listButton.setBorderPainted(false);
        listButton.setFocusPainted(false);

        JPanel labelPanel = new JPanel(new BorderLayout());
        JPanel motherPanel = new JPanel(new BorderLayout());
        JLabel topLabel = new JLabel();
        JLabel bottomLabel = new JLabel();
        JLabel artWork = new JLabel();
        Font topLabelFont = new Font("optima",Font.PLAIN,14);
        Font bottomLabelFont = new Font("optima",Font.BOLD,12);


        setImageIconForButton("Icons/crossIcon.png",deleteButton,28);
        deleteButton.setMaximumSize(new Dimension(28,28));
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(NEAR_VERY_DARK_GRAY);
        if (status.equals("playlist")){
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playlistToRemoveFrom.removeSong(song);
                }
            });
        } else {
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    User.getInstance().getMusicLibrary().removeSong(song);
                }
            });
        }
        //must add space before the song and artist name
        topLabel.setText("  " + song.getTitle());
        topLabel.setFont(topLabelFont);
        bottomLabel.setText("  " + song.getArtistName());
        bottomLabel.setFont(bottomLabelFont);
        topLabel.setForeground(Color.WHITE);
        bottomLabel.setForeground(Color.WHITE);

        setImageIconForArtWork(song.getArtwork(),artWork,70);

        labelPanel.add(topLabel,BorderLayout.NORTH);
        labelPanel.add(bottomLabel,BorderLayout.SOUTH);

        motherPanel.add(labelPanel,BorderLayout.WEST);
        listButton.add(motherPanel);
        listButton.add(artWork,BorderLayout.WEST);
        listButton.add(deleteButton,BorderLayout.EAST);
        motherPanel.setBackground(NEAR_VERY_DARK_GRAY);
        labelPanel.setBackground(NEAR_VERY_DARK_GRAY);
        listButton.setBackground(NEAR_VERY_DARK_GRAY);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.playAList(song , songs);
            }
        });
        this.add(listButton);
    }
    private void playlistButtonCreator(Playlist playlist) throws IOException {
        JButton listButton = new JButton();

        listButton.setLayout(new BorderLayout());
        listButton.setMaximumSize(new Dimension(700,70));
        listButton.setBorderPainted(false);
        listButton.setFocusPainted(false);

        JPanel labelPanel = new JPanel(new BorderLayout());
        JPanel motherPanel = new JPanel(new BorderLayout());
        JLabel topLabel = new JLabel();
        JLabel artWork = new JLabel();
        Font topLabelFont = new Font("optima",Font.PLAIN,14);


        topLabel.setText("  " + playlist.getName());
        topLabel.setFont(topLabelFont);
        topLabel.setForeground(Color.WHITE);
        if (playlist.getSongs().size() > 0)
            setImageIconForArtWork(playlist.getSongs().get(0).getArtwork(),artWork,70);

        labelPanel.add(topLabel,BorderLayout.NORTH);

        motherPanel.add(labelPanel,BorderLayout.WEST);
        listButton.add(motherPanel);
        listButton.add(artWork,BorderLayout.WEST);
        motherPanel.setBackground(NEAR_VERY_DARK_GRAY);
        labelPanel.setBackground(NEAR_VERY_DARK_GRAY);
        listButton.setBackground(NEAR_VERY_DARK_GRAY);
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playlist.getSongs().size() > 0) {
                    try {
                        CenterArea centerArea = new CenterArea();
                        centerArea.playlistToRemoveFrom = playlist;
                        centerArea.preparePlayListsToAdd(playlist.getSongs() , "playlist");
                        MusicPlayer.getJpotifyGUI().changeCenterArea(centerArea);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(listButton);
    }
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = new BufferedImage(size,size,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        btn.setIcon(new ImageIcon(finalImg));
    }
    private void setImageIconForArtWork(byte[] bytes , JLabel artWork,int size) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        BufferedImage img = ImageIO.read(in);
        BufferedImage finalImg = new BufferedImage(size,size,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        artWork.setIcon(new ImageIcon(finalImg));
    }
}
