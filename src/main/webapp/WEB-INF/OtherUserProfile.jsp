<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Achievement" %>
<%@ page import="org.example.oopdefaultkgb.Enum.AchievementEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User otherUser = (User) request.getAttribute("otherUser");
  int userId = (int) request.getAttribute("userId");
  List<Achievement> achievements = (List<Achievement>) request.getAttribute("otherUserAchievementList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Other User Profile</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #121212;
      color: #ffffff;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    header {
      background: #333333;
      color: #ffffff;
      width: 100%;
      padding: 20px 0;
      border-bottom: 3px solid #00ffff;
      display: flex;
      justify-content: center;
      align-items: center;
      position: relative;
    }
    header h1 {
      margin: 0;
    }
    header .quiz-icon {
      position: absolute;
      top: 15px;
      left: 15px;
      cursor: pointer;
      width: 40px;
      height: auto;
    }
    .container {
      width: 80%;
      margin: 20px auto;
      padding: 20px;
      background: #1e1e1e;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    .main-content {
      display: flex;
      justify-content: space-between;
      width: 100%;
      align-items: flex-start;
    }
    .section {
      flex: 1;
      margin: 10px;
    }
    .section h2 {
      color: #00ffff;
      margin-bottom: 10px;
      text-align: center;
    }
    .profile-info p,
    .achievements-list ul li {
      background: #2b2b2b;
      margin: 10px 0;
      padding: 10px;
      border-radius: 5px;
    }
    .achievements-list ul {
      list-style: none;
      padding: 0;
    }
    .achievements-list ul li {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .profile-image-container {
      display: flex;
      align-items: center;
      margin: 20px 0;
    }
    .profile-image {
      text-align: center;
      margin-right: 20px;
    }
    .profile-image img {
      max-width: 200px;
      border-radius: 10px;
      margin-bottom: 10px;
    }
    .profile-buttons {
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    .profile-buttons button {
      margin: 5px;
      padding: 10px 20px;
      background-color: #50b3a2;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .profile-buttons .message-btn {
      background-color: #5ac57e;
    }
    .profile-buttons .friend-btn {
      background-color: #7f972e;
    }
    .profile-buttons .cancel-btn {
      background-color: #243710;
    }
    .profile-buttons .delete-btn {
      background-color: grey;
    }
    .profile-buttons .reject-btn {
      background-color: brown;
    }
    #messageBox {
      display: none;
      position: fixed;
      bottom: 20px;
      right: 20px;
      background: #fff;
      border: 1px solid #ccc;
      padding: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    #messageBox textarea {
      width: 300px;
      height: 100px;
    }
    #messageBox button {
      margin-top: 10px;
    }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      var action = "";
      var rejectButton;

      var isFriend = <%= request.getAttribute("isFriend") != null ? request.getAttribute("isFriend") : "false" %>;
      var alreadySent = <%= request.getAttribute("alreadySent") != null ? request.getAttribute("alreadySent") : "false" %>;
      var receivedFriendRequest = <%= request.getAttribute("receivedFriendRequest") != null ? request.getAttribute("receivedFriendRequest") : "false" %>;

      console.log("isFriend: " + isFriend);
      console.log("alreadySent: " + alreadySent);
      console.log("receivedFriendRequest: " + receivedFriendRequest);

      // Initialize button based on the friend status
      if (isFriend) {
        $("#friendRequestButton")
                .removeClass("friend-btn")
                .addClass("delete-btn")
                .text("Delete Friend");
        console.log("Friend button initialized as 'Delete Friend'");
      } else if (receivedFriendRequest) {
        $("#friendRequestButton")
                .removeClass("friend-btn")
                .addClass("accept-btn")
                .text("Accept Friend Request");
        console.log("Friend button initialized as 'Accept Friend Request'");
        rejectButton = $("<button></button>")
                .addClass("default-btn reject-btn")
                .text("Reject Friend Request")
                .attr("id", "rejectFriendRequestButton");
        $("#friendRequestButton").after(rejectButton);

        rejectButton.click(function() {
          console.log("Reject Friend Request button clicked!");
          action = "rejectFriendRequest";
          rejectButton.remove();
          $("#friendRequestButton")
                  .removeClass("accept-btn")
                  .addClass("friend-btn")
                  .text("Send Friend Request");
          performAction(action);
        });
      } else if (alreadySent) {
        $("#friendRequestButton")
                .removeClass("friend-btn")
                .addClass("cancel-btn")
                .text("Cancel Friend Request");
        console.log("Friend button initialized as 'Cancel Friend Request'");
      }

      // Handle the click event for the friend request button
      $("#friendRequestButton").click(function() {
        var button = $(this);
        console.log("Friend request button clicked!");

        if (button.hasClass("friend-btn")) {
          button.removeClass("friend-btn").addClass("cancel-btn");
          button.text("Cancel Friend Request");
          action = "sendFriendRequest";
        } else if (button.hasClass("accept-btn")) {
          button.removeClass("accept-btn").addClass("delete-btn");
          button.text("Delete Friend");
          action = "acceptFriendRequest";
          if (rejectButton) rejectButton.remove();
        } else if (button.hasClass("cancel-btn")) {
          button.removeClass("cancel-btn").addClass("friend-btn");
          button.text("Send Friend Request");
          action = "cancelFriendRequest";
        } else if (button.hasClass("delete-btn")) {
          if (confirm("Are you sure you want to delete this friend?")) {
            button.removeClass("delete-btn").addClass("friend-btn");
            button.text("Send Friend Request");
            action = "deleteFriend";
          }
        }
        console.log("Action determined: " + action);
        performAction(action);
      });

      // Handle the click event for the message button
      $(document).ready(function() {
        $("#messageButton").click(function() {
          $("#messageBox").show();
        });

        $("#sendMessageButton").click(function() {
          var messageContent = $("#messageContent").val();
          $.ajax({
            url: "<%= request.getContextPath() %>/mail-send-servlet",
            type: "POST",
            data: {
              userId: "<%= userId %>",
              message: messageContent,
              action: "send message",
              mailTypeId: 2,
              otherUserId: "<%= otherUser.getId() %>"
            },
            success: function(response) {
              alert("Message sent successfully: " + response);
              $("#messageBox").hide();
              $("#messageContent").val('');
            },
            error: function(xhr, status, error) {
              alert("Error: Could not send message");
            }
          });
        });

        $("#cancelMessageButton").click(function() {
          $("#messageBox").hide();
          $("#messageContent").val('');
        });
      });

      function performAction(action) {
        if (action) {
          $.ajax({
            url: "<%= request.getContextPath() %>/mail-send-servlet",
            type: "POST",
            data: {
              userId: "<%= userId %>",
              action: action,
              mailTypeId: 0,
              otherUserId: "<%= otherUser.getId() %>"
            },
            success: function() {
              console.log("Action performed successfully");
            },
            error: function(xhr, status, error) {
              console.log("Error occurred while performing action: " + error);
              alert("Error: " + error);
            }
          });
        }
      }
    });
  </script>
