<!doctype html>
<html>
	<head>
		<title>Erreur à l'exécution</title>
		<meta name="layout" content="indexLayout">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
	</head>
	<body>
		<g:renderException exception="${exception}" />
	</body>
</html>