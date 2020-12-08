<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.DepartmentBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部署削除</title>
</head>
<body>
<%DepartmentBean db=new DepartmentBean();
db=(DepartmentBean)request.getAttribute("db");
%>
<h1>部署削除画面</h1>
<table border="1">
<tr>
<th>部署番号</th>
<th>部署名</th>
<th>住所</th>
</tr>

<tr>
<td><%=db.getDepartmentNo() %></td>
<td><%=db.getDepartmentName() %></td>
<td><%=db.getAddress() %></td>

</tr>
</table>
<form action="DepartmentDelete" method="post">
<button type="submit" value="<%=db.getDepartmentNo() %>" name="departmentNo">確定</button>
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>