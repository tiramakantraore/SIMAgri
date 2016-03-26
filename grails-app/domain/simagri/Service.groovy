package simagri

class Service implements Serializable{
    String nom
    String description
    Boolean actif=true
    static constraints = {
        nom(blank:false,nullable:false)
        actif(nullable:true,default:true)
        description(nullbale:true,blank:true,maxSize:255,widget: 'textarea')


    }
	String toString(){
		"${nom}"
	}
}
