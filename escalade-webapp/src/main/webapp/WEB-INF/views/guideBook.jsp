<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page guideBook-page">
    <section class="clean-block clean-product dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">${guideBook.name.toUpperCase()}</h2>
            </div>
            <div class="block-content">
                <div class="product-info">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="gallery">
                                <div class="sp-wrap">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="info">
                                <h3>${guideBook.name.toUpperCase()}</h3>
                                <div class="summary">
                                    <table class="table table-striped table-responsive-sm">
                                        <tr>
                                            <th>Région : </th>
                                            <td>${guideBook.region}</td>
                                        </tr>
                                        <tr>
                                            <th>Date de parution : </th>
                                            <td>${guideBook.releaseDate}</td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="summary">
                                    <p>
                                        <c:out value="${guideBook.description}"/>
                                    </p>
                                    <c:if test="${!empty userInSessionId && userInSessionId != guideBook.user.id}">
                                        <c:if test="${guideBook.booked == false}">
                                            <div>
                                                <a href="<c:url value="/guideBookReservation/${guideBook.id}"/>" class="btn btn-outline-info btn-block">
                                                    Réserver topo
                                                </a>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>