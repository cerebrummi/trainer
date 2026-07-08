package vokabeltrainer.panels.dictionary;

import java.util.HashMap;

public class Interaction
{ 
   private static HashMap<Interaction, Command> interactions;
   
   static
   {
      Interaction.interactions = new HashMap<>();
      
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.CHAPTER_WHICH), Command.RESTORE_WHICH_CHAPTER);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.SEARCH_WHICH_OWN), Command.RESTORE_WHICH_SEARCH_OWN);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.SEARCH_WHICH_NEW), Command.RESTORE_WHICH_SEARCH_NEW);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.OPENED_PAGE, Status.TAB_CHAPTER), Command.NO_TABLE);
      
      interactions.put(new Interaction(Action.NEW_TO_OWN, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.NEW_TO_OWN, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.NEW_TO_OWN, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.NEW_TO_OWN, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
      interactions.put(new Interaction(Action.NEW_TO_OWN, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);      
      
      interactions.put(new Interaction(Action.OWN_TO_NEW, Status.OPENED_PAGE), Command.NOTHING);
      interactions.put(new Interaction(Action.OWN_TO_NEW, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.OWN_TO_NEW, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.OWN_TO_NEW, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.OWN_TO_NEW, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.SELECT_TABLE, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
      
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.UNSELECT_TABLE, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
    
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.UNSELECT_ALL, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
      
      interactions.put(new Interaction(Action.UNSELECT_EXPRESSION, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.CHAPTER_WHICH), Command.RESTORE_WHICH_CHAPTER);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.WORK_WASTEBIN, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
      
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.DELETE_SELECTED_IN_TABLE, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
      
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.DELETE_ALL_SELECTED, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
      
      interactions.put(new Interaction(Action.SAVE, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.SAVE, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.SAVE, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.SAVE, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
      
      interactions.put(new Interaction(Action.SORT_NOW, Status.TAB_SELECTED_EXPRESSIONS), Command.TABLE_SELECTED_EXPRESSIONS);
      interactions.put(new Interaction(Action.SORT_NOW, Status.CHAPTER_WHICH), Command.TABLE_CHAPTER_WHICH);
      interactions.put(new Interaction(Action.SORT_NOW, Status.EXPRESSIONKIND_WHICH), Command.TABLE_EXPRESSIONKIND_WHICH);
      interactions.put(new Interaction(Action.SORT_NOW, Status.SEARCH_WHICH_OWN), Command.TABLE_SEARCH_WHICH_OWN);
      interactions.put(new Interaction(Action.SORT_NOW, Status.SEARCH_WHICH_NEW), Command.TABLE_SEARCH_WHICH_NEW);
   }
   
   public static Command getCommand(Interaction interaction)
   {
      if (interactions.get(interaction) == null)
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
            System.out.println("Interaction " + interaction + Command.ERROR);
            return Command.ERROR;
         }
      }
      return interactions.get(interaction);
   }

   public Interaction(Action action, Status status)
   {
      this.action = action;
      this.status = status;
   }

   public Interaction(Status status)
   {
      this.status = status;
   }

   private Status status;
   private Action action;

   Status getStatus()
   {
      return status;
   }

   Action getAction()
   {
      return action;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((action == null) ? 0 : action.hashCode());
      result = prime * result + ((status == null) ? 0 : status.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Interaction other = (Interaction) obj;
      if (action != other.action)
         return false;
      if (status != other.status)
         return false;
      return true;
   }

   @Override
   public String toString()
   {
      return "Interaction [status=" + status + ", action=" + action + "]";
   }

}
