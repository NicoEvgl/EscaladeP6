<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Welcome</title>
    </head>
    <body>
        <c:if test="${ !empty sessionScope.userInSessionUsername }">
            <p>Welcome ${ sessionScope.userInSessionUsername } !</p>
        </c:if>

    </body>
</html>

<%@include file="home.jsp"%>