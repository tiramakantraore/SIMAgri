package simagri

import grails.converters.JSON

class CartesGeoController {

    def dataMiningService
    def myUtilityService
    def myFormHelperService
    def index() { }


    def getDataForMap={
           render dataMiningService.getDataForMap(params)

    }
}
