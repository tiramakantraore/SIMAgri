<script type="text/javascript">

    var beginDate;
    var  periode= $('#periode');
    var  fromDate= $('#fromDate');
    var  toDate= $('#toDate');
    var nbjrs=-30;
    var mesureSelected=$('#mesureSelected');

    function onclickMesureOption(){
        selectionMesure();
        buildOffres();
    }
    function selectionMesure(){
        var mesureOption = $("input[type='radio'][name='mesure']:checked").val();
        mesureSelected.val(mesureOption);
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
            data: {fromDate:fromDate.val(),
                toDate:toDate.val(),idMesure:mesureSelected.val()},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
                if (!data.isEmpty) {
                    $('#parametresTableau').empty();
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
                        vals:["Stock"],
                        onRefresh:function (pivotUIOptions){
                            $(".pvtRenderer").prependTo("#parametresTableau");

                        }
                    }, true, "fr");
                }else {
                    $('#stockPivot').html("Il n'y a pas de données pour dessiner le pivot")
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
                    buildStocks();
                    periode.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                }
        );
        periode.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

        buildStocks();


    });

</script>