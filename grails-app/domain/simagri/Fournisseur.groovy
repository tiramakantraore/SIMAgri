package simagri

class Fournisseur implements Serializable{
    String nom
    String mobile
    String nomContact
    String numTelBureau
    String adressePhysique
    String email
    String siteWeb
    static constraints = {
        nom(nullable:false,blank:false,unique:true)
        mobile(nullable:false,blank:false)
        email(nullable:true,email:true)
        siteWeb(nullable:true,url:true)
        nomContact(nullable:false)
        numTelBureau(nullable:false)
        adressePhysique(nullable:false)
        services(nullable:true)
    }
    static hasMany=[services:Service]
}
