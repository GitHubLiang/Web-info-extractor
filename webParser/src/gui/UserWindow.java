package gui;

import webParser.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class UserWindow extends JFrame{
	public UserWindow() {
		setTitle("***提取表格信息@粉丝煲***");
		setBounds(100,0,1000,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);	//创建垂直分割的面板
		final JTextArea jta = new JTextArea(100,200);
		final JScrollPane jspane = new JScrollPane(jta);	//创建一个带有文本域的滚动面板用于显示提取到的信息
		vSplitPane.setDividerLocation(100);	//设置垂直分割面板的分割条距上边为100像素
		final JSplitPane vSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vSplitPane1.setDividerLocation(550);
		vSplitPane1.setTopComponent(jspane);
		final JPanel panel1 = new JPanel();
		panel1.add(new JLabel("A:"));
		final JTextField aTextField = new JTextField("A4",5);
		panel1.add(aTextField);
		panel1.add(new JLabel("B:"));
		final JTextField bTextField = new JTextField("B4",5);
		panel1.add(bTextField);
		vSplitPane.setBottomComponent(vSplitPane1); //将滚动面板添加到分割面板的下部
		vSplitPane1.setBottomComponent(panel1);
		 
		//提示域：所抓取的网站名称
		final JLabel webLabel = new JLabel();
		webLabel.setText("请选择抓取的网站：");
		webLabel.setBounds(35,25,120,30);
		//创建用于选择提取类型的下拉列表
		String[] webName = {"淘宝","教务处","手机账单","通话记录","亚马逊"};
		final JComboBox jcomboBox = new JComboBox(webName);
		jcomboBox.setBounds(150,25,80,30);
		//创建查找文件地址的浏览框
		final JTextField fileAddr = new JTextField();
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
				fileChoose.showOpenDialog(getContentPane());
				String str = fileChoose.getSelectedFile().getAbsolutePath();
				System.out.println(str);
				fileAddr.setText(str);
			}
		});
		//抓取文件的按钮，以此按钮触发读取数据库的功能
		final JButton grab = new JButton();
		final JPanel panel = new JPanel();
		grab.setText("抓取");
		grab.setBounds(810,25,60,30);
		grab.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				/*to-do 调用读取数据程序*/
				String str1 = jcomboBox.getSelectedItem().toString();
				String str2 = fileAddr.getText();
				if(str1 == "教务处"){
				ReadDao read = new ReadDao();  
				Vector dataVector = read.getAllscore(str2); 
				Vector columnVector = new Vector();
				String str[] = {"学期","课程号","课程名","授课教师","类别","学分","学时","成绩","成绩类别","备注"};
				 for(int i = 0;i < 9;i++){
					 columnVector.add(str[i]);
				 }
				final DefaultTableModel tableModel = new DefaultTableModel(dataVector,columnVector);
				final JTable table = new JTable(dataVector,columnVector); 
				//table.setRowSorter(new TableRowSorter(tableModel));
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						int selectedRow = table.getSelectedRow();
						Object oa = tableModel.getValueAt(selectedRow,0);
						Object ob = tableModel.getValueAt(selectedRow,1);
						aTextField.setText(oa.toString());
						bTextField.setText(ob.toString());
					}
				});
				final JButton addButton = new JButton("添加"); // 创建按钮
			    addButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            // 创建表格行数组
			            String[] rowValues = { aTextField.getText(),
			                    bTextField.getText() };
			            tableModel.addRow(rowValues); // 向表格模型中添加一行
			            int rowCount = table.getRowCount() + 1; // 把表格的总行数加1
			            // 文本框设置值为A连接总行数加1的值
			            aTextField.setText("A" + rowCount);
			            // 文本框设置值为B连接总行数加1的值
			            bTextField.setText("B" + rowCount);
			        }
			    });
			    panel1.add(addButton); // 把按钮添加到面板上
			    final JButton updButton = new JButton("修改"); // 创建按钮
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
			    panel1.add(updButton); // 把按钮添加到面板上
			    final JButton delButton = new JButton("删除"); // 创建按钮
			    delButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            int selectedRow = table.getSelectedRow(); // 获得被选中行的索引
			            if (selectedRow != -1) // 判断是否存在被选中行
			                tableModel.removeRow(selectedRow); // 从表格模型当中删除指定行
			        }
			    });
			    panel1.add(delButton); // 把按钮添加到面板上
				jspane.add(table);
				jspane.setViewportView(table);
				}
				else if(str1 == "淘宝"){
					Taobao tb = new Taobao();
					try { 
						String str3 = tb.taoBao(str2);
						jta.setText(str3);
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
			}
		});
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null); 
		jpanel.add(webLabel);
		jpanel.add(jcomboBox);
		jpanel.add(fileAddr);
		jpanel.add(jButton);
		jpanel.add(grab);
		vSplitPane.setTopComponent(jpanel); 
		getContentPane().add(vSplitPane);	//添加分割面板
	}
	public static void main(String args[]) {
		UserWindow userWindow = new UserWindow();
		userWindow.setVisible(true);
	}
}
