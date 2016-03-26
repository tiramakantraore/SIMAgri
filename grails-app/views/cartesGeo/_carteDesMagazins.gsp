<div class="row">
    <div class="col-sm-12 col-md-12" style="display:inline-block">

        <div  id="groupMagazins">
            <div class="row">
                <div class="col-sm-2 col-md-2">
                    <a id="check-allMagazins" href="javascript:void(0);">Tous les magazins</a>
                </div>
                <div class="col-sm-2 col-md-2 offset8">
                    <a id="uncheck-allMagazins" href="javascript:void(0);">Aucun magazin </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <bill:checkBoxList referenceCollection="${mesMagazins}" containerClass="${ctnerTemplateMag}" instanceName="magazins" onClickCheck="magazinClick();"/>
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
                <div id="gmapMagazin" style="height: 100px; position: relative; background-color: rgb(229, 227, 223); overflow: hidden;"></div>

            </div>
        </div>