<%@ page import="simagri.MyJobConfig" %>
%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="adminLayout">--}%
		%{--<g:set var="entityName" value="${message(code: 'myJobConfig.label', default: 'MyJobConfig')}" />--}%
		%{--<title><g:message code="changeCron.myJobConfig"/></title>--}%
	%{--</head>--}%
	%{--<body>--}%
		<div class="row">

            <div class="col-sm-4 col-md-4">
                <div class="well small">
                    <ul class="nav nav-list">
                        <li class="nav-header"><g:message code="title.myJobConfig" /></li>

                        <li>
                            <g:link class="create" action="cron">
                                 <i class="glyphicon glyphicon-plus"></i>
                                <g:message code="changeCron.myJobConfig" />
                            </g:link>
                        </li>
                        <li>
                            <g:link class="create" action="start">
                                 <i class="glyphicon glyphicon-plus"></i>
                                <g:message code="startCron.myJobConfig" />
                            </g:link>
                        </li>
                        <li>
                            <g:link class="create" action="stop">
                                 <i class="glyphicon glyphicon-plus"></i>
                                <g:message code="stopCron.myJobConfig" />
                            </g:link>
                        </li>
                        <li>
                            <g:link class="create" action="edit">
                                 <i class="glyphicon glyphicon-plus"></i>
                                <g:message code="edit.myJobConfig" />
                            </g:link>
                        </li>
                    </ul>
                </div>
            </div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="changeCron.myJobConfig"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>

                <g:if test="${myJobConfigInstance?.cron}">
                    <dt><g:message code="myJobConfig.cron.label" default="Cron" /> :

                    <g:fieldValue bean="${myJobConfigInstance}" field="cron"/>
                    </dt>

                </g:if>


                <g:if test="${myJobConfigInstance?.statut}">
                    <dt><g:message code="myJobConfig.statut.label" default="Statut" /> :

                    <g:fieldValue bean="${myJobConfigInstance}" field="statut"/>
                    </dt>

                </g:if>


            </dl>

				<g:form class="well small" action="cron">
					<g:hiddenField name="id" value="${myJobConfigInstance?.id}" />
					<div class="form-actions">
                        <div type="submit" class="btn-flat  btn-primary">

                            <g:message code="default.button.executer.label" default="Create" />
                        </div>
                        <div type="submit" class="btn-flat  btn-primary" name="_action_start" >

                            <g:message code="default.button.start.label" default="Démarrer" />
                        </div>
                        <div type="submit" class="btn-flat  btn-primary" name="_action_stop" >

                            <g:message code="default.button.stop.label" default="Arrêter" />
                        </div>



					</div>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
