package simagri

class CategorieProduit implements Serializable {
    LogoProduit logo
    String nom
    Boolean actif=true
    Mesure mesure
	String comment
    static constraints = {
        nom(blank:false,maxSize:100,unique:true)
        mesure(nullable:true)
        actif(nullable:true,default:true)
		comment(nullable:true,maxSize:255)
        logo(nullable:true)


    }
//    static searchable = {
//        only=['nom','comment']
//    }
    static transients = ["groupBy"]
    def getGroupBy(){
        return "Categories"
    }
	String toString()
	{
		"${nom}"
	}
    static hasMany=[produits:Produit,mesures:Mesure]
    static mapping = {
        sort "nom"
        produits cascade:"all-delete-orphan"
        mesures cascade:"all-delete-orphan"
        cache true
    }
}
