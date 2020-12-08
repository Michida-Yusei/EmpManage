<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>リザルト画面</title>
</head>
<body>

<%String url=(String)request.getAttribute("url");
   String msg=(String)request.getAttribute("msg");%>
<h3><%=msg %></h3><br>
<a href=<%=url %>>home</a>
</body>
</html>