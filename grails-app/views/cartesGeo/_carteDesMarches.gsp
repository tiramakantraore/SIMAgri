<div class="row">
    <div class="col-sm-12 col-md-12 " id="groupMarches" style="display:inline-block">

        <div  id="IdMarches">
            <div class="row">
                <div class="col-sm-2 col-md-2">
                    <a id="check-allMarkets" href="javascript:void(0);">Tous les marchés</a>
                </div>
                <div class="col-sm-2 col-md-2 offset8">
                    <a id="uncheck-allMarkets" href="javascript:void(0);">Aucun marché </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12" id="listeMarches">
                    <bill:checkBoxList referenceCollection="${mesMarches}" containerClass="${ctnerTemplate}" instanceName="markets" onClickCheck="marcheClick();"/>
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
                <div id="gmapMarche" style="height: 100px; position: relative; background-color: rgb(229, 227, 223); overflow: hidden;"></div>

            </div>
        </div>