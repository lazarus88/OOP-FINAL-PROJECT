<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="org.example.oopdefaultkgb.Enum.AchievementEnum" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.*" %>
<%@ page import="java.util.Map" %>
<%
  List<Mail> mails = (List<Mail>) request.getAttribute("mails");
  List<User> senderUsers = (List<User>) request.getAttribute("senderUsers");
  List<Achievement> achievements = (List<Achievement>) request.getAttribute("achievements");
  List<Quiz> popularQuizList = (List<Quiz>) request.getAttribute("quizList");
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  int userId = ((User) request.getAttribute("currentUser")).id;
  session.setAttribute("userId", userId);
  List<Quiz> recentQuizList = (List<Quiz>) request.getAttribute("recentlyCreated");
  Map<HistoryQuiz, Quiz> historyQuiz = (Map<HistoryQuiz, Quiz>) request.getAttribute("historyQuiz");
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
      position: relative;
      padding-right: 100px;
    }
    .badge {
      position: absolute;
      top: 50%;
      right: 25px;
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
          <input type="hidden" name="userId" value="<%= userId %>">
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
    <ul class="list-group list-group-flush scrollable-list">
      <%
        if (popularQuizList != null && !popularQuizList.isEmpty()) {
          for (Quiz quiz : popularQuizList) {
      %>
      <li class="list-group-item">
        <a href="take-quiz-servlet?quizId=<%=quiz.id%>" target="_blank"><%= quiz.quizName %></a>
        <span class="badge badge-primary">Popular</span>
      </li>
      <%
        }
      } else {
      %>
      <li class="list-group-item">No popular quizzes available</li>
      <%
        }
      %>
    </ul>
  </div>

  <!-- Recently Created Quizzes -->
  <div class="card">
    <div class="card-header">Recently Created Quizzes</div>
    <ul class="list-group list-group-flush scrollable-list">
      <%
        if (recentQuizList != null && !recentQuizList.isEmpty()) {
          for (Quiz quiz : recentQuizList) {
      %>
      <li class="list-group-item">
        <a href="take-quiz-servlet?quizId=<%=quiz.id%>" target="_blank"><%= quiz.quizName %></a>
        <span class="badge badge-primary">New</span>
      </li>
      <%
        }
      } else {
      %>
      <li class="list-group-item">No popular quizzes available</li>
      <%
        }
      %>
    </ul>
  </div>

  <!-- User's Recent Quiz Taking Activities -->
  <div class="card">
    <div class="card-header">Your Recent Quiz Taking Activities</div>
    <ul class="list-group list-group-flush scrollable-list">
      <%
        if (historyQuiz != null && !historyQuiz.isEmpty()) {
          for (Map.Entry<HistoryQuiz, Quiz> entry : historyQuiz.entrySet()) {
            HistoryQuiz history = entry.getKey();
            Quiz quiz = entry.getValue();
      %>
      <li class="list-group-item">
        <a href="take-quiz-servlet?quizId=<%=quiz.id%>" target="_blank"><%= quiz.quizName %></a>
        <span class="badge badge-success">Completed</span>
      </li>
      <%
        }
      } else {
      %>
      <li class="list-group-item">No recent quiz taking activities</li>
      <%
        }
      %>
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
      <li class="list-group-item" data-mail-id="<%= mail.getId() %>">
        <div class="message-content">
          <p><%= messageContent %></p>
          <% if (showButtons) { %>
          <div>
            <button class="btn btn-success btn-accept" data-sender-id="<%= mail.getSenderUserId() %>" data-mail-type-id="<%= mail.getMailTypeId() %>" data-message="<%= mail.getMessage() %>">Accept</button>
            <button class="btn btn-danger btn-reject" data-sender-id="<%= mail.getSenderUserId() %>" data-mail-type-id="<%= mail.getMailTypeId() %>" data-message="<%= mail.getMessage() %>">Reject</button>
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

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
  $(document).ready(function() {
    console.log('jQuery version:', $.fn.jquery);

    // Event listener for accept buttons
    $('.btn-accept').click(function() {
      console.log('Accept button clicked');
      var parentLi = $(this).closest('li');
      handleResponse($(this).data('sender-id'), 'accept', $(this).data('mail-type-id'), $(this).data('message'), parentLi);
    });

    // Event listener for reject buttons
    $('.btn-reject').click(function() {
      console.log('Reject button clicked');
      var parentLi = $(this).closest('li');
      handleResponse($(this).data('sender-id'), 'reject', $(this).data('mail-type-id'), $(this).data('message'), parentLi);
    });
  });

  function handleResponse(mailSenderId, action, mailTypeId, message, parentLi) {
    console.log('handleResponse called with:', {
      mailSenderId: mailSenderId,
      action: action,
      mailTypeId: mailTypeId,
      message: message
    });
    $.ajax({
      url: "<%= request.getContextPath() %>/receive-mail-servlet",
      type: "POST",
      data: {
        mailTypeId: mailTypeId,
        mailSenderId: mailSenderId,
        action: action,
        userId: <%= userId %>,
        message: message
      },
      success: function(response) {
        console.log('AJAX success:', response);
        // Remove the parent <li> element
        parentLi.fadeOut(10, function() {
          $(this).remove();
        });
      },
      error: function(xhr, status, error) {
        console.error('AJAX error:', error);
      }
    });
  }
</script>
</body>
</html>
