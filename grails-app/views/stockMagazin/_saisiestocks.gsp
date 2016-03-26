                        <g:form class="form-horizontal" action="saisie" >
                                            <div class="row">
                                            <div class="col-sm-12 col-md-12 ">
                                                <g:hiddenField name="selectedList"  />
                                                <g:hiddenField name="magazinId" />

                                                <g:render template="/stockMagazin/enteteFirstPage"/>
                                                <g:render template="/stockMagazin/grid_position"/>
                                            </div>
                                            </div>
                           </g:form>
                        <div class="form-actions">
                                        <div  class="btn-flat  btn-primary validerstock">

                                            <g:message code="default.button.create.label" default="Create" />
                                        </div>
                        </div>
                        %{--<g:render template="/stockMagazin/saisiestocksJs"/>--}%
