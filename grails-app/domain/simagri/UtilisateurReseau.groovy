package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurReseau implements Serializable{
    Utilisateur utilisateur
    Reseau reseau
    static constraints = {
    }
    static mapping = {
        table 'reseau_membres'
        version false
        cache true
        id composite: ['reseau','utilisateur']
    }
    static  UtilisateurReseau create(Utilisateur utilisateur,Reseau reseau, Boolean flush=false, Boolean insert=true) {
        UtilisateurReseau utilisateurReseau=new  UtilisateurReseau(utilisateur:utilisateur,reseau:reseau)
        utilisateurReseau.save(flush:flush,insert:insert)
        return utilisateurReseau
    }
    static  UtilisateurReseau edit(Utilisateur utilisateur,Reseau reseau, Boolean flush=false) {
        def utilisateurReseau=findByReseauAndUtilisateur(reseau,utilisateur)
        if (!utilisateurReseau) {
            utilisateurReseau=new  UtilisateurReseau(utilisateur:utilisateur,reseau:reseau)
            utilisateurReseau.save(flush:flush,insert:true)
        }
        return utilisateurReseau
    }
    static Boolean remove(Utilisateur utilisateur,Reseau reseau, Boolean flush=false) {
        UtilisateurReseau utilisateurReseau=findByUtilisateurAndReseau(utilisateur,reseau)
        return  utilisateurReseau ? utilisateurReseau.delete(flush:flush):false
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurReseau where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static void removeAll(Boolean reallyAll) {
        if (reallyAll)
        executeUpdate("DELETE FROM UtilisateurReseau")
    }
    static void removeAll(Reseau reseau) {
        executeUpdate("DELETE FROM UtilisateurReseau where reseau=:reseau",[reseau:reseau])
    }
    static UtilisateurReseau get (Long utilisateurId, Long reseauId) {
        find 'from UtilisateurReseau where utilisateur.id=:utilisateurId and reseau.id=:reseauId',[utilisateurId:utilisateurId,reseauId:reseauId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurReseau)){
            return false
        }
        other.utilisateur?.id==utilisateur?.id &&
                other.reseau?.id==reseau?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (utilisateur)
            builder.append(utilisateur.id)
        if (reseau)
            builder.append(reseau.id)
       return builder.toHashCode()

    }
}
