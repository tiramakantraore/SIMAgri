
%{--<div class="row">--}%
    %{--<div class="col-sm-12 col-md-12 ">--}%
        %{--<div id="reseauAdministreGroup" >--}%

                %{--<label for="groupId"><g:message code="groupe.text" default="Groupe" /> * </label>--}%
                %{--<input id="groupId" name="ReseauxIds" style="width:250px;" />--}%
                %{--<div id="treeview" >   </div>--}%
                %{--<g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>--}%

        %{--</div>--}%
    %{--</div>--}%
%{--</div>--}%
<div class="row">
    <div class="col-sm-12 col-md-12">
        %{--well small well-sm--}%
                <div class="row">

                    <div class="col-xs-8">

                        <f:field bean="utilisateurInstance" property="mobilePhone" required="true" >
                            <input type='tel' id='mobilePhone' name='mobilePhone' value="${utilisateurInstance?.mobilePhone}" class="form-control" autocomplete="off"/>
                        </f:field>
                </div>

                <div class="col-xs-4">
                </div>
               </div>





</div>
</div>