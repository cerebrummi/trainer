package vokabeltrainer.tonionlayout;

import java.awt.LayoutManager;

/** LayoutHelper 4.0 (released 24. June 2026)
* 
* Copyright (c) 2026 Birke Heeren
*
* Licensed under the MIT License.
* 
* */

public class LayoutHelper
{
   static boolean isTOnionLayout(LayoutManager layout)
   {
      return layout instanceof TrainLayout 
            || layout instanceof TotemLayout
            || layout instanceof BullsEyeLayout
            || layout instanceof ExpanderLayout;
   }
}
