package simagri

class AlerteReseau implements Serializable {
    String nom
    Boolean recevoirOffres
    Boolean recevoirPrix
    Boolean recevoirParEmail
    Boolean recevoirParSMS
    Boolean valide=false
    Boolean suspendre=false
    Boolean isRejected=false
    Date validationDate
    Date dateDemarrage
    Date rejectedDate
    Date dateCreation =new Date()
    String  crontabExpression
    String typeOffre ='Vente'
    String typeAlerte='Prix'
    Date dateDernierEnvoi=new Date()
    Boolean lundi
    Boolean mardi
    Boolean mercredi
    Boolean jeudi
    Boolean vendredi
    Boolean samedi
    Boolean dimanche
    Utilisateur operateur
    static constraints = {
        nom nullable:true
        recevoirOffres (nullable:true,blank:true)
        recevoirPrix (nullable:true,blank:true)
        recevoirParEmail (nullable:true,blank:true)
        recevoirParSMS (nullable:true,blank:true)
        valide(nullable:true)
        isRejected(nullable:true)
        suspendre(nullable:true)
        validationDate(nullable:true)
        rejectedDate(nullable:true)
        crontabExpression(nullable:true,blank:true)
        operateur(nullable:true)
        dateDemarrage(nullable: true)
        dateDernierEnvoi(nullable: true)
        dateCreation(nullable: true)
        lundi(nullable:true)
        mardi(nullable:true)
        mercredi(nullable:true)
        jeudi(nullable:true)
        vendredi(nullable:true)
        samedi(nullable:true)
        dimanche(nullable:true)
        typeOffre(blank:false,inList:['Achat','Vente'],maxSize:15)
        typeAlerte(nullable: true,inList:['Prix','Offres'],maxSize:15)
    }
    String toString()
    {
        "${reseaux.nom?:"Non Renseigné"}"
    }
    def getNbmarkets() {
        markets?.size()
    }
    def getNbdestinataires() {
        destinataires?.size()
    }
    def getNbproduits() {
        produits?.size()
    }
    def getIsPriceAlerte (){
       return  recevoirPrix
    }
    Set<Utilisateur>  getDestinataires(){
        return  AlerteReseauDestinataire.findAllByAlerteReseau(this).collect{it.utilisateur} as Set
    }
    boolean hasDestinataire(Utilisateur destinataire){
        return AlerteReseauDestinataire.findAllByAlerteReseauAndUtilisateur(this,destinataire).count>0
    }

    Set<Reseau>  getReseaux(){
        return  AlerteReseauReseau.findAllByAlerteReseau(this).collect{it.reseau} as Set
    }
   Set<Produit>  getProduits(){
        return  AlerteReseauProduit.findAllByAlerteReseau(this).collect{it.produit} as Set
    }
    Set<Marche>  getMarkets(){
        return  AlerteReseauMarche.findAllByAlerteReseau(this).collect{it.marche} as Set
    }
    boolean hasReseau(Reseau reseau){
        return AlerteReseauReseau.findAllByAlerteReseauAndReseau(this,reseau).count>0
    }
    boolean hasProduit(Produit produit){
        return AlerteReseauProduit.findAllByAlerteReseauAndProduit(this,produit).count>0
    }
    boolean hasMarche(Marche marche){
        return AlerteReseauMarche.findAllByAlerteReseauAndMarche(this,marche).count>0
    }
    def getIsAlertePrix(){
        typeAlerte=="Prix"
    }
    def getCronExpression() {
        def dayOfWeek=""
//        - Day of Week, 1-7 or SUN-SAT, ?
        if (!lundi && !mardi && !mercredi && !jeudi && !vendredi && !samedi && !dimanche) {
            dayOfWeek="?"
        } else
        if (lundi && mardi && mercredi && jeudi && vendredi && samedi && dimanche) {
            dayOfWeek="?"
        } else {
            if (lundi)
                dayOfWeek="2"
            if (mardi)  {
                if (dayOfWeek!="")
                    dayOfWeek+=","
                dayOfWeek+="3"
            }
            if (mercredi)  {
                if (dayOfWeek!="")
                    dayOfWeek+=","
                dayOfWeek+="4"
            }
            if (jeudi)  {
                if (dayOfWeek!="")
                    dayOfWeek+=","
                dayOfWeek+="5"
            }
            if (vendredi)  {
                if (dayOfWeek!="")
                    dayOfWeek+=","
                dayOfWeek+="6"
            }
            if (samedi)  {
                if (dayOfWeek!="")
                    dayOfWeek+=","
                dayOfWeek+="7"
            }
            if (dimanche)  {
                if (dayOfWeek!="")
                    dayOfWeek+=","
                dayOfWeek+="1"
            }
        }
        return     "* * * * ${dayOfWeek} ?"
      //   "2 * * * * ?"

    }

    static mapping = {
//        destinataires lazy: false
        sort validationDate:"desc"
    }
//    static fetchMode = [reseaux: 'eager']
}
