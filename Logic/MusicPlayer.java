package Logic;

import Logic.jl.decoder.JavaLayerException;
import Logic.jl.player.advanced.AdvancedPlayer;
import java.io.*;
import java.util.ArrayList;

public class MusicPlayer {
    private static volatile AdvancedPlayer player = null;
    private static volatile FileInputStream fis = null;
    private static volatile BufferedInputStream bis = null;
    private static volatile long remainingBytes = 0;
    private static volatile long songTotalLength = 0;
    private static volatile Song currentlyPlaying;
    private static Thread playingThread;
    private static volatile boolean onPause;
    private static volatile boolean isPlaying;
    private static boolean isOnLoop;
    private static volatile boolean goToNextSong;
    private static volatile boolean goToPreviousSong;

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
                        currentlyPlaying = songsToBePlayed.get(i);
                        fis = new FileInputStream(currentlyPlaying.getSongAddress());
                        bis = new BufferedInputStream(fis);
                        songTotalLength = bis.available();
                        bis.mark(Integer.MAX_VALUE);
                        player = new AdvancedPlayer(bis);
                        currentlyPlaying.setDateListenedTo(System.currentTimeMillis());
                        while (player.play(1)) {
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
                        playAList(songsToBePlayed);
                    }
                }

            }
        });
        playingThread.start();
    }

    public static void seek(int percentage , String forwardOrBackwards){
        if (forwardOrBackwards.equals("forward")){
            try {
                long skippingPoint =(long)( (double)songTotalLength * ((double)percentage / 100.0) );
                bis.skip(skippingPoint);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (forwardOrBackwards.equals("backward")){
            try {
                pause();
                remainingBytes = bis.available();
                bis.reset();
                long skippingPoint = (songTotalLength - remainingBytes) - (long)((double)songTotalLength * ((double)percentage / 100.0));
                System.out.println(skippingPoint * 100 / songTotalLength);
                bis.skip(skippingPoint);
                resume();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
}
