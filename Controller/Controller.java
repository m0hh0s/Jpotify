package Controller;

import GUI.*;
import Logic.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * the controller in which the Logic and GUI connect
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class Controller{
    private LoginPage loginPage;
    private JpotifyGUI jpotifyGUI;
    private User user;
    private CenterArea centerArea;
    private boolean mute;

    /**
     * makes a new page of jpotifyGUI and login page
     */
    public Controller(){
        loginPage = new LoginPage();
        try {
            jpotifyGUI = new JpotifyGUI();
            MusicPlayer.setJpotifyGUI(jpotifyGUI);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * add listener to the various parts
     */
    public void setupJpotify(){

        //add listener to login button
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
//<<<<<<< HEAD
                jpotifyGUI.getLibraryAndPlayListArea().getPlayLists().setPlayLists(user.getMusicLibrary().getPlaylists());
//=======
                jpotifyGUI.getLibraryAndPlayListArea().getPlayLists().setPlayLists(user.getMusicLibrary().getPlaylists());
//>>>>>>> added the id in the header part
                SwingUtilities.updateComponentTreeUI(jpotifyGUI);
            }
        });

        //add listener to the button to add song
        jpotifyGUI.getLibraryAndPlayListArea().getPlusButtonForSong().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                user.getMusicLibrary().addSong();
                SwingUtilities.updateComponentTreeUI(jpotifyGUI);
            }
        });

        //add listener to the button to add playList
        jpotifyGUI.getLibraryAndPlayListArea().getPlusButtonForPlayList().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPlayListPage app = new AddPlayListPage();
                app.getAddPlayListButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int result = jpotifyGUI.getLibraryAndPlayListArea().getPlayLists().searchByName(app.getPlayListName().getText());
                        if(result == 0) {
                            Playlist playlist = new Playlist(app.getPlayListName().getText());
                            //user.getMusicLibrary().addPlaylist(playlist);
                            jpotifyGUI.getLibraryAndPlayListArea().getPlayLists().addPlayList(playlist);
                            app.setVisible(false);
                            SwingUtilities.updateComponentTreeUI(jpotifyGUI);
                        }
                        else
                            app.setVisible(false);
                    }
                });
            }
        });

        //add listener to the songs button in library
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

        //add listener to the playList button in library
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

        //add listener to the Albums button in library
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

        //add listener to the menu part in musicPlayer Area
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
                                    int result = astpp.getMap().get(checkBox).compareSong(MusicPlayer.getCurrentlyPlaying());
                                    if(result == 0)
                                        astpp.getMap().get(checkBox).addSong(MusicPlayer.getCurrentlyPlaying());
                                }
                            }
                            astpp.setVisible(false);
                        }
                    });
                }
            }
        });

        //add listener to the backward button
        jpotifyGUI.getMusicPlayerArea().getBackwardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.previousSong();
            }
        });

        //add listener to the forward button
        jpotifyGUI.getMusicPlayerArea().getForwardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.nextSong();
            }
        });

        //add listener to the replayButton
        jpotifyGUI.getMusicPlayerArea().getReplayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.changeLoopStatus();
            }
        });

        //add listener to the play button
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

        //add listener to the sound slider
        jpotifyGUI.getMusicPlayerArea().getSoundSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SoundControl.setSystemVolume(jpotifyGUI.getMusicPlayerArea().getSoundSlider().getValue());
            }
        });

        //add listener to the time slider
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

        //add listener to the mute button
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

        //add listener to save the user
        jpotifyGUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SavedFilesHandler.saveUserData(user);
                super.windowClosing(e);
            }
        });
    }
}
