package Controller;

import GUI.CenterArea;
import GUI.JpotifyGUI;
import GUI.LoginPage;
import Logic.MusicPlayer;
import Logic.SavedFilesHandler;
import Logic.SoundControl;
import Logic.User;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Controller{
    private LoginPage loginPage;
    private JpotifyGUI jpotifyGUI;
    private User user;

    public Controller(){
        loginPage = new LoginPage();
        try {
            jpotifyGUI = new JpotifyGUI();
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    public void setupJpotify(){
        loginPage.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user = SavedFilesHandler.loadUserData(loginPage.getTextField().getText());
                } catch (IOException ex) {
                    user = new User();
                    user.setUsername(loginPage.getTextField().getText());
                }
                User.setInstance(user);
                loginPage.setVisible(false);
                jpotifyGUI.setVisible(true);
            }
        });
        jpotifyGUI.getLibraryAndPlayListArea().getPlusButtonForSong().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.getMusicLibrary().addSong();
            }
        });
        jpotifyGUI.getLibraryAndPlayListArea().getPlusLabelForPlayList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
//        jpotifyGUI.getLibraryAndPlayListArea().getPlusLabelForPlayList().addActionListener();
//        jpotifyGUI.getLibraryAndPlayListArea().getSongs().addActionListener();
//        jpotifyGUI.getLibraryAndPlayListArea().getPlayListButton().addActionListener();
//        jpotifyGUI.getLibraryAndPlayListArea().getAlbums().addActionListener();
//        jpotifyGUI.getLibraryAndPlayListArea().getArtist().addActionListener();
        jpotifyGUI.getMusicPlayerArea().getBackwardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.previousSong();
            }
        });
        jpotifyGUI.getMusicPlayerArea().getForwardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.nextSong();
            }
        });
        jpotifyGUI.getMusicPlayerArea().getReplayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.changeLoopStatus();
            }
        });
        jpotifyGUI.getMusicPlayerArea().getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MusicPlayer.resume();
                MusicPlayer.playAList(user.getMusicLibrary().getSongs() , jpotifyGUI);
            }
        });
        jpotifyGUI.getMusicPlayerArea().getSoundSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SoundControl.setSystemVolume(jpotifyGUI.getMusicPlayerArea().getSoundSlider().getValue());
            }
        });
        jpotifyGUI.getMusicPlayerArea().getTimeSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (MusicPlayer.isPlaying()) {
                    int currentValue = jpotifyGUI.getMusicPlayerArea().getTimeSlider().getValue();
                    int maxValue = jpotifyGUI.getMusicPlayerArea().getTimeSlider().getMaximum();
                    int remaining = MusicPlayer.getCurrentlyPlaying().getRemaining((double)currentValue / (double) maxValue);
                    MusicPlayer.seek(currentValue);
                    String minute = String.format("%02d" , ((remaining - (remaining & 60)) / 60));
                    String second = String.format("%02d" , (remaining % 60));
                    jpotifyGUI.getMusicPlayerArea().getTimePassed().setText(minute + ":" + second);
                }
            }
        });
        jpotifyGUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SavedFilesHandler.saveUserData(user);
                super.windowClosing(e);
            }
        });

    }
}
