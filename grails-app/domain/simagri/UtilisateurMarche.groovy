package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurMarche implements Serializable{
    Marche marche
    Utilisateur utilisateur
    static constraints = {
    }
    static mapping = {
        table 'utilisateur_marche'
        version false
        id composite: ['utilisateur','marche']
    }
    static  UtilisateurMarche create(Utilisateur utilisateur,Marche marche, Boolean flush=false) {
        UtilisateurMarche utilisateurMarche=new  UtilisateurMarche(marche:marche,utilisateur:utilisateur)
        utilisateurMarche.save(flush:flush,insert:true)
        return utilisateurMarche
    }
    static  UtilisateurMarche edit(Utilisateur utilisateur,Marche marche, Boolean flush=false) {
        def utilisateurMarche=findByMarcheAndUtilisateur(marche,utilisateur)
        if (!utilisateurMarche){
            utilisateurMarche=new  UtilisateurMarche(marche:marche,utilisateur:utilisateur)
            utilisateurMarche.save(flush:flush,insert:true)
        }
        return utilisateurMarche
    }
    static Boolean remove(Marche marche,Utilisateur utilisateur, Boolean flush=false) {
        UtilisateurMarche utilisateurMarche=findByMarcheAndUtilisateur(marche,utilisateur)
        return  utilisateurMarche ? utilisateurMarche.delete(flush:flush):false
    }
    static void removeAll(Marche marche) {
        executeUpdate("DELETE FROM UtilisateurMarche where marche=:marche",[marche:marche])
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurMarche where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static UtilisateurMarche get (Long UtilisateurId, Long marcheId) {
        find 'from UtilisateurMarche where utilisateur.id=:UtilisateurId and marche.id=:marcheId',[UtilisateurId:UtilisateurId,marcheId:marcheId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurMarche)){
            return false
        }
        other.marche?.id==marche?.id &&
                other.utilisateur?.id==utilisateur?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (marche)
            builder.append(marche.id)
        if (utilisateur)
            builder.append(utilisateur.id)
       return builder.toHashCode()

    }
}
