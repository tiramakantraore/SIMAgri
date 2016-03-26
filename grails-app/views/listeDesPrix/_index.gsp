
    <div class="tab-pane" id="idPrixForm">

        <ul class="nav nav-pills" >

            <li><a href="#IdPrixAValider" data-toggle="tab" title="${g.message(code:"prixAValider.text", default:"Prix à valider")}">  ${g.message(code:"prixAValider.text", default:"Prix à valider")}</a></li>
            <li><a href="#IdPrixValides" data-toggle="tab" title="${g.message(code:"prixAValider.text", default:"Prix validés")} ">${g.message(code:"prixAValider.text", default:"Prix validés")} </a></li>
            <li><a href="#IdPrixRejetes" data-toggle="tab" title="${g.message(code:"prixRejetes.text", default:"Prix rejétés")} ">${g.message(code:"prixRejetes.text", default:"Prix rejétés")} </a></li>
        </ul>
        <div class="tab-content">

            <div class="tab-pane active" id="IdPrixAValider">
                <div id="listPrixAValiderContent">
                    <div class="row">
                        <div class="col-sm-12 col-md-12">

                            <div class="page-header">
                                <h1><g:message code="list.priceHolder" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>
                            %{--<filterpane:filterButton text="Rechercher" />--}%
                            <table class="table ">
                                <thead>
                                <tr>
                                    <th></th>

                                    <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="marche" title="${message(code: 'priceHolder.auteur.label', default: 'Auteur')}" />

                                    <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="marche" title="${message(code: 'priceHolder.marche.label', default: 'Marche')}" />

                                    <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="produit" title="${message(code: 'priceHolder.produit.label', default: 'Produit')}" />


                                    <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="isFromSMS" title="${message(code: 'priceHolder.sourcePrix.label', default: 'Source ')}" />

                                    <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="date" title="${message(code: 'priceHolder.date.label', default: 'Date')}" />


                                    <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="prixGros" title="${message(code: 'priceHolder.prixGros.label', default: 'Prix Gros')}" />

                                    <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="prixDetail" title="${message(code: 'priceHolder.prixDetail.label', default: 'Prix Détail')}" />



                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${priceHolderInstanceList}" var="priceHolderInstance">
                                    <tr>
                                        <td class="link">
                                            <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="priceHolder" action="edit" update="listPrixAValiderContent" method="GET"
                                                          id="${priceHolderInstance.id}" params="{update:'listPrixAValiderContent'}" class="btn-flat  btn-small">
                                                Modifier&raquo;</g:remoteLink>
                                        </td>
                                        <td>${fieldValue(bean: priceHolderInstance, field: "auteur")}</td>
                                        <td>${fieldValue(bean: priceHolderInstance, field: "marche")}</td>
                                        <td>${fieldValue(bean: priceHolderInstance, field: "produit")}</td>
                                        <td>${fieldValue(bean: priceHolderInstance, field: "sourcePrix")}</td>



                                        <td><g:formatDate date="${priceHolderInstance.date}" type="datetime" style="MEDIUM" timeStyle="MEDIUM"/></td>


                                        <td>${fieldValue(bean: priceHolderInstance, field: "prixGros")}</td>

                                        <td>${fieldValue(bean: priceHolderInstance, field: "prixDetail")}</td>


                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                            <div class="pagination">
                                <util:remotePaginate update="listPrixAValiderContent" controller="priceHolder" action="list"  total="${priceHolderInstanceTotal}" params="${filterParams}"/>

                            </div>
                            <export:formats controller="priceHolder" action="list" formats="['csv', 'excel', 'pdf']" />
                        </div>

                    </div>

                    %{--<filterpane:filterPane controller="priceHolder"  associatedProperties="marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"  excludeProperties="date,isFromSMS,periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,year,month,annee,mois,semaine,trimestre,globalPrice,transactionDate,active" dialog="true"  update="listPrixAValiderContent"  domain="simagri.PriceHolder" />--}%

                    %{--<g:render template="/priceHolder/list"/>--}%
                </div>

             </div>
            <div class="tab-pane " id="IdPrixValides">
                <div id="listPrixValidesContent">
                    <div class="row">
                        <div class="col-sm-12 col-md-12 ">

                            <div class="page-header">
                                <h1><g:message code="list.prix" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>

                            %{--<filterpane:filterButton text="Rechercher"/>--}%


                            <table id="prixHtml" class="table ">
                                <thead>
                                <tr>
                                    <th></th>

                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="categorieTarifaire" title="${message(code: 'prix.categorieTarifaire.label', default: 'Type prix')}" />

                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="auteur" title="${message(code: 'prix.auteur.label', default: 'Auteur')}" />

                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="produit" title="${message(code: 'prix.produit.label', default: 'Produit')}" />
                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="mesure" title="${message(code: 'prix.mesure.label', default: 'Mesure')}" />
                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="marche" title="${message(code: 'prix.marche.label', default: 'Marché')}" />

                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="date" title="${message(code: 'prix.date.label', default: 'Date')}" />

                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="isFromSMS" title="${message(code: 'priceHolder.sourcePrix.label', default: 'Source ')}" />

                                    <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="montant" title="${message(code: 'prix.montant.label', default: 'Montant')}" />

                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${prixInstanceList}" var="prixInstance">
                                    <tr>
                                        <td class="link">
                                            <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="prix"  action="edit" update="listPrixValidesContent" method="GET"
                                                          id="${prixInstance.id}" params="{update:'listPrixValidesContent'}" class="btn-flat  btn-small">
                                                Modifier&raquo;</g:remoteLink>
                                        </td>

                                        <td>${fieldValue(bean: prixInstance, field: "categorieTarifaire")}</td>

                                        <td>${fieldValue(bean: prixInstance, field: "auteur")}</td>

                                        <td>${fieldValue(bean: prixInstance, field: "produit")}</td>

                                        <td>${fieldValue(bean: prixInstance, field: "mesure")}</td>

                                        <td>${fieldValue(bean: prixInstance, field: "marche")}</td>

                                        <td><g:formatDate date="${prixInstance.date}" type="datetime" style="MEDIUM" timeStyle="MEDIUM" /></td>

                                        <td>${fieldValue(bean: prixInstance, field: "sourcePrix")}</td>


                                        <td>${fieldValue(bean: prixInstance, field: "montant")}</td>



                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                            <div class="pagination">
                                <util:remotePaginate update="listPrixValidesContent" controller="prix" action="list"  total="${prixInstanceTotal}" params="${filterParams}"/>

                            </div>
                            <export:formats controller="prix"  action="list" formats="['csv', 'excel', 'pdf']" />
                        </div>
                    </div>
                    <filterpane:filterPane controller="prix"  associatedProperties="marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"  excludeProperties="periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active" dialog="true" update="listPrixValidesContent" domain="simagri.PriceHolder" />

                </div>
            </div>
            <div class="tab-pane" id="IdPrixRejetes" >
            <g:render template="/saisiePrix/abandonnerPrix"/>
             </div>
        </div>

    </div>
<script type="text/javascript">

    $(document).ready(function() {
       $('a[href="#IdPrixAValider"]').tab('show');
    });

</script>
