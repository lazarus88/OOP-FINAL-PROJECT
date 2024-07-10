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
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Admin Dashboard</h1>

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

    <div class="card mb-4">
        <div class="card-header">Remove User Accounts</div>
        <div class="card-body">
            <form action="AdminActionServlet" method="post">
                <input type="hidden" name="action" value="removeUser">

                <button type="submit" class="btn btn-danger mt-3">Remove Selected Users</button>
            </form>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">Remove Quizzes</div>
        <div class="card-body">
            <form action="AdminActionServlet" method="post">
                <input type="hidden" name="action" value="removeQuiz">

                <button type="submit" class="btn btn-danger mt-3">Remove Selected Quizzes</button>
            </form>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">Clear Quiz History</div>
        <div class="card-body">
            <form action="AdminActionServlet" method="post">
                <input type="hidden" name="action" value="clearQuizHistory">
                <div class="form-group">
                    <label for="quizId">Select Quiz:</label>
                    <select class="form-control" id="quizId" name="quizId">
                    </select>
                </div>
                <button type="submit" class="btn btn-warning">Clear Quiz History</button>
            </form>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">Promote User Accounts</div>
        <div class="card-body">
            <form action="AdminActionServlet" method="post">
                <input type="hidden" name="action" value="promoteUser">
                <button type="submit" class="btn btn-success mt-3">Promote Selected Users to Admin</button>
            </form>
        </div>
    </div>

    <div class="card mb-4">
        <div class="card-header">Site Statistics</div>
        <div class="card-body">
            <p>Number of Users:</p>
            <p>Number of Quizzes Taken: </p>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
