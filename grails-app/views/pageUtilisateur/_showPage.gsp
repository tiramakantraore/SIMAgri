
<%@ page import="simagri.PageUtilisateur" %>
%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="pageLayout">--}%
		%{--<title>${pageUtilisateurInstance?.nom}</title>--}%

	%{--</head>--}%
	%{--<body>--}%
    <div class="row">
                <div class="col-sm-2 col-md-2">

                </div>
                <div class="col-sm-8 col-md-8 userPageContent" >
                    <h1>${pageUtilisateurInstance?.nom}</h1>
                    <g:if test="$pageUtilisateurInstance?.contenu?.trim()">
                        <bill:imageWithText texte="${pageUtilisateurInstance?.contenu?.prettify()}" imageURL="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageUtilisateurInstance?.id])}" imagePosition="${pageUtilisateurInstance?.alignement?.name() }" imageHeight="50"/>
                   </g:if>

                </div>
                <div class="col-sm-2 col-md-2">

                </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function(){

            openMenuOnHover();


        })
    </script>
	%{--</body>--}%
%{--</html>--}%
