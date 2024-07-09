<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Multiple Choice Question</title>
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
            margin-inline: 10px;
        }

        input[type="number"],
        input[type="checkbox"],
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

        .option-group {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .option-group label {
            margin: 0 10px 0 5px;
        }

        .option-group input[type="text"] {
            flex-grow: 1;
        }
    </style>
</head>
<% int i = (int) request.getAttribute("Nquestion");
    request.setAttribute("Nquestion", i + 1); %>
<body>
<h1>Create Question # <%= i %></h1>
<form action="saveQuestion.jsp" method="post">
    <label for="question">Question:</label><br>
    <input type="text" id="question" name="question" size="100"><br><br>

    <div class="option-group">
        <input type="radio" id="correctAnswer1" name="correctAnswer" value="1" >
        <label for="correctAnswer1">Answer #1:</label>
        <input type="text" id="option1" name="option1">
    </div>
    <div class="option-group">
        <input type="radio" id="correctAnswer2" name="correctAnswer" value="2">
        <label for="correctAnswer2">Answer #2:</label>
        <input type="text" id="option2" name="option2">
    </div>
    <div class="option-group">
        <input type="radio" id="correctAnswer3" name="correctAnswer" value="3">
        <label for="correctAnswer3">Answer #3:</label>
        <input type="text" id="option3" name="option3">
    </div>
    <div class="option-group">
        <input type="radio" id="correctAnswer4" name="correctAnswer" value="4">
        <label for="correctAnswer4">Answer #4:</label>
        <input type="text" id="option4" name="option4">
    </div><br>

    <input type="submit" value="Create Question">
</form>
</body>
</html>
