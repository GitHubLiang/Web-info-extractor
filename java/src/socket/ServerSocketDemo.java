/**
 * 服务器socket：服务端程序：serverSocket当收到客户端的请求时accept（）返回一个socket；
 * 读取客户端程序的输入信息并将其输出，实现与客户端的通信
 */
package socket;
import java.net.*;
import java.io.*;

public class ServerSocketDemo {
	public static void main(String args[]) {
		try {
			ServerSocket serverSocket=new ServerSocket(4331);
			Socket clientSocket=null;
			String str;
			DataInputStream in=null;
			DataOutputStream out=null;
			
			clientSocket = serverSocket.accept();//接受客户连接请求
			in=new DataInputStream(clientSocket.getInputStream());//获取套接字的输入流
			out=new DataOutputStream(clientSocket.getOutputStream());//获取套接字的输出流
			while(true){
				str=in.readUTF();
				out.writeUTF(str);;
				System.out.println("服务器收到："+str);
				Thread.sleep(1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
