
<div class="row">

    <div class="col-sm-2 col-md-2">

    </div>

    <div class="col-sm-10 col-md-10">
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
        <g:form class="form-horizontal" action="save" enctype="multipart/form-data" >
           <br>
            <div class="row ">

                <div class="col-xs-8">

                    <f:field bean="s3AssetInstance" property="title" required="true" >
                        <input type='text' name='title' value="${s3AssetInstance?.title}" class="form-control" autocomplete="off"/>
                    </f:field>
                </div>

                <div class="col-xs-4">
                </div>
            </div>
            <div class="row ">

                <div class="col-xs-8">

                    <f:field bean="s3AssetInstance" property="localPath" required="true" >
                        <input type="file" name='myFile' value="${s3AssetInstance?.localPath}" class="form-control" autocomplete="off"/>
                    </f:field>
                </div>

                <div class="col-xs-4">
                </div>
            </div>

            <div class="row ">

                <div class="col-xs-8">

                    <f:field bean="s3AssetInstance" property="description" required="true" >
                        <g:textArea name="description" cols="80" rows="5" maxlength="40" value="${s3AssetInstance?.description}" placeholder="Description du document" class="form-control" />
                    </f:field>
                </div>

                <div class="col-xs-4">
                </div>
            </div>


        </g:form>
        <br>
        <div type="submit" class="btn-flat  btn-primary validerDoc">

            <g:message code="default.button.create.label" default="Create" />
        </div>
    </div>
    </div>

