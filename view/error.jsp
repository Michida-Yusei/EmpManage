<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.ErrorBean" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー画面</title>
</head>
<body>
<%ErrorBean eb=new ErrorBean();
eb=(ErrorBean)request.getAttribute("eb");%>

<h2>エラーメッセージ表示画面</h2>
エラーコード : <%=eb.getErrorCode() %><br>
エラーメッセージ : <%=eb.getErrorMsg() %><br>

<a href="javascript:history.back()">戻る</a>

</body>
</html>