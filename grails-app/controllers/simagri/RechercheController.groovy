package simagri

class RechercheController {
    def elasticSearchService
    def searchCheckList(){
        log.info("params ${params}")
        def containerClass="my4Columns  limitHeight"
        def instanceName="markets"
        def referenceCollection=Utilisateur.findAll()


        render(template:"/partials/checkList" ,model:[ containerClass: containerClass,instanceName:instanceName,referenceCollection:referenceCollection])
    }
    def searchAll () {
        def res = elasticSearchService?.search("${params.query}")?.searchResults
        def resMsg = "<span class=\"resultatRecherche\"> Résultats de la recherche:</span><br/>"
      //  def resMsg = "<strong> Résultats de la recherche:</strong>"
        resMsg += "<div class=\"contentuResultatRecherche searchwell\">"
        res.each {
            switch(it){

                case Produit:
                    resMsg += "<strong>Produit :</strong> ${it.toString()} <br />"
                    break
                case Utilisateur:
                    resMsg += "<strong>Membre :</strong> ${it.toString()} <br />"
                    break
                case Prix:
                    resMsg += "<strong>Prix :</strong> ${it.toString()} <br />"
                    break
                case Offre:
                    resMsg += "<strong>Offre :</strong> ${it.toString()}<br />"
                    break
                case Reseau:
                    resMsg += "<strong>Reseau :</strong> ${it.toString()} <br />"
                    break
                default:
                    resMsg += "<strong>Autre :</strong> ${it}<br />"
                    break
            }
        }
        resMsg += "</div>"
        flash.notice = resMsg
        def resultat
        if (params.query)
            resultat=res?resMsg:"Aucun résultat trouvé"
        else
            resultat=""

        render template: 'searchresult',model:[resultat:resultat]

    }
    def index() {}
}
