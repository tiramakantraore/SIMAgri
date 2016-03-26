<!doctype html>

<html>
<head>
    <meta name="layout" content="adminLayout">

    <title>Bienvenue sur SIMAgri</title>

</head>
<body>
<div class="col-sm-4 col-md-4">
</div>
<div class="col-sm-6 col-md-6">
<div class="tabbable"> <!-- Only required for left/right tabs -->
    <ul class="nav nav-tabs nav-pills">
        <li class="active"><a href="#tab1" data-toggle="tab">Minutes</a></li>
        <li><a href="#tab2" data-toggle="tab">Par heure</a></li>
        <li><a href="#tab3" data-toggle="tab">Par jour</a></li>
        <li><a href="#tab4" data-toggle="tab">Par semaine</a></li>
        <li><a href="#tab5" data-toggle="tab">Par mois</a></li>
        <li><a href="#tab6" data-toggle="tab">Par an</a></li>
    </ul>

    <div class="tab-content">
        <div class="tab-pane active" id="tab1">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div id="container">   </div>
                </div>
            </div>
        </div>
        <div class="tab-pane " id="tab2">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div id="containerPie"> </div>
                </div>
            </div>
        </div>
        <div class="tab-pane" id="tab3">
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div id="containerLine"> </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="col-sm-4 col-md-4">
</div>
</body>
</html>