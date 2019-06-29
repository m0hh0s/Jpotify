package Logic;

import java.io.IOException;
/**
 * controls the sound of the system and song
 * @author Mohsen Hosseini and Sattar Noee
 * @version 1.0
 */
public class SoundControl {
    /**
     * sets the system volume to the volume
     * @param volume desired volume
     */
    public static void setSystemVolume(int volume) {
        if(volume < 0 || volume > 100)
        {
            throw new RuntimeException("Error: " + volume + " is not a valid number. Choose a number between 0 and 100");
        }

        else
        {
            double endVolume = 655.35 * volume;

            Runtime rt = Runtime.getRuntime();
            Process pr;
            try
            {
                pr = rt.exec("src\\Logic\\nircmd.exe" + " setsysvolume " + endVolume);
                pr = rt.exec("src\\Logic\\nircmd.exe" + " mutesysvolume 0");

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * mutes the sound
     */
    public static void mute(){
        Runtime rt = Runtime.getRuntime();
        Process pr;
        try
        {
            pr = rt.exec("src\\Logic\\nircmd.exe" + " mutesysvolume 2");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
