<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員情報管理システム</title>
</head>
<body>
<h1>社員情報管理システム／一般</h1>
<ul>
<li><a href="login.html">ログイン</a></li>

<li><form name="form1" action="EmployeeShow" method="post">
  <a href="#" onClick="document.form1.submit();">社員一覧</a>
</form></li>

<li><a href="searchIppan.jsp">社員検索</a></li>

<li><form name="form2" action="DepartmentShow" method="post">
  <a href="#" onClick="document.form2.submit();">部署一覧</a>
 </form></li>

 <li><form name="form3" action="GradeShow" method="post">
  <a href="#" onClick="document.form3.submit();">等級一覧</a>
 </form></li>
</ul>
</body>
</html>