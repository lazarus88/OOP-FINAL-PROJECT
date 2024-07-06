<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
<h1>Welcome to Quiz</h1>
<p>Please log in.</p>
<form action="CreationServlet" method="post">
    User Name: <input type="text" name="name"/><br>
    Password: <input type="text" name="pass"/>
    <input type="submit" value="Login">
</form>

</body>
</html>