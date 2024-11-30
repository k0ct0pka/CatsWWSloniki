<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.11.2024
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/authorize" method="post">

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
    </label>
    <br>
    <input type="submit" value="submit">
</form>
</body>
</html>

