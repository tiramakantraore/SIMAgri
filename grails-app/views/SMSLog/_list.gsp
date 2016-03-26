<%@ page import="simagri.SMSLogger" %>
<div class="row">
    <div class="col-sm-12 col-md-12">

        <div class="page-header">
            <h1><g:message code="list.SMSLogger" /></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
</div>
</div>

<div class="row" style="padding-top: 5px;padding-bottom: 5px">
    <div class="panel-group" id="accordion">
        %{--<div id="ChoixReseau" title="Filtrer par réseau" class="col-sm-12 col-md-12 ">--}%
            <div id="panelChoixReseau"  class="panel panel-default" >
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
                        <g:hiddenField id="isFirstLoad" name="isFirstLoad" value="${isFirstLoad}" />
                        <bill:radioBoxList referenceCollection="${reseauList}" instanceName="reseau"  containerClass="${ctnerTemplate} limitHeight" labelClass="labelClass" onClickRadio="onclickReseauOption();" emptyText="Il n y a pas de réseaux"/>

                    </div>
                </div>
            </div>

        %{--</div>--}%
        <div class="panel panel-default" data-render="periode">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="accordion-toggle" data-toggle="collapse" data-target="#collapsePeriode" ><g:message code="periode.text" default="Période" /> </a>
                    <i class="indicator icon-minus pull-right">

                    </i>
                </h4>
            </div>
            <div id="collapsePeriode" class="panel-collapse collapse in">
                <div class="panel-body">
                    <g:hiddenField id="periodeSelected" name="periodeSelected" value="Le mois"/>
                    <bill:radioBoxList referenceCollection="${['Aujourd\'hui','La semaine','Le mois','Le trimestre','L\'année']}" radioName="filterPeriodeOptionLog" defaultValue="Le mois"
                                       isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickPeriodeOption();"/>
                    <br>
                    <div>
                        <label><input type="checkbox" name="morePeriodeOpts" value="periode"> Plus d'options </label>
                    </div>

                    <div id="periode" class="input-group date pull-left periodebg morePeriodeOpts box">
                        <span class="glyphicon glyphicon-calendar"></span>
                        <b class="caret"></b>
                    </div>

                    <input type="hidden" id="fromDate" name="fromDate" class="form-control">
                    <input type="hidden" id="toDate" name="toDate" class="form-control">

                </div>
            </div>


        </div>
    </div>
</div>
		<div class="row">
			<div class="col-sm-12 col-md-12">

                <filterpane:filterButton text="Rechercher" />
                <div id="tableau">
			            <g:render template="tableau"/>
                </div>
                <export:formats action="listWithPeriode" formats="['csv', 'excel', 'pdf']"  />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.SMSLogger" />

