<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style1.css">
    <title>Doctor Login</title>
</head>
<body>
<div id="container" class="container">
    <div class="header">
        <h1>Login as Doctor</h1>
    </div>
    <div class="content">
        <form action="Servlet" method="post" class="log-in">
            <label for="email"><span>EMAIL</span><input type="text" name="email" id="email"></label>
            <label for="password"><span>PASSWORD</span><input type="password" name="password" id="password"></label>
            <input type="submit" value="Login">
        </form>
    </div>
</div>
</body>
</html>
