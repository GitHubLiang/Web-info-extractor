package com.action;

import com.db.MySQLJdbc;
import java.io.IOException;
import java.sql.Connection;
//import java.sql.SQLException;
import java.sql.Statement;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class filterAction {
	private static Connection conn;
	private static Statement st;
	private static String username;
	private static String password;
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static String score() throws IOException {
		//File input = new File("C:/Users/yang/Desktop/score.html");
		//Document doc = Jsoup.parse(input , "GBK"); 
		String myurl = "http://1.lab2lib.sinaapp.com/score.jsp";
		System.out.println("score2");
		Document doc = (Document) Jsoup.connect(myurl).get();
		System.out.println("score1");
		boolean flag = false;
		String[] d = new String[10];
		int i;
		for(Element ele : doc.select("table").select("tr")){
			System.out.println("score");
			if(ele.select("td").get(0).text().charAt(0)=='2'){
				flag = true;
			} else flag = false;
			i = 0;
			for(Element ele2 : ele.select("td")){
				if(!ele2.select("td").toString().equals("")){
					//String url = ele.select("td").get(0).select("a").attr("href");
					String text = ele2.select("td").get(0).text();
					//String count = ele.select("td").get(0).select("span").text();
					//if(url!=""&&getNum(coount)>=500){//500完全可以改成静态全局变量以及手动输入的方式
					//	System.out.println(text+"——"+count+"——"+url);
					//}
					d[i++] = text;
					System.out.print(text+"\t");
				}
			}
			if(flag==true){
				try {
					conn = MySQLJdbc.getConnection();
					String sql = 
			"insert into reportCard(学期,课程号,课程名,授课教师,类别,学分,学时,成绩,成绩类别,备注) values(\""+d[0]+"\",\""
			+d[1]+"\",\""+d[2]+"\",\""+d[3]+"\",\""+d[4]+"\",\""+d[5]+"\",\""+d[6]+"\",\""+d[7]
			+"\",\""+d[8]+"\",\""+d[9]+"\")";
					st = conn.createStatement();
					int a = st.executeUpdate(sql);
					System.out.println(sql+" "+a);
					if (a == 0) System.out.println("insert failed");
				} catch (Exception e) {
					e.printStackTrace();
					return "erro";
				}
			}
			System.out.println("");
		}
		return "success";
	}	
}