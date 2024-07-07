<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Friend" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%List<User> friendList = (List<User>) request.getAttribute("friendList");
   User user = (User)request.getAttribute("currentUser");
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
<form action="other-user-profile-servlet" method="get">
    <input id="search" type="text" name="otherUsername"/><br>
    <input type="submit" value="Search"/>
    <input type="hidden" name="userId" value="<%= user.id %>">
</form>
<h1>Fullname: <%= user.fullName%></h1>
<h1>Username: <%= user.userName%></h1>
<h1>Your Friends</h1>
<ul>
    <% for(User friend : friendList) { %>
    <li><%= friend.fullName %>, user: <%= friend.userName %></li>
    <% } %>
</ul>

<h2 style = "color: #450202;">შენი მიღწევები</h2>
<ul>
    <li style = "color: #450202;">საღოლ საღოლ</li>
    <li style = "color: #450202;">საღოლ საღოლ</li>
</ul>
</body>
</html>
