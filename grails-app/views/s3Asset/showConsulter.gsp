<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="pageLayout">
        <title>${s3AssetInstance?.title}</title>
    </head>
    <body>

    <div class="row">
    <div class="col-sm-2 col-md-2">
    </div>

    <div class="col-sm-6 col-md-6" style="background-color: white">

    <g:if test="${flash.message}">
        <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
    </g:if>
    <f:display bean="s3AssetInstance" property="title"/>
    <f:display bean="s3AssetInstance" property="description"/>

        <a href="<s3:createLinkTo asset='${s3AssetInstance}'/>">
           <p>Télécharger le document</p>
    </a>
      %{--<dl>--}%

        %{--<g:if test="${s3AssetInstance?.title}">--}%
                %{--<dt style="font-weight:bold ;display:inline">--}%
                    %{--<g:message code="s3Asset.title.label" default="Titre document"/> :--}%
                %{--</dt>--}%
                %{--<dt style="font-weight:normal;display:inline">--}%
                    %{--<g:fieldValue bean="${s3AssetInstance}" field="title"/>--}%
                %{--</dt>--}%
        %{--</g:if>--}%



        %{--<g:if test="${s3AssetInstance?.description}">--}%
                %{--<dt style="font-weight:bold ;display:inline">--}%
            %{--<g:message code="s3Asset.description.label" default="Description"/> :--}%
                %{--</dt>--}%
            %{--<dt style="font-weight:normal;display:inline">--}%
               %{--<g:fieldValue bean="${s3AssetInstance}" field="description"/>--}%
            %{--</dt>--}%
        %{--</g:if>--}%

                %{--<dt style="font-weight:bold;display:inline">--}%
                  %{--<a href="<s3:createLinkTo asset='${s3AssetInstance}'/>">--}%
                    %{--Télécharger le document--}%
                    %{--</a>--}%
                    %{----}%
                %{--</dt>--}%
            %{----}%

    %{--</dl>--}%


    </div>


    %{--<div id="documents" ></div>--}%
    %{--<div class="pagination">--}%
        %{--<util:remotePaginate update="listContent" action="list"  total="${S3Asset.count()}" controller="home" action="filterDocuments"--}%
                             %{--update="documents" max="5" alwaysShowPageSizes="true" pageSizes="[5, 10,15,20,100]"/>--}%

    %{--</div>--}%
    </div>
    <div class="col-sm-2 col-md-2">
    </div>
    </body>

</html>
