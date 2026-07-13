package vokabeltrainer;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import vokabeltrainer.types.Direction;
import vokabeltrainer.types.Expression;
import vokabeltrainer.types.SortingType;

public class ExpressionComparator implements Comparator<Expression> {

    private SortingType sortingType;
    private Direction direction;

    public ExpressionComparator(SortingType sortingType, Direction direction) {
        this.sortingType = sortingType;
        this.direction = direction;
    }

    @Override
    public int compare(Expression o1, Expression o2) {
        switch (sortingType) {
            case DATE:
                return o2.getLastModified().compareTo(o1.getLastModified());
            case INDEX:
                int o1Int, o2Int;

                try {
                    o1Int = Integer.valueOf(o1.getSortingIndex());
                    o2Int = Integer.valueOf(o2.getSortingIndex());
                    if (o1Int > o2Int) {
                        return 1;
                    } else if (o1Int < o2Int) {
                        return -1;
                    }
                    return 0;
                } catch (Exception e) {
                }

                Collator coll = Collator.getInstance(Locale.GERMAN);
                coll.setStrength(Collator.PRIMARY);
                return coll.compare(o1.getSortingIndex(), o2.getSortingIndex());
            case ALPHABET:
                if (Direction.NEW_TO_OWN == direction) {
                    if (o1.getLL().isSwedish()) {
                        Collator coll2 = Collator.getInstance(Locale.GERMAN);
                        coll2.setStrength(Collator.PRIMARY);
                        return coll2.compare(o1.getLL().getSwedish(),
                            o2.getLL().getSwedish());
                    } else if (o1.getLL().isGerman()) {
                        Collator coll2 = Collator.getInstance(Locale.GERMAN);
                        coll2.setStrength(Collator.PRIMARY);
                        return coll2.compare(o1.getLL().getGerman(),
                            o2.getLL().getGerman());
                    }
                    return o1.getLL().getHebrewNoMatterWhichKind()
                        .compareTo(o2.getLL().getHebrewNoMatterWhichKind());
                } else {
                    Collator coll2 = Collator.getInstance(Locale.GERMAN);
                    coll2.setStrength(Collator.PRIMARY);
                    return coll2.compare(o1.getOwnLanguage(), o2.getOwnLanguage());
                }
        }
        return 0;
    }

}
