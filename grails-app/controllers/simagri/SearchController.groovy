package simagri

class SearchController {
    def searchAll() {

        def query = {
            if (params.query) {
                or {

                    ilike('title', "%${params.query}%")
                    ilike('description', "%${params.query}%")

                }

            }
            order("id", "desc")
        }
        def documents = S3Asset.createCriteria().list(query)
        def sortIndex = 'date'
        def sortOrder = 'desc'
        query = {
            if (params.query) {
                or {

                    ilike('titre', "%${params.query}%")
                    ilike('contenu', "%${params.query}%")

                }

            }
            order(sortIndex, sortOrder)
        }
        def topNInfos = Info.createCriteria().list(query)

        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        render view: 'index', model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,
                                      topNInfos: topNInfos, topNDocuments: documents]
//
//        render(template: "/home/documents", model: [topNDocuments: documents])

    }
    def index() {

        def topNDocuments = S3Asset.createCriteria().list() {
            ne("status", "removed")
            and {
                eq("mimeType", 'application/pdf')
            }

            order("id", "desc")
            maxResults(5)
        }
        def sortIndex = 'date'
        def sortOrder = 'desc'
        def topNInfos = Info.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
            eq("actif", true)
            and {
                ge("dateExpiration", new Date())

            }
            order(sortIndex, sortOrder)
            maxResults(5)
        }

        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        render view: 'index', model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true), pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,
                                      topNInfos: topNInfos, topNDocuments: topNDocuments, searchUpdate: "documents"]


    }
}
