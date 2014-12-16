package com.widget;

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.table.*;
 
public class CTable extends JTable {
    public CTable() {
        initialize();
    }
    
    /**
     * This method initializes this
     */
    private void initialize() {
        setOpaque(false);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // getTableHeader().setDefaultRenderer(new CTableColumnRenderer());
        getTableHeader().setOpaque(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setShowVerticalLines(false);
        setBorder(null);
    }
    
    /**
     * @author Administrator
     */
    private final class CTableColumnRenderer implements TableCellRenderer,
            Serializable {
        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            JLabel headTitle = new JLabel("|  " + value.toString());
            headTitle.setForeground(Color.WHITE);
            return headTitle;
        }
    }
}
