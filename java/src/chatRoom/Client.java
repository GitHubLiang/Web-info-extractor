/*
 * 聊天室客户端程序，构造了窗体的布局，创建客户端socket
 * 通过输出流发送数据，输入流读取服务器端发送的数据，将其显示在文本域中
 */
package chatRoom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements Runnable {
	private JPanel jpanel=new JPanel();
	private JLabel nameLable=new JLabel("姓名：");	//标签对象
	private JTextField nameField=new JTextField();	//文本框对象
	private JTextArea msgArea=new JTextArea();	//文本域对象
	private JTextField sendField=new JTextField();
	private JScrollPane jScrollPanel=new javax.swing.JScrollPane();	//
	private BufferedReader reader;
	private PrintWriter write;
	private Socket socket;
	public Client(String title) {
		super(title);	
		this.setSize(360,340);	//定义窗口呢大小
		this.add(jpanel);
		jpanel.setLayout(null);	//窗体布局
		msgArea.setEditable(false);	//不可编译
		jpanel.add(nameLable);
		nameLable.setBounds(10,10,60,20);
		jpanel.add(nameField);
		nameField.setBounds(60,10,270,21);
		jpanel.add(sendField);
		sendField.setBounds(10,270,320,21);
		msgArea.setColumns(20);
		msgArea.setRows(5);
		jScrollPanel.setViewportView(msgArea);
		jpanel.add(jScrollPanel);
		jScrollPanel.setBounds(10,40,320,220);
		ActionListener aListener=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				write.println(nameField.getText()+":"+sendField.getText());
				sendField.setText("");
			}
		};
		sendField.addActionListener(aListener);
	}
	public void run() {
		while(true) {
			try {
				msgArea.append(reader.readLine()+"\n");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	void getSocket() {
		msgArea.append("尝试与服务器连接");
		try {
			socket=new Socket("172.17.132.170",9999);
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			write=new PrintWriter(socket.getOutputStream(),true);
			new Thread(this).start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		Client client=new Client("ChatRoom!");
		client.setVisible(true);
		client.getSocket();
	}
}
