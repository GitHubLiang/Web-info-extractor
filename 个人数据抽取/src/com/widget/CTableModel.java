package com.widget;

import javax.swing.table.DefaultTableModel;
 
public class CTableModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}