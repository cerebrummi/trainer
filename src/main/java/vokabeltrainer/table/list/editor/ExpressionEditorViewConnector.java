package vokabeltrainer.table.list.editor;

import java.util.Set;

import vokabeltrainer.common.main.App;
import vokabeltrainer.types.grammatical.GrammaticalEnum.GrammaticalParentEnum;

public interface ExpressionEditorViewConnector
{
   public void showGrammaticalParentEnums(App app,
         Set<GrammaticalParentEnum> grammaticalEnumsToShow);
}
