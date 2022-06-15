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
    <title>Change Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <form action="/change-password" method="post">
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6 col-xs-12">
                    <div class="jumbotron">

                        <h1 class="text-center">Change Password Page</h1>

                        <div class="text-danger">${errorMessage}</div>

                        <br>
                        <div class="form-group">
                            <label class="control-label" for="current-password">Current Password</label>
                            <input
                                    id="current-password"
                                    type="password"
                                    name="current-password"
                                    class="form-control"
                                    placeholder="Current Password"
                                    required>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="new-password">New Password</label>
                            <input
                                    id="new-password"
                                    type="password"
                                    name="new-password"
                                    class="form-control"
                                    placeholder="New Password"
                                    required>
                        </div>
                        <div class="text-center" style="margin-top: 24px;">
                            <button type="submit" class="btn btn-outline-warning">Update</button>
                            <button type="button" class="btn btn-outline-secondary" onclick="history.back()">Cancel</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>