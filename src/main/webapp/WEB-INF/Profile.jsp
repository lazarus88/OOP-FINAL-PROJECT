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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>პროფილის გვერდის</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        header {
            background: #50b3a2;
            color: #fff;
            width: 100%;
            padding: 30px 0;
            border-bottom: 3px solid #e8491d;
        }
        header h1 {
            text-align: center;
            margin: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .container h1 {
            color: #333;
            text-align: left;
        }
        .container ul {
            list-style: none;
            padding: 0;
        }
        .container ul li {
            background: #e8e8e8;
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
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
        header .quiz-icon {
            position: absolute;
            top: 15px;
            left: 15px;
            cursor: pointer;
            width: 40px; /* Adjust the size as needed */
            height: auto;
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <a href="user-profile-servlet?userId=<%=user.id%>">
            <img src="some.png" alt="Quiz Icon" class="quiz-icon" />
        </a>
        <h1>პროფილის გვერდი</h1>
    </div>
</header>
<div class="container main-content">
    <div class="search-box">
        <form action="other-user-profile-servlet" method="get">
            <input id="search" type="text" name="otherUsername" placeholder="Search for a user..."/><br>
            <input type="submit" value="Search"/>
            <input type="hidden" name="userId" value=<%= user.id %>>
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
            <input type="hidden" name="userId" value=<%= user.id %>>
        <li><%= friend.fullName %>,     იუზერნეიმი: <%= friend.userName %>
            <input type="submit" value="forward profile"/></li>
        </form>
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
