package vokabeltrainer.cmd;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import vokabeltrainer.common.main.App;

public class Renderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 2183657576759647252L;
    
    private App app;
    
    public Renderer(App app)
    {
       this.app = app;
    }

    @Override
    public void setOpaque(boolean makeBackGroundVisible) {
        super.setOpaque(true); // THIS DOES THE TRICK
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        setText((String) value);
        setBackground(app.appColors.getLightYellow());
        return this;
    }
}