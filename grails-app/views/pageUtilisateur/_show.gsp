
<%@ page import="simagri.PageUtilisateur" %>
<g:render template="/partials/showHeader" />

    <g:form class="form-horizontal" action="show" >
        <div class="row">
            <div class="col-sm-2 col-md-2">

            </div>
            <div class="col-sm-8 col-md-8 userPageContent" >
                <h1>${pageUtilisateurInstance?.nom}</h1>
                <g:if test="$pageUtilisateurInstance?.contenu?.trim()">
                    <bill:imageWithText texte="${pageUtilisateurInstance?.contenu}" imageURL="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageUtilisateurInstance?.id])}" imagePosition="${pageUtilisateurInstance?.alignement?.name() }"
                                        imageHeight="25"/>
                </g:if>
            </div>
            <div class="col-sm-2 col-md-2">

            </div>
        </div>
        <g:hiddenField name="id" value="${pageUtilisateurInstance?.id}" />
        <g:render template="/partials/btnShow"/>
    </g:form>
