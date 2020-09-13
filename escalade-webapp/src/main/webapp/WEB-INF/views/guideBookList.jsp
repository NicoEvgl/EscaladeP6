<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page guideBookList-page">
    <section class="clean-block clean-services dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Chercher un topo</h2>
                <c:set var="actionForm" value="guideBookList/search"/>
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
                <c:forEach items="${guideBookList}" var="guideBook" >
                    <div class="col-md-6 col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">${guideBook.name}</h4>
                            </div>
                            <div class="card-body">
                                <a href="<c:url value="/guideBook/${guideBook.id}"/>" class="btn btn-outline-secondary btn-sm">
                                    Voir
                                </a>
                            </div>
                            <div class="card-footer">
                                <c:choose>
                                    <c:when test="${guideBook.booked == false}">
                                        <p class="bg-success text-white">DISPONIBLE</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="bg-danger text-white">INDISPONIBLE</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>
