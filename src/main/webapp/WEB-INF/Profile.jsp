<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %><%--
  Created by IntelliJ IDEA.
  User: ntati
  Date: 7/5/2024
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%= ((User)request.getAttribute("currentUser")).getFullName() %>
</h1>
<br/>
<a href="mail-servlet?userId=5">My Mails</a>
</h2>
</body>
</html>
