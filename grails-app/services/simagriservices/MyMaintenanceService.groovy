package simagriservices

import grails.transaction.Transactional
import org.joda.time.DateTime
import org.springframework.dao.OptimisticLockingFailureException
import simagri.*
@Transactional
class MyMaintenanceService {
    def springSecurityService
    def elasticSearchService
    def elasticSearchAdminService
    Utilisateur getCurrent(){
      if (springSecurityService.loggedIn)
           return Utilisateur.get(springSecurityService?.principal?.id?:new Long("0"))
        else
          return null


    }
    def indexer_prix(){
        try{
            elasticSearchService?.index(Prix)
            elasticSearchAdminService?.refresh()
        }
        catch(Exception e){
            log.debug(e)
        }
    }
    def indexer_offres(){
        try{
            elasticSearchService?.index(Offre)
            elasticSearchAdminService?.refresh()
        }
        catch(Exception e){
            log.debug(e)
        }
    }
    def indexer_tables_de_base(){
        try{
            elasticSearchService?.index(Civilite)
            elasticSearchService?.index(Mesure)
            elasticSearchService?.index(Produit)
            elasticSearchService?.index(Region)
            elasticSearchService?.index(Province)
            elasticSearchService?.index(Commune)
            elasticSearchService?.index(Marche)
            elasticSearchService?.index(Utilisateur)
            elasticSearchService?.index(Reseau)
            elasticSearchAdminService?.refresh()
        }
        catch(Exception e){
            log.debug(e)
        }
    }


    def mettre_a_jour_les_roles(){
//        Promise p = task {
            Requestmap.findByUrlAndConfigAttribute('/**','IS_AUTHENTICATED_ANONYMOUSLY')?:new Requestmap(url: '/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save(failOnError: true)
            Requestmap.findByUrlAndConfigAttribute('/accueil','ROLE_USER,ROLE_ADMIN,ROLE_SUPERVISEUR,ROLE_ENQUETEUR,ROLE_ANONYME,ROLE_SUPER_ADMIN')?:new Requestmap(url: '/accueil', configAttribute: 'ROLE_USER,ROLE_ADMIN,ROLE_SUPERVISEUR,ROLE_ENQUETEUR,ROLE_ANONYME,ROLE_SUPER_ADMIN').save(failOnError: true)
            Requestmap.findByUrlAndConfigAttribute('/admin','ROLE_ADMIN,ROLE_SUPERVISEUR')?:new Requestmap(url: '/admin', configAttribute: 'ROLE_ADMIN,ROLE_SUPERVISEUR').save(failOnError: true)
            Requestmap.findByUrlAndConfigAttribute('/params/create','ROLE_SUPER_ADMIN')?:new Requestmap(url: '/params/create', configAttribute: 'ROLE_SUPER_ADMIN').save(failOnError: true)
            Requestmap.findByUrlAndConfigAttribute('/params/list','ROLE_SUPER_ADMIN')?:new Requestmap(url: '/params/list', configAttribute: 'ROLE_SUPER_ADMIN').save(failOnError: true)
            Requestmap.findByUrlAndConfigAttribute('/offre/*','IS_AUTHENTICATED_REMEMBERED')?:new Requestmap(url: '/offre/*', configAttribute: 'IS_AUTHENTICATED_REMEMBERED').save(failOnError: true)
            Requestmap.findByUrlAndConfigAttribute('/prix/*','ROLE_USER,IS_AUTHENTICATED_FULLY')?:new Requestmap(url: '/prix/*', configAttribute: 'ROLE_USER,IS_AUTHENTICATED_FULLY').save(failOnError: true)

            def userRole=SecRole.findByAuthority("ROLE_USER")?:new SecRole(authority:"ROLE_USER").save()
            def adminRole=SecRole.findByAuthority("ROLE_ADMIN")?:new SecRole(authority:"ROLE_ADMIN").save()
            def superviseurRole=SecRole.findByAuthority("ROLE_SUPERVISEUR")?:new SecRole(authority:"ROLE_SUPERVISEUR").save()
            def enqueteurRole=SecRole.findByAuthority("ROLE_SUPERVISEUR")?:new SecRole(authority:"ROLE_SUPERVISEUR").save()
            def anonymeRole=SecRole.findByAuthority("ROLE_ANONYME")?:new SecRole(authority:"ROLE_ANONYME").save()

            def administrateurs=Utilisateur.findAllByRole("administrateur")

            administrateurs.each{user->
                SecUserSecRole.create(user,adminRole)
            }
            def enqueteurs=Utilisateur.findAllByRole("enqueteur")
            enqueteurs.each{user->
                SecUserSecRole.create(user,enqueteurRole)
            }
            def superviseurs=Utilisateur.findAllByRole("superviseur")
            superviseurs.each{user->
                SecUserSecRole.create(user,superviseurRole)
            }
            def utilisateurs_publics=Utilisateur.findAllByRole("public")
            utilisateurs_publics.each{user->
                SecUserSecRole.create(user,userRole)
            }
            def utilisateurs_anonyme=Utilisateur.findAllByRole("anonyme")
            utilisateurs_anonyme.each{user->
                SecUserSecRole.create(user,anonymeRole)
            }    // Long running task
//        }
//        p.onError { Throwable err ->
//            println "An error occured ${err.message}"
//        }
//        p.onComplete { result ->
//            println "Promise returned $result"
//        }
// block until result is called
       // def result = p.get()
// block for the specified time
//        def result = p.get(1,SECONDS)


    }
    def reinitialiser_mot_de_passe(Map params){

        def utilisateurs=Utilisateur.findAllByRole(params.role)

        utilisateurs.each{user->
            user.changePassword(params.pwd)
            user.save()
        }

    }
    def toLong(def value){
        def valueLong=[]
        value.each{
            valueLong<<it.minus("[")?.minus("]")?.toLong()
        }
        return valueLong
    }
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
}
