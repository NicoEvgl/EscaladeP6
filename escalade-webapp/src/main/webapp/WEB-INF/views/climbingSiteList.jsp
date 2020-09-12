<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<div class="view" style="background-image: url('https://cdn.crunchify.com/bg.png'); background-repeat: no-repeat; background-size: cover; background-position: center ;"/>
<main class="page climbingSiteList-page">
    <section class="clean-block clean-services dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Chercher un site</h2>
                <c:set var="actionForm" value="climbingSiteList/search"/>
                <c:if test="${!empty action}">
                    <c:set var="actionForm" value=""/>
                </c:if>
                <div class="container d-flex justify-content-center">
                    <form:form cssClass="form-inline" modelAttribute="searchFilter" method="post" action="${actionForm}">
                        <div class="form-inline select-style">
                            <form:select path="name" cssClass="form-control mb-2 mr-sm-2">
                                <form:option value="">Site</form:option>
                                <form:options items="${nameList}"/>
                            </form:select>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                        <div class="form-inline select-style">
                            <form:select path="region" cssClass="form-control mb-2 mr-sm-2">
                                <form:option value="">Région</form:option>
                                <form:options items="${regionList}"/>
                            </form:select>
                            <form:errors path="region" cssClass="error"/>
                        </div>
                        <div class="form-inline select-style">
                            <form:select path="nbRoutes" cssClass="form-control mb-2 mr-sm-2">
                                <form:option value="">Nombre de voies</form:option>
                                <form:options items="${nbRoutesList}"/>
                            </form:select>
                            <form:errors path="nbRoutes" cssClass="error"/>
                        </div>
                        <div class="form-inline select-style">
                            <form:select path="quotation" cssClass="form-control mb-2 mr-sm-2">
                                <form:option value="">Cotation</form:option>
                                <form:options items="${quotationList}"/>
                            </form:select>
                            <form:errors path="quotation" cssClass="error"/>
                        </div>
                        <div class="form-inline">
                            <form:button class="btn btn-secondary mb-2" type="submit">Rechercher</form:button>
                        </div>
                    </form:form>
                </div>
                <c:if test="${!empty noResults}">
                    <div class="block-heading">
                        <h4 class="text-info">${noResults}</h4>
                    </div>
                </c:if>
            </div>
            <div class="row">
                <c:forEach items="${climbingSiteList}" var="climbingSite">
                    <div class="col-md-6 col-lg-4">
                        <div class="card>">
                            <c:if test="${empty climbingSite.photoList}">
                                <img class="card-img-top w-100 d-block" src="<c:url value="/resources/img/no_image_found.png"/>" >
                            </c:if>
                            <c:if test="${!empty climbingSite.photoList}">
                                <img class="card-img-top w-100 d-block" src="<c:url value="${climbingSite.photoList.get(0).url}"/>">
                            </c:if>
                            <div class="card-body">
                                <h4 class="card-title">${climbingSite.name}</h4>
                                <p class="card-text">${climbingSite.region}</p>
                                <p class="card-text">Voies : ${climbingSite.nbRoutes}</p>
                                <p class="card-text">Cotation : ${climbingSite.quotation}</p>
                            </div>
                            <div class="card-body">
                                <a class="btn btn-outline-secondary btn-sm" href="<c:url value="/climbingSite/${climbingSite.id}"/>">Voir</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>
