<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Quiz Homepage</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .container {
      margin-top: 20px;
    }
    .card {
      margin-bottom: 20px;
    }
    .card-header {
      background-color: #007bff;
      color: white;
    }
    .list-group-item {
      cursor: pointer;
    }
    .badge {
      float: right;
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Quiz Hub</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <form id="profileForm" action="user-profile-servlet" method="POST" style="display: inline;">
          <input type="hidden" name="userId" value=<%= request.getAttribute("userId") != null ? (int) request.getAttribute("userId") : request.getParameter("userId")%>>
          <a class="nav-link" href="user-profile-servlet" onclick="document.getElementById('profileForm').submit(); return false;">Profile</a>
        </form>
      </li>
    </ul>
  </div>
</nav>

<div class="container">
  <h1 class="text-center mb-4">Welcome to Quiz Hub</h1>

  <!-- Popular Quizzes -->
  <div class="card">
    <div class="card-header">Popular Quizzes</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Quiz 1 <span class="badge badge-primary">Popular</span></li>
      <li class="list-group-item">Quiz 2 <span class="badge badge-primary">Popular</span></li>
      <li class="list-group-item">Quiz 3 <span class="badge badge-primary">Popular</span></li>
    </ul>
  </div>

  <!-- Recently Created Quizzes -->
  <div class="card">
    <div class="card-header">Recently Created Quizzes</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">New Quiz 1 <span class="badge badge-info">New</span></li>
      <li class="list-group-item">New Quiz 2 <span class="badge badge-info">New</span></li>
      <li class="list-group-item">New Quiz 3 <span class="badge badge-info">New</span></li>
    </ul>
  </div>

  <!-- User's Recent Quiz Taking Activities -->
  <div class="card">
    <div class="card-header">Your Recent Quiz Taking Activities</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Took Quiz 1 <span class="badge badge-success">Completed</span></li>
      <li class="list-group-item">Took Quiz 2 <span class="badge badge-success">Completed</span></li>
    </ul>
  </div>

  <!-- User's Recent Quiz Creating Activities -->
  <div class="card" id="quizCreatingActivitiesCard" style="display: none;">
    <div class="card-header">Your Recent Quiz Creating Activities</div>
    <ul class="list-group list-group-flush" id="quizCreatingActivitiesList">
      <!-- Items will be added dynamically -->
    </ul>
  </div>

  <!-- User's Achievements -->
  <div class="card">
    <div class="card-header">Your Achievements</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Achievement 1 <span class="badge badge-warning">New</span></li>
      <li class="list-group-item">Achievement 2 <span class="badge badge-warning">New</span></li>
    </ul>
  </div>

  <!-- User's Messages -->
  <div class="card">
    <div class="card-header">Messages</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">Friend Request from User A <span class="badge badge-info">Friend Request</span></li>
      <li class="list-group-item">Challenge from User B <span class="badge badge-danger">Challenge</span></li>
    </ul>
  </div>

  <!-- Friends' Recent Activities -->
  <div class="card">
    <div class="card-header">Friends' Recent Activities</div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">
        <a href="#">Friend 1</a> took <a href="#">Quiz A</a> <span class="badge badge-success">Completed</span>
      </li>
      <li class="list-group-item">
        <a href="#">Friend 2</a> created <a href="#">Quiz B</a> <span class="badge badge-info">New</span>
      </li>
      <li class="list-group-item">
        <a href="#">Friend 3</a> earned <a href="#">Achievement X</a> <span class="badge badge-warning">New</span>
      </li>
    </ul>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
  $(document).ready(function() {
    // Sample data: user's recent quiz creating activities
    var userQuizCreatingActivities = [
      'Created Quiz 1',
      'Created Quiz 2'
    ];

    // Check if user has created any quizzes
    if (userQuizCreatingActivities.length > 0) {
      var list = $('#quizCreatingActivitiesList');
      userQuizCreatingActivities.forEach(function(activity) {
        list.append('<li class="list-group-item">' + activity + ' <span class="badge badge-info">New</span></li>');
      });
      $('#quizCreatingActivitiesCard').show();
    }
  });
</script>
</body>
</html>
