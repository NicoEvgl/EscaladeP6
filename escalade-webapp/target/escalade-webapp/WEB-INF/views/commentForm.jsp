<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page commentForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Ajouter un commentaire</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <form:form modelAttribute="comment" method="post" action="addCommentProcess/${climbingSiteId}">
                <div class="form-group">
                    <form:textarea path="commentText" type="text" cssClass="form-control" rows="5" placeholder="Commentaire" required="true" autofocus=""/>
                    <form:errors  path="commentText" cssClass="error"/>
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
