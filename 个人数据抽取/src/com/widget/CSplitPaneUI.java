package com.widget;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.plaf.basic.*;

/**
 * @author ¿Ó÷”Œæ
 *
 */
public class CSplitPaneUI extends BasicSplitPaneUI implements Serializable {
    private Color foreground;
    private CSplitPaneDivider divider;
    
    @Override
    public BasicSplitPaneDivider createDefaultDivider() {
        divider = new CSplitPaneDivider(this);
        if (foreground != null) {
            divider.setForeground(foreground);
            divider.repaint();
        }
        return getDivider();
    }
    
    public void setForeground(Color fg) {
        this.foreground = fg;
        if (getDivider() != null)
            getDivider().setForeground(foreground);
    }
    
    public CSplitPaneDivider getDivider() {
        return divider;
    }
}