package com.entity;

public class Person {
	private String userName = new String();
	private String passWord = new String();
	private String isManage = new String();
	
	public String getuserName() {
		String name = this.userName;
		return name;
	}
	public String getpassWord() {
		String pass = this.passWord;
		return pass;
	}
	public String getisManage() {
		String is = this.isManage;
		return is;
	}
	public void setuserName(String user) {
		this.userName = user;
	}
	public void setpassWord(String pass) {
		this.passWord = pass;
	}
	public void setisManage(String is) {
		this.isManage = is;
	}
}
