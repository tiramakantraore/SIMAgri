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
class MarketXLSImporter extends AbstractExcelImporter {

       static Map CONFIG_BOOK_COLUMN_MAP = [sheet:'Marchés', startRow: 2, columnMap:[ 'A' : 'code','B':'nom',C:'region', 'D':'lieu']]


        static Map propertyConfigurationMap = [:]
//                code:([expectedType: ExcelImportService.PROPERTY_TYPE_STRING, defaultValue:'code']),
//        ]

        public MarketXLSImporter(fileName){
            super(fileName)
        }
    def getExcelImportService() {
        ExcelImportService.getService()
    }
        List<Map> getMarkets(){
            List marketList = excelImportService.columns(workbook, CONFIG_BOOK_COLUMN_MAP,null,propertyConfigurationMap)
            return marketList
        }


}
