package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurMagazin implements Serializable{
    Magazin magazin
    Utilisateur utilisateur
    static constraints = {
    }
    static mapping = {
        table 'utilisateur_magazin'
        version false
        id composite: ['utilisateur','magazin']
    }
    static  UtilisateurMagazin create(Utilisateur utilisateur,Magazin magazin, Boolean flush=false) {
        UtilisateurMagazin utilisateurMagazin=new  UtilisateurMagazin(magazin:magazin,utilisateur:utilisateur)
        utilisateurMagazin.save(flush:flush,insert:true)
        return utilisateurMagazin
    }
    static  UtilisateurMagazin edit(Utilisateur utilisateur,Magazin magazin, Boolean flush=false) {
        def utilisateurMagazin=findByMagazinAndUtilisateur(magazin,utilisateur)
        if (!utilisateurMagazin) {
            utilisateurMagazin=new  UtilisateurMagazin(magazin:magazin,utilisateur:utilisateur)
            utilisateurMagazin.save(flush:flush,insert:true)
        }
        return utilisateurMagazin
    }
    static Boolean remove(Magazin magazin,Utilisateur utilisateur, Boolean flush=false) {
        UtilisateurMagazin utilisateurMagazin=findByMagazinAndUtilisateur(magazin,utilisateur)
        return  utilisateurMagazin ? UtilisateurMagazin.delete(flush:flush):false
    }
    static void removeAll(Magazin magazin) {
        executeUpdate("DELETE FROM UtilisateurMagazin where magazin=:magazin",[magazin:magazin])
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurMagazin where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static UtilisateurMagazin get (Long UtilisateurId, Long magazinId) {
        find 'from UtilisateurMagazin where utilisateur.id=:UtilisateurId and magazin.id=:magazinId',[UtilisateurId:UtilisateurId,magazinId:magazinId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurMagazin)){
            return false
        }
        other.magazin?.id==magazin?.id &&
                other.utilisateur?.id==utilisateur?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (magazin)
            builder.append(magazin.id)
        if (utilisateur)
            builder.append(utilisateur.id)
       return builder.toHashCode()

    }
}
