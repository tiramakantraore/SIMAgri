package simagri

class MonImage implements Serializable{
    String nom
    String description
    Reseau proprietaire
    String adresse
    byte[] photo
    static constraints = {
        nom blank:false, maxSize:25
        adresse nullable:true,url:true
        description blank:true, maxSize:1000
        proprietaire nullable:false
        photo(nullable:true, maxSize:1048576)
    }
    static mapping = {
        cache true
    }
    String toString(){
        nom
    }
    Boolean canRender(){
        return photo?.size()>0
    }
}
