<%@ page import="org.w3c.dom.Document" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome dawdadwa</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("button").click(function(){
                $("#UserName").hide();
            });
        });
    </script>
</head>
<body>
<h1>Welcome to Quiz</h1>
<%--<p>Please log in.</p>--%>
<%--User Name: <input id="UserName" type="text" name="name"/><br>--%>
<%--Password: <input id="passWord" type="text" name="pass"/>--%>
<%--<form action="LoginServlet" method="post">--%>
    <input id="UserName" type="submit" value="Login">
<%--</form>--%>
<%--<form action="CreationServlet" method="post">--%>
<%--    <input type="submit" value="Create Account">--%>
<%--</form>--%>
<a href="user-profile-servlet">Profile</a>
</body>
</html>
