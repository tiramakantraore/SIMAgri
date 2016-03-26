<%@ page import="simagri.Reseau" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="${request?.isPjax?'pjaxListContent':'accueilLayout'}">
    <title><g:message code="miseEnLigne.label" /></title>
    <style type="text/css">

    .entry-content {font-family: 'robotoregular',cursive;}


    </style>
    <ckeditor:resources/>
</head>
<body>
<g:form name="list">
    <g:hiddenField name="theId" value="${utilisateurInstance?.id}" />
    <g:hiddenField id="userType" name="userType" value="${userType}" />
    <g:hiddenField name="suffixe" value="${suffixe}" />
    <g:hiddenField id="isFirstLoad" name="isFirstLoad" value="${isFirstLoad}" />
    <div class="row">

        <div class="col-sm-4 col-md-4">
            <div class="well small">
                <ul class="nav nav-list">
                    <li>
                        <g:remoteLink onLoading="showSpinner();" params="{userType:\$('#userType').val()}" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">
                            <i class="glyphicon glyphicon-plus"></i>
                            <g:message code="create.${suffixe}" />
                        </g:remoteLink>
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-sm-8 col-md-8">
            <div class="page-header">
                <h1><g:message code="list.${suffixe}" /></h1>
            </div>
        </div>
    </div>
      <div class="row" style="padding-top: 5px;padding-bottom: 5px">
          <div id="ChoixReseau" title="Filtrer par réseau" class="col-sm-12 col-md-12 ">
              <div id="panelChoixMesure"  class="panel panel-default" >
                  <div class="panel-heading">
                      <h4 class="panel-title">
                          <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseReseau">Filtrer par réseau</a>
                          <i class="indicator icon-minus pull-right">

                          </i>
                      </h4>
                  </div>
                  <div id="collapseReseau" class="panel-collapse collapse in">
                      <div class="panel-body">
                          <g:hiddenField id="reseauSelected" name="reseauSelected" />
                          %{--<div style="max-height: 400px;overflow-y:scroll ">--}%
                              <bill:radioBoxList referenceCollection="${reseauList}" instanceName="reseau"  containerClass="${ctnerTemplate} limitHeight" labelClass="labelClass" onClickRadio="onclickReseauOption();" emptyText="Il n y a pas de réseaux"/>
                          %{--</div>--}%
                      </div>
                  </div>
              </div>

          </div>

      </div>
		<div class="row">
            %{--<div class="col-sm-3 col-md-3">--}%
                        %{--<h4><strong><g:message code="liste.reseau"/></strong></h4>--}%
                        %{--<div  class="k-content" >--}%
                            %{--<div id="treeview" class="groupe-section"></div>--}%

                        %{--</div>--}%
            %{--</div>--}%
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" params="${filterParams}"/>
                <div id="tableau">
                <g:render template="tableau"/>
                 </div>


                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
		
        <filterpane:filterPane dialog="true"  associatedProperties="entreprise.nom,activite.nom,reseauPrincipal.nom"  excludeProperties="username,homePage,status,password,passwordExpired,accountLocked,accountExpired,enabled,photo,postCode,physicalAddress,secondPhone,country,town,poste,comment,dateOfBirth,reseaux" domain="simagri.Utilisateur" />
  </g:form>
<script type="text/javascript">
        var reseauSelected=$('#reseauSelected');
        var tableau=$('#tableau');
        var userType=$('#userType');
        var isLoaded=false;
        var isFirstLoad=$('#isFirstLoad');
        function saveLocally(){
            if (isLoaded) {
                saveToBrowser("reseau",reseauSelected.val());
            }
        }
        function LoadFromLocal(){
            loadRadioFromBrowser("reseau");
            if (isFirstLoad.val()=="true") {
            selectionReseau();
            reBuildList();
            }
        }
        function reBuildList(){
            jQuery.ajax({
                url: "${createLink(controller:'utilisateur', action:'renderListWithReseau')}",
                data: {reseauId:reseauSelected.val(),userType:userType.val()},
                cache: false,
                success: function(html) {
                   tableau.html(html);
                }
            });
        }
        function selectionReseau() {
            var reseauOption = $("input[type='radio'][name='reseau']:checked").val();

            reseauSelected.val(reseauOption);
        }
        function onclickReseauOption(){
            selectionReseau();
            reBuildList();
            saveLocally();
        }

        var  id=$('#theId');
        var previousSizeOfChecked;
        var suffixe=$('#suffixe');
        var listForm=$("form[name='list']");
        var destinatairesSMS = $('#destinatairesSMS'),
                reseauTree = $('#treeview'),
                idResauxSMS = $('#ReseauxIdsSMS');
        $(".modifier").click(function() {
                    listForm.ajaxSubmit({
                        async: true,
                        method:'GET',
                        data:{id:id.val(),userType:userType.val(),isCreation:false,suffixe:suffixe.val(),isChange:false},
                        url: "${createLink(controller:'utilisateur', action:'edit')}",
                        success: function (data) {
                            $('#simagriIndex').html(data);

                        }

                    });
                    return false; // prevent normal submit
                }
        );

        $(document).ready(function() {

//            scriptTree();

            openMenuOnHover();
            LoadFromLocal();
            isLoaded=true;
            $('#accordionpane').find('.accordion-toggle').click(function (e) {
                var chevState =$(e.target).siblings("i.indicator").toggleClass('icon-plus icon-minus');
                $(e.target).siblings("i.indicator").not(chevState).removeClass("icon-plus").addClass("icon-minus");


            });

            $('i.indicator').click(function (e) {
                $(e.target).prev().click();
            });
        });


</script>
</body>
</html>
