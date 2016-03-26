
<%@ page import="simagri.Post" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title><g:message code="show.post"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.post" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.post" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.post" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="show.post"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${postInstance?.author}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="post.author.label" default="Author" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:link controller="utilisateur" action="show" id="${postInstance?.author?.id}">${postInstance?.author?.encodeAsHTML()}</g:link> </dt>
						
                        </dt>
                        </div>
					</g:if>
				
					<g:if test="${postInstance?.category}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="post.category.label" default="Category" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:fieldValue bean="${postInstance}" field="category"/>

                        
                        </dt>
                        </div>
					</g:if>
				
					<g:if test="${postInstance?.comments}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="post.comments.label" default="Comments" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:fieldValue bean="${postInstance}" field="comments"/>

                        
                        </dt>
                        </div>
					</g:if>
				
					<g:if test="${postInstance?.createdAt}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="post.createdAt.label" default="Created At" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:formatDate date="${postInstance?.createdAt}" />  </dt>
						
                        </dt>
                        </div>
					</g:if>
				
					<g:if test="${postInstance?.post}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="post.post.label" default="Post" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:fieldValue bean="${postInstance}" field="post"/>

                        
                        </dt>
                        </div>
					</g:if>
				
					<g:if test="${postInstance?.title}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="post.title.label" default="Title" /> :
                         </dt>
                        <dt style="font-weight:bold;display:inline">
						
							<g:fieldValue bean="${postInstance}" field="title"/>

                        
                        </dt>
                        </div>
					</g:if>
				
				</dl>

				<g:form >
					<g:hiddenField name="id" value="${postInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${postInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>

					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
