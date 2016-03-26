<script type="text/javascript">
    var mesuresItems=$("input[type='checkbox'][name^='mesures_']");
    var id=$('#id');
    function fireOnEdit() {
        if (!(id.val()==undefined)) {
        $.ajax({
            url: "${createLink(controller:'categorieProduit', action:'updateByJSON')}",
            accepts: "text/json",
            data: {id:id.val()},
            success: function(data, status){
                if (!data.isEmpty){
                    mesuresItems.prop('checked', false);
                    mesuresItems.filter(function( index ) {
                        return (data.mesures.hasObject(this.name))
                    }).prop('checked', true);


                }


            }
        });
       }
    }

    $(document).ready(function() {
       fireOnEdit();
    });
</script>