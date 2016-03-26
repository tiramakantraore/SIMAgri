<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta name="layout" content="adminLayout">

  <title><g:message code="mettreAJourAgenda.label"/></title>

    <r:require modules="calendar" />
</head>
<body>

%{--<div class="nav" role="navigation">--}%
    %{--<ul>--}%
        %{--<li><a href="${createLink(uri: '/accueil')}" class="home">Retour</a></li>--}%
        %{--<li><g:link action="index" class="calendar">Calendrier</g:link></li>--}%
        %{--<li><g:link action="create" class="create">Nouvel événement</g:link></li>--}%
    %{--</ul>--}%
%{--</div>--}%
<div class="row">


    <div class="col-sm-4 col-md-4">

    </div>
    <div class="col-sm-6 col-md-6">
        <div class="page-header">
            <h1><g:message code="mettreAJourAgenda.label"/></h1>
        </div>
        <div id="calendar"></div>
    </div>
    <div class="col-sm-4 col-md-4">

    </div>
</div>

</body>
</html>