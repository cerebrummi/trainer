package vokabeltrainer.types;

public enum LLType
{
   HEBREW,
   SWEDISH,
   GERMAN,
   UNKOWN;
   
   public static final LLType[] ALL = {HEBREW, SWEDISH, GERMAN};
   public static final LLType[] SWEDISH_ONLY = {SWEDISH};
   public static final LLType[] HEBREW_ONLY = {HEBREW};
   public static final LLType[] ALL_BUT_GERMAN = {HEBREW, SWEDISH};
   public static final LLType[] GERMAN_ONLY = {GERMAN};
   public static final LLType[] ALL_BUT_SWEDISH = {HEBREW, GERMAN};
}
