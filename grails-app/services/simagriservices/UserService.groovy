package simagriservices

import grails.transaction.Transactional
import org.springframework.context.MessageSourceResolvable
import org.springframework.context.i18n.LocaleContextHolder
import simagri.CategorieProduit
import simagri.Magazin
import simagri.Marche
import simagri.Produit
import simagri.Region
import simagri.Reseau
import simagri.SecRole
import simagri.SecUserSecRole
import simagri.Service
import simagri.Utilisateur
import simagri.UtilisateurCategorieProduit
import simagri.UtilisateurMagazin
import simagri.UtilisateurMarche
import simagri.UtilisateurMarcheEcriture
import simagri.UtilisateurProduit
import simagri.UtilisateurRegion
import simagri.UtilisateurReseau
import simagri.UtilisateurService

//@Transactional
class UserService {
    def grailsApplication
    def messageSource
//    static transactional = true
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def toLong(def value){
        def valueLong=[]
        value.each{

            valueLong<<it.toLong()
        }
        return valueLong
    }
    @Transactional
    def save(params, Utilisateur user, Boolean insert=true,Boolean removeDependances=true,Boolean ecraserDoublons=false) {
//        UserSaver  usersaver=new UserSaver()
        try {
            def listeReseaux
            if (params?.ReseauxIds?.getClass()==Long) {
                listeReseaux = [Reseau.get(params?.ReseauxIds)]
            }else {
                if (params?.ReseauxIds?.getClass()==String) {
                 listeReseaux = [Reseau.get(params?.ReseauxIds?.toString()?.toLong())]
                }

            }


            if (listeReseaux==null) {
                def listeParam=params?.ReseauxIds?.tokenize(',')?.collect{it? it as Long:new Long(0)}
                def idReseaux=toList(listeParam?:[new Long("0")])
                listeReseaux=Reseau.createCriteria().list(){
                    inList("id",idReseaux)
                }
            }
            def idRegions=toLong(params?.regions?:"0")
            def listeRegions=Region.createCriteria().list(){
                inList("id",idRegions)
            }

            def idMarches=toLong(params?.markets?:"0")
            def listeMarches=Marche.createCriteria().list(){
                inList("id",idMarches)
            }
            def idMarchesEcriture=toLong(params?.marchesEcriture?:"0")
            def listeMarchesEcriture=Marche.createCriteria().list(){
                inList("id",idMarchesEcriture)
            }

            def idProduits=toLong(params?.produits?:"0")
            def listeProduits=Produit.createCriteria().list(){
                inList("id",idProduits)
            }

            def idCategoriesProduits=toLong(params?.categories?:"0")
            def listeCategorieProduits=CategorieProduit.createCriteria().list(){
                inList("id",idCategoriesProduits)
            }
            def idMagazins=toLong(params?.magazins?:"0")
            def listeMagazins=Magazin.createCriteria().list(){
                inList("id",idMagazins)
            }
            def idServices=toLong(params?.services?:"0")
            def listeServices=Service.createCriteria().list(){
                inList("id",idServices)
            }

            if (!user)
                user=new Utilisateur(params as Map)
            user?.properties= params
            Reseau reseauPrincipal= listeReseaux?Reseau.get(listeReseaux.ancetreId[0]):Reseau.findByEstReseau(true)

            if (reseauPrincipal){
                user.reseauPrincipal = reseauPrincipal as Reseau
                    def saveSucced=user.save(flush:true,failOnError: true)
                    if (saveSucced) {
                        def userRole=SecRole.findByAuthority("ROLE_USER")?:new SecRole(authority:"ROLE_USER").save(flush:true)
                        def enqueteurRole=SecRole.findByAuthority("ROLE_ENQUETEUR")?:new SecRole(authority:"ROLE_ENQUETEUR").save(flush:true)
                        def adminRole=SecRole.findByAuthority("ROLE_ADMIN")?:new SecRole(authority:"ROLE_ADMIN").save(flush:true)
                        def superviseurRole=SecRole.findByAuthority("ROLE_SUPERVISEUR")?:new SecRole(authority:"ROLE_SUPERVISEUR").save(flush:true)
                        def anonymeRole=SecRole.findByAuthority("ROLE_ANONYME")?:new SecRole(authority:"ROLE_ANONYME").save(flush:true)

                        if (user.isOnlyAdmin) {
                            if (!SecUserSecRole.exists(user.id,adminRole.id))
                            SecUserSecRole.create(user,adminRole)
                        }else  if (user.isOnlyEnqueteur) {
                            if (!SecUserSecRole.exists(user.id,enqueteurRole.id))
                            SecUserSecRole.create(user,enqueteurRole)
                        }
                        else  if (user?.isOnlySuperviseur) {
                            if (!SecUserSecRole.exists(user.id,superviseurRole.id))
                            SecUserSecRole.create(user,superviseurRole)
                        }
                        else  if (user.isOnlyPubicUser) {
                            if (!SecUserSecRole.exists(user.id,userRole.id))
                            SecUserSecRole.create(user,userRole)
                        }
                        else  if (user.isOnlyAnonymeUser) {
                            if (!SecUserSecRole.exists(user.id,anonymeRole.id))
                            SecUserSecRole.create(user,anonymeRole)
                        }

                        if (removeDependances) {
                            UtilisateurReseau.removeAll(user)
                            UtilisateurProduit.removeAll(user)
                            UtilisateurMarche.removeAll(user)
                            UtilisateurMarcheEcriture.removeAll(user)
                            UtilisateurMagazin.removeAll(user)
                            UtilisateurService.removeAll(user)
                            UtilisateurCategorieProduit.removeAll(user)
                            UtilisateurRegion.removeAll(user)
                            listeMarches.each { marche ->
                                UtilisateurMarche.create(user, marche as Marche, true)
                            }
                            listeMarchesEcriture.each { marche ->
                                UtilisateurMarcheEcriture.create(user, marche as Marche, true)
                            }
                            listeRegions.each { region ->
                                UtilisateurRegion.create(user, region as Region, true)
                            }

                            listeReseaux.each { reseau ->
                                UtilisateurReseau.create(user, reseau as Reseau, true)
                            }
                            listeProduits.each { produit ->
                                UtilisateurProduit.create(user, produit as Produit, true)
                            }
                            listeCategorieProduits.each { categorieProduit ->
                                UtilisateurCategorieProduit.create(user, categorieProduit as CategorieProduit, true)
                            }
                            listeMagazins.each { magazin ->
                                UtilisateurMagazin.create(user, magazin as Magazin, true)
                            }
                            listeServices.each { service ->
                                UtilisateurService.create(user, service as Service, true)
                            }
                        }else {
                            if (ecraserDoublons)
                                UtilisateurReseau.removeAll(user)
                            listeMarches.each { marche ->
                                UtilisateurMarche.edit(user, marche as Marche, true)
                            }
                            listeMarchesEcriture.each { marche ->
                                UtilisateurMarcheEcriture.edit(user, marche as Marche, true)
                            }
                            listeRegions.each { region ->
                                UtilisateurRegion.edit(user, region as Region, true)
                            }

                            listeReseaux.each { reseau ->
                                if (ecraserDoublons)
                                    UtilisateurReseau.create(user, reseau as Reseau, true)
                                else
                                    UtilisateurReseau.edit(user, reseau as Reseau, true)
                            }
                            listeProduits.each { produit ->
                                UtilisateurProduit.edit(user, produit as Produit, true)
                            }
                            listeCategorieProduits.each { categorieProduit ->
                                UtilisateurCategorieProduit.edit(user, categorieProduit as CategorieProduit, true)
                            }
                            listeMagazins.each { magazin ->
                                UtilisateurMagazin.edit(user, magazin as Magazin, true)
                            }
                            listeServices.each { service ->
                                UtilisateurService.edit(user, service as Service, true)
                            }
                        }
                    }else {
                        println "saveSucced $saveSucced"
                    }
                }
                else {
                println "reseau principal $reseauPrincipal"
                return null
            }

          return user

          }
            catch(e) {
                log.debug( " exception in ${this.getClass().simpleName}.Save ${e}")
                return user
            }
    }
    def createAndSave (params, Utilisateur user=null,Boolean ecraserDoublons=true) {
        try {
            if (!user)
                user=new Utilisateur(params as Map)
            def userExist=Utilisateur.findByMobilePhone(params.mobilePhone?.toString()?.getRightPhone())
            if (!userExist) {
                if (!user)
                    user=new Utilisateur()
                user.username="${user.lastName} ${user.firstName}"
                user.login=user.username
                if (!user.password){
                    user.password=grailsApplication.config.grails.simAgriDefaultPassword
                    user.encodePassword()
                }
                user.avatar = params.avatar
                user.mobilePhone= user?.mobilePhone?.toString()?.getRightPhone()
                return  save (params,user,true,true,true)
            }else {
                params.messageRetour=messageSource.getMessage('userExist.message' as MessageSourceResolvable, [userExist.toString(),params.mobilePhone?.toString()?.getRightPhone()], LocaleContextHolder.locale)
                return user
            }


        }
        catch(e) {
            log.debug( " exception in ${this.getClass().simpleName}.Save ${e}")
            return user

        }

    }
    @Transactional
    Boolean update(params, Utilisateur user,Boolean removeDependances=true) {
        try {
//            if (!user)
//                user=new Utilisateur(params as Map)
            user.properties = params as Map
            user.mobilePhone=user?.mobilePhone?.toString()?.getRightPhone()
            user.username="${user.lastName} ${user.firstName}"
            if (!user.login && user.username)
                user.login=user.username
            return save(params,user,false,false)
        }
        catch(e) {

            log.debug( " exception in ${this.getClass().simpleName}.Save ${e}")
            return false
        }
    }
    def bulkUpdate(params, String thePhone,Boolean ecraserDoublons=true) {
        try {

            def user=Utilisateur.findByMobilePhone(thePhone)
            if (user) {
                        user.properties = params
                        user.lastName=params.lastName
                        if (!user.password){
                            user.password=grailsApplication.config.grails.simAgriDefaultPassword
                            user.encodePassword()
                        }
                return save(params,user,false,false,ecraserDoublons)
            }

        }
        catch(e) {

            log.debug( " exception in ${this.getClass().simpleName}.update ${e}")
            return false
        }
    }

}
