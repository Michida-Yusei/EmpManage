<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員登録</title>
</head>
<body>
<h1>社員登録画面</h1>
<form name="form1" action="EmployeeInsert" method="post">
<table>
<tr>
  <td>社員番号</td>
  <td><input type="text" name="employeeNo"  value="" required>
  *整数を入力してくだいさい</td>
</tr>
<tr>
  <td>社員氏名</td>
  <td><input type="text" name="employeeName"  value="" required></td>
</tr>
<tr>
  <td>職種</td>
  <td><input type="text" name="job"  value="" required></td>
</tr>
<tr>
  <td>上司番号</td>
  <td><input type="text" name="managerNo"  value="" required>
  *整数を入力してください　上司がいない場合は０を入力してくください</td>
</tr>
<tr>
  <td>入社年月日</td>
  <td><input type="text" name="joiningCompanyDate"  value="" required>
  *入力例　（2018/04/01）</td>
</tr>
<tr>
  <td>給料</td>
  <td><input type="text" name="salary"  value="" required>
  *実数を入力してください</td>
</tr>
<tr>
  <td>手当</td>
  <td><input type="text" name="allowance"  value="" required>
  *実数を入力してください</td>
</tr>
<tr>
  <td>部署番号</td>
  <td><input type="text" name="department"  value="" required>
  *整数を入力してください</td>
</tr>
  </table>
  <input type="submit" value="確定">
  </form>
  <a href="javascript:history.back()">戻る</a>
</body>
</html>