<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin User Profile</title>
</head>
<body>
<h2>Create Announcement</h2>
<form action="AdminActionServlet" method="post">
    <input type="hidden" name="action" value="createAnnouncement">
    Announcement Message: <input type="text" name="announcementMessage"><br>
    <input type="submit" value="Create Announcement">
</form>

<h2>Remove User Accounts</h2>
<form action="AdminActionServlet" method="post">
    <input type="hidden" name="action" value="removeUser">
    <!-- Display list of users with checkboxes -->
    <!-- Example: <input type="checkbox" name="userIds" value="userId"> User Name -->
    <input type="submit" value="Remove Selected Users">
</form>

<h2>Remove Quizzes</h2>
<form action="AdminActionServlet" method="post">
    <input type="hidden" name="action" value="removeQuiz">
    <!-- Display list of quizzes with checkboxes -->
    <!-- Example: <input type="checkbox" name="quizIds" value="quizId"> Quiz Name -->
    <input type="submit" value="Remove Selected Quizzes">
</form>

<h2>Clear Quiz History</h2>
<form action="AdminActionServlet" method="post">
    <input type="hidden" name="action" value="clearQuizHistory">
    <!-- Dropdown to select quiz -->
    <select name="quizId">
        <!-- Options populated dynamically from database -->
        <!-- Example: <option value="quizId">Quiz Name</option> -->
    </select><br>
    <input type="submit" value="Clear Quiz History">
</form>

<h2>Promote User Accounts</h2>
<form action="AdminActionServlet" method="post">
    <input type="hidden" name="action" value="promoteUser">
    <!-- Display list of users with checkboxes -->
    <!-- Example: <input type="checkbox" name="userIds" value="userId"> User Name -->
    <input type="submit" value="Promote Selected Users to Admin">
</form>

<h2>Site Statistics</h2>
<!-- Display site statistics -->
<p>Number of Users: <!-- Retrieve from backend --></p>
<p>Number of Quizzes Taken: <!-- Retrieve from backend --></p>
</body>
</html>
