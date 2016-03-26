<%@ page import="simagri.MonImage; simagri.PageUtilisateur; simagri.PageAccueil" %>

<g:render template="/partials/showHeader"/>
    <div class="row">
        <div class="col-sm-12 col-md-12">
            <g:if test="${flash.message}">
                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
            </g:if>

            <g:hasErrors bean="${pageUtilisateurInstance}">
                <bootstrap:alert class="alert-error">
                    <ul>
                        <g:eachError bean="${pageUtilisateurInstance}" var="error">
                            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </bootstrap:alert>
            </g:hasErrors>

            <g:form name="edit"  method="POST" class="form-horizontal"  >
				<g:hiddenField name="version" value="${pageAccueilInstance?.version}" />
                <g:hiddenField name="theId" value="${pageAccueilInstance?.id}" />

                <g:render template="form"/>


                <br>
                    <div class="form-actions">
                       <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'pageAccueil', action:'edit')}','','La validation de la page  a réussi','listContent');return false;" >
                            <g:message code="default.button.update.label" default="Update" />
                        </div>
                        <div onclick="submitForm($(this).closest('form'),
                                '${createLink(controller:'pageAccueil', action:'delete')}','','La suppression de la page d\'accueil a réussi','listContent');return false;" class="btn-flat  btn-primary" formnovalidate>
                            <i class="glyphicon glyphicon-trash"></i>
                            <g:message code="default.button.delete.label" default="Delete" />
                        </div>
                    </div>
			</g:form>
        </div>
    </div>

    <script type="text/javascript">
        var mesImages=$("input[type='checkbox'][name^='mesImages_']");
        var  id=$('#theId');
        // handle the back and forward buttons

        function fireOnEdit()
        {
            if (!(id.val()==undefined)) {
                $.ajax({
                    url: "${createLink(controller:'pageAccueil', action:'updateByJSON')}",
                    accepts: "text/json",
                    data: {id:id.val()},
                    success: function(data, status){
                        if (!data.isEmpty){

                            mesImages.prop('checked', false);
                            var pagePrixId=$("input[type='radio'][name='pagePrix'][value='"+data.pagePrixId+"']");
                            pagePrixId.prop('checked', true);
                            var pageOffreId=$("input[type='radio'][name='pageOffre'][value='"+data.pageOffreId+"']");
                            pageOffreId.prop('checked', true);
                            var pageStockId=$("input[type='radio'][name='pageStock'][value='"+data.pageStockId+"']");
                            pageStockId.prop('checked', true);
                            var pageReseauId=$("input[type='radio'][name='pageReseau'][value='"+data.pageReseauId+"']");
                            pageReseauId.prop('checked', true);
                            var banniereId=$("input[type='radio'][name='banniere'][value='"+data.banniereId+"']");
                            banniereId.prop('checked', true);



                            mesImages.filter(function( index ) {
                                return (data.mesImages.hasObject(this.name))
                            }).prop('checked', true);


                        }


                    }
                });
            }

        }
        jQuery(document).ready(function() {
            fireOnEdit();
            $(window).bind('popstate', function(event) {

                // if the event has our history data on it, load the page fragment with AJAX
                var state = event.originalEvent.state;
                if (state) {
                    container.load(state.path);
                }
            });

        });
    </script>
