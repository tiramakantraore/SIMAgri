package simagri

class ListeDesAlertesController {
def exportService
def filterPaneService
    def show() {


        params.max = Math.min(params.max ? params.int('max') : 25, 100)


        def alerteInstanceList= AlerteReseau.createCriteria().list(params) {
            eq('valide',false)
            and {
                eq('isRejected',false)
            }
        }
        def  alerteInstanceTotal=AlerteReseau.createCriteria().list() {
            eq('valide',false)
            and {
                eq('isRejected',false)
            }
        }?.size()

        def alerteValideInstanceList= AlerteReseau.createCriteria().list(params) {
            eq('valide',true)
        }
        def  alerteValideInstanceTotal=AlerteReseau.createCriteria().list() {
            eq('valide',true)
        }?.size()


        render template:"index" ,model:[alerteInstanceList:alerteInstanceList,alerteInstanceTotal:alerteInstanceTotal,alerteValideInstanceList: alerteValideInstanceList, alerteValideInstanceTotal: alerteValideInstanceTotal]

    }

}
