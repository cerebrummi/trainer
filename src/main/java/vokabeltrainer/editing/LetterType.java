package vokabeltrainer.editing;

public enum LetterType
{
   GERMAN("_de"),
   HEBREW("_il"),
   NUMBER("_##"),
   SIGN("_**"),
   SWEDISH("_se"),
   NONE("");

   private String realm;
   
   LetterType(String realm)
   {
      this.realm = realm;
   }

   public String getRealm()
   {
      return realm;
   }
   
}
