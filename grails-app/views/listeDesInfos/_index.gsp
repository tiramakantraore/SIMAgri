<%@ page import="simagri.Info" %>

<div class="tab-pane" id="idPrixForm">

        <ul class="nav nav-pills" >

            <li><a href="#IdInfosAValider" data-toggle="tab" title="${g.message(code:"infoAValider.text", default:"Infos à valider")}"> ${g.message(code:"infoAValider.text", default:"Infos à valider")} </a></li>
            <li><a href="#IdInfosValides" data-toggle="tab" title="${g.message(code:"infoValides.text", default:"Infos validées")}"> ${g.message(code:"infoValides.text", default:"Infos validées")}</a></li>
            <li><a href="#IdInfosRejetes" data-toggle="tab" title="${g.message(code:"infoRejetes.text", default:"Infos rejetées")}"> ${g.message(code:"infoRejetes.text", default:"Infos rejetées")}</a></li>
        </ul>
        <div class="tab-content">

            <div class="tab-pane active" id="IdInfosAValider">
                <div id="listInfoContent">

                    <div class="row">

                        <div class="col-sm-12 col-md-12">
                            <div class="page-header">
                                <h1 class="info-title"><g:message code="list.infoAValider" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>

                            <div class="info-title">
                                <filterpane:filterButton text="Chercher" />
                            </div>
                            <g:each in="${infoInstanceList}" var="infoInstance">
                                <div class="infosContent">
                                    <g:if test="${infoInstance.contenu}">
                                        <a name="${infoInstance.id}"></a>
                                    %{--<span class="infos-date">${infoInstance.date}</span>--}%
                                        <h4>
                                            ${infoInstance.titre}</h4>
                                        ${infoInstance.contenu.decodeHTML()}
                                    %{--<p>${infoInstance.contenu}</p>--}%
                                        <a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">
                                            découvrir le lien
                                        </a>
                                        <p><prettytime:display date="${infoInstance?.date}" /></p>
                                    </g:if>
                                </div>
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="edit" update="listInfoContent" method="GET"
                                              id="${infoInstance.id}"  params="{update:'listInfoContent'}"  class="btn-flat  btn-primary">
                                    Modifier&raquo;</g:remoteLink>
                            </g:each>
                        %{--</tbody>--}%
                        %{--</table>--}%
                            <div class="pagination">
                                <util:remotePaginate update="listInfoContent" controller="info" action="list"  total="${infoInstanceTotal}" params="${filterParams}" />

                            </div>
                            <export:formats  controller="info" action="list" formats="['csv', 'excel', 'pdf']" />

                        </div>
                        <div class="col-sm-4 col-md-4">
                        </div>
                    </div>
                    <filterpane:filterPane dialog="true"  controller="info"  domain="simagri.Info" />

                </div>

             </div>
            <div class="tab-pane " id="IdInfosValides">
                <div id="listInfoValideContent">

                    <filterpane:filterButton text="Chercher" />
                    <div class="row">
                        <div class="col-sm-12 col-md-12">
                            <div class="page-header">
                                <h1 class="info-title"><g:message code="list.infoValidees" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>

                            <div class="info-title">

                            </div>
                            <g:each in="${infoValideInstanceList}" var="infoInstance">
                                <div class="infosContent">
                                    <g:if test="${infoInstance.contenu}">

                                    %{--<span class="infos-date">${infoInstance.date}</span>--}%
                                        <h4> <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="show" update="listInfoContent" method="GET"
                                                           id="${infoInstance.id}"  params="{update:'listInfoContent'}"  class="link">
                                            ${infoInstance.titre}</g:remoteLink></h4>

                                        ${infoInstance.contenu.decodeHTML()}
                                    %{--<p>${infoInstance.contenu}</p>--}%
                                        <a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">
                                            découvrir le lien
                                        </a>
                                        <p><prettytime:display date="${infoInstance?.date}" /></p>
                                    </g:if>


                                    <g:elseif test="${infoInstance.url}">
                                        <h4> <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="show" update="listInfoContent" method="GET"
                                                           id="${infoInstance.id}"  params="{update:'listInfoContent'}"  class="link">
                                            ${infoInstance.titre}</g:remoteLink></h4>
                                        <g:if test="${infoInstance?.url?.contains('youtube')}">
                                            <g:video videoKey="${infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""}" width="100%" height="350px"/>

                                        </g:if>
                                        <g:elseif test="${infoInstance.url}">
                                            <a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">
                                                découvrir le lien
                                            </a>
                                        </g:elseif>

                                        <p><prettytime:display date="${infoInstance?.date}" /></p>


                                    </g:elseif>
                                </div>
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="edit" update="listInfoValideContent" method="GET"
                                              id="${infoInstance.id}"  params="{update:'listInfoValideContent'}"  class="btn-flat  btn-primary">
                                    Modifier&raquo;</g:remoteLink>
                            </g:each>
                        %{--</tbody>--}%
                        %{--</table>--}%
                            <div class="pagination">
                                <util:remotePaginate  controller="info" update="listInfoValideContent" action="listValidees"  total="${infoValideInstanceTotal}" params="${filterParams}" />

                            </div>
                            <export:formats  controller="info" action="listValidees" formats="['csv', 'excel', 'pdf']" />

                        </div>
                    </div>
                    <filterpane:filterPane  controller="info" dialog="true" domain="simagri.Info" />

                </div>
            </div>
            <div class="tab-pane" id="IdInfosRejetes" >
            <g:render template="/info/abandonner"/>
                   
             </div>
        </div>

    </div>
<script type="text/javascript">

    $(document).ready(function() {
       $('a[href="#IdInfosAValider"]').tab('show');
    });

</script>
