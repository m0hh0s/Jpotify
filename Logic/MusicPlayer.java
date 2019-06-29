package Logic;

import GUI.JpotifyGUI;
import Logic.jl.decoder.JavaLayerException;
import Logic.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Mohsen Hosseiny and Sattar Noee
 * @version 1.0
 */
public class MusicPlayer {
    private static volatile AdvancedPlayer player = null;
    private static volatile FileInputStream fis = null;
    private static volatile BufferedInputStream bis = null;
    private static volatile int startPoint = 0;
    private static volatile int endPoint = 0;
    private static volatile Song currentlyPlaying;
    private static Thread playingThread;
    private static volatile boolean onPause;
    private static volatile boolean isPlaying;
    private static boolean isOnLoop;
    private static volatile boolean goToNextSong;
    private static volatile boolean goToPreviousSong;
    private static JpotifyGUI jpotifyGUI;

    public static Song getCurrentlyPlaying() {
        return currentlyPlaying;
    }
    public static boolean isOnLoop() {
        return isOnLoop;
    }
    public static boolean isPlaying() {
        return isPlaying;
    }
    public static void pause() {
        onPause = true;
        isPlaying = false;
    }
    public static void resume() {
        if (player != null) {
            onPause = false;
            isPlaying = true;
            synchronized (player) {
                player.notifyAll();
            }
        }
    }
    public static void changeLoopStatus(){
        isOnLoop = !isOnLoop;
    }
    public static void playAList(Song startingSong ,ArrayList<Song> songsToBePlayed) {
        if (player != null && isPlaying){
            playingThread.stop();
        }
        onPause = false;
        isPlaying = true;
        try {
            jpotifyGUI.getMusicPlayerArea().prepareButtonToAdd(jpotifyGUI.getMusicPlayerArea().getPlayButton() , "Icons/pauseIconWhite.png" ,40);
            SwingUtilities.updateComponentTreeUI(jpotifyGUI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        playingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = songsToBePlayed.indexOf(startingSong) ; i < songsToBePlayed.size() ; i++) {
                    try {
                        currentlyPlaying = songsToBePlayed.get(i);
                        fis = new FileInputStream(currentlyPlaying.getSongAddress());
                        bis = new BufferedInputStream(fis);
                        endPoint = bis.available();
                        bis.mark(Integer.MAX_VALUE);
                        currentlyPlaying.setDateListenedTo(System.currentTimeMillis());
                        int duration = currentlyPlaying.getTotalLength();
                        String minute = String.format("%02d" , ((duration - (duration & 60)) / 60));
                        String second = String.format("%02d" , (duration % 60));
                        jpotifyGUI.getMusicPlayerArea().getTotalTime().setText(minute + ":" + second);
                        jpotifyGUI.getMusicPlayerArea().getSongName().setText(currentlyPlaying.getTitle());
                        jpotifyGUI.getMusicPlayerArea().getSongSinger().setText(currentlyPlaying.getArtistName());
                        jpotifyGUI.getMusicPlayerArea().getTimeSlider().setMaximum(endPoint);
                        jpotifyGUI.getLibraryAndPlayListArea().setImageIconForLabel(new BufferedInputStream(new ByteArrayInputStream(currentlyPlaying.getArtwork())));
                        player = new AdvancedPlayer(bis);
                        while (player.play(1)) {
                            jpotifyGUI.getMusicPlayerArea().getTimeSlider().setValue(endPoint - bis.available());
                            if (onPause) {
                                synchronized (player) {
                                    player.wait();
                                }
                            }
                            if (goToNextSong || goToPreviousSong){
                                goToNextSong = false;
                                break;
                            }
                        }
                    } catch (JavaLayerException | InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    if (goToPreviousSong){
                        goToPreviousSong = false;
                        if (i == 0){
                            if (isOnLoop)
                                i = songsToBePlayed.size() - 2;
                            else
                                return;
                        }
                        else {
                            i -= 2;
                        }
                    }
                    if (isOnLoop && (i == songsToBePlayed.size() - 1) ){
                        playAList(startingSong , songsToBePlayed);
                        currentlyPlaying = null;
                        isPlaying = false;
                        onPause = true;
                    }
                }
                currentlyPlaying = null;
                isPlaying = false;
                onPause = true;
            }
        });
        playingThread.start();
    }
    public static void seek(int seekPoint) {
        try {
            bis.reset();
            bis.skip(seekPoint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void nextSong(){
        goToNextSong = true;
        if (onPause)
            resume();
    }
    public static void previousSong(){
        goToPreviousSong = true;
        if (onPause)
            resume();
    }
    public static JpotifyGUI getJpotifyGUI() {
        return jpotifyGUI;
    }
    public static void setJpotifyGUI(JpotifyGUI jpotifyGUI) {
        MusicPlayer.jpotifyGUI = jpotifyGUI;
    }
    public static void pauseOrResume(){
        if (onPause)
            resume();
        else
            pause();
    }
}
