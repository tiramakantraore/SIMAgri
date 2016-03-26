<script type="text/javascript">
    var submitBtn=$('#submit');
    var marketItems=$("input[type='checkbox'][name^='markets_']");
    var produitsItems=$("input[type='checkbox'][name^='produits_']");
    var  id=$('#id');


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
    %{--var createForm=$("form[name='create']");--}%
    %{--$(".validerCreate").click(function() {--}%
                %{--createForm.ajaxSubmit({--}%
                    %{--async: true,--}%
                    %{--url:"${createLink(controller:'reseau', action:'create')}",--}%
                    %{--success: function (data) {--}%
                        %{--createForm[0].reset();--}%
                        %{--$('a[href="#IdParent"]').tab('show');--}%
                        %{--$.jGrowl("La création du groupe a réussi", {--}%
                            %{--sticky: false,--}%
                            %{--header: 'Notification',--}%
                            %{--theme: 'iphone'--}%
                        %{--});--}%

                    %{--},--}%
                    %{--beforeSubmit :showSpinner(),--}%
                    %{--onComplete:hideSpinner()--}%

                %{--});--}%
                %{--return false; // prevent normal submit--}%
            %{--}--}%
    %{--);--}%
    %{--var editForm=$("form[name='edit']");--}%
    %{--$(".validerEdit").click(function() {--}%

                %{--editForm.ajaxSubmit({--}%
                    %{--async: true,--}%
                    %{--url:"${createLink(controller:'reseau', action:'edit')}",--}%
                    %{--success: function (data) {--}%
                        %{--$('#listContent").html(data);--}%
                    %{--}--}%

                %{--});--}%
                %{--return false; // prevent normal submit--}%
            %{--}--}%
    %{--);--}%

    jQuery(document).ready(function() {
        $('#check-allMarkets').click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', true);
        });
        $('#uncheck-allMarkets').click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', false);
        });

        $('#check-allProduits').click(function(){
            $("input[type='checkbox'][name^='produits_']").prop('checked', true);
        });
        $('#uncheck-allProduits').click(function(){
            $("input[type='checkbox'][name^='produits_']").prop('checked', false);
        });


       fireOnEdit();

    });
</script>