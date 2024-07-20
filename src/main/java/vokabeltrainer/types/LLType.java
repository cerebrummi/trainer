package vokabeltrainer.types;

public enum LLType
{
   HEBREW,
   SWEDISH,
   UNKOWN;
   
   public static final LLType[] ALL = {HEBREW, SWEDISH};
   public static final LLType[] SWEDISH_ONLY = {SWEDISH};
   public static final LLType[] HEBREW_ONLY = {HEBREW};
}
