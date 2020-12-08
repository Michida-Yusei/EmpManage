<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.DepartmentBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部署更新</title>
</head>
<body>
<h1>部署更新画面</h1>
<%DepartmentBean db=new DepartmentBean();
db=(DepartmentBean)request.getAttribute("db");
%>
<form name="form2" action="DepartmentUpdate" method="post">
<table>
<tr>
  <td>部署番号</td>
  <td><%=db.getDepartmentNo() %><input type="hidden" name="departmentNo"  value="<%=db.getDepartmentNo() %>" required></td>
</tr>
<tr>
  <td>部署名</td>
  <td><input type="text" name="departmentName"  value="<%=db.getDepartmentName() %>" required></td>
</tr>
<tr>
  <td>住所</td>
  <td><input type="text" name="address"  value="<%=db.getAddress() %>" required></td>
</tr>
  </table>
  <input type="submit" value="確定">
  </form>
  <a href="javascript:history.back()">戻る</a>
</body>
</html>