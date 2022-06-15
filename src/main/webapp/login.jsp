<%--
  Created by IntelliJ IDEA.
  User: qpham
  Date: 08/12/2021
  Time: 08:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login With JSP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

    <form action="/login" method="post">
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6 col-xs-12">
                    <div class="jumbotron">

                        <h1 class="text-center">Login Page</h1>
                        <br>

                        <div class="text-danger">${errorMessage}</div>

                        <div class="form-group">
                            <label class="control-label" for="username">User Name</label>
                            <input
                                    id="username"
                                    type="text"
                                    name="username"
                                    class="form-control"
                                    placeholder="Username"
                                    maxlength="16"
                                    required>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="password">Password</label>
                            <input
                                    id="password"
                                    type="password"
                                    name="password"
                                    class="form-control"
                                    placeholder="Password"
                                    maxlength="8"
                                    required>
                        </div>
                        <div class="text-center" style="margin-top: 24px;">
                            <button type="submit" class="btn btn-outline-warning">Login</button>
                            <button type="button" class="btn btn-outline-secondary" onclick="document.forms[0].reset();">Clear</button>
                            <br>
                        </div>
<%--                        <div>--%>
<%--                            <br>--%>
<%--                            Not a member yet ? <a href="registration.jsp">Join Us</a>--%>
<%--                        </div>--%>

                    </div>
                </div>
                <div class="col-md-3"></div>

            </div>
        </div>
    </form>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>