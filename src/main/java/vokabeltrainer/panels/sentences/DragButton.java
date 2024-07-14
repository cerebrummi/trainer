package vokabeltrainer.panels.sentences;

import javax.swing.JButton;

import vokabeltrainer.types.Word;

public class DragButton extends JButton 
{
   private static final long serialVersionUID = -3293135954498484462L;
   
   private Word word;

   public DragButton(Word word)
   {
      super(word.getWord());
      this.word = word;
   }

   public Word getWord()
   {
      return word;
   }

   public void setWord(Word word)
   {
      this.word = word;
   }
}
