package simagriclasses

import org.grails.plugins.excelimport.AbstractExcelImporter
import org.grails.plugins.excelimport.ExcelImportService


/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 11/06/12
 * Time: 06:35
 * To change this template use File | Settings | File Templates.
 */
class UserXLSImporter extends AbstractExcelImporter {

       static Map CONFIG_BOOK_COLUMN_MAP = [sheet:'Contacts', startRow: 1, columnMap:[ 'A' : 'firstName','B':'lastName', 'C':'mobilePhone'
               ,'D': 'gender', 'E': 'email', 'F': 'country', 'G': 'town', 'H': 'entreprise', 'I': 'activite', 'J': 'datenaissance'
                ]]


    static Map propertyConfigurationMap = [
                gender:([expectedType: ExcelImportService.PROPERTY_TYPE_STRING, defaultValue:'male']),
                name:([expectedType: ExcelImportService.PROPERTY_TYPE_STRING, defaultValue:'']),
                mobilePhone:([expectedType: ExcelImportService.PROPERTY_TYPE_STRING, defaultValue:'']),
                username:([expectedType: ExcelImportService.PROPERTY_TYPE_STRING, defaultValue:'']),
                activite:([expectedType: ExcelImportService.PROPERTY_TYPE_STRING, defaultValue:'']),
                datenaissance:([expectedType: ExcelImportService.PROPERTY_TYPE_DATE, defaultValue:''])]

        public UserXLSImporter(String fileName){
            super(fileName)
        }

    static def getExcelImportService() {
        ExcelImportService.getService()
    }
        List<Map> getUsers(){
            List userList = excelImportService.columns(workbook, CONFIG_BOOK_COLUMN_MAP,null,propertyConfigurationMap)
            return userList
        }


}
