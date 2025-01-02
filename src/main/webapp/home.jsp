<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 29.12.2024
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="home.css">
</head>
<body>
<div>
    <h1 style="color: pink">Cats WW Sloniki</h1>
    <p>Hi ${user.name}</p>
    <a href="create.jsp">ADD CAT</a>
    <a href="${pageContext.request.contextPath}/update">My Cats</a>
</div>

<c:forEach items="${cats}" var="cat">
    <div>
        <img src="data:image/png;base64,${cat.image}" alt="">
        <p>Name of Cat: ${cat.name}</p>
        <p>Breed of Cat: ${cat.breed}</p>
        <p>Name of owner:  ${cat.user.name}</p>
    </div>
</c:forEach>

</body>
</html>
