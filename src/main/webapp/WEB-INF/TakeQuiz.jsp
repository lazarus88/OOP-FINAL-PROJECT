<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Question" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Answer" %>
<%
  // Retrieve the questions and answers map from the request attribute
  List<Question> questions = (List<Question>) request.getAttribute("questions");
  Map<Integer, List<Answer>> answersMap = (Map<Integer, List<Answer>>) request.getAttribute("answers");
  session.setAttribute("questions", questions);
  session.setAttribute("quizId", request.getAttribute("quizId"));
  session.setAttribute("userId", request.getAttribute("userId"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quiz</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h1 class="text-center mb-4">Quiz</h1>
  <form action="submit-quiz-servlet" method="POST">
    <%
      if (questions != null && !questions.isEmpty()) {
        for (Question question : questions) {
    %>
    <div class="card mb-3">
      <div class="card-header">
        <%= question.getQuestion() %>
      </div>
      <div class="card-body">
        <%
          List<Answer> answers = answersMap.get(question.getId());
          if (answers != null) {
            for (Answer answer : answers) {
        %>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="question_<%= question.getId() %>" value="<%= answer.getId() %>" id="q<%= question.getId() %>_<%= answer.getId() %>">
          <label class="form-check-label" for="q<%= question.getId() %>_<%= answer.getId() %>">
            <%= answer.getAnswer() %>
          </label>
        </div>
        <%
            }
          }
        %>
      </div>
    </div>
    <%
      }
    } else {
    %>
    <p>No questions available.</p>
    <%
      }
    %>
    <button type="submit" class="btn btn-primary">Submit Quiz</button>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
