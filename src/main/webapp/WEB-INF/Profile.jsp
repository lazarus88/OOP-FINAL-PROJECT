<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ntati
  Date: 7/5/2024
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<User> friendList = (List<User>) request.getAttribute("friendList"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List of Friends</h1>
<ul>
    <%for(User friend : friendList){ %>
    <li><%=friend.fullName%>, user: <%=friend.userName%></li>
    <%}%>
</ul>
</body>
</html>
