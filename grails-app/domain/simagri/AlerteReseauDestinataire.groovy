package simagri
import org.apache.commons.lang.builder.HashCodeBuilder
class AlerteReseauDestinataire implements Serializable{
    Utilisateur utilisateur
    AlerteReseau alerteReseau
    static constraints = {
    }
    static mapping = {
        table 'alerte_reseau_destinataires'
        version false
        id composite: ['alerteReseau','utilisateur']
    }
    static  AlerteReseauDestinataire create(Utilisateur utilisateur,AlerteReseau alerteReseau, Boolean flush=false, Boolean insert=true) {
        AlerteReseauDestinataire utilisateurReseau=new  AlerteReseauDestinataire(utilisateur:utilisateur,alerteReseau:alerteReseau)
        utilisateurReseau.save(flush:flush,insert:insert)
        return utilisateurReseau
    }

    static Boolean remove(Utilisateur utilisateur,AlerteReseau alerteReseau, Boolean flush=false) {
        AlerteReseauDestinataire alerteReseauDestinataire=AlerteReseauDestinataire.findByUtilisateurAndAlerteReseau(utilisateur,alerteReseau)
        return  alerteReseauDestinataire ? alerteReseauDestinataire.delete(flush:flush):false
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM AlerteReseauDestinataire where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static void removeAll(AlerteReseau alerteReseau) {
        executeUpdate("DELETE FROM AlerteReseauDestinataire where alerteReseau=:alerteReseau",[alerteReseau:alerteReseau])
    }
    static AlerteReseauDestinataire get (Long utilisateurId, Long alerteReseauId) {
        find 'from AlerteReseauDestinataire where utilisateur.id=:utilisateurId and alerteReseau.id=:alerteReseauId',[utilisateurId:utilisateurId,alerteReseauId:alerteReseauId]
    }
    boolean equals(other) {
        if (! (other instanceof AlerteReseauDestinataire)){
            return false
        }
        other.utilisateur?.id==utilisateur?.id &&
                other.alerteReseau?.id==alerteReseau?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (utilisateur)
            builder.append(utilisateur.id)
        if (alerteReseau)
            builder.append(alerteReseau.id)
       return builder.toHashCode()

    }
}
