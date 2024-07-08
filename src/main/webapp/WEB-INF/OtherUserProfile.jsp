<%@ page import="org.example.oopdefaultkgb.EntityDTO.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User otherUser = (User) request.getAttribute("otherUser");
   int userId = (int)request.getAttribute("userId");

%>
<!DOCTYPE html>
<html>
<head>
  <title>Dynamic Button Example with jQuery</title>
  <!-- Include jQuery from a CDN -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <style>
    .default-btn {
      background-color: blue;
      color: white;
      border: none;
      padding: 10px 20px;
      cursor: pointer;
      margin: 5px;
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
  </style>
  <script type="text/javascript">
    var action = "";
    var rejectButton;
    $(document).ready(function() {
      console.log("Document is ready!");

      // Initialize button based on the friend status
      if (isFriend) {
        $("#friendRequestButton")
                .removeClass("friend-btn")
                .addClass("delete-btn")
                .text("Delete Friend");
        console.log("Friend button initialized as 'Delete Friend'");
      }
      else if(receivedFriendRequest){
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
    }
      else if(alreadySent){
        $("#friendRequestButton")
                .removeClass("friend-btn")
                .addClass("cancel-btn")
                .text("Cancel Friend Request");
        console.log("Friend button initialized as 'Cancel Friend Request'");
      }

      // Handle the click event for the friend request button

      // Handle click event for the reject button
      rejectButton.click(function() {
        console.log("Reject Friend Request button clicked!");
        action = "rejectFriendRequest";
        rejectButton.remove();
        $("#friendRequestButton")
                .removeClass("accept-btn")
                .addClass("friend-btn")
                .text("Send Friend Request");
      });
      $("#friendRequestButton").click(function() {
        console.log("Friend request button clicked!");
        var button = $(this);

        if (button.hasClass("friend-btn")) {
          button.removeClass("friend-btn").addClass("cancel-btn");
          button.text("Cancel Friend Request");
          action = "sendFriendRequest";
        } else if (button.hasClass("accept-btn")) {
          button.removeClass("accept-btn").addClass("friend-btn");
          button.text("Delete Friend");
          action = "acceptFriendRequest";
          rejectButton.remove();

        }else if (button.hasClass("cancel-btn")) {
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

        if (action) {
          // Perform the AJAX call to the servlet
          $.ajax({
            url: "<%= request.getContextPath() %>/mail-send-servlet",
            type: "POST",
            data: {
              userId: "<%= userId %>",  // Properly escape this value
              action: action,
              mailTypeId: 0,
              otherUserId: "<%= otherUser.id %>" // Add otherUserId properly
            },
            success: function() {
              console.log("Action performed successfully");
            },
            error: function() {
              console.log("Error occurred: ");
              alert("Error: ");
            }
          });
        }
      });

      // Handle the click event for the message button
      $("#messageButton").click(function() {
        console.log("Message button clicked!");
        // Perform the AJAX call to the servlet for messaging
        $.ajax({
          url: "<%= request.getContextPath() %>/message-servlet",
          type: "POST",
          data: {
            userId: "<%= otherUser.getId() %>", // Properly escape this value
            message: "Your message content here"  // Add message content dynamically as needed
          },
          success: function(response) {
            console.log("Message sent successfully: " + response);
            alert("Message sent successfully: " + response);
          },
          error: function(xhr, status, error) {
            console.log("Error occurred while sending message: " + error);
            alert("Error: " + error);
          }
        });
      });
    });

    // JavaScript to set isFriend variable, make sure it is executed before the ready function
    var isFriend = <%= request.getAttribute("isFriend") != null ? request.getAttribute("isFriend") : "false" %>;
    var alreadySent =<%=request.getAttribute("alreadySent") != null ?  request.getAttribute("alreadySent") : "false"%>;
    var receivedFriendRequest =<%=request.getAttribute("receivedFriendRequest") != null ?  request.getAttribute("receivedFriendRequest") : "false"%>;

    console.log("isFriend variable set to: " + isFriend);
  </script>
</head>
<body>
<h1>Fullname: <%= otherUser.getFullName() %></h1>
<h1>Username: <%= otherUser.getUserName() %></h1>

<!-- Message button -->
<button id="messageButton" class="default-btn message-btn">Message</button>

<!-- Friend request button -->
<button id="friendRequestButton" class="default-btn friend-btn">Send Friend Request</button>

</body>
</html>
