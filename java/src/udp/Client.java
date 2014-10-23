package udp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.net.*;

public class Client extends JFrame implements Runnable,ActionListener {
	int port;
	MulticastSocket socket=null;
	InetAddress group=null;
	JButton ince=new JButton("开始接收");
	JButton stop=new JButton("停止接收");
	JTextArea inceAr=new JTextArea(10,10);
	JTextArea inced=new JTextArea(10,10);
	Thread thread;
	boolean b=false;
	public Client() {
		super("广播数据报");
		thread=new Thread(this);
		ince.addActionListener(this);	//绑定按钮ince的单击事件
		stop.addActionListener(this);	//绑定按钮stop单击事件
		inceAr.setForeground(Color.blue);	//指定文本域中文字的颜色
		JPanel north=new JPanel();	//创建JPanel对象
		north.add(ince);	//将按钮添加到面板上
		north.add(stop);
		add(north,BorderLayout.NORTH);
		JPanel center=new JPanel();
		center.setLayout(new GridLayout(1,2));	//设置面板布局
		center.add(inceAr);
		center.add(inced);
		add(center,BorderLayout.CENTER);
		validate();
		port=9999;
		try {
			group=InetAddress.getByName("224.255.10.0");	//多点播送地址？。。。
			socket=new MulticastSocket(port);	//绑定多点广播套接字
			socket.joinGroup(group);	//加入广播组
		}catch(Exception e){
			e.printStackTrace();
		}
		setBounds(100,50,360,380);
		setVisible(true);
	}
	public void run() {
		while(true) {
			byte data[]=new byte[1024];
			DatagramPacket packet=null;
			packet=new DatagramPacket(data,data.length,group,port);
			try {
				socket.receive(packet);
				String message=new String(packet.getData(),0,packet.getLength());	//获取数据报中的内容
				inceAr.setText("正在接受的内容：\n"+message);
				inced.append(message+"\n");
			}catch(Exception e){
				e.printStackTrace();
			}
			if(b==true){
				break;
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ince) {
			ince.setBackground(Color.pink);	//设置按钮的颜色
			stop.setBackground(Color.gray);
			if(!(thread.isAlive())) {
				thread=new Thread(this);
			}
			thread.start();
			b=false;
		}
		if(e.getSource()==stop) {
			ince.setBackground(Color.yellow);
			stop.setBackground(Color.red);
			b=true;
		}
	}
	public static void main(String args[]) {
		Client rec=new Client();
		rec.setSize(460,200);
	}
}
