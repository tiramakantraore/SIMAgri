<%@ page defaultCodec="html" %>
<div class="fieldcontain control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${label}</label>
    <div class="controls">
        <%= widget %>
        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
    </div>
</div>

