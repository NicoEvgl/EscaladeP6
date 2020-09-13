<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page personalPage-page">
    <section class="clean-block clean-services dark">
        <div class="container">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Liste des utilisateurs</h2>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="card shadow mb-3">
                            <div class="card-header py-3">
                                <a href="<c:out value="javascript:history.go(-1)"/>" class="btn btn-outline-primary btn-block">Retour</a>
                            </div>
                            <div class="card-body">
                                <table class="table text-justify my-0">
                                    <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Prénom</th>
                                        <th>Pseudo</th>
                                        <th>Email</th>
                                        <th>Rôle</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userList}" var="user">
                                        <tr>
                                            <td>${user.lastName}</td>
                                            <td>${user.firstName}</td>
                                            <td>${user.username}</td>
                                            <td>${user.email}</td>
                                            <td>${user.role}</td>
                                            <td>
                                                <a href="<c:url value="/editUserRole/${user.id}"/>" class="btn btn-outline-secondary btn-sm">Changer le rôle</a>
                                            </td>
                                            <td>
                                                <a href="<c:url value="/deleteUser/${user.id}"/>" class="btn btn-outline-secondary btn-sm">Supprimer le compte</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
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

<%@include file="footer.jsp"%>
