package simagri

class Contact implements Serializable{
    String nom
    String email
    String telephone
    String sujet
    String message
    Boolean publier=false
    static constraints = {
        nom  blank:false, maxSize:50
        email email:true,nullable:false,blank:false,maxSize:130
        telephone nullable:true
        sujet nullable:true,blank:true
        message nullable:false,blank:false
        publier nullable:true
    }
    String toString(){
       return nom
    }
}
