package simagriservices

import grails.transaction.Transactional
import simagri.Utilisateur
@Transactional(readOnly=true)
class MyUtilityService {
    def springSecurityService

    Utilisateur getCurrent(){
      if (springSecurityService.loggedIn)
           return Utilisateur.get(springSecurityService?.principal?.id?:new Long("0"))
        else
          return null


    }
    Boolean isLogged(){
            return springSecurityService?.loggedIn?:false


    }
    Long getPrincipal(){
        return springSecurityService?.principal?.id?:new Long("0")


    }

    def toLong(def value){
        def valueLong=[]

        if (value){
            value.each{
                    valueLong<<it.minus("[")?.minus("]")?.toLong()

            }
        }

        return valueLong
    }

    def toList(value) {
        [value].flatten().findAll { it != null }
    }
}
