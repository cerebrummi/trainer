package vokabeltrainer.table;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Vector;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.AbstractAction;

import vokabeltrainer.common.ApplicationSound;
import vokabeltrainer.common.Settings;
import vokabeltrainer.editing.LetterHelper;
import vokabeltrainer.editing.SwedishLetter;
import vokabeltrainer.types.Expression;

public class SoundAction extends AbstractAction
{
   private static final long serialVersionUID = -13252544217898606L;

   private ExpressionTable table;

   public SoundAction(ExpressionTable table)
   {
      this.table = table;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0)
      {
         Expression expression = (Expression) table.getValueAt(selectedRow, 0);
         if (expression.getDefinitions().isExpressionKindText())
         {
            // nothing
         }
         else if (expression.getLL().isSwedish())
         {
            buchstabieren(expression);
         }
         // else nothing
      }

   }

   private void buchstabieren(Expression expression)
   {
      Vector<AudioInputStream> inputStreams = new Vector<>();
      long frameLength = 0;
      for (SwedishLetter letter : LetterHelper
            .findSwedishLetters(expression.getLL().getSwedish()))
      {
         AudioInputStream stream = new AudioInputStream(
               new ByteArrayInputStream(letter.getSound()),
               ApplicationSound.audioFormat2, letter.getSound().length);
         inputStreams.add(stream);
         frameLength += stream.getFrameLength();
      }

      SequenceInputStream sequenceInputStream = new SequenceInputStream(
            inputStreams.elements());

      try
      {
         Clip clip = AudioSystem.getClip();

         clip.open(new AudioInputStream(sequenceInputStream,
               ApplicationSound.audioFormat, frameLength));
         FloatControl volume = (FloatControl) clip
               .getControl(FloatControl.Type.MASTER_GAIN);
         volume.setValue(Settings.getVolume());
         clip.start();
      }
      catch (LineUnavailableException | IOException e)
      {
         // nothing
      }
   }

}
