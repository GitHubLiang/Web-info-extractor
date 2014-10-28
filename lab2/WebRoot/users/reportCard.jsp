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
    
    <title>My JSP 'reportCard.jsp' starting page</title>
    
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
  <form name="scoreForm" action="<%=path%>/user/query.jsp"></form> 
      <h2>学生成绩单</h2>
      <hr> 
      <br>
      <br>
      <table width="900"  border="1" cellpadding="0" cellspacing="0">
      <tr>
        <td width="15%"><b>学期</b></td>
        <td width="20%"><b>课程名</b></td>
        <td width="10%"><b>授课教师</b></td>
        <td width="10%"><b>类别</b></td>
        <td width="5%"><b>学分</b></td>
        <td width="5%"><b>学时</b></td>
        <td width="5%"><b>成绩</b></td>
        <td width="10%"><b>成绩类别</b></td>
        <td width="15%"><b>备注</b></td>
      </tr>
      <s:iterator var="score" value="#session.score_list" status="status">
        <tr>
         <td width="15%"><s:property value="#score.term"/></td>
         <td width="20%"><s:property value="#score.course"/></td>
         <td width="10%"><s:property value="#score.teacher"/></td>
         <td width="10%"><s:property value="#score.sort"/></td>
         <td width="5%"><s:property value="#score.credit"/></td>
         <td width="5%"><s:property value="#scoer.period"/></td>
         <td width="5%"><s:property value="#score.grade"/></td>
         <td width="10%"><s:property value="#score.gradeL"/></td>
         <td width="15%"><s:property value="#score.remark"/></td>
        </tr>
      </s:iterator>
      </table>
      <br>
      <br> 
     </center>
  </body>
</html>
