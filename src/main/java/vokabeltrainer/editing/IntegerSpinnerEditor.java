package vokabeltrainer.editing;

import javax.swing.JSpinner;
import java.io.Serial;

public class IntegerSpinnerEditor extends JSpinner.DefaultEditor
{
   @Serial
   private static final long serialVersionUID = -2883157635376997080L;

   public IntegerSpinnerEditor(JSpinner spinner)
   {
      super(spinner);

      getTextField().setFormatterFactory(new NumberCodeFormatterFactory());
      getTextField().setEditable(false);
   }
}
