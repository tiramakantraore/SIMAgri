package simagri

class ListeDesOffresController {
def exportService
def filterPaneService
    def show() {
        params.max = Math.min(params.max ? params.int('max') : 20, 100)

        def offreInstanceList= Offre.createCriteria().list(params) {
            eq('isValidated',false)
            and {
                eq('isRejected',false)
            }
        }
        def  offreInstanceTotal=Offre.createCriteria().list() {
            eq('isValidated',false)
            and {
                eq('isRejected',false)
            }
        }?.size()


        def offreValideInstanceList= Offre.createCriteria().list(params) {
            eq('isValidated',true)
        }
        def  offreValideInstanceTotal=Offre.createCriteria().list() {
            eq('isValidated',true)
        }?.size()

        render template:"index" ,model:[offreInstanceList:offreInstanceList,offreInstanceTotal:offreInstanceTotal,offreValideInstanceList: offreValideInstanceList, offreValideInstanceTotal: offreValideInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

}
