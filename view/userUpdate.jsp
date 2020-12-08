<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員更新</title>
</head>
<body>
<h1>社員更新画面</h1>
<%UserBean ub=new UserBean();
ub=(UserBean)request.getAttribute("ub");
%>
<form name="form2" action="UserUpdate" method="post">
<table>
<tr>
  <td>ユーザーID</td>
  <td><%=ub.getUserId() %><input type="hidden" name="userId" value="<%=ub.getUserId() %>"></td>
</tr>
<tr>
  <td>パスワード</td>
  <td><input type="text" name="pass" value="<%=ub.getPass() %>" required></td>
</tr>
<tr>
  <td>社員番号</td>
  <td><input type="text" name="employeeNo"  value="<%=ub.getEmployeeNo() %>" required>
  *整数を入力してください</td>
</tr>
<tr>
  <td>メールアドレス</td>
  <td><input type="text" name="mail"  value="<%=ub.getMail() %>" required></td>
</tr>
<tr>
  <td>権限</td>
  <td><input type="text" name="authority"  value="<%=ub.getAuthority() %>" required>
  *整数を入力してください</td>
</tr>
  </table>
  <input type="submit" value="確定">
  </form>
  <a href="javascript:history.back()">戻る</a>
</body>
</html>