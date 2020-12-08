<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.DepartmentBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部署登録</title>
</head>
<body>
<h1>部署登録画面</h1>
<form name="form1" action="DepartmentInsert" method="post">
<table>
<tr>
  <td>部署番号</td>
  <td><input type="text" name="departmentNo"  value="" required>
  *整数を入力してください</td>
</tr>
<tr>
  <td>部署名</td>
  <td><input type="text" name="departmentName"  value="" required></td>
</tr>
<tr>
  <td>住所</td>
  <td><input type="text" name="address"  value="" required></td>
</tr>

  </table>
  <input type="submit" value="確定">
  </form>
  <a href="javascript:history.back()">戻る</a>
</body>
</html>