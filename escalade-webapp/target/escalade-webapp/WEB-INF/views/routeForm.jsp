<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page routeForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Ajouter une voie</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <form:form modelAttribute="route" method="post" action="addRouteProcess/${sectorId}">
                <div class="form-group">
                    <form:hidden path="sector.id" value="${sectorId}"/>
                    <form:errors path="sector.id" cssClass="errors"/>
                </div>
                <div class="form-group">
                    <form:input path="name" type="text" cssClass="form-control item" placeholder="Nom de la voie" required="true" autofocus=""/>
                    <form:errors  path="name" cssClass="error"/>
                </div>
                <div class="form-group select-style">
                    <form:select path="quotation" cssClass="form-control">
                        <form:option value="">Cotation de la voie</form:option>
                        <form:options items="${quotationList}"/>
                    </form:select>
                    <form:errors path="quotation" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="height" type="text" cssClass="form-control item" placeholder="Hauteurs de la voie" required="true" autofocus=""/>
                    <form:errors  path="height" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:button class="btn btn-primary btn-block" type="submit">Enregistrer</form:button>
                </div>
                <div class="form-group">
                    <a href="<c:url value="/climbingSite/${climbingSitId}"/>" class="btn btn-outline-primary btn-block">
                        Annuler
                    </a>
                </div>
            </form:form>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>