</head>
<body>
<header>
  <div class="container">
    <a href="user-profile-servlet?userId=<%=userId%>">
      <img src="some.png" alt="Quiz Icon" class="quiz-icon" />
    </a>
    <h1>Other User Profile Page</h1>
  </div>
</header>
<div class="container">
  <div class="profile-image-container">
    <div class="profile-image">
      <img src="default-profile.png" alt="Profile Image"/>
    </div>
    <div class="profile-buttons">
      <button id="friendRequestButton" class="default-btn friend-btn">Send Friend Request</button>
      <button id="messageButton" class="default-btn message-btn">Message</button>
    </div>
  </div>
  <div class="main-content">
    <div class="section profile-info">
      <h2>Profile Information</h2>
      <p>Full Name: <%= otherUser.getFullName() %></p>
      <p>Username: <%= otherUser.getUserName() %></p>
      <p>Registration Date: <%= otherUser.getCreatedAt() %></p>
    </div>

    <div class="section achievements-list">
      <h2>Achievements</h2>
      <ul>
        <% for (Achievement achievement : achievements) { %>
        <li><%= AchievementEnum.intToString(achievement.achievementId) %>, Achieved on: <%= achievement.achievedAt %></li>
        <% } %>
      </ul>
    </div>
  </div>

  <div id="messageBox">
    <textarea id="messageContent" placeholder="Write your message here..."></textarea>
    <br>
    <button id="sendMessageButton" class="default-btn message-btn">Send</button>
    <button id="cancelMessageButton" class="default-btn">Cancel</button>
  </div>
</div>
</body>
</html>
