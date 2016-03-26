package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurReseauInvite implements Serializable{
    Utilisateur utilisateur
    Reseau reseau
    Boolean accepte=false
    Date dateInvitation
    Date dateAcceptation
    static constraints = {
        dateAcceptation (nullable:true)
        dateInvitation (nullable:true)
    }
    static mapping = {
        table 'reseau_invitations'
        version false
        id composite: ['reseau','utilisateur']
    }
    static  UtilisateurReseauInvite create(Utilisateur utilisateur,Reseau reseau, Boolean flush=false, Boolean insert=true) {
        UtilisateurReseauInvite utilisateurReseau=new  UtilisateurReseauInvite(utilisateur:utilisateur,reseau:reseau)
        utilisateurReseau.save(flush:flush,insert:insert)
        return utilisateurReseau
    }
    static  UtilisateurReseauInvite edit(Utilisateur utilisateur,Reseau reseau, Boolean flush=false) {
        def utilisateurReseauInvite=findByReseauAndUtilisateur(reseau,utilisateur)
        if (utilisateurReseauInvite){
            utilisateurReseauInvite.reseau=reseau
            utilisateurReseauInvite.utilisateur=utilisateur
            utilisateurReseauInvite.save(flush:flush,update:true)
        }
        return utilisateurReseauInvite
    }
    static Boolean remove(Utilisateur utilisateur,Reseau reseau, Boolean flush=false) {
        UtilisateurReseauInvite utilisateurReseau=findByUtilisateurAndReseau(utilisateur,reseau)
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
    static UtilisateurReseauInvite get (Long utilisateurId, Long reseauId) {
        find 'from UtilisateurReseau where utilisateur.id=:utilisateurId and reseau.id=:reseauId',[utilisateurId:utilisateurId,reseauId:reseauId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurReseauInvite)){
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
