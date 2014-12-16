package com.widget;

import java.awt.Color;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.JSplitPane;

 
public class CSplitPane extends JSplitPane {
    
    private final class UIAdapter extends ComponentAdapter implements
            Serializable {
        public void componentResized(ComponentEvent e) {
            setUI(getCSplitPaneUI());
        }
    }
    
    private static final long serialVersionUID = 1L;
    private CSplitPaneUI cSplitPaneUI; 
    private UIAdapter uiAdapter;
    
    /**
     * This is the default constructor
     */
    public CSplitPane() {
        // super();
        uiAdapter = new UIAdapter();
        initialize();
    }
    
    public CSplitPaneUI getCSplitPaneUI() {
        if (cSplitPaneUI == null) {
            cSplitPaneUI = new CSplitPaneUI();
        }
        return cSplitPaneUI;
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.addComponentListener(uiAdapter);
        this.setUI(getCSplitPaneUI());
        this.setSize(300, 200);
        setOpaque(false);
    }
    
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        getCSplitPaneUI().setForeground(fg);
    }
    
}
