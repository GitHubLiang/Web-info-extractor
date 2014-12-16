package com.Frame;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.*;
import com.entity.*;
import com.login.Session;
import com.Listener.*;
import com.Listener.ComponentAdapter;
import com.dao.QueryTableDao; 
import com.widget.*;

public class ShowMyTable extends JPanel implements TreeSelectionListener {
    
    private static final long serialVersionUID = 1L;
    private JPanel jPanel4 = null;
    private JPanel jPanel5 = null;
    private JLabel jLabel = null;
    private JFormattedTextField fromDateField = null;
    private JLabel jLabel1 = null;
    private JFormattedTextField toDateField = null;
    private JButton searchButton = null;
    private JLabel jLabel2 = null;
    private JComboBox dateComboBox = null;
    private AlphaScrollPane jScrollPane = null;
    private JTable afterTable = null;
    private JPanel jPanel7 = null;
    private JPanel jPanel11 = null;
    private JPanel jPanel10 = null;
    private AlphaScrollPane alphaScrollPane = null;
    private CTable dayProjectTable = null; 
    private JPanel jPanel13 = null;
    private CSplitPane cSplitPane = null;
    private Person person = null; // @jve:decl-index=0:
    
    /**
     * This is the default constructor
     */
    public ShowMyTable() {
        setName("ShowMyTable");
        initialize();
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(600, 500);
        setLayout(new BorderLayout());
        this.add(getCSplitPane(), BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
               // loadDayProjectTable();
                getSearchButton().doClick();
            }
        });
    }
    
    /**
     * This method initializes cSplitPane
     * 布置上下两个部分
     * @return com.lzw.widget.CSplitPane
     */
    private CSplitPane getCSplitPane() {
        if (cSplitPane == null) {
            cSplitPane = new CSplitPane();
            cSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
            cSplitPane.setContinuousLayout(true);
            cSplitPane.setTopComponent(getJPanel4());//上部用于查询
            //cSplitPane.setBottomComponent(getJPanel7());//下部用于显示所查询的表
            cSplitPane.setResizeWeight(0.5);
            cSplitPane.setOneTouchExpandable(true);
            cSplitPane.setForeground(new Color(0x8d5b51));
        }
        return cSplitPane;
    }
    
    /**
     * This method initializes jPanel4
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel4() {
        if (jPanel4 == null) {
            jPanel4 = new JPanel();
            jPanel4.setLayout(new BorderLayout());
            jPanel4.setPreferredSize(new Dimension(0, 220));
            jPanel4.setOpaque(false);
            jPanel4.setBorder(BorderFactory.createTitledBorder(null,
                    "未来十四天的任务", TitledBorder.DEFAULT_JUSTIFICATION,
                    TitledBorder.TOP, new Font("sansserif", Font.BOLD, 12),
                    new Color(48, 102, 163)));
            jPanel4.add(getJPanel5(), BorderLayout.NORTH);
            jPanel4.add(getJScrollPane(), BorderLayout.CENTER);
        }
        return jPanel4;
    }
   
    /**
     * This method initializes jScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    private AlphaScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new AlphaScrollPane();
            jScrollPane.setBackground(new Color(151, 188, 229));
            jScrollPane.setViewportView(getAfterTable());
            
        }
        return jScrollPane;
    }
    
    /**
     * This method initializes jPanel5
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel5() {
        if (jPanel5 == null) {
            GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
            gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints8.gridy = 0;
            gridBagConstraints8.weightx = 0.0;
            gridBagConstraints8.ipadx = 0;
            gridBagConstraints8.gridx = 8;
            GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
            gridBagConstraints7.gridx = 7;
            gridBagConstraints7.weightx = 1.0;
            gridBagConstraints7.fill = GridBagConstraints.NONE;
            gridBagConstraints7.anchor = GridBagConstraints.EAST;
            gridBagConstraints7.gridy = 0;
            jLabel2 = new JLabel();
            jLabel2.setText("选择其一：");
            jLabel2.setForeground(new Color(0, 0, 204));
            GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
            gridBagConstraints6.insets = new Insets(0, 2, 0, 5);
            gridBagConstraints6.gridy = 0;
            gridBagConstraints6.gridx = 6;
            GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
            gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints4.gridx = 4;
            gridBagConstraints4.gridy = 0;
            gridBagConstraints4.weightx = 0.0;
            gridBagConstraints4.ipadx = 65;
            gridBagConstraints4.insets = new Insets(0, 2, 5, 2);
            GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
            gridBagConstraints3.insets = new Insets(6, 2, 6, 2);
            gridBagConstraints3.gridy = 0;
            gridBagConstraints3.gridx = 3;
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.fill = GridBagConstraints.NONE;
            gridBagConstraints1.gridx = 1;
            gridBagConstraints1.gridy = 0;
            gridBagConstraints1.weightx = 0.0;
            gridBagConstraints1.anchor = GridBagConstraints.WEST;
            gridBagConstraints1.ipadx = 65;
            gridBagConstraints1.insets = new Insets(0, 2, 5, 2);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.insets = new Insets(6, 4, 6, 2);
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx = 0;
            jLabel1 = new JLabel();
            jLabel1.setText("到");
            jLabel = new JLabel();
            jLabel.setText("索引：");
            jPanel5 = new JPanel();
            jPanel5.setLayout(new GridBagLayout());
            jPanel5.setPreferredSize(new Dimension(0, 25));
            jPanel5.setOpaque(false);
            jPanel5.add(jLabel, gridBagConstraints);
            jPanel5.add(getFromDateField(), gridBagConstraints1);
            jPanel5.add(jLabel1, gridBagConstraints3);
            jPanel5.add(getToDateField(), gridBagConstraints4);
            jPanel5.add(getSearchButton(), gridBagConstraints6);
            jPanel5.add(jLabel2, gridBagConstraints7);
            jPanel5.add(getDateComboBox(), gridBagConstraints8);
        }
        return jPanel5;
    } 
    /**
     * This method initializes afterTable
     * 
     * @return javax.swing.JTable
     */
    private JTable getAfterTable() {
        if (afterTable == null) {
            String[] colums = new String[] { "表格名", "保存时间", "备注"};
            CTableModel tableModel = new CTableModel();
            afterTable = new CTable();
            afterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableModel.setDataVector(null, colums);
            afterTable.setModel(tableModel);
            TableCellRenderer renderer = new ProgressCellRenderer();
            //afterTable.getColumn("tableName").setCellRenderer(renderer);
        }
        return afterTable;
    } 
    /**
     * This method initializes fromDateField
     * 
     * @return javax.swing.JTextField
     */
    private JFormattedTextField getFromDateField() {
        if (fromDateField == null) {
            fromDateField = new JFormattedTextField(DateFormat
                    .getDateInstance());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            fromDateField.setValue(calendar.getTime());
            fromDateField.setColumns(6);
        }
        return fromDateField;
    }
    
    /**
     * This method initializes toDateField
     * 
     * @return javax.swing.JTextField
     */
    private JFormattedTextField getToDateField() {
        if (toDateField == null) {
            toDateField = new JFormattedTextField(DateFormat.getDateInstance());
            toDateField.setValue(new Date(System.currentTimeMillis()));
            toDateField.setColumns(6);
        }
        return toDateField;
    }
    
    /**
     * This method initializes searchButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getSearchButton() {
        if (searchButton == null) {
            searchButton = new JButton();
            searchButton.setText("搜索");
            searchButton.addActionListener(new ActionAdapter() {
                public void actionPerformed(ActionEvent e) {
                    Date from = (Date) fromDateField.getValue();
                    java.sql.Date fromDate = new java.sql.Date(from.getTime());
                    Date to = (Date) toDateField.getValue();
                    java.sql.Date toDate = new java.sql.Date(to.getTime());
                    System.out.println(fromDate+"\t"+toDate);
                    QueryTableDao dao = new QueryTableDao();
                    if (getPerson() == null)
                        return;
                    List<TableList> list = dao.searchDetails(
                            getPerson().getuserName(), fromDate, toDate);
                    loadSearchTableData(list);
                    getDateComboBox().setSelectedItem("待选天数");
                    TitledBorder border = (TitledBorder) getJPanel4()
                            .getBorder();
                    border.setTitle(fromDate + " 到 " + toDate + "表格");
                    getJPanel4().repaint();
                }
            });
        }
        return searchButton;
    }
    
    /**
     * This method initializes dateComboBox
     * 
     * @return javax.swing.JComboBox
     */
    private JComboBox getDateComboBox() {
        if (dateComboBox == null) {
            dateComboBox = new JComboBox();
            String[] list = { "待选天数", "七天以内", "十四以内", "二十一天以内","所有" };
            DefaultComboBoxModel model = new DefaultComboBoxModel(list);
            dateComboBox.setModel(model);
            dateComboBox.addItemListener(new ItemAdapter() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (getPerson() == null)
                            return;
                        Object item = e.getItem();
                        Calendar calendar = Calendar.getInstance();
                        long millis = calendar.getTimeInMillis();
                        java.sql.Date sd = new java.sql.Date(millis);
                        java.sql.Date ed = null;
                        QueryTableDao dao = new QueryTableDao();
                        String perName = getPerson().getuserName();
                        if (item.equals("七天以内")) {
                            calendar.add(Calendar.DAY_OF_MONTH, 6);
                            long m = calendar.getTimeInMillis();
                            ed = new java.sql.Date(m);
                            List<TableList> list = dao.searchDetails(
                                    perName, sd, ed);
                            loadSearchTableData(list);
                        }
                        if (item.equals("十四天以内")) {
                            calendar.add(Calendar.DAY_OF_MONTH, 13);
                            long m = calendar.getTimeInMillis();
                            ed = new java.sql.Date(m);
                            List<TableList> list = dao.searchDetails(
                                    perName, sd, ed);
                            loadSearchTableData(list);
                        }
                        if (item.equals("二十一天以内")) {
                            calendar.add(Calendar.DAY_OF_MONTH, 20);
                            long m = calendar.getTimeInMillis();
                            ed = new java.sql.Date(m);
                            List<TableList> list = dao.searchDetails(
                                    perName, sd, ed);
                            loadSearchTableData(list);
                        }
                        TitledBorder border = (TitledBorder) getJPanel4()
                                .getBorder();
                        border.setTitle(item + "的表格");
                        getJPanel4().repaint();
                    }
                }
            });
            getDateComboBox().setSelectedIndex(1);
        }
        return dateComboBox;
    }  
    private void loadSearchTableData(List<TableList> list) {
       // CTableModel model = (CTableModel) getAfterTable().getModel();
        getAfterTable().clearSelection();
        //model.setRowCount(0);
        for (TableList details : list) {
            Vector<Object> rowData = new Vector<Object>();
            rowData.add(details);
            rowData.add(details.getTableName());
            rowData.add(details.getDate());
            rowData.add(details.getRemark()); 
            //model.addRow(rowData);
        }
    }
    
    public Person getPerson() {
        if (person == null)
            person = Session.getUser();
        return person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
  @Override
   public void valueChanged(TreeSelectionEvent e) {
         TreePath path = e.getPath();
         if (path == null) {
             setPerson(Session.getUser());
         }
         Object object = path.getLastPathComponent();
         if (object instanceof DefaultMutableTreeNode) {
             DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
             Object value = node.getUserObject();
             if (value instanceof Person) {
                 Person per = (Person) value;
                 setPerson(per);
             } else if (Session.getUser() != null) {
                 setPerson(Session.getUser());
             }
         }
         //loadDayProjectTable();
         getDateComboBox().setSelectedIndex(0);
         getDateComboBox().setSelectedIndex(1);
     }
  
}
