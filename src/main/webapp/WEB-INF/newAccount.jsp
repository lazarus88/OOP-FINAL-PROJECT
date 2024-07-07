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
<h1 style = "text-align:center;">Create new account</h1>
<p>Enter your account details</p>
<form action="CreationServlet" method="post">
    Full Name: <input  type="text" name="fullName"/><br>
    User Name: <input  type="text" name="name"/><br>
    Password: <input  type="text" name="pass"/><br>
    <input id="UserName" type="submit" value="Create"><br>
    <a href="forward?path=LogIn">Log in</a><br>
    <%--</form>--%>
    <%--<form action="CreationServlet" method="post">--%>
    <%--    <input type="submit" value="Create Account">--%>
    <%--</form>--%>

</body>
</html>
