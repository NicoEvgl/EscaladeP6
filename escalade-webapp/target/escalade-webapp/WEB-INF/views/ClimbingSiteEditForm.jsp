<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page updateClimbingSiteForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">${message}
            <div class="block-heading">
                <h2 class="text-login">Modification du site ${editedClimbingSite.name}</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <form:form method="post" action="editClimbingSiteProcess/${editedClimbingSite.id}" modelAttribute="editedClimbingSite">
                <div class="form-group">
                    <form:hidden path="id" value="${editedClimbingSite.id}"/>
                    <form:errors  path="id" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="name" type="text" value="${editedClimbingSite.name}" cssClass="form-control item" placeholder="Nom du site" required="true" autofocus=""/>
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
                    <form:input path="climbingType" type="text" value="${editedClimbingSite.climbingType}" cssClass="form-control item" placeholder="Type d'escalade" autofocus=""/>
                    <form:errors  path="climbingType" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="rockType" type="text" value="${editedClimbingSite.rockType}" cssClass="form-control item" placeholder="Type de roche" autofocus=""/>
                    <form:errors  path="rockType" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="height" type="text" value="${editedClimbingSite.height}" cssClass="form-control item" placeholder="Hauteurs (de ...m à ...m)" autofocus=""/>
                    <form:errors  path="height" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="nbRoutes" type="number" value="${editedClimbingSite.nbRoutes}" cssClass="form-control item" placeholder="Nombre de voies" required="true" autofocus=""/>
                    <form:errors  path="nbRoutes" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="quotation" type="text" value="${editedClimbingSite.quotation}" cssClass="form-control item" placeholder="Cotation" required="true" autofocus=""/>
                    <form:errors  path="quotation" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:textarea path="info" type="text" rows="5" value="${editedClimbingSite.info}" cssClass="form-control item" placeholder="Infos du site" autofocus=""/>
                    <form:errors  path="info" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:button class="btn btn-primary btn-block" type="submit">Enregistrer</form:button>
                </div>
                <div class="form-group">
                    <a href="<c:url value="/climbingSiteList"/>" class="btn btn-outline-primary btn-block">
                        Annuler
                    </a>
                </div>
            </form:form>
        </div>
    </section>
</main>

