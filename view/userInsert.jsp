<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利用者登録</title>
</head>
<body>
<h1>利用者登録画面</h1>
<form name="form1" action="UserInsert" method="post">
<table>
<tr>
  <td>ユーザーID</td>
  <td><input type="text" name="userId" value="" required></td>
</tr>
<tr>
  <td>パスワード</td>
  <td><input type="text" name="pass"  value="" required></td>
</tr>
<tr>
  <td>社員番号</td>
  <td><input type="text" name="employeeNo"  value="" required> *整数を入力してください</td>
</tr>
<tr>
  <td>メールアドレス</td>
  <td><input type="text" name="mail"  value="" required></td>
</tr>
<tr>
  <td>権限</td>
  <td><input type="text" name="authority"  value="" required> *整数を入力してください</td>
</tr>
  </table>
  <input type="submit" value="確定">
  </form>
  <a href="javascript:history.back()">戻る</a>
</body>
</html>