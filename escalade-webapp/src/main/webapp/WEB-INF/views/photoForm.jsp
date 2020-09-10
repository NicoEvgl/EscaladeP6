<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page photoForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Ajouter photo pour ${}</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <div>
                <form:form cssClass="form-group" method="post" action="addPhotoProcess" modelAttribute="photo">
                    <div class="form-group">
                        <form:input path="name" type="text" cssClass="form-control item" placeholder="Nom de la photo" required="true" autofocus=""/>
                        <form:errors  path="name" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <form:input path="url" type="text" cssClass="form-control item" placeholder="URL de la photo" required="true" autofocus=""/>
                        <form:errors  path="url" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <form:button type="submit" class="btn btn-primary btn-block">Enregistrer</form:button>
                    </div>
                    <div class="form-group">
                        <a href="<c:url value="/climbingSite/${climbingSiteId}"/>" class="btn btn-outline-primary btn-block">
                            Annuler
                        </a>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
</main>
