<%@ page import="org.w3c.dom.Document" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #bac1f8;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            font-size: 2em;
            color: #000109;
            text-align: center;
        }

        .form-container {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .form-container input {
            font-size: 1em;
            margin: 10px;
            padding: 10px;
            width: calc(100% - 22px);
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .form-container label {
            font-size: 1.2em;
            margin-bottom: 5px;
            display: block;
        }

        .form-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .form-container input[type="submit"]:hover {
            background-color: #45a049;
        }

        .form-container a {
            font-size: 1em;
            color: #0000EE;
            text-decoration: none;
            margin-top: 10px;
        }

        .form-container a:hover {
            text-decoration: underline;
        }

        .red-text {
            color: red;
            text-align: center;
            margin-bottom: 10px;
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
<div class="form-container">
    <p class="red-text">This userName is already in use</p>
    <form action="CreationServlet" method="post">
        <label for="Fullname">Full Name:</label>
        <input id="Fullname" type="text" name="fullName" required/><br>
        <label for="User">Username:</label>
        <input id="User" type="text" name="name" required/><br>
        <label for="passWord">Password:</label>
        <input id="passWord" type="password" name="pass" required/><br>
        <input type="submit" value="Create Account">
    </form>
    <a href="forward?path=LogIn">Log In</a><br>
</div>
</body>
</html>
