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
				rowVector.add(rs.getString("学期"));
				rowVector.add(rs.getString("课程号"));
				rowVector.add(rs.getString("课程名")); 
				rowVector.add(rs.getString("授课教师"));
				rowVector.add(rs.getString("类别"));
				rowVector.add(rs.getString("学分")); 
				rowVector.add(rs.getString("学时"));
				rowVector.add(rs.getString("成绩"));
				rowVector.add(rs.getString("成绩类别")); 
				rowVector.add(rs.getString("备注"));
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
