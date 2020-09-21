<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<main class="page updateClimbingAreaForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Modification du site ${tagedClimbingSite.name}</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <form:form method="post" action="climbingSiteTagProcess/${tagedClimbingSite.id}" modelAttribute="tagedClimbingSite">
                <div class="form-group">
                    <form:hidden path="id" value="${tagedClimbingSite.id}"/>
                    <form:errors  path="id" cssClass="error"/>
                </div>
                <div class="form-group">
                    <c:if test="${sessionScope.userInSessionRole == 'Admin' || sessionScope.userInSessionRole == 'Member'}">
                        <form:label path="certified">Officiel Les Amis de l'Escalade</form:label>
                        <form:checkbox path="certified" value="${registeredClimbingSite.certified}" cssClass="form-check-inline"/>
                        <form:errors  path="certified" cssClass="error"/>
                    </c:if>
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

