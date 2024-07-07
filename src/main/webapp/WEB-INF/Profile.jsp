<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Friend" %>
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
<style>
    body {background-color: #e37a7a
    }
    h1 {color: indigo}
</style>
<head>
    <title>Title</title>
</head>
<body>
<h2 style = "color: #450202;">შენი მეგობრები</h2>
<ul>
    <%for(User friend : friendList){%>

    <li style = "color: #450202;"><%=friend.fullName%>, user: <%=friend.userName%></li>
    <%}%>
</ul>
<h2 style = "color: #450202;">შენი მიღწევები</h2>
<ul>
    <li style = "color: #450202;">საღოლ საღოლ</li>
    <li style = "color: #450202;">საღოლ საღოლ</li>
</ul>
<a href="other-user-profile-servlet?userId=<%=((User)request.getAttribute("currentUser")).id%>&otherUserId=1">Other User Profile</a>
</body>
</html>
