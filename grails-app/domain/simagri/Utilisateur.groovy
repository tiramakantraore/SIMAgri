package simagri

import static org.h2.util.StringUtils.cache

class Utilisateur extends SecUser  {
    transient def grailsApplication
    String username
    String firstName
    String lastName
    String email
    String mobilePhone
    String gender
    String login
	Boolean estHomme
    Date dateOfBirth
    Entreprise entreprise
    String homePage
    Civilite civilite
    Lieux geoLocation
    Activite activite
    String comment
    String poste
    String town
    String country
    Currency devise
    String secondPhone
    String physicalAddress
    String postCode
    String role='public'
    String avatar
    String nomComplet
    Operateur operateur
    byte[] photo
    Performance performance
    Marche marcheEnqueteur
    Reseau reseauPrincipal
    static hasMany = [posts: Post]
    static searchable = {
//        mobilePhone component: true
//        email component: true
        only = ['firstName', 'lastName','email','mobilePhone','gender']
    }
    static constraints = {
        username (blank: true, nullable:true, maxSize:150)
        firstName(nullable:true, maxSize:50)
        lastName(nullable:true, maxSize:150)
        login(nullable:true, size:5..64 )
        password nullable: true
        email(email:true, nullable: true, blank: true, maxSize:200)
        mobilePhone (nullable:true,blank: false, unique: true, maxSize:30,phoneNumber: true)
        gender(blank:true,inList:['male','female'], maxSize:10)
        civilite(nullable:true)
        devise(nullable:true,blank: true)
        dateOfBirth(nullable:true)
        entreprise(nullable:true)
        activite(nullable:true)
        homePage(url:true,nullable:true)
        geoLocation(nullable:true)
        poste(nullable:true)
        country(nullable:true, maxSize:30)
        town(nullable:true, maxSize:50)
        secondPhone (nullable:true, maxSize:30)
        physicalAddress (nullable:true,widget: 'textarea')
        postCode (nullable:true)
        comment(nullable:true,maxSize:255,widget:'textarea')
        marcheEnqueteur nullable:true
        operateur(nullable: true)
        performance nullable:true
        reseauPrincipal nullable:true
        avatar nullable:true
        photo nullable:true, maxSize:1048576

        role(maxSize:15,inList:["anonyme","enqueteur","fournisseur","public","superviseur", "admin","superAdmin"])

    }


    def getIsAdmin(){
          return ((role=='grandAdmin') ||(role=='admin') || (isSuperAdmin) || isSuperviseur)
    }

    def getIsOnlyAdmin(){
        return (role=='admin')
    }

    def getIsSuperAdmin(){
        return (role=='superAdmin')
    }
    def isAuthorized(String appName){
        if (isAdmin){
            return true
        } else  {
            def appli=Application.findByCode(appName)
            appli?((ApplicationRole.findByApplicationAndRole(appli,role)?.estAttribue || isAdmin) && (appli?.isActivated)):false
        }
    }

    Set<Reseau>  getReseaux(){
        try {
            return  UtilisateurReseau.findAllByUtilisateur(this).collect{it.reseau} as Set
        } catch ( e){
            log.debug(e)
            return []
        }


    }
    Set<AlerteReseau>  getAlertes(){
        try {
            return  AlerteReseauDestinataire.findAllByUtilisateur(this).collect{it.alerteReseau} as Set
        } catch ( e){
            log.debug(e)
            return []
        }

    }
    Set<Service>  getServices(){
        try {
            return  UtilisateurService.findAllByUtilisateur(this).collect{it.service} as Set
        } catch ( e){
            log.debug(e)
            return []
        }

    }
    Set<Marche>  getMarkets(){
        try {
            UtilisateurMarche.findAllByUtilisateur(this)?.collect{it.marche} as Set
        } catch ( e){
            log.debug(e)
            return []
        }

    }
    Set<Marche>  getMarchesEcriture(){
        try {
            UtilisateurMarcheEcriture.findAllByUtilisateur(this)?.collect{it.marche} as Set
        } catch ( e){
            log.debug(e)
            return []
        }

    }
    Set<Magazin>  getMagazins(){
        try {
            UtilisateurMagazin.findAllByUtilisateur(this)?.collect{it.magazin} as Set
        } catch ( e){
            log.debug(e)
            return []
        }

    }
    Set<Produit>  getProduits(){
        try {
            UtilisateurProduit.findAllByUtilisateur(this)?.collect{it.produit} as Set
        } catch ( e){
            log.debug(e)
            return []
        }

    }

