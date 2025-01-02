<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.11.2024
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<a href="login.jsp"> login</a>

<form action="${pageContext.request.contextPath}/authorize" method="post">
    <label>
        Name:
        <br>
        <input type="text" name="name" required>
    </label>
    <br>
    <br>
    <label>
        Email:
        <br>
        <input type="text" name="email" required>
    </label>
    <br>
    <br>
    <label>
        Password:
        <br>
        <input type="text" name="password" required>
        </label>
    <br>
    <br>
    <input type="submit">
</form>
</body>
</html>