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
      <h1>Book Detail</h1>
      <hr> 
     <br>
      <form name="deleteForm" action="<%=path%>/book/Book_delete.action" method="post">
      <table width="800"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="5%"><b>ISBN</b></td>
        <td width="5%"><b>Title</b></td>
        <td width="10%"><b>AuthorID</b></td>
        <td width="15%"><b>Publisher</b></td>
        <td width="15%"><b>PublishDate</b></td>
        <td width="10"><b>Price</b></td>
      </tr>
      <s:iterator value="#session.book_list" status="status">
        <tr>
         <td width="20%"><s:property value="ISBN"/></td>
         <td width="10%"><s:property value="Title"/></td>
         <td width="5%"><s:property value="AuthorID"/></td>
         <td width="15%"><s:property value="Publisher"/></td>
         <td width="20%">
           <s:date name="PublishDate" format="yyyy-MM-dd" />
         </td>
         <td width="10%"><s:property value="Price"/></td> 
         <td> 
           <a href="<%=path%>/book/Book_delete.action?ISBN=<s:property value="ISBN"/>"onclick="return confirm('确定要删除吗？\n\n该操作不可恢复！')">删除</a></td>
 			<td><a href="<%=path%>/book/Book_modify.jsp">更新</a>
         </td>
        </tr>  
      </s:iterator>
         <tr>
        <td width="20%"><b>AuthorID</b></td>
        <td width="25%"><b>Name</b></td>
        <td width="20%"><b>Age</b></td>
        <td width="25"><b>Country</b></td>
      </tr>
      <s:iterator var="author" value="#session.author_list" status="status">
        <tr>
         <td width="20%"><s:property value="#author.AuthorID"/></td>
         <td width="25%"><s:property value="#author.Name"/></td>
         <td width="20%"><s:property value="#author.Age"/></td>
         <td width="25%"><s:property value="#author.Country"/></td>  
        </tr>
      </s:iterator>
      </table>
    </form>
      <br>
      <br>
      <a href="<%=path%>/index.jsp">Back to Login</a>
    </center>  
  </body>
</html>
