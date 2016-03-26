<script type="text/javascript">

    function tabselector(tabname){
        return  'a[href=\"'+'#'+tabname+'\"]';

    }

    function scriptCreerCalendrier() {
        var formParams=$('form[name="event"]');
        var valider=$(".validerCalendrier");



        $('#startTime').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });

        $('#endTime').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });

        valider.click(function(event) {
            event.stopPropagation();
            valider.attr('disabled', 'disabled');
            var jqxhr = $.ajax({
                type: "POST",
                url: "${createLink(controller:'event', action:'saveByService')}",
                data: {formData: JSON.stringify(getFormObj(formParams))},
                dataType: "text",
                cache: false,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=x-user-defined");
                }
            }).done(function () {
                $.jGrowl("L''événement a été créé avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                valider.removeAttr("disabled");
                formParams[0].reset();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                $.jGrowl("L''enregistrement de l'évenement a échoué, erreur rapportée " + errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });
        });

    }


    $(document).ready(function() {

        scriptCreerCalendrier();


    });
</script>