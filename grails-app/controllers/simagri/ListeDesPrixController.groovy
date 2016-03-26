package simagri

import org.grails.plugin.filterpane.FilterPaneUtils

class ListeDesPrixController {
def exportService
def filterPaneService
    def show() {
        params.max = Math.min(params.max ? params.int('max') : 15, 100)


        def priceHolderInstanceList= PriceHolder.createCriteria().list(params) {
            eq('isValidated',false)
            and {
                eq('isRejected',false)
            }
        }

        def  priceHolderInstanceTotal=PriceHolder.withCriteria {
            eq('isValidated',false)
            and {
                eq('isRejected',false)
            }
        }?.size()


        def prixInstanceList=Prix.list(params)
        def prixInstanceTotal=Prix.count
        render template:"index" ,model:[priceHolderInstanceList: priceHolderInstanceList, priceHolderInstanceTotal: priceHolderInstanceTotal,
                                        prixInstanceList: prixInstanceList,prixInstanceTotal: prixInstanceTotal]



    }
//    def export = {attrs ->
//        def  priceHolderInstanceTotal=PriceHolder.findAll{isValidated==false}.size()
//        println " export priceHolderInstanceTotal $priceHolderInstanceTotal"
//        params.max=priceHolderInstanceTotal
//        def response = attrs.response
//        response.contentType = grailsApplication.config.grails.mime.types[params.format]
//        response.setHeader("Content-disposition", "attachment; filename="+"Prix-a-valider".uniquify(".${params.extension}"))
//        List fields = [ "sourcePrix","date","marche","produit","globalPrice"]
//        Map labels = ["sourcePrix":"SOURCE","date":"DATE",
//                      "marche": "MARCHE", "produit": "PRODUIT",
//                      "globalPrice":"Prix"]
//
//        def humanCase = {
//            domain, value ->
//                return value?.humanify()
//        }
//        def dateFormat ={
//            domain, value ->
//                return value?.getAsDay()
//        }
//
//        Map formatters = [sourcePrix:humanCase,date:dateFormat]
//
//        Map parameters = [title: "LISTE DES PRIX A  VALIDER", "column.widths": [0.15, 0.12, 0.2, 0.20, 0.32, 0.10]]
//
//        exportService.export(params.format, response.outputStream, PriceHolder.list(params), fields, labels,formatters,parameters)
//    }
}
