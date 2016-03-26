package simagriclasses

import grails.util.Environment
import simagri.CategorieProduit
import simagri.Mesure
import simagri.Produit

class ProductImporter {
    String fileName
    Produit aProduct
    Mesure mesure
    CategorieProduit aProductCategory
    ProductImporter(String afileName)
    {
        this.fileName=afileName
    }
    void  executeImport(){
        ProductXLSImporter productImporter=new ProductXLSImporter(fileName)
        productImporter.getProducts().each{productMap->
            if ((Environment.current == Environment.DEVELOPMENT)||(Environment.current.name == 'devmali' )) {
                println "product ${productMap}"
            }
            def productcode=productMap.code
            def productCategory=productMap.categorie
            String codeMesure=productMap.unite_de_mesure?:""
            if (productcode!=null) {
                aProductCategory=CategorieProduit.findByNom(productCategory) ?:new CategorieProduit(nom:productCategory).save(failOnError: true)
                mesure=Mesure.findByCode(codeMesure)?:new Mesure(code:codeMesure,name: codeMesure,valeur: 1)
                if (!mesure)
                mesure=productCategory.equals("Animal")? Mesure.findByCode("tete"):Mesure.findByCode("kg")


				aProduct=Produit.findByCode(productMap.code)

                if (!aProduct){
                    new Produit(code:productMap.code,categorie:aProductCategory,nom:productMap.nom,nomScientifique: productMap.nom,mesure:mesure,actif:true).save(failOnError: true)
                } else
                {
                    aProduct.nom=productMap.nom
                    aProduct.nomScientifique=productMap.nom
                    aProduct.mesure=mesure
                    aProduct.save(failOnError: true)
                }


            }
        }
    }


}