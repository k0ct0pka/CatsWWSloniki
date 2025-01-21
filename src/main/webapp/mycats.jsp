<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 30.12.2024
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="home.css">
</head>
<body>
<h2><a href="/home.jsp">Back To Home Page</a></h2>
    <c:forEach items="${cats}" var="cat">
        <c:if test="${cat.user.id == user.id}">
        <div>
            <form action="/home" method="post" enctype="multipart/form-data">
                <input type="hidden" value="PUT" name="_method">
                <input type="hidden" value="${cat.id}" name="id">
                <img src="data:image/png;base64,${cat.image}" alt="">
                <p>Change Image: <input type="file" name="image"></p>
                <p>Change Cat's name: <input type="text" value="${cat.name}" name="name"></p>
                <p>Change Cat's breed: <input type="text" value="${cat.breed}" name="breed"></p>
                <br>
                <input type="submit" value="Save">
                <br>
            </form>
            <br>
            <form action="/delete" method="post">
                <input type="hidden" value="DELETE" name="_method">
                <input type="hidden" value="${cat.id}" name="id">
                <input type="submit" value="DELETE">
            </form>
        </div>
        </c:if>
    </c:forEach>
</body>
</html>
