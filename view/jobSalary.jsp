<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.SalaryBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>職種ごとの平均給与</title>
</head>
<body>

<% ArrayList<SalaryBean> salList=(ArrayList<SalaryBean>)request.getAttribute("salList");%>

<h1>職種ごとの平均給与</h1>
<table border="1">
<tr>
<th>仕事</th>
<th>人数</th>
<th>平均給与</th>
</tr>
<%for(SalaryBean sb : salList){ %>
<tr>
<td><%=sb.getJob() %></td>
<td><%=sb.getNinzu() %></td>
<td><%=sb.getAverageSalary() %></td>
</tr>
<%} %>
</table>
<a href="javascript:history.back()">戻る</a>
</body>
</html>