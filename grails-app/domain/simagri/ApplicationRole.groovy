package simagri

class ApplicationRole  {
    Application application
    String role
    Boolean estAttribue
    static constraints = {
        application(nullable:true)
        role(maxSize:15,inList:["anonyme","decideur","enqueteur","public","superviseur", "admin","superAdmin"])
        estAttribue(nullable:false)
    }
    String toString(){
        return role
    }
    static belongsTo=[application:Application]
}
