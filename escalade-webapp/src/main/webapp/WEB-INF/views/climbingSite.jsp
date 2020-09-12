<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="header.jsp"%>

<main class="page climbingSite-page">
    <section class="clean-block clean-product dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">${climbingSite.name.toUpperCase()}</h2>
                <c:if test="${sessionScope.userInSessionRole == 'Admin'}">
                  <span>
                      <a class="btn btn-sm btn-outline-primary" href="<c:url value="/editClimbingSite/${climbingSite.id}"/>">Modifier le site</a>
                  </span>
                </c:if>
            </div>
            <div class="block-content">
                <div class="product-info">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="gallery">
                                <div>
                                    <c:if test="${empty climbingSite.photoList}">
                                        <a href="<c:url value="/resources/img/no_image_found.png"/>">
                                            <img class="img-fluid d-block mx-auto" src="<c:url value="/resources/img/no_image_found.png"/>" alt="no image found">
                                        </a>
                                    </c:if>
                                    <c:forEach items="${climbingSite.photoList}" var="photo">
                                        <a href="<c:url value="${photo.url}"/>">
                                            <img class="img-fluid d-block mx-auto" src="<c:url value="${photo.url}"/>" alt=" photo du site ${climbingSite.name}">
                                        </a>
                                    </c:forEach>
                                </div>
                                <div>
                                    <c:if test="${sessionScope.userInSessionId == climbingSite.user.id || sessionScope.userInSessionRole == 'Admin'}">
                                        <a href="<c:url value="/climbingSite/${climbingSite.id}/photoForm"/>" class="btn btn-outline-secondary">
                                            Ajouter une photo
                                        </a>
                                        <a href="<c:url value="/climbingSite/${climbingSite.id}/photoList"/>" class="btn btn-outline-secondary">
                                            Afficher toutes les photos
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info">
                                <h3>Description</h3>
                                <div class="summary">
                                    <p>${climbingSite.info}</p>
                                    <p class="card-text">Type d'escalade : ${climbingSite.climbingType}</p>
                                    <p class="card-text">Type de roche : ${climbingSite.rockType}</p>
                                    <p class="card-text">Hauteurs : ${climbingSite.height}</p>
                                    <p class="card-text">Voies : ${climbingSite.nbRoutes}</p>
                                    <p class="card-text">Cotation : ${climbingSite.quotation}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="product-info">
                    <div>
                        <ul class="nav nav-tabs" id="Tab">
                            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" id="sectors-tab" href="#sectors">Secteurs</a></li>
                            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" id="routes-tabs" href="#routes">Voies</a></li>
                            <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" id="comments-tab" href="#comments">Commentaires</a></li>
                        </ul>
                        <div class="tab-content" id="TabContent">
                            <div class="tab-pane active fade show specifications" role="tabpanel" id="sectors">
                                <c:if test="${(!empty userInSessionId && sessionScope.userInSessionId == climbingSite.user.id) || sessionScope.userInSessionId == 'Admin'}">
                                    <p class="my-5">
                                        <a href="<c:url value="/addSector/${climbingSite.id}"/>" class="btn btn-outline-primary">
                                            <i class="fas fa-plus-square ml-2"> Ajouter un secteur </i>
                                        </a>
                                    </p>
                                </c:if>
                                <table class="table table-striped table-responsive-lg">
                                    <thead>
                                    <tr>
                                        <th class="align-middle" style="text-align: center">Secteur</th>
                                        <th class="align-middle" style="text-align: center">Description</th>
                                        <c:if test="${(!empty userInSessionId && sessionScope.userInSessionId == climbingSite.user.id) || sessionScope.userInSessionId.equals('Admin')}">
                                            <th class="align-middle" style="text-align: center">Modifier secteur</th>
                                            <th class="align-middle" style="text-align: center">Ajouter une voie</th>
                                            <th class="align-middle" style="text-align: center">Supprimer secteur</th>
                                        </c:if>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${climbingSite.sectorList}" var="sector" >
                                        <tr>
                                            <td class="align-middle" style="text-align: center">${sector.name}</td>
                                            <td class="align-middle" style="text-align: justify">${sector.description}</td>
                                            <c:if test="${(!empty userInSessionId && sessionScope.userInSessionId == sector.climbingSite.user.id) || sessionScope.userInSessionId.equals('Admin')}">
                                                <td class="align-middle" style="text-align: center">
                                                    <a href="<c:url value="/editSector/${sector.id}"/>" class="btn btn-outline-primary">
                                                        <i class="fas fa-edit ml-2"></i>
                                                    </a>
                                                </td>
                                                <td class="align-middle" style="text-align: center">
                                                    <a href="<c:url value="/addRoute/${sector.id}"/>" class="btn btn-outline-success">
                                                        <i class="fas fa-plus-square ml-2">
                                                        </i>
                                                    </a>
                                                </td>
                                                <td class="align-middle" style="text-align: center">
                                                    <a href="<c:url value="/deleteSector/${sector.id}"/> " class="btn btn-outline-danger">
                                                        <i class="fas fa-trash ml-2"></i>
                                                    </a>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade show specifications" role="tabpanel" id="routes">
                                <c:forEach items="${climbingSite.sectorList}" var="sector" >
                                    <section class="clean-block clean-services dark">
                                        <div class="container">
                                            <div class="block-heading">
                                                <h2 class="text-info">Voies du secteur ${sector.name.toUpperCase()}</h2>
                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="card shadow mb-3">
                                                        <div class="card-header py-3">
                                                        </div>
                                                        <div class="card-body">
                                                            <table class="table text-justify my-0">
                                                                <thead>
                                                                <tr>
                                                                    <th class="align-middle" style="text-align: center">Nom</th>
                                                                    <th class="align-middle" style="text-align: center">Cotation</th>
                                                                    <th class="align-middle" style="text-align: center">Hauteur</th>
                                                                    <c:if test="${(!empty userInSessionId && sessionScope.userInSessionId == sector.climbingSite.user.id) || sessionScope.userInSessionRole.equals('Admin')}">
                                                                        <th class="align-middle" style="text-align: center">Modifier voie</th>
                                                                        <th class="align-middle" style="text-align: center">Supprimer voie</th>
                                                                    </c:if>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <c:forEach items="${sector.routeList}" var="route">
                                                                    <tr>
                                                                        <td class="align-middle" style="text-align: center">${route.name}</td>
                                                                        <td class="align-middle" style="text-align: center">${route.quotation}</td>
                                                                        <td class="align-middle" style="text-align: center">${route.height}</td>
                                                                        <c:if test="${(!empty userInSessionId && sessionScope.userInSessionId == sector.climbingSite.user.id) || sessionScope.userInSessionRole.equals('Admin')}">
                                                                            <td class="align-middle" style="text-align: center">
                                                                                <a href="<c:url value="/editRoute/${route.id}"/> " class="btn btn-outline-primary">
                                                                                    <i class="fas fa-edit ml-2"></i>
                                                                                </a>
                                                                            </td>
                                                                            <td class="align-middle" style="text-align: center">
                                                                                <a href="<c:url value="/deleteRoute/${route.id}"/> " class="btn btn-outline-danger">
                                                                                    <i class="fas fa-trash ml-2"></i>
                                                                                </a>
                                                                            </td>
                                                                        </c:if>
                                                                    </tr>
                                                                </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </c:forEach>
                            </div>
                            <div class="tab-pane fade show" role="tabpanel" id="comments">
                                <c:if test="${!empty userInSessionId}">
                                    <p class="my-5">
                                        <a href="<c:url value="/addComment/${climbingSite.id}"/>" class="btn btn-outline-primary">
                                            <i class="fas fa-plus-square ml-2"> Ajouter un commentaire </i>
                                        </a>
                                    </p>
                                </c:if>
                                <c:if test="${empty userInSessionId}">
                                    <a href="<c:url value="/login"/>" class="btn btn-outline-primary">
                                        <i class="fas fa-plus-square ml-2"> Connectez-vous pour ajouter un commentaire </i>
                                    </a>
                                </c:if>
                                <c:forEach items="${climbingSite.commentList}" var="comment">
                                    <div class="reviews">
                                        <div class="review-item">
                                            <c:if test="${sessionScope.userInSessionRole == 'Admin' || sessionScope.userInSessionRole == 'Member'}">
                                                <div class="align-items-sm-start">
                                                    <a href="<c:url value="/editComment/${comment.id}"/> " class="btn btn-sm btn-outline-primary">
                                                        <i class="fas fa-edit ml-2"></i>
                                                    </a>
                                                    <a href="<c:url value="/deleteComment/${comment.id}"/> " class="btn btn-sm btn-outline-danger">
                                                        <i class="fas fa-trash ml-2"></i>
                                                    </a>
                                                </div>
                                            </c:if>
                                            <span class="text-muted"><a href="#">${comment.user.username}</a>, <fmt:formatDate value="${comment.creationDate}" pattern="dd/MM/yyyy HH:mm:ss"/></span>
                                            <p>${comment.commentText}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>