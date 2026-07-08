package vokabeltrainer.types;

public enum LanguageDirection
{
   OWN_TO_HEBREW(Direction.OWN_TO_NEW),
   HEBREW_TO_OWN(Direction.NEW_TO_OWN),
   OWN_TO_SWEDISH(Direction.OWN_TO_NEW),
   SWEDISH_TO_OWN(Direction.NEW_TO_OWN),
   OWN_TO_GERMAN(Direction.OWN_TO_NEW),
   GERMAN_TO_OWN(Direction.NEW_TO_OWN);
   
   private Direction direction;

   LanguageDirection(Direction direction)
   {
      this.direction = direction;
   }

   public Direction getDirection()
   {
      return direction;
   }

}
