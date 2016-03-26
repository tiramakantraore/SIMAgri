package simagri

import org.codehaus.groovy.grails.commons.GrailsClass

class ListesController {
def grailsApplication
def myUtilityService
    def index() {

    }
    def searchAll() {
//        def query = {
//            if (params.query) {
//                or {
//
//                    ilike('title', "%${params.query}%")
//                    ilike('description', "%${params.query}%")
//
//                }
//
//            }
//        }
//        def documents = S3Asset.createCriteria().list(query)
//
//        render(template: "/home/documents", model: [topNDocuments: documents])

    }
    def show(){
        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        def user=myUtilityService.getCurrent()
        render view: "index", model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),user:user,activeMenu:'Listes',
                                       pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,searchUpdate: "documents"]

    }
}
