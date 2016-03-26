package simagri

class Produit implements Serializable{
    CategorieProduit categorie
	String code
    String nom
	String nomScientifique
	String variete
    String nomCategorie
	String commentaire
    Boolean actif=true
    Mesure mesure
    static searchable = {
        mesure reference:true
        except = ['categorie', 'actif','nomCategorie','variete']
    }
    static constraints = {
        categorie(nullable:true)
		code(blank:false,maxSize:15,unique:true)
        nom(blank:false,maxSize:150)
        nomScientifique(nullable:true,maxSize:150)
        variete(nullable:true)
        mesure(nullable:true)
        actif(nullable:true,default:true)
        nomCategorie(nullable:true)
        commentaire(nullable:true,maxSize:255,widget:'textarea')
	  }
    static transients = ["groupBy"]
	String toString(){
		"${nom}"
	}
    def getGroupBy(){
        categorie?.nom
    }
    static mapping = {
        sort "nom"
        cache true
    }
    static void updateCategorie(){
        Produit.findAll().each{
            if (it.nomCategorie==null){
            it.nomCategorie=it.categorie?.nom
            it.save(flush:true)
            }
        }
    }
    static belongsTo= [categorie:CategorieProduit]
    static fetchMode = [categorie: 'eager']
}
