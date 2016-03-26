<%@ page import="simagri.Marche; simagri.Reseau" %>
<div class="row">
    <div class="col-sm-4 col-md-4">
        <g:render template="periode"/>
        <br>
        <input type="button" class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des performances','tableau_des_performances.xls')" value="exporter en Excel">

    </div>
    <div class="col-sm-8 col-md-8">
        <div id="parametresTableau">

        </div>
    </div>

</div>
<div class="row">
    <div class="col-sm-4 col-md-4 ">
    </div>
    <div class="col-sm-4 col-md-4">
        <g:render template="groupement"/>
    </div>
    <div class="col-sm-3 col-md-3">
        <g:render template="aggregats"/>
    </div>

</div>
<div class="row">
    <div class="col-sm-12 col-md-12"  >
        <div id="perfPivot" class="classPivotjs pivotContent" style="padding-top: 25px">
            <div id="perfContainer"></div>
        </div>

    </div>

</div>

<g:render template="dataminingperfenqueteurjs"/>
