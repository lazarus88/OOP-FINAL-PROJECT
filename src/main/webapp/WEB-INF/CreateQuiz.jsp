<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Parameters</title>
    <style>
        body {
            font-size: 18px; /* Increases the base font size for the entire body */
        }

        h1 {
            font-size: 2em; /* Increases the font size of the header */
        }

        label {
            font-size: 2em; /* Increases the font size of labels */
        }

        input[type="number"],
        input[type="checkbox"],
        input[type="submit"],
        button {
            font-size: 2em; /* Increases the font size of form elements */
        }
    </style>
</head>
<body>
<h1>Quiz Parameters</h1>
<form action="CreateQuestionsServlet" method="post">
    <label for="numQuestions">Number of Questions:</label>
    <input type="number" id="numQuestions" name="numQuestions" min="1" max="50" value="10"><br><br>

    <label>Options:</label><br>
    <input type="checkbox" id="1" name="isRandom" value="true">
    <label for="1">Random order of qustions</label><br>
    <input type="checkbox" id="2" name="isOnePage" value="true">
    <label for="2">One page(default multiple page)</label><br>
    <input type="checkbox" id="3" name="isImmediate" value="true">
    <label for="3">Immediate score</label><br>
    <input type="checkbox" id="4" name="isPracticeEnabled" value="true">
    <label for="4">Practice is enabled</label><br>
    <input type="hidden" name="userId" value="<%= request.getParameter("userId") %>">

    <input type="submit" value="Create Quiz">
</form>

<script>
    function submitForm() {
        const form = document.getElementById('quizParametersForm');
        const formData = new FormData(form);

        const parameters = {};
        formData.forEach((value, key) => {
            if (!parameters[key]) {
                parameters[key] = {};
            }
            alert(value)
            parameters[key].push(value);
        });
        <%--$.ajax({--%>
        <%--    url: "<%= request.getContextPath() %>/message-servlet",--%>
        <%--    type: "POST",--%>
        <%--    data: {--%>
        <%--        isRandom: parameters[key]--%>
        <%--        message: "Your message content here"--%>
        <%--    },--%>

      //  console.log('Selected Quiz Parameters:', parameters);

      //  alert('Quiz Parameters have been set! Check the console for details.');
    }
</script>
</body>
</html>
