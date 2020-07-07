package vokabeltrainer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class ApplicationSound
{
   private static byte[] shredderSound = { };
   private static byte[] splotchSound = { };
   private static byte[] clappingSound = { };
   private static byte[] waveSound = { };
   
   private static AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true,
         false);

   public static void setShredderSound(InputStream in)
   {
      try
      {
         shredderSound = in.readAllBytes();
      }
      catch (IOException e)
      {
         // nothing
      }
   }

   public static AudioInputStream getShredderSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(shredderSound),
            audioFormat, shredderSound.length);
   }

   public static void setSplotchSound(InputStream audioInputStream)
   {
      try
      {
         splotchSound = audioInputStream.readAllBytes();
      }
      catch (IOException e)
      {
         // nothing
      }
   }

   public static AudioInputStream getSplotchSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(splotchSound),
            audioFormat, splotchSound.length);
   }

   public static void setClappingSound(InputStream audioInputStream)
   {
      try
      {
         clappingSound = audioInputStream.readAllBytes();
      }
      catch (IOException e)
      {
         // nothing
      }
   }
   
   public static AudioInputStream getClappingSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(clappingSound),
            audioFormat, clappingSound.length);
   }

   public static void setWaveSound(InputStream audioInputStream)
   {
      try
      {
         waveSound = audioInputStream.readAllBytes();
      }
      catch (IOException e)
      {
         // nothing
      }
   }
   
   public static AudioInputStream getWaveSound()
   {
      return new AudioInputStream(new ByteArrayInputStream(waveSound),
            audioFormat, waveSound.length);
   }
}
