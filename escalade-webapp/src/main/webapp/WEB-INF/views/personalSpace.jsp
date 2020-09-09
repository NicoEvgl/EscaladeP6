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
</main>