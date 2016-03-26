<script type="text/javascript">
    var submitBtn=$('#submit');
    var reseauTree=$('#treeview');
    var marketItems=$("input[type='checkbox'][name^='markets_']");
    var marchesEcritureItems=$("input[type='checkbox'][name^='marchesEcriture_']");
    var produitsItems=$("input[type='checkbox'][name^='produits_']");
    var magazinsItems=$("input[type='checkbox'][name^='magazins_']");
    var servicesItems=$("input[type='checkbox'][name^='services_']");
    var checkAllMarkets=$('#check-allMarkets'),checkAllProduits=$('#check-allProduits'),
            checkAllMagazins=$('#check-allMagazins'),
            unCheckAllMarkets=$('#uncheck-allMarkets'), unCheckAllProduits=$('#uncheck-allProduits'),
            unCheckAllMagazins=$('#uncheck-allMagazins');


    var previousSizeOfChecked= 0,
    idResaux=$('#groupsId'),
    performance=$('#performance'),
            marcheEnqueteur=$('#marcheEnqueteur');
   var  id=$('#theId');
  var markets=$('#markets'),
            produits=$('#produits'),
            userType=$('#userType'),
            suffixe=$('#suffixe'),
            magazins=$('#magazins'),
          isChange=$('#isChange');
   var marketIds=$('#marketIds');
    var dateLimite=new Date();
    var options={format:'dd/mm/yyyy',weekStart:0};
    var showForm=$("form[name='show']");
    var url;
    if (isChange.val() == "true") {
        url="${createLink(controller:'utilisateur', action:'editProfile')}";
    }else {
        url="${createLink(controller:'utilisateur', action:'edit')}";
    }


    $(".modifier").click(function() {
                showForm.ajaxSubmit({
                    async: true,
                    method:'GET',
                    data:{id:id.val(),userType:userType.val(),isCreation:false,suffixe:suffixe.val(),isChange:(isChange.val() == "true")},
                    url: url,
                    success: function (data) {
                        if (isChange.val() == "true") {
                            $('#bodyProfile').html(data);
                        }else {
                            $('#listContent').html(data);
                        }

                    },
                    before:showSpinner(),
                    onComplete:hideSpinner()

                });
                return false; // prevent normal submit
            }
    );
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
                    produitsItems.prop('checked', false);
                    magazinsItems.prop('checked', false);
                    marchesEcritureItems.prop('checked', false);
                    servicesItems.prop('checked', false);

                    marketItems.filter(function( index ) {
                        return (data.marches.hasObject(this.name))
                    }).prop('checked', true);

                    produitsItems.filter(function( index ) {
                        return (data.produits.hasObject(this.name))
                    }).prop('checked', true);

                    marchesEcritureItems.filter(function( index ) {
                        return (data.marchesEcriture.hasObject(this.name))
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
    var div_click_escape=function(eventObject){
        return false;
    };

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
            fireOnSelectReseau(optionarray) ;}
        previousSizeOfChecked=checkedNodes.length;
    }

    $(document).ready(function() {


        $('input[type="checkbox"]').click(function(){
            if($(this).attr("value")=="pwd"){
                $(".changepwd").toggle();
            }
        });
        $.getJSON("${createLink(controller:'reseau', action:'getSimpleTree')}", {userId:id.val()},function (data) {
            var reseaux= reseauTree.kendoTreeView({
                dataSource: data  ,
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
        $('#dateOfBirth').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });
        $('#mobilePhone').focus();
//        $(".close").click(function(){
//            $(".alert").alert();
//        });
        reseauTree.click(div_click_escape);
        reseauTree.mousedown(div_click_escape);
        reseauTree.mouseup(div_click_escape);

        marketItems.click(div_click_escape);
        marketItems.mousedown(div_click_escape);
        marketItems.mouseup(div_click_escape);

        produitsItems.click(div_click_escape);
        produitsItems.mousedown(div_click_escape);
        produitsItems.mouseup(div_click_escape);


        magazinsItems.click(div_click_escape);
        magazinsItems.mousedown(div_click_escape);
        magazinsItems.mouseup(div_click_escape);





    });
</script>