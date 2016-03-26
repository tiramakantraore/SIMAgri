<script type="text/javascript">
    var childCount= 0,index=0;
    function addChoice(){
        index=childCount+1;
        var clone = $('#detail_clone').clone();
        var htmlId = 'details['+childCount+'].';
        var detailInput = clone.find("input[id$=choix]");

        clone.find("input[id$=id]")
                .attr('id',htmlId + 'id')
                .attr('name',htmlId + 'id');
        clone.find("input[id$=deleted]")
                .attr('id',htmlId + 'deleted')
                .attr('name',htmlId + 'deleted');
        clone.find("input[id$=new]")
                .attr('id',htmlId + 'new')
                .attr('name',htmlId + 'new')
                .attr('value', 'true');
        clone.find(".optionLabel").html("  <span class=\"optionLabel\">  Option "+index+" </span>");



        detailInput.attr('id',htmlId + 'choix')
                .attr('name',htmlId + 'choix');

        clone.attr('id', 'detail'+childCount);
        $('#childList').append(clone);
        clone.show();
        detailInput.focus();
        childCount++;
    }
    jQuery(document).ready(function() {
        childCount = ${sondageInstance?.details?.size()} + 0;
        var id=$('#sondageId');
        if (id.val()==undefined){
            addChoice();
            addChoice();
            addChoice();
            $('#titre').focus();

        }


//                            $('#datetimepicker3').datetimepicker({
//                               pick12HourFormat: false
//                           });
//                           $('#setMinDate").click(function () {
//                               $('#datetimepicker3').data("DateTimePicker").setMinDate(new Date("june 12, 2013"));
//                           });
//                           $('#setMaxDate").click(function () {
//                               $('#datetimepicker3').data("DateTimePicker").setMaxDate(new Date("july 4, 2013"));
//                           });
//                           $('#show").click(function () {
//                               $('#datetimepicker3').data("DateTimePicker").show();
//                           });
//                           $('#disable").click(function () {
//                               $('#datetimepicker3').data("DateTimePicker").disable();
//                           });
//                           $('#enable").click(function () {
//                               $('#datetimepicker3').data("DateTimePicker").enable();
//                           });
//                           $('#setDate").click(function () {
//                               $('#datetimepicker3').data("DateTimePicker").setDate("10/23/2013");
//                           });
//                           $('#getDate").click(function () {
//                               alert($('#datetimepicker3').data("DateTimePicker").getDate());
//                           });
//        $('#datetimepicker5').datetimepicker({
//            pickTime: false
//        });
        $('#dateDebut').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });
        $('#dateFin').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'
        });
//        $('#dateFin').datepicker({
//            pickTime: false,
//
//            language: 'fr'
//        });

        //bind click event on delete buttons using jquery live
        $('body').on('click', '.del-detail', function(){
            //find the parent div
            var prnt = $(this).parents(".detail-div");
            //find the deleted hidden input
            var delInput = prnt.find("input[id$=deleted]");
            //check if this is still not persisted
            var newValue = prnt.find("input[id$=new]").attr('value');
            //if it is new then i can safely remove from dom
            if(newValue == 'true'){
                prnt.remove();
                delInput.attr('value','true');
            }else{
                //set the deletedFlag to true
                delInput.attr('value','true');
                //hide the div
                prnt.hide();
            }
        });

    });
</script>