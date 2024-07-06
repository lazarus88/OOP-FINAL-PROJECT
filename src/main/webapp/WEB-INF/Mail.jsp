<%@ page import="org.example.oopdefaultkgb.EntityDTO.Mail" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Requests</title>
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
