package vokabeltrainer.tonionlayout;
/*
 * Copyright (c) 2020, Birke Heeren All rights reserved.
 * Use only at own risk.
 *
 * TOnion Project
 * Version 3.0: 19 July 2020
 */
import java.awt.AWTError;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;

import javax.swing.JViewport;

/**
 * The <code>BullsEyeLayout</code> class is a layout manager that lays out a
 * container's component in the center.
 * <p>
 * Minimum and maximum sizes are taken into account.
 * <p>
 * <code>TotemLayout</code>, <code>TrainLayout</code> and
 * <code>BullsEyeLayout</code> work together like layers of an onion. They stack
 * into each other and are called TOnionLayout. TOnionLayout was developed to
 * layout forms and datamasks. By using minimum and maximum size the layout will
 * resize to fit the available space. The components inside TOnionLayout only
 * have to fit together approximately, the layout will align the components to
 * look neatly by itself. <code>BullsEyeLayout</code> will give the component the
 * optimal width and height.
 * <p>
 * Even though TOnionLayout is done top-down each layer inquires about the
 * minimum and maximum sizes of all its components. To acquire a good
 * performance each layer caches the overall minimum and maximum size of its
 * components. Therefore BullsEyeLayout can not be shared. Adding or removing a
 * component invalidates the cache of the layout and all TOnionLayouts above
 * it.
 * <p>
 * All first components inside a TOnionLayout must have a minimum and maximum
 * size set for the layout to function properly, otherwise minimum and maximum
 * sizes are estimated. TOnionLayers that change between filled and empty should
 * have a minimum and maximum size set, which is only used when empty.
 * <p>
 * JButtons should be wrapped with a JPanel that has a FlowLayout. The minimum
 * and maximum sizes are set on the JPanel.
 * <p>
 * JTables should be wrapped with a JPanel that has a BorderLayout and be added
 * to the center component. The minimum and maximum sizes are set on the JPanel.
 * <p>
 * TOnionLayout can be placed inside a JScrollPane. If the window size is
 * deceased TOnionLayout will shrink to its minimum size before the scrollbars
 * appear.
 * <p>
 * TOnionLayout corrects inconsistencies of minimum and maximum sizes with
 * maximum = minimum; Use <code>BullsEyeLayoutTest</code> to show inconsistencies
 * or method toString().
 *
 * @author Birke Heeren
 * @since private
 * @version BullsEyeLayout 3.0 (released 19. July 2020)
 */

