<%@ page import="simagri.NoteMarche" %>

<div class="tab-pane" id="idPrixForm">

        <ul class="nav nav-pills" >
                <li><a href="#IdNoteAValider" data-toggle="tab" title="${g.message(code:"noteMarcheAValider.text", default:"Notes de marché à valider")}">

                ${g.message(code:"noteMarcheAValider.text", default:"Notes de marché à valider")} </a></li>
            <li><a href="#IdNotesValides" data-toggle="tab" title="${g.message(code:"noteMarcheValides.text", default:"Notes de marché validées")}"> ${g.message(code:"noteMarcheValides.text", default:"Notes de marché validées")}</a></li>
            <li><a href="#IdNotesRejetes" data-toggle="tab" title="${g.message(code:"noteMarcheRejetes.text", default:"Notes de marché rejetées")}"> ${g.message(code:"noteMarcheRejetes.text", default:"Notes de marché rejetées")}</a></li>
        </ul>
        <div class="tab-content">

            <div class="tab-pane active" id="IdNoteAValider">
                <div id="listInfoContent">

                    <div class="row">

                        <div class="col-sm-12 col-md-12">
                            <div class="page-header">
                                <h1 class="info-title"><g:message code="list.notesAValider" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>

                            <div class="info-title">
                                <filterpane:filterButton text="Chercher" />
                            </div>
                            <g:each in="${noteMarcheInstanceList}" var="noteMarcheInstance">

                                <div class="infosContent">
                                    <g:if test="${noteMarcheInstance.contenu}">
                                        <h4> <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="noteMarche" action="show" update="listInfoContent" method="GET"
                                                      id="${noteMarcheInstance.id}"  params="{update:'listInfoContent'}"  class="link">
                                            ${noteMarcheInstance.titre}</g:remoteLink></h4>
                                        ${noteMarcheInstance.contenu.decodeHTML()}
                                        <p><prettytime:display date="${noteMarcheInstance?.date}" /></p>
                                    </g:if>
                               </div>
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="noteMarche" action="edit" update="listInfoContent" method="GET"
                                              id="${noteMarcheInstance.id}"  params="{update:'listInfoContent'}"  class="btn-flat  btn-primary">
                                    Modifier&raquo;</g:remoteLink>
                            </g:each>
                            <div class="pagination">
                                <util:remotePaginate update="listInfoContent" controller="noteMarche" action="list"  total="${noteMarcheInstanceTotal}" params="${filterParams}" />

                            </div>
                            <export:formats  controller="noteMarche" action="list" formats="['csv', 'excel', 'pdf']" />

                        </div>
                        <div class="col-sm-4 col-md-4">
                        </div>
                    </div>
                    <filterpane:filterPane dialog="true"  controller="noteMarche"  domain="simagri.NoteMarche" />

                </div>

             </div>
            <div class="tab-pane " id="IdNotesValides">
                <div id="listInfoValideContent">

                    <filterpane:filterButton text="Chercher" />
                    <div class="row">
                        <div class="col-sm-12 col-md-12">
                            <div class="page-header">
                                <h1 class="info-title"><g:message code="list.notesValidees" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>

                            <g:each in="${noteMarcheValideInstanceList}" var="noteMarcheInstance">

                                <g:if test="${noteMarcheInstance.contenu}">

                                    <div class="info-title">
                                        <h4> <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="noteMarche" action="show" update="listInfoContent" method="GET"
                                                           id="${noteMarcheInstance.id}"  params="{update:'listInfoContent'}"  class="link">
                                            ${noteMarcheInstance.titre}</g:remoteLink></h4>
                                    </div>

                                    ${noteMarcheInstance.contenu.decodeHTML()}
                                    <p><prettytime:display date="${noteMarcheInstance?.date}" /></p>
                                </g:if>


                            </g:each>
                            <div class="pagination">
                                <util:remotePaginate  controller="noteMarche" update="listInfoValideContent" action="listValidees"  total="${noteMarcheValideInstanceTotal}" params="${filterParams}" />

                            </div>
                            <export:formats  controller="noteMarche" action="listValidees" formats="['csv', 'excel', 'pdf']" />

                        </div>
                    </div>
                    <filterpane:filterPane  controller="noteMarche" dialog="true" domain="simagri.NoteMarche" />

                </div>
            </div>
            <div class="tab-pane" id="IdNotesRejetes">
            <g:render template="/noteMarche/abandonner"/>
                   
             </div>
        </div>

    </div>
<script type="text/javascript">

    $(document).ready(function() {
       $('a[href="#IdNoteAValider"]').tab('show');
    });

</script>
