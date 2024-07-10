<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Result</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .score-card {
            margin-top: 20px;
        }
        .score {
            font-size: 2em;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">Quiz Result</h1>
    <div class="card score-card">
        <div class="card-body text-center">
            <p>You answered <strong><%= request.getAttribute("correctCount") %></strong> out of <strong><%= request.getAttribute("totalQuestions") %></strong> questions correctly.</p>
            <div class="score">
                Your Score: <%= (int) ((Integer) request.getAttribute("correctCount") * 100.0 / (Integer) request.getAttribute("totalQuestions")) %>%
            </div>
        </div>
    </div>
    <div class="mt-3 text-center">
        <a href="ShowQuizServlet" class="btn btn-primary">Take Another Quiz</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
