<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page userProfile-page">
    <section class="clean-block clean-services dark">
        <div class="container">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Profil de ${userProfile.username}</h2>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="card shadow mb-3">
                            <div class="card-body">
                                <table class="table text-center my-0">
                                    <thead>
                                    <tr>
                                        <th>Pseudo</th>
                                        <th>Email</th>
                                        <th>Rôle</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>${userProfile.username}</td>
                                        <td><a href="<c:url value="mailto:${userProfile.email}"/>">${userProfile.email}</a></td>
                                        <td>${userProfile.role}</td>
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

<%@include file="footer.jsp"%>
