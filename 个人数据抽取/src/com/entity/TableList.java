package com.entity;

public class TableList {
	private String tableName = new String();
	private String date = new String();
	private String remark = new String();
	
	public void setTableName(String name) {
		this.tableName = name;
	}
	public String getTableName() {
		return this.tableName;
	} 
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return this.date;
	} 
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return this.remark;
	} 
}
