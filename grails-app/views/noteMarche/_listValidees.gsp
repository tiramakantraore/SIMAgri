<%@ page import="simagri.Info" %>
<filterpane:filterButton text="Chercher" />
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div class="page-header">
            <h1 class="info-title"><g:message code="list.noteMarcheValidees" /></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <div class="info-title">

        </div>
        <g:each in="${noteMarcheValideInstanceList}" var="noteMarcheInstance">
            <div class="infosContent">
                <g:if test="${noteMarcheInstance.contenu}">

                %{--<span class="infos-date">${noteMarcheInstance.date}</span>--}%
                    <h4>

                        <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="show" update="listNoteMarcheValideContent" method="GET"
                                      id="${noteMarcheInstance.id}"  params="{update:'listInfoValideContent'}"  class="btn-flat  btn-primary">
                            ${noteMarcheInstance.titre}</g:remoteLink>
                        </h4>
                    ${noteMarcheInstance.contenu.decodeHTML()}

                </g:if>



                <p><prettytime:display date="${noteMarcheInstance?.date}" /></p>
            </div>
            <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="info" action="edit" update="listInfoValideContent" method="GET"
                          id="${noteMarcheInstance.id}"  params="{update:'listInfoValideContent'}"  class="btn-flat  btn-primary">
                Modifier&raquo;</g:remoteLink>
        </g:each>
    %{--</tbody>--}%
    %{--</table>--}%
        <div class="pagination">
            <util:remotePaginate  controller="noteMarche" update="listInfoValideContent" action="listValidees"  total="${noteMarcheValideInstanceTotal}" params="${filterParams}" />

        </div>
        <export:formats  controller="info" action="listValidees" formats="['csv', 'excel', 'pdf']" />

    </div>
</div>
<filterpane:filterPane  controller="info" dialog="true" domain="simagri.NoteMarche" />
