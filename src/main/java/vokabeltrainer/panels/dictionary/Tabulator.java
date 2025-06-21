package vokabeltrainer.panels.dictionary;

public enum Tabulator
{
   DATA_TAB(0), CHAPTER_TAB(1), SEARCH_TAB(2), KIND_TAB(3), SELECTED_TAB(4);

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
