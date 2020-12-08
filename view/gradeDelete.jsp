<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.GradeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>等級削除</title>
</head>
<body>
<h1>等級削除画面</h1>
<%GradeBean gb=new GradeBean();
gb=(GradeBean)request.getAttribute("gb");
%>
<table border="1">
<tr>
<th>等級番号</th>
<th>最低給料</th>
<th>最大給料</th>
</tr>

<tr>
<td><%=gb.getGrade() %></td>
<td><%=gb.getMinSalary() %></td>
<td><%=gb.getMaxSalary() %></td>

</tr>
</table>
<form action="GradeDelete" method="post">
<button type="submit" value="<%=gb.getGrade() %>" name="grade">確定</button>
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>