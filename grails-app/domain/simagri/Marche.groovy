package simagri

import org.hibernate.TransientObjectException


class Marche implements Serializable{
    String code
	String nom
    Region region
	Lieux location
    Boolean actif=true
    String description
    static searchable = {
        region parent: true, reference: true

        location parent: false, reference: true
        only =['code','nom','description']
    }
    static constraints = {

		code(blank:false,unique:true)
        nom(blank:false,maxSize:100,unique:true)
        actif(nullable:true,default:true)
        region(nullable:false)
        location(nullable:true)
        description(nullable:true,maxSize:255,widget:'textarea')

    }
	String toString(){
		"${nom}"
	}


    def getRegionName(){
        region?.nom?:"NA"
    }
    Set<Produit>  getProduits(){
        try {
            return MarcheProduit.findAllByMarche(this).collect{it.produit} as Set
        }
        catch( e){
            log.debug(e)
            return []
        }
    }

	static transients = ["mesProduits","contenu","regionName","titrecontenu"]
    Set<Reseau>  getReseaux(){
   try {
        return  ReseauMarche.findAllByMarche(this).collect{it.reseau} as Set
    }
    catch(TransientObjectException e){
        log.error(e)
        return []
    }
    }
    Set<Utilisateur>  getUtilisateurs(){
        try {
        return  UtilisateurMarche.findAllByMarche(this).collect{it.utilisateur}  as Set
        }
        catch(TransientObjectException e){
            log.error(e)
            return []
        }
    }
    Set<Utilisateur>  getMembresAutorises(){
        try {
            return  UtilisateurMarcheEcriture.findAllByMarche(this).collect{it.utilisateur} as Set
        }
        catch( e){
            log.error(e)
            return []
        }

    }
    boolean hasReseau(Reseau reseau){
        return ReseauMarche.findAllByMarcheAndReseau(this,reseau).count>0
    }
    def getMesProduits (){
				produits?.flatten()

    }
    def getTitrecontenu(){
        return "<strong>(Nom Produit | Prix (Gros/Détail))</strong>"
    }
    def getContenu(){
        def sortIndex = 'date'
        def sortOrder  ='desc'
        def maxRows=1

        StringBuffer buffer=new StringBuffer()
        def lesProduits= produits
        buffer.append("<div class=\"infoWindow\">")

                    if (lesProduits.size()>0) {
                    lesProduits.eachWithIndex{ produit,index->
                        def nomProduit=produit.nom
                        def prixGros = Prix.createCriteria().list() {
                            eq ('produit',produit)
                            and {
                                eq('marche',this)
                            }
                            and {
                                eq('categorieTarifaire',"Gros")
                            }
                            projections {
                                property('montant','montant')
                            }
                            maxResults(maxRows)
                            order(sortIndex, sortOrder)
                        }
                        def prixDetail = Prix.createCriteria().list() {
                            eq ('produit',produit)
                            and {
                                eq('marche',this)
                            }
                            and {
                                eq('categorieTarifaire',"Detail")
                            }
                            projections {
                                property('montant','montant')
                            }


                            maxResults(maxRows)
                            order(sortIndex, sortOrder)
                        }
                        def prix="${prixGros[0]?prixGros[0]:"-"}/${prixDetail[0]?prixDetail[0]:"-"}"

                            buffer.append("<p>")
                            def rubrique="<strong>${nomProduit} :</strong>"
                            def temp="${rubrique} ${prix}"
                            buffer.append(temp.toString())
                            buffer.append("</p>")
                    }
                        buffer.append("</div>")
            return buffer.toString()
        }else {
            buffer.append("<p>")
            buffer.append("Il n y a pas de données dans le marché <strong> ${this.nom}</strong>")
            buffer.append("</p>")
            return  buffer
        }



    }
    static def stringify(List<Marche> listeMarche){
    def marchestr=[]
        listeMarche.flatten().eachWithIndex{mar,i->
                marchestr<< mar.code

            }
        return marchestr
    }
    static mapping = {
        sort "nom"
        cache true
    }
}
