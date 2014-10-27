<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登陆页面</title>
  </head> 
  <body>
  	<center><h2>
    	<form action="login" method="post">
    		<br/>
    		<br/>
    		<br/>
    		欢迎进入成绩查询系统<br/>
    		<br/>
    		<br/>
    		<br/>
    		<br/>
    		<br/>
    		<br/>
    		 用户名:<input name="username"/><br/>
    		 密码:<input name="password"/><br/>
    		<input value="登录" type="submit">
    	</form>
    </center>
  </body>
</html>
