<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Mail" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.Service.MailService" %><%--
  Created by IntelliJ IDEA.
  User: ntati
  Date: 7/5/2024
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<%
  // Retrieve the list from the request attribute
  List<Mail> mails = (List<Mail>) request.getAttribute("AllMails");

  if (mails != null) {
    for (Mail mail : mails) {
%>
<li>
   <%= mail %>
</li>
<%
  }
} else {
%>
<li>No users available.</li>
<%
  }
%>

<br/>
</body>
</html>