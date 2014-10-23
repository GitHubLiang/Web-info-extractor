/*
 * 服务端程序，创建服务端套接字，接收客户端的连接请求，启动线程，将连到的套接字添加到队列中
 */
package chatRoom;
import java.net.*;
import java.io.*;

public class ServerProcess {
	private SocketManager socketMan=new SocketManager();
	void getServer() {
		try {
			ServerSocket serverSocket=new ServerSocket(9999);	//创建服务器套接字
			System.out.println("服务器套接字已连接~");
			while(true) {
				Socket socket=serverSocket.accept();	//等待连接
				new write_Thread(socket).start();	//启动线程
				socketMan.add(socket);	//	添加套接字
				socketMan.sendClientCount();	//将当前套接字数输出；
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	class write_Thread extends Thread {
		Socket socket=null;
		private BufferedReader reader;
		private PrintWriter write;
		public write_Thread(Socket socket) {	//利用socket参数构造新的线程
			this.socket=socket;
		}
		public void run() {
			try {
				reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				write=new PrintWriter(socket.getOutputStream(),true);
				String msg;
				while((msg=reader.readLine())!=null) {
					System.out.println(msg);
					socketMan.writeAll(msg);	//将客户端输出信息写入流中
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
		ServerProcess server=new ServerProcess();
		server.getServer();
	}
}
