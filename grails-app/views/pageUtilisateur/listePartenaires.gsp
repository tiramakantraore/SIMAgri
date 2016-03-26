
<%@ page import="simagri.PageUtilisateur" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="pageLayout">
		<g:set var="entityName" value="${message(code: 'pageUtilisateur.label', default: 'PageUtilisateur')}" />
		<title><g:message code="list.partenaires"  /></title>

	</head>
	<body>
    <div class="row" id="simagriIndex">
        <div class="col-sm-2 col-md-2">
        </div>
        <div class="col-sm-8 col-md-8">
            <div class="page-header">
                <h1><g:message code="list.partenaires" /></h1>
            </div>
            <g:if test="${flash.message}">
                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
            </g:if>
        </div>
        <div class="col-sm-2 col-md-2">
        </div>
    </div>
    <div class="row">
        <div class="col-sm-2 col-md-2">
        </div>
        <div class="col-sm-8 col-md-8">
        <div class="row">


                    <g:each in="${pageUtilisateurInstanceList}" status="i" var="entryInstance">
                        <h2>${entryInstance?.nom}</h2>
                                <g:if test="$entryInstance?.contenu?.trim()">
                                    <bill:imageWithText texte="${entryInstance?.contenu?.prettify()}" imageURL="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:entryInstance?.id])}" imagePosition="${entryInstance?.alignement?.name() }"/>
                                </g:if>
                        <div class="entry-adress">
                            <p>${entryInstance?.adresse}</p>
                            <p>${entryInstance?.email}</p>
                            <p>${entryInstance?.mobile}</p>
                            <p>${entryInstance?.telephone}</p>
                        </div>


                    </g:each>

				%{--<div class="pagination">--}%
					%{--<util:remotePaginate update="listContent" action="list"  total="${pageUtilisateurInstanceTotal}" params="${filterParams}" />--}%
                    %{--<filterpane:filterButton text="Rechercher" />--}%
				%{--</div>--}%
     		</div>
        </div>
        <div class="col-sm-2 col-md-2">
        </div>
    </div>
    <script type="text/javascript">

        $(document).ready(function(){
            openMenuOnHover();

        })


    </script>
    </body>
</html>
