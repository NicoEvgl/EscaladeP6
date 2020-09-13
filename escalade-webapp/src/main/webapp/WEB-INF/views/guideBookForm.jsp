<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page guideBookForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Ajouter un topo</h2>
            </div>
            <form:form modelAttribute="guideBook" method="post" action="addGuideBookProcess">
                <div class="form-group">
                    <form:input path="name" type="text" cssClass="form-control item" placeholder="Nom du topo" required="true" autofocus=""/>
                    <form:errors  path="name" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:textarea path="description" type="text" rows="5" cssClass="form-control item" placeholder="Description du topo" required="true" autofocus=""/>
                    <form:errors  path="description" cssClass="error"/>
                </div>
                <div class="form-group select-style">
                    <form:select path="region" cssClass="form-control">
                        <form:option value="">Région</form:option>
                        <form:options items="${regionList}"/>
                    </form:select>
                    <form:errors path="region" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input path="releaseDate" type="date" cssClass="form-control item" placeholder="Date de parution" required="true" autofocus=""/>
                    <form:errors  path="releaseDate" cssClass="error"/>
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