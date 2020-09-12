<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page routeEditForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Modifier la voie ${editedRoute.name}</h2>
            </div>
            <form:form modelAttribute="editedRoute" method="post" action="editRouteProcess/${editedRoute.id}">
                <div class="form-group">
                    <form:hidden path="id" value="${editedRoute.id}"/>
                    <form:errors path="id" cssClass="errors"/>
                </div>
                <div class="form-group">
                    <form:hidden path="sector.id" value="${editedRoute.sector.id}"/>
                    <form:errors path="sector.id" cssClass="errors"/>
                </div>
                <div class="form-group">
                    <form:input path="name" type="text" value="${editedRoute.name}" cssClass="form-control item" placeholder="Nom de la voie" required="true" autofocus=""/>
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
                    <form:input path="height" type="text"  value="${editedRoute.height}" cssClass="form-control item" placeholder="Hauteur de la voie" required="true" autofocus=""/>
                    <form:errors  path="height" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:button class="btn btn-primary btn-block" type="submit">Enregistrer</form:button>
                </div>
                <div class="form-group">
                    <a href="<c:out value="javascript:history.go(-1)"/>" class="btn btn-outline-primary btn-block">
                        Annuler
                    </a>
                </div>
            </form:form>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>
