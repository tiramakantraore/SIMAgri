package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class AlerteReseauMarche implements Serializable{
    Marche marche
    AlerteReseau alerteReseau
    static constraints = {
    }
    static mapping = {
        table 'alerte_reseau_marche'
        version false
        id composite: ['alerteReseau','marche']
    }
    static  AlerteReseauMarche create(Marche marche,AlerteReseau alerteReseau, Boolean flush=false, Boolean insert=true) {
        AlerteReseauMarche alerteReseauMarche=new  AlerteReseauMarche(marche:marche,alerteReseau:alerteReseau)
        alerteReseauMarche.save(flush:flush,insert:insert)
        return alerteReseauMarche
    }

    static Boolean remove(Marche marche,AlerteReseau alerteReseau, Boolean flush=false) {
        AlerteReseauMarche alerteReseauMarche=AlerteReseauMarche.findByMarcheAndAlerteReseau(marche,alerteReseau)
        return  alerteReseauMarche ? alerteReseauMarche.delete(flush:flush):false
    }
    static void removeAll(Marche marche) {
        executeUpdate("DELETE FROM AlerteReseauMarche where marche=:marche",[marche:marche])
    }
    static void removeAll(AlerteReseau alerteReseau) {
        executeUpdate("DELETE FROM AlerteReseauMarche where alerteReseau=:alerteReseau",[alerteReseau:alerteReseau])
    }
    static AlerteReseauProduit get (Long marcheId, Long alerteReseauId) {
        find 'from AlerteReseauMarche where marche.id=:marcheId and alerteReseau.id=:alerteReseauId',[marcheId:marcheId,alerteReseauId:alerteReseauId]
    }
    boolean equals(other) {
        if (! (other instanceof AlerteReseauMarche)){
            return false
        }
        other.marche?.id==marche?.id &&
                other.alerteReseau?.id==alerteReseau?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (marche)
            builder.append(marche.id)
        if (alerteReseau)
            builder.append(alerteReseau.id)
       return builder.toHashCode()

    }
}
