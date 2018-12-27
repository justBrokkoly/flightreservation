<%--
  Created by IntelliJ IDEA.
  User: кирилл
  Date: 07.11.2018
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<form action="registerUser" method="post">
    <h5> Registration User:</h5>
    <pre>
        First Name: <input type="text" name="firstName"/>
        Last Name:<input type="text" name="lastName"/>
        User Name:<input type="text" name="email"/>
        Password:<input type="password" name="password"/>
        Confirm password:<input type="password" name="confirmPassword"/>
        <input type="submit" value="register">
    </pre>
</form>
</body>
</html>
