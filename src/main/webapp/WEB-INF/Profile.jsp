<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Friend" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Achievement" %>
<%@ page import="org.example.oopdefaultkgb.Enum.AchievementEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Achievement> achievementList = (List<Achievement>) request.getAttribute("achievementList");
    List<User> friendList = (List<User>) request.getAttribute("friendList");
    User user = (User)request.getAttribute("currentUser");
%>
<!DOCTYPE html>
<html>
<head>
    <title>პროფილის გვერდის</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }
        header {
            background: #50b3a2;
            color: #fff;
            padding-top: 30px;
            min-height: 70px;
            border-bottom: #e8491d 3px solid;
        }
        header a {
            color: #fff;
            text-decoration: none;
            text-transform: uppercase;
            font-size: 16px;
        }
        header ul {
            padding: 0;
            list-style: none;
        }
        header li {
            display: inline;
            padding: 0 20px 0 20px;
        }
        .main-conteiner
        .main-content {
            padding: 20px;
            background: #fff;
            margin-top: 20px;
        }
        .main-content h1 {
            text-align: left;
            color: #333;
        }
        .main-content ul {
            list-style: none;
            padding: 0;
        }
        .main-content ul li {
            background: #e8e8e8;
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
        }
        .search-box {
            text-align: center;
            margin: 20px 0;
        }
        .search-box input[type="text"] {
            padding: 10px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .search-box input[type="submit"] {
            padding: 10px 20px;
            background: #50b3a2;
            border: none;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
        }
        .search-box input[type="submit"]:hover {
            background: #429184;
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <h1>პროფილის გვერდი</h1>
    </div>
</header>
<div class="container main-content">
    <div class="search-box">
        <form action="other-user-profile-servlet" method="get">
            <input id="search" type="text" name="otherUsername" placeholder="Search for a user..."/><br>
            <input type="submit" value="Search"/>
            <input type="hidden" name="userId" value="<%= user.id %>">
        </form>
    </div>
    <h1>სრული სახელი: <%= user.fullName %></h1>
    <h1>იუზერნეიმი: <%= user.userName %></h1>
    <h1>რეგისტრაციის თარიღი: <%= user.createdAt %></h1>

    <h1>კეთილები:</h1>
    <ul>
        <% for (User friend : friendList) { %>
        <form action="other-user-profile-servlet" method="get">
            <input type="hidden" name="otherUsername" value="<%= friend.userName %>">
            <input type="hidden" name="userId" value="<%= user.id %>">
        <li><%= friend.fullName %>,     იუზერნეიმი: <%= friend.userName %> <input type="submit" value="forward profile"/></li>
        <% } %>
    </ul>

    <h1 style="color: #450202;">მიღწევები</h1>
    <ul>
        <% for (Achievement achievement : achievementList) { %>
        <li style="color: #450202;"><%= AchievementEnum.intToString(achievement.achievementId) %>, მიღწეულია: <%= achievement.achievedAt%> - დროს</li>
        <% } %>
    </ul>
    <form action="CreateQuizServlet" method="post">
        <input type="submit" value="Create quiz"/>
        <input type="hidden" name="userId" value="<%= user.id %>">
    </form>

</div>
</body>
</html>
