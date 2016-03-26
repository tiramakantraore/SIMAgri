<script type="text/javascript">
    var marketItems=$("input[type='checkbox'][name^='markets_']");
    var produitsItems=$("input[type='checkbox'][name^='produits_']");
    var parentsItems=$("input[type='radio'][name^='parent_']");
    var membresItems=$("input[type='checkbox'][name^='membres_']");
    var  id=$('#theId');

    var div_click_escape=function(eventObject){
        return false;
    };
    function fireOnEdit()
    {
        if (!(id.val()==undefined)) {
        $.ajax({
            url: "${createLink(controller:'reseau', action:'updateByJSON')}",
            accepts: "text/json",
            data: {id:id.val(),isGroupe:true},
            success: function(data, status){
                if (!data.isEmpty){
                    marketItems.prop('checked', false);
                    produitsItems.prop('checked', false);
                    marketItems.filter(function( index ) {
                        return (data.marches.hasObject(this.name))
                    }).prop('checked', true);
                    var parent=$("input[type='radio'][value='"+data.parent+"']");
                    parent.prop('checked', true);
                    produitsItems.filter(function( index ) {
                        return (data.produits.hasObject(this.name))
                    }).prop('checked', true);
                    membresItems.filter(function( index ) {
                        return (data.membres.hasObject(this.name))
                    }).prop('checked', true);

                }


            }
        });
       }

    }

    jQuery(document).ready(function() {

      fireOnEdit();

        parentsItems.click(div_click_escape);
        parentsItems.mousedown(div_click_escape);
        parentsItems.mouseup(div_click_escape);

        marketItems.click(div_click_escape);
        marketItems.mousedown(div_click_escape);
        marketItems.mouseup(div_click_escape);

        produitsItems.click(div_click_escape);
        produitsItems.mousedown(div_click_escape);
        produitsItems.mouseup(div_click_escape);

    });
</script>