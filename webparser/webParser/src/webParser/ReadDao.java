package webParser;
import webParser.score;
import webParser.MySQLJdbc;

import java.util.ArrayList; 
import java.util.Vector;

public class ReadDao extends SuperDao{
	public Vector getAllscore(String str) {
		try {
		new PersonalData().score(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		final int MAX = 1000;
		final ArrayList<score> list = new ArrayList<score>(); 
		Vector dataVector = new Vector();
		try {  
			conn = MySQLJdbc.getConnection(); 
			final String sql = "select * from reportCard;";
			stmt = conn.prepareStatement(sql); 
			rs = stmt.executeQuery();  
			while (rs.next()) {   
				Vector rowVector = new Vector();
				rowVector.add(rs.getString("ѧ��"));
				rowVector.add(rs.getString("�γ̺�"));
				rowVector.add(rs.getString("�γ���")); 
				rowVector.add(rs.getString("�ڿν�ʦ"));
				rowVector.add(rs.getString("���"));
				rowVector.add(rs.getString("ѧ��")); 
				rowVector.add(rs.getString("ѧʱ"));
				rowVector.add(rs.getString("�ɼ�"));
				rowVector.add(rs.getString("�ɼ����")); 
				rowVector.add(rs.getString("��ע"));
				dataVector.add(rowVector);
				}
			return dataVector;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			this.destoryResource();
		}
	}
}
