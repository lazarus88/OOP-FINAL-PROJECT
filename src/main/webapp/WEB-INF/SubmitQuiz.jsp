<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Quiz" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int userId = (int)request.getAttribute("userId");
    Quiz quiz = (Quiz) request.getAttribute("quiz");
    List<User> friends = (List<User>) request.getAttribute("friendList");
%>
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
        .friend-list-box {
            display: none;
            position: absolute;
            top: 50%; /* Adjusted to lift the box up */
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            max-height: 300px;
            overflow-y: auto;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            z-index: 1000;
            padding: 10px;
        }
        .friend-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }
        .scrollable-list {
            max-height: 300px;
            overflow-y: auto;
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
        <button id="challengeFriendBtn" class="btn btn-secondary">Challenge Friend</button>
    </div>
    <div id="friendListBox" class="friend-list-box">
        <ul class="list-group list-group-flush scrollable-list">
            <% for (User friend : friends) { %>
            <li class="list-group-item friend-item">
                <span><%= friend.userName %></span>
                <form action="mail-send-servlet" method="post" class="d-inline">
                    <input type="hidden" name="otherUserId" value="<%= friend.id %>">
                    <input type="hidden" name="userId" value="<%= userId %>">
                    <input type="hidden" name="mailTypeId" value="<%= 1 %>">
                    <input type="hidden" name="action" value="sendChallengeRequest">
                    <input type="hidden" name="quizId" value=<%=quiz.id%>>
                    <button type="submit" class="btn btn-sm btn-success send-challenge-btn">Send Challenge</button>
                </form>
            </li>
            <% } %>
        </ul>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $('#challengeFriendBtn').click(function() {
            $('#friendListBox').toggle();
        });

        $(document).click(function(event) {
            if (!$(event.target).closest('#friendListBox, #challengeFriendBtn').length) {
                $('#friendListBox').hide();
            }
        });
    });
</script>
</body>
</html>
