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
<form action="SearchEmpNo" method="post">
<p>*整数で入力してください</p>
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

<h3>入社年月日の範囲を入力してください</h3>
<p>*yyyy/mm/ddの形式で入力してください</p>
<form action="SearchJoining" method="post">
<input type="text" name="joining1" value="">
<p>から</p>
<input type="text" name="joining2" value="">
<input type="submit" value="検索">
</form>

<h3>等級の範囲を入力してください</h3>
<p>* 実数を入力してください</p>
<form action="SearchGrade" method="post">
<p>最低等級</p>
<input type="text" name="grade1" value="">
<p>最大等級</p>
<input type="text" name="grade2" value="">
<input type="submit" value="検索">
</form>
<a href="javascript:history.back()">戻る</a>
</body>
</html>