<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page photoEditForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Modifier photo -${editedPhoto.name}-</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <div>
                <form:form cssClass="form-group" method="post" action="editPhotoProcess/${editedPhoto.id}" modelAttribute="editedPhoto">
                    <div class="form-group">
                        <form:hidden path="id" value="${editedPhoto.id}"/>
                        <form:errors  path="id" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <form:hidden path="climbingSite.id" value="${editedPhoto.climbingSite.id}"/>
                        <form:errors  path="climbingSite.id" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <form:input path="name" type="text" value="${editedPhoto.name}" cssClass="form-control item" placeholder="Nom de la photo" required="true" autofocus=""/>
                        <form:errors  path="name" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <form:input path="url" type="text" value="${editedPhoto.url}" cssClass="form-control item" placeholder="URL du lien de la photo" required="true" autofocus=""/>
                        <form:errors  path="url" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <form:button type="submit" class="btn btn-primary btn-block">Enregistrer</form:button>
                    </div>
                    <div class="form-group">
                        <a href="<c:url value="/climbingSite/${editedPhoto.climbingSite.id}/photoList"/>" class="btn btn-outline-secondary">
                            Annuler
                        </a>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>