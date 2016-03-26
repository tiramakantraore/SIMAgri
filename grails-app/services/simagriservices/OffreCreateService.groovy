package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import org.joda.time.DateTime
import org.springframework.dao.OptimisticLockingFailureException
import simagri.*

@Transactional
class OffreCreateService {
    def  myUtilityService
    String messageEchec
    public bindprices(params,Offre offreInstance) {

        if (params?.prixUnitaire == null)
            offreInstance.prixUnitaire = new BigDecimal("0.0")
        else
            offreInstance.prixUnitaire = new BigDecimal(params?.prixUnitaire ?: "0.0")


        if (params?.prixTotal == null)
            offreInstance.prixTotal = new BigDecimal("0.0")
        else
            offreInstance.prixTotal = new BigDecimal(params?.prixTotal ?: "0.0")

        if (params?.prixUnitaireGros == null)
            offreInstance.prixUnitaireGros = new BigDecimal("0.0")
        else
            offreInstance.prixUnitaireGros = new BigDecimal(params?.prixUnitaireGros ?: "0.0")
        if (params?.prixTotalGros == null)
            offreInstance.prixTotalGros = new BigDecimal("0.0")
        else
            offreInstance.prixTotalGros = new BigDecimal(params?.prixTotalGros ?: "0.0")
    }

    def creer(def params) {
        try {
            def user = myUtilityService.getCurrent()
            user?.attach()
            def offreInstance

            Map formData = JSON.parse(params.formData?.toString()) as Map
            //students in request params is parsed to json objects and stored in the List
                    offreInstance = new Offre(formData)
                    try {
                        def theDate=new DateTime()
                        def theDateOfParams=new Date(formData?.date?.toString())
                        if (theDateOfParams>theDate.toDate())
                            offreInstance.date=theDate.toDate()
                        if (offreInstance.date>offreInstance.dateExpiration)
                            offreInstance.dateExpiration=new Date(theDate.plusDays(15).toDate().toString())
                        offreInstance.isValidated=false
                        offreInstance.operateur=user
                        offreInstance.isRejected=false
                        offreInstance.statut="En_cours"
                        if (!offreInstance.contact)
                            offreInstance.contact=offreInstance.auteur?.mobilePhone?:""

                        bindprices(formData,offreInstance)
                        if (!offreInstance.auteur)
                            offreInstance.auteur= user
                        offreInstance.reseau=user.reseauPrincipal
                        if (offreInstance.isVente) {
                            if (!offreInstance.prixUnitaireGros)
                                offreInstance.errors.rejectValue('prixUnitaireGros', 'nullable')
                        }


                        offreInstance.save(flush:true,failOnError: true)

                    }
                    catch (OptimisticLockingFailureException e) {
                        Offre.merge(offreInstance)
                        println "error ${e}"
                        log.error " exception ${e}"
                        return false
                    }
            return true
        } catch (Exception e) {
            println "error ${e}"
            log.error " exception ${e}"
            return false
        }
    }

}
