<script type="text/javascript">


    var beginDate;
    var  periode= $('#periode');
    var  fromDate= $('#fromDate');
    var  toDate= $('#toDate');
    var nbjrs=-30;

    function buildBilanSMS() {

        $.ajax({
            url: "${createLink(controller:'home', action:'setBilanSMS')}",
            data: {fromDate:fromDate.val(),
                toDate:toDate.val()},
            dataType: "json",
            accepts: "text/json",
            success: function(data, status){
                if (!data.isEmpty) {
                    $('#parametresTableau').empty();

                $('#smsPivot').pivotUI(data.data,{
                    sorted:true,
                    menuLimit:1000,
                    pivotId : "smsPivot",
                    pivotContainerId :"smsPivotContainer",
                    aggregatorName:"Somme en entiers",
                    rows:["Opérateur"],
                    cols:["Réseau"],
                    vals:["Nbre SMS"],
                    onRefresh:function (pivotUIOptions){
                        $(".pvtRenderer").prependTo("#parametresTableau");

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
                    buildBilanSMS();
                    periode.find('span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
                }
        );
        periode.find('span').html(moment(nbjrs.days().fromNow()).format('DD/MM/YYYY') + ' - ' + moment(Date.parse('today')).format('DD/MM/YYYY'));

        buildBilanSMS();

    });

</script>