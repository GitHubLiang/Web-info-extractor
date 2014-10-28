package com.dao;
import com.entity.score;
import com.db.MySQLJdbc;
import java.util.ArrayList; 

public class ReadDao extends SuperDao{
	public ArrayList<score> getAllscore() {
		final ArrayList<score> list = new ArrayList<score>();
		try {  
			conn = MySQLJdbc.getConnection();
			final String sql = "select * from reportCard;";
			stmt = conn.prepareStatement(sql); 
			rs = stmt.executeQuery(); 
			while (rs.next()) { 
				final score b = new score();
				b.setTerm(rs.getString("ѧ��"));
				b.setCourseH(rs.getString("�γ̺�"));
				b.setCourse(rs.getString("�γ���")); 
				b.setTeacher(rs.getString("�ڿν�ʦ"));
				b.setSort(rs.getString("���"));
				b.setCredit(rs.getString("ѧ��")); 
				b.setPeriod(rs.getString("ѧʱ"));
				b.setGrade(rs.getString("�ɼ�"));
				b.setGradeL(rs.getString("�ɼ����")); 
				b.setRemark(rs.getString("��ע"));
				list.add(b);
				}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			this.destoryResource();
		}
	}
}
