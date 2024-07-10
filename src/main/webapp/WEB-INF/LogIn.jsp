<%@ page import="org.w3c.dom.Document" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #121212;
            color: #ffffff;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            font-size: 2.5em;
            color: #ffffff;
            text-align: center;
            margin-bottom: 20px;
        }

        .form-container {
            background: #1e1e1e;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            padding: 20px;
            max-width: 400px;
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .form-container input, .form-container label {
            font-size: 1.2em;
            margin: 10px 0;
            width: calc(100% - 22px);
        }

        .form-container input[type="text"],
        .form-container input[type="password"] {
            padding: 10px;
            background-color: #2b2b2b;
            border: 1px solid #555555;
            border-radius: 5px;
            color: #ffffff;
        }

        .form-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 20px;
        }

        .form-container input[type="submit"]:hover {
            background-color: #45a049;
        }

        .form-container a {
            font-size: 1.2em;
            color: #1e90ff;
            text-decoration: none;
            margin-top: 10px;
        }

        .form-container a:hover {
            text-decoration: underline;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("button").click(function() {
                $("#UserName").hide();
            });
        });
    </script>
</head>
<body>
<h1>Welcome to Quiz</h1>
<div class="form-container" >
    <form action = "LoginServlet" method = "post" >
        <label for="User" > Username:</label >
        <input id = "User" type = "text" name = "name" / ><br >
        <label for="passWord" > Password:</label >
        <input id = "passWord" type = "password" name = "pass" / ><br >
        <input type = "submit" value = "Login" >
    </form >
    <a href = "forward?path=newAccount" > Create account </a ><br >
</div >
<form action="ShowQuizServlet" method="post" style="margin-top: 20px;">
    <input type="hidden" name="userId" value="<%= 4 %>">
    <input type="hidden" name="quizId" value="<%= 1 %>">
    <input type="submit" value="Start quiz" class="btn btn-primary"/>
</form>
</body>
</html>
