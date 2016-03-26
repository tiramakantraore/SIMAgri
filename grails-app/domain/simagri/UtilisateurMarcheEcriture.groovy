package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurMarcheEcriture implements Serializable{
    Marche marche
    Utilisateur utilisateur
    static constraints = {
    }
    static mapping = {
        table 'utilisateur_marche_ecriture'
        version false
        id composite: ['utilisateur','marche']
    }
    static  UtilisateurMarcheEcriture create(Utilisateur utilisateur,Marche marche, Boolean flush=false) {
        UtilisateurMarcheEcriture utilisateurMarche=new  UtilisateurMarcheEcriture(marche:marche,utilisateur:utilisateur)
        utilisateurMarche.save(flush:flush,insert:true)
        return utilisateurMarche
    }
    static  UtilisateurMarcheEcriture edit(Utilisateur utilisateur,Marche marche, Boolean flush=false) {
        def utilisateurMarcheEcriture=findByMarcheAndUtilisateur(marche,utilisateur)
        if (!utilisateurMarcheEcriture) {
            utilisateurMarcheEcriture=new  UtilisateurMarcheEcriture(marche:marche,utilisateur:utilisateur)
            utilisateurMarcheEcriture.save(flush:flush,insert:true)
        }
        return utilisateurMarcheEcriture
    }
    static Boolean remove(Marche marche,Utilisateur utilisateur, Boolean flush=false) {
        UtilisateurMarcheEcriture utilisateurMarche=findByMarcheAndUtilisateur(marche,utilisateur)
        return  utilisateurMarche ? utilisateurMarche.delete(flush:flush):false
    }
    static void removeAll(Marche marche) {
        executeUpdate("DELETE FROM UtilisateurMarcheEcriture where marche=:marche",[marche:marche])
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurMarcheEcriture where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static UtilisateurMarcheEcriture get (Long UtilisateurId, Long marcheId) {
        find 'from UtilisateurMarcheEcriture where utilisateur.id=:UtilisateurId and marche.id=:marcheId',[UtilisateurId:UtilisateurId,marcheId:marcheId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurMarcheEcriture)){
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
