<script type="text/javascript">
    CKEDITOR.editorConfig = function( config )
    {
        config.toolbar = 'MyToolbar';

        config.toolbar_MyToolbar =
                [
                    { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
                    { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','Scayt' ] },
                    '/',
                    { name: 'styles', items : [ 'Styles','Format' ] },
                    { name: 'basicstyles', items : [ 'Bold','Italic','Strike','-','RemoveFormat' ] },
                    { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote' ] },
                    { name: 'links', items : [ 'Link','Unlink','Anchor' ] }
                ];
    };
    var date=$('.datepicker');
    var dateExpiration=$('.dateExpiration');
    function myformatDate(date){
        if(!date.getDate()){return '';}
        var day = date.getDate();
        var month = date.getMonth();
        var year = date.getFullYear();
        month++; // adjust javascript month
        return jQuery.datepicker.formatDate( 'dd/mm/yy', date );
    }
    var dateLimite=new Date();


    function createInfos() {
        date.datepicker({
            onSelect: function(dateText,inst) {
                var currentDate = date.datepicker( "getDate" );
                if (currentDate> dateLimite){
                    alert("Vous ne pouvez choisir une date posterieure à la date du jour : "+myformatDate(dateLimite));
                    date.val(myformatDate(dateLimite));
                }

            }
        });

        dateExpiration.datepicker({

        });

    } //Fin du script de creation des informations

    $(document).ready(function() {
        createInfos();
    });
</script>
