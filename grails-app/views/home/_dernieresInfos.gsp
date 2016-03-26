<g:if test="${topNInfos}">
    <div class="contentbox">

        <div class="panel panel-default">
            <div class="panel-heading"> <span class="glyphicon glyphicon-list-alt"></span><b>Flash Infos</b></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-12 col-md-12">

                        <ul class="flashNews">



                            <g:each var="info" in="${topNInfos}">
                                <li class="news-item">
                                    <g:link controller="info" action="showPublic" id="${info?.id}" target="_blank">
                                        <p>${info?.infoTitle}</p>
                                    </g:link>
                                </li>

                            </g:each>

                        </ul>

                    </div>
                </div>
            </div>
            <div class="panel-footer"> </div>
        </div>




        <br>
        <div class="row">
            <div class="col-sm-2 col-md-2">
            </div>
            <div class="col-sm-8 col-md-8">
                <g:link controller="info" action="listPublic" target="_blank" class="btn-flat  btn-success btn-medium">
                    Voir toutes les actualités
                </g:link>
            </div>
            <div class="col-sm-2 col-md-2">
            </div>
        </div>


    </div>

</g:if>