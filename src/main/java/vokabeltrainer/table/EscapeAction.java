package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import vokabeltrainer.table.list.editor.LanguageExpressionEditorView;
import vokabeltrainer.table.list.editor.TextExpressionEditorView;

public class EscapeAction extends AbstractAction
{
   /**
    * 
    */
   private static final long serialVersionUID = 2610276331172351891L;
   private JDialog dialog;
   private boolean isText;

   public EscapeAction(LanguageExpressionEditorView dialog)
   {
      this.dialog = dialog;
      this.isText = false;
   }

   public EscapeAction(TextExpressionEditorView dialog)
   {
      this.dialog = dialog;
      this.isText = true;
   }
   
   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(isText)
      {
         ((TextExpressionEditorView)this.dialog).disposeDialog();
      }
      else
      {
         ((LanguageExpressionEditorView)this.dialog).disposeDialog();
      }
      
   }

}
