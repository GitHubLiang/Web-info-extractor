/**
 * UDP编程——广播数据报，实现主机不断地向客户机传送信息，客户端不断地接收数据
 */
package udp;

import java.net.*; 

public class Weather extends Thread {
	String weather="天气预报：明天起气温下降，请注意保暖！";
	int port=9999;
	InetAddress iaddress=null;
	MulticastSocket socket=null;	//声明多点广播套接字
	Weather() {	//显示构造
		try {
				iaddress=InetAddress.getByName("224.255.10.0");	//实例化inetaddress对象
				socket=new MulticastSocket(port);	//
				socket.setTimeToLive(1);	//指定发送范围是本地网络
				socket.joinGroup(iaddress);	//加入广播组
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			DatagramPacket packet=null;	//数据报包，负责UDP的传送
			byte data[]=weather.getBytes();	//声明字节数组
			packet=new DatagramPacket(data,data.length,iaddress,port);	//指定数据包内存的空间和大小，和数据包的目标地址与端口
			
			System.out.println(new String(data));
			try {
				socket.send(packet);
				sleep(3000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
		Weather w=new Weather();
		w.start();
	}
}
