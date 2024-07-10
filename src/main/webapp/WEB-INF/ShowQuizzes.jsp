<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Quiz" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    // Assuming "quizzes" is a request attribute containing a list of Quiz objects
    List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
    // Formatter for displaying the date
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Quizzes</title>
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
            float: right;
        }
        .scrollable-list {
            max-height: 200px;
            overflow-y: auto;
        }
        .btn-group {
            float: right;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">Available Quizzes</h1>

    <!-- Quizzes List -->
    <div class="card">
        <div class="card-header">Quizzes</div>
        <ul class="list-group list-group-flush">
            <%
                if (quizzes != null && !quizzes.isEmpty()) {
                    for (Quiz quiz : quizzes) {
            %>
            <li class="list-group-item">
                <%= quiz.getQuizName() %>
                <br>
                <small>Created by: <%= quiz.getCreatorUserId() %></small>
                <div class="btn-group">
                    <form action="take-quiz-servlet" method="GET" style="display: inline;">
                        <% session.setAttribute("quizId",quiz.id);%>
                        <button type="submit" class="btn btn-primary btn-sm">Take Quiz</button>
                    </form>
                </div>
            </li>
            <%
                }
            } else {
            %>
            <li class="list-group-item">No quizzes available</li>
            <%
                }
            %>
        </ul>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
