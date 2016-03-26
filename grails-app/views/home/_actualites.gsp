<g:if test="${topNInfos?.size()?:0>0}">

        %{--<div class="panel panel-default ">--}%
            %{--<div class="panel-heading"> <span class="glyphicon glyphicon-list-alt"></span><b> Actualités</b></div>--}%
            %{--<div class="panel-body">--}%
                %{--<div class="row">--}%
                    %{--<div class="col-sm-12 col-md-12">--}%

                        <ul class="flashNews">



                            <g:each var="info" in="${topNInfos}">
                                <li class="news-item">
                                    <g:link controller="info" action="showPublic" id="${info?.id}" target="_blank">
                                        <p style="color:darkgreen "><strong>${info?.infoTitle}</strong></p>
                                    </g:link>
                                </li>

                            </g:each>

                        </ul>

                    %{--</div>--}%
                %{--</div>--}%
            %{--</div>--}%
            %{--<div class="panel-footer">--}%
                <div class="row">
                    %{--<div class="col-sm-2 col-md-2">--}%
                    %{--</div>--}%
                    <div class="col-sm-12 col-md-12">
                        <g:link controller="info" action="listPublic" target="_blank" class="btn-flat  btn-link">
                          <trong> Voir toutes les actualités</trong>
                        </g:link>
                    </div>
                    %{--<div class="col-sm-2 col-md-2">--}%
                    %{--</div>--}%
                </div>

            %{--</div>--}%
        %{--</div>--}%

</g:if>