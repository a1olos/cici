<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<h2>Add Book</h2>
<form method="post" action="<%=request.getContextPath() %>/book/add.do">
bookname:<input type="text" name="name" id="name">
author:<input type="text" name="author" id="author">
<input type="submit" value="Add">
</form>
</body>
</html>
