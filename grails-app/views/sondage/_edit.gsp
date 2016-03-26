<%@ page import="simagri.Sondage" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'sondage.label', default: 'Sondage')}"/>
    <title><g:message code="edit.sondage"/></title>
</head>

<body>
<div class="row">

    <div class="col-sm-3 col-md-3">

    </div>

    <div class="col-sm-6 col-md-6">

        <div class="page-header">
            <h1><g:message code="edit.sondage"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <g:hasErrors bean="${sondageInstance}">
            <bootstrap:alert class="alert-error">
                <ul>
                    <g:eachError bean="${sondageInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </ul>
            </bootstrap:alert>
        </g:hasErrors>

            <g:form class="well small form-horizontal" action="edit" id="${sondageInstance?.id}">
                <g:hiddenField name="sondageId"/>
                <g:render template="form"/>
                     <br>
                    <div class="form-actions">
                         <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'sondage', action:'edit')}','','La validation de la page  a réussi','listContent');return false;" >

                            <g:message code="default.button.update.label" default="Update"/>
                        </div>
                        <div type="submit" class="btn-flat  btn-primary" name="_action_delete" formnovalidate=""
                                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                             <i class="glyphicon glyphicon-trash"></i>
                            <g:message code="default.button.delete.label" default="Delete"/>
                        </div>
                    </div>
            </g:form>
    </div>
    <g:render template='detail' model="['detail':null,'i':'_clone','hidden':true]"/>
    <div class="col-sm-3 col-md-3">

    </div>
</div>
<g:render template="sondagejs"/>
</body>
</html>
