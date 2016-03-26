<%@ page import="simagri.Post" %>



<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="post.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="author" name="author.id" from="${simagri.Utilisateur.list()}" optionKey="id" required="" value="${postInstance?.author?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="post.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="category" value="${postInstance?.category}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'comments', 'error')} ">
	<label for="comments">
		<g:message code="post.comments.label" default="Comments" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${postInstance?.comments?}" var="c">
    <li><g:link controller="comment" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="comment" action="create" params="['post.id': postInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'createdAt', 'error')} required">
	<label for="createdAt">
		<g:message code="post.createdAt.label" default="Created At" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="createdAt" precision="day"  value="${postInstance?.createdAt}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'post', 'error')} required">
	<label for="post">
		<g:message code="post.post.label" default="Post" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="post" value="${postInstance?.post}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: postInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="post.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" value="${postInstance?.title}"/>
</div>

