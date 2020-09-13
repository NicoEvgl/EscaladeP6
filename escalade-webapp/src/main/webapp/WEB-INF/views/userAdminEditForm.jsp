<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page userAdminEditForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Changer le rôle de l'utilisateur</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
            </div>
            <form:form method="post" action="editUserRoleProcess/${editedUser.id}" modelAttribute="editedUser">
                <div class="form-group">
                    <form:select path="role" cssClass="form-control">
                        <form:option value="">Role</form:option>
                        <form:options items="${roleList}"/>
                    </form:select>
                    <form:errors path="role" cssClass="error"/>
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
