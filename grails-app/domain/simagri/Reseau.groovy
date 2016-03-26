package simagri

class Reseau implements Serializable{
    String nom
	String commentaire
	String siteWeb
	String email
    Boolean active=true
    Boolean estReseau=false
    Reseau parent
    String nomParent
    PageAccueil pageAccueil
    static searchable = {
    //    parent parent: true, reference: true
        except=['parent']
    }
    static constraints = {
        parent(nullable:true)
        nomParent(nullable:true)
        nom(blank:false,unique:true)
		email(nullable:true,email:true,maxSize:255)
        siteWeb(nullable:true,url:true)
		commentaire(nullable:true,maxSize:255,widget:'textarea')
        active(nullable: true,defaut:true)
        pageAccueil nullable:true
        estReseau  (nullable: true)
    }
    boolean hasElement(def element){
        def maps = createCriteria().list() {
            eq('parent', element)
        }
        return  !maps.isEmpty()
    }
    static void updateParent(){
        findAll().each{
            if (it.nomParent==null){
                it.nomParent=it.parent?.nom
                it.save(flush:true)
            }
        }
    }

	String toString(){
		return "${nom}"
	}

    static hasMany = [fils:Reseau]
    static belongsTo = [parent:Reseau]
    static transients = ['tousLesFils','markets','mesProduits','mesMarches','nbProduits','nbMarches','nbMembres','nombreMembres','estParent','hasChildren','nbTotalMembres','noeuds','nomRacine','ancetreId']


    Set<Marche>  getMarkets(){
        try {
            ReseauMarche.findAllByReseau(this)?.collect{it.marche} as Set
        } catch ( e){
            log.debug(e)
            return []
        }

    }
    boolean hasMarche(Marche marche){
        return ReseauMarche.findAllByReseauAndMarche(this,marche)?.count>0
    }

    Set<Produit>  getProduits(){
        try {
            return  ReseauProduit.findAllByReseau(this).collect{it.produit} as Set
        }
        catch( e){
            log.error(e)
            return []
        }

    }

    Set<Utilisateur>  getMembres(){
        try {
            return  UtilisateurReseau.findAllByReseau(this).collect{it.utilisateur} as Set
        }
        catch( e){
            log.error(e)
            return []
        }

    }
    Integer  getMembresCount(){
     def compteur=   UtilisateurReseau.withCriteria{
            eq('reseau',this)
            projections{
                        count "utilisateur"

                    }
        }
        return  compteur[0]?:0
    }
    boolean hasUtilisateur(Utilisateur utilisateur){
        return UtilisateurReseau.findAllByUtilisateurAndReseau(utilisateur,this).count>0
    }

    boolean estReseauFeuille(){
        return fils?.isEmpty()
    }
    boolean estPetitFils(){
        return parent?.parent?:false
    }
    boolean estArrierePetitFils(){
        return this.parent?.parent?.parent?:false
    }

    boolean estReseauRacine(){
        return !parent
    }
    def getHasChildren(){
        return !fils?.isEmpty()
    }
    def getTousLesFils (){
        fils?fils + fils *. tousLesFils.flatten ():[]
    }

    def getNbProduits (){
        mesProduits.size()
    }
    def getNbMarches (){
        mesMarches.size()
    }
    def getNoeuds (){
        List<Reseau> reseauxParents=[]
        def ascendants = {reseau, callback ->
            if (reseau?.parent){
                if (reseau?.parent?.estReseau)
                {
                    reseauxParents<<reseau?.parent
                }
                else {
                    callback(reseau.parent,callback)
                }
            }

        }
        ascendants(this,ascendants)

        return reseauxParents
    }
    Long getAncetreId (){
        def  reseauRacineId=0
        def ascendants = {reseau, callback ->
            if (reseau?.parent){
                if (reseau?.parent?.estReseau)
                {
                    reseauRacineId=reseau?.parent?.id
                }
                else {
                    callback(reseau.parent,callback)
                }
            }

        }
        ascendants(this,ascendants)
        return reseauRacineId?:this.id

    }
    def getNomRacine() {
        if (noeuds?.size()>0) {
            return noeuds[0].nom
        } else
        {
            return "NA"
        }

    }


    def getNbMembres (){
        membres?.unique()?.size()?:0
    }
    def getTousMesMembres(){
        def membresDuReseau=[]
        def compterMembres = {reseau, callback ->
            def theChildNodes = Reseau.createCriteria().list(){
                eq("parent.id",reseau.id)
                and {
                    eq("active",true)
                }

            }
            membresDuReseau<<reseau?.membres

            if (theChildNodes)
                theChildNodes.each {callback(it, callback)}

        }
        compterMembres(this,compterMembres)

        return membresDuReseau
    }
    def getNbTotalMembres(){
        def theCounter=0
        def compterMembres = {reseau, callback ->
            def theChildNodes = Reseau.createCriteria().list(){
                eq("parent.id",reseau.id)
                and {
                     eq("active",true)
                    }

            }

            theCounter+=reseau?.membresCount


            if (theChildNodes)
                theChildNodes.each {callback(it, callback)}

        }
        compterMembres(this,compterMembres)

        return theCounter
    }

    def getEstParent(){
        return estReseauRacine()
    }
	def getMesProduits (){
			produits?.flatten()?:parent?.mesProduits?.flatten()?:markets?.mesProduits?.flatten()?:[]
		}
    def getMesMarches (){
        markets?.flatten()?:parent?.mesMarches?.flatten()?:[]
    }
    static mapping = {
        sort "nom"
      //  fils lazy: false

    }
}
