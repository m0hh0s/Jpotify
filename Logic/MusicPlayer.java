package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.*;

public class MusicPlayer{
    private static AdvancedPlayer player = null;
    private static FileInputStream fis = null;
    private static BufferedInputStream bis = null;
    private static long pauseLocation;
    private static long songTotalLength;
    private static String songPath;
    private static boolean isOnLoop;

    public static void play(String path){
        MusicPlayer.stop(); //stopping the previous song
        try {
            fis = new FileInputStream(path);
            songTotalLength = fis.available();
            bis = new BufferedInputStream(fis);
            player = new AdvancedPlayer(bis);
            songPath = path + "";
        } catch (JavaLayerException | IOException e) {
            System.out.println("problem with player & files");
        }
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                    if (isOnLoop){
                        MusicPlayer.play(songPath);
                    }
                } catch (JavaLayerException e) {
                    System.out.println("problem playing");
                }
            }
        }.start();
    }

    public static void stop(){
        if (player != null)
            player.close();
        songTotalLength = 0;
        pauseLocation = 0;
    }

    public static void pause(){
        try {
            pauseLocation = fis.available();
            player.close();
        } catch (IOException e) {
            System.out.println("problem in pausing");
        }
    }

    public static void resume(){
        try {
            fis = new FileInputStream(songPath);
            fis.skip(songTotalLength - pauseLocation);
            bis = new BufferedInputStream(fis);
            player = new AdvancedPlayer(bis);

        } catch (JavaLayerException | IOException e) {
            System.out.println("problem with player & files");
        }
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    System.out.println("problem playing");
                }
            }
        }.start();
    }

    public static void changeLoopStatus(){
        isOnLoop = !isOnLoop;
    }
}
