<%@ page import="simagri.PageUtilisateur" %>
<div class="row myMenuContainer">
    <nav class="navbar navbar-custom no-bottom-padding" id="myNavmenu" role="navigation">
        %{--<div class="container">--}%
        %{--<div class="container">--}%
        <div class="container navbarborder">
            <div class="navbar-header">

                <div type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                    <span class="sr-only">toggle</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </div>
            </div>


            <ul class="nav pull-left navbar-custom navbar-nav " style="padding-left:100px">
                    <li data-render-menu="accueil"  class="${(activeMenu=='Accueil')?'active':''}">
                        <g:remoteLink controller="home" action="accueil" update="simagriIndex" method="GET" id="accueilNotLogged" onLoading="showSpinner();"
                                      onComplete="hideSpinner()"><strong>Accueil</strong>
                        </g:remoteLink></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><strong>Afrique Verte</strong> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <g:each var="pageUtil" in="${pagesAv}">
                                <li class="controller"> <g:remoteLink controller="pageUtilisateur" method="GET" action="showPage" update="simagriIndex" id="${pageUtil.id}" onLoading="showSpinner();"
                                                                      onComplete="hideSpinner()"><strong>${pageUtil.nom}</strong>
                                </g:remoteLink>
                                </li>
                            </g:each>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><strong>SIMAgri</strong> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <g:each var="pageUtil" in="${pagesSIMAgri}">
                                <li class="controller"> <g:remoteLink controller="pageUtilisateur"  method="GET" action="showPage" update="simagriIndex" id="${pageUtil.id}" onLoading="showSpinner();"
                                                                      onComplete="hideSpinner()"><strong>${pageUtil.nom}</strong>
                                </g:remoteLink>
                                </li>
                            </g:each>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><strong>Partenaires</strong> <b class="caret"></b></a>
                        <ul class="dropdown-menu">

                            <g:each var="pageUtil" in="${pagesPartenaires}">
                                <li class="controller"> <g:remoteLink controller="pageUtilisateur" method="GET" action="showPage" update="simagriIndex" id="${pageUtil.id}" onLoading="showSpinner();"
                                                                      onComplete="hideSpinner()"><strong>${pageUtil.nom}</strong>
                                </g:remoteLink>
                                </li>
                            </g:each>
                        </ul>
                    </li>

            </ul>    <!-- End The drop down menu -->
        </div>

    </nav>
</div>




