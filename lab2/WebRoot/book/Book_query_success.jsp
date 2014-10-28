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
	 
  </head>
  
  <body>
    <center>
      <h1>Query System</h1>
      
      <hr> 
        <a href="<%=path%>/book/Book_query.jsp">Query Book</a>    
      	<a href="<%=path%>/book/Book_add.jsp">Add Book</a>
     <br>
      <table width="800"  border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td width="50%"><b>Title</b></td> 
      </tr>
      <s:iterator var="book" value="#session.book_list" status="status">
        <tr> 
         <td width="50%"><a href="<%=path%>/book/Book_detail.jsp?ISBN=<s:property value="#book.ISBN"/>"><s:property value="#book.Title"/></a></td>
        </tr>
      </s:iterator>
      </table>
      <br>
      <br>
      <a href="<%=path%>/index.jsp">Back to Login</a>
    </center>  
  </body>
</html>
