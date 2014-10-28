<%@ page language="java" import="java.util.*" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
   
  <!-- <frameset rows="25%,75%" frameborder="0">
  	<frame src="login/1-1.html"  marginheight="60">
  		<frameset cols="20%,55%,25%" frameborder="0">
  		<frame src="login/1-3.html">
  		<frame src="login/1-2.html" marginheight="60">
  		<frame src="login/1-4.html">
  		</frameset>
  </frameset>
 -->
<body bgcolor="LightBlue">
  <center> 
  
   <form name="loginForm" action= "<%=path%>/login/login.action" method="post"> 
        <table bgcolor="LightPink" width=50% height=200 border=1>
          <tr>
            <td colspan="2" align="center"><h2>用户登录</h2></td>
          </tr>
          <tr>
            <td>用户名：</td>
            <td>
              <input type="text" name="username">
            </td>
          </tr>
          <tr>
            <td>密码：</td>
            <td><input type="password" name="password"></td>
          </tr>
          <tr>
            <td></td>
            <td><input type="submit" value="login">
            <input type="submit" value="sign up"></td>
          </tr>
        </table> 
        </form>
    </center>  
  </body> 
</html>
