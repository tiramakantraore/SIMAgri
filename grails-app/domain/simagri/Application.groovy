package simagri

class Application implements Serializable {
    String nom
    String code
    Boolean isActivated=false
    static constraints = {
        code(nullable:false,blank: false,maxSize:15)
        nom(nullable:false,blank: false,maxSize:150)
        isActivated(nullable:false)
    }
    String toString()
    {
        "${nom}"
    }
    static hasMany = [roles:ApplicationRole]
    static mapping = {sort id:"desc"
        roles cascade:"all-delete-orphan"
    }
}
