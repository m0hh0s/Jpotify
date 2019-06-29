package Controller;

import GUI.*;
import Logic.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Controller{
    private LoginPage loginPage;
    private JpotifyGUI jpotifyGUI;
    private User user;
    private CenterArea centerArea;
    private boolean mute;

    public Controller(){
        loginPage = new LoginPage();
        try {
            jpotifyGUI = new JpotifyGUI();
            MusicPlayer.setJpotifyGUI(jpotifyGUI);
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
                jpotifyGUI.setId(user.getUsername());
                jpotifyGUI.getLibraryAndPlayListArea().getPlayLists().setPlayLists(user.getMusicLibrary().getPlaylists());
                SwingUtilities.updateComponentTreeUI(jpotifyGUI);
            }
        });
        jpotifyGUI.getLibraryAndPlayListArea().getPlusButtonForSong().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.getMusicLibrary().addSong();
                SwingUtilities.updateComponentTreeUI(jpotifyGUI);
            }
        });
        jpotifyGUI.getLibraryAndPlayListArea().getPlusButtonForPlayList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPlayListPage app = new AddPlayListPage();
                app.getAddPlayListButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Playlist playlist = new Playlist(app.getPlayListName().getText());
                        //user.getMusicLibrary().addPlaylist(playlist);
                        jpotifyGUI.getLibraryAndPlayListArea().getPlayLists().addPlayList(playlist);
                        app.setVisible(false);
                        SwingUtilities.updateComponentTreeUI(jpotifyGUI);
                    }
                });
            }
        });
        jpotifyGUI.getLibraryAndPlayListArea().getSongs().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getMusicLibrary().getSongs().size() > 0) {
                    try {
                        centerArea = new CenterArea();
                        user.getMusicLibrary().sortSongs();
                        centerArea.preparePlayListsToAdd(user.getMusicLibrary().getSongs() , "songs");
                        jpotifyGUI.changeCenterArea(centerArea);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        jpotifyGUI.getLibraryAndPlayListArea().getPlayListButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    centerArea = new CenterArea();
                    centerArea.preparePlayListsToAdd(user.getMusicLibrary().getPlaylists() , "playlist");
                    jpotifyGUI.changeCenterArea(centerArea);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        jpotifyGUI.getLibraryAndPlayListArea().getAlbums().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getMusicLibrary().getAlbums().size() > 0) {
                    try {
                        centerArea = new CenterArea();
                        centerArea.preparePlayListsToAdd(user.getMusicLibrary().getAlbums(), "album");
                        jpotifyGUI.changeCenterArea(centerArea);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        jpotifyGUI.getMusicPlayerArea().getAddToPlayList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MusicPlayer.getCurrentlyPlaying() != null) {
                    AddSongToPlayListPage astpp = new AddSongToPlayListPage(user.getMusicLibrary().getPlaylists());
                    astpp.getAddSongTOPlayListButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (JCheckBox checkBox : astpp.getMap().keySet()) {
                                if (checkBox.isSelected()) {
                                    astpp.getMap().get(checkBox).addSong(MusicPlayer.getCurrentlyPlaying());
                                }
                            }
                            astpp.setVisible(false);
                        }
                    });
                }
            }
        });
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
                MusicPlayer.pauseOrResume();
                JButton playButton = jpotifyGUI.getMusicPlayerArea().getPlayButton();
                try {
                    if (MusicPlayer.isPlaying()){
                        jpotifyGUI.getMusicPlayerArea().prepareButtonToAdd(playButton , "Icons/pauseIconWhite.png" ,40);
                    }else {
                        jpotifyGUI.getMusicPlayerArea().prepareButtonToAdd(playButton , "Icons/play.png" ,40);
                    }
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(jpotifyGUI);
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
        jpotifyGUI.getMusicPlayerArea().getMuteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SoundControl.mute();
                JButton muteButton = jpotifyGUI.getMusicPlayerArea().getMuteButton();
                if (mute) {
                    try {
                        jpotifyGUI.getMusicPlayerArea().prepareButtonToAdd(muteButton, "Icons\\muteIcon.png", 20);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    mute = false;
                }else {
                    try {
                        jpotifyGUI.getMusicPlayerArea().prepareButtonToAdd(muteButton, "Icons\\onlineIcon.png", 20);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    mute = true;
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
