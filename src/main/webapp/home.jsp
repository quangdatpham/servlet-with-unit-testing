<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6 col-xs-12">
                <h1><%= "Hello World!" %></h1>
                <div>${currentUser != null ? currentUser.fullname : ""}</div>
                <br/>
                <a href="/home">Home</a>
                <hr />
                <a href="/login">Login</a>
                <hr />
                <a href="/profile">Profile</a>
                <hr />
                <a href="/change-password">Change Password</a>
                <hr />
                <a href="/security-question">Security Question</a>
                <hr />
                <a class="text-warning" href="/logout">Logout</a>
            </div>
        </div>
    </div>
</body>
</html>