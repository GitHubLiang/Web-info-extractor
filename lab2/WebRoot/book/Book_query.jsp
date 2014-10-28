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
  
  <body>
    <center>
      <h1>Query Book</h1>
      <hr>
      <form name="queryForm" action="<%=path%>/book/Book_query.action" method="post">   
        <table>
          <tr>
            <td>AuthorName</td>
            <td> <input type="text" name="Name">
          <tr>
            <td colspan="2" align="center"><input type="submit" value="query"/></td>
          </tr>
        </table>
      </form>
      <br>
      <br>
      <a href="<%=path%>/book/Book_query_success.jsp">return</a>
    </center>  
  </body>
</html>
