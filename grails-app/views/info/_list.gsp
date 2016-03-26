
<%@ page import="simagri.Info" %>

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

