package test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
class Extractor {
	private File file;
	private Document doc;
	private String tableName;
	private int tableCount, n, colCount;
	private Connection conn;
	private Statement stmt;
	private String sql;
	
	public Extractor(String url) throws IOException, SQLException {
		file = new File(url);
		doc = Jsoup.parse(file, "GBK");
		tableName = url.substring(url.lastIndexOf('\\')+1,url.lastIndexOf('.'));
		tableCount = colCount = 0;
		conn = Dao.getConnection();
		stmt = conn.createStatement();
	}
	private void create(String[] cols) throws SQLException {
		tableName = tableName.replaceAll(" ", "_");
		sql = "create table "+tableName+(++tableCount)+"(";
		for(int i=0;i<n;i++) {
			if((cols[i].length()==1&&(int)cols[i].charAt(0)==160)||cols[i].length()==0) cols[i] = "null"+ (++colCount);
			cols[i] = cols[i].replaceAll(" ", "_");
			sql += cols[i]+" varchar(255)";
			//if(tableCount==6&&i==1) put("length "+cols[i].length()+" ascii "+(int)cols[i].charAt(0));
			if(i<n-1) {
				sql += ", ";
			}
		}
		sql += ")";
		System.out.println(sql);
		stmt.execute(sql);
	}
	private void insert(String[] cols) throws SQLException {
		sql = "insert into "+tableName+tableCount+" values(";
		for(int i=0;i<min(n,cols.length);i++) {
			cols[i] = cols[i].replaceAll("'", "''");
			sql += "'" + cols[i] + "'";
			if(i<n-1) {
				sql += ", ";
			}
		}
		sql += ")";
		System.out.println(sql);
		stmt.execute(sql);
	}
	private int min(int a, int b) {
		if(a<b) return a;
		return b;
	}
	private int max(int a, int b) {
		if(a<b) return b;
		return a;
	}
	private void put(String s) {
		System.out.println(s);
	}
	private void put(int i) {
		System.out.println(i);
	}
	public void extract() throws SQLException {
		int row = -1;
		int[] col = new int[3];
		String[][] tmp = new String[3][50];
		for(Element ele : doc.select("table")) {
			row = -1;
			col[0] = col[1] = col[2] = 0;
			put("Find a table!");
			boolean detectedTable = false;
			for(Element ele2 : ele.select("tr")) {
				if(!detectedTable){
					row++;
					if(row > 2) break;
					put("row "+row);
					int j = 0;
					for(Element e : ele2.select("th")) {
						put("text "+e.select("th").text());
						col[row]++;
						if(j > 49) break;
						tmp[row][j++] = e.select("th").text();
					}
					if(j > 49) break;
					for(Element e : ele2.select("td")) {
						put("text "+e.select("td").text());
						col[row]++;
						if(j > 49) break;
						tmp[row][j++] = e.select("td").text();
					}
					if(j > 49) break;
					if(row == 2) {
						if(min(col[1],col[2])<col[0]-2) break;
						if(max(col[1],col[2])>col[0]) break;
						if(min(min(col[0],col[1]),col[2])<2) break;
						put("col[0] "+col[0]);
						put("col[1] "+col[1]);
						put("col[2] "+col[2]);
						n = col[0];
						create(tmp[0]);
						insert(tmp[1]);
						insert(tmp[2]);
						detectedTable = true;
					}
				} else {
					int j = 0;
					for(Element e : ele2.select("td")) {
						tmp[0][j++] = e.select("td").text();
					}
					if(j!=col[2]) continue;
					insert(tmp[0]);
				}
			}
		}
	}
}
public class Main {	
	
	public static void main(String[] args) throws IOException, SQLException {
		//Extractor e = new Extractor("F:\\SE\\labs\\jwc.htm");//ok
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\rank985.html");
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\大一年度项目查询.htm");//fine
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\上学期评教结果查询.htm");//surprise!
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\管理员.html");
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\HTML 字符实体.htm");//get~
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\申请处理结果查询.htm");
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\托福.html");ok
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\ACM.html");
		Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\HTML 参考手册.htm");
        e.extract();
	}
}
