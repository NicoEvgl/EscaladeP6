<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<html lang="fr">
    <head>
        <title>Les amis de l'escalade</title>
        <meta charset="UTF-8"/>

        <script src="https://kit.fontawesome.com/c822637fde.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/resources/fonts/simple-line-icons.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/smoothproducts.css"/>"/>
        <link rel="stylesheet" href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css"/>"/>
        <link rel="stylesheet" href="<c:url value="https://fonts.googleapis.com/css?family=Montserrat:400,400i,700,700i,600,600i"/>"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="<c:url value="/home"/>">
               <img src="<c:url value="https://img.icons8.com/color/48/000000/climbing.png"/>" alt="Les amis de l'escalade icon" style="width: 50px; height:auto">
            </a>
            <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarTogglerDemo02" >
                <span class="sr-only">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <c:choose>
                    <c:when test="${!empty userInSessionId}">
                        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/personalSpace/${userInSessionId}"/>">${ userInSessionUsername }</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/climbingSiteList"/>">Les sites d'escalade</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/guideBook"/>">Les topos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/logout"/>">Se déconnecter</a>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/register"/>">S'inscrire</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/login"/>">Se connecter</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/climbingSiteList"/>">Les sites d'escalade</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/guideBook"/>">Les topos</a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>
    </body>
</html>
