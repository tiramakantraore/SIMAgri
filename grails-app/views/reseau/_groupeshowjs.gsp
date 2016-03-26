<script type="text/javascript">
    var submitBtn=$('#submit');
    var marketItems=$("input[type='checkbox'][name^='markets_']");
    var produitsItems=$("input[type='checkbox'][name^='produits_']");
    var  id=$('#theId');


    function fireOnEdit()
    {
        if (!(id.val()==undefined)) {

        $.ajax({
            url: "${createLink(controller:'reseau', action:'updateByJSON')}",
            accepts: "text/json",
            data: {id:id.val(),isGroupe:false},
            success: function(data, status){
                if (!data.isEmpty){
                    marketItems.prop('checked', false);
                    produitsItems.prop('checked', false);
                    marketItems.filter(function( index ) {
                        return (data.marches.hasObject(this.name))
                    }).prop('checked', true);

                    produitsItems.filter(function( index ) {
                        return (data.produits.hasObject(this.name))
                    }).prop('checked', true);


                }


            }
        });
       }

    }
    var div_click_escape=function(eventObject){
        return false;
    };
    jQuery(document).ready(function() {
              fireOnEdit();
        marketItems.click(div_click_escape);
        marketItems.mousedown(div_click_escape);
        marketItems.mouseup(div_click_escape);

        produitsItems.click(div_click_escape);
        produitsItems.mousedown(div_click_escape);
        produitsItems.mouseup(div_click_escape);
    });
</script>