package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class ReseauMarche implements Serializable{
    Marche marche
    Reseau reseau
    static constraints = {
    }
    static mapping = {
        table 'reseau_marche'
        version false
        id composite: ['reseau','marche']
    }
    static  ReseauMarche create(Marche marche,Reseau reseau, Boolean flush=false) {
        ReseauMarche reseauMarche=new  ReseauMarche(marche:marche,reseau:reseau)
        reseauMarche.save(flush:flush,insert:true)
        return reseauMarche
    }
    static Boolean remove(Marche marche,Reseau reseau, Boolean flush=false) {
        ReseauMarche reseauMarche=findByMarcheAndReseau(marche,reseau)
        return  reseauMarche ? reseauMarche.delete(flush:flush):false
    }
    static void removeAll(Marche marche) {
        executeUpdate("DELETE FROM ReseauMarche where marche=:marche",[marche:marche])
    }
    static void removeAll(Reseau reseau) {
        executeUpdate("DELETE FROM ReseauMarche where reseau=:reseau",[reseau:reseau])
    }
    static ReseauMarche get (Long marcheId, Long reseauId) {
        find 'from ReseauMarche where marche.id=:marcheId and reseau.id=:reseauId',[marcheId:marcheId,reseauId:reseauId]
    }
    boolean equals(other) {
        if (! (other instanceof ReseauMarche)){
            return false
        }
        other.marche?.id==marche?.id &&
                other.reseau?.id==reseau?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (marche)
            builder.append(marche.id)
        if (reseau)
            builder.append(reseau.id)
       return builder.toHashCode()

    }
}
