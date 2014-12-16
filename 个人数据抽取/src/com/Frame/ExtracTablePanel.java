package com.Frame; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.dao.*;  
import com.entity.Person;
import com.login.Session;

public class ExtracTablePanel extends JPanel implements Serializable,
TreeSelectionListener{
	
	public JPanel panel1;
	public JSplitPane vSplitPane;	//创建垂直分割的面板
	public JTextArea jta;
	public JScrollPane jspane;	//创建一个带有文本域的滚动面板用于显示提取到的信息
	public  JSplitPane vSplitPane1;
	public   JTextField aTextField;
	public  JTextField bTextField;
	public JLabel webLabel;
	public JTextField fileAddr;
	public JButton addRowButton = new JButton("添加行"); // 创建按钮
	public  JButton addColumnButton = new JButton("添加列");
    public JButton updButton = new JButton("修改"); // 创建按钮
    public JButton delButton = new JButton("删除行"); // 创建按钮
    public JButton delColumnButton = new JButton("删除列"); // 创建按钮 
    public JButton saveButton = new JButton("保存");	//创建保存数据库的按钮
    public Person person = new Person();
    
	Vector dataVector = new Vector(); 
	Vector columnVector = new Vector();
	

    public ExtracTablePanel() {
        this.setName("抓取表格");
        initialize();// 初始化程序界面
    }
    
	public void initialize() {
		this.setSize(800,715);
		panel1 = new JPanel();
		jta = new JTextArea(100,200);
		jspane = new JScrollPane(jta);  
		vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vSplitPane.setDividerLocation(100);	//设置垂直分割面板的分割条距上边为100像素
		vSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vSplitPane1.setDividerLocation(550);
		vSplitPane1.setTopComponent(jspane);
		panel1.add(new JLabel("A:"));
		aTextField = new JTextField("A4",5);
		panel1.add(aTextField);
		panel1.add(new JLabel("B:"));
		bTextField = new JTextField("B4",5);
		panel1.add(bTextField);
		vSplitPane.setBottomComponent(vSplitPane1); //将滚动面板添加到分割面板的下部
		vSplitPane1.setBottomComponent(panel1);
		this.add(vSplitPane);	//添加分割面板
		  
		//创建查找文件地址的浏览框
		fileAddr = new JTextField();
		fileAddr.setBounds(300,25,400,30);
		//开始选择文件的按钮
		final JButton jButton= new JButton();
		jButton.setText("浏览");
		jButton.setBounds(720,25,60,30);
		jButton.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				JFileChooser fileChoose = new JFileChooser();
				//创建文件过滤器
				FileFilter filter = new FileNameExtensionFilter("html网页(*.html;*.htm)","html","htm");
				fileChoose.setFileFilter(filter);
				//fileChoose.showOpenDialog(getContentPane());
				String str = fileChoose.getSelectedFile().getAbsolutePath();
				System.out.println(str);
				fileAddr.setText(str);
			}
		}); 
		//抓取文件的按钮，以此按钮触发读取数据库的功能
		final JButton grab = new JButton();
		grab.setText("抓取");
		grab.setBounds(810,25,60,30);
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);  
		jpanel.add(fileAddr);
		jpanel.add(jButton);
		jpanel.add(grab);
		vSplitPane.setTopComponent(jpanel);

	    panel1.add(addRowButton); // 把按钮添加到面板上
	    panel1.add(addColumnButton);
	    panel1.add(updButton); // 把按钮添加到面板上
	    panel1.add(delButton); // 把按钮添加到面板上 
	    panel1.add(delColumnButton); // 把按钮添加到面板上 
	    panel1.add(saveButton);
	    
		grab.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				/*to-do 调用读取数据程序*/ 
				String str = fileAddr.getText();  
				ReadDao read = new ReadDao(str);  //读取数据库中的表
				 
		        LinkedList<TableHead> tableHeads = read.getTable();
		        System.out.println(tableHeads.size());//返回有几个表
		        String[][] str1 = new String[tableHeads.size()][];
				String tableName[] = new String[tableHeads.size()]; 
		        int j = 0;
		        for(TableHead t : tableHeads) {
		        	System.out.println(t.tableName);//表的名字
		        	System.out.println(t.columnLen);//表头 
		        	tableName[j] = t.tableName;
		        	for(int i=0;i<t.columnLen;i++) {
		        		System.out.println(t.columns[i]);
		        		str1[j][i] = t.columns[i];
		        	}
		        	j++;
		        }
		        for(int k = 0;k < j;k++) {
		        dataVector = read.getAllscore(str1[k],tableName[k]);
				columnVector = new Vector(); 
				 for(int i = 0;i < j;i++){
					 columnVector.add(str1[k][i]);
				 } 
				} 
				final DefaultTableModel tableModel = new DefaultTableModel(dataVector,columnVector);
				final JPanel panel = new JPanel();
				final JTable table = new JTable(tableModel); 
				table.setRowSorter(new TableRowSorter(tableModel));
				table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );	//设置对行的选择为多选模式
				table.setColumnSelectionAllowed(true);	//设置列选择为允许选择
				table.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						int selectedRow = table.getSelectedRow();
						int selectedCloumn = table.getSelectedColumn();
						Object oa = tableModel.getValueAt(selectedRow,0);
						Object ob = tableModel.getValueAt(selectedCloumn,0);
						aTextField.setText(oa.toString());
						bTextField.setText(ob.toString());
					}
				}); 
			    addRowButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            // 创建表格行数组
			            String[] rowValues = { aTextField.getText(),
			                    bTextField.getText() }; 
			            tableModel.addRow(rowValues); // 向表格模型中添加一行 
			            int rowCount = table.getRowCount() + 1; // 把表格的总行数加1
			            int columnCount=table.getColumnCount();
			            // 文本框设置值为A连接总行数加1的值
			            aTextField.setText("A" + rowCount);
			            // 文本框设置值为B连接总列数不变的值
			            bTextField.setText("B" +columnCount);
			        }
			    });
			    addColumnButton.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e) {
			    	String name = new String();
			    	name = JOptionPane.showInputDialog(null,"请输入增加列的标识符");  
			    	tableModel.addColumn(name);//添加一列
			    	 int rowCount = table.getRowCount(); // 把表格的总行数加1
			         int columnCount=table.getColumnCount()+1;
			            // 文本框设置值为A连接总行数加1的值
			            aTextField.setText("A" + rowCount);
			            // 文本框设置值为B连接总列数不变的值
			            bTextField.setText("B" +columnCount);
			    	}
			    }); 
			    updButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            int selectedRow = table.getSelectedRow(); // 获得被选中行的索引
			            if (selectedRow != -1) { // 判断是否存在被选中行
			                // 修改表格模型当中的指定值
			                tableModel.setValueAt(aTextField.getText(), selectedRow, 0);
			                tableModel.setValueAt(bTextField.getText(), selectedRow, 1);
			            }
			        }
			    }); 
			    delButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            int selectedRow = table.getSelectedRow(); // 获得被选中行的索引
			            if (selectedRow != -1){ // 判断是否存在被选中行
			            	int n = JOptionPane.showConfirmDialog(null, "确认删除吗？","确认对话框",JOptionPane.YES_NO_CANCEL_OPTION);
			            	if(n == JOptionPane.YES_OPTION)
			            		tableModel.removeRow(selectedRow); // 从表格模型当中删除指定行
			            }
			        }
			    });
			    delColumnButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	int columnCount = tableModel.getColumnCount();
			            int selectedColumn = table.getSelectedColumn(); // 获得被选中列的索引
			            System.out.println(selectedColumn);
			            if (selectedColumn != -1){ // 判断是否存在被选中列
			            	int n = JOptionPane.showConfirmDialog(null, "确认删除所在列吗？","确认对话框",JOptionPane.YES_NO_CANCEL_OPTION);
			            	if(n == JOptionPane.YES_OPTION){
			            	TableColumnModel columnModel = table.getColumnModel();
			                TableColumn tableColumn = columnModel.getColumn(selectedColumn);
			                columnModel.removeColumn(tableColumn);
			                tableModel.setColumnCount(columnCount - 1);
			            }
			            }
			        }
			    });
			    saveButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		String column = new String();
			    		Vector <String>columnName = new Vector<String>();
			    		Vector <Vector<String>>dataVector = new Vector<Vector<String>>();
			    		Vector<String> rowVector;
			    		String tableName = new String();
			    		int columnCount = 0;
			    		int rowCount = 0;
			    		int info = 0;
			    		
			    		int n = JOptionPane.showConfirmDialog(null, "保存当前表？","确认对话框",JOptionPane.YES_NO_CANCEL_OPTION);
		            	if(n == JOptionPane.YES_OPTION){ 
		            		tableName = JOptionPane.showInputDialog(null,"请输入新增表名"); 
		            		columnCount = tableModel.getColumnCount();
		            		rowCount = tableModel.getRowCount();
		            		for(int i = 0;i < columnCount;i++) {	//读取表中的列名
		            			column = tableModel.getColumnName(i);
		            			columnName.add(column); 
		            		} 
		            		for(int i = 0;i < rowCount;i++) { 
		            			rowVector = new Vector<String>();
		            			for(int j = 0;j < columnCount;j++) {
		            				rowVector.add((String)tableModel.getValueAt(i,j)); 
		            			}
		            			dataVector.add(rowVector);
		            		}
		            		SaveTable save = new SaveTable();
		            		info = save.saveTable(tableName,columnName,dataVector);
		            		if(info == 1) {
		            			JOptionPane.showMessageDialog(null,"保存成功");
		            		}
		            		else{
		            			JOptionPane.showMessageDialog(null,"保存失败");
		            		}
		            	}
			     }
			    });
				jspane.add(table);
				jspane.setViewportView(table); 
			}   
		});
		 
	} 
	public void setPerson(Person Person) {
        this.person = Person;
        if (person == null)
            return; 
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
            } else {
                setPerson(Session.getUser());
            }
        }
    }
}
