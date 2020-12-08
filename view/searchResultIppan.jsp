<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.EmployeeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
</head>
<body>
<h1>社員一覧</h1>

<% ArrayList<EmployeeBean> empList=(ArrayList<EmployeeBean>)request.getAttribute("empList");%>


<table border="1">
<tr>
<th>社員番号</th>
<th>氏名</th>
<th>役職</th>
<th>部署番号</th>
<th>部署名</th>
</tr>
<%for(EmployeeBean em : empList){ %>
<tr>
<td><%=em.getEmployeeNo() %></td>
<td><%=em.getEmployeeName() %></td>
<td><%=em.getJob() %></td>
<td><%=em.getDepartment() %></td>
<td><%=em.getDepartmentName() %></td>
</tr>
<%} %>
</table>
<a href="javascript:history.back()">戻る</a>
</body>
</html>