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
      <h1>About the Book</h1>
      <hr>
     
      <form name="saveForm" action="<%=path%>/book/Book_save.action" method="post">
        
        <table>
        <s:iterator value="#session.mod_Book" status="status">
          <tr>
            <td colspan="2"><s:fielderror name="book_error"/></td>
          </tr>
          <tr>
            <td>ISBN:</td>
            <td> 
               <input type=text name="ISBN" value="<s:property value="ISBN"/>">
            </td>
          </tr>
          <tr>
            <td>Title</td>
            <td> 
              <input type=text name="Title" value="<s:property value="Title"/>">
            </td>
          </tr>
          <tr>
            <td>AuthorID</td>
            <td> 
               <input type=text name="AuthorID" value="<s:property value="AuthorID"/>">
            </td>
          </tr>
          <tr>
            <td>Publisher</td>
            <td> 
               <input type=text name="Publisher" value="<s:property value="Publisher"/>">
            </td>
          </tr>
          <tr>
            <td>PubishDate</td> 
            <td><input type="text" name="PublishDate"  value="<s:date name="%{PublishDate}" format="yyyy-MM-dd"/>"/></td>
          </tr>
          <tr>
            <td>Price</td>
            <td> 
             <input type=text name="Price" value="<s:property value="Price"/>">
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input type="submit" value="save"/></td>
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
