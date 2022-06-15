<%--
  Created by IntelliJ IDEA.
  User: qpham
  Date: 08/12/2021
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6 col-xs-12">
                <h1 class="text-center">Profile Page</h1>
                <p>Fullname: <strong>${currentUser.getFullname()}</strong></p>
                <p>Username: <strong>${currentUser.getUsername()}</strong></p>
                <button type="button" class="btn btn-outline-secondary" onclick="history.back()">Back</button>
            </div>
        </div>
    </div>
</body>
</html>