package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.*;
import java.util.ArrayList;

public class MusicPlayer {
    private static volatile AdvancedPlayer player = null;
    private static volatile FileInputStream fis = null;
    private static volatile long pauseLocation = 0;
    private static volatile long songTotalLength = 0;
    private static Thread playingThread;
    private static volatile boolean onPause;
    private static volatile boolean isPlaying;
    private static boolean isOnLoop;

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

    public static void resume(ArrayList<Song> songsToBePlayed) {
        onPause = false;
        isPlaying = true;
        synchronized (player) {
            player.notifyAll();
        }
    }

    public static void changeLoopStatus(){
        isOnLoop = !isOnLoop;
    }

    public static void playAList(ArrayList<Song> songsToBePlayed) {
        onPause = false;
        isPlaying = true;
        playingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < songsToBePlayed.size(); i++) {
                    try {
                        fis = new FileInputStream(songsToBePlayed.get(i).getSongAddress());
                        player = new AdvancedPlayer(fis);
                        while (player.play(1)) {
                            if (onPause) {
                                synchronized (player) {
                                    player.wait();
                                }
                            }
                        }
                    } catch (FileNotFoundException | JavaLayerException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isOnLoop && (i == songsToBePlayed.size() - 1) ){
                        playAList(songsToBePlayed);
                    }
                }

            }
        });
        playingThread.start();
//        if (MusicPlayer.isOnLoop()){
//            try {
//                playingThread.join();
//                System.out.println("now........");
//                playAList(songsToBePlayed);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
    }
}
