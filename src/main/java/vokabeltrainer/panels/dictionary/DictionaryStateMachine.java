package vokabeltrainer.panels.dictionary;

public class DictionaryStateMachine
{
   public static Command getCommand(Interaction interaction)
   {
      if (Interaction.interactions.get(interaction) == null)
      {
         switch (interaction.getAction())
         {
         case CHAPTER_WHICH:
            return Command.TABLE_CHAPTER_WHICH;
         case COPY:
            return Command.NOTHING;
         case DELETE_ALL_SELECTED:
            return Command.NOTHING;
         case DELETE_SELECTED_IN_TABLE:
            return Command.NOTHING;
         case EXPRESSIONKIND_WHICH:
            return Command.TABLE_EXPRESSIONKIND_WHICH;
         case MOVE_TO_CHAPTER:
            return Command.TABLE_SELECTED_EXPRESSIONS;
         case MOVE_TO_DATABASE:
            return Command.TABLE_SELECTED_EXPRESSIONS;
         case NEW_EXPRESSION:
            return Command.NOTHING; // must be nothing, because it is always
                                    // followed by Action "save"
         case NEW_TO_OWN:
            return Command.NO_TABLE;
         case OPENED_PAGE:
            return Command.NO_TABLE;
         case OWN_TO_NEW:
            return Command.NO_TABLE;
         case SAVE:
            return Command.NOTHING;
         case SEARCH_WHICH_NEW:
            return Command.TABLE_SEARCH_WHICH_NEW;
         case SEARCH_WHICH_OWN:
            return Command.TABLE_SEARCH_WHICH_OWN;
         case SELECT_TABLE:
            return Command.NOTHING;
         case SORT_NOW:
            return Command.NOTHING;
         case TAB_CHAPTER:
            return Command.NO_TABLE;
         case TAB_EXPRESSIONKIND:
            return Command.NO_TABLE;
         case TAB_SEARCH:
            return Command.NO_TABLE;
         case TAB_SELECTED_EXPRESSIONS:
            return Command.TABLE_SELECTED_EXPRESSIONS;
         case UNSELECT_ALL:
            return Command.NOTHING;
         case UNSELECT_EXPRESSION:
            return Command.NOTHING;
         case UNSELECT_TABLE:
            return Command.NOTHING;
         case WORK_SHREDDER:
            return Command.NOTHING;
         case WORK_WASTEBIN:
            return Command.NO_TABLE;
         case DATA_CHAPTER:
            return Command.NO_TABLE;
         default:
            return Command.ERROR;
         }
      }
      return Interaction.interactions.get(interaction);
   }
}
