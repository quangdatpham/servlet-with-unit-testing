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
    <title>Security Question</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
    <form action="/security-question" method="post">
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6 col-xs-12">
                    <div class="jumbotron">

                        <h1 class="text-center">Security Question Page</h1>
                        <br>
                        <div class="text-danger">${errorMessage}</div>

                        <div class="form-group">
                            <div><label class="control-label" for="question1">Question 1</label></div>
                            <select class="form-control w-25 d-inline" name="question1" id="question1" required>
                                <option value="">--</option>
                                <option value="What is your date of birth?">What is your date of birth?</option>
                                <option value="What was your favorite school teacher’s name?">What was your favorite school teacher’s name?</option>
                                <option value="What’s your favorite movie?">What’s your favorite movie?</option>
                                <option value="What was your first car?">What was your first car?</option>
                            </select>
                            <input
                                    id="answer1"
                                    type="password"
                                    name="answer1"
                                    class="form-control d-inline"
                                    style="width: 74%;"
                                    placeholder="Answer 1"
                                    required>
                        </div>
                        <hr />
                        <div class="form-group">
                            <div><label class="control-label" for="question2">Question 2</label></div>
                            <select class="form-control w-25 d-inline" name="question2" id="question2">
                                <option value="">--</option>
                                <option value="What is your date of birth?">What is your date of birth?</option>
                                <option value="What was your favorite school teacher’s name?">What was your favorite school teacher’s name?</option>
                                <option value="What’s your favorite movie?">What’s your favorite movie?</option>
                                <option value="What was your first car?">What was your first car?</option>
                            </select>
                            <input
                                    id="answer2"
                                    type="password"
                                    name="answer2"
                                    class="form-control d-inline"
                                    style="width: 74%;"
                                    placeholder="Answer 2">
                        </div>
                        <hr />
                        <div class="form-group">
                            <div><label class="control-label" for="question3">Question 3</label></div>
                            <select class="form-control w-25 d-inline" name="question3" id="question3">
                                <option value="">--</option>
                                <option value="What is your date of birth?">What is your date of birth?</option>
                                <option value="What was your favorite school teacher’s name?">What was your favorite school teacher’s name?</option>
                                <option value="What’s your favorite movie?">What’s your favorite movie?</option>
                                <option value="What was your first car?">What was your first car?</option>
                            </select>
                            <input
                                    id="answer3"
                                    type="password"
                                    name="answer3"
                                    class="form-control d-inline"
                                    style="width: 74%;"
                                    placeholder="Answer 3">
                        </div>
                        <div class="text-center" style="margin-top: 24px;">
                            <button type="submit" class="btn btn-outline-warning">Submit</button>
                            <button type="button" class="btn btn-outline-secondary" onclick="history.back()">Cancel</button>
                            <button type="button" class="btn btn-outline-secondary" onclick="document.forms[0].reset();">Clear</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>