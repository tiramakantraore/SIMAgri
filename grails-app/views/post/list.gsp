
<%@ page import="simagri.Post" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title><g:message code="list.post"  /></title>

        <r:require module="export"/>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.post" /></li>

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
					<h1><g:message code="list.post" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table ">
					<thead>
						<tr>
                        <th></th>
						
							<th class="header"><g:message code="post.author.label" default="Author" /></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="category" title="${message(code: 'post.category.label', default: 'Category')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="createdAt" title="${message(code: 'post.createdAt.label', default: 'Created At')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="post" title="${message(code: 'post.post.label', default: 'Post')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="title" title="${message(code: 'post.title.label', default: 'Title')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${postInstanceList}" var="postInstance">
						<tr>
                            <td class="link">
                                <g:link action="show" id="${postInstance.id}" class="btn btn-small">Modifier&raquo;</g:link>
                            </td>
						
							<td>${fieldValue(bean: postInstance, field: "author")}</td>
						
							<td>${fieldValue(bean: postInstance, field: "category")}</td>
						
							<td><g:formatDate date="${postInstance.createdAt}" /></td>
						
							<td>${fieldValue(bean: postInstance, field: "post")}</td>
						
							<td>${fieldValue(bean: postInstance, field: "title")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${postInstanceTotal}" params="${filterParams}" />
                    <filterpane:filterButton text="Rechercher" />
				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Post" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
