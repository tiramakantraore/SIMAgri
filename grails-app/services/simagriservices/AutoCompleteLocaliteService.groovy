package simagriservices

import simagri.Service

class AutoCompleteLocaliteService {

    def list(params) {
        def query = {
           or {
                ilike("nom", "${params.term}%") // term is the parameter send by jQuery autocomplete


            }
            projections { // good to select only the required columns.
                property("nom")
                property("description")

            }
        }
        def clist = Service.createCriteria().list(query) // execute  to the get the list of companies
        def serviceSelectList = [] // to add each company details
        clist.each {
            def ownerMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
            ownerMap.put("id", it[0])
            ownerMap.put("label", it[1])
            ownerMap.put("value", it[1])

            serviceSelectList.add(ownerMap) // add to the arraylist
        }
        return serviceSelectList
    }
}
