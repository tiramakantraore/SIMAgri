<script type="text/javascript">
    var beginDate;
    var  periode= $('#periode');
    var  fromDate= $('#fromDate');
    var  toDate= $('#toDate');
    var EvolutionPrix=$('#EvolutionPrix');
    var nbjrs=-30;
    function cacher_selecteur(selecteur){
        selecteur.html("Il n y'a pas de données pour cette requête").addClass('errorClass');
        //   pas_de_donnees();
    }
    function montrer_selecteur(selecteur){
        selecteur.css("display", "inline-block").removeClass('errorClass');
    }
    function buildEvolutionPrix() {
        $.getJSON("${createLink(controller:'home', action:'setPrixEvolution')}",{fromDate:fromDate.val(),
            toDate:toDate.val()})
                .done (function(data) {
            // Create the chart
            if (!data.isEmpty) {
                montrer_selecteur(EvolutionPrix);
                var   chart =   EvolutionPrix.highcharts('StockChart', {
                    rangeSelector : {
                        selected : 0
                    },

                    title : {
                        text : 'Statistiques des visites '
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
    function buildPrix() {
        var myaverage = $.pivotUtilities.aggregatorTemplates.average;
        var count =$.pivotUtilities.aggregatorTemplates.count;
        var nbjrs=30;
        var numberFormat = $.pivotUtilities.numberFormat;
        var frFormat = numberFormat({thousandsSep:" ", decimalSep:",", digitsAfterDecimal: 0});

        var moyenne = function() { return myaverage(frFormat)(["Prix"]); };
        showSpinner();

        $.ajax({
            url: "${createLink(controller:'home', action:'setPivotPrices')}",
            data: {fromDate:fromDate.val(),
                toDate:toDate.val()},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
                if (!data.isEmpty) {
                   var tabIsEmpty=$('#parametresTableau').empty();

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
                            $(".pvtRenderer").prependTo("#parametresTableau");

                        }
                    }, true, "fr");
                }else {
                    $('#pricePivot').html("Il n'y a pas de données pour dessiner le pivot")
                }
            }
        });
        hideSpinner();

    }




    $(document).ready(function(){
        moment.lang('fr');
        toDayDate=moment(Date.parse('today')).format('DD/MM/YYYY');

        beginDate=moment(nbjrs.days().fromNow()).format('DD/MM/YYYY');

        fromDate.val(beginDate);
        toDate.val(toDayDate);
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

                    buildPrix();

                    periode.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                }
        );
        periode.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));


        buildPrix();
        $('a[href="#IdAnalysePrix"]').tab('show');

    });

</script>