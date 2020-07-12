package vokabeltrainer.panels.backup;

import vokabeltrainer.panels.BackupPanelView;

public class BackupController implements BackupControllerConnector
{
   private BackupPanelView backupPanel;
   
   public BackupController()
   {
     backupPanel = new BackupPanelView(this);
   }

   public BackupPanelView getBackupPanel()
   {
      return backupPanel;
   }

}
