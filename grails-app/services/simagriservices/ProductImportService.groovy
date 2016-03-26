package simagriservices

import grails.transaction.Transactional
import simagri.Mesure
import simagri.Qualite
import simagriclasses.ProductImporter
@Transactional
class ProductImportService {
    String theContextPath
    def importFile(String fileName){
        //   Chargement des mesures
        Mesure.findByCode("kg") ?: new Mesure(code: 'kg', name: 'kilogramme', isLocal: false).save(failOnError: true)
        Mesure.findByCode("tonne") ?: new Mesure(code: 'tonne', name: 'tonne', isLocal: false).save(failOnError: true)
        Mesure.findByCode("tine") ?: new Mesure(code: 'tine', name: 'tine', isLocal: true).save(failOnError: true)
        Mesure.findByCode("sac5kg") ?: new Mesure(code: 'sac5kg', name: 'Sac de 5 kilogrammes', isLocal: true).save(failOnError: true)
        Mesure.findByCode("sac25kg") ?: new Mesure(code: 'sac25kg', name: 'Sac de 25 kilogrammes', isLocal: true).save(failOnError: true)
        Mesure.findByCode("sac50kg") ?: new Mesure(code: 'sac50kg', name: 'Sac de 50 kilogrammes', isLocal: true).save(failOnError: true)
        Mesure.findByCode("sac100kg") ?: new Mesure(code: 'sac100kg', name: 'Sac de 100 kilogrammes', isLocal: true).save(failOnError: true)
        Mesure.findByCode("yoruba") ?: new Mesure(code: 'yoruba', name: 'yoruba', isLocal: true).save(failOnError: true)
        Mesure.findByCode("tete") ?: new Mesure(code: 'tete', name: 'Tête', isLocal: false).save(failOnError: true)
        Qualite.findByCode("A") ?: new Qualite(code:'A',nom:'Qualité A').save(failOnError: true)
        Qualite.findByCode("B") ?:new Qualite(code:'B',nom:'Qualité B').save(failOnError: true)
        Qualite.findByCode("C") ?:new Qualite(code:'C',nom:'Qualité C').save(failOnError: true)

        new ProductImporter(fileName).executeImport()


    }
}
