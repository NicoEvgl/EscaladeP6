<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page userEditForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Modifier le profil</h2>
            </div>
            <form:form method="post" action="editUserProcess/${userEdit.id}" modelAttribute="userEdit">
                <div class="form-group">
                    <form:hidden path="id" value="${userEdit.id}"/>
                    <form:errors path="id" cssClass="errors"/>
                </div>

                        <div class="form-group select-style">
                            <form:select path="role" cssClass="form-control">
                                <form:option value="">Rôle</form:option>
                                <form:options items="${roleList}"/>
                            </form:select>
                            <form:errors path="role" cssClass="error"/>
                        </div>


                <div class="form-group">
                    <form:select path="gender" type="text" value="${userEdit.gender}" cssClass="form-control" placeholder="Genre" required="true" autofocus="">
                        <form:option value="" label="Civilité"/>
                        <form:option value="M" label="Monsieur"/>
                        <form:option value="F" label="Madame"/>
                    </form:select>
                </div>
                <div class="form-group">
                    <form:input path="firstName" type="text" value="${userEdit.firstName}" cssClass="form-control item" placeholder="Prénom" required="true" autofocus=""/>
                    <form:errors  path="firstName" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="lastName" type="text" value="${userEdit.lastName}" cssClass="form-control item" placeholder="Nom" required="true" autofocus=""/>
                    <form:errors  path="lastName" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="username" type="text" value="${userEdit.username}" cssClass="form-control item" placeholder="Pseudo" required="true" autofocus=""/>
                    <form:errors  path="username" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="email" type="email" value="${userEdit.email}" cssClass="form-control item" placeholder="Email" required="true" autofocus=""/>
                    <form:errors  path="email" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="address" type="text" value="${userEdit.address}" cssClass="form-control item" placeholder="Adresse" required="true" autofocus=""/>
                    <form:errors  path="address" cssClass="error"/>

                    <form:input path="address2" type="text" value="${userEdit.address2}" cssClass="form-control item" placeholder="appartement, étage..." required="false" autofocus=""/>
                    <form:errors  path="address2" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="zip" type="text" value="${userEdit.zip}" cssClass="form-control item" placeholder="Code Postal" required="true" autofocus=""/>
                    <form:errors  path="zip" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="city" type="text" value="${userEdit.city}" cssClass="form-control item" placeholder="Ville" required="true" autofocus=""/>
                    <form:errors  path="city" cssClass="error"/>
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