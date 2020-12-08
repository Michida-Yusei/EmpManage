<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.GradeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>等級一覧</title>
</head>
<body>

<% ArrayList<GradeBean> graList=(ArrayList<GradeBean>)request.getAttribute("graList");%>

<h1>等級一覧</h1>
<table border="1">
<tr>
<th>等級番号</th>
<th>最低給料</th>
<th>最大給料</th>
</tr>
<%for(GradeBean gb : graList){ %>
<tr>
<td><%=gb.getGrade() %></td>
<td><%=gb.getMinSalary() %></td>
<td><%=gb.getMaxSalary() %></td>
</tr>
<%} %>
</table>
<a href="javascript:history.back()">戻る</a>
</body>
</html>