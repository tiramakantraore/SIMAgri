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
class ProductXLSImporter extends AbstractExcelImporter {

       static Map CONFIG_BOOK_COLUMN_MAP = [sheet:'Produits', startRow: 1, columnMap:[ 'A' : 'code','B':'nom', 'C':'categorie','D':'unite_de_mesure']]


        static Map propertyConfigurationMap = [:]
//                code:([expectedType: ExcelImportService.PROPERTY_TYPE_STRING, defaultValue:'code']),
//        ]

        public ProductXLSImporter(fileName){
            super(fileName)
        }
    def getExcelImportService() {
        ExcelImportService.getService()
    }
        List<Map> getProducts(){
            List productList = excelImportService.columns(workbook, CONFIG_BOOK_COLUMN_MAP,null,propertyConfigurationMap)
            return productList
        }


}
