package simagri

class Magazin implements Serializable{
	String code 
	String nom
    String mobile
	Lieux localite
    Boolean actif=true
	String description
    String nomContact
    String numTelBureau
    String adressePhysique
    String email
//    static searchable = true
    static constraints = {
		code(blank:false,maxSize:15,unique:true)
        nom(blank:false,maxSize:150)
        localite(nullable:true)
        actif(nullable:true,default:true)
        nomContact(nullable:true)
        numTelBureau(nullable:true)
        adressePhysique(nullable:true)
        mobile(nullable:true)
        email(nullable:true,blank:true,maxSize:350)
		description(nullable:true,maxSize: 255, widget:'textarea')
    }
    Set<Produit>  getProduits(){
        try {
            return MagazinProduit.findAllByMagazin(this).collect{it.produit} as Set
        }
        catch( e){
            return []
        }
    }
	static hasMany=[stocks:StockMagazin]
    static transients = ["contenu","titrecontenu","nomLocalite"]
	String toString(){
		"${nom}"
	}
    def getTitrecontenu(){
        return "<strong>(Nom Produit | Stock)</strong>"
    }
    def getNomLocalite() {
        localite?.nom
    }
    static mapping = {
        sort "nom"
        cache true
    }
    def getContenu(){
        String texte =""
        def sortIndex = 'maxdate'
        def sortOrder  ='desc'
        def maxRows=20
        def fromDate=new Date()
        def toDate=new Date()
//        def magazinHolders = ViewStocksMagazin.createCriteria().list() {
//            ge("magazinid", this.id)
//            maxResults(maxRows)
//            order(sortIndex, sortOrder)
//        }

      def  magazinHolders = ViewStocksMagazin.createCriteria().list() {

            eq("magazinid", this.id)
            projections {
                groupProperty("produitid")
                groupProperty("mesureid")
                avg('stock','stock')
            }
        }
        def props = ['produit','mesure','stock']
        def stocksmagazin = magazinHolders?.collect{ row->

            def cols = [:]

            row.eachWithIndex{colVal, ind->
                cols[props[ind]] = colVal
            }
             cols
          }
        if (stocksmagazin!=[[:]])
        if (stocksmagazin.size()>0){
            StringBuffer buffer=new StringBuffer()
            buffer.append("<div class=\"infoWindow\">")
            stocksmagazin.each{magHolder->
                if (magHolder?.produit && magHolder?.mesure ){
                            buffer.append("<p>")
                            def rubrique="<strong>${Produit.get(magHolder?.produit)?.nom} :</strong>"
                            def temp="${rubrique} ${magHolder?.stock} ${Mesure.get(magHolder?.mesure)?.code}"
                            buffer.append(temp.toString())
                            buffer.append("</p>")
                }

            }
            buffer.append("</div>")
            return buffer.toString()


        }else
        {   StringBuffer buffer=new StringBuffer()
            buffer.append("<p>")
            buffer.append("Il n y a pas de données dans le magazin <strong>${this.nom}</strong>")
            buffer.append("</p>")
            return  buffer
        }

    }
}
