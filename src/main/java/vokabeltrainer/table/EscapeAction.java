package vokabeltrainer.table;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import vokabeltrainer.table.list.editor.LanguageExpressionEditorView;
import vokabeltrainer.table.list.editor.PictureExpressionEditorView;

public class EscapeAction extends AbstractAction
{
   private static final long serialVersionUID = 2610276331172351891L;
   private JDialog dialog;

   public EscapeAction(LanguageExpressionEditorView dialog)
   {
      this.dialog = dialog;
   }

   public EscapeAction(PictureExpressionEditorView dialog)
   {
      this.dialog = dialog;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if (this.dialog instanceof LanguageExpressionEditorView)
      {
         ((LanguageExpressionEditorView) this.dialog).disposeDialog();
      }
      else
      {
         ((PictureExpressionEditorView) this.dialog).disposeDialog();
      }
   }
}
