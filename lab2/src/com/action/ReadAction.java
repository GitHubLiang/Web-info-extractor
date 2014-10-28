package com.action; 

import java.util.ArrayList; 
import com.dao.ReadDao;
import com.entity.score; 

public class ReadAction extends SuperAction { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2495498540796769368L;

	public String readScore() { 
		try {
			filterAction filter=new filterAction();
			String s=filter.score();
		}catch(Exception e){
			e.printStackTrace();
		}
		final ReadDao bdao = new ReadDao(); 
		final ArrayList<score> list2 = bdao.getAllscore();  
		session.setAttribute("score_list", list2);
		return "query_success";
	}
}
