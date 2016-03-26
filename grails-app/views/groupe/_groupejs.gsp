<script type="text/javascript">
    var marketItems=$("input[type='checkbox'][name^='markets_']");
    var produitsItems=$("input[type='checkbox'][name^='produits_']");
    var membresItems=$("input[type='checkbox'][name^='membres_']");
    var  id=$('#id');


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
                    membresItems.prop('checked', false);
                    marketItems.filter(function( index ) {
                        return (data.marches.hasObject(this.name))
                    }).prop('checked', true);

                    membresItems.filter(function( index ) {
                        return (data.membres.hasObject(this.name))
                    }).prop('checked', true);
                    var parent=$("input[type='radio'][value='"+data.parent+"']");
                    if (parent)
                    parent.prop('checked', true);
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
                    %{--url:"${createLink(controller:'groupe', action:'create')}",--}%
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
                    %{--url:"${createLink(controller:'groupe', action:'edit')}",--}%
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
        $('#check-allMembres').click(function(){
            $("input[type='checkbox'][name^='membres_']").prop('checked', true);
        });
        $('#injecter-Membres').click(function(){
            $.ajax({
                url: "${createLink(controller:'reseau', action:'updateByJSON')}",
                accepts: "text/json",
                data: {id:id.val(),isGroupe:true},
                success: function(data, status){
                    if (!data.isEmpty){
                            membresItems.filter(function( index ) {
                            return (data.membres.hasObject(this.name))
                        }).prop('checked', false);
                    }
               }
            });
         //   $("input[type='checkbox'][name^='membres_']").prop('checked', true);
        });
        $('#uncheck-allMembres').click(function(){
            $("input[type='checkbox'][name^='membres_']").prop('checked', false);
        });
      fireOnEdit();

    });
</script>