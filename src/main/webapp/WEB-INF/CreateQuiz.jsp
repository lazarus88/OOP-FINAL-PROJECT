<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Parameters</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 18px; /* Base font size */
            margin: 0;
            padding: 20px;
            background-color: #f4f4f9;
        }

        h1 {
            font-size: 2em; /* Header font size */
            text-align: center;
            color: #333;
        }

        form {
            max-width: 1000px;
            margin: 0 auto;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            font-size: 1.2em; /* Label font size */
            display: block;
            margin-bottom: 10px;
        }

        input[type="number"],
        input[type="checkbox"],
        input[type="radio"],
        input[type="text"],
        input[type="submit"],
        button {
            font-size: 1em; /* Form elements font size */
            margin-bottom: 20px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .checkbox-group {
            margin-bottom: 20px;
        }

        .checkbox-group label {
            display: inline-block;
            margin-right: 20px;
        }
        .radio-group label {
            display: inline-block;
            margin-right: 20px;
        }

        .radio-group input[type="radio"] {
            margin-right: 5px;
        }

        .radio-group label {
            display: inline-block;
            margin-right: 20px;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<h1>Quiz Parameters</h1>
<form action="CreateQuestionsServlet" method="post" id="quizParametersForm">
    <label for="quizName">Quiz Name:</label>
    <input type="text" id="quizName" name="quizName" required><br><br>

    <label for="numQuestions">Number of Questions:</label>
    <input type="number" id="numQuestions" name="numQuestions" min="1" max="50" value="10"><br><br>

    <label>Options:</label><br>
    <div class="checkbox-group">
        <input type="checkbox" id="1" name="isRandom" value="true">
        <label for="1">Random order of questions</label>
    </div>
    <div class="checkbox-group">
        <input type="checkbox" id="2" name="isOnePage" value="true">
        <label for="2">One page (default multiple page)</label>
    </div>
    <div class="checkbox-group">
        <input type="checkbox" id="3" name="isImmediate" value="true">
        <label for="3">Immediate score</label>
    </div>
    <div class="checkbox-group">
        <input type="checkbox" id="4" name="isPracticeEnabled" value="true">
        <label for="4">Practice is enabled</label>
    </div>
    <input type="hidden" name="userId" value="<%= request.getParameter("userId") %>">

    <input type="submit" value="Create Quiz">

</form>

</body>
</html>
