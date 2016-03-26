package simagriclasses

import grails.util.Environment
import simagri.Marche
import simagri.Region

class MarketImporter {
    String fileName
    Marche aMarket
    Region aRegion
    MarketImporter(String afileName)
    {
        this.fileName=afileName
    }
    void  executeImport(){
        MarketXLSImporter marketImporter=new MarketXLSImporter(fileName)
        marketImporter.getMarkets().each{marketMap->
            if ((Environment.current == Environment.DEVELOPMENT)||(Environment.current.name == 'devmali' )) {
                println "marche ${marketMap}"
            }
            def regionName=marketMap.region
            def marketCode=marketMap.code
            def marketName=marketMap.nom
            if (marketCode!=null) {
                if (regionName)
                aRegion=Region.findByNom(regionName) ?:new Region(nom:regionName).save(failOnError: true)
                aMarket=Marche.findByNom(marketCode) ?:new Marche(code:marketCode,nom:marketName,region:aRegion).save(failOnError: true)

            }
        }
    }
}