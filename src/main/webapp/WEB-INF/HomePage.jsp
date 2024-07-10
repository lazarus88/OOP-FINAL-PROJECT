<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Mail" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Achievement" %>
<%@ page import="org.example.oopdefaultkgb.Enum.AchievementEnum" %>
<%
  // Assuming "mails" is a request attribute containing a list of Mail objects
  List<Mail> mails = (List<Mail>) request.getAttribute("mails");
  List<User> senderUsers = (List<User>) request.getAttribute("senderUsers");
  List<Achievement> achievements = (List<Achievement>) request.getAttribute("achievements");
  // Formatter for displaying the date
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  int userId = ((User) request.getAttribute("currentUser")).id;
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quiz Homepage</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #121212;
      color: #ffffff;
    }
    .container {
      margin-top: 20px;
    }
    .card {
      margin-bottom: 20px;
      background-color: #1e1e1e;
      border: none;
    }
    .card-header {
      background-color: #333333;
      color: white;
      text-align: center;
    }
    .list-group-item {
      background-color: #1e1e1e;
      border: none;
      color: white;
    }
    .badge {
      position: absolute;
      top: 50%;
      right: 25px; /* Adjust this value as needed */
      transform: translateY(-50%);
    }
    .scrollable-list {
      max-height: 200px;
      overflow-y: auto;
    }
    .navbar-dark .navbar-brand {
      color: #ffffff;
    }
    .navbar-dark .nav-link {
      color: #ffffff;
    }
    .navbar {
      background-color: #333333;
    }
    .logout-button {
      background-color: transparent;
      border: none;
      color: #ffffff;
      text-decoration: underline;
      cursor: pointer;
      padding: 0;
    }
    .logout-button:hover {
      color: #ff4d4d;
    }
    .btn-accept, .btn-reject {
      margin-top: 10px;
    }
    .message-content {
      padding: 10px;
      border: 1px solid #444444;
      border-radius: 5px;
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark">
  <a class="navbar-brand" href="#">Quiz Hub</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <form id="logoutForm" action="logout" method="POST" style="display: inline;">
          <button class="nav-link logout-button" type="submit">Logout</button>
        </form>
      </li>
    </ul>
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <form id="mailForm" action="mail-servlet" method="GET" style="display: inline;">
          <input type="hidden" name="userId" value="<%=userId%>">
          <button class="nav-link btn btn-link" type="submit">Inbox</button>
        </form>
      </li>
      <li class="nav-item">
        <form id="showQuizzesForm" action="ShowQuizServlet" method="GET" target="_blank" style="display: inline;">
          <input type="hidden" name="userId" value="<%= request.getAttribute("userId") != null ? (int) request.getAttribute("userId") : request.getParameter("userId") %>">
          <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") != null ? (int) request.getAttribute("quizId") : request.getParameter("quizId") %>">
          <button class="nav-link btn btn-link" type="submit">Quizzes</button>
        </form>
      </li>
      <li class="nav-item">
        <form id="profileForm" action="user-profile-servlet" method="POST" style="display: inline;">
          <input type="hidden" name="userId" value="<%= request.getAttribute("userId") != null ? (int) request.getAttribute("userId") : request.getParameter("userId") %>">
          <a class="nav-link" href="user-profile-servlet" onclick="document.getElementById('profileForm').submit(); return false;">Profile</a>
        </form>
      </li>
    </ul>
  </div>
</nav>

<div class="container">
  <h1 class="text-center mb-4">Welcome to Quiz Hub</h1>

  <!-- Popular Quizzes -->
  <div class="card">
    <div class="card-header">Popular Quizzes</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Quiz 1 <span class="badge badge-primary">Popular</span></li>
      <li class="list-group-item">Quiz 2 <span class="badge badge-primary">Popular</span></li>
      <li class="list-group-item">Quiz 3 <span class="badge badge-primary">Popular</span></li>
    </ul>
  </div>

  <!-- Recently Created Quizzes -->
  <div class="card">
    <div class="card-header">Recently Created Quizzes</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">New Quiz 1 <span class="badge badge-info">New</span></li>
      <li class="list-group-item">New Quiz 2 <span class="badge badge-info">New</span></li>
      <li class="list-group-item">New Quiz 3 <span class="badge badge-info">New</span></li>
    </ul>
  </div>

  <!-- User's Recent Quiz Taking Activities -->
  <div class="card">
    <div class="card-header">Your Recent Quiz Taking Activities</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Took Quiz 1 <span class="badge badge-success">Completed</span></li>
      <li class="list-group-item">Took Quiz 2 <span class="badge badge-success">Completed</span></li>
    </ul>
  </div>

  <!-- User's Recent Quiz Creating Activities -->
  <div class="card" id="quizCreatingActivitiesCard" style="display: none;">
    <div class="card-header">Your Recent Quiz Creating Activities</div>
    <ul class="list-group list-group-flush" id="quizCreatingActivitiesList">
      <!-- Items will be added dynamically -->
    </ul>
  </div>

  <!-- User's Achievements -->
  <div class="card">
    <div class="card-header">Your Achievements</div>
    <ul class="list-group list-group-flush scrollable-list">
      <%
        if (achievements != null && !achievements.isEmpty()) {
          for (Achievement achievement : achievements) {
      %>
      <li class="list-group-item">
        <%= AchievementEnum.intToString(achievement.achievementId) %>
        <br>
        <small>Achieved <%= achievement.achievedAt.format(formatter) %></small>
      </li>
      <%
        }
      } else {
      %>
      <li class="list-group-item">No Achievements</li>
      <%
        }
      %>
    </ul>
  </div>

  <!-- User's Messages -->
  <div class="card">
    <div class="card-header">Messages</div>
    <ul class="list-group list-group-flush scrollable-list">
      <%
        if (mails != null && !mails.isEmpty()) {
          int index = 0;
          for (Mail mail : mails) {
            String messageType;
            String messageContent = "";
            boolean showButtons = true;
            if (mail.getMailTypeId() == 1) {
              messageType = "Challenge";
              messageContent = senderUsers.get(index).getUserName() + " sent you a challenge in " + mail.getMessage();
            } else if (mail.getMailTypeId() == 0) {
              messageType = "Friend Request";
              messageContent = senderUsers.get(index).getUserName() + " sent you a friend request";
              showButtons = true;
            } else {
              messageType = "Note";
              messageContent = senderUsers.get(index).getUserName() + " sent you a note: " + mail.getMessage();
              showButtons = false;
            }
            String badgeClass = (mail.getMailTypeId() == 1) ? "badge-danger" : "badge-info";
      %>
      <li class="list-group-item">
        <div class="message-content">
          <p><%= messageContent %></p>
          <% if (showButtons) { %>
          <div>
            <button class="btn btn-success btn-accept" onclick="handleResponse('<%= mail.getId() %>', 'accept')">Accept</button>
            <button class="btn btn-danger btn-reject" onclick="handleResponse('<%= mail.getId() %>', 'reject')">Reject</button>
          </div>
          <% } %>
          <span class="badge <%= badgeClass %>"><%= messageType %></span>
        </div>
      </li>
      <%
          index++;
        }
      } else {
      %>
      <li class="list-group-item">No messages</li>
      <%
        }
      %>
    </ul>
  </div>

  <!-- Friends' Recent Activities -->
  <div class="card">
    <div class="card-header">Friends' Recent Activities</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">
        <a href="#">Friend 1</a> took <a href="#">Quiz A</a> <span class="badge badge-success">Completed</span>
      </li>
      <li class="list-group-item">
        <a href="#">Friend 2</a> created <a href="#">Quiz B</a> <span class="badge badge-info">New</span>
      </li>
      <li class="list-group-item">
        <a href="#">Friend 3</a> earned <a href="#">Achievement X</a> <span class="badge badge-warning">New</span>
      </li>
    </ul>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
  $(document).ready(function() {
    // Sample data: user's recent quiz creating activities
    var userQuizCreatingActivities = [
      'Created Quiz 1',
      'Created Quiz 2'
    ];

    // Check if user has created any quizzes
    if (userQuizCreatingActivities.length > 0) {
      var list = $('#quizCreatingActivitiesList');
      userQuizCreatingActivities.forEach(function(activity) {
        list.append('<li class="list-group-item">' + activity + ' <span class="badge badge-info">New</span></li>');
      });
      $('#quizCreatingActivitiesCard').show();
    }
  });

  function handleResponse(mailId, action) {
    $.ajax({
      url: 'handle-response-servlet',
      type: 'POST',
      data: {
        mailId: mailId,
        action: action
      },
      success: function(response) {
        // Reload the page or handle the response appropriately
        location.reload();
      },
      error: function(xhr, status, error) {
        console.error(error);
      }
    });
  }
</script>
</body>
</html>
