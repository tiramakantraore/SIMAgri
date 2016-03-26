package simagriservices

import simagri.Produit

class AutoCompleteProductService {

    def list(params) {
        def query = {
           or {
                ilike("nom", "${params.term}%") // term is the parameter send by jQuery autocomplete
                ilike("categorie.nom", "${params.term}%")
             }
            projections { // good to select only the required columns.
                createAlias('categorie','categorie')
                createAlias('mesure','mesure')
                property("id")
                property("nom")
                property("mesure.id")
                property("mesure.code")
            }
        }
        def clist = Produit.createCriteria().list(query) // execute  to the get the list of companies
        def productSelectList = [] // to add each company details
        clist.each {
            def ownerMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
            ownerMap.put("id", it[0])
            ownerMap.put("label", it[1])
            ownerMap.put("value", it[1])
            ownerMap.put("mesureId", it[2])
            ownerMap.put("mesureName", it[3 as String])
            productSelectList.add(ownerMap) // add to the arraylist
        }
        return productSelectList
    }
}
