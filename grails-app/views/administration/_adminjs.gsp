<script type="text/javascript">


    var  periodePrix= $('#periodePrix');
    var  fromDatePrix= $('#fromDatePrix');
    var  toDatePrix= $('#toDatePrix');
    var nbMonthLastPrices=0;
    var n = -1;
    var nbjrs=n*30;
    var URL =$(location).attr('href')+"#",
            menuPrix= URL+"IdPrix",
            menuOffres=URL+"IdOffres",
            menuStocks =URL+"IdStocks",
            menuDatamining =URL+"IdDatamining",
            menuAnalyseSMS= URL+"IdAnalyseSMS",
            menuPerformancesEnqueteurs=URL+"IdPerformancesEnqueteurs",
            menuStatistiquesVisites =URL+"IdStatsVisites",
            menuHistorique=URL+"IdHistoriqueSMS";
          var  activeMenu=menuAnalyseSMS;
    var toDayDatePrix;
    var toDayDateOffres;
    var beginDate;
    var beginDatePrix;
    var  periode= $('#periode');
    var  periodeSMS= $('#periodeSMS');
    var  periodeOffre= $('#periodeOffre');
    var  fromDate= $('#fromDate');
    var  toDate= $('#toDate');
    var  fromDateSMS= $('#fromDateSMS');
    var  toDateSMS= $('#toDateSMS');
    var  fromDateOffre= $('#fromDateOffre');
    var  toDateOffre= $('#toDateOffre');
    var tableauDeBord=$('#dashboardTab');
    var  columnSelected= $('#columnSelected');
    var searchInput=$('#search');
    function onSearchChange() {
        var searchText=searchInput.val();
        var columnSelectedVal=columnSelected.val();
        <g:remoteFunction controller="administration" action="listPrix" onloading="showSpinner(true);"
        update="prix" params='{searchvalue: searchText, columnSelected: columnSelectedVal}'/>

    }
    function ongroupeclick(){
        buildPerformance();
    }
    function onclickOption(){
        var filterOption=$("input[type='radio'][name='filterOption']:checked").val();
        columnSelected.val(filterOption);
    }
    function reduireLeftPanel(){
        $header = $(".myheader");
        var myfluidybody=$('#myfluidbody');
        //getting the next element
        $content = $(".thecontainer");
        //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
        if ($content.is(":visible")) {
        $content.slideToggle(500, function () {
            //execute this after slideToggle is done
            //change text of header based on visibility of content div
            $header.text(function () {
                //change text based on condition
                myfluidybody.removeClass("col-sm-9").addClass("col-sm-12");
                myfluidybody.removeClass("col-md-9").addClass("col-md-12");

                return $content.is(":visible") ? "Reduire(-)":"Reduire(-)" ;

            });
        });
        }
      //  "Développer(+)"
    }
    function montrerLeftPanel(){
        $header = $(".myheader");
        var myfluidybody=$('#myfluidbody');
        //getting the next element
        $content = $(".thecontainer");
        //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
        if (!$content.is(":visible")) {
            $content.slideToggle(500, function () {
                //execute this after slideToggle is done
                //change text of header based on visibility of content div
                $header.text(function () {
                    //change text based on condition
                    myfluidybody.removeClass("col-sm-12").addClass("col-sm-9");
                    myfluidybody.removeClass("col-md-12").addClass("col-md-9");


                    return $content.is(":visible") ? "Développer(+)" : "Développer(+)";

                });
            });
        }
        //  "Développer(+)"
    }
    function buildPerformance() {
        var mysum =$.pivotUtilities.aggregatorTemplates.sum;
        var numberFormat = $.pivotUtilities.numberFormat;
        var frFormat = numberFormat({thousandsSep:" ", decimalSep:",", digitsAfterDecimal:0});
        var somme = function() { return mysum(frFormat)(["Nombre"]); };
        var groupBy=$("input:radio[name='groupe']:checked").val();
        var aggregat=$("input:radio[name='aggregat']:checked").val();
        $.ajax({
            url: "${createLink(controller:'home', action:'setPerfEnqueteurs')}",
            data: {fromDate:fromDate.val(),
                toDate:toDate.val(),groupBy:groupBy,aggregat:aggregat},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
//                $.jGrowl("Fin du chargement", {
//                    sticky: false,
//                    header: 'Notification',
//                    theme: 'iphone'
//                });

                if (!data.isEmpty) {
                $('#perfPivot').pivotUI(data.data,{
                    sorted:true,
                    menuLimit:1000,
                    pivotId : "perfPivot",
                    aggregatorName:"Somme en entiers",
                    pivotContainerId :"perfContainer",
                    rows:["Enqueteur"],
                    cols:["Marché"],
                    vals:["Nombre"],
                    onRefresh:function (pivotUIOptions){


                    }
                }, true, "fr");
                }else {
                    $('#perfPivot').html("Il n'y a pas de données pour dessiner le pivot")
                }
            }
        });


    }
    function buildPrix() {
        var myaverage = $.pivotUtilities.aggregatorTemplates.average;
        var count =$.pivotUtilities.aggregatorTemplates.count;

        var numberFormat = $.pivotUtilities.numberFormat;
        var frFormat = numberFormat({thousandsSep:" ", decimalSep:",", digitsAfterDecimal: 0});

        var moyenne = function() { return myaverage(frFormat)(["Prix"]); };
        var nombre = function() { return count()(["Produit"]); };


        $.ajax({
            url: "${createLink(controller:'home', action:'setPivotPrices')}",
            data: {fromDate:fromDatePrix.val(),
                toDate:toDatePrix.val()},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
                if (!data.isEmpty) {
                    $('#pricePivot').pivotUI(data.data,{
                        aggregators: {
                            "Moyenne": moyenne
                        },
                        aggregatorName:"Moyenne",
                        sorted:true,
                        renderChoiceId:"renduPrix",
                        menuLimit:1000,
                        pivotId : "pricePivot",
                        pivotContainerId :"priceContainer"
                        ,
                        rows:["Produit"],
                        cols:["Marché"],
                        vals:["Prix"],
                        onRefresh:function (pivotUIOptions){


                        }
                    }, true, "fr");
                }else {
                    $('#pricePivot').html("Il n'y a pas de données pour dessiner le pivot")
                }
            }
        });


    }


    function buildOffres() {

        var myaverage = $.pivotUtilities.aggregatorTemplates.average;
        var count =$.pivotUtilities.aggregatorTemplates.count;

        var numberFormat = $.pivotUtilities.numberFormat;
        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});

        var moyenne = function() { return myaverage(frFormat)(["Montant"]); };
        var nombre = function() { return count()(["Produit"]); };





        $.ajax({
            url: "${createLink(controller:'home', action:'setPivotOffers')}",
            data: {fromDate:fromDatePrix.val(),
                toDate:toDatePrix.val()},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
                if (!data.isEmpty) {
                    $('#offrePivot').pivotUI(data.data,{
                        aggregators: {
                            "Moyenne": moyenne
                        },
                        aggregatorName:"Moyenne",
                        //    aggregatorName:"Moyenne",
                        sorted:true,
                        pivotId : "offrePivot",
                        renderChoiceId:"renduOffre",
                        pivotContainerId :"offreContainer",
                         menuLimit:1000,
                        rows:["Produit"],
                        cols:["Origine"],
                        vals:["Montant"]
                    }, true, "fr");
                }else {
                    $('#offrePivot').html("Il n'y a pas de données pour dessiner le pivot")
                }
            }
        });



    }
    function buildStocks() {
        var myaverage = $.pivotUtilities.aggregatorTemplates.average;
        var count =$.pivotUtilities.aggregatorTemplates.count;

        var numberFormat = $.pivotUtilities.numberFormat;
        var frFormat = numberFormat();

        var moyenne = function() { return myaverage(frFormat)(["Stock"]); };
        var nombre = function() { return count()(["Produit"]); };

        $.ajax({
            url: "${createLink(controller:'home', action:'setPivotStocks')}",
            data: {fromDate:fromDatePrix.val(),
                toDate:toDatePrix.val()},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
                if (!data.isEmpty) {
                    $('#stockPivot').pivotUI(data.data,{
                        aggregators: {
                            "Moyenne": moyenne
                        },
                        aggregatorName:"Moyenne",
                        sorted:true,
                        pivotId : "stockPivot",
                        pivotContainerId :"stockContainer",
                        rows:["Produit"],
                        cols:["Magazin"],
                        vals:["Stock"]
                    }, true, "fr");
                }else {
                    $('#stockPivot').html("Il n'y a pas de données pour dessiner le pivot")
                }
            }
        });


    }
    function buildTheCharts(){

        if (activeMenu==menuPrix) {
            reduireLeftPanel();
            buildPrix();

        }
        else
        {
            if (activeMenu==menuStocks) {
                reduireLeftPanel();
                buildStocks();

            }
            else
            {  if (activeMenu==menuOffres) {
                reduireLeftPanel();
                buildOffres();

            }

            }
        }

    }


    function buildBilanSMS() {

        $.ajax({
            url: "${createLink(controller:'home', action:'setBilanSMS')}",
            data: {fromDate:fromDateSMS.val(),
                toDate:toDateSMS.val()},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
//                $.jGrowl("Fin du chargement", {
//                    sticky: false,
//                    header: 'Notification',
//                    theme: 'iphone'
//                });
                if (!data.isEmpty) {
                    $('#showdata').html(JSON.stringify(data.data));
                $('#smsPivot').pivotUI(data.data,{
                    sorted:true,
                    menuLimit:1000,
                    pivotId : "smsPivot",
                    pivotContainerId :"smsPivotContainer",
                    aggregatorName:"Somme en entiers",
                    rows:["Opérateur"],
                    cols:["Réseau","Est Entrant ?"],
                    vals:["Nbre SMS"],
                    onRefresh:function (pivotUIOptions){


                    }
                }, true, "fr");
                }else {
                    $('#smsPivot').html("Il n'y a pas de données pour dessiner le pivot")
                }
            }
        });
   }

    $(document).ready(function(){


        moment.lang('fr');
        toDayDate=moment(Date.parse('today')).format('DD/MM/YYYY');

        beginDate=moment(nbjrs.days().fromNow()).format('DD/MM/YYYY');

        fromDate.val(beginDate);
        toDate.val(toDayDate);

        fromDateSMS.val(beginDate);
        toDateSMS.val(toDayDate);



        fromDatePrix.val(beginDate);
        toDatePrix.val(toDayDate);

        fromDateOffre.val(beginDate);
        toDateOffre.val(toDayDate);

        beginDatePrix=beginDate;

        periodePrix.daterangepicker(
                {
                    ranges: {
                        "Aujourd'hui": [new Date(), new Date()],
                        'Ce mois': [moment().startOf('month'), moment().endOf('month')],
                        'Ce trimestre': [moment().subtract('month', 3).startOf('month'), moment().endOf('month')]
                    },
                    opens: 'left',
                    format: 'DD/MM/YYYY',
                    locale :{
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
                    startDate:fromDatePrix,
                    endDate:toDayDatePrix
                },
                function(start, end) {

                    fromDatePrix.val(start.format('DD/MM/YYYY'));
                    toDatePrix.val(end.format('DD/MM/YYYY'));
                    buildTheCharts();
                    periodePrix.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                }
        );
        periodePrix.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

        periodeOffre.daterangepicker(
                {
                    ranges: {
                        "Aujourd'hui": [new Date(), new Date()],
                        'Ce mois': [moment().startOf('month'), moment().endOf('month')],
                        'Ce trimestre': [moment().subtract('month', 3).startOf('month'), moment().endOf('month')]
                    },
                    opens: 'left',
                    format: 'DD/MM/YYYY',
                    locale :{
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
                    startDate:fromDateOffre,
                    endDate:toDayDate
                },
                function(start, end) {

                    fromDateOffre.val(start.format('DD/MM/YYYY'));
                    toDateOffre.val(end.format('DD/MM/YYYY'));
                    buildTheCharts();
                    periodeOffre.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                }
        );
        periodeOffre.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

        periode.daterangepicker(
                {
                    ranges: {
                        "Aujourd'hui": [new Date(), new Date()],
                        'Ce mois': [moment().startOf('month'), moment().endOf('month')],
                        'Ce trimestre': [moment().subtract('month', 3).startOf('month'), moment().endOf('month')]
                    },
                    opens: 'left',
                    format: 'DD/MM/YYYY',
                    locale :{
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
                    startDate:fromDate,
                    endDate:toDayDate
                },
                function(start, end) {

                    fromDate.val(start.format('DD/MM/YYYY'));
                    toDate.val(end.format('DD/MM/YYYY'));
                    buildPerformance();
                    periode.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                }
        );
        periode.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));


        periodeSMS.daterangepicker(
                {
                    ranges: {
                        "Aujourd'hui": [new Date(), new Date()],
                        'Ce mois': [moment().startOf('month'), moment().endOf('month')],
                        'Ce trimestre': [moment().subtract('month', 3).startOf('month'), moment().endOf('month')]
                    },
                    opens: 'left',
                    format: 'DD/MM/YYYY',
                    locale :{
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
                    startDate:fromDateSMS,
                    endDate:toDayDate
                },
                function(start, end) {

                    fromDateSMS.val(start.format('DD/MM/YYYY'));
                     toDateSMS.val(end.format('DD/MM/YYYY'));
                     periodeSMS.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                    buildBilanSMS();
                }
        );
        periodeSMS.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));


        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            //  e.target activated tab
            //   e.relatedTarget previous tab

            activeMenu = e.target;
            if (activeMenu==menuAnalyseSMS){
                reduireLeftPanel();
                buildBilanSMS();
            }
            else
            if (activeMenu==menuPerformancesEnqueteurs){
                reduireLeftPanel();
               buildPerformance();
            } else
            if ((activeMenu==menuStatistiquesVisites)|| (activeMenu==menuHistorique)){
                montrerLeftPanel();
            }else
            if (activeMenu==menuDatamining){
                $('a[href="#IdPrix"]').tab('show');
            }
            else
            {
                buildTheCharts();
            }



        });

        $('a[href="#IdStatsVisites"]').tab('show');

        $(".myheader").click(function () {

            $header = $(this);
            var myfluidybody=$('#myfluidbody');
            //getting the next element
            $content = $(".thecontainer");
            //open up the content needed - toggle the slide- if visible, slide up, if not slidedown.
            $content.slideToggle(500, function () {
                //execute this after slideToggle is done
                //change text of header based on visibility of content div
                $header.text(function () {
                    //change text based on condition
                    myfluidybody.toggleClass('col-sm-9 col-sm-12');
                    myfluidybody.toggleClass('col-md-9 col-md-12');

                    return $content.is(":visible") ? "Reduire(-)" : "Développer(+)";

                });
            });

        });
    });

</script>