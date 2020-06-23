package vokabeltrainer.panels.dictionary;

import vokabeltrainer.panels.dialogs.TrashCanDialog;
import vokabeltrainer.types.Language;

public class TrashCanController implements TrashCanControllerConnector
{
   private TrashCanDialog trashCanDialog;
   private DictionaryControllerConnector connector;

   public TrashCanController(DictionaryControllerConnector connector,
         Language initialLanguage)
   {
      this.connector = connector;
      this.trashCanDialog = new TrashCanDialog(this, initialLanguage);
   }

   @Override
   public DictionaryControllerConnector getDictionaryControllerConnector()
   {
      return connector;
   }

   public TrashCanDialog getTrashCanDialog()
   {
      return trashCanDialog;
   }
}
