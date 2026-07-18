package vokabeltrainer.common.main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class AppSounds {
    private static byte[] shredderSound = {};
    private static byte[] splotchSound = {};
    private static byte[] clappingSound = {};
    private static byte[] waveSound = {};

    public static AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, false);

    public static AudioFormat audioFormat2 = new AudioFormat(48000, 16, 2, true, false);

    Settings settings;
    
    public AppSounds(Settings settings)
    {
       this.settings = settings;
    }

    public void setShredderSound(InputStream in) {
        try {
            shredderSound = in.readAllBytes();
        } catch (Exception e) {
        }

        if (shredderSound.length == 0) {
           System.exit(1);
        }
    }

    public AudioInputStream getShredderSound() {
        return new AudioInputStream(new ByteArrayInputStream(shredderSound), audioFormat, shredderSound.length);
    }

    public void setSplotchSound(InputStream audioInputStream) {
        try {
            splotchSound = audioInputStream.readAllBytes();
        } catch (Exception e) {
        }

        if (splotchSound.length == 0) {
           System.exit(1);
        }
    }

    public AudioInputStream getSplotchSound() {
        return new AudioInputStream(new ByteArrayInputStream(splotchSound), audioFormat, splotchSound.length);
    }

    public void setClappingSound(InputStream audioInputStream) {
        try {
            clappingSound = audioInputStream.readAllBytes();
        } catch (Exception e) {
        }

        if (clappingSound.length == 0) {
           System.exit(1);
        }
    }

    public AudioInputStream getClappingSound() {
        return new AudioInputStream(new ByteArrayInputStream(clappingSound), audioFormat, clappingSound.length);
    }

    public void setWaveSound(InputStream audioInputStream) {
        try {
            waveSound = audioInputStream.readAllBytes();
        } catch (Exception e) {
        }

        if (waveSound.length == 0) {
           System.exit(1);
        }
    }

    public AudioInputStream getWaveSound() {
        return new AudioInputStream(new ByteArrayInputStream(waveSound), audioFormat, waveSound.length);
    }
}
