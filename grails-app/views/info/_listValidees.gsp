<%@ page import="simagri.Info" %>
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
                    <h4>

                        <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="show" update="listInfoValideContent" method="GET"
                                      id="${infoInstance.id}"  params="{update:'listInfoValideContent'}"  class="btn-flat  btn-primary">
                            ${infoInstance.titre}</g:remoteLink>
                        </h4>
                    ${infoInstance.contenu.decodeHTML()}
                %{--<p>${infoInstance.contenu}</p>--}%
                    <a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">
                        découvrir le lien
                    </a>
                    <p><prettytime:display date="${infoInstance?.date}" /></p>
                </g:if>


                <g:elseif test="${infoInstance.url}">
                    %{--<h4><g:link action="show" id="${infoInstance.id}">${infoInstance.titre}</g:link>--}%
                    %{----}%
                    %{----}%
                    %{--</h4>--}%
                    <h4>
                    <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="show" update="listInfoValideContent" method="GET"
                                  id="${infoInstance.id}"  params="{update:'listInfoValideContent'}"  class="btn-flat  btn-primary">
                        ${infoInstance.titre}</g:remoteLink>
                    </h4>

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
