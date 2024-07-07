<%@ page import="org.w3c.dom.Document" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<style>
    body {background-color: lightcoral}
    h1 {color: indigo}
</style>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
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
<h1 style = "text-align:center;">Welcome to Quiz</h1>
<p>Please log in.</p>
<form action="LoginServlet" method="post">
    User Name: <input  type="text" name="name"/><br>
    Password: <input  type="text" name="pass"/><br>
    <input id="UserName" type="submit" value="Login"><br>
    <a href="forward?path=newAccount">Create new account</a><br>
    <%--</form>--%>
    <%--<form action="CreationServlet" method="post">--%>
    <%--    <input type="submit" value="Create Account">--%>
    <%--</form>--%>
    <a href="user-profile-servlet">Profile</a><br>
    <a href="forward?path=OtherUserProfile">Other User Profile</a><br>

</body>
</html>


