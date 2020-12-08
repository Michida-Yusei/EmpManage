<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.DepartmentBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部署一覧</title>
</head>
<body>

<% ArrayList<DepartmentBean> depList=(ArrayList<DepartmentBean>)request.getAttribute("depList");%>

<h1>部署一覧</h1>
<table border="1">
<tr>
<th>部署番号</th>
<th>部署</th>
</tr>
<%for(DepartmentBean db : depList){ %>
<tr>
<td><%=db.getDepartmentNo() %></td>
<td><%=db.getDepartmentName() %></td>
</tr>
<%} %>
</table>
<a href="javascript:history.back()">戻る</a>
</body>
</html>