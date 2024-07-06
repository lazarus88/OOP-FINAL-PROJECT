<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <h1>Welcome to HomeWork 5</h1>
    <p>Please log in.</p>
    <form action="LoginServlet" method="post">
        User Name: <input type="text" name="name"/><br>
        Password: <input type="text" name="pass"/>
        <input type="submit" value="Login">
    </form>
    <p><a href="newAccount.html">Create New Account</a></p>
</head>
<body>

</body>
</html>