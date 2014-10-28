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
				b.setTerm(rs.getString("学期"));
				b.setCourseH(rs.getString("课程号"));
				b.setCourse(rs.getString("课程名")); 
				b.setTeacher(rs.getString("授课教师"));
				b.setSort(rs.getString("类别"));
				b.setCredit(rs.getString("学分")); 
				b.setPeriod(rs.getString("学时"));
				b.setGrade(rs.getString("成绩"));
				b.setGradeL(rs.getString("成绩类别")); 
				b.setRemark(rs.getString("备注"));
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
