package org.backend.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * A class used for playing music, for interactiveness.
 */
public class Music {

    public static void playSound(String soundFile) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();

    }
}
