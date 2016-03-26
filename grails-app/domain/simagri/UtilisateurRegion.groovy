package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurRegion implements Serializable{
    Region region
    Utilisateur utilisateur
    static constraints = {
    }
    static mapping = {
        table 'utilisateur_region'
        version false
        id composite: ['utilisateur','region']
    }
    static  UtilisateurRegion create(Utilisateur utilisateur,Region region, Boolean flush=false) {
        UtilisateurRegion utilisateurRegion=new  UtilisateurRegion(region:region,utilisateur:utilisateur)
        utilisateurRegion.save(flush:flush,insert:true)
        return utilisateurRegion
    }
    static  UtilisateurRegion edit(Utilisateur utilisateur,Region region, Boolean flush=false) {
        def utilisateurRegion=findByRegionAndUtilisateur(region,utilisateur)
        if (!utilisateurRegion) {
            utilisateurRegion=new  UtilisateurRegion(region:region,utilisateur:utilisateur)
            utilisateurRegion.save(flush:flush,insert:true)
        }
        return utilisateurRegion
    }
    static Boolean remove(Region region,Utilisateur utilisateur, Boolean flush=false) {
        UtilisateurRegion utilisateurRegion=findByRegionAndUtilisateur(region,utilisateur)
        return  utilisateurRegion ? utilisateurRegion.delete(flush:flush):false
    }
    static void removeAll(Region region) {
        executeUpdate("DELETE FROM UtilisateurRegion where region=:region",[region:region])
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurRegion where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static UtilisateurRegion get (Long UtilisateurId, Long regionId) {
        find 'from UtilisateurRegion where utilisateur.id=:UtilisateurId and region.id=:regionId',[UtilisateurId:UtilisateurId,regionId:regionId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurRegion)){
            return false
        }
        other.region?.id==region?.id &&
                other.utilisateur?.id==utilisateur?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (region)
            builder.append(region.id)
        if (utilisateur)
            builder.append(utilisateur.id)
       return builder.toHashCode()

    }
}
