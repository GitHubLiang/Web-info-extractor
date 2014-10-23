/**
 * 对inetAddress类的学习和测试：生成、主要方法
 */
package socket;

import java.net.UnknownHostException;
import java.net.*;
public class InetAddressDemo {
	public static void main(String args[]) {
		InetAddress inetAddress;
		try {
			inetAddress=InetAddress.getLocalHost();
			String localname=inetAddress.getHostName();
			String localIp=inetAddress.getHostAddress();
			System.out.println("主机名："+localname);
			System.out.println("IP地址："+localIp);
		}catch (UnknownHostException e){
			e.printStackTrace();
		}
	}
}
