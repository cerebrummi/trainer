package vokabeltrainer.panels.dictionary;

public interface TrashCanDialogConnector
{

   boolean isRestore();

   void tableValidateRepaint();

   boolean isTableNotNull();

   void setRestore(boolean restore);

   void selectAllExpressionsInTable();

   void unselectAllExpressionsInTable();

}
