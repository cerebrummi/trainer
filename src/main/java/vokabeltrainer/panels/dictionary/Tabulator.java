package vokabeltrainer.panels.dictionary;

public enum Tabulator
{
   CHAPTER_TAB(0), SEARCH_TAB(1), KIND_TAB(2), NEW_TAB(3), SELECTED_TAB(4);

   private int index;
   private static Tabulator tabShowing;

   Tabulator(int index)
   {
      this.index = index;
   }

   public int getIndex()
   {
      return index;
   }

   public static Tabulator getTabShowing()
   {
      return tabShowing;
   }

   public static void setTabShowing(Tabulator tabShowing)
   {
      Tabulator.tabShowing = tabShowing;
   }
}