<script type="text/javascript">
    var URL =$(location).attr('href')+"#";
    var  fromDate= $('#fromDate');
    var  toDate= $('#toDate');
    var  periode=$('#periode');
    var isPeriodeLoaded=false;
    var isLoaded=false;
    var beginDate;
    var toDayDate;
    var reseauSelected=$('#reseauSelected');
    var periodeSelected=$('#periodeSelected');
    var tableau=$('#tableau');
    var isFirstLoad=$('#isFirstLoad');
    function getFromDate(){
        return fromDate.val();
    }
    function getPeriodeOption(){
        var date= new Date(),firstDay,lastDay;

        var filterOption=$("input[type='radio'][name='filterPeriodeOptionLog']:checked").val();
        periodeSelected.val(filterOption);
        switch (periodeSelected.val()) {
            case "Aujourd&#39;hui":
                toDayDate=moment(Date.parse('today')).format('DD/MM/YYYY');


                beginDate=toDayDate;

                fromDate.val(beginDate);
                toDate.val(toDayDate);

                periode.find('span').html(beginDate + ' - ' + beginDate);


                break;
            case "La semaine":
                var curr = new Date;
                firstDay = new Date(curr.setDate(curr.getDate() - curr.getDay()));
                lastDay = new Date(curr.setDate(curr.getDate() - curr.getDay()+6));


                if (lastDay>date)
                    lastDay=date;
                toDayDate=moment(lastDay).format('DD/MM/YYYY');

                beginDate=moment(firstDay).format('DD/MM/YYYY');

                fromDate.val(beginDate);
                toDate.val(toDayDate);
                periode.find('span').html(beginDate + ' - ' + toDayDate);

                break;
            case "Le mois":


                firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
                lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
                if (lastDay>date)
                    lastDay=date;
                toDayDate=moment(lastDay).format('DD/MM/YYYY');
                beginDate=moment(firstDay).format('DD/MM/YYYY');
                fromDate.val(beginDate);
                toDate.val(toDayDate);

                periode.find('span').html(beginDate + ' - ' + toDayDate);
                break;
            case "Le trimestre":
                firstDay = new Date(date.getFullYear(), date.getMonth()-2, 1);
                lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
                if (lastDay>date)
                    lastDay=date;
                toDayDate=moment(lastDay).format('DD/MM/YYYY');
                beginDate=moment(firstDay).format('DD/MM/YYYY');

                fromDate.val(beginDate);
                toDate.val(toDayDate);

                periode.find('span').html(beginDate + ' - ' + toDayDate);

                break;
            case "L&#39;ann&eacute;e":
                firstDay = new Date(date.getFullYear(), 0, 1);
                lastDay = new Date(date.getFullYear(), 11, 31);
                if (lastDay>date)
                    lastDay=date;
                toDayDate=moment(lastDay).format('DD/MM/YYYY');
                beginDate=moment(firstDay).format('DD/MM/YYYY');
                fromDate.val(beginDate);
                toDate.val(toDayDate);

                periode.find('span').html(beginDate+ ' - ' + toDayDate);

                break;
        }

    }
    function onclickPeriodeOption(){
        getPeriodeOption();
        reBuildList();

    }
    function cacher_selecteur(selecteur){
        selecteur.html("Il n y'a pas de données pour cette requête").addClass('errorClass');
        //   pas_de_donnees();
    }
    function montrer_selecteur(selecteur){
        selecteur.css("display", "inline-block").removeClass('errorClass');
    }

    function saveLocally(){
        if (isLoaded) {
            saveToBrowser("filterPeriodeOptionLog",periodeSelected.val());
            saveToBrowser("reseau",reseauSelected.val());
        }
    }
    function LoadFromLocal(){

        isPeriodeLoaded=loadRadioFromBrowser("filterPeriodeOptionLog");
        isLoaded=true;
        loadRadioFromBrowser("reseau");
        if (isFirstLoad.val()=="true") {
            selectionReseau();
            reBuildList();
        }
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
    function reBuildList(){
        jQuery.ajax({
            url: "${createLink(controller:'SMSLog', action:'renderListWithPeriode')}",
            data: {reseauId:reseauSelected.val(),fromDate:fromDate.val(),toDate:toDate.val()},
            cache: false,
            success: function(html) {
                tableau.html(html);
            }
        });
        saveLocally();
    }
    $(document).ready(function(){

        moment.lang('fr');
        toDayDate = moment(Date.parse('today')).format('DD/MM/YYYY');


        fromDate.val(toDayDate);
        toDate.val(toDayDate);

        periode.daterangepicker(
                {
                    opens: 'right',
                    format: 'DD/MM/YYYY',
                    locale: {
                        applyLabel: 'Valider',
                        cancelLabel: 'Annuler',
                        fromLabel: 'Du',
                        toLabel: 'Au',
                        weekLabel: 'W',
                        customRangeLabel: 'Ma période',
                        daysOfWeek: moment()._lang._weekdaysMin.slice(),
                        monthNames: moment()._lang._monthsShort.slice(),
                        firstDay: 0
                    },
                    startDate: fromDate,
                    endDate: toDayDate
                },
                function (start, end) {

                    fromDate.val(start.format('DD/MM/YYYY'));
                    toDate.val(end.format('DD/MM/YYYY'));
                    periode.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                    reBuildList();


                }
        );
        //periode.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

        $('#accordionpane').find('.accordion-toggle').click(function (e) {
            var chevState =$(e.target).siblings("i.indicator").toggleClass('icon-plus icon-minus');
            $(e.target).siblings("i.indicator").not(chevState).removeClass("icon-plus").addClass("icon-minus");


        });

        $('i.indicator').click(function (e) {
            $(e.target).prev().click();
        });

        LoadFromLocal();
        onclickPeriodeOption();

    });

</script>




