package vokabeltrainer.panels.input;

import javax.swing.JComboBox;

import vokabeltrainer.types.Chapter;

import java.io.Serial;

public class ChapterComboBox extends JComboBox<Chapter>
{
   @Serial
   private static final long serialVersionUID = -5633721990953684860L;

   public ChapterComboBox()
   {
      this.setRenderer(new ChapterComboBoxRenderer());
      this.setLightWeightPopupEnabled(true);
      this.setEditable(false);
   }
}
