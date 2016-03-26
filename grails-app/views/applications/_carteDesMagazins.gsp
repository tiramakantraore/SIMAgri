
<div class="row">
    <div class="col-sm-12 col-md-12" style="display:inline-block">
        <h1><g:message code="carte_des_magazins" /></h1>
        <div  id="groupMagazins">
            <div class="row">
                <div class="col-sm-3 col-md-3">
                    <a id="check-allMagazins" href="javascript:void(0);"><g:message code="tousLesMagazins.text" default="Tous les magazins" /></a>
                </div>
                <div class="col-sm-3 col-md-3 offset6">
                    <a id="uncheck-allMagazins" href="javascript:void(0);"><g:message code="aucunMagazin.text" default="Aucun magazin" />  </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12" id="listeMagazins">
                    <bill:checkBoxList referenceCollection="${mesMagazins}" containerClass="${ctnerTemplateMag} limitHeight" instanceName="magazins" onClickCheck="magazinClick();"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="btn-flat  btn-link">Appliquer</div>
                </div>
            </div>
        </div>
    </div>
</div>
        <div class="row">

            <div class="col-sm-12 col-md-12" id="mapMagazinsContainer">
                <div id="gmapMagazin" style="height: 600px; position: relative; background-color: rgb(229, 227, 223); overflow: hidden;"></div>

            </div>
        </div>
<g:render template="/applications/cartes_geo_mag_js"/>