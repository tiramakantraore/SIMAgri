<div id="menu" >
    <div class="panel list-group no-left-padding no-right-padding" >

        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#configs" >Applications
            <i class="indicator icon-minus pull-right">
            </i></a>
        <div id="configs" class="sublinks collapse in">
     <sec:ifAdmin  >
            <g:remoteLink controller="applications" action="showSMS" data-type='item' class="list-group-item small" id="sms" update="listContent" method="GET" onLoading="showSpinner();"
                                          onComplete="hideSpinner('listContent');">Envoyer des sms
            </g:remoteLink>

            <g:remoteLink controller="applications" action="showEmail" data-type='item' class="list-group-item small" id="sms" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Envoyer des mails
            </g:remoteLink>

            <g:remoteLink controller="applications" action="showAlertes" data-type='item' class="list-group-item small" id="alertes" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Configurer des alertes
            </g:remoteLink>
    </sec:ifAdmin>
            <g:remoteLink controller="applications" action="showCarteMarche" data-type='item' class="list-group-item small" id="carteMarches" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="carteDesMarches.text" default="Carte des marchés" />
            </g:remoteLink>
            <g:remoteLink controller="applications" action="showCarteMagazin" data-type='item' class="list-group-item small" id="cateMagazin" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Carte des magazins
            </g:remoteLink>
            <g:remoteLink controller="applications" action="showEvent" data-type='item' class="list-group-item small" id="events" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');"><g:message code="evenements.text" default="Evénements" />
            </g:remoteLink>
            <g:remoteLink controller="applications" action="showDoc" data-type='item' class="list-group-item small" id="docs" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Importer des documents
            </g:remoteLink>
            <g:remoteLink controller="applications" action="showQuizz" data-type='item' class="list-group-item small" id="quizz" update="listContent" method="GET" onLoading="showSpinner();"
                          onComplete="hideSpinner('listContent');">Configurer des quiz
            </g:remoteLink>
             <g:remoteLink controller="applications" action="showSondage" data-type='item' class="list-group-item small" id="quizz" update="listContent" method="GET" onLoading="showSpinner();"
                           onComplete="hideSpinner('listContent');">Configurer des sondages
             </g:remoteLink>

        </div>
        <a href="#" class="list-group-item groupclass" data-toggle="collapse" data-target="#download" ><g:message code="telecharger.label" default="Télécharger" />
            <i class="indicator icon-minus pull-right">
            </i></a>
        <div id="download" class="sublinks collapse in">
            <g:link controller="home" action="downloadSIMAgriMob" class="list-group-item small">SIMAgri Mobile</g:link>
        </div>

    </div>
</div>
