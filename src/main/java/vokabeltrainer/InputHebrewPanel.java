package vokabeltrainer;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputHebrewPanel extends JPanel
{
   private static final long serialVersionUID = 2787773393300243696L;

   private JTextField hebrewField;
   private JTextField pleneField;
   private JTextField defektivField;
   
   public InputHebrewPanel()
   {
      LayoutManager layout = new CardLayout();
      this.setLayout(layout);
      this.setMinimumSize(new Dimension(Settings.getKeyboardWidth(), 150));
      this.setMaximumSize(new Dimension(Settings.getKeyboardWidth(), 150));
   }

}
