package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurService implements Serializable{
    Service service
    Utilisateur utilisateur
    static constraints = {
    }
    static mapping = {
        table 'utilisateur_service'
        version false
        id composite: ['utilisateur','service']
    }
    static  UtilisateurService create(Utilisateur utilisateur,Service service, Boolean flush=false) {
        UtilisateurService utilisateurService=new  UtilisateurService(service:service,utilisateur:utilisateur)
        utilisateurService.save(flush:flush,insert:true)
        return utilisateurService
    }
    static  UtilisateurService edit(Utilisateur utilisateur,Service service, Boolean flush=false) {
        def utilisateurService=findByServiceAndUtilisateur(service,utilisateur)
        if (!utilisateurService) {
            utilisateurService=new  UtilisateurService(service:service,utilisateur:utilisateur)
            utilisateurService.save(flush:flush,insert:true)

        }
        return utilisateurService
    }
    static Boolean remove(Service service,Utilisateur utilisateur, Boolean flush=false) {
        UtilisateurService utilisateurService=findByServiceAndUtilisateur(service,utilisateur)
        return  utilisateurService ? utilisateurService.delete(flush:flush):false
    }
    static void removeAll(Service service) {
        executeUpdate("DELETE FROM UtilisateurService where service=:service",[service:service])
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurService where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static UtilisateurService get (Long UtilisateurId, Long serviceId) {
        find 'from UtilisateurService where utilisateur.id=:UtilisateurId and service.id=:serviceId',[UtilisateurId:UtilisateurId,serviceId:serviceId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurService)){
            return false
        }
        other.service?.id==service?.id &&
                other.utilisateur?.id==utilisateur?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (service)
            builder.append(service.id)
        if (utilisateur)
            builder.append(utilisateur.id)
       return builder.toHashCode()

    }
}
