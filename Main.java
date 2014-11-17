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

public class Main {
	public static void taoBao(Document doc) throws SQLException {
		String[][] tmp = new String[100][30];
		int x = 0,y = 0;
		for(Element ele : doc.select("table").select("tr")){
			boolean flag = false;
			for(Element ele2 : ele.select("td")){
				if(!ele2.select("td").toString().equals("")){
					//String url = ele.select("td").get(0).select("a").attr("href");
					String text = ele2.select("td").get(0).text();
					
					//String count = ele.select("td").get(0).select("span").text();
					if(text.equals("ȫѡ �ϲ����� �ϲ����� ����ȷ���ջ� ����ʹ�����ÿ������ֻѡ����� ��ʶ�ı��� ��һҳ") || text.equals("ȫѡ �ϲ����� �ϲ����� ����ȷ���ջ� 1 2 3 4 5 ... �� 15 ҳ�� ���� ҳ ȷ��")) {
						flag = true;
						break;
					} else if(text.equals("���� ��� ɾ��") || text.equals("���� ת�� ��� ɾ��") || text.equals("�鿴���̻ ���� ת�� ��� ɾ��")) {
						continue;
					}
					if(text.equals("Ͷ������") || text.equals("�����ۺ�") || text.equals("�����ۺ� Ͷ������") || text.equals("�����ۺ� Ͷ������ �˷���ʧЧ") || text.equals("�����Ƿ���̨ �����ۺ�") || text.equals("�����Ƿ���̨ �����ۺ� Ͷ������")) {
						continue;
					}
					if(text.equals("���׹ر� ��������") || text.equals("���׳ɹ� ��������") || text.equals("���׳ɹ� �������� ˫������") || text.equals("���׳ɹ� �������� �鿴���� ˫������")) {
						continue;
					}
					if(text.equals("׷������ �ٴι���") || text.equals("׷������") || text.equals("�ٴι���")) {
						continue;
					}
					if(text.equals("�����Ƿ���̨")) {
						continue;
					}
					if(text.equals("���շ���")) {
						continue;
					}
					if(text.length() > 0) {
						tmp[x][y++] = text;
						flag = true;
					}
				}
			}
			if(!flag) {
				x++;
				y = 0;
			}
		}
		String[][] t = new String[100][30];
		x = y = 0;
		for (int i=0; i<20; i++) {
			if (tmp[i][0] == null) continue;
			if (tmp[i][8] == null) { // one good
				for (int j=0; j<6; j++) {
					if (j == 0) {
						t[x][y++] = tmp[i][j].substring(0, 10);
						t[x][y++] = tmp[i][j].substring(10);
					}
					else
						t[x][y++] = tmp[i][j];
				}
				x++;
				y = 0;
				
			} else {// more than one goods
				t[x][y++] = tmp[i][0].substring(0, 10);
				t[x][y++] = tmp[i][0].substring(10);
				t[x][y++] = tmp[i][1];
				t[x][y++] = "|                            ������                                                                              |";
				t[x][y++] = "|      ����               |";
				t[x][y++] = "|      ����               |";
				t[x++][y++] = tmp[i][5];
				y = 0;
				t[x][y++] = "    -     ";
				t[x][y++] = "         -         ";
				t[x][y++] = "    -     ";
				t[x][y++] = tmp[i][2];
				t[x][y++] = tmp[i][3];
				t[x][y++] = tmp[i][4];
				t[x++][y++] = "    -     ";
				y = 0;
				for (int j=0; tmp[i][6+j*3]!=null; j++) {
					t[x][y++] = "    -     ";
					t[x][y++] = "         -         ";
					t[x][y++] = "    -     ";
					t[x][y++] = tmp[i][6+j*3];
					t[x][y++] = tmp[i][6+j*3+1];
					t[x][y++] = tmp[i][6+j*3+2];
					t[x++][y++] = "    -     ";
					y = 0;
				}
			}
		}
		
		Connection conn = Dao.getConnection();
		Statement stmt = conn.createStatement();
		String sql;
		
		for(int xx = 0;xx<25;xx++) {
			sql = "insert into �Ա�  values('"+t[xx][0]+"','"+t[xx][1]+"','"+t[xx][2]+"','"+t[xx][3]+"','"+t[xx][4]+"','"+t[xx][5]+"')";
			stmt.executeUpdate(sql);
		}
		
		sql = "select * from �Ա�";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			for(int yy = 1;yy<=6;yy++) {
				//if (tmp[xx][yy]!=null && tmp[xx][yy].length()>4)
				if (yy == 2) {
					if (rs.getString(yy).length() < 6) {
						System.out.printf(rs.getString(yy)+"\t\t");
					} else
						System.out.printf(rs.getString(yy)+"\t");
				} else
					System.out.printf(rs.getString(yy)+"\t\t");
			}
			System.out.println("");
		}
	}
	
	public static void jwc(Document doc) {
		for(Element ele : doc.select("table").select("tr")){
			for(Element ele2 : ele.select("td")){
				if(!ele2.select("td").toString().equals("")){
					String text = ele2.select("td").get(0).text();
					System.out.print(text+"\t");
				}
			}
			System.out.println("");
		}
	}
	
	public static void getChart(String mode, String url) throws IOException, SQLException {
		File input = new File(url);
		Document doc = Jsoup.parse(input, "GBK");
		switch(mode) {
		case "�Ա�":
			taoBao(doc);
			break;
		case "����":
			jwc(doc);
		    break;
		default:
			break;
		}
	}

	public static void main(String[] args) throws IOException, SQLException {
		getChart("�Ա�", "C:\\Users\\yang\\Desktop\\�Ա�.html");
	}
	
}
