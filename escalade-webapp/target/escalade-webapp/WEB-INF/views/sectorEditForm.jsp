
<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page sectorEditForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Modifier secteur ${editedSector.name}</h2>
            </div>
            <form:form modelAttribute="editedSector" method="post" action="editSectorProcess/${editedSector.id}">
                <div class="form-group">
                    <form:hidden path="climbingSite.id" value="${editedSector.climbingSite.id}"/>
                    <form:errors path="climbingSite.id" cssClass="errors"/>
                </div>
                <div class="form-group">
                    <form:input path="name" value="${editedSector.name}" type="text" cssClass="form-control item" placeholder="Nom du secteur" required="true" autofocus=""/>
                    <form:errors  path="name" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:textarea path="description" value="${editedSector.description}" type="text"  rows="5" cssClass="form-control item" placeholder="Description du secteur" required="true" autofocus=""/>
                    <form:errors  path="description" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:button class="btn btn-primary btn-block" type="submit">Enregistrer</form:button>
                </div>
                <div class="form-group">
                    <a href="<c:url value="/climbingSite/${climbingSiteId}"/>" class="btn btn-outline-primary btn-block">
                        Annuler
                    </a>
                </div>
            </form:form>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>