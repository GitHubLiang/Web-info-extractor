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
      <h1>Modify Book</h1>
      <hr>
      <form name="modifyForm" action="<%=path%>/book/Book_modify.action" method="post">
        <table> 
        <s:iterator value="#session.book_list" status="status">
          <tr>
            <td>ISBN:</td>
            <td> 
               <input type=text name="ISBN" value="<s:property value="ISBN"/>">
            </td>
          </tr>
           <tr>
            <td>Title</td>
            <td>
              <input type="text" name="Title">
            </td>
          </tr>
          <tr>
            <td>AuthorID:</td>
            <td><input type="text" name="AuthorID">
          </tr>
          <tr>
           <td>Publisher</td>
           <td><input type="text" name="Publisher">
          <tr>
            <td>PublishDate</td>
            <s:textfield name="PublishDate"
                      value="%{PublishDate == null ? '' :getText('global.date',{PublishDate})}"/>
          <!-- <td><input type="text" value="<s:date name="PublishDate" format="yyyy-mm-dd"/>"/></td> --> 
          </tr>
           <tr>
            <td>Price:</td>
            <td>
              <input type="text" name="Price">
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input type="submit" value="modify"/></td>
          </tr>
          </s:iterator>
        </table>
      </form>
      <br>
      <br>
      <a href="<%=path%>/book/Book_query_success.jsp">return</a>
    </center>  
  </body>
</html>
