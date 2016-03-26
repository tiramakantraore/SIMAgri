<div class="row">
    <div class="col-sm-12 col-md-12 " id="groupMarches" style="display:inline-block">
        <h1><g:message code="carte_des_marches" /></h1>
        <div  id="IdMarches">
            <div class="row">
                <div class="col-sm-3 col-md-3">
                    <a id="check-allMarkets" href="javascript:void(0);"><g:message code="tousLesMarches.text" default="Tous les marchés" /> </a>
                </div>
                <div class="col-sm-3 col-md-3 offset6">
                    <a id="uncheck-allMarkets" href="javascript:void(0);"><g:message code="aucunMarche.text" default="Aucun marché" />  </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12" id="listeMarches">
                    <bill:checkBoxList referenceCollection="${mesMarches}" containerClass="${ctnerTemplate} limitHeight" instanceName="markets" onClickCheck="marcheClick();"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="btn-flat  btn-link maj_carte">Appliquer</div>
                </div>
            </div>
        </div>

    </div>
</div>
        <div class="row">

            <div class="col-sm-12 col-md-12" >
                <div id="gmapMarche" style="height: 600px; position: relative; background-color: rgb(229, 227, 223); overflow: hidden;"></div>

            </div>
        </div>
<g:render template="/applications/cartes_geo_marches_js"/>
