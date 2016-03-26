package simagri

class Civilite implements Serializable{
     String code
	 String libelle
    static constraints = {
        code(blank:true,maxSize:10)
        libelle(blank:false,unique:true,maxSize:70)
    }
	String toString(){
		"${libelle}"
	}
    static searchable = {
        only= ['code','libelle']
    }
    static mapping = {
        sort "libelle"
        cache true
    }

}
