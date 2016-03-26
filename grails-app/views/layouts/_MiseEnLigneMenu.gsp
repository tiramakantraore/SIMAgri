<div id="menu" >
    <div class="panel list-group no-left-padding no-right-padding" >

        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#configs" >Mises en ligne
            <i class="indicator icon-minus pull-right">
            </i></a>
        <div id="configs" class="sublinks collapse in">
            <g:remoteLink controller="mettreEnLigne" action="showPrix" data-type='item' class="list-group-item small" id="mettreEnLignePrix" update="listContent" method="GET" onLoading="showSpinner();"
             onComplete="hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}">Mise en Ligne des prix
            </g:remoteLink>
            <g:remoteLink controller="mettreEnLigne" action="showOffres" data-type='item' class="list-group-item small" id="offres" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}">Mise en Ligne des offres
            </g:remoteLink>
            <g:remoteLink controller="mettreEnLigne" action="showStocks" data-type='item' class="list-group-item small" id="stocks" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}">Mise en Ligne des stocks
            </g:remoteLink>
            <g:remoteLink controller="mettreEnLigne" action="showInfos" data-type='item' class="list-group-item small" id="infos" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}">Mise en Ligne des infos
            </g:remoteLink>
            <sec:ifAdmin>
                <g:remoteLink controller="mettreEnLigne" action="showProduits" data-type='item' class="list-group-item small" id="produits" update="listContent" method="GET" onLoading="showSpinner();"
                              onComplete="hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}">Mise en Ligne des produits
                </g:remoteLink>
                <g:remoteLink controller="mettreEnLigne" action="showMarches" data-type='item' class="list-group-item small" id="marches" update="listContent" method="GET" onLoading="showSpinner();"
                              onComplete="hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}"><g:message code="miseEnLigne.marches.text" default="Mise en Ligne des marchés" />
                </g:remoteLink>
                <g:remoteLink controller="mettreEnLigne" action="showUsers" data-type='item' class="list-group-item small" id="users" update="listContent" method="GET" onLoading="showSpinner();"
                              onComplete="hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}">Mise en Ligne des utilisateurs
                </g:remoteLink>
             </sec:ifAdmin>
        </div>


    </div>
</div>
