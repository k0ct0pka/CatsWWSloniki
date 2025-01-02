<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.12.2024
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/home" enctype="multipart/form-data">
    <label>
        Name:
        <br>
        <input type="text" name="name" required>
    </label>
    <br>
    <br>
    <label>
        Breed:
        <br>
        <input type="text" name="breed" required>
    </label>
    <br>

    <br>
    <label>
        Image:
        <br>
        <input type="file" name="image" required>
    </label>
    <br>

    <input type="submit" value="submit">
</form>
</body>
</html>
