package simagriservices

import grails.transaction.Transactional
import simagriclasses.UserImporter
class UserImportService  {
    def grailsApplication
    String theContextPath
    def execute(String myContextPath){

        theContextPath=myContextPath
    }
    def importFile( String fileName,def params,Boolean ecraserDoublons){
        def userImport=new UserImporter(fileName)
        userImport.executeImport(params,ecraserDoublons)
    }
}
