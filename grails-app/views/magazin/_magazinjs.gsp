<script type="text/javascript">
    var produitsItems=$("input[type='checkbox'][name^='produits_']");
    var  id=$('#id');


    function fireOnEdit()
    {
        if (!(id.val()==undefined)) {
        $.ajax({
            url: "${createLink(controller:'magazin', action:'updateByJSON')}",
            accepts: "text/json",
            data: {id:id.val()},
            success: function(data, status){
                if (!data.isEmpty){
                    produitsItems.prop('checked', false);

                    var parent=$("input[type='radio'][value='"+data.parent+"']");
                    parent.prop('checked', true);
                    produitsItems.filter(function( index ) {
                        return (data.produits.hasObject(this.name))
                    }).prop('checked', true);


                }


            }
        });
       }

    }

    jQuery(document).ready(function() {


        $('#check-allProduits').click(function(){
            $("input[type='checkbox'][name^='produits_']").prop('checked', true);
        });
        $('#uncheck-allProduits').click(function(){
            $("input[type='checkbox'][name^='produits_']").prop('checked', false);
        });

      fireOnEdit();

    });
</script>