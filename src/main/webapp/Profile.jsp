<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
<h1>User Profile</h1>
<p>Username: <%= request.getAttribute("username") %></p>
<p>Age: <%= request.getAttribute("age") %></p>
<p>Email: <%= request.getAttribute("email") %></p>
</body>
</html>