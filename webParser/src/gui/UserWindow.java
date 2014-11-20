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
		setTitle("***��ȡ�����Ϣ@��˿��***");
		setBounds(100,0,1000,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);	//������ֱ�ָ�����
		final JTextArea jta = new JTextArea(100,200);
		final JScrollPane jspane = new JScrollPane(jta);	//����һ�������ı���Ĺ������������ʾ��ȡ������Ϣ
		vSplitPane.setDividerLocation(100);	//���ô�ֱ�ָ����ķָ������ϱ�Ϊ100����
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
		vSplitPane.setBottomComponent(vSplitPane1); //�����������ӵ��ָ������²�
		vSplitPane1.setBottomComponent(panel1);
		 
		//��ʾ����ץȡ����վ����
		final JLabel webLabel = new JLabel();
		webLabel.setText("��ѡ��ץȡ����վ��");
		webLabel.setBounds(35,25,120,30);
		//��������ѡ����ȡ���͵������б�
		String[] webName = {"�Ա�","����","�ֻ��˵�","ͨ����¼","����ѷ"};
		final JComboBox jcomboBox = new JComboBox(webName);
		jcomboBox.setBounds(150,25,80,30);
		//���������ļ���ַ�������
		final JTextField fileAddr = new JTextField();
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
				fileChoose.showOpenDialog(getContentPane());
				String str = fileChoose.getSelectedFile().getAbsolutePath();
				System.out.println(str);
				fileAddr.setText(str);
			}
		});
		//ץȡ�ļ��İ�ť���Դ˰�ť������ȡ���ݿ�Ĺ���
		final JButton grab = new JButton();
		final JPanel panel = new JPanel();
		grab.setText("ץȡ");
		grab.setBounds(810,25,60,30);
		grab.addActionListener(new ActionListener(){
			public void actionPerformed(final ActionEvent e) {
				/*to-do ���ö�ȡ���ݳ���*/
				String str1 = jcomboBox.getSelectedItem().toString();
				String str2 = fileAddr.getText();
				if(str1 == "����"){
				ReadDao read = new ReadDao();  
				Vector dataVector = read.getAllscore(str2); 
				Vector columnVector = new Vector();
				String str[] = {"ѧ��","�γ̺�","�γ���","�ڿν�ʦ","���","ѧ��","ѧʱ","�ɼ�","�ɼ����","��ע"};
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
				final JButton addButton = new JButton("���"); // ������ť
			    addButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            // �������������
			            String[] rowValues = { aTextField.getText(),
			                    bTextField.getText() };
			            tableModel.addRow(rowValues); // ����ģ�������һ��
			            int rowCount = table.getRowCount() + 1; // �ѱ�����������1
			            // �ı�������ֵΪA������������1��ֵ
			            aTextField.setText("A" + rowCount);
			            // �ı�������ֵΪB������������1��ֵ
			            bTextField.setText("B" + rowCount);
			        }
			    });
			    panel1.add(addButton); // �Ѱ�ť��ӵ������
			    final JButton updButton = new JButton("�޸�"); // ������ť
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
			    panel1.add(updButton); // �Ѱ�ť��ӵ������
			    final JButton delButton = new JButton("ɾ��"); // ������ť
			    delButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            int selectedRow = table.getSelectedRow(); // ��ñ�ѡ���е�����
			            if (selectedRow != -1) // �ж��Ƿ���ڱ�ѡ����
			                tableModel.removeRow(selectedRow); // �ӱ��ģ�͵���ɾ��ָ����
			        }
			    });
			    panel1.add(delButton); // �Ѱ�ť��ӵ������
				jspane.add(table);
				jspane.setViewportView(table);
				}
				else if(str1 == "�Ա�"){
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
		getContentPane().add(vSplitPane);	//��ӷָ����
	}
	public static void main(String args[]) {
		UserWindow userWindow = new UserWindow();
		userWindow.setVisible(true);
	}
}