    boolean hasMagazin(Magazin magazin){
        def utilisateursrProduits = UtilisateurMagazin.createCriteria().list() {
            eq('utilisateur',this)
            and
                    {eq('produit',magazin)}
        }
        return  !utilisateursrProduits.isEmpty()
    }
    boolean hasProduit(Produit produit){
        def utilisateursProduits = UtilisateurProduit.createCriteria().list() {
            eq('utilisateur',this)
            and
                    {eq('produit',produit)}
        }
        return  !utilisateursProduits.isEmpty()
    }
    boolean hasReseau(Reseau reseau){
        def utilisateursReseaux = UtilisateurReseau.createCriteria().list() {
            eq('utilisateur',this)
            and
                    {eq('reseau',reseau)}
        }
        return  !utilisateursReseaux.isEmpty()
    }
    boolean hasMarche(Marche marche){
        def utilisateursMarches = UtilisateurMarche.createCriteria().list() {
            eq('utilisateur',this)
            and
             {eq('marche',marche)}
        }
        return  !utilisateursMarches.isEmpty()
    }
     Set<Reseau> getNoeudsRacine () {
       return reseaux*.collect{it.getNoeuds()} as Set<Reseau>
    }
    def getIsOnlySuperviseur(){
        return (role=='superviseur')
    }
    def getIsSuperviseur(){
        return (role=='superviseur')|| (isSuperAdmin)
    }
    def getIsGrandAdmin(){
        return (role=='grandAdmin')
    }
    def getIsEnqueteur(){
        return (role=='enqueteur')
    }
    def getIsOnlyEnqueteur(){
        return (role=='enqueteur')
    }
    def getIsOnlyPubicUser(){
        return (role=='public')
    }
    def getIsPubicUser(){
        return (role=='public')||(role=='fournisseur')
    }
    def getIsOnlyAnonymeUser(){
        return (role=='anonyme')
    }
    def getIsAnonymeUser(){
        return (role=='anonyme')
    }
    def getPrefix() {
        return mobilePhone.toString()?.getPhonePrefix()

    }
    def getCanPostData(){
       return (isAdmin || isEnqueteur ||isSuperviseur||isSuperAdmin)
    }


    static def normaliserMobilePhones() {
        Utilisateur.list().each{user->
            user.mobilePhone=user.mobilePhone.getRightPhone()

                  Operateur.list().each{oper->
                      if (oper.isPrefixInList(user.getPrefix())) {
                          user.operateur=oper
                      }
                  }
            Reseau reseauPrincipal= Reseau.get(user.reseaux*.ancetreId[0])
            if (reseauPrincipal){
                user.reseauPrincipal = reseauPrincipal
            }

            user.save(flush:true)
        }
    }

    static def unformatMobilePhone() {
        Utilisateur.list().each{user->
            def aAmobilePhone=user.mobilePhone

            user.mobilePhone=aAmobilePhone.replaceAll(' ','')

            user.save(flush:true)
        }
    }

    static def normaliser_reseau_principal() {
        def maxRows=400
        def users= withCriteria {

            isNull("reseauPrincipal")
            maxResults(maxRows)

        }
        def compteur=0
        users.each{user->

            Reseau reseauPrincipal= Reseau.get(user.reseaux*.ancetreId[0])
            if (reseauPrincipal)         {
                user.reseauPrincipal = reseauPrincipal
            }

            user.save(flush:true)
            compteur+=1
        }
    }

    static boolean exists(String phoneNumber) {
        return findByMobilePhone(phoneNumber)?:false

    }

    String toString(){
        return "${nomComplet?:""}"
    }

    String toFullString(){
        return "${nomComplet?:""}-${mobilePhone?:""}"
    }


	static transients = ['mesProduits','mesMarches','mesMagazins',"groupBy","estHomme",'nbProduits','nbMarches','grandAdmin','groupEnqueteurBy']
    static mapping = {
        login index:'Login_Idx'
        mobilePhone index:'MobilePhone_Idx'
        nomComplet formula: "CONCAT(UPPER(LAST_NAME),' ',CONCAT(UPPER(LEFT(FIRST_NAME, 1)), SUBSTRING(FIRST_NAME, 2)))"
        sort "lastName"
    }
    def getGroupBy(){
         entreprise?.nom?:"Destinataires"
    }
    def getGroupEnqueteurBy(){
      " Marche : ${marcheEnqueteur?.nom?:"Enqueteurs"} "
    }
		def getMesProduits (){
			produits?.flatten()
		}

        def getMesMarches (){
            markets?.flatten()
        }
        def getMesMagazins(){
            magazins?.flatten()?.unique()?:[]
        }
    def getEstHomme() {
        gender=="male"
    }
    def getNbProduits (){
        mesProduits.size()
    }
    def getNbMarches (){
        mesMarches.size()
    }
    def affecterMarcheEnqueteur(Marche marche){
            marcheEnqueteur=marche
            UtilisateurMarche.removeAll(this)
            UtilisateurMarche.create(this,marche,false)
        }
    def retirerMarcheEnqueteur(){
        marcheEnqueteur=null
        UtilisateurMarche.removeAll(this)
    }


}
