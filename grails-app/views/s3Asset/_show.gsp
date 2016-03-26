  
%{--<html>--}%
    %{--<head>--}%
        %{--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>--}%
        %{--<meta name="layout" content="adminLayout">--}%
        %{--<title><g:message code="show.s3Asset" /></title>--}%
    %{--</head>--}%
    %{--<body>--}%




    <div class="row">

    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
                <li class="nav-header"><g:message code="title.s3Asset"/></li>
                <li>
                    <g:link class="list" action="list">
                         <i class="glyphicon glyphicon-list"></i>
                        <g:message code="list.s3Asset"/>
                    </g:link>
                </li>
                <li>
                    <g:link class="create" action="create">
                         <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.s3Asset"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="col-sm4 col-md-4 ">

    <g:if test="${flash.message}">
        <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
    </g:if>
        <g:form >
        <f:display bean="s3AssetInstance" property="title"/>
        <f:display bean="s3AssetInstance" property="description"/>

        <dt style="font-weight:bold;display:inline">
                  <a href="<s3:createLinkTo asset='${s3AssetInstance}'/>">
                    Télécharger le document
                    </a>
          </dt>


        <div class="form-actions">
            <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                          id="${s3AssetInstance.id}" class="btn">
                <i class="icon-pencil"></i>
                <g:message code="default.button.edit.label" default="Edit" />
            </g:remoteLink>

            <div onclick="submitForm($(this).closest('form'),
                    '${createLink(controller:'s3Asset', action:'delete')}','','La suppression du document a réussi','listContent');return false;" class="btn-flat  btn-primary" formnovalidate>
                <i class="glyphicon glyphicon-trash"></i>
                <g:message code="default.button.delete.label" default="Delete" />
            </div>

        </div>
    </g:form>

    </div>

    </div>
    %{--</body>--}%

%{--</html>--}%
