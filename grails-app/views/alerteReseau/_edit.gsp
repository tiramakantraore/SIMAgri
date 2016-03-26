<%@ page import="org.springframework.validation.FieldError" %>

%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="adminLayout">--}%
		%{--<g:set var="entityName" value="${message(code: 'alerte.label', default: 'Alerte')}" />--}%
		%{--<title><g:message code="edit.alerte" /></title>--}%
	%{--</head>--}%
	%{--<body>--}%
		<div class="row">

			<div class="col-sm-5 col-md-5">
                <h1><g:message code="edit.alerte" /></h1>

			</div>
        </div>
        <div class="row">
			<div class="col-sm-12 col-md-12">


				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${alerteInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${alerteInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
					<g:form class="form-horizontal" action="edit" >
						<g:hiddenField name="version" value="${alerteInstance?.version}" />
                        <g:hiddenField name="ReseauxIds" />
                        <g:hiddenField name="alertId" value="${alertId}" />
                        <g:hiddenField name="id" value="${alerteInstance?.id}" />
                           <g:render template="/partials/btnEdit"/>
					        <g:render template="form"/>
                            <g:render template="/partials/btnEdit"/>

					</g:form>

			</div>

		</div>
    %{--<script type="text/javascript">--}%
        %{--var markets=$('#markets"),--}%
                %{--produits=$('#produits"),--}%
                %{--categories=$('#categories"),--}%
                %{--destinataires=$('#destinataires"),--}%
                %{--typeAlerte=$('#typeAlerte"),--}%
                %{--typeOffreDiv=$('#typeOffreDiv"),--}%
                %{--marcheDiv=$('#marcheDiv"),--}%
         %{--id=$('#alertId");--}%
        %{--var submitBtn=$('#submit");--}%
        %{--var reseauTree=$('#treeview");--}%
        %{--function fireOnSelectReseau(optionarray)--}%
        %{--{--}%
            %{--jQuery.ajax({--}%
                %{--url: "${createLink(controller:'autoComplete', action:'updateMarketsFromReseaux')}",--}%
                %{--data: {selectedList:JSON.stringify(optionarray)},--}%
                %{--cache: true,--}%
                %{--success: function(html) {--}%

                    %{--markets.empty().html(html).multiselect('refresh');--}%

                %{--}--}%
            %{--});--}%

            %{--jQuery.ajax({--}%
                %{--url: "${createLink(controller:'autoComplete', action:'updateCategoriesFromReseaux')}",--}%
                %{--data: {selectedList:JSON.stringify(optionarray)},--}%
                %{--cache: true,--}%
                %{--success: function(html) {--}%
                    %{--categories.empty().html(html).multiselect('refresh');--}%

                %{--}--}%
            %{--});--}%
            %{--jQuery.ajax({--}%
                %{--url: "${createLink(controller:'autoComplete', action:'updateDestinatairesFromReseaux')}",--}%
                %{--data: {selectedList:JSON.stringify(optionarray)},--}%
                %{--cache: false,--}%
                %{--success: function(html) {--}%

                    %{--destinataires.empty().html(html).multiselect('refresh');--}%
                %{--}--}%
            %{--});--}%
            %{--jQuery.ajax({--}%
                %{--url: "${createLink(controller:'autoComplete', action:'updateProduitsFromReseaux')}",--}%
                %{--data: {selectedList:JSON.stringify(optionarray)},--}%
                %{--cache: true,--}%
                %{--success: function(html) {--}%
                    %{--produits.empty().html(html).multiselect('refresh');--}%


                %{--}--}%
            %{--});--}%

        %{--}--}%
        %{--// function that gathers IDs of checked nodes--}%
        %{--function checkedNodeIds(nodes, checkedNodes) {--}%
            %{--for (var i = 0; i < nodes.length; i++) {--}%
                %{--if (nodes[i].checked) {--}%
                    %{--checkedNodes.push(nodes[i].id);--}%
                %{--}--}%

                %{--if (nodes[i].hasChildren) {--}%
                    %{--checkedNodeIds(nodes[i].children.view(), checkedNodes);--}%
                %{--}--}%
            %{--}--}%
        %{--}--}%
        %{--function creer(){--}%

            %{--var checkedNodes = [],--}%
                    %{--PtreeView = $('#treeview").data("kendoTreeView"),--}%
                    %{--message;--}%

            %{--checkedNodeIds(PtreeView.dataSource.view(), checkedNodes);--}%

            %{--if (checkedNodes.length > 0) {--}%
                %{--$('#ReseauxIds").val(checkedNodes.join(","));--}%

            %{--}--}%


            %{--submitBtn.click();--}%

        %{--}--}%
        %{--jQuery(document).ready(function() {--}%

            %{--typeAlerte.change(function() {--}%
                %{--var type=typeAlerte.val();--}%
                %{--if (type==='Prix') {--}%
                    %{--marcheDiv.css("display", "inline-block");--}%
                    %{--typeOffreDiv.css("display", "none");--}%


                %{--} else {--}%
                    %{--marcheDiv.css("display", "none");--}%
                    %{--typeOffreDiv.css("display", "inline-block");--}%


                %{--}--}%


            %{--});--}%
            %{--$.getJSON("${createLink(controller:'reseau', action:'getSimpleTree')}", {alertId:id.val()},function (data) {--}%
                %{--var reseaux= reseauTree.kendoTreeView({--}%
                    %{--dataSource: data,--}%
                    %{--checkboxes: {--}%
                        %{--checkChildren: false--}%
                    %{--}--}%

                %{--});--}%
              %{--//  reseaux.data("kendoTreeView").expand(".k-item");--}%
                %{--reseauTree.data("kendoTreeView").dataSource.bind("change", function (e) {--}%
                    %{--var checkedNodes = [],--}%
                            %{--treeView = reseauTree.data("kendoTreeView"),--}%
                            %{--message;--}%

                    %{--checkedNodeIds(treeView.dataSource.view(), checkedNodes);--}%

                    %{--if (checkedNodes.length > 0) {--}%
                        %{--message = "IDs of checked nodes: " + checkedNodes.join(",");--}%
                    %{--} else {--}%
                        %{--message = "No nodes checked.";--}%
                    %{--}--}%

%{--//                    kendoConsole.log("is changing on " + this.text(e.node));--}%
                    %{--var optionarray = []; var i=0; j=0;--}%
                    %{--for(i=0; i < checkedNodes.length; i++ ) {--}%
                        %{--optionarray[j] = {--}%
                            %{--'id' :checkedNodes[i]--}%
                        %{--};--}%
                        %{--j++--}%
                    %{--}--}%
                    %{--fireOnSelectReseau(optionarray) ;--}%

                %{--});--}%
            %{--});--}%

            %{--markets.multiselect({height: 225,selectedList:10,checkAllText: "Tous",uncheckAllText: "Aucun",--}%
                %{--noneSelectedText:"",selectedText: "# de # selectionnés",autoOpen:false});--}%
            %{--destinataires.multiselect({height: 225,selectedList:10,checkAllText: "Tous",uncheckAllText: "Aucun",--}%
                %{--noneSelectedText:"",selectedText: "# de # selectionnés",autoOpen:false});--}%
            %{--produits.multiselect({height: 225,selectedList:10,checkAllText: "Tous",uncheckAllText: "Aucun",--}%
                %{--noneSelectedText:"",selectedText: "# de # selectionnés",autoOpen:false});--}%
            %{--categories.multiselect({height: 225,selectedList:10,checkAllText: "Tous",uncheckAllText: "Aucun",--}%
                %{--noneSelectedText:"",selectedText: "# de # selectionnés",autoOpen:false});--}%
            %{--categories.on("close", function(event, ui) {--}%
                %{--var optionarray = [];j=0;--}%
                %{--for( i=0; i < this.options.length; i++ ) {--}%

                    %{--if (this.options[i].selected) {--}%

                        %{--optionarray[j] = {--}%
                            %{--'id' :this.options[i].value--}%
                        %{--};--}%
                        %{--j++;--}%


                    %{--}--}%
                %{--}--}%
                %{--jQuery.ajax({--}%
                    %{--url: "${createLink(controller:'autoComplete', action:'updateProductFromCategories')}",--}%
                    %{--data: {selectedList:JSON.stringify(optionarray)},--}%
                    %{--cache: false,--}%
                    %{--success: function(html) {--}%
                        %{--produits.empty().html(html).multiselect('refresh');--}%
                        %{--produits.multiselect("open");--}%

                    %{--}--}%
                %{--});--}%
            %{--});--}%


        %{--});--}%
    %{--</script>--}%
	%{--</body>--}%
%{--</html>--}%
