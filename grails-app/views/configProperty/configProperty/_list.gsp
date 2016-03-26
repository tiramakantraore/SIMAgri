
<%@ page import="com.dbconfig.ConfigProperty" %>
%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="main.gsp">--}%
		%{--<g:set var="entityName" value="${message(code: 'configProperty.label', default: 'ConfigProperty')}" />--}%
		%{--<g:render template="head"/>--}%
		<style type="text/css">
		td, tr{
			font-size: 12px;
		    overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		}
		table{
			table-layout: fixed;
		}
		</style>
		<script type="text/javascript">

			%{--<g:remoteFunction controller="configProperty" action="show" update="greetingBox"/>--}%

			function executeshow(name) {
				<g:remoteFunction controller="greeting" action="greetName" update="greetingBox" params="'name='+name"/>
			}
		function clicked(objId, configKey, value, data){
			$('#'+objId).prop('checked', true);
			$('#'+objId).prop('disabled', true);
			$('#' + objId).parent().parent().find('td:nth-child(4)').text(value);
			var configKey = $('#' + objId).parent().parent().find('td:nth-child(2)').text();
			var link = '<a href="${request.contextPath}/configProperty/show/' + data + '">' + configKey + '</a>';
			$('#' + objId).parent().parent().find('td:nth-child(2)').get(0).innerHTML = link;
		}
		</script>
	%{--</head>--}%
	%{--<body>--}%
<g:render template="/partials/showHeader"/>
		%{--<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all ui-resizable">--}%
			%{--<ul class="ui-tabs-nav ui-helper-clearfix ui-widget-header">--}%
				%{--<li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active">--}%
					%{--<g:link class="" controller="configProperty" action="list">Statut Propriété</g:link>--}%
				%{--</li>--}%
				%{--<li class="ui-state-default ui-corner-top">--}%
					%{--<g:link class="" controller="configProperty" action="create">Créer Propriété</g:link>--}%
				%{--</li>--}%
			%{--</ul>--}%
			%{--<div id="list-configProperty" class="content scaffold-list" role="main">--}%
				<h2>&nbsp;</h2>
				<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>



                <table class="table ">
                    <thead>
                    <tr>
                        <th></th>
                        <util:remoteSortableColumn update="listContent" action="list" property="configKey" title="${message(code: 'configProperty.config.name.label', default: 'Nom paramètre')}" />
                        <th><g:message code="configProperty.config.file.value.label" default="Valeur dans le fichier" /></th>
                        <util:remoteSortableColumn update="listContent" action="list" property="dbProperty" title="${message(code: 'configProperty.db.value.label', default: 'Valeur dans la base')}" />
                        <util:remoteSortableColumn update="listContent" action="list" property="currentProperty" title="${message(code: 'configProperty.current.value.label', default: 'Valeur actuelle')}" />


                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                    <g:each in="${comparedProperties}" status="i" var="comparedProperty">

						<td>
                            <g:if test="${isInDb || comparedProperty.dbProperty}">
                                <input type="checkbox" id="checkbox_${i}" name="id" value="${comparedProperty.configKey}" checked disabled />
                            </g:if>
                            <g:else>
                                <g:checkBox name='checkbox_${i}'
                                                value="${comparedProperty.configKey}" checked="false"
                                                onclick="${remoteFunction(action:'addToFrequentlyUsedList', params: [configKey:comparedProperty.configKey, value:comparedProperty.fileProperty], onSuccess: "clicked('checkbox_${i}', '${comparedProperty.configKey}', '${comparedProperty.fileProperty}', data)" )}" />
                            </g:else>
					   	</td>
                            <g:if test="${comparedProperty.dbId != null}">
                                <td class="link"><g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="configProperty" action="edit" update="listContent" method="GET"
                                              id="${comparedProperty.dbId}" params="[update:'listContent']">
                                    ${fieldValue(bean: comparedProperty, field: "configKey")}</g:remoteLink></td>

                                %{--<td><g:link action="show" id="${comparedProperty.dbId}">${fieldValue(bean: comparedProperty, field: "configKey")}</g:link></td>--}%
                            </g:if>
                            <g:else>
                                <td>${fieldValue(bean: comparedProperty, field: "configKey")}</td>
                            </g:else>

                            <td>${fieldValue(bean: comparedProperty, field: "fileProperty")}</td>

                            <td>${fieldValue(bean: comparedProperty, field: "dbProperty")}</td>

                            <td>${fieldValue(bean: comparedProperty, field: "currentProperty")}</td>


                        </tr>
                    </g:each>
                    </tbody>
                    <tr>
                        <td colspan="5">Total Records: ${totalNum}</td>
                    </tr>
                </table>

                <export:formats formats="['csv', 'excel', 'pdf']" />
			%{--</div>--}%
		%{--</div>--}%
	%{--</body>--}%
%{--</html>--}%
