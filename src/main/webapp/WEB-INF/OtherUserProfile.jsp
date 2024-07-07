<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  </style>
  <script type="text/javascript">
    $(document).ready(function() {
      // Initialize button based on the friend status
      if (isFriend) {
        $("#friendRequestButton")
                .removeClass("friend-btn")
                .addClass("delete-btn")
                .text("Delete Friend");
      }

      // Handle the click event for the friend request button
      $("#friendRequestButton").click(function() {
        var button = $(this);
        if (button.hasClass("friend-btn")) {
          button.removeClass("friend-btn").addClass("cancel-btn");
          button.text("Cancel Friend Request");
          // Here you can add AJAX call to send the friend request
        } else if (button.hasClass("cancel-btn")) {
          button.removeClass("cancel-btn").addClass("friend-btn");
          button.text("Send Friend Request");
          // Here you can add AJAX call to cancel the friend request
        } else if (button.hasClass("delete-btn")) {
          // Confirm deletion
          if (confirm("Are you sure you want to delete this friend?")) {
            button.removeClass("delete-btn").addClass("friend-btn");
            button.text("Send Friend Request");
            // Here you can add AJAX call to delete the friend
          }
        }
      });

      // Handle the click event for the message button
      $("#messageButton").click(function() {
        alert("Message button clicked!");
        // Here you can add the logic to handle messaging
      });
    });
  </script>
</head>
<body>
<h1>Welcome to other User</h1>
<!-- Message button -->
<button id="messageButton" class="default-btn message-btn">Message</button>

<!-- Friend request button -->
<button id="friendRequestButton" class="default-btn friend-btn">Send Friend Request</button>

<br/><br/>

<!-- Assuming this information is dynamically determined and passed from the server -->
<script type="text/javascript">
  var isFriend = <%= request.getAttribute("isFriend") != null ? request.getAttribute("isFriend") : false %>;
</script>
</body>
</html>
