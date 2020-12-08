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
<th>住所</th>
</tr>
<%for(DepartmentBean db : depList){ %>
<tr>
<td><%=db.getDepartmentNo() %></td>
<td><%=db.getDepartmentName() %></td>
<td><%=db.getAddress() %></td>
</tr>
<%} %>
</table>
<pre>

</pre>
<a href="departmentInsert.jsp">部署登録</a>
<pre>
</pre>
<a>更新する部署の部署番号を入力してください</a>
<form name="form1" action="DepartmentUpdateSearch" method="post">
<input type="text" name="departmentNo" size="4" value="">
  <a href="#" onClick="document.form1.submit();">入力画面へ</a>
</form>
<pre>
</pre>
<a>削除する部署の部署番号を入力してください</a>
<form name="form2" action="DepartmentDelSearch" method="post">
<input type="text" name="departmentNo" size="4" value="">
  <a href="#" onClick="document.form2.submit();">確認画面へ</a>
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>