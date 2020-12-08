<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.EmployeeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員一覧</title>
</head>
<body>
<h1>社員一覧</h1>

<% ArrayList<EmployeeBean> empList=(ArrayList<EmployeeBean>)request.getAttribute("empList");%>


<table border="1">
<tr>
<th>社員番号</th>
<th>氏名</th>
<th>職種</th>
<th>上司番号</th>
<th>上司氏名</th>
<th>入社年月日</th>
<th>給料</th>
<th>手当</th>
<th>部署番号</th>
<th>部署名</th>
<th>等級</th>
</tr>
<%for(EmployeeBean em : empList){ %>
<tr>
<td><%=em.getEmployeeNo() %></td>
<td><%=em.getEmployeeName() %></td>
<td><%=em.getJob() %></td>
<td><%=em.getManagerNo() %></td>
<td><%=em.getManagerName() %></td>
<td><%=em.getJoiningCompanyDate() %></td>
<td><%=em.getSalary() %></td>
<td><%=em.getAllowance() %></td>
<td><%=em.getDepartment() %></td>
<td><%=em.getDepartmentName() %></td>
<td><%=em.getGrade() %></td>
</tr>
<%} %>
</table>
<pre>

</pre>
<a href="employeeInsert.jsp">社員登録</a>
<pre>
</pre>
<a>更新する社員の社員番号を入力してください</a>
<form name="form1" action="EmployeeUpdateSearch" method="post">
<input type="text" name="employeeNo" size="4" value="">
  <a href=# onClick="document.form1.submit();">入力画面へ</a>
</form>
<pre>
</pre>
<a>削除する社員の社員番号を入力してください</a>
<form name="form2" action="EmployeeDelSearch" method="post">
<input type="text" name="employeeNo" size="4" value="">
  <a href="#" onClick="document.form2.submit();">確認画面へ</a>
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>