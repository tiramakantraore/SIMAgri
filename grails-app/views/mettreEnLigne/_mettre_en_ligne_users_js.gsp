<script type="text/javascript">

    var submitUsersBtn=$('#sendUsers');

    var ReseauxIds=$('#ReseauxIds');
    var reseauTree=$('#usertreeview');

    function submitUserForm(formObj, url,inputField,successMessage,updateSelector) {
        loadUsers();
        $(formObj).ajaxSubmit({
            async: true,
            url: url,
            success: function (data) {
                if (successMessage>"") {
                    $.jGrowl(successMessage, {
                        sticky: false,
                        header: 'Notification',
                        theme: 'iphone'
                    });
                }
                if (updateSelector>''){
                    $('#'+updateSelector).html(data);
                    $(formObj).trigger("reset");
                }else {
                    emptyFileInputField(formObj,inputField);  //reset and hide input file info
                    $(formObj).trigger("reset");   //reset other form elements
                }
            }
        });
    }
    function loadUsers(){
        var checkedNodes = [],
                PtreeView = $('#usertreeview').data("kendoTreeView"),
                message;

        checkedNodeIds(PtreeView.dataSource.view(), checkedNodes);

        if (checkedNodes.length > 0) {
            message = "IDs of checked nodes: " + checkedNodes.join(",");
            $('#ReseauxIds').val(checkedNodes.join(","));

        } else {
            message = "No nodes checked.";
        }

    }
    function importerUtilisateurs() {


//        $('#myUserFile").kendoUpload();


        $.getJSON("${createLink(controller:'reseau', action:'getSimpleTree')}", function (data) {
            var reseaux= reseauTree.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }

            });
            reseauTree.data("kendoTreeView").dataSource.bind("change", function (e) {
                var checkedNodes = [],
                        treeView = reseauTree.data("kendoTreeView"),
                        message;

                checkedNodeIds(treeView.dataSource.view(), checkedNodes);

                if (checkedNodes.length > 0) {
                    message = "IDs of checked nodes: " + checkedNodes.join(",");
                } else {
                    message = "No nodes checked.";
                }

//                    kendoConsole.log("is changing on " + this.text(e.node));
                var optionarray = []; var i=0; j=0;
                for(i=0; i < checkedNodes.length; i++ ) {
                    optionarray[j] = {
                        'id' :checkedNodes[i]
                    };
                    j++
                }

            });
            reseauTree.data("kendoTreeView").expand(".k-item");
        });



    }//Fin du script d'importation des utilisateurs
    $(document).ready(function() {
        importerUtilisateurs();
    });
</script>