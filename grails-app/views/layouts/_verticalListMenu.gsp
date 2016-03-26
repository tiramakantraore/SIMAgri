<div id="menu" >
    <div class="panel list-group no-left-padding no-right-padding" >
        %{--<div class="panel-heading">--}%
                <a href="#" class="accordion-toggle list-group-item groupclass" data-toggle="collapse" data-target="#configs" >Listes Configurations
                    <i class="indicator icon-minus pull-right"> </i>
                   </a>

      %{--</div>--}%

        <div id="configs" class="sublinks collapse in">
            <g:remoteLink controller="pageAccueil" action="list" data-type='item' class="list-group-item small" id="pageAccueil" update="listContent" method="GET" onLoading="showSpinner();"
             onComplete="hideSpinner('listContent');">Liste des pages d'accueil
            </g:remoteLink>

            <g:remoteLink controller="pageUtilisateur" action="list" data-type='item' class="list-group-item small" id="pageUtilisateur" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des pages utilisateur
            </g:remoteLink>
            %{--<g:link controller="pageUtilisateur" action="list" class="list-group-item small">Liste des pages utilisateur</g:link> --}%
            <g:remoteLink controller="monImage" action="list" data-type='item' class="list-group-item small" id="monImage" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des images
            </g:remoteLink>


        </div>
        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#acteurs" >Listes des acteurs
            <i class="indicator icon-minus pull-right">
            </i>
        </a>
        <div id="acteurs" class="sublinks collapse in">

            <g:remoteLink controller="utilisateur" action="list" data-type='item' params="{userType:'anonyme'}" class="list-group-item small" id="utilisateurs" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des utilisateurs anonymes
            </g:remoteLink>
            %{--<g:link controller="utilisateur" action="list" data-type='item' params="[isFullHtml:true,userType:'public']" data-pjax='#listContent' class="list-group-item small" >--}%
                %{--Liste des utilisateurs publics--}%
            %{--</g:link>--}%
            <g:remoteLink controller="utilisateur" action="list" data-type='item' params="{userType:'public'}" class="list-group-item small" id="utilisateurs" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des utilisateurs publics
            </g:remoteLink>
            %{--<g:link controller="utilisateur" action="listHtml" data-type='item' params="[userType:'public']" class="list-group-item small" onLoading="showSpinner();"--}%
                           %{--data-pjax='#listContent'>Liste des utilisateurs--}%
            %{--</g:link>--}%
            <g:remoteLink controller="utilisateur" action="list" data-type='item' params="{userType:'enqueteur'}" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.enqueteur" />
            </g:remoteLink>
            <g:remoteLink controller="performance" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des objectifs de performance
            </g:remoteLink>
            <g:remoteLink controller="utilisateur" action="list" data-type='item' params="{userType:'admin'}" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des administrateurs
            </g:remoteLink>
            <g:remoteLink controller="utilisateur" action="list" data-type='item' params="{userType:'fournisseur'}" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des fournisseurs
            </g:remoteLink>
            <sec:ifSuperViseur>
            <g:remoteLink controller="utilisateur" action="list" data-type='item' params="{userType:'superviseur'}" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des superviseurs
            </g:remoteLink>
            </sec:ifSuperViseur>
            <g:remoteLink controller="groupe" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des groupes
            </g:remoteLink>
            <sec:ifSuperViseur>
            <g:remoteLink controller="reseau" action="list" data-type='item'  class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.reseau" />
            </g:remoteLink>
            </sec:ifSuperViseur>

        </div>


        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#objetsValidables" >Listes des mises en ligne<i class="indicator icon-minus pull-right">
        </i></a>
        <div id="objetsValidables" class="sublinks collapse in">
            <g:remoteLink controller="s3Asset" action="list" data-type='item'  params="{userType:'public'}" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des documents
            </g:remoteLink>
            <g:remoteLink controller="listeDesPrix" action="show" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des prix
            </g:remoteLink>
            <g:remoteLink controller="listeDesStocks" action="show" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des stocks
            </g:remoteLink>
            <g:remoteLink controller="listeDesOffres" action="show" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des offres
            </g:remoteLink>
            <g:remoteLink controller="listeDesAlertes" action="show" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des alertes
            </g:remoteLink>
            <g:remoteLink controller="listeDesInfos" action="show" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des infos
            </g:remoteLink>
            <g:remoteLink controller="listeNoteMarche" action="show" data-type='item' class="list-group-item small" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des notes de marché
            </g:remoteLink>
            <g:remoteLink controller="SMSLog" action="list" data-type='item'  class="list-group-item small" id="sendsms" method="GET" update="listContent" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Journal des SMS
            </g:remoteLink>
            %{--<g:remoteLink controller="priceHolder" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des prix à valider--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="prix" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des prix validés--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="prixRejetes" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des prix rejetés--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="stockMagazin" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des stocks à valider--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="stockMagazin" action="listValides" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des stocks validés--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="stockMagazin" action="listRejetes" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des stocks rejetés--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="offre" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des offres à valider--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="offreValidee" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des offres validées--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="offreRejetee" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des offres rejetées--}%
            %{--</g:remoteLink>--}%


            %{--<g:remoteLink controller="alerteReseau" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des alertes à valider--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="alerteReseau" action="listValidees" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des alertes validées--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="alerteReseau" action="listRejets" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des alertes rejetées--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="alerteReseau" action="listesuspendues" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des alertes suspendues--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="info" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des infos à valider--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="info" action="listValidees" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des infos validées--}%
            %{--</g:remoteLink>--}%
            %{--<g:remoteLink controller="info" action="listRejetees" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des infos rejetées--}%
            %{--</g:remoteLink>--}%



        </div>
        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#paramDeBase" ><g:message code="list.parametres" />
            <i class="indicator icon-minus pull-right">
            </i></a>
        <div id="paramDeBase" class="sublinks collapse in">
        <sec:ifSuperAdmin>
            <g:remoteLink controller="operateur" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des operateurs
            </g:remoteLink>
            %{--<g:remoteLink controller="configProperty" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des paramètres systèmes--}%
            %{--</g:remoteLink>--}%
    </sec:ifSuperAdmin>
            %{--<g:remoteLink controller="geoname" action="list" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des codes GPS--}%
            %{--</g:remoteLink>--}%
            <g:remoteLink controller="civilite" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.civlites" />
            </g:remoteLink>
            <g:remoteLink controller="activite" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des professions
            </g:remoteLink>
            <g:remoteLink controller="entreprise" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des entreprises
            </g:remoteLink>
            <g:remoteLink controller="produit" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des produits
            </g:remoteLink>
            <g:remoteLink controller="categorieProduit" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.categorieProduit" />
            </g:remoteLink>
            <g:remoteLink controller="qualite" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.qualites" />
            </g:remoteLink>
            <g:remoteLink controller="mesure" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.unitesmesure" />
            </g:remoteLink>
            <g:remoteLink controller="mesureCorrespondance" data-type='item' action="saisie" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.tableEquivalenceMesure" />
            </g:remoteLink>
            %{--<g:remoteLink controller="mesureCorrespondance" data-type='item' action="list" class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"--}%
                          %{--onComplete="hideSpinner('listContent');">Liste des correspondances de mesures--}%
            %{--</g:remoteLink>--}%
            <g:remoteLink controller="marche" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.marche" />
            </g:remoteLink>
            <g:remoteLink controller="magazin" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des magazins
            </g:remoteLink>
            <g:remoteLink controller="service" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des services
            </g:remoteLink>
            <g:remoteLink controller="lieux" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des lieux
            </g:remoteLink>
            <g:remoteLink controller="commune" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des communes
            </g:remoteLink>
            <g:remoteLink controller="province" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Liste des provinces
            </g:remoteLink>
            <g:remoteLink controller="region" action="list" data-type='item' class="list-group-item small"  update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="list.region" />
            </g:remoteLink>

        </div>


    </div>
</div>
