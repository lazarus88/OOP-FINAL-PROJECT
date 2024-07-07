<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Friend" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List<User> friendList = (List<User>) request.getAttribute("friendList");
            String username = (String) request.getAttribute("username");
            String fullname = (String) request.getAttribute("fullname");
%>
<html>
<style>
    body {background-color: #bac1f8;}
    h1 {color: #000109;}
</style>
<head>
    <title>Title</title>
</head>
<body>
<form action="OtherUserProfile.jsp" method="GET">
    <input id="search" type="text" name="username"/><br>
    <input type="submit" value="Search"/>
</form>
<h1>Fullname: <%= fullname%></h1>
<h1>Username: <%= username%></h1>
<h1>Your Friends</h1>
<ul>
    <% for(User friend : friendList) { %>
    <li><%= friend.fullName %>, user: <%= friend.userName %></li>
    <% } %>
</ul>
</body>
</html>
