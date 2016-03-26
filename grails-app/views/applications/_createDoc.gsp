
<div class="row">

    <div class="col-sm-1 col-md-1">

    </div>

    <div class="col-sm-8 col-md-8 col-sm-offset3 col-md-offset3">
        <h1><g:message code="create.s3Asset" /></h1>
        %{--enctype="multipart/form-data"--}%
        <g:form class="form-horizontal" action="saveDoc" method="post" enctype="multipart/form-data" >
            <g:if test="${flash.message}">
                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
            </g:if>
           <br>
            <div class="row ">

                <div class="col-xs-8">

                    <f:field bean="s3AssetInstance" property="title" required="true" input-class="form-control" input-autocomplete="off"/>

                </div>

                <div class="col-xs-4">
                </div>
            </div>
            <div class="row ">

                <div class="col-xs-8">

                    <f:field bean="s3AssetInstance" property="localPath" required="true"  input-class="form-control" input-autocomplete="off">
                        <input type="file"  id='localPath' name="${property}" value="${it.value}" class="form-control" autocomplete="off"/>
                    </f:field>

                    %{--<uploadr:add name="myUploadrName" path="/my/upload/path" direction="up" noSound="true" maxVisible="8" unsupported="/my/controller/action" rating="false" voting="false" colorPicker="false" maxSize="2048000" />--}%


                </div>

                <div class="col-xs-4">
                </div>
            </div>

            <div class="row ">

                <div class="col-xs-8">

                    <f:field bean="s3AssetInstance" property="description" required="true" >
                        <g:textArea name="${property}" cols="80" rows="5" maxlength="255" value="${it.value}" placeholder="Description du document" class="form-control" />
                    </f:field>
                </div>

                <div class="col-xs-4">
                </div>
            </div>
            <g:render template="/partials/asyncBtn" model="[theactionName:'saveDoc',btnClass:'btn-primary',btnName:'default.button.envoyer.label',inputField:'localPath',successMessage:'Le téléchargement  a réussi']"/>



        </g:form>
        <br>

    </div>
    </div>

