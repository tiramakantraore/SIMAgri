<script type="text/javascript">
    var  columnSelected= $('#columnSelected');
    var beginDate;
    var nbjrs=-30;
    var  periode= $('#periode');
    var  fromDate= $('#fromDate');
    var  toDate= $('#toDate');
    function ongroupeclick(){
        buildPerformance();
    }
    function onclickOption(){
        var filterOption=$("input[type='radio'][name='filterOption']:checked").val();
        columnSelected.val(filterOption);
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
                if (!data.isEmpty) {
                    $('#parametresTableau').empty();
                    $('#perfPivot').pivotUI(data.data,{
                        sorted:true,
                        pivotId : "perfPivot",
                        aggregatorName:"Somme en entiers",
                        pivotContainerId :"perfContainer",
                        rows:["Enqueteur"],
                        cols:["Marché","Aggrégat"],
                        vals:["Nombre"],
                        onRefresh:function (pivotUIOptions){
                            $(".pvtRenderer").prependTo("#parametresTableau");

                        }
                    }, true, "fr");
                }else {
                    $('#perfPivot').html("Il n'y a pas de données pour dessiner le pivot")
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
                    buildPerformance();
                    periode.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                }
        );
        periode.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

        buildPerformance();

    });

</script>