<%@ page import="simagri.CategorieProduit" defaultCodec="html" %>
<div class="control-group ${invalid ? 'error' : ''}">
	<label class="control-label" for="${property}">${label}</label>
	<div class="controls">
        <g:select name="role" from="${roleList}" value="${utilisateurInstance?.role}"  valueMessagePrefix="utilisateur.role" />
        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
	</div>
</div>