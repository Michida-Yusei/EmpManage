<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員検索</title>
</head>
<body>
<h1>検索画面</h1>

<h3>社員番号を入力してください</h3>
<p>*整数を入力してください</p>
<form action="SearchEmpNo" method="post">
<input type="text" name="employeeNo" value="">
<input type="submit" value="検索">
</form>

<h3>部署名を入力してください</h3>
<form action="SearchDep" method="post">
<input type="text" name="departmentName" value="">
<input type="submit" value="検索">
</form>

<h3>職種を入力してください</h3>
<form action="SearchJob" method="post">
<input type="text" name="job" value="">
<input type="submit" value="検索">
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>