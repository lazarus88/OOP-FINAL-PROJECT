<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin User Profile</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #1c1e21;
            color: #ffffff;
        }
        .container {
            margin-top: 20px;
        }
        .card {
            margin-bottom: 20px;
            background-color: #2a2d31;
            border: 1px solid #444950;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .card-header {
            background-color: #444950;
            border-bottom: 1px solid #444950;
            border-radius: 8px 8px 0 0;
            color: white;
            font-weight: bold;
        }
        .form-check-label {
            color: #ffffff;
        }
        .btn {
            border-radius: 20px;
            transition: background-color 0.3s ease;
        }
        .btn-primary {
            background-color: #7289da;
            border-color: #7289da;
        }
        .btn-primary:hover {
            background-color: #5b6fbd;
            border-color: #5b6fbd;
        }
        .btn-danger {
            background-color: #ff6b6b;
            border-color: #ff6b6b;
        }
        .btn-danger:hover {
            background-color: #e65c5c;
            border-color: #e65c5c;
        }
        .btn-warning {
            background-color: #f1c40f;
            border-color: #f1c40f;
        }
        .btn-warning:hover {
            background-color: #d4a907;
            border-color: #d4a907;
        }
        .btn-success {
            background-color: #4caf50;
            border-color: #4caf50;
        }
        .btn-success:hover {
            background-color: #43a047;
            border-color: #43a047;
        }
        .mt-5 {
            margin-top: 3rem !important;
        }
        .mb-4 {
            margin-bottom: 1.5rem !important;
        }
        .search-bar-container {
            display: flex;
            justify-content: space-between;
        }
        .search-bar {
            width: 48%;
        }
        .error-message {
            color: #ff6b6b;
            font-weight: bold;
            margin-bottom: 10px;
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
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Admin Dashboard</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form id="logoutForm" action="logout" method="POST" style="display: inline;">
                    <button class="logout-button" type="submit">Logout</button>
                </form>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center mb-4">Admin Dashboard</h1>

    <div class="card mb-4">
        <div class="card-header">Site Statistics</div>
        <div class="card-body">
            <p>Number of Users: <span id="numberOfUsers">...</span></p>
            <p>Number of Quizzes Taken: <span id="numberOfQuizzesTaken">...</span></p>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">Create Announcement</div>
        <div class="card-body">
            <form action="AdminActionServlet" method="post">
                <input type="hidden" name="action" value="createAnnouncement">
                <div class="form-group">
                    <label for="announcementMessage">Announcement Message:</label>
                    <input type="text" class="form-control" id="announcementMessage" name="announcementMessage">
                </div>
                <button type="submit" class="btn btn-primary">Create Announcement</button>
            </form>
        </div>
    </div>

    <div class="search-bar-container mb-4">
        <div class="search-bar card">
            <div class="card-header">Promote User</div>
            <div class="card-body">
                <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <div class="error-message"><%= errorMessage %></div>
                <%
                    }
                %>
                <form action="AdminActionServlet" method="post">
                    <input type="hidden" name="action" value="promoteUser">
                    <div class="form-group">
                        <label for="promoteUsername">Username:</label>
                        <input type="text" class="form-control" id="promoteUsername" name="username">
                    </div>
                    <button type="submit" class="btn btn-success">Promote User</button>
                </form>
            </div>
        </div>

        <div class="search-bar card">
            <div class="card-header">Remove User</div>
            <div class="card-body">
                <%
                    if (errorMessage != null) {
                %>
                <div class="error-message"><%= errorMessage %></div>
                <%
                    }
                %>
                <form action="AdminActionServlet" method="post">
                    <input type="hidden" name="action" value="removeUser">
                    <div class="form-group">
                        <label for="removeUsername">Username:</label>
                        <input type="text" class="form-control" id="removeUsername" name="username">
                    </div>
                    <button type="submit" class="btn btn-danger">Remove User</button>
                </form>
            </div>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">Remove Quizzes</div>
        <div class="card-body">
            <form action="AdminActionServlet" method="post">
                <input type="hidden" name="action" value="removeQuiz">
                <div class="form-group">
                    <label for="removeQuizId">Quiz ID:</label>
                    <input type="text" class="form-control" id="removeQuizId" name="quizId">
                </div>
                <button type="submit" class="btn btn-danger">Remove Quiz</button>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
