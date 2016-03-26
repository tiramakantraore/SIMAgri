package simagriservices

import simagri.Utilisateur

class AutoCompleteService {

    def list(params) {

        def query = {
            if (params.term.substring(1,2).matches("[0-9]"))
            {
                ilike("mobilePhone", "%${params.term}%")

            } else {
                ilike("nomComplet", "%${params.term}%")
            }
            projections { // good to select only the required columns.

                property("id")
                property("nomComplet")
                property("mobilePhone")

            }
            order("nomComplet", "asc")

        }
        def clist = Utilisateur.createCriteria().list(query) // execute  to the get the list of companies
        def offerOwnerSelectList = [] // to add each company details
        clist.each {
            def ownerMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
            ownerMap.put("id", it[0])
            ownerMap.put("label", "${it[1]} [mobile:${it[2]}]")
            ownerMap.put("value", "${it[1]}")
            ownerMap.put("mobilePhone", "${it[2]}")
            offerOwnerSelectList.add(ownerMap) // add to the arraylist
        }
        return offerOwnerSelectList
    }
}
