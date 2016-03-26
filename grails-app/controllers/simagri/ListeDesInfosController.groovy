package simagri

class ListeDesInfosController {
def exportService
def filterPaneService
    def show() {

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def infoInstanceList= Info.createCriteria().list(params) {
            eq('actif',false)
            and {
                eq('isRejected',false)
            }
        }
        def  infoInstanceTotal=Info.createCriteria().list() {
            eq('actif',false)
            and {
                eq('isRejected',false)
            }
        }?.size()


        def infoValideInstanceList= Info.createCriteria().list(params) {
            eq('actif',true)
        }
        def  infoValideInstanceTotal=Info.createCriteria().list() {
            eq('actif',true)
        }?.size()


        render template:"index" ,model:[infoInstanceList:infoInstanceList,infoInstanceTotal:infoInstanceTotal,infoValideInstanceList: infoValideInstanceList, infoValideInstanceTotal: infoValideInstanceTotal]

    }

}
