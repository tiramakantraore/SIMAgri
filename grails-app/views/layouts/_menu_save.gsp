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

        <sec:ifLoggedIn>
            <li data-render-menu="accueil" class="${((activeMenu=='Accueil')||(activeMenu==null))?'active':''}">
                %{--<g:link controller="home" action="index" >--}%
                    %{--<strong>Accueil</strong>--}%
                %{--</g:link>--}%
                <g:link controller="home" action="accueil"  data-pjax='#body'><strong>Accueil</strong>
                </g:link>
             </li>

            <li data-render-menu="tableauBord" class="${(activeMenu=='Tableau')?'active':''}">
                %{--<g:link controller="dashboard" action="accueil"> <strong>Tableau de bord</strong></g:link>--}%
            <g:link controller="dashboard" action="accueil"  data-pjax='#body'><strong>Tableau de bord</strong>
            </g:link>
            </li>
            <li data-render-menu="mettreEnLigne" class="${(activeMenu=='MiseEnLigne')?'active':''}" >
                <g:link controller="mettreEnLigne" action="show" data-pjax='#body'><strong>Mises en ligne</strong>
                </g:link>
            </li>

            <sec:ifAdmin>
                <li data-render-menu="listes" class="${(activeMenu=='Listes')?'active':''}">
                    <g:link controller="listes" action="show" data-pjax='#body'><strong>Listes</strong>
                    </g:link>
                </li>
            </sec:ifAdmin>
            <li data-render-menu="applications" class="${(activeMenu=='Applications')?'active':''}">
                <g:link controller="applications" action="show" data-pjax='#body'><strong>Applications</strong>
                </g:link>
            </li>

            <sec:ifAdmin  >
                <li data-render-menu="administration" class="${(activeMenu=='Administration')?'active':''}">
                    <g:link controller="administration" action="show" data-pjax='#body' ><strong>Admin</strong>
                    </g:link>
                </li>
            </sec:ifAdmin>
            <li data-render-menu="profil" class="${(activeMenu=='Profil')?'active':''}">
                <g:link controller="utilisateur" action="editProfile" params="[isChange:'true']" data-pjax='#body' >
                    <i class="glyphicon glyphicon-user"></i>
                    <strong>Profil</strong>
                </g:link>
                %{--<g:remoteLink controller="utilisateur" action="edit" params="{isChange:'true'}" method="GET" update="body" id="profil" onLoading="showSpinner();"--}%
                              %{--onComplete="hideSpinner()"><i class="glyphicon glyphicon-user"></i><strong>Profil</strong>--}%
                %{--</g:remoteLink>--}%
            </li>
    </sec:ifLoggedIn>


<sec:ifNotLoggedIn>
    <li data-render-menu="accueil"  class="${(activeMenu=='Accueil')?'active':''}">

        <g:link controller="home" action="accueil"  data-pjax='#body' ><strong>Accueil</strong>
        </g:link>
         %{--<g:remoteLink controller="home" action="accueil" update="simagriIndex" method="GET" id="accueilNotLogged" onLoading="showSpinner();"--}%
                      %{--onComplete="hideSpinner()"><strong>Accueil</strong>--}%
        %{--</g:remoteLink>--}%
    </li>
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

</sec:ifNotLoggedIn>

</ul>    <!-- End The drop down menu -->

<div class="nav pull-right   navbar-custom navbar-nav ">
        <g:form name="searchForm" controller="search" action="searchAll" class="navbar-form navbar-left form-search form-inline" role="search" >

                <div class="input-group  my-search-group">
                    <input type="text" name="query" id="query" class="form-control"  placeholder="Chercher">
                    <div class="input-group-btn">
                        <div type="submit" class="btn-success btn-search-flat" target="_blank" >
                            <i class="glyphicon glyphicon-search"></i>
                        %{--<g:link  controller="search" action="searchAll"  target="_blank">--}%

                        %{--</g:link>--}%
                        </div>
                    </div>

                </div>

        </g:form>

    %{--<g:form name="searchForm"  class="navbar-form navbar-left form-search form-inline" role="search" >--}%


              %{--<div class="input-group  my-search-group">--}%
                  %{--<input type="text" name="query" id="query" class="form-control"  placeholder="Chercher">--}%
                  %{--<div class="input-group-btn">--}%
                      %{--<g:remoteLink params="{query:\$('#query').val()}"  action="searchAll" id="1"--}%
                                    %{--update="${searchUpdate}" class="btn-success btn-search-flat">--}%
                          %{--<i class="glyphicon glyphicon-search"></i>--}%
                      %{--</g:remoteLink>--}%
                  %{--</div>--}%

              %{--</div>--}%

    %{--</g:form>--}%

</div>    <!-- End The drop down menu -->


</div>
%{--</div>--}%
%{--</div>--}%

</nav>
</div>




