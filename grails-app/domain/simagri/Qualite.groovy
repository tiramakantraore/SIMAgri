package simagri

class Qualite implements Serializable{
	String code
    String nom
    String description
    static searchable = {
        only= ['code','nom','description']
    }
    static constraints = {
		code(blank:false,maxSize:15,unique:true)
        nom(blank:false)
        description(blank:true,nullable:true,maxSize:255)
    }
	String toString(){
		"${nom}"
	}
    static mapping = {
        sort "nom"
        cache true
    }
}
