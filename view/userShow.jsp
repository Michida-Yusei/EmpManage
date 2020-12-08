<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利用者一覧</title>
</head>
<body>

<% ArrayList<UserBean> usList=(ArrayList<UserBean>)request.getAttribute("usList");%>

<h1>利用者一覧</h1>
<table border="1">
<tr>
<th>ユーザーID</th>
<th>パスワード</th>
<th>社員番号</th>
<th>メールアドレス</th>
<th>権限</th>
</tr>
<%for(UserBean ub : usList){ %>
<tr>
<td><%=ub.getUserId() %></td>
<td><%=ub.getPass() %></td>
<td><%=ub.getEmployeeNo() %></td>
<td><%=ub.getMail() %></td>
<td><%=ub.getAuthority() %></td>
</tr>
<%} %>
</table>
<pre>

</pre>
<a href="userInsert.jsp">利用者登録</a>
<pre>
</pre>
<a>更新する利用者の利用者IDを入力してください</a>
<form name="form1" action="UserUpdateSearch" method="post">
<input type="text" name="userId" size="4" value="">
  <a href="#" onClick="document.form1.submit();">利用者更新</a>
</form>
<pre>
</pre>
<a>削除する利用者の利用者IDを入力してください</a>
<form name="form2" action="UserDelSearch" method="post">
<input type="text" name="userId" size="4" value="">
  <a href="#" onClick="document.form2.submit();">利用者削除</a>
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>