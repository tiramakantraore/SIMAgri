<div id="menu">
    <div class="panel list-group">

        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#valider" >Validations
            <i class="indicator icon-minus pull-right">
            </i></a>
        <div id="valider" class="sublinks collapse in">

            <g:remoteLink controller="saisiePrix" action="validerPrix" data-type='item' class="list-group-item small" id="Prix" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Prix
            </g:remoteLink>
            <g:remoteLink controller="offre" action="validerOffre" data-type='item' class="list-group-item small" id="offre" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Offres
            </g:remoteLink>
            <g:remoteLink controller="stockMagazin" action="validerStocks" data-type='item' class="list-group-item small" method="GET" id="stocks" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Stocks
            </g:remoteLink>
            <g:remoteLink controller="alerteReseau" action="validerAlerte" data-type='item' class="list-group-item small" method="GET" id="alertes" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Alertes
            </g:remoteLink>
            <g:remoteLink controller="info" action="validerInfo" data-type='item' class="list-group-item small" method="GET" id="infos" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Informations
            </g:remoteLink>

            <g:remoteLink controller="noteMarche" action="validerNote" data-type='item' class="list-group-item small" method="GET" id="infos" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Notes de marché
            </g:remoteLink>

        </div>
        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#statistiquesgrp" >Statistiques
            <i class="indicator icon-minus pull-right">
            </i></a>
        <div id="statistiquesgrp" class="sublinks collapse in">

            <g:remoteLink controller="administration" action="statistiques" data-type='item' class="list-group-item small" id="Statistiques" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Les Statistiques
            </g:remoteLink>

            <g:remoteLink controller="administration" action="dataminingprix" data-type='item' class="list-group-item small"  method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Analyse des Prix
            </g:remoteLink>
            <g:remoteLink controller="administration" action="dataminingoffre" data-type='item' class="list-group-item small"  method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Analyse des Offres
            </g:remoteLink>
            <g:remoteLink controller="administration" action="dataminingstock" data-type='item' class="list-group-item small"  method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Analyse des Stocks
            </g:remoteLink>
            <g:remoteLink controller="administration" action="dataminingperformance" data-type='item' class="list-group-item small"  method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">${g.message(code:"analysePerfEnqueteurs.text", default:" Analyse des Performances des enquêteurs")}
            </g:remoteLink>
            <g:remoteLink controller="administration" action="dataminingsms" data-type='item' class="list-group-item small" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Analyse des SMS
            </g:remoteLink>
            %{--<g:link controller="home" action="webmail" data-type='item' class="list-group-item small"--}%
                         %{-->Webmail--}%
            %{--</g:link>--}%
        </div>
        %{--<a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#configurer" >Configurer</a>--}%
        %{--<div id="configurer" class="sublinks collapse in">--}%
            %{--<g:remoteLink controller="pageAccueil" action="create" data-type='item' class="list-group-item small" method="GET" id="pageAccueil" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter une page d'accueil--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="pageUtilisateur" action="create" data-type='item'  class="list-group-item small" method="GET" id="pageUtilisateur" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter une page utilisateur--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="monImage" action="create" data-type='item' class="list-group-item small" method="GET" id="pageUtilisateur" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter une image--}%
            %{--</g:remoteLink>--}%

        %{--</div>--}%
        %{--<a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#Acteurs" >Acteurs</a>--}%
        %{--<div id="Acteurs" class="sublinks collapse in">--}%
            %{--<g:remoteLink controller="utilisateur" action="create" data-type='item' params="{userType:'public'}" method="GET" class="list-group-item small" id="utilisateur" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter un utilisateur--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="groupe" action="create" data-type='item' class="list-group-item small" method="GET" id="groupe" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter un groupe--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="utilisateur" action="create" data-type='item' params="[userType:'enqueteur']" method="GET" class="list-group-item small" id="enqueteur" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter un enquêteur--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="utilisateur" action="create" data-type='item' params="[userType:'fournisseur']" method="GET" class="list-group-item small" id="fournisseur" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter un fournisseur--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="performance" action="validerPerformance" data-type='item' class="list-group-item small" method="GET" id="performance" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Assigner les objectifs de performance aux enquêteurs--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="marche" action="validerMarcheEnqueteur" data-type='item' class="list-group-item small" method="GET" id="validerMarcheEnqueteur" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Assigner les marchés aux enquêteurs--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="utilisateur" action="create" data-type='item' params="[userType:'admin']" class="list-group-item small" method="GET" id="administrateur" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter un administrateur--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="administrateurReseau" action="create" params="[userType:'admin']" class="list-group-item small" id="administrateurReseau" update="performance" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Assigner les réseaux aux administrateurs--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="groupe" action="create"  data-type='item' class="list-group-item small" id="groupe" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter un groupe--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="reseau" action="create" data-type='item' class="list-group-item small" id="reseau" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Ajouter un réseau--}%
            %{--</g:remoteLink>--}%
        %{--</div>--}%
    <sec:ifSuperAdmin>
        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#Maintenance" >Maintenance
            <i class="indicator icon-minus pull-right">
            </i></a>
        <div id="Maintenance" class="sublinks collapse in">
            %{--<g:remoteLink controller="post" action="create" data-type='item' class="list-group-item small" id="post" method="GET" update="listContent" onLoading="showSpinner();"--}%
                                          %{--onComplete="hideSpinner('listContent');">Ajouter un post--}%
            %{--</g:remoteLink>--}%
            <g:remoteLink controller="quartz" action="list" data-type='item' class="list-group-item small" id="myJobConfig" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Configuration du job
            </g:remoteLink>
            <g:remoteLink controller="sendsms" action="list" data-type='item'  class="list-group-item small" id="sendsms" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Tester SMS
            </g:remoteLink>
            <g:remoteLink controller="home" data-type='item' action="mettre_a_jour_les_roles"  class="list-group-item small" id="mettre_a_jour_les_roles" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">${g.message(code:"mettreAJourRole.text", default:"Mettre à jour les rôles")}
            </g:remoteLink>
            %{--<g:remoteLink controller="home" data-type='item' action="indexer_tables_de_base"  class="list-group-item small" id="indexer_tables_de_base" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Indexer les tables de base--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="home" data-type='item' action="indexer_prix"  class="list-group-item small" id="indexer_prix" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Indexer les prix--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="home" data-type='item' action="indexer_offres"  class="list-group-item small" id="indexer_offres" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Indexer les offres--}%
            %{--</g:remoteLink>--}%
            <g:remoteLink controller="home" data-type='item' action="supprimerAlertes"  class="list-group-item small" id="supprimerAlertes" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Supprimer toutes les alertes
            </g:remoteLink>
            %{--<g:remoteLink controller="securityInfo" data-type='item' action="config"  class="list-group-item small" id="securityInfo" method="GET" update="listContent" onLoading="showSpinner();"--}%
                                          %{--onComplete="hideSpinner('listContent');">Administration de la sécurité--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="user" action="search" data-type='item' class="list-group-item small" id="securityInfo" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Gestion des utilisateurs--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="requestmap" action="search" data-type='item' class="list-group-item small" id="requestmap" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Gestion des autorisations--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="role" action="search" data-type='item' class="list-group-item small" id="requestmap" method="GET" update="listContent" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Gestion des rôles--}%
            %{--</g:remoteLink>--}%
            <g:remoteLink controller="ConfigProperty" action="list"  data-type='item' class="list-group-item small" id="requestmap" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">${g.message(code:"gestionParamnDyn.text", default:"Gestion des paramètres dynamiques")}
            </g:remoteLink>
            <g:remoteLink controller="alerteReseau" action="listeexecute" data-type='item' class="list-group-item small" method="GET" id="listeexecute" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Notifications des alertes
            </g:remoteLink>
            %{--<g:link controller="asynchronousMail" action="list" target="_BLANK" data-type='item' class="list-group-item small">--}%
                         %{--Liste des mails--}%
            %{--</g:link>--}%


        </div>
    </sec:ifSuperAdmin>

    </div>
</div>
