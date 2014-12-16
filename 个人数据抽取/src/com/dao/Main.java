package com.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException, SQLException {
		//Extractor e = new Extractor("F:\\SE\\labs\\jwc.htm");//ok
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\rank985.html");
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\��һ�����Ŀ��ѯ.htm");//fine
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\��ѧ�����̽����ѯ.htm");//surprise!
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\����Ա.html");
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\HTML �ַ�ʵ��.htm");//get~
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\���봦������ѯ.htm");
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\�и�.html");ok
		//Extractor e = new Extractor("F:\\SE\\labs\\lab5\\src\\ACM.html");
		ReadDao e = new ReadDao("F:\\SE\\labs\\lab5\\src\\������Ƹ߿��ɼ�����.htm");
        e.extract();
        LinkedList<TableHead> tableHeads = e.getTable();
        System.out.println(tableHeads.size());//�����м�����
        for(TableHead t : tableHeads) {
        	System.out.println(t.tableName);
        	System.out.println(t.columnLen);
        	for(int i=0;i<t.columnLen;i++) {
        		System.out.println(t.columns[i]);
        	}
        }
        
	}
}
