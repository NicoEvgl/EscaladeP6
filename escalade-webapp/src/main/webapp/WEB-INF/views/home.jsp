<%@ page pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>


<main class="page home-page">
    <section class="clean-block clean-info dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Bienvenue sur le site de l'association</h2>
                <h2 class="text-info">Les Amis de l'Escalade</h2>
                <p>
                    Notre site référence les falaises de France et du monde.
                </p>
                <p>
                    Vous souhaitez grimper et vous cherchez un site d’escalade ?
                </p>
                <p>
                    Nos fiches des sites d'escalade vous offriront une multitude d’informations
                    (localisation, type d'escalde, nombre de voies, secteurs ...) pour vous aider à faire votre choix.
                </p>
                <p>
                    Vous avez également la possibilité d'emprunter un topo à un membre, alors n'attendez plus et
                    GRIMPEZ avec nous !
                </p>
            </div>
            <div class="row align-items-center">
                <div class="col-md-6"><img class="img-thumbnail" src="<c:url value="/resources/img/home1.jpg"/>"/></div>
                <div class="col-md-6">
                    <h3 class="text-info"><a href="<c:url value="/climbingSiteList"/>">Consulter les sites de France</a></h3>
                    <div class="getting-started-info">
                        <p>
                            Il est possible de consulter la liste des sites déjà enregistrer par les membres de
                            l'association ou les utilisateurs inscrits.
                        </p>
                    </div>
                </div>
            </div>
            <div class="row align-items-center">
                <div class="col-md-6">
                    <h3 class="text-info"><a href="<c:url value="/guideBookList"/>">Consulter la liste des topos</a></h3>
                    <div class="getting-started-info">
                        <p>
                            Il est possible de consulter la liste des topos mis à disposition par les membres de
                            l'association ou les utilisateurs inscrits.</a>
                        </p>
                    </div>
                </div>
                <div class="col-md-6"><img class="img-thumbnail" src="<c:url value="/resources/img/home2.jpg"/>"/></div>
            </div>
        </div>
    </section>
</main>

<%@include file="footer.jsp"%>
