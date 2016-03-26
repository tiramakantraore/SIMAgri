package simagri

class PageReseauController {
def myUtilityService
def grailsApplication
    def accueil(){

        try {
            def codeReseau=params.nomReseau
            def query = {
                if (params.nomReseau) {
                        ilike('nom', "%${params.nomReseau}%")
                }
            }
            def reseau = Reseau.createCriteria().list(query)?.first()


            def userProductsList=[]
            def userMarketsList=[]
            def user=myUtilityService.getCurrent()
            user?.attach()
            def userProductId=user?.mesProduits?.id?.flatten()
            if (userProductId){
                userProductsList=Produit.createCriteria().list() {
                    'in' ('id',userProductId)
                    order("nom", "asc")
                }
            }
            def userMarketId=user?.mesMarches?.id?.flatten()
            if (userMarketId){
                userMarketsList=Marche.createCriteria().list() {
                    'in' ('id',userMarketId)
                    order("nom", "asc")
                }
            }



            def nomProfile="${user.toString()}"
            def nombreMoisDerniersPrix=grailsApplication.config.grails.nbMoisDerniersPrix
            def   nombreMoisDernieresOffres=grailsApplication.config.grails.nombreMoisDernieresOffres?:3
            def   nombreMoisDerniersStocks=grailsApplication.config.grails.nombreMoisDerniersStocks?:3

            def sortIndex='date'
            def sortOrder='desc'

            def topNDocuments = S3Asset.createCriteria().list() {
                ne("status", "removed")
                optionList{
                    eq("name","reseauId")
                    eq("value",reseau?.id?:new Long("0"))
                }
                maxResults(5)
                order("id", "desc")
            }

            def topNInfos= Info.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
                eq("actif", true)
                and {
                    ge('reseau',reseau)
                }

                order(sortIndex, sortOrder)
            }

            def reseauxList = Reseau.createCriteria().list() {
                eq('estReseau',true)
                order('nom', 'asc')
            }

            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def isNewInfo = Info.createCriteria().list(params) {
                order('date', 'desc')
                and {
                    between("date", (new Date())-1, (new Date()))

                }
            }
// Within an action you can increment a counter
            // if (!user?.isSuperAdmin)



            [pageAccueilInstance:reseau.pageAccueil?:PageAccueil.findByEstPrincipal(true),
             activeMenu:'Tableau',
             nomUtilisateur:nomProfile,user:user,userProductsList:userProductsList,userMarketsList:userMarketsList,nombreMoisDerniersPrix:nombreMoisDerniersPrix,
             nombreMoisDernieresOffres:nombreMoisDernieresOffres,nombreMoisDerniersStocks:nombreMoisDerniersStocks,searchUpdate:"actualitesUpdate",
             topNInfos:topNInfos,reseauxList:reseauxList,documentList:topNDocuments,isNewInfo:(isNewInfo?.size()>0)]
        }

        catch (Exception e) {
            log.error " exception ${e}"
        }
    }
}
