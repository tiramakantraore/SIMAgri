<%@ page import="simagri.Mesure; simagri.Marche; simagri.Reseau" %>

<div class="row">
    <div class="col-sm-4 col-md-4">
        <g:render template="periode" />
        <br>
        <input type="button"  class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des offres','tableau_des_offres.xls')" value="exporter en Excel">

    </div>
    <div class="col-sm-8 col-md-8">
        <div id="parametresTableau">

        </div>
    </div>
</div>
<br>
<div class="row">
    <div class="col-sm-4 col-md-4 ">
    </div>
    <div id="ChoixMesure" title="Filtrer par mesure" class="col-sm-8 col-md-8 ">
        <div id="panelChoixMesure"  class="panel panel-default" >
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseMesure">Filtrer par mesure</a>
                    <i class="indicator icon-minus pull-right">

                    </i>
                </h4>
            </div>
            <div id="collapseMesure" class="panel-collapse collapse in">
                <div class="panel-body">
                    <g:hiddenField id="mesureSelected" name="mesureSelected" />
                    <div style="max-height: 400px;overflow-y:scroll ">
                        <bill:radioBoxList referenceCollection="${Mesure.list()}" instanceName="mesure"  containerClass="my4Columns" labelClass="labelClass" onClickRadio="onclickMesureOption();" emptyText="Il n y a pas de mesures, veuillez vérifier votre profil"/>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>
<br>
<div class="row">
    <div class="col-sm-12 col-md-12">
        <div id="offrePivot" class="pvtRendererArea pivotContent">
            <div id="offreContainer"></div>
        </div>
    </div>
</div>
<g:render template="dataminingoffrejs"/>
