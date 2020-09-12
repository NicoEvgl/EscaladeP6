<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page commentEditForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Modifier commentaire</h2>
            </div>
            <form:form modelAttribute="editedComment" method="post" action="editCommentProcess/${id}">
                <div class="form-group">
                    <form:hidden path="climbingSite.id" value="${editedComment.climbingSite.id}"/>
                    <form:errors  path="climbingSite.id" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:hidden path="user.id" value="${editedComment.user.id}"/>
                    <form:errors  path="user.id" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:hidden path="creationDate" value="${editedComment.creationDate}"/>
                    <form:errors  path="creationDate" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:textarea path="commentText" value="${editedComment.commentText}" type="text" rows="5" cssClass="form-control item" placeholder="Commentaire" required="true" autofocus=""/>
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
