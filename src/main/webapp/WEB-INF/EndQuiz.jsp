<!DOCTYPE html>
<html>

<head>
    <title>End of Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .center-label {
            background-color: #50b3a2;
            color: #fff;
            padding: 20px 40px;
            text-align: center;
            border-radius: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .center-label:hover {
            background-color: #429184;
        }
    </style>
    <script>
        function goToHome() {
            window.location.href = 'user-profile-servlet?userId=<%=(int)request.getAttribute("userId")%>';
        }
    </script>
</head>
<body>
<div class="center-label" onclick="goToHome()">
    End Quiz - Go to Home
</div>
</body>
</html>