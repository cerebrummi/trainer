package vokabeltrainer.common;

public final class Main
{
   static void main(String[] args)
   {
      Initializer initializer = new Initializer();
      
      initializer.programStart();
      
      initializer.preloadAfterProgramStart();
   }
}
