package simagri

class ListeDesStocksController {
def exportService
def filterPaneService
    def show() {

        params.max = Math.min(params.max ? params.int('max') : 20, 100)

        def stockMagazinInstanceList= StockMagazin.createCriteria().list(params) {
            eq('isValidated',false)
        }
        def  stockMagazinInstanceTotal=StockMagazin.createCriteria().list() {
            eq('isValidated',false)
        }?.size()

        def stockMagazinInstanceValideList= StockMagazin.createCriteria().list(params) {
            eq('isValidated',true)
        }
        def  stockMagazinInstanceValideTotal=StockMagazin.createCriteria().list() {
            eq('isValidated',true)
        }?.size()

        render template:"index" ,model:[stockMagazinInstanceList:stockMagazinInstanceList,stockMagazinInstanceTotal:stockMagazinInstanceTotal,stockMagazinInstanceValideList: stockMagazinInstanceValideList, stockMagazinInstanceValideTotal: stockMagazinInstanceValideTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]




    }

}
