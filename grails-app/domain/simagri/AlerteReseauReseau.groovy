package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class AlerteReseauReseau implements Serializable{
    Reseau reseau
    AlerteReseau alerteReseau
    static constraints = {
    }
    static mapping = {
        table 'alerte_reseau_reseau'
        version false
        id composite: ['alerteReseau','reseau']
    }
    static  AlerteReseauReseau create(Reseau reseau,AlerteReseau alerteReseau, Boolean flush=false, Boolean insert=true) {
        AlerteReseauReseau alerteReseauReseau=new  AlerteReseauReseau(reseau:reseau,alerteReseau:alerteReseau)
        alerteReseauReseau.save(flush:flush,insert:insert)
        return alerteReseauReseau
    }

    static Boolean remove(Reseau reseau,AlerteReseau alerteReseau, Boolean flush=false) {
        AlerteReseauReseau alerteReseauReseau=findByReseauAndAlerteReseau(reseau,alerteReseau)
        return  alerteReseauReseau ? alerteReseauReseau.delete(flush:flush):false
    }
    static void removeAll(Reseau reseau) {
        executeUpdate("DELETE FROM AlerteReseauReseau where reseau=:reseau",[reseau:reseau])
    }
    static void removeAll(AlerteReseau alerteReseau) {
        executeUpdate("DELETE FROM AlerteReseauReseau where alerteReseau=:alerteReseau",[alerteReseau:alerteReseau])
    }
    static AlerteReseauReseau get (Long reseauId, Long alerteReseauId) {
        find 'from AlerteReseauReseau where reseau.id=:reseauId and alerteReseau.id=:alerteReseauId',[reseauId:reseauId,alerteReseauId:alerteReseauId]
    }
    boolean equals(other) {
        if (! (other instanceof AlerteReseauReseau)){
            return false
        }
        other.reseau?.id==reseau?.id &&
                other.alerteReseau?.id==alerteReseau?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (reseau)
            builder.append(reseau.id)
        if (alerteReseau)
            builder.append(alerteReseau.id)
       return builder.toHashCode()

    }
}
