package simagri

class Activite implements Serializable{
    String code
	String libelle
	String commentaire

    static constraints = {
        code(blank:false,unique:true,maxSize:15)
        libelle(blank:false)
        commentaire(nullable:true,maxSize:255,widget:'textarea')
    }
	String toString()
	{
		"${libelle}"
	}
    static mapping = {
        sort "libelle"
    }
}
