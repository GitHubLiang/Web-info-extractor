package webParser;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mysql.jdbc.PreparedStatement;

public class PersonalData extends SuperDao {
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
	public static String score(String str) throws IOException {
		File input = new File(str);
		System.out.println(str);
		Document doc = Jsoup.parse(input , "GBK");
		System.out.println(doc.body().toString()); 
		boolean flag = false;
		String[] d = new String[10];
		int i;
		try {
			conn = MySQLJdbc.getConnection(); 
			final String sql1 = "truncate table reportCard;"; 
			st = conn.createStatement();
			st.executeUpdate(sql1);
			}catch(Exception e){
				e.printStackTrace();
			}
		for(Element ele : doc.select("table").select("tr")){
			if(ele.select("td").get(0).text().charAt(0)=='2'){
				flag = true;
			} else flag = false;
			i = 0;
			for(Element ele2 : ele.select("td")){
				if(!ele2.select("td").toString().equals("")){ 
					String text = ele2.select("td").get(0).text();
					d[i++] = text; 
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
		}
		return "success";
	}
//	public static void main(String args[]) {	
//		try {
//			new PersonalData().score();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}