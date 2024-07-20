package vokabeltrainer.panels.dictionary;

import vokabeltrainer.types.LanguageDirection;

public interface TrashCanDialogConnector
{

   boolean isRestore();

   void tableValidateRepaint();

   LanguageDirection getSelectedLanguage();

   boolean isTableNotNull();

   void setRestore(boolean restore);

   void selectAllExpressionsInTable();

   void unselectAllExpressionsInTable();

}
