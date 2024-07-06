<%--
  Created by IntelliJ IDEA.
  User: kkerd
  Date: 25.05.2024
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>inUse</title>
    <h1>The Name <%=request.getParameter("name")%> is Already In Use</h1>
    <p> Please enter another name and password.</p>
    <form action="CreationServlet" method="post">
        User Name: <input type="text" name="name"/><br>
        Password: <input type="text" name="pass"/>
        <input type="submit" value="Login">
    </form>
</head>
<body>

</body>
</html>
