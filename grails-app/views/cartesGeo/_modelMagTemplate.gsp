
<div class="col-sm-12 col-md-12" style="display:inline-block">

    <div class="contentbox" id="groupMagazins">
        <div class="row">
            <div class="col-sm-2 col-md-2">
                <a id="check-allMarkets" href="javascript:void(0);">Tous les magazins</a>
            </div>
            <div class="col-sm-2 col-md-2 offset8">
                <a id="uncheck-allMarkets" href="javascript:void(0);">Aucun magazin </a>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <bill:checkBoxList referenceCollection="${mesMagazins}" containerClass="${ctnerTemplate}" instanceName="magazins" onClickCheck="marcheClick();"/>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div class="btn-flat  btn-link">Appliquer</div>
            </div>
        </div>
    </div>
</div>