package vokabeltrainer.table.list.editor.dialog;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import vokabeltrainer.BackgroundPanelTiled;
import vokabeltrainer.Settings;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Main;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Binjan;
import vokabeltrainer.types.ExpressionKind;

public class ExtrasDialog extends JDialog
{
   private static final long serialVersionUID = 5971982264587010049L;
   
   private static final int WIDTH_INFO_PANEL = 250;
   
   private JPanel layout;
   private ExpressionKind kind;
   private JComboBox<Binjan> binjan;
   private JButton closeButton;

   public ExtrasDialog()
   {
      super(Common.getjFrame(), "Cerebrummi©",
            Dialog.ModalityType.APPLICATION_MODAL);
      setSize(530, 530);
      layout = new BackgroundPanelTiled();
      layout.setBorder(
            BorderFactory.createLineBorder(Settings.getGreen(), 15, false));
      layout.setLayout(new TrainLayout(layout, 15));
      getContentPane().add(layout);
      
      initGuiFields();
      layout.add(initLeft());
      layout.add(initRight());
      initController();
   }

   private void initController()
   {
      closeButton.addActionListener(event -> {
         this.setVisible(false);
      });
   }

   private Component initRight()
   {
      JPanel vertical = new JPanel();
      TotemLayout layout = new TotemLayout(vertical, 15);
      vertical.setLayout(layout);
      vertical.setOpaque(false);
      vertical.setBackground(Settings.getTransparent());
      
      return vertical;
   }

   private Component initLeft()
   {
      JPanel vertical = new JPanel();
      TotemLayout layout = new TotemLayout(vertical, 15);
      vertical.setLayout(layout);
      vertical.setOpaque(false);
      vertical.setBackground(Settings.getTransparent());
      
      vertical.add(binjan);
      vertical.add(closeButton);
      
      return vertical;
   }

   private void initGuiFields()
   {
      Font germanfont = Main.getGermanFont(16F);
      binjan = new JComboBox<>(Binjan.values());
      binjan.setMaximumRowCount(Binjan.values().length);
      binjan.setBorder(new TitledBorder("Binjan"));
      binjan.setFont(germanfont);
      binjan.setEditable(false);
      binjan.setMinimumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      binjan.setMaximumSize(new Dimension(WIDTH_INFO_PANEL, 50));
      
      closeButton = new JButton("anwenden");
   }

   public ExpressionKind getKind()
   {
      return kind;
   }

   public void setKind(ExpressionKind kind)
   {
      this.kind = kind;
   }

   public JComboBox<Binjan> getBinjan()
   {
      return binjan;
   }
}
