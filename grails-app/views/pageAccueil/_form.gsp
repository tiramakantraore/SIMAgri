<%@ page import="simagri.PageUtilisateur; simagri.MonImage" %>
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

                <f:field bean="pageAccueilInstance" property="nom">
                    <g:textField name="${property}" value="${it.value}"  maxlength="25" required="" class="form-control"/>
                </f:field>
                <f:field bean="pageAccueilInstance" property="estPrincipal">
                    <g:checkBox name="${property}" value="${it.value}" class="check"/>
                </f:field>

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