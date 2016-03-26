<%@ page import="simagri.MonImage; simagri.PageUtilisateur; simagri.PageAccueil" %>

<g:render template="/partials/showHeader" model="[beanName:'pageAccueil']"/>
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
        <g:form class="form-horizontal" action="show" >
            <g:hiddenField name="version" value="${pageAccueilInstance?.version}" />
            <g:hiddenField name="theId" value="${pageAccueilInstance?.id}" />
            <g:hiddenField name="id" value="${pageAccueilInstance?.id}" />
            <ul class="nav nav-pills" id="dashboardTab">
                <li class="active"><a href="#IdIdentification" data-toggle="tab">Identification</a></li>
                <li class="nav-divider"></li>
                <li><a href="#IdMesImages" data-toggle="tab">Mes images</a></li>
                <li><a href="#IdPagePrix" data-toggle="tab">Page des prix</a></li>
                <li><a href="#IdPageOffre" data-toggle="tab">Page des offres</a></li>
                <li><a href="#IdPageStock" data-toggle="tab">Page des stocks</a></li>
                <li><a href="#IdPageReseau" data-toggle="tab">Page des réseaux</a></li>
                <li><a href="#IdBanniere" data-toggle="tab">Banniere</a></li>
            </ul>


            <div class="tab-content tabContent">
                <div class="tab-pane active" id="IdIdentification">

                    <div class="row">

                        <div class="col-sm-5 col-md-5">

                            <f:display bean="pageAccueilInstance" property="nom"/>
                            <f:display bean="pageAccueilInstance" property="estPrincipal"/>


                        </div>

                        <div class="col-sm-7 col-md-7">
                        </div>
                    </div>

                </div>


                <div class="tab-pane" id="IdMesImages">

                    <bill:checkBoxList referenceCollection="${MonImage.list(sort: 'nom', order: 'asc')}" containerClass="${ctnerTemplateImage}" instanceName="mesImages" />

                </div>
                <div class="tab-pane" id="IdPagePrix">
                    <bill:radioBoxList referenceCollection="${PageUtilisateur.list(sort: 'nom', order: 'asc')}" instanceName="pagePrix" defaultValue="${pageAccueilInstance?.pagePrix?.nom}" />

                </div>
                <div class="tab-pane" id="IdPageOffre">
                    <bill:radioBoxList referenceCollection="${PageUtilisateur.list(sort: 'nom', order: 'asc')}" instanceName="pageOffre" defaultValue="${pageAccueilInstance?.pageOffre?.nom}"  />

                </div>
                <div class="tab-pane" id="IdPageStock">
                    <bill:radioBoxList referenceCollection="${PageUtilisateur.list(sort: 'nom', order: 'asc')}" instanceName="pageStock" defaultValue="${pageAccueilInstance?.pageStock?.nom}" />

                </div>
                <div class="tab-pane" id="IdPageReseau">
                    <bill:radioBoxList referenceCollection="${PageUtilisateur.list(sort: 'nom', order: 'asc')}" instanceName="pageReseau" defaultValue="${pageAccueilInstance?.pageReseau?.nom}" />

                </div>
                <div class="tab-pane" id="IdBanniere">
                    <bill:radioBoxList referenceCollection="${PageUtilisateur.list(sort: 'nom', order: 'asc')}" instanceName="banniere" defaultValue="${pageAccueilInstance?.banniere?.nom}" />
                </div>

            </div>


            <g:render template="/partials/btnShow"/>
        </g:form>
    </div>
</div>

<script type="text/javascript">
    var mesImages=$("input[type='checkbox'][name^='mesImages_']");
    var  id=$('#theId');
    function fireOnEdit()
    {
        if (!(id.val()==undefined)) {
            $.ajax({
                url: "${createLink(controller:'pageAccueil', action:'updateByJSON')}",
                accepts: "text/json",
                data: {id:id.val()},
                success: function(data, status){
                    if (!data.isEmpty){

                        mesImages.prop('checked', false);
                        var pagePrixId=$("input[type='radio'][name='pagePrix'][value='"+data.pagePrixId+"']");
                        pagePrixId.prop('checked', true);
                        var pageOffreId=$("input[type='radio'][name='pageOffre'][value='"+data.pageOffreId+"']");
                        pageOffreId.prop('checked', true);
                        var pageStockId=$("input[type='radio'][name='pageStock'][value='"+data.pageStockId+"']");
                        pageStockId.prop('checked', true);
                        var pageReseauId=$("input[type='radio'][name='pageReseau'][value='"+data.pageReseauId+"']");
                        pageReseauId.prop('checked', true);
                        var banniereId=$("input[type='radio'][name='banniere'][value='"+data.banniereId+"']");
                        banniereId.prop('checked', true);
                        mesImages.filter(function( index ) {
                            return (data.mesImages.hasObject(this.name))
                        }).prop('checked', true);


                    }


                }
            });
        }

    }
    jQuery(document).ready(function() {
        fireOnEdit();

    });
</script>
