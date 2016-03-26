<g:render template="/partials/showHeader" model="[beanName:'s3Asset',isEdit:true]"/>
<div class="row">

    <div class="col-sm-12 col-md-12">
        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <g:hasErrors bean="${s3AssetInstance}">
            <bootstrap:alert class="alert-error">
                <ul>
                    <g:eachError bean="${s3AssetInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </bootstrap:alert>
        </g:hasErrors>

        <g:form class="well small form-horizontal" action="save" enctype="multipart/form-data" >

                <g:render template="form"/>
                <br>
                <g:hiddenField name="version" value="${s3AssetInstance?.version}" />
                <g:hiddenField name="id" value="${s3AssetInstance?.id}" />
                <div class="form-actions">
                    <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'s3Asset', action:'edit')}','','La validation du document  a réussi','listContent');return false;" >

                        <g:message code="default.button.update.label" default="Update" />
                    </div>
                    <div onclick="submitForm($(this).closest('form'),
                            '${createLink(controller:'s3Asset', action:'delete')}','','La suppression du document a réussi','listContent');return false;" class="btn-flat  btn-primary" formnovalidate>
                        <i class="glyphicon glyphicon-trash"></i>
                        <g:message code="default.button.delete.label" default="Delete" />
                    </div>
                </div>
        </g:form>

    </div>

</div>
