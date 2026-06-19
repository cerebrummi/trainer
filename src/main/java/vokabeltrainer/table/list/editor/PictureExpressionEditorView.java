package vokabeltrainer.table.list.editor;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import vokabeltrainer.cmd.TextHelper;
import vokabeltrainer.common.ApplicationColors;
import vokabeltrainer.common.Common;
import vokabeltrainer.common.Settings;
import vokabeltrainer.common.colors.InputColors;
import vokabeltrainer.panels.translation.Translator;
import vokabeltrainer.table.EscapeAction;
import vokabeltrainer.tonionlayout.TotemLayout;
import vokabeltrainer.tonionlayout.TrainLayout;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.LLType;

public class PictureExpressionEditorView extends JDialog
{
   private static final long serialVersionUID = 5853498340870217732L;

   private Expression expression;
   private boolean newExpression;
   
 private NikudExpressionEditorControllerConnector connector;

 private JPanel outerLayout;


   public PictureExpressionEditorView(
         NikudExpressionEditorControllerConnector connector)
   {
      super(Common.getjFrame(), Settings.getWindowTitle(),
            Dialog.ModalityType.APPLICATION_MODAL);
      this.connector = connector;

      setResizable(true);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setSize(Math.min(screenSize.width - 60, 1320),
            Math.min(screenSize.height - 60, 825));

      outerLayout = new JPanel();
      outerLayout.setBackground(InputColors.getEditorBackground());
      outerLayout.setBorder(BorderFactory.createLineBorder(
            InputColors.getEditorBackground(), 15, false));
      outerLayout.setLayout(new TotemLayout(outerLayout, 15));

      JPanel layout = new JPanel();
      layout.setOpaque(false);
      layout.setBackground(ApplicationColors.getTransparent());
      layout.setLayout(new TrainLayout(layout, 15));
      layout.add(new JLabel("Bilder"));
      
      outerLayout.add(layout);

      getContentPane().add(new JScrollPane(outerLayout));

      initController();
   }

   
   private void initController()
   {
      

      

      getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "ESCAPE_KEY");
      getRootPane().getActionMap().put("ESCAPE_KEY", new EscapeAction(this));
   }

   public void disposeDialog()
   {
      this.dispose();
   }

  

   


   private String cleanTextAndNoComma(String text)
   {
      return TextHelper.cleanText(text).replaceAll(",", "");
   }

   private String cleanText(String text)
   {
      return TextHelper.cleanText(text);
   }

   private void setExpressionForReset()
   {
      setExpression(this.expression, this.newExpression);
   }

   public void setExpression(Expression expression, boolean newExpression)
   {

   }

   

   public Expression getExpression()
   {
      return expression;
   }

   


  

   public void setWritingDirection(LLType llType)
   {
      switch (Settings.getMyWritingDirection())
      {
      case LEFT_TO_RIGHT:
         
         break;
      case RIGHT_TO_LEFT:
         
      }

      switch (llType)
      {
      case GERMAN:
      case SWEDISH:
         
         break;
      case HEBREW:
      
         break;
      case UNKOWN:
         switch (Settings.getLanguageInput())
         {
         case GERMAN:
         case SWEDISH:
            
            break;
         case PLENE_DEFEKTIV:
         case SIMPLE:
            
         }
      }
   }


}
