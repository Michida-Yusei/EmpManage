<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.GradeBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>等級登録</title>
</head>
<body>
<h1>等級登録画面</h1>
<form name="form1" action="GradeInsert" method="post" >
<table>
<tr>
  <td>等級番号</td>
  <td><input type="text" name="grade"  value="" required> *整数を入力してください</td>
</tr>
<tr>
  <td>最低給料</td>
  <td><input type="text" name="minSalary"  value="" required> *実数を入力してください</td>
</tr>
<tr>
  <td>最大給料</td>
  <td><input type="text" name="maxSalary"  value="" required> *実数を入力してください</td>
</tr>

  </table>
  <input type="submit" value="確定">
  </form>
  <a href="javascript:history.back()">戻る</a>
</body>
</html>