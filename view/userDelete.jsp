<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利用者削除</title>
</head>
<body>
<h1>利用者削除画面</h1>
<%UserBean ub=new UserBean();
ub=(UserBean)request.getAttribute("ub");
%>
<table border="1">
<tr>
<th>ユーザーID</th>
<th>パスワード</th>
<th>社員番号</th>
<th>メールアドレス</th>
<th>権限</th>
</tr>

<tr>
<td><%=ub.getUserId() %></td>
<td><%=ub.getPass() %></td>
<td><%=ub.getEmployeeNo() %></td>
<td><%=ub.getMail() %></td>
<td><%=ub.getAuthority() %></td>
</tr>
</table>
<form action="UserDelete" method="post">
<button type="submit" value="<%=ub.getUserId() %>" name="userId">確定</button>
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>