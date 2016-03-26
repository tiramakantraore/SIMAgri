package simagriservices

import simagriclasses.MarketImporter


class MarketImportService {
    def grailsApplication
    String theContextPath
    def execute(String myContextPath){


        theContextPath=myContextPath
    }
    def importFile(String fileName){

        new MarketImporter(fileName).executeImport()
    }

}
