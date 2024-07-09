<%@ page import="org.example.oopdefaultkgb.EntityDTO.Mail" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Requests</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      margin: 0;
      padding: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
    }

    h1 {
      font-size: 2em;
      color: #333;
      text-align: center;
      margin-bottom: 20px;
    }

    .container {
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
      max-width: 800px;
      width: 100%;
      margin-bottom: 20px;
    }

    ul {
      list-style-type: none;
      padding: 0;
    }

    li {
      font-size: 1.2em;
      padding: 10px;
      border-bottom: 1px solid #ccc;
    }

    li:last-child {
      border-bottom: none;
    }

    .no-items {
      color: #888;
      font-style: italic;
    }
  </style>
</head>
<body>

<%!
  // Function to display list of Mail objects
  void displayMailList(List<Mail> mails, JspWriter out) throws IOException {
  if (mails != null) {
  for (Mail mail : mails) {
  out.println("<li>" + mail + "</li>");
  }
  } else {
  out.println("<li>No items available.</li>");
  }
  }
%>

<h1>Friend Requests</h1>
<ul>
  <%

    List<Mail> friendRequests = (List<Mail>) request.getAttribute("friendRequests");
    displayMailList(friendRequests, out);
  %>
</ul>

<h1>Challenge Requests</h1>
<ul>
  <%
    List<Mail> challengeRequests = (List<Mail>) request.getAttribute("challengeRequests");
    displayMailList(challengeRequests, out);
  %>
</ul>

<h1>Notes</h1>
<ul>
  <%
    List<Mail> notes = (List<Mail>) request.getAttribute("notes");
    displayMailList(notes, out);
  %>
</ul>

</body>
</html>
