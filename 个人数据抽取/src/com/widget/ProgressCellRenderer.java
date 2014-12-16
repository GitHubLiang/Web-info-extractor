/**
 * 
 */
package com.widget;

import java.awt.Component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 * @author ¿Ó÷”Œæ
 *
 */
public class ProgressCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        ProgressBar bar = new ProgressBar();
        bar.setStringPainted(true);
        bar.setValue((Integer) value);
        return bar;
    }
    
    class ProgressBar extends JProgressBar {
        
        @Override
        public String toString() {
            String str = String.format("%03d", getValue());
            return str;
        }
    }
}