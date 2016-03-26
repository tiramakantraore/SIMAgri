
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'smsToReseaux.label', default: 'SmsToReseaux')}" />
		<title><g:message code="create.smsToReseaux" /></title>
        <style>
        #example {
            text-align: center;
        }

        .groupe-section {
            display: inline-block;
            vertical-align: top;
            width: 100%;
            min-height: 230px;
            text-align: left;
            margin: 0 0.5em;
        }
        </style>

	</head>
	<body>
    <div class="row">
        <div class="page-header ">
            <h1><g:message code="create.smsToReseaux" class="col-sm-6 col-md-6 offset-6"/></h1>
            <p><strong><g:message code="smsToReseaux.selectReseaux" class="col-sm-6 col-md-6 offset-6"/></strong></p>
        </div>
        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <g:hasErrors bean="${smsToReseauxInstance}">
            <bootstrap:alert class="alert-error">
                <ul>
                    <g:eachError bean="${smsToReseauxInstance}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </bootstrap:alert>
        </g:hasErrors>
    </div>
		<div class="row">
            <div class="col-sm-5 col-md-5">
                %{--<br>--}%
                %{--<br>--}%
                %{--<br>--}%
                %{--<br>--}%
                %{--<br>--}%
                %{--<br>--}%

            <div class="well small" style="min-height:250px">
                <g:message code="choisir.reseau"/>
                <div  class="k-content" >
                    <div id="treeview" class="groupe-section"></div>
                    <div id="result" class="groupe-section"></div>

                    <div class="groupe-section">
                        %{--<h3 class="title">Console log--}%
                        %{--</h3>--}%
                        <div class="console"></div>
                    </div>


                </div>
            </div>
           </div>
			
			<div class="col-sm-6 col-md-6 offset-1">



				<fieldset>

					<g:form name="create" class="well small form-horizontal" action="create" >
                           <g:render template="form"/>
                           <g:hiddenField name="ReseauxIds" />
							<div class="form-actions">

								<button type="submit" id="submit" class="btn-flat  btn-primary">

                                    <g:message code="sendSMS.button.label" default="Envoyer" />
								</button>
							</div>
					</g:form>
				</fieldset>
				
			</div>


        </div>

	</body>
</html>
