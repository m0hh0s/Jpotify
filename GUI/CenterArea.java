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
import java.util.ArrayList;

/**
 * center area is for showing songs and playLists and ...
 * in a list way and being able to delete some of them
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class CenterArea extends JPanel{
    private Color VERY_DARK_GRAY = new Color(40,40,40);
    private Color NEAR_VERY_DARK_GRAY = new Color(50,50,50);
    private Playlist playlistToRemoveFrom = null;

    /**
     * sets the playlistToRemoveFrom
     * @param playlistToRemoveFrom
     * @throws IOException
     */
    public CenterArea(Playlist playlistToRemoveFrom) throws IOException {
        this();
        this.playlistToRemoveFrom = playlistToRemoveFrom;
    }

    /**
     * sets the layout and background
     * @throws IOException
     */
    public CenterArea() throws IOException {
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBackground(VERY_DARK_GRAY);
        this.add(Box.createRigidArea(new Dimension(40,10)));
    }

    /**
     * find out what kind the array is and
     * calls the propere method
     * @param arrayList array list of songs ,albums or playLists
     * @param status shows the status of array
     * @throws IOException
     */
    public void preparePlayListsToAdd(ArrayList arrayList , String status ) throws IOException {
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
                playlistButtonCreator(playlist,arrayList);
            }
        }
    }

    /**
     * creat buttons for albums
     * set its settings and add
     * them to the layout
     * @param album
     * @throws IOException
     */
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

    /**
     * creat buttons for songs
     * set its settings and add
     * them to the layout
     * @param song song
     * @param songs arrayList of songs
     * @param status
     * @throws IOException
     */
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

    /**
     * creat buttons for playlists
     * set its settings and add
     * them to the layout
     * @param playlist
     * @param arrayList
     * @throws IOException
     */
    private void playlistButtonCreator(Playlist playlist,ArrayList arrayList) throws IOException {
        JButton listButton = new JButton();
        JButton deleteButton = new JButton();


        JPanel labelPanel = new JPanel(new BorderLayout());
        JPanel motherPanel = new JPanel(new BorderLayout());
        JLabel topLabel = new JLabel();
        JLabel artWork = new JLabel();
        Font topLabelFont = new Font("optima",Font.PLAIN,14);

        listButton.setLayout(new BorderLayout());
        listButton.setMaximumSize(new Dimension(700,70));
        listButton.setBorderPainted(false);
        listButton.setFocusPainted(false);

        setImageIconForButton("Icons/crossIcon.png",deleteButton,28);
        deleteButton.setMaximumSize(new Dimension(28,28));
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(NEAR_VERY_DARK_GRAY);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playlist.getName()!="Favourites" && playlist.getName()!="Shared Playlist")
                arrayList.remove(playlist);
            }
        });

        topLabel.setText("  " + playlist.getName());
        topLabel.setFont(topLabelFont);
        topLabel.setForeground(Color.WHITE);
        if (playlist.getSongs().size() > 0)
            setImageIconForArtWork(playlist.getSongs().get(0).getArtwork(),artWork,70);

        labelPanel.add(topLabel,BorderLayout.NORTH);

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

    /**
     * set the icon for the button
     * @param path path of icon
     * @param btn
     * @param size size of icon
     * @throws IOException
     */
    private void setImageIconForButton(String path, JButton btn ,int size) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        BufferedImage finalImg = new BufferedImage(size,size,img.getType());
        Graphics2D graphics2D = finalImg.createGraphics();
        graphics2D.drawImage(img,0,0,size,size,null);
        graphics2D.dispose();
        btn.setIcon(new ImageIcon(finalImg));
    }

    /**
     * sets artwork icon
     * @param bytes the bytes of an image
     * @param artWork the label for image to be set on
     * @param size the size of icon
     * @throws IOException
     */
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
