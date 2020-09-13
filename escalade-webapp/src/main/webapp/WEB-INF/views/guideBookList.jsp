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
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>
