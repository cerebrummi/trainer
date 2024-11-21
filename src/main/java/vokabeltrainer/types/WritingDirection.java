package vokabeltrainer.types;

import java.awt.ComponentOrientation;

public enum WritingDirection
{
   LEFT_TO_RIGHT(ComponentOrientation.LEFT_TO_RIGHT),
   RIGHT_TO_LEFT(ComponentOrientation.RIGHT_TO_LEFT);

   private ComponentOrientation componentOrientation;
   
   WritingDirection(ComponentOrientation componentOrientation)
   {
      this.componentOrientation = componentOrientation;
   }

   public ComponentOrientation getComponentOrientation()
   {
      return componentOrientation;
   }
}
