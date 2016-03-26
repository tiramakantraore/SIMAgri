package simagri

class Partenaire implements Serializable {
    String nom
    String url
    String email
    String description
    byte[] logo
    static constraints = {
        nom unique:true,maxSize:25
        url url:true,nullable:false
        email email:true,nullable:false

        description(nullable:true,maxSize:255,widget:'textarea')
        logo(nullable:true, maxSize:1048576)
    }
    static mapping = {
        cache true
    }
}
