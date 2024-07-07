<%@ page import="org.w3c.dom.Document" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <style>
        body {background-color: #bac1f8;}
        h1 {color: #000109; text-align: center;}
        .form-container{
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .form-container input{
            margin: 10px;
        }
        .red-text{
            color: red;
        }
    </style>
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
<div class="form-container">
    <div class="form-container">
        <form action="LoginServlet" method="post">

            <label for="User">Username:</label>
            <input id="User" type="text" name="name"/><br>
            <label for="passWord">Password:</label>
            <input id="passWord" type="password" name="pass"/><br>
            <input type="submit" value="Login">
        </form>
        <a href="forward?path=newAccount">Create account</a><br>
    </div>
</div>
</body>
</html>
