<%@ page import="org.example.oopdefaultkgb.EntityDTO.Quiz" %>
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
        bla {
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
            text-align: center;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            text-align: center;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .option-group {
            display: none;
            align-items: center;
            margin-bottom: 10px;
        }

        .option-group label {
            margin: 0 10px 0 5px;
        }

        .option-group input[type="text"] {
            flex-grow: 1;
        }

        .radio-group input[type="radio"] {
            margin-right: 5px;
        }

        .radio-group label {
            display: inline-block;
            margin-right: 20px;
            vertical-align: middle;
        }

        .hidden {
            display: none;
        }
    </style>
    <script>
        function updateContent() {
            var radioButtons = document.querySelectorAll('input[name="questionType"]');
            var selectedType = null;

            // Iterate over each radio button to find the selected one
            for (var i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].checked) {
                    selectedType = radioButtons[i].value;
                    break;
                }
            }

            var textContent = document.getElementById("textContent");
            var multipleChoiceContent = document.getElementById("multipleChoiceContent");
            var fillBlankContent = document.getElementById("fillBlankContent");
            var pictureContent = document.getElementById("pictureContent");

            // Hide all content initially
            textContent.classList.add("hidden");
            textContent.classList.remove("radio-group");
            multipleChoiceContent.classList.add("hidden");
            multipleChoiceContent.classList.remove("radio-group");
            fillBlankContent.classList.add("hidden");
            fillBlankContent.classList.remove("radio-group");
            pictureContent.classList.add("hidden");
            pictureContent.classList.remove("radio-group");
            // Show the content based on selected type
            if (selectedType === "Question-Response") {
                textContent.classList.remove("hidden");
                textContent.classList.add("radio-group");
            } else if (selectedType === "multipleChoice") {
                multipleChoiceContent.classList.remove("hidden");
                multipleChoiceContent.classList.add("radio-group");
            } else if (selectedType === "fillBlank") {
                fillBlankContent.classList.remove("hidden");
                fillBlankContent.classList.add("radio-group");
            }else if (selectedType === "pictureResponse") {
                pictureContent.classList.remove("hidden");
                pictureContent.classList.add("radio-group");
            }
        }
    </script>
</head>
<body>
<%
    int i = (int) request.getAttribute("Nquestion");
    Quiz quiz = (Quiz) request.getAttribute("quiz");
    session.setAttribute("quiz", quiz);
%>
    <form>
        <h1>Create Question # <%= i %></h1>
        <h2>Select Question Type</h2>
        <label>
            <input type="radio" name="questionType" value="Question-Response" onchange="updateContent()"> Question-Response
        </label>
        <br>
        <label>
            <input type="radio" id="multiple" name="questionType" value="multipleChoice" onchange="updateContent()"> Multiple Choice
        </label>
        <br>
        <label>
            <input type="radio" name="questionType" value="fillBlank" onchange="updateContent()"> Fill in the Blank
        </label>
        <br>
        <label>
            <input type="radio" name="questionType" value="pictureResponse" onchange="updateContent()"> Picture Response
        </label>
    </form>
    <form id="textContent" class="hidden"  action="CreateMultipleChoiceQuestionServlet" method="post">
        <label for="question">Question:</label><br>
        <input type="text" id="question" name="question" size="100" required><br><br>
        <h3>Correct Answers</h3>
        <label for="textCorrectAnswer1">Answer #1:</label>
        <input type="text" id="textCorrectAnswer1" name="textCorrectAnswer1" required><br>
        <label for="textCorrectAnswer2">Answer #2:</label>
        <input type="text" id="textCorrectAnswer2" name="textCorrectAnswer2" ><br>
        <label for="textCorrectAnswer3">Answer #3:</label>
        <input type="text" id="textCorrectAnswer3" name="textCorrectAnswer3" ><br>
        <label for="textCorrectAnswer4">Answer #4:</label>
        <input type="text" id="textCorrectAnswer4" name="textCorrectAnswer4" ><br>
        <input type="hidden" name="type" value="<%= 0 %>">
        <input type="hidden" name="Nquestion" value="<%= i+1 %>">
        <input type="submit" value="Create Question">
    </form>
    <form id="multipleChoiceContent" class="hidden"  action="CreateMultipleChoiceQuestionServlet" method="post">
        <label for="question">Question:</label><br>
        <input type="text" id="question" name="question" size="100" required><br><br>
        <h3>Multiple Choice Question</h3>
        <input type="radio" id="correctAnswer1" name="correctAnswer" value="1" required>
        <label for="correctAnswer1">Answer #1:</label>
        <input type="text" id="option1" name="option1" required><br>
        <input type="radio" id="correctAnswer2" name="correctAnswer" value="2">
        <label for="correctAnswer2">Answer #2:</label>
        <input type="text" id="option2" name="option2" required><br>
        <input type="radio" id="correctAnswer3" name="correctAnswer" value="3">
        <label for="correctAnswer3">Answer #3:</label>
        <input type="text" id="option3" name="option3" required><br>
        <input type="radio" id="correctAnswer4" name="correctAnswer" value="4">
        <label for="correctAnswer4">Answer #4:</label>
        <input type="text" id="option4" name="option4" required><br>
        <input type="hidden" name="type" value="<%= 1 %>">
        <input type="hidden" name="Nquestion" value="<%= i+1 %>">
        <input type="submit" value="Create Question">
    </form>
    <form id="fillBlankContent" class="hidden" action="CreateMultipleChoiceQuestionServlet" method="post">
        <label for="question">Question:</label><br>
        <input type="text" id="question" name="question" size="100" required><br><br>
        <h3>Correct Answers</h3>
        <label for="fillCorrectAnswer1">Answer #1:</label>
        <input type="text" id="fillCorrectAnswer1" name="fillCorrectAnswer1" required><br>
        <label for="fillCorrectAnswer2">Answer #2:</label>
        <input type="text" id="fillCorrectAnswer2" name="fillCorrectAnswer2"><br>
        <label for="fillCorrectAnswer3">Answer #3:</label>
        <input type="text" id="fillCorrectAnswer3" name="fillCorrectAnswer3" ><br>
        <label for="fillCorrectAnswer4">Answer #4:</label>
        <input type="text" id="fillCorrectAnswer4" name="fillCorrectAnswer4" ><br>
        <input type="hidden" name="type" value="<%= 2 %>">
        <input type="hidden" name="Nquestion" value="<%= i+1 %>">
        <input type="submit" value="Create Question">
    </form>
    <form id="pictureContent" class="hidden" action="CreateMultipleChoiceQuestionServlet" method="post">
        <label for="question">Question:</label><br>
        <input type="text" id="question" name="question" size="100" required><br><br>
        <h3>Correct Answers</h3>
        <label for="picCorrectAnswer1">Answer #1:</label>
        <input type="text" id="picCorrectAnswer1" name="picCorrectAnswer1" required><br>
        <label for="picCorrectAnswer2">Answer #2:</label>
        <input type="text" id="picCorrectAnswer2" name="picCorrectAnswer2"><br>
        <label for="picCorrectAnswer3">Answer #3:</label>
        <input type="text" id="picCorrectAnswer3" name="picCorrectAnswer3" ><br>
        <label for="picCorrectAnswer4">Answer #4:</label>
        <input type="text" id="picCorrectAnswer4" name="picCorrectAnswer4" ><br>
        <input type="hidden" name="type" value="<%= 3 %>">
        <input type="hidden" name="Nquestion" value="<%= i+1 %>">
        <input type="submit" value="Create Question">
    </form>

</body>
</html>
