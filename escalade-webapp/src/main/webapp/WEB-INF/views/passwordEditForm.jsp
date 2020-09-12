<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page passwordEditForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Modifier mot de passe</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <form:form method="post" action="editPasswordProcess/${userEdit.id}" modelAttribute="userEdit">

                <div class="form-group">
                    <form:label path="password">Nouveau mot de passe</form:label>
                    <form:password path="password" cssClass="form-control item" placeholder="Mot de passe" minlenght="6" pattern="^(?=.{6,}$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9]).*$" title="Le mot de passe doit contenir au moins 6 caractères avec au moins 1 majuscule et 1 chiffre" required="true"/>
                    <form:errors  path="password" cssClass="error"/>
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

<%@include file="footer.jsp"%>