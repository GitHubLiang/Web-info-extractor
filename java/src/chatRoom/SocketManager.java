/**
 * 创建socketmanager类，使实现对于多个socket的添加、删除、返回socket个数、输出选中socket输出流信息的功能
 */
package chatRoom;
import java.util.*;
import java.net.*;
import java.io.*;

public class SocketManager extends ArrayList {
	//synchronized java关键字，表示同步代码块，当一个线程访问同步代码块时，其余线程不能访问同步代码块，但是可以访问
	//非同步代码块，保证同一时刻最多只有一个线程访问该代码块
	synchronized void add(Socket socket) {	//添加套接字
		super.add(socket);
	}
	synchronized void delete(Socket socket) {	//删除套接字
		super.remove(socket);
	}
	synchronized void sendClientCount() {	//输出当前聊天人数
		String info="当前聊天人数"+size();
		System.out.println(info);
		writeAll(info);
	}
	synchronized void writeAll(String str) {	//使用套接字输出流，输出信息
		PrintWriter write=null;
		Socket socket;
		for(int i=0;i<size();i++) {
			socket=(Socket)get(i);	//	获取套接字
			try {
				write=new PrintWriter(socket.getOutputStream(),true);	//	创建输出流
				if(write != null)
					write.println(str);	//通过输出流输出信息
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
