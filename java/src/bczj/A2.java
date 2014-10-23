/**
 * 对于字节流InputStream和OutputStream的测试
 */
package bczj;
import java.io.*;

public class A2 {
	public static void main(String args[]) {
		InputStream in = System.in;
		try{
			byte[] cs=new byte[1204];
			System.out.println("使用byte");
			while(in.read(cs)!=-1){//read()函数是要字节为参数的
				String str=new String(cs).trim();
				System.out.println(str);
			}
			in.close();
			
			OutputStream out=System.out;
			cs="现在使用outputStream\n".getBytes();
			out.write(cs);
			String str="限制使用字符串规划";
			cs=str.getBytes();
			out.write(cs); 
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
