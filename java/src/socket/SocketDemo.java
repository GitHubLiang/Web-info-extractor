/**
 * socket,客户端程序
 * 通过IP和端口连接到服务器程序上，实现和服务器的通信
 */
package socket;
import java.net.*;
import java.io.*;

public class SocketDemo {
	public static void main(String args[]) {
		try {
			Socket clientSocket=new Socket("172.17.132.170",4331);
			String str=null;
			DataInputStream in=null;
			DataOutputStream out=null;
			
			in=new DataInputStream(clientSocket.getInputStream());
			out=new DataOutputStream(clientSocket.getOutputStream());
			out.writeUTF("我是客户及：");
			int i=0;
			while(true) {
				str=in.readUTF();
				out.writeUTF(i++ +"");
				System.out.println("客户端收到："+str);
				Thread.sleep(1000);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}