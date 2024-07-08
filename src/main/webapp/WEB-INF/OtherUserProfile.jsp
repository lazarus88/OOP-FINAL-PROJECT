<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.oopdefaultkgb.EntityDTO.Achievement" %>
<%@ page import="org.example.oopdefaultkgb.Enum.AchievementEnum" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  User otherUser = (User) request.getAttribute("otherUser");
  int userId = (int) request.getAttribute("userId");
  Boolean isFriend = (Boolean) request.getAttribute("isFriend");
  Boolean alreadySent = (Boolean) request.getAttribute("alreadySent");
  Boolean receivedFriendRequest = (Boolean) request.getAttribute("receivedFriendRequest");
  List<Achievement> achievements = (List<Achievement>) request.getAttribute("otherUserAchievementList");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Dynamic Button Example with jQuery</title>
  <!-- Include jQuery from a CDN -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f4f8;
      color: #333;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 80%;
      margin: auto;
      overflow: hidden;
    }
    header {
      background: #50b3a2;
      color: #fff;
      padding-top: 30px;
      min-height: 70px;
      border-bottom: #e8491d 3px solid;
    }
    header h1 {
      text-align: center;
    }
    .main-content h1 {
      text-align: left;
      color: #333;
    }
    button {
      background-color: blue;
      color: white;
      border: none;
      padding: 10px 20px;
      cursor: pointer;
      margin: 5px;
      border-radius: 5px;
    }
    .message-btn {
      background-color: green;
    }
    .friend-btn {
      background-color: orange;
    }
    .cancel-btn {
      background-color: red;
    }
    .delete-btn {
      background-color: grey;
    }
    .reject-btn {
      background-color: brown;
    }
    .main-container h1{
      text-align: left;
    }
  </style>
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

      var action = "";
      var rejectButton;
      var isFriend = <%= isFriend != null ? isFriend : "false" %>;
      var alreadySent = <%= alreadySent != null ? alreadySent : "false" %>;
      var receivedFriendRequest = <%= receivedFriendRequest != null ? receivedFriendRequest : "false" %>;

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
        if (action) {
          // Perform the AJAX call to the servlet
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
            error: function() {
              console.log("Error occurred while performing action");
              alert("Error: Could not perform action");
            }
          });
        }
      });

      // Handle the click event for the message button
      $("#messageButton").click(function() {
        console.log("Message button clicked!");
        $.ajax({
          url: "<%= request.getContextPath() %>/message-servlet",
          type: "POST",
          data: {
            userId: "<%= otherUser.getId() %>",
            message: "Your message content here" 
          },
          success: function(response) {
            console.log("Message sent successfully: " + response);
            alert("Message sent successfully: " + response);
          },
          error: function(xhr, status, error) {
            console.log("Error occurred while sending message: " + error);
            alert("Error: Could not send message");
          }
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
    <h1>სხვისი პროფილის გვერდი</h1>
  </div>
</header>
<div class="container">
  <h1>სრული სახელი: <%= otherUser.getFullName() %></h1>
  <h1>იუზერნეიმი: <%= otherUser.getUserName() %></h1>
  <h1>რეგისტრაციის თარიღი: <%= otherUser.getCreatedAt() %></h1>


<!-- Friend request button -->
<button id="friendRequestButton" class="default-btn friend-btn">Send Friend Request</button>

  <!-- Message button -->
  <button id="messageButton" class="default-btn message-btn">Message</button>

  <!-- Friend request button -->
  <button id="friendRequestButton" class="default-btn friend-btn">Send Friend Request</button>
  <h1 style="color: #450202;">მიღწევები</h1>
  <ul>
    <% for (Achievement achievement : achievements) { %>
    <li style="color: #450202;"><%= AchievementEnum.intToString(achievement.achievementId) %>, მიღწეულია: <%= achievement.achievedAt%> - დროს</li>
    <% } %>
  </ul>
</div>
</body>
</html>