public class BullsEyeLayout
      implements LayoutManager, LayoutManager2, java.io.Serializable
{

   /*
    * serialVersionUID
    */
   private static final long serialVersionUID = 5350471242829162225L;

   /**
    * BullsEyeLayout remembers the minimum size of its components. Adding or
    * deleting a component causes the minimum size to be recalculated. The
    * update is passed up the TOnion layers to the outside, therefore
    * BullsEyeLayout must know the component it is assigned to. BullsEyeLayout
    * can not be shared between components.
    */
   private Dimension dimMin;

   /**
    * BullsEyeLayout remembers the maximum size of its components. Adding or
    * deleting a component causes the maximum size to be recalculated. The
    * update is passed up the TOnion layers to the outside, therefore
    * BullsEyeLayout must know the component it is assigned to. BullsEyeLayout
    * can not be shared between components.
    */
   private Dimension dimMax;

   /**
    * This is the container BullsEyeLayout is assigned to.
    */
   private Container self;

   /**
    * Creates a BullsEyeLayout.
    */
   public BullsEyeLayout(Container self)
   {
      this.dimMin = null;
      this.dimMax = null;
      this.self = self;
   }

   /**
    * Determines the preferred size of the container argument using this
    * BullsEyeLayout.
    * <p>
    * The preferred size is all size available
    *
    * @param self
    *           the container in which to do the layout
    * @return the preferred dimensions to lay out the subcomponents of the
    *         specified container
    * @see java.awt.Container#getPreferredSize()
    */
   @Override
   public Dimension preferredLayoutSize(Container self)
   {
      synchronized (self.getTreeLock())
      {
         checkContainer(self);
         int ncomponents = self.getComponentCount();
         if (ncomponents > 1)
         {
            throw new AWTError("BullsEyeLayout can hold only one component");
         }
         if (ncomponents == 0)
         {
            if (self.getMinimumSize() != null)
            {
               return self.getMinimumSize();
            }
            else if (self.getParent() instanceof JViewport)
            {
               JViewport vp = (JViewport) self.getParent();
               Insets insets = self.getInsets();
               return new Dimension(vp.getWidth() - insets.left - insets.right,
                     vp.getHeight() - insets.top - insets.bottom);
            }
            else
            {
               return self.getSize();
            }
         }

         if (self.getParent() instanceof JViewport)
         {
            return this.minimumLayoutSize(self);
         }

         Insets insets = self.getInsets();
         int w;
         int h;

         h = self.getHeight() - (insets.top + insets.bottom);
         w = self.getWidth() - (insets.left + insets.right);

         int hmin = 0;
         int hmax = Integer.MAX_VALUE;
         int wmin = 0;
         int wmax = Integer.MAX_VALUE;
         Component comp = self.getComponent(0);
         Dimension dmin;
         Dimension dmax;
         /*
          * In case Component is Container with Layout instance of TrainLayout,
          * TotemLayout or BullsEyeLayout the dimensions derived by content - if
          * any - should override given Dimensions. Only when there is no
          * content the given Dimensions should be used.
          */
         if (comp instanceof Container && (((Container) comp)
               .getLayout() instanceof TotemLayout
               || ((Container) comp).getLayout() instanceof TrainLayout
               || ((Container) comp).getLayout() instanceof BullsEyeLayout))
         {
            Dimension dminContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).minimumLayoutSize((Container) comp);
            if (dminContent != null)
               dmin = dminContent;
            else
               dmin = comp.getMinimumSize();
            Dimension dmaxContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).maximumLayoutSize((Container) comp);
            if (dmaxContent != null)
               dmax = dmaxContent;
            else
               dmax = comp.getMaximumSize();
         }
         else
         {
            dmin = comp.getMinimumSize();
            dmax = comp.getMaximumSize();
         }

         // MINIMUM
         if (dmin != null)
         {
            if (dmin.height > hmin)
               hmin = dmin.height; // minheight is maximized
            if (dmin.width > wmin)
               wmin = dmin.width; // minwidth is maximized
         }
         else // minimum was not set on innermost layer
         {
            hmin = h;
            wmin = w;
         }

         // MAXIMUM
         if (dmax != null)
         {
            if (dmax.height < hmax)
               hmax = dmax.height; // maxheight is minimized
            if (dmax.width < wmax)
               wmax = dmax.width; // maxwidth is minimized
         }
         else // maximum was not set on innermost layer
         {
            hmax = h;
            wmax = w;
         }

         // height
         if (hmin > hmax)
         {
            // error correction, to show error use BullsEyeLayoutTest or
            // toString()
            hmax = hmin;
         }
         else if (hmax != Integer.MAX_VALUE)
         {
            if (h <= hmin)
               h = hmin;
            else if (hmax < h)
               h = hmax;
            // else h = h;
         }
         else if (h < hmin)
            h = hmin;
         // else h = h;

         // width
         if (wmin > wmax)
         {
            // error correction, to show error use BullsEyeLayoutTest or
            // toString()
            wmax = wmin;
         }
         else if (wmax != Integer.MAX_VALUE)
         {
            if (w <= wmin)
               w = wmin;
            else if (wmax < w)
               w = wmax;
            // else w = w;
         }
         else if (w < wmin)
            w = wmin;
         // else w = w;

         return new Dimension(w, h);
      }
   }

   /**
    * Determines the minimum size of the container argument using this
    * BullsEyeLayout.
    * <p>
    * The minimum height of a BullsEyeLayout is the minimum height of the
    * component in the container, plus the top and bottom insets of the self
    * container.
    * <p>
    * The minimum width of a BullsEyeLayout is the minimum width of the
    * component in the container, plus the left and right insets of the self
    * container.
    *
    * @param self
    *           the container in which to do the layout
    * @return the minimum dimensions needed to lay out the subcomponents of the
    *         specified container
    * @see java.awt.Container#doLayout
    */
   @Override
   public Dimension minimumLayoutSize(Container self)
   {
      synchronized (self.getTreeLock())
      {
         checkContainer(self);
         if (dimMin != null)
            return dimMin;
         Insets insets = self.getInsets();
         int h = 0;
         int w = 0;
         int ncomponents = self.getComponentCount();
         if (ncomponents > 1)
         {
            throw new AWTError("BullsEyeLayout can hold only one component");
         }
         if (ncomponents == 0)
         {
            dimMin = null;
            return null;
         }

         Component comp = self.getComponent(0);
         Dimension dmin;

         /*
          * In case Component is Container with Layout instance of TrainLayout,
          * TotemLayout or BullsEyeLyout the dimensions derived by content - if
          * any - should override given Dimensions. Only when there is no
          * content the given Dimensions should be used.
          */
         if (comp instanceof Container && (((Container) comp)
               .getLayout() instanceof TotemLayout
               || ((Container) comp).getLayout() instanceof TrainLayout
               || ((Container) comp).getLayout() instanceof BullsEyeLayout))
         {
            Dimension dminContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).minimumLayoutSize((Container) comp);
            if (dminContent != null)
               dmin = dminContent;
            else
               dmin = comp.getMinimumSize();
         }
         else
         {
            dmin = comp.getMinimumSize();
         }
         if (dmin != null)
         {
            if (h < dmin.height)
               h = dmin.height; // minheight is maximized
            if (w < dmin.width)
               w = dmin.width; // minwidth is maximized
         }
         else
         {
            h = self.getHeight() - (insets.top + insets.bottom);
            w = self.getWidth() - (insets.left + insets.right);
         }

         dimMin = new Dimension(w, h);
         return dimMin;
      }
   }

   /**
    * Determines the maximum size of the container argument using this
    * BullsEyeLayout.
    * <p>
    * The maximum height of a BullsEyeLayout is the maximum height of the
    * component in the container, plus the top and bottom insets of the self
    * container.
    * <p>
    * The maximum width of a BullsEyeLayout is the maximum width of the
    * component in the container, plus the left and right insets of the self
    * container.
    *
    * @param self
    *           the container in which to do the layout
    * @return the minimum dimensions needed to lay out the subcomponents of the
    *         specified container
    * @see java.awt.Container#doLayout
    */
   @Override
   public Dimension maximumLayoutSize(Container self)
   {
      synchronized (self.getTreeLock())
      {
         checkContainer(self);
         if (dimMax != null)
            return dimMax;
         Insets insets = self.getInsets();
         int h = 0;
         int w = 0;
         int ncomponents = self.getComponentCount();
         if (ncomponents > 1)
         {
            throw new AWTError("BullsEyeLayout can hold only one component");
         }

         Component comp = self.getComponent(0);
         Dimension dmin;

         /*
          * In case Component is Container with Layout instance of TrainLayout,
          * TotemLayout or BullsEyeLyout the dimensions derived by content - if
          * any - should override given Dimensions. Only when there is no
          * content the given Dimensions should be used.
          */
         if (comp instanceof Container && (((Container) comp)
               .getLayout() instanceof TotemLayout
               || ((Container) comp).getLayout() instanceof TrainLayout
               || ((Container) comp).getLayout() instanceof BullsEyeLayout))
         {
            Dimension dminContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).minimumLayoutSize((Container) comp);
            if (dminContent != null)
               dmin = dminContent;
            else
               dmin = comp.getMinimumSize();
         }
         else
         {
            dmin = comp.getMinimumSize();
         }
         if (dmin != null)
         {
            if (h < dmin.height)
               h = dmin.height; // minheight is maximized
            if (w < dmin.width)
               w = dmin.width; // minwidth is maximized
         }
         else
         {
            h = self.getHeight() - (insets.top + insets.bottom);
            w = self.getWidth() - (insets.left + insets.right);
         }

         return new Dimension(w, h);
      }
   }

   /**
    * Lays out the specified container using this layout.
    * <p>
    * This method reshapes the component in the specified self container in
    * order to satisfy the constraints of the <code>BullsEyeLayout</code>
    * object.
    * <p>
    * The component in a BullsEyeLayout is given the maximal height and width
    * within its minium and maximum dimensions range. If the available space is
    * larger than needed by the component, then the component is placed in the
    * center. If the available space is smaller than needed by the component,
    * then the component is placed at the top respectively left and some part of
    * it will be hidden.
    *
    * @param self
    *           the container in which to do the layout
    * @see java.awt.Container
    * @see java.awt.Container#doLayout
    */
   @Override
   public void layoutContainer(Container self)
   {
      synchronized (self.getTreeLock())
      {
         checkContainer(self);
         int ncomponents = self.getComponentCount();
         if (ncomponents > 1)
         {
            throw new AWTError("BullsEyeLayout can hold only one component");
         }
         if (ncomponents == 0)
            return;

         Insets insets = self.getInsets();
         int availableHeight;
         int availableWidth;
         if (self.getParent() instanceof JViewport)
         {
            JViewport vp = (JViewport) self.getParent();
            availableHeight = vp.getHeight() - (insets.top + insets.bottom);
            availableWidth = vp.getWidth() - (insets.left + insets.right);
         }
         else
         {
            availableHeight = self.getHeight() - (insets.top + insets.bottom);
            availableWidth = self.getWidth() - (insets.left + insets.right);
         }

         int h = availableHeight;
         int w = availableWidth;
         int hmin = 0;
         int hmax = Integer.MAX_VALUE;
         int wmin = 0;
         int wmax = Integer.MAX_VALUE;

         Component comp = self.getComponent(0);
         Dimension dmin;
         Dimension dmax;
         /*
          * In case Component is Container with Layout instance of TrainLayout
          * or TotemLayout the dimensions derived by content - if any - should
          * override given Dimensions. Only when there is no content the given
          * Dimensions should be used.
          */
         if (comp instanceof Container && (((Container) comp)
               .getLayout() instanceof TotemLayout
               || ((Container) comp).getLayout() instanceof TrainLayout
               || ((Container) comp).getLayout() instanceof BullsEyeLayout))
         {
            Dimension dminContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).minimumLayoutSize((Container) comp);
            if (dminContent != null)
               dmin = dminContent;
            else
               dmin = comp.getMinimumSize();
            Dimension dmaxContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).maximumLayoutSize((Container) comp);
            if (dmaxContent != null)
               dmax = dmaxContent;
            else
               dmax = comp.getMaximumSize();
         }
         else
         {
            dmin = comp.getMinimumSize();
            dmax = comp.getMaximumSize();
         }

         // MINIMUM
         if (dmin != null)
         {
            if (dmin.height > hmin)
               hmin = dmin.height; // minheight is maximized
            if (dmin.width > wmin)
               wmin = dmin.width; // minwidth is maximized
         }
         else // minimum was not set on innermost layer
         {
            // w = w;
         }

         // MAXIMUM
         if (dmax != null)
         {
            if (dmax.height < hmax)
               hmax = dmax.height; // maxheight is minimized
            if (dmax.width < wmax)
               wmax = dmax.width; // maxwidth is minimized
         }
         else // maximum was not set on innermost layer
         {
            // w = w;
         }

         // height
         if (hmin > hmax)
         {
            // error correction, to show error use BullsEyeLayoutTest or
            // toString()
            hmax = hmin;
         }
         else if (hmax != Integer.MAX_VALUE)
         {
            if (h <= hmin)
               h = hmin;
            else if (hmax < h)
               h = hmax;
            // else h = h;
         }
         else if (h < hmin)
            h = hmin;
         // else h = h;

         // width
         if (wmin > wmax)
         {
            // error correction, to show error use BullsEyeLayoutTest or
            // toString()
            wmax = wmin;
         }
         else if (wmax != Integer.MAX_VALUE)
         {
            if (w <= wmin)
               w = wmin;
            else if (wmax < w)
               w = wmax;
            // else w = w;
         }
         else if (w < wmin)
            w = wmin;
         // else w = w;

         int x = insets.left;
         int y = insets.top;
         int deltaX = (availableWidth - w) / 2 + x;
         int deltaY = (availableHeight - h) / 2 + y;

         comp.setBounds(Math.max(x, deltaX), Math.max(y, deltaY), w, h);
      }
   }

   /**
    * Returns the string representation of this BullsEyeLayout's values.
    * 
    * @return a string representation of this BullsEyeLayout
    */
   public String toString()
   {
      synchronized (self.getTreeLock())
      {
         int ncomponents = self.getComponentCount();
         if (ncomponents > 1)
         {
            return "BullsEyeLayout can hold only one component, but has "
                  + ncomponents + "components";
         }
         if (ncomponents == 0)
         {
            return "BullsEyeLayout has no components.\n"
                  + "Layout MinimumSize = " + self.getMinimumSize()
                  + " Layout MaximumSize = " + self.getMaximumSize()
                  + "\nBullsEyeLayout toString() was called";
         }
         System.out.println("BullsEyeLayout has 1 component.");

         Insets insets = self.getInsets();
         int availableHeight;
         int availableWidth;
         if (self.getParent() instanceof JViewport)
         {
            JViewport vp = (JViewport) self.getParent();
            availableHeight = vp.getHeight() - (insets.top + insets.bottom);
            availableWidth = vp.getWidth() - (insets.left + insets.right);
            System.out.println(
                  "The parent of this layer is a JViewport (usually part of JScrollpane).");
         }
         else
         {
            System.out.println("The parent of this layer is "
                  + (self.getParent() != null ? self.getParent().getClass()
                        : "no parent"));
            availableHeight = self.getHeight() - (insets.top + insets.bottom);
            availableWidth = self.getWidth() - (insets.left + insets.right);
         }

         int h = availableHeight;
         int w = availableWidth;
         System.out.println("The available height is " + h);
         System.out.println("The available width is " + w);
         int hmin = 0;
         int hmax = Integer.MAX_VALUE;
         int wmin = 0;
         int wmax = Integer.MAX_VALUE;

         Component comp = self.getComponent(0);
         Dimension dmin;
         Dimension dmax;
         /*
          * In case Component is Container with Layout instance of TrainLayout
          * or TotemLayout the dimensions derived by content - if any - should
          * override given Dimensions. Only when there is no content the given
          * Dimensions should be used.
          */
         if (comp instanceof Container && (((Container) comp)
               .getLayout() instanceof TotemLayout
               || ((Container) comp).getLayout() instanceof TrainLayout
               || ((Container) comp).getLayout() instanceof BullsEyeLayout))
         {
            Dimension dminContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).minimumLayoutSize((Container) comp);
            if (dminContent != null)
            {
               dmin = dminContent;
               if (comp.getMinimumSize() != null
                     && !dmin.equals(comp.getMinimumSize()))
                  System.out.println("ATTENTION: In the component "
                        + " the MinimumSize explicitly set was overridden"
                        + "\non purpose because the component has got TOnionLayout and has got components."
                        + "\nMinimumSize explicitly set = "
                        + comp.getMinimumSize());
            }
            else
               dmin = comp.getMinimumSize();

            Dimension dmaxContent = ((LayoutManager2) ((Container) comp)
                  .getLayout()).maximumLayoutSize((Container) comp);
            if (dmaxContent != null)
            {
               dmax = dmaxContent;
               if (comp.getMaximumSize() != null
                     && !dmax.equals(comp.getMaximumSize()))
                  System.out.println("ATTENTION: In the component "
                        + " the MaximumSize explicitly set was overridden"
                        + "\non purpose because the component has got TOnionLayout and has got components."
                        + "\nMaximumSize explicitly set = "
                        + comp.getMaximumSize());
            }
            else
               dmax = comp.getMaximumSize();
         }
         else
         {
            dmin = comp.getMinimumSize();
            dmax = comp.getMaximumSize();
         }

         // MINIMUM
         if (dmin != null)
         {
            if (dmin.height > hmin)
               hmin = dmin.height; // minheight is maximized
            if (dmin.width > wmin)
               wmin = dmin.width; // minwidth is maximized
            System.out.println("Component MinimumWidth = " + dmin.width
                  + ", MinimumHeight = " + dmin.height);
         }
         else // minimum was not set on innermost layer
         {
            // w = w;
            System.err
                  .println("Component MinimumSize was not set! Estimation for "
                        + " MinimumWidth = " + w);
         }

         // MAXIMUM
         if (dmax != null)
         {
            if (dmax.height < hmax)
               hmax = dmax.height; // maxheight is minimized
            if (dmax.width < wmax)
               wmax = dmax.width; // maxwidth is minimized
            System.out.println("Component MaximumWidth = " + dmax.width
                  + ", MaximumHeight = " + dmax.height);
         }
         else // maximum was not set on innermost layer
         {
            // w = w;
            System.err
                  .println("Component MaximumSize was not set! Estimation for "
                        + " MaximumWidth = " + w);
         }

         // height
         if (hmin > hmax)
         {
            System.err.println("ERROR in component height of this layout:"
                  + "\nThe MinimumHeight required by component = " + hmin
                  + "\nis larger than"
                  + "\nthe MaximumHeight allowed by the component = " + hmax);
            hmax = hmin; // error correction
         }
         else if (hmax != Integer.MAX_VALUE)
         {
            if (h <= hmin)
            {
               if (h < hmin && self.getParent() instanceof JViewport)
               {
                  System.out.println(
                        "OKAY: JViewport (usually part of JScrollPane) should show vertical scrollbar"
                              + "\nbecause the height available = " + h
                              + "\nis smaller than the MinimumHeight required by the component = "
                              + hmin);
               }
               else if (h < hmin)
               {
                  System.err.println("ERROR: The height available = " + h
                        + "\nis smaller than the MinimumHeight required by the component = "
                        + hmin
                        + "\nTherefore part of the component will be hidden!");
               }
               h = hmin;
               System.out.println("component height is OKAY:"
                     + "\nThe MinimumHeight required by component = " + hmin
                     + "\nis smaller or equal to"
                     + "\nthe MaximumHeight allowed by the component = "
                     + hmax);
            }
            else if (hmax < h)
            {
               h = hmax;
               System.out.println("component height is OKAY:"
                     + "\nThe MinimumHeight required by component = " + hmin
                     + "\nis smaller or equal to"
                     + "\nthe MaximumHeight allowed by the component = " + hmax
                     + "\nThe height available = " + h
                     + "\nThe height of the layout is set to = " + hmax);
            }
            else // h = h;
            {
               System.out.println("component height in this layout is OKAY:"
                     + "\nThe MinimumHeight required by component = " + hmin
                     + "\nis smaller or equal to"
                     + "\nthe MaximumHeight allowed by the component = " + hmax
                     + "The height available = " + h
                     + "\nThe height of the layout is set to = " + h);
            }
         }
         else if (h < hmin)
         {
            if (self.getParent() instanceof JViewport)
            {
               System.out.println(
                     "OKAY: JViewport (usually part of JScrollPane) should show vertical scrollbar"
                           + "\nbecause the height available = " + h
                           + "\nis smaller than the MinimumHeight required by the component = "
                           + hmin);
            }
            else
            {
               System.err.println("ERROR: The height available = " + h
                     + "\nis smaller than the MinimumHeight required by the component = "
                     + hmin
                     + "Therefore part of the component will be hidden!");
            }
            h = hmin;
            System.out.println("component height is OKAY:"
                  + "\nThe MinimumHeight required by component = " + hmin
                  + "\nThe MaximumHeight was not set by the component."
                  + "\nThe height of the layout is set to = " + h);
         }
         else // h = h;
         {
            System.out.println("component height is OKAY:"
                  + "\nThe MinimumHeight required by components = " + hmin
                  + "\nThe MaximumHeight was not set by the component."
                  + "\nThe height of the layout is set to = " + h);
         }

         // width
         if (wmin > wmax)
         {
            System.err.println("ERROR in component width of this layout:"
                  + "\nThe MinimumWidth required by component = " + wmin
                  + "\nis larger than"
                  + "\nthe MaximumWidth allowed by the component = " + wmax);
            wmax = wmin; // error correction
         }
         else if (wmax != Integer.MAX_VALUE)
         {
            if (w <= wmin)
            {
               if (w < wmin && self.getParent() instanceof JViewport)
               {
                  System.out.println(
                        "OKAY: JViewport (usually part of JScrollPane) should show horizontal scrollbar"
                              + "\nbecause the width available = " + w
                              + "\nis smaller than the MinimumWidth required by the component = "
                              + wmin);
               }
               else if (w < wmin)
               {
                  System.err.println("ERROR: The width available = " + w
                        + "\nis smaller than the MinimumWidth required by the component = "
                        + wmin
                        + "\nTherefore part of the component will be hidden!");
               }
               w = wmin;
               System.out.println("component width is OKAY:"
                     + "\nThe MinimumWidth required by component = " + wmin
                     + "\nis smaller or equal to"
                     + "\nthe MaximumWidth allowed by the components = "
                     + wmax);
            }
            else if (wmax < w)
            {
               System.out.println("component width is OKAY:"
                     + "\nThe MinimumWidth required by component = " + wmin
                     + "\nis smaller or equal to"
                     + "\nthe MaximumWidth allowed by the components = " + wmax
                     + "\nThe width available = " + w
                     + "\nThe width of the layout is set to = " + wmax);
               w = wmax;
            }
            else // w = w;
            {
               System.out.println("component width in this layout is OKAY:"
                     + "\nThe MinimumWidth required by component = " + wmin
                     + "\nis smaller or equal to"
                     + "\nthe MaximumWidth allowed by the component = " + wmax
                     + "The width available = " + w
                     + "\nThe width of the layout is set to = " + w);
            }
         }
         else if (w < wmin)
         {
            if (self.getParent() instanceof JViewport)
            {
               System.out.println(
                     "OKAY: JViewport (usually part of JScrollPane) should show horizontal scrollbar"
                           + "\nbecause the width available = " + w
                           + "\nis smaller than the MinimumWidth required by the component = "
                           + wmin);
            }
            else
            {
               System.err.println("ERROR: The width available = " + w
                     + "\nis smaller than the MinimumWidth required by the component = "
                     + wmin
                     + "Therefore part of the component will be hidden!");
            }
            w = wmin;
            System.out.println("component widths are OKAY:"
                  + "\nThe MinimumWidth required by component = " + wmin
                  + "\nThe MaximumWidth was not set by the component."
                  + "\nThe width of the layout is set to = " + w);
         }
         else // w = w;
         {
            System.out.println("component width is OKAY:"
                  + "\nThe MinimumWidth required by component = " + wmin
                  + "\nThe MaximumWidth was not set by the component."
                  + "\nThe width of the layout is set to = " + w);
         }

         int x = insets.left;
         int y = insets.top;
         int deltaX = (availableWidth - w) / 2 + x;
         int deltaY = (availableHeight - h) / 2 + y;

         comp.setBounds(Math.max(x, deltaX), Math.max(y, deltaY), w, h);
         System.out.println(
               "Component is set to height = " + h + " and width = " + w);
         System.out.println("Location is x = " + Math.max(x, deltaX)
               + " and y = " + Math.max(y, deltaY));
      }
      return "\nBullsEyeLayout toString() was called";
   }

   /**
    * invalidates Layout, minimum and maximum sizes of content will be
    * recalculated
    * 
    * @param name
    *           the name of the component
    * @param comp
    *           the component to be added
    */
   @Override
   public void addLayoutComponent(String name, Component comp)
   {
      invalidateLayout(comp.getParent());
   }

   /**
    * invalidates Layout, minimum and maximum sizes of content will be
    * recalculated
    *
    * @param name
    *           the name of the component
    * @param comp
    *           the component to be added
    */
   @Override
   public void addLayoutComponent(Component comp, Object constraints)
   {
      invalidateLayout(comp.getParent());
   }

   /**
    * invalidates Layout, minimum and maximum sizes of content will be
    * recalculated
    * 
    * @param comp
    *           the component to be removed
    */
   @Override
   public void removeLayoutComponent(Component comp)
   {
      invalidateLayout(comp.getParent());
   }

   /**
    * Invalidates the layout, indicating that if the layout manager has cached
    * information it should be discarded.
    */
   @Override
   public void invalidateLayout(Container self)
   {
      checkContainer(self);
      this.dimMin = null;
      this.dimMax = null;
      if (self.getParent() != null && self.getParent().getLayout() != null
            && (self.getParent().getLayout() instanceof TotemLayout
                  || self.getParent().getLayout() instanceof TrainLayout
                  || self.getParent().getLayout() instanceof BullsEyeLayout))
      {
         ((LayoutManager2) self.getParent().getLayout())
               .invalidateLayout(self.getParent());
      }
   }

   /**
    * Returns the alignment along the x axis. This specifies how the component
    * would like to be aligned relative to other components. The value should be
    * a number between 0 and 1 where 0 represents alignment along the origin, 1
    * is aligned the furthest away from the origin, 0.5 is centered, etc.
    */
   @Override
   public float getLayoutAlignmentX(Container self)
   {
      return 0;
   }

   /**
    * Returns the alignment along the y axis. This specifies how the component
    * would like to be aligned relative to other components. The value should be
    * a number between 0 and 1 where 0 represents alignment along the origin, 1
    * is aligned the furthest away from the origin, 0.5 is centered, etc.
    */
   @Override
   public float getLayoutAlignmentY(Container self)
   {
      return 0;
   }

   void checkContainer(Container self)
   {
      if (this.self != self)
      {
         throw new AWTError("BullsEyeLayout can't be shared");
      }
   }
}
