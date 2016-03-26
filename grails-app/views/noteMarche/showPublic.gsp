
<%@ page import="simagri.Info" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="indexLayout">
		<g:set var="entityName" value="${message(code: 'info.label', default: 'Info')}" />
		<title>${noteMarcheInstance.titre}</title>
	</head>
	<body>
		<div class="row">

			
			<div class="col-sm-12 col-md-12">
                <g:render template="infostemplate"/>

       			</div>

		</div>
	</body>
</html>
