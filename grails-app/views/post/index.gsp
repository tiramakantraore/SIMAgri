
<%@ page import="simagri.Post" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-post" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-post" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="post.author.label" default="Author" /></th>
					
						<util:remoteSortableColumn update="listContent" action="list" property="category" title="${message(code: 'post.category.label', default: 'Category')}" />
					
						<util:remoteSortableColumn update="listContent" action="list" property="createdAt" title="${message(code: 'post.createdAt.label', default: 'Created At')}" />
					
						<util:remoteSortableColumn update="listContent" action="list" property="post" title="${message(code: 'post.post.label', default: 'Post')}" />
					
						<util:remoteSortableColumn update="listContent" action="list" property="title" title="${message(code: 'post.title.label', default: 'Title')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${postInstanceList}" status="i" var="postInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${postInstance.id}">${fieldValue(bean: postInstance, field: "author")}</g:link></td>
					
						<td>${fieldValue(bean: postInstance, field: "category")}</td>
					
						<td><g:formatDate date="${postInstance.createdAt}" /></td>
					
						<td>${fieldValue(bean: postInstance, field: "post")}</td>
					
						<td>${fieldValue(bean: postInstance, field: "title")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<util:remotePaginate update="listContent" action="list"  total="${postInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
