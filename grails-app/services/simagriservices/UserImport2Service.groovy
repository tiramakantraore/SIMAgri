package simagriservices

import grails.transaction.Transactional

@Transactional
class UserImport2Service {
    String theContextPath
    def execute(String myContextPath){
		
       theContextPath=myContextPath
//        new UserImporter(myContextPath+'/excell-datas/Reseau_Centre_Nord_1.xls').executeImport()
//        new UserImporter(myContextPath+'/excell-datas/Reseau_Centre_Nord.xls').executeImport()
//        new UserImporter(myContextPath+'/excell-datas/Reseau_FADA.xls').executeImport()
    }
}
