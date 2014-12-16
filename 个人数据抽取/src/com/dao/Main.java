package com.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

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
		ReadDao e = new ReadDao("F:\\SE\\labs\\lab5\\src\\河南理科高考成绩排名.htm");
        e.extract();
        LinkedList<TableHead> tableHeads = e.getTable();
        System.out.println(tableHeads.size());//返回有几个表
        for(TableHead t : tableHeads) {
        	System.out.println(t.tableName);
        	System.out.println(t.columnLen);
        	for(int i=0;i<t.columnLen;i++) {
        		System.out.println(t.columns[i]);
        	}
        }
        
	}
}
