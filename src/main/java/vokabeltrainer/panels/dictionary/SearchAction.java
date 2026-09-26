package vokabeltrainer.panels.dictionary;

import java.awt.event.ActionEvent;
import java.io.Serial;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class SearchAction extends AbstractAction
{
   JButton searchButton;

   @Serial
   private static final long serialVersionUID = -2307894271836844332L;

   public SearchAction(JButton searchButton)
   {
      this.searchButton = searchButton;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      searchButton.doClick();
   }

}
