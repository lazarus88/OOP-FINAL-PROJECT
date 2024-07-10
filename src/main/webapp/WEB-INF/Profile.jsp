<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Friend" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Achievement" %>
<%@ page import="org.example.oopdefaultkgb.Enum.AchievementEnum" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Achievement> achievementList = (List<Achievement>) request.getAttribute("achievementList");
    List<User> friendList = (List<User>) request.getAttribute("friendList");
    User user = (User)request.getAttribute("currentUser");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile Page</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #121212;
            color: #ffffff;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        header {
            background: #333333;
            color: #ffffff;
            width: 100%;
            padding: 20px 0;
            border-bottom: 3px solid #00ffff;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
        }
        header h1 {
            margin: 0;
        }
        header .quiz-icon {
            position: absolute;
            top: 15px;
            left: 15px;
            cursor: pointer;
            width: 40px;
            height: auto;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background: #1e1e1e;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .main-content {
            display: flex;
            justify-content: space-between;
            width: 100%;
        }
        .section {
            flex: 1;
            margin: 10px;
        }
        .section h2 {
            color: #00ffff;
            margin-bottom: 10px;
            text-align: center;
        }
        .profile-info p,
        .friends-list ul li,
        .achievements-list ul li {
            background: #2b2b2b;
            margin: 10px 0;
            padding: 10px;
            border-radius: 5px;
        }
        .friends-list ul,
        .achievements-list ul {
            list-style: none;
            padding: 0;
        }
        .friends-list ul li,
        .achievements-list ul li {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .profile-image {
            text-align: center;
            margin: 20px 0;
        }
        .profile-image img {
            max-width: 200px;
            border-radius: 10px;
            margin-bottom: 10px;
        }
        .search-create {
            text-align: center;
            margin: 20px 0;
            display: flex;
            justify-content: space-between;
            width: 100%;
        }
        .search-box,
        .create-quiz,
        .update-image {
            flex: 1;
            margin: 0 10px;
        }
        .search-box input[type="text"] {
            padding: 10px;
            width: calc(100% - 22px);
            border: 1px solid #555555;
            border-radius: 5px;
            background-color: #2b2b2b;
            color: #ffffff;
        }
        .create-quiz input[type="submit"],
        .update-image input[type="submit"] {
            padding: 10px 20px;
            background: #50b3a2;
            border: none;
            color: #ffffff;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }
        .create-quiz input[type="submit"]:hover,
        .update-image input[type="submit"]:hover {
            background: #429184;
        }
    </style>
</head>
<body>
<header>
    <a href="HomeServlet?userId=<%=user.id%>">
        <img src="some.png" alt="Quiz Icon" class="quiz-icon" />
    </a>
    <h1>Profile Page</h1>
</header>
<div class="container">
    <div class="profile-image">
        <img src="default-profile.png" alt="Profile Image"/>
        <div class="update-image">
            <form action="UpdateImageServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="userId" value="<%= user.id %>">
                <input type="file" name="profileImage" accept="image/*" class="btn btn-secondary btn-sm"/>
                <input type="submit" value="Update Image" class="btn btn-primary"/>
            </form>
        </div>
    </div>
    <div class="search-create">
        <div class="search-box">
            <% if(request.getAttribute("otherUserNotFound") != null && (Boolean)request.getAttribute("otherUserNotFound")) { %>
            <h4>User not found</h4>
            <% } %>
            <form action="other-user-profile-servlet" method="get">
                <input id="search" type="text" name="otherUsername" placeholder="Search for a user..." onkeydown="if(event.key === 'Enter'){ this.form.submit(); return false; }"/><br>
                <input type="hidden" name="userId" value="<%= user.id %>">
            </form>
        </div>
        <div class="create-quiz">
            <form action="CreateQuizServlet" method="post">
                <input type="hidden" name="userId" value="<%= user.id %>">
                <input type="submit" value="Create Quiz" class="btn btn-primary"/>
            </form>
        </div>
    </div>


    <div class="main-content">
        <div class="section profile-info">
            <h2>Profile Information</h2>
            <p>Full Name: <%= user.fullName %></p>
            <p>Username: <%= user.userName %></p>
            <p>Registration Date: <%= user.createdAt.format(formatter) %></p>
        </div>

        <div class="section friends-list">
            <h2>Friends</h2>
            <ul>
                <% for (User friend : friendList) { %>
                <form action="other-user-profile-servlet" method="post">
                    <input type="hidden" name="otherUsername" value="<%= friend.userName %>">
                    <input type="hidden" name="userId" value="<%= user.id %>">
                    <li>
                        <%= friend.fullName %>, Username: <%= friend.userName %>
                        <input type="submit" value="View Profile" class="btn btn-secondary btn-sm"/>
                    </li>
                </form>
                <% } %>
            </ul>
        </div>

        <div class="section achievements-list">
            <h2>Achievements</h2>
            <ul>
                <% for (Achievement achievement : achievementList) { %>
                <li>
                    <%= AchievementEnum.intToString(achievement.achievementId) %>, Achieved on: <%= achievement.achievedAt.format(formatter) %>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>