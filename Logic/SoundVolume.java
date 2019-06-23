package Logic;

import javax.sound.sampled.*;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;

public class SoundVolume {
    public static void chnage() {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo(); // target, not source
            for (Line.Info lineInfo : lineInfos) {
                Line line = null;
                boolean opened = true;
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    if (!opened) {
                        line.open();
                    }
                    FloatControl volCtrl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
                    System.out.println("    volCtrl.getValue() = " + volCtrl.getValue());
                } catch (LineUnavailableException | IllegalArgumentException e) {
                    e.printStackTrace();
                } finally {
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }
    public static void setSystemVolume(int volume)
    {
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
               // pr = rt.exec("src\\Logic\\nircmd.exe" + " mutesysvolume 0");

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
