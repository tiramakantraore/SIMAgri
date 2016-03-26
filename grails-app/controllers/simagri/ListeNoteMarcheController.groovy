package simagri

class ListeNoteMarcheController {
def exportService
def filterPaneService
    def show() {

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def noteMarcheInstanceList= NoteMarche.createCriteria().list(params) {
            eq('actif',false)
            and {
                eq('isRejected',false)
            }
        }
        def  noteMarcheInstanceTotal=NoteMarche.createCriteria().list() {
            eq('actif',false)
            and {
                eq('isRejected',false)
            }
        }?.size()


        def noteMarcheValideInstanceList= NoteMarche.createCriteria().list(params) {
            eq('actif',true)
        }
        def  noteMarcheValideInstanceTotal=NoteMarche.createCriteria().list() {
            eq('actif',true)
        }?.size()


        render template:"index" ,model:[noteMarcheInstanceList:noteMarcheInstanceList,noteMarcheInstanceTotal:noteMarcheInstanceTotal,noteMarcheValideInstanceList: noteMarcheValideInstanceList, noteMarcheValideInstanceTotal: noteMarcheValideInstanceTotal]

    }

}
