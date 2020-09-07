<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page climbingSite-page">
    <section class="clean-block clean-product dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">${climbingSite.name.toUpperCase()}</h2>
                <c:if test="${sessionScope.userInSessionRole == 'Administrator' || climbingSite.user.id == sessionScope.userInSessionId}">
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
                                <div class="sp-wrap">
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
            </div>
        </div>
    </section>
</main>
