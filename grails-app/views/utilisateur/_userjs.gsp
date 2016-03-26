<script type="text/javascript">
    var reseauTree=$('#treeview');
    var marketItems=$("input[type='checkbox'][name^='markets_']");
    var marchesEcritureItems=$("input[type='checkbox'][name^='marchesEcriture_']");
    var produitsItems=$("input[type='checkbox'][name^='produits_']");
    var categorieProduitsItems=$("input[type='checkbox'][name^='categories_']");
    var magazinsItems=$("input[type='checkbox'][name^='magazins_']");
    var servicesItems=$("input[type='checkbox'][name^='services_']");
    var mobilePhone=$("[name='mobilePhone']");
    var previousSizeOfChecked= 0,
    idResaux=$('#groupsId'),
    performance=$('#performance'),
     marcheEnqueteur=$('#marcheEnqueteur');
    var  id=$('#theId');
    var markets=$('#markets'),
    marketsMEL=$('#marketsMEL'),
            produits=$('#produits'),
            magazins=$('#magazins');
   var marketIds=$('#marketIds');
    var dateLimite=new Date();
    var options={format:'dd/mm/yyyy',weekStart:0};
    var checkAllMarkets=$('#check-allMarkets'),checkAllProduits=$('#check-allProduits'),
            checkAllMagazins=$('#check-allMagazins'),
            unCheckAllMarkets=$('#uncheck-allMarkets'), unCheckAllProduits=$('#uncheck-allProduits'),
            unCheckAllMagazins=$('#uncheck-allMagazins'), checkAllServices=$('#check-allServices'), unCheckAllServices=$('#uncheck-allServices'),
            allMarketsMiseEnLigne=$('#check-allMarketsMiseEnLigne'),uncheckMarketsMiseEnLigne=$('#uncheck-allMarketsMiseEnLigne');
    var
            userType=$('#userType'),
            suffixe=$('#suffixe'),
            isChange=$('#isChange'),
            url,
            isCreation=$('#isCreation');


    var editForm=$("form[name='edit']");
    var createForm=$("form[name='create']");
    if (isChange.val() == "true") {
        url="${createLink(controller:'utilisateur', action:'editProfile')}";
    }else {
        url="${createLink(controller:'utilisateur', action:'edit')}";
    }
    $(".validerEdit").click(function() {
        editForm.ajaxSubmit({
            async: true,
            url:url,
            data:{id:id.val(),userType:userType.val(),isCreation:isCreation.val(),suffixe:suffixe.val(),isChange:(isChange.val() == "true")},
            success: function (data) {
                             if (isChange.val() == "true") {
                                $('#bodyProfile').html(data);
                            }else {
                                $('#listContent').html(data);
                            }


            }
            ,
            beforeSubmit :showSpinner(),
            onComplete:hideSpinner()

    });
                return false; // prevent normal submit
            }
    );

    $(".validerCreate").click(function() {
                createForm.ajaxSubmit({
                    async: true,
                    url:"${createLink(controller:'utilisateur', action:'create')}",
                    data:{userType:userType.val(),isCreation:isCreation.val(),suffixe:suffixe.val(),isChange:(isChange.val() == "true")},
                    success: function (data) {
                           createForm[0].reset();
                          //  createForm.trigger("reset");   //reset other form elements
                        $('a[href="#IdIdentification"]').tab('show');
                        $.jGrowl("La création de l'utilisateur a réussi", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                        });

                    },
                    beforeSubmit :showSpinner(),
                    onComplete:hideSpinner()

                });
                return false; // prevent normal submit
            }
    );
    function fireOnSelectCategorie(optionarray)
    {

        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateProduitsFromCategories')}",
            data: {categories:JSON.stringify(optionarray),instanceName:"produits"},
            cache: false,
            success: function(html) {
                produits.html(html);
            }
        });

    }
    function fireOnSelectRegion(optionarray)
    {

        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateMarchesFromRegion')}",
            data: {regions:JSON.stringify(optionarray),instanceName:"markets"},
            cache: false,
            success: function(html) {
                markets.html(html);
            }
        });


    }
    function fireOnSelectMELRegion(optionarray)
    {

        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateMarchesFromRegion')}",
            data: {regions:JSON.stringify(optionarray),instanceName:"marchesEcriture"},
            cache: false,
            success: function(html) {
                marketsMEL.html(html);
            }
        });


    }


    function fireOnSelectReseau(optionarray)
    {
        $.ajax({
            url: "${createLink(controller:'autoComplete', action:'marcheJSON')}",
            accepts: "text/json",
            data: {selectedList:JSON.stringify(optionarray)},
            success: function(data, status){
                var listOfChecked=[];
               if (!data.isEmpty){
                   marketItems.prop('checked', false);

                   marketItems.filter(function( index ) {
                       return (data.data.hasObject(this.name))
                   }).prop('checked', true);


              }


            }
        });
        $.ajax({
            url: "${createLink(controller:'autoComplete', action:'produitJSON')}",
            accepts: "text/json",
            data: {selectedList:JSON.stringify(optionarray)},
            success: function(data, status){
                var listOfChecked=[];
                if (!data.isEmpty){
                    produitsItems.prop('checked', false);
                    produitsItems.filter(function( index ) {
                        return (data.data.hasObject(this.name))
                    }).prop('checked', true);

                }


            }
        });


    }
    function fireOnEdit() {
        if (!(id.val()==undefined)) {
        $.ajax({
            url: "${createLink(controller:'utilisateur', action:'updateByJSON')}",
            accepts: "text/json",
            data: {id:id.val()},
            success: function(data, status){
                if (!data.isEmpty){

                    marketItems.prop('checked', false);
                    categorieProduitsItems.prop('checked', false);
                    produitsItems.prop('checked', false);
                    magazinsItems.prop('checked', false);
                    marchesEcritureItems.prop('checked', false);
                    servicesItems.prop('checked', false);

                    marketItems.filter(function( index ) {
                        return (data.marches.hasObject(this.name))
                    }).prop('checked', true);

                    marchesEcritureItems.filter(function( index ) {
                        return (data.marchesEcriture.hasObject(this.name))
                    }).prop('checked', true);

                    categorieProduitsItems.filter(function( index ) {
                        return (data.categorieproduits.hasObject(this.name))
                    }).prop('checked', true);
                    produitsItems.filter(function( index ) {
                        return (data.produits.hasObject(this.name))
                    }).prop('checked', true);


                    magazinsItems.filter(function( index ) {
                        return (data.magazins.hasObject(this.name))
                    }).prop('checked', true);

                    servicesItems.filter(function( index ) {
                        return (data.services.hasObject(this.name))
                    }).prop('checked', true);
                }


            }
        });
       }
    }


    function fireOnTreeChecked() {
        var checkedNodes = [],
                treeView = reseauTree.data("kendoTreeView"),
                message;
        checkedNodeIds(treeView.dataSource.view(), checkedNodes);

        var optionarray = [];
        var i = 0;
        var j = 0;
        for (i = 0; i < checkedNodes.length; i++) {
            optionarray[j] = {
                'id': checkedNodes[i]
            };
            j++
        }
        if (checkedNodes.length > 0) {
            idResaux.val(checkedNodes.join(","));
        }
        if ((checkedNodes.length!=previousSizeOfChecked) && (previousSizeOfChecked>0)){
        if (optionarray.length>0)
            if ((id.val()==undefined)) {
                fireOnSelectReseau(optionarray);
            }
        }
        previousSizeOfChecked=checkedNodes.length;
    }
    function categorieClick(){
       var categoriesSelected = [];
        $('#listeCategories').find('input:checked').each(function () {
            categoriesSelected.push($(this).attr('value'));
        });
        fireOnSelectCategorie(categoriesSelected);
    }
    function regionClick(){
       var regionsSelected = [];
        $('#listeRegions').find('input:checked').each(function () {
            regionsSelected.push($(this).attr('value'));
        });
        fireOnSelectRegion(regionsSelected);
    }

    function regionMELClick(){
        var regionsSelected = [];
        $('#listeRegionsMEL').find('input:checked').each(function () {
            regionsSelected.push($(this).attr('value'));
        });
        fireOnSelectMELRegion(regionsSelected);
    }

    $(document).ready(function() {
        checkAllMarkets.click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', true);
        });
        unCheckAllMarkets.click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', false);
        });

        allMarketsMiseEnLigne.click(function(){
            $("input[type='checkbox'][name^='marchesEcriture_']").prop('checked', true);
        });
        uncheckMarketsMiseEnLigne.click(function(){
            $("input[type='checkbox'][name^='marchesEcriture_']").prop('checked', false);
        });

        checkAllProduits.click(function(){
            $("input[type='checkbox'][name^='produits_']").prop('checked', true);
        });
        unCheckAllProduits.click(function(){
            $("input[type='checkbox'][name^='produits_']").prop('checked', false);
        });

        checkAllMagazins.click(function(){
            $("input[type='checkbox'][name^='magazins_']").prop('checked', true);
        });
        unCheckAllMagazins.click(function(){
            $("input[type='checkbox'][name^='magazins_']").prop('checked', false);
        });

        checkAllServices.click(function(){
            $("input[type='checkbox'][name^='services_']").prop('checked', true);
        });
        unCheckAllServices.click(function(){
            $("input[type='checkbox'][name^='services_']").prop('checked', false);
        });

        $('input[type="checkbox"]').click(function(){
            if($(this).attr("value")=="pwd"){
                $(".changepwd").toggle();
            }
        });


        $.getJSON("${createLink(controller:'reseau', action:'getSimpleTree')}", {userId:id.val()},function (data) {
            var reseaux= reseauTree.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }
                ,
                schema: {
                    model: {
                        id: 'id',
                        text: 'text'
                    }
                }
            });
            reseauTree.data("kendoTreeView").dataSource.bind("change", function (e) {

                fireOnTreeChecked();
            });
            reseauTree.data("kendoTreeView").expand(".k-item");
        });
       fireOnEdit();
        $('.fileinput').fileinput();
        $("input[name='dateOfBirth']").datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });
        mobilePhone.focus();
        $.mask.definitions['~']='[+]';
        mobilePhone.mask("~99999999999");
        mobilePhone.change(function() {
            if (mobilePhone.val()>"") {

                $.getJSON("${createLink(controller:"home", action:'findUserByMobile')}", {mobilePhone:mobilePhone.val()},
                        function(data){
                            if (data.isValid){
                                if (!data.isEmpty) {
                                    alert(data.userName+' utilise déjà le numéro '+mobilePhone.val());

                                }
                            }
                            else {
                                alert("le numéro "+mobilePhone.val()+" n' est pas un numéro de téléphone valide");
                            }

                        }
                );



            }
        });
    });
</script>