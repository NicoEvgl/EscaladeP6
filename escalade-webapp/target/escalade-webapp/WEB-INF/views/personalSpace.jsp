<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page personalPage-page">
    <section class="clean-block clean-services dark">
        <div class="container">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Profil</h2>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="card shadow mb-3">
                            <div class="card-header py-3">
                                <a href="<c:url value="/editUser/${userInSessionId}"/>" class="btn btn-outline-primary btn-block">Modifier le profil</a>
                            </div>
                            <div class="card-body">
                                <table class="table text-center my-0">
                                    <thead>
                                    <tr>
                                        <th>Genre</th>
                                        <th>Prénom</th>
                                        <th>Nom</th>
                                        <th>Pseudo</th>
                                        <th>Email</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>${userInSession.gender}</td>
                                        <td>${userInSession.firstName}</td>
                                        <td>${userInSession.lastName}</td>
                                        <td>${userInSession.username}</td>
                                        <td>${userInSession.email}</td>
                                    </tr>
                                    </tbody>
                                    <thead>
                                    <tr>
                                        <th>Adresse</th>
                                        <th>Adresse2</th>
                                        <th>Code Postal</th>
                                        <th>Ville</th>
                                        <th>Rôle</th>
                                        <td>
                                            <a href="<c:url value="/editPassword/${userInSession.id}"/>" class="btn btn-outline-secondary btn-sm">Modifier le mot de passe</a>
                                        </td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>${userInSession.address}</td>
                                        <td>${userInSession.address2}</td>
                                        <td>${userInSession.zip}</td>
                                        <td>${userInSession.city}</td>
                                        <td>${userInSession.role}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="clean-block clean-services dark">
        <div class="container">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Mes sites</h2>
                    <a href="<c:url value="/climbingSiteForm"/>" class="btn btn-outline-primary btn-block">Ajouter un site</a>
                </div>
                <div class="row">
                    <c:forEach items="${climbingSiteList}" var="climbingSite" >
                        <div class="col-md-6 col-lg-4">
                            <div class="card">
                                <c:if test="${empty climbingSite.photoList}">
                                    <img class="card-img-top w-100 d-block" src="<c:url value="/resources/img/no_image_found.png"/>" >
                                </c:if>
                                <c:if test="${!empty climbingSite.photoList}">
                                    <img class="card-img-top w-100 d-block" src="<c:url value="${climbingSite.photoList.get(0).url}"/>">
                                </c:if>
                                <div class="card-body">
                                    <h4 class="card-title">${climbingSite.name}</h4>
                                    <p class="card-text" style="height: 50px">${climbingSite.region}</p>
                                </div>
                                <div class="card-footer">
                                    <a href="<c:url value="/climbingSite/${climbingSite.id}"/>" class="btn btn-outline-primary btn-sm">Voir</a>
                                    <a href="<c:url value="/editClimbingSite/${climbingSite.id}"/>" class="btn btn-outline-primary btn-sm"> Modifier </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
</main>