<%@ page defaultCodec="html" %>
<div class="fieldcontain control-group ${invalid ? 'error' : ''}">

    <div class="controls">
        <g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>
        <label class="control-label" for="${property}">${label}</label>  <%= widget %>
    </div>
</div>
