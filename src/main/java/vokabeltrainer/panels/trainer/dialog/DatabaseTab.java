package vokabeltrainer.panels.trainer.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vokabeltrainer.common.main.App;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.Model;
import vokabeltrainer.panels.list.table.DatabaseTableModel;
import vokabeltrainer.panels.list.table.DatabaseTableMultiselect;
import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.panels.translation.Translator;

public class DatabaseTab extends JPanel
{
   private static final long serialVersionUID = -5986907667521647559L;

   private JButton nextButton;
   private JButton cancelButton;

   private Translator translator;

   public DatabaseTab(App app, Common common, Model model, StartTrainingView dialog)
   {
      translator = common.getTranslator();
      setLayout(new BorderLayout());
      setBackground(app.appColors.getBackground());
      setOpaque(true);

      JLabel question = new JLabel(
            translator.realisticTranslate(app, Translation.DATENBANK));
      question.setForeground(app.appColors.trainer.getTextForeground());
      question.setFont(app.appFonts.buttonFont);
      question.setOpaque(false);
      question.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      add(question, BorderLayout.NORTH);

      JPanel center = new JPanel();
      center.setOpaque(false);
      center.setBackground(app.appColors.getTransparent());
      center.setLayout(new FlowLayout());
      new ButtonGroup();

      Vector<String> names = new Vector<>();
      names.add(translator.realisticTranslate(app, Translation.DATENBANK));
      DatabaseTableModel databaseTableModel = new DatabaseTableModel(
            model.data.getDatabaseArray(), names);
      DatabaseTableMultiselect databaseTable = new DatabaseTableMultiselect(app,
            databaseTableModel, app.settings.getKeyboardWidth());
      JScrollPane scroller = new JScrollPane(databaseTable);
      scroller.setMinimumSize(new Dimension(app.settings.getKeyboardWidth(), 300));
      scroller.setMaximumSize(
            new Dimension(app.settings.getKeyboardWidth() + 50, 700));
      scroller.setBorder(BorderFactory.createEmptyBorder());

      center.add(scroller);

      add(center, BorderLayout.CENTER);

      JPanel buttonWrapper = new JPanel(new FlowLayout());
      buttonWrapper.setOpaque(false);

      cancelButton = new JButton(
            translator.realisticTranslate(app, Translation.ABBRECHEN));
      cancelButton.setFont(app.appFonts.buttonFont);
      cancelButton.setBackground(app.appColors.trainer.getButton());
      cancelButton.setForeground(app.appColors.trainer.getButtonForeground());
      cancelButton.setIcon(new ImageIcon(app.appImages.getCancel()));

      nextButton = new JButton(
            translator.realisticTranslate(app, Translation.WEITER));
      nextButton.setFont(app.appFonts.buttonFont);
      nextButton.setBackground(app.appColors.trainer.getButton());
      nextButton.setForeground(app.appColors.trainer.getButtonForeground());
      nextButton.setIcon(new ImageIcon(app.appImages.getArrow()));
      nextButton.setEnabled(true);

      buttonWrapper.add(cancelButton);
      buttonWrapper.add(nextButton);
      add(buttonWrapper, BorderLayout.SOUTH);

      databaseTable.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent mouseEvent)
         {
            removeTabsToTheRight(dialog);
         }
      });

      nextButton.addActionListener(_ -> {
         if (dialog.getTabbedPane().getTabCount() == 3)
         {
            dialog.setDatabaseNames(
                  databaseTable.getModel().getDatabaseNames());
            dialog.getTabbedPane().addTab(
                  translator.realisticTranslate(app, Translation.WIE_VIELE),
                  new ImageIcon(app.appImages.getArrow()),
                  new AmountTab(app, common, model, dialog));
         }
         dialog.getTabbedPane().setSelectedIndex(3);
      });

      cancelButton.addActionListener(_ -> {
         dialog.cancelTrainingStart();
      });
   }

   void removeTabsToTheRight(StartTrainingView dialog)
   {
      if (dialog.getTabbedPane().getTabCount() == 4)
      {
         dialog.getTabbedPane().remove(3);
      }
      else if (dialog.getTabbedPane().getTabCount() == 5)
      {
         dialog.getTabbedPane().remove(4);
         dialog.getTabbedPane().remove(3);
      }
   }

   public JButton getNextButton()
   {
      return nextButton;
   }

}
