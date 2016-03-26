<%@ page import="simagri.PageUtilisateur" %>
    <style>
    .entry-content {font-family: 'robotoregular',cursive;}
    </style>

<g:render template="/partials/showHeader" />
		<div class="row">

			<div class="col-sm-12 col-md-12">



				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${pageUtilisateurInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${pageUtilisateurInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

                    %{--url="[controller: 'pageUtilisateur',action:'postcreate']"   update="listContent"--}%
                    <g:form  name="create"  id="myForm" class="well small form-horizontal" >
                        <g:render template="/partials/btnCreerWithCkEditor"/>
                        <g:render template="form"/>
                        <g:render template="/partials/btnCreerWithCkEditor"/>
					</g:form>
				
			</div>

		</div>
<script type="application/javascript">
    // Variable to store your files

    %{--$("button[type='submit']").click(function() {--}%
        %{--alert("i intercept");--}%
        %{--var formParams=$('form[name="create"]');--}%
%{--//        createForm.ajaxForm(function(data) {--}%
%{--//            $('#listContent").html(data);--}%
%{--//--}%
%{--//        });--}%
        %{--alert("Form without Photo "+JSON.stringify(getFormObjExcept(formParams,"photo")));--}%
        %{--var jqxhr = $.ajax({--}%
            %{--type: "POST",--}%
            %{--url: "${createLink(controller:'pageUtilisateur', action:'postcreate')}",--}%
            %{--data: {formData: JSON.stringify(getFormObjExcept(formParams,"photo"))},--}%
            %{--dataType: "text",--}%
            %{--//Options signifiant à jQuery de ne pas s'occuper du type de données--}%
            %{--cache: false,--}%

            %{--beforeSend: function (xhr) {--}%
                %{--xhr.overrideMimeType("text/plain; charset=x-user-defined");--}%
            %{--}--}%
        %{--}).done(function () {--}%
            %{--$.jGrowl("Le sondage a été créé avec succès", {--}%
                %{--sticky: false,--}%
                %{--header: 'Notification',--}%
                %{--theme: 'iphone'--}%
            %{--});--}%
            %{--$('#listContent").html(html);--}%
        %{--}).fail(function (jqXHR, textStatus, errorThrown) {--}%
            %{--$.jGrowl("La création du sondage a échoué, erreur rapportée " + errorThrown, {--}%
                %{--sticky: true,--}%
                %{--theme: 'ui-state-error ui-corner-all'--}%
            %{--});--}%

        %{--});--}%

        %{--return false; // prevent normal submit--}%
    %{--});--}%

    var formSubmitUrl="${createLink(controller:'pageUtilisateur', action:'postcreate')}";
    %{--$("button[type='submit']").click(function() {--}%
        %{--submitForm(createForm,"${createLink(controller:'pageUtilisateur', action:'postcreate')}");--}%
        %{--createForm.ajaxSubmit({--}%
            %{--async: false,--}%
            %{--url: "${createLink(controller:'pageUtilisateur', action:'postcreate')}",--}%
            %{--success : function(data){--}%
               %{--$.ajax({--}%
                %{--type: "POST",--}%
                %{--url: "${createLink(controller:'pageUtilisateur', action:'renderShow')}",--}%
                %{--data: {id: data.id},--}%
                %{--dataType: "text",--}%
                %{--//Options signifiant à jQuery de ne pas s'occuper du type de données--}%
                %{--cache: false,--}%

                %{--beforeSend: function (xhr) {--}%
                %{--xhr.overrideMimeType("text/plain; charset=x-user-defined");--}%
                %{--}--}%
                %{--}).done(function (html) {--}%
                %{--$('#listContent").html(html);--}%
                %{--}).fail(function (jqXHR, textStatus, errorThrown) {--}%
                %{--$.jGrowl("L'affichage de la page a échoué, erreur rapportée " + errorThrown, {--}%
                %{--sticky: true,--}%
                %{--theme: 'ui-state-error ui-corner-all'--}%
                %{--});--}%

                %{--});--}%
              %{--}--}%
        %{--});--}%
        %{--return false; // prevent normal submit--}%
    %{--});--}%

    $(document).ready(function() {

    });
</script>
