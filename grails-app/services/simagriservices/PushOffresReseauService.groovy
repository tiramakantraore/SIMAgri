package simagriservices

import simagri.AlerteReseau
import simagri.Utilisateur


class PushOffresReseauService {
    def smsDecoderService
    def fireByAlerte(def alerte) {

            def listeProduits=alerte.produits
            def listeMembres=alerte?.destinataires?.flatten()?:alerte.reseaux*.membres?.flatten()
            listeMembres.eachWithIndex{membre, j ->
                Utilisateur auteur=membre  as  Utilisateur
                listeProduits.eachWithIndex{prod,l->

                    if (prod.code.equals(prod.code) ){

                        def typeOffre=(alerte.typeOffre=="Achat")?"A":"V"
                        def isAlerte="isAlerte"
                        def requette ="getOffre #${typeOffre}#${prod.code.toUpperCase()}#${isAlerte}"
                        smsDecoderService.decodeIt(requette,auteur.mobilePhone)
                    }
                }

        }

    }
    def fire() {

            def listeAlerteOffresAValider=AlerteReseau.createCriteria().list(){
                eq('valide',true)
                and {
                    eq('recevoirOffres',true)
                }
                and {
                    eq('suspendre',false)
                }
                cache true
            }

            listeAlerteOffresAValider.eachWithIndex{alerte,i->
                fireByAlerte(alerte)

            }

    }

}
