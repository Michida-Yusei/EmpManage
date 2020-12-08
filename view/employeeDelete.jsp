<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.EmployeeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員削除</title>
</head>
<body>
<h1>社員削除画面</h1>
<%EmployeeBean em=new EmployeeBean();
em=(EmployeeBean)request.getAttribute("em");
%>
<table border="1">
<tr>
<th>社員番号</th>
<th>氏名</th>
<th>役職</th>
<th>上司番号</th>
<th>入社年月日</th>
<th>給料</th>
<th>手当</th>
<th>部署番号</th>
</tr>

<tr>
<td><%=em.getEmployeeNo() %></td>
<td><%=em.getEmployeeName() %></td>
<td><%=em.getJob() %></td>
<td><%=em.getManagerNo() %></td>
<td><%=em.getJoiningCompanyDate() %></td>
<td><%=em.getSalary() %></td>
<td><%=em.getAllowance() %></td>
<td><%=em.getDepartment() %></td>
</tr>
</table>
<form action="EmployeeDelete" method="post">
<button type="submit" value="<%=em.getEmployeeNo()%>" name="employeeNo">確定</button>
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>