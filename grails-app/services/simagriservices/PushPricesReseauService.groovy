package simagriservices

import org.joda.time.DateTime
import simagri.AlerteReseau
import simagri.Utilisateur

class PushPricesReseauService {
    def smsDecoderService

    def fireByAlerte(AlerteReseau alerte) {
        if (!alerte.suspendre) {
        def dateDuJour=new DateTime()
            def listeMembres=alerte?.destinataires
            listeMembres.eachWithIndex{membre, j ->

                def dayOfWeek=dateDuJour.dayOfWeek
                def daysofWeek=[]
                if (alerte.dimanche)
                    daysofWeek<<7
                if (alerte.lundi)
                    daysofWeek<<1
                if (alerte.mardi)
                    daysofWeek<<2
                if (alerte.mercredi)
                    daysofWeek<<3
                if (alerte.jeudi)
                    daysofWeek<<4
                if (alerte.vendredi)
                    daysofWeek<<5
                if (alerte.samedi)
                    daysofWeek<<6
                if (dayOfWeek in daysofWeek)  {
                    try {
                        def listMarches=alerte?.markets?.flatten()

                        def listProduits=alerte?.produits?.flatten()
                        def produitstr=""
                        def marchestr=""
                        listProduits.eachWithIndex{prod,k->
                            if (k>0)
                                produitstr+=","
                            produitstr+= prod.code

                        }
                        listMarches.eachWithIndex{mar,k->
                            if (k>0)
                                marchestr+=","
                            marchestr+= mar.code

                        }
                        def isAlerte="isAlerte"
                        def requette="getPrix #${produitstr}#${marchestr}#${isAlerte}"
                        smsDecoderService.decodeIt(requette,(membre as Utilisateur).mobilePhone.getRightPhone())
                    }
                    catch (Exception e) {
                        log.error  " exception ${e}"
                    }

                }

            }
        }
    }


    def fire() {
        Set<AlerteReseau>  listeAlertePrixAValider=AlerteReseau.createCriteria().list(){
                eq('valide',true)
                and {
                    eq('recevoirPrix',true)
                }
                and {
                    eq('suspendre',false)
                }
            } as Set<AlerteReseau>
         listeAlertePrixAValider.eachWithIndex{ alerte,i ->
         fireByAlerte(alerte)

        }
    }

}
