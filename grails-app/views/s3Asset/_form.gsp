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

