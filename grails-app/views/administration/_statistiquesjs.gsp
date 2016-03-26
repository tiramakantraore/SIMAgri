<script type="text/javascript">
    var URL =$(location).attr('href')+"#",
            menuStatistiquesVisites =URL+"IdStatsVisites",
            menuHistorique=URL+"IdHistoriqueSMS";
          var  activeMenu=menuStatistiquesVisites;
    var statistiquesVisitesId=$('#statistiquesVisites');
    var  fromDate= $('#fromDate');
    var  toDate= $('#toDate');
    var  periode=$('#periode');
    var typeVisiteurs=$('#typeVisiteurs');
    var isTypeVisiteurs=false;
    var isPeriodeLoaded=false;
    var isLoaded=false;
    var nbVisiteurs=$('#NbVisiteurs');
    var beginDate;
    var toDayDate;
    var periodeSelected=$('#periodeSelected');
    function getPeriodeOption(){
        var date= new Date(),firstDay,lastDay;

        var filterOption=$("input[type='radio'][name='filterPeriodeOption']:checked").val();
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
//               firstDay = Date.today().previous().monday();
//               lastDay = Date.today().next().sunday();


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
        buildStatistiquesVisites();

    }
    function cacher_selecteur(selecteur){
        selecteur.html("Il n y'a pas de données pour cette requête").addClass('errorClass');
        //   pas_de_donnees();
    }
    function montrer_selecteur(selecteur){
        selecteur.css("display", "inline-block").removeClass('errorClass');
    }
    function buildStatistiquesVisites() {
         $.getJSON("${createLink(controller:'home', action:'setStatistiquesVisites')}",{fromDate:fromDate.val(),
                toDate:toDate.val(),isUniqueVisitors:typeVisiteurs.val()})
                    .done (function(data) {
                // Create the chart
                if (!data.isEmpty) {
                    montrer_selecteur(statistiquesVisitesId);
//                    nbVisiteurs.val(data.count+" Visiteurs ");
                    var   chart =   statistiquesVisitesId.highcharts('StockChart', {
                        rangeSelector : {
                            selected : 0
                        },

                        title : {
                            text : 'Statistiques des visites --> Nbre Total : '+data.count
                        },
                        credits:{
                            enabled:false
                        },
                        series : [{
                            name : 'Visites',
                            data : data.aaData,
//                            type : 'line',
                            type : 'areaspline',
                            threshold : null,
                            tooltip : {
                                valueDecimals : 2
                            },
                            fillColor : {
                                linearGradient : {
                                    x1: 0,
                                    y1: 0,
                                    x2: 0,
                                    y2: 1
                                },
                                stops : [[0, Highcharts.getOptions().colors[0]], [1, '#FAB300']]
                            }
                        }]
                    });
                    chart.hideLoading();
                }else {
                    cacher_selecteur(statistiquesVisitesId);
                }

            }).fail(function( jqxhr, textStatus, error ) {
                        var err = textStatus + ', ' + error;
                        console.log( "La requete a echoué: " + err);
                    });


    }
    function onclickTypeVisiteurs(){
        var filterTypePrixOption=$("input[type='radio'][name='filterTypeVisiteurs']:checked").val();
        typeVisiteurs.val(filterTypePrixOption);
        onclickPeriodeOption();
        saveLocally();
    }
    function saveLocally(){
        if (isLoaded) {

            saveToBrowser("filterTypeVisiteurs",typeVisiteurs.val());

            saveToBrowser("filterPeriodeOptionStats",periodeSelected.val());



        }
    }
    function LoadFromLocal(){

        isTypeVisiteurs=loadRadioFromBrowser("filterTypeVisiteurs");
        isPeriodeLoaded=loadRadioFromBrowser("filterPeriodeOptionStats");
        isLoaded=true;


//      onclickPeriodeOption();
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
//                        customRangeLabel: 'Ma période',
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
                    buildStatistiquesVisites();

                }
        );
        //periode.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

        $('a[href="#IdStatsVisites"]').tab('show');
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