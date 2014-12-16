package com.dao;

public class TableHead {
	public String tableName;
	public String[] columns;
	public int columnLen;
	
	public TableHead(String name, String[] cols, int n) {
		tableName = name;
		columnLen = n;
		columns = new String[n];
		for(int i=0;i<n;i++) {
			columns[i] = cols[i];
		}
	}	
}
