
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'price.label', default: 'price')}" />
    		<title><g:message code="price.label" /></title>

	</head>
	<body>
                        <g:form class="form-horizontal" action="saisie" >

                            <g:render template="/saisiePrix/_saisieprix"/>

                        </g:form>


	</body>
</html>
