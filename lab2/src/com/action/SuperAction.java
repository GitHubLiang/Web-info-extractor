package com.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

//所有Action的父类Action

public class SuperAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = -8179970384132390709L;
	protected HttpServletRequest request;
	protected HttpSession session ; 
	protected ServletContext application;
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletContext(ServletContext application) {
	  	// TODO Auto-generated method stub
		this.application = application;
	}

}
