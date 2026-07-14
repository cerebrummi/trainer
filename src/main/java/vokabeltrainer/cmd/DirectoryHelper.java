package vokabeltrainer.cmd;

import java.io.File;
import javax.swing.JOptionPane;

import vokabeltrainer.panels.translation.Translation;
import vokabeltrainer.common.main.Common;
import vokabeltrainer.common.main.View;

public class DirectoryHelper {

    public boolean makeDirectory(Common common, View view, File customDir) {
        try {
            customDir.mkdirs();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getjFrame(),
                common.getTranslator()
                    .realisticTranslate(Translation.FEHLER_BEIM_SPEICHERN),
                common.getTranslator()
                    .realisticTranslate(Translation.FEHLERMELDUNG) + " \n" + e,
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
