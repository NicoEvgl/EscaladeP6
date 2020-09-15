<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page guideBookReservationForm-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-login">Demande de réservation</h2>
                <p>
                    <c:if test="${!empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </p>
                <c:if test="${reservationStatus == 'null' || reservationStatus == 'Refusée' || reservationStatus == 'Annulée' || reservationStatus == 'Clôturée'}">
                    <p>
                        Vous êtes sur le point de faire une demande de réservation pour ce topo,
                        Notre association se contente de mettre en relation les utilisateurs.
                        Si le propriétaire accepte votre demande, il vous contactera directement.
                    </p>
                    <p>Vous pouvez consulter le statut de votre demande dans votre espace personnel.</p>
                </c:if>
                <c:if test="${reservationStatus == 'Acceptée'}">
                    <p>
                        Vous avez déjà fait une demande de réservation pour ce topo.
                        Celle-ci a été acceptée par le propriétaire.
                        Celui-ci prendra rapidement contact avec vous par mail.
                    </p>
                    <p>Pensez à consulter votre boîte mail.</p>
                </c:if>
                <c:if test="${reservationStatus == 'En attente'}">
                    <p>
                        Vous avez déjà fait une demande de réservation pour ce topo.
                        Celle-ci n'a pas encore été traitée par le propriétaire.
                    </p>
                    <p>Vous pouvez consulter le statut de votre demande dans votre espace personnel.</p>
                </c:if>
            </div>
            <form:form modelAttribute="guideBookReservation" method="post" action="guideBookReservationProcess/${guideBookId}">
                <div class="form-group">
                    <c:choose>
                        <c:when test="${reservationStatus == 'Acceptée' || reservationStatus == 'En attente'}">
                            <a href="<c:url value="/personalSpace/${userInSessionId}"/>">
                                <button type="button" class="btn btn-primary btn-block">Espace personnel</button>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <form:button class="btn btn-primary btn-block" type="submit">Valider la demande</form:button>
                        </c:otherwise>
                    </c:choose>
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
