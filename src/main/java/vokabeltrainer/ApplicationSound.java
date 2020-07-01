package vokabeltrainer;

import javax.sound.sampled.AudioInputStream;

public class ApplicationSound
{
   private static  AudioInputStream shredderSound;
   public static void setShredderSound(AudioInputStream audioInputStream)
   {
      shredderSound = audioInputStream;
   }
   public static AudioInputStream getShredderSound()
   {
      return shredderSound;
   }

}
