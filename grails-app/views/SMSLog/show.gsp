
<%@ page import="simagri.SMSLogger" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'SMSLogger.label', default: 'SMSLogger')}" />
		<title><g:message code="show.SMSLogger"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.SMSLogger" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.SMSLogger" />
							</g:link>
						</li>

					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="show.SMSLogger"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${SMSLoggerInstance?.message}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="SMSLogger.message.label" default="Message" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:fieldValue bean="${SMSLoggerInstance}" field="message"/>

                        
                        </dt>
                        </div>
					</g:if>
				
					<g:if test="${SMSLoggerInstance?.date}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="SMSLogger.date.label" default="Date" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:formatDate date="${SMSLoggerInstance?.date}" />  </dt>

                        </div>
					</g:if>
				
					<g:if test="${SMSLoggerInstance?.isIn}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="SMSLogger.isIn.label" default="Is In" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:formatBoolean boolean="${SMSLoggerInstance?.isIn}" />  </dt>

                        </div>
					</g:if>
				
					<g:if test="${SMSLoggerInstance?.user}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="SMSLogger.user.label" default="User" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:link controller="utilisateur" action="show" id="${SMSLoggerInstance?.user?.id}">${SMSLoggerInstance?.user?.encodeAsHTML()}</g:link> </dt>

                        </div>
					</g:if>
				
				</dl>

				<g:form >
					<g:hiddenField name="id" value="${SMSLoggerInstance?.id}" />
					<div class="form-actions">


					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
