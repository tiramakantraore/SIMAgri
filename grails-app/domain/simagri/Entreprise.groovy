package simagri

class Entreprise implements Serializable{
    String nom
    String site_web
    String email
    static constraints = {
        nom(nullable:false,unique:true)
        site_web(nullable:true)
        email(nullable:true,email:true)
    }
    String toString(){
        "${nom}"
    }
    static mapping = {
        sort "nom"
        cache true
    }
}
