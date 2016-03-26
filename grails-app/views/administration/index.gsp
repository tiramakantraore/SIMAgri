<!doctype html>
<html>
<head>
    <meta name="layout" content="accueilLayout">
    <title><g:message code="administration.text" /></title>
    <style type="text/css">

    .entry-content {font-family: 'robotoregular',cursive;}
    </style>
    <ckeditor:resources/>
</head>
<body>
<div class="row  no-right-padding byBodyContainer">
    <div class="col-sm-3 col-md-3 ">
        <g:render template="/layouts/verticalAdminMenu"  />
    </div>
    <div class="col-sm-9 col-md-9 " id="myfluidbody" >

        <div id="listContent" >
            <div class="cadreTexteAccueil">
                <p><g:message code="menu.liste.titre" /></p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        var container =$("#main");
        $('[data-toggle="collapse"]').click(function (e) {
            var chevState =$(this).children("i.indicator").toggleClass('icon-plus icon-minus');
            $(this).children("i.indicator").not(chevState).removeClass("icon-plus").addClass("icon-minus");


        });
        $("[data-type='item']").on("click", function () {
            $("[data-type='item']").removeClass("active");
            $(this).addClass("active");
        });

        openMenuOnHover();
    });

</script>
</body>
</html>
