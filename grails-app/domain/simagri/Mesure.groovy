package simagri

class Mesure implements Serializable {
	String code
	String name
	Boolean isLocal
    Boolean isConvertible=true
	BigDecimal valeur=1.0
    String description
	Date dateCreated
	Date lastUpdated
    static searchable = {
        only= ['code','name','description']
    }
    static constraints = {
		code(blank:false,maxSize:15,unique:true)
		name(blank:false,maxSize:150)
		isLocal(blank:false)
		valeur(nullable:true, bindable: false)
        isConvertible nullable:true
        description(nullable:true,maxSize: 255,widget:'textarea')
    }
	String toString(){
		"${name}"
	}
    static mapping = {
        sort "name"
        cache true
    }
   
}
