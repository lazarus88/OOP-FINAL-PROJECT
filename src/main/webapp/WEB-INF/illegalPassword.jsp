<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome dawdadwa</title>
</head>
<body>
<h1>Welcome to Quiz</h1>
<p>Incorrect IserName or Password.</p>
User Name: <input type="text" name="name"/><br>
Password: <input type="text" name="pass"/>
<form action="LoginServlet" method="post">
    <input type="submit" value="Login">
</form>
<form action="CreationServlet" method="post">
    <input type="submit" value="Create Account">
</form>
</body>
</html>
