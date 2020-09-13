<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<div class="view" style="background-image: url('https://cdn.crunchify.com/bg.png'); background-repeat: no-repeat; background-size: cover; background-position: center ;"/>
<main class="page guideBook-page">
    <section class="clean-block clean-services dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Chercher un topo</h2>
                <div class="container d-flex justify-content-center">
                    <form:form cssClass="form-inline" modelAttribute="guidebook" method="post" action="guideBook">
                        <div class="form-inline select-style">
                            <form:select path="name" cssClass="form-control mb-2 mr-sm-2">
                                <form:option value="">Topo</form:option>
                            </form:select>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                        <div class="form-inline select-style">
                            <form:select path="region" cssClass="form-control mb-2 mr-sm-2">
                                <form:option value="">Région</form:option>
                            </form:select>
                            <form:errors path="region" cssClass="error"/>
                        </div>
                        <div class="form-inline">
                            <form:button class="btn btn-secondary mb-2" type="submit">Rechercher</form:button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>
