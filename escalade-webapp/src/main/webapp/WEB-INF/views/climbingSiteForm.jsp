<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page climbingAreaForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Ajouter un site</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <form:form modelAttribute="climbingSite" method="post" action="addClimbingSiteProcess">
                <div class="form-group">
                    <form:input path="name" type="text" cssClass="form-control item" placeholder="Nom du site" required="true" autofocus=""/>
                    <form:errors  path="name" cssClass="error"/>
                </div>
                <div class="form-group select-style">
                    <form:select path="region" cssClass="form-control">
                        <form:option value="">Région</form:option>
                        <form:options items="${regionList}"/>
                    </form:select>
                    <form:errors path="region" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="climbingType" type="text" cssClass="form-control item" placeholder="Type d'escalade" autofocus=""/>
                    <form:errors  path="climbingType" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="rockType" type="text" cssClass="form-control item" placeholder="Type de roche" autofocus=""/>
                    <form:errors  path="rockType" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="height" type="text" cssClass="form-control item" placeholder="Hauteurs (de ...m à ...m)" autofocus=""/>
                    <form:errors  path="height" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="nbRoutes" type="number" cssClass="form-control item" placeholder="Nombre de voies" required="true" autofocus=""/>
                    <form:errors  path="nbRoutes" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="quotation" type="text" cssClass="form-control item" placeholder="Cotation" required="true" autofocus=""/>
                    <form:errors  path="quotation" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:textarea path="info" type="text" rows="5" cssClass="form-control item" placeholder="Infos du site" autofocus=""/>
                    <form:errors  path="info" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:button class="btn btn-primary btn-block" type="submit">Enregistrer</form:button>
                </div>
                <div class="form-group">
                    <a href="<c:url value="/personalSpace/${userInSessionId}"/>" class="btn btn-outline-primary btn-block">
                        Annuler
                    </a>
                </div>
            </form:form>
        </div>
    </section>
</main>