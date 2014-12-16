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
	public JSplitPane vSplitPane;	//������ֱ�ָ�����
	public JTextArea jta;
	public JScrollPane jspane;	//����һ�������ı���Ĺ������������ʾ��ȡ������Ϣ
	public  JSplitPane vSplitPane1;
	public   JTextField aTextField;
	public  JTextField bTextField;
	public JLabel webLabel;
	public JTextField fileAddr;
	public JButton addRowButton = new JButton("�����"); // ������ť
	public  JButton addColumnButton = new JButton("�����");
    public JButton updButton = new JButton("�޸�"); // ������ť
    public JButton delButton = new JButton("ɾ����"); // ������ť
    public JButton delColumnButton = new JButton("ɾ����"); // ������ť 
    public JButton saveButton = new JButton("����");	//�����������ݿ�İ�ť
    public Person person = new Person();
    
	Vector dataVector = new Vector(); 
	Vector columnVector = new Vector();
	

    public ExtracTablePanel() {
        this.setName("ץȡ���");
        initialize();// ��ʼ���������
    }
    
	public void initialize() {
		this.setSize(800,715);
		panel1 = new JPanel();
		jta = new JTextArea(100,200);
		jspane = new JScrollPane(jta);  
		vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vSplitPane.setDividerLocation(100);	//���ô�ֱ�ָ����ķָ������ϱ�Ϊ100����
		vSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vSplitPane1.setDividerLocation(550);
		vSplitPane1.setTopComponent(jspane);
		panel1.add(new JLabel("A:"));
		aTextField = new JTextField("A4",5);
		panel1.add(aTextField);
		panel1.add(new JLabel("B:"));
		bTextField = new JTextField("B4",5);
		panel1.add(bTextField);
		vSplitPane.setBottomComponent(vSplitPane1); //�����������ӵ��ָ������²�
		vSplitPane1.setBottomComponent(panel1);
		this.add(vSplitPane);	//��ӷָ����
		  
		//���������ļ���ַ�������
		fileAddr = new JTextField();
		fileAddr.setBounds(300,25,400,30);
		//��ʼѡ���ļ��İ�ť
		final JButton jButton= new JButton();
		jButton.setText("���");
		jButton.setBounds(720,25,60,30);
		jButton.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				JFileChooser fileChoose = new JFileChooser();
				//�����ļ�������
				FileFilter filter = new FileNameExtensionFilter("html��ҳ(*.html;*.htm)","html","htm");
				fileChoose.setFileFilter(filter);
				//fileChoose.showOpenDialog(getContentPane());
				String str = fileChoose.getSelectedFile().getAbsolutePath();
				System.out.println(str);
				fileAddr.setText(str);
			}
		}); 
		//ץȡ�ļ��İ�ť���Դ˰�ť������ȡ���ݿ�Ĺ���
		final JButton grab = new JButton();
		grab.setText("ץȡ");
		grab.setBounds(810,25,60,30);
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);  
		jpanel.add(fileAddr);
		jpanel.add(jButton);
		jpanel.add(grab);
		vSplitPane.setTopComponent(jpanel);

	    panel1.add(addRowButton); // �Ѱ�ť��ӵ������
	    panel1.add(addColumnButton);
	    panel1.add(updButton); // �Ѱ�ť��ӵ������
	    panel1.add(delButton); // �Ѱ�ť��ӵ������ 
	    panel1.add(delColumnButton); // �Ѱ�ť��ӵ������ 
	    panel1.add(saveButton);
	    
		grab.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				/*to-do ���ö�ȡ���ݳ���*/ 
				String str = fileAddr.getText();  
				ReadDao read = new ReadDao(str);  //��ȡ���ݿ��еı�
				 
		        LinkedList<TableHead> tableHeads = read.getTable();
		        System.out.println(tableHeads.size());//�����м�����
		        String[][] str1 = new String[tableHeads.size()][];
				String tableName[] = new String[tableHeads.size()]; 
		        int j = 0;
		        for(TableHead t : tableHeads) {
		        	System.out.println(t.tableName);//�������
		        	System.out.println(t.columnLen);//��ͷ 
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
				table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );	//���ö��е�ѡ��Ϊ��ѡģʽ
				table.setColumnSelectionAllowed(true);	//������ѡ��Ϊ����ѡ��
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
			            // �������������
			            String[] rowValues = { aTextField.getText(),
			                    bTextField.getText() }; 
			            tableModel.addRow(rowValues); // ����ģ�������һ�� 
			            int rowCount = table.getRowCount() + 1; // �ѱ�����������1
			            int columnCount=table.getColumnCount();
			            // �ı�������ֵΪA������������1��ֵ
			            aTextField.setText("A" + rowCount);
			            // �ı�������ֵΪB���������������ֵ
			            bTextField.setText("B" +columnCount);
			        }
			    });
			    addColumnButton.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e) {
			    	String name = new String();
			    	name = JOptionPane.showInputDialog(null,"�����������еı�ʶ��");  
			    	tableModel.addColumn(name);//���һ��
			    	 int rowCount = table.getRowCount(); // �ѱ�����������1
			         int columnCount=table.getColumnCount()+1;
			            // �ı�������ֵΪA������������1��ֵ
			            aTextField.setText("A" + rowCount);
			            // �ı�������ֵΪB���������������ֵ
			            bTextField.setText("B" +columnCount);
			    	}
			    }); 
			    updButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            int selectedRow = table.getSelectedRow(); // ��ñ�ѡ���е�����
			            if (selectedRow != -1) { // �ж��Ƿ���ڱ�ѡ����
			                // �޸ı��ģ�͵��е�ָ��ֵ
			                tableModel.setValueAt(aTextField.getText(), selectedRow, 0);
			                tableModel.setValueAt(bTextField.getText(), selectedRow, 1);
			            }
			        }
			    }); 
			    delButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            int selectedRow = table.getSelectedRow(); // ��ñ�ѡ���е�����
			            if (selectedRow != -1){ // �ж��Ƿ���ڱ�ѡ����
			            	int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����","ȷ�϶Ի���",JOptionPane.YES_NO_CANCEL_OPTION);
			            	if(n == JOptionPane.YES_OPTION)
			            		tableModel.removeRow(selectedRow); // �ӱ��ģ�͵���ɾ��ָ����
			            }
			        }
			    });
			    delColumnButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	int columnCount = tableModel.getColumnCount();
			            int selectedColumn = table.getSelectedColumn(); // ��ñ�ѡ���е�����
			            System.out.println(selectedColumn);
			            if (selectedColumn != -1){ // �ж��Ƿ���ڱ�ѡ����
			            	int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����������","ȷ�϶Ի���",JOptionPane.YES_NO_CANCEL_OPTION);
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
			    		
			    		int n = JOptionPane.showConfirmDialog(null, "���浱ǰ��","ȷ�϶Ի���",JOptionPane.YES_NO_CANCEL_OPTION);
		            	if(n == JOptionPane.YES_OPTION){ 
		            		tableName = JOptionPane.showInputDialog(null,"��������������"); 
		            		columnCount = tableModel.getColumnCount();
		            		rowCount = tableModel.getRowCount();
		            		for(int i = 0;i < columnCount;i++) {	//��ȡ���е�����
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
		            			JOptionPane.showMessageDialog(null,"����ɹ�");
		            		}
		            		else{
		            			JOptionPane.showMessageDialog(null,"����ʧ��");
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
