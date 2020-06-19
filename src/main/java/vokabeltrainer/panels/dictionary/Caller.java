package vokabeltrainer.panels.dictionary;

public enum Caller
{
   CHAPTER_TAB(0), SEARCH_TAB(1), KIND_TAB(2), NEW_TAB(3), SELECTED_TAB(4);

   private int index;
   private static Caller tabShowing;

   Caller(int index)
   {
      this.index = index;
   }

   public int getIndex()
   {
      return index;
   }

   public static Caller getTabShowing()
   {
      return tabShowing;
   }

   public static void setTabShowing(Caller tabShowing)
   {
      Caller.tabShowing = tabShowing;
   }
}
