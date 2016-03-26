package simagri

class Offre implements Serializable{
    CategorieProduit categorieProduit
	String statut ="En_cours"
	String typeOffre ="Vente"
	String modePaiement="Especes"
	Date date
	Integer delaiEnJours
    Date dateExpiration
    Date dateValidation
    Integer quantite
    Integer delaiLivraison
	Qualite qualite
	Mesure mesure
	String conditions
	String commentaire
    Lieux origine
    Lieux lieuStockage
    Lieux lieuLivraison
	Produit produit
    Boolean isValidated =false
    Boolean isRejected =false
    Boolean IsForSell=false
	BigDecimal prixUnitaire
    BigDecimal prixTotal
    BigDecimal prixUnitaireGros
    BigDecimal prixTotalGros
    BigDecimal coutTransport
    Utilisateur operateur
    Boolean isFromSMS
    Utilisateur auteur
    Reseau reseau
    String contact
    String sourceTag //add field 13-12-2016
    static searchable = {
        auteur parent: true, reference: true
        mesure parent:false, reference:true
        sourceTag nullable: true
//        reseau parent: true, reference: true

//        categorieProduit parent: false, reference: true
//        qualite parent: false, reference: true
        produit component:true
        except=['categorieProduit','operateur','reseau','origine','lieuStockage','lieuLivraison','qualite']
    }
    static transients = ['prixUnitGrosDetail','montantGlobalGrosDetail','statutOffre','intitule','nbJoursExpiration','titre','sourceOffre','quantiteU']
    static constraints = {
        categorieProduit(nullable:true)
		produit(nullable:false)
		statut(blank:false, inList:['En_cours','Approuve','Rejete'])
        typeOffre(blank:false,inList:['Achat','Vente'],maxSize:15)
        modePaiement(nullable:true,blank:true,inList:['Especes','Cheque_marchand','transfert_bancaire','Check_ou_carte_credit','NA'],maxSize:25)
		date(blank:false,widget:'datePicker')
        delaiEnJours(nullable:true,blank:true)
        isFromSMS(nullable:true)
        dateExpiration(blank:true,nullable:true,widget:'datePicker' )
        quantite(blank:false,validator :{qual,obj-> return qual>0})
        mesure(nullable:true)
        prixUnitaire(nullable:true, bindable: false)
        prixTotal(nullable:true, bindable: false)
        prixUnitaireGros(nullable:true, bindable: false)
        prixTotalGros(nullable:true, bindable: false)
        qualite(nullable:true)
        origine(nullable:true)
        lieuStockage(nullable:true)
        lieuLivraison(nullable:true)
        conditions(nullable:true,blank:true,maxSize:255,widget:'textarea')
        commentaire(nullable:true,blank:true,maxSize:255,widget:'textarea')
        isValidated(nullable:true,default:false)
        isRejected(nullable:true,default:false)
        delaiLivraison(nullable:true)
        dateValidation(nullable:true)
        coutTransport(nullable:true)
        contact(nullable:true)
        operateur(nullable:true)
        reseau(nullable:true)
        sourceTag nullable: true
    }
	String toString(){

        def texteOperation=(typeOffre=="Achat")?"veut acheter":"veut vendre"
        def devise="FCFA"
        def montantTotal="Total="
        def textejrsExpiration=nbJoursExpiration?"elle expire dans ${nbJoursExpiration} jours":""
        def texteValidation=dateValidation?" l'offre a été validée le ${dateValidation.toGoodFormat()} ${textejrsExpiration} ":""
		def texteExpiration="${texteValidation}"
        def conditionsParticulieres=" ${conditions?conditions:""} "
        def texteVente=(typeOffre=="Achat")?"":" à  ${prixUnitGrosDetail} ${devise} /${mesure?.code}, ${montantTotal} ${montantGlobalGrosDetail} ${devise}"
        def Lacivilite=auteur?.civilite?.code?:auteur?.civilite?.libelle?:""
        def  contenu_action="""${Lacivilite} ${auteur?.toString()}
                             ${texteOperation} ${quantite} ${mesure.code} de ${produit?.nom} (${produit?.code})
                             ${texteVente}${texteExpiration}${conditionsParticulieres}
                             contact : ${contact}
                            """
        return contenu_action
	}
    def getIsVente(){
       return typeOffre=="Vente"
    }
    String getTitre(){

        def texteOperation=(typeOffre=="Achat")?"veut acheter ${quantite} ${mesure.code} de ${produit?.nom}..":"veut vendre ${quantite} ${mesure.code} de ${produit?.nom}..."
        def Lacivilite=auteur?.civilite?.code?:auteur?.civilite?.libelle?:""
        def  contenu_action="""${Lacivilite} ${auteur?.toString()}
                             ${texteOperation}
                            """
        return contenu_action
    }
    def getSourceOffre(){
        if (isFromSMS){
            return "SMS"
        }else {
            return "WEB"
        }
    }
    def setTotalPrice (BigDecimal value){
        if (!value){
           totalPrice= (quantite?:0) * (prixUnitaire?:0)
        }
        else{
            totalPrice=value
            prixUnitaire= totalPrice/(prixUnitaire?:1)
        }
    }
    def getTotalPrice(){
        return totalPrice
    }
//    def setStatut(String value){
//        println "value ${value}"
//        statut=value
//    }
    def getIntitule(){
         "${produit}-${typeOffre}"
    }
    def getStatutOffre(){
        if (isRejected)
        {
            return "Rejété"
        }else {
            "${(dateExpiration>=new Date())?"En cours":"Expiré"}"
        }

    }
    def getNorme(idMesure){

        def mesureFiltre=idMesure?Mesure.get(idMesure):produit?.mesure

        if (mesure?.code==mesureFiltre?.code) {
            return [quantite:quantite,mesure:mesure]
        }else {
            def mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesure,mesureFiltre)

            if (mesCorresp){
                def equivalence=mesCorresp?.equivalence

                if (equivalence){
                    return [quantite:quantite*equivalence,mesure:mesureFiltre]
                }else {
                    return [quantite:quantite,mesure:mesure]

                }
            }else {
                mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesureFiltre,mesure)
                if (mesCorresp){
                    def equivalence=1
                    if (mesCorresp?.equivalence>0)
                    equivalence=1/mesCorresp?.equivalence

                    if (equivalence){
                        return [quantite:quantite*equivalence,mesure:mesureFiltre]
                    }else {
                        return [quantite:quantite,mesure:mesure]

                    }
                }else
                    return [quantite:quantite,mesure:mesure]
            }


        }

    }
    def getQuantiteNorme(idMesure){

       def mesureFiltre=idMesure?Mesure.get(idMesure):produit?.mesure

        if (mesure?.code==mesureFiltre?.code) {
            return quantite
        }else {
            def mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesure,mesureFiltre)
            if (mesCorresp){
                def equivalence=mesCorresp?.equivalence
                if (equivalence){
                    return quantite*equivalence
                }else {
                    return quantite
                }
            }else {
            mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesureFiltre,mesure)
            if (mesCorresp){
                def equivalence=1
                if (mesCorresp?.equivalence>0)
                    equivalence=1/mesCorresp?.equivalence

                if (equivalence){
                    return quantite*equivalence
                }else {
                    return quantite
                 }
            }else
                return quantite
        }


        }

    }
    def getMesurenorme(idMesure){
        def mesureFiltre
        if (idMesure) {
            mesureFiltre=idMesure?Mesure.get(idMesure):produit?.mesure
            if (mesure?.code==mesureFiltre?.code) {
                return mesure
            }else {
                return mesureFiltre

            }
        }else {
            return mesure
        }


    }
    def getPunorme(idMesure){
        def mesureFiltre
        if (idMesure) {
            mesureFiltre=idMesure?Mesure.get(idMesure):produit?.mesure

        }else {
            mesureFiltre= mesure
    }
        if (mesure?.code==mesureFiltre?.code) {
            return prixUnitaireGros
        }else {
            def mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesure,mesureFiltre)
            if (mesCorresp){

                def equivalence=mesCorresp?.equivalence

                if (equivalence){
                    return prixUnitaireGros/equivalence
                }else {
                    return prixUnitaireGros
                }
            }else {
                mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesureFiltre,mesure)
                if (mesCorresp){
                    def equivalence=1
                    if (mesCorresp?.equivalence>0)
                    equivalence=1/mesCorresp?.equivalence

                    if (equivalence){
                        return prixUnitaireGros/equivalence
                    }else {
                        return prixUnitaireGros
                    }
                }else {
                    return prixUnitaireGros
                }
            }


        }

    }
//    def getStatut(){
//        if (isRejected)
//        {
//            return "Rejété"
//        }else {
//            return statut
//        }
//
//    }
    def getQuantiteU(){
        "${quantite} ${mesure.code}"
    }
    def getPrixUnitGrosDetail(){
        "${prixUnitaireGros?prixUnitaireGros.toFCFA():"-"}"
    }

    def getMontantGlobalGrosDetail(){
        def mtGlobal=(prixUnitaireGros?:0)*(quantite?quantite:0.0)
        "${prixUnitaireGros?mtGlobal.toFCFA():"-"}"
    }
    def getNbJoursExpiration(){
        if (dateExpiration>new Date()){
        return dateExpiration-new Date()
        }else
            return 0;

    }


    def log_me() {
        def texteTypeOffre=typeOffre?.equals("Achat")?" d'achat ":" de vente "
        def  contenu_action="${auteur?.toString()} a publié une offre  ${texteTypeOffre} pour le produit ${produit?.toString()}"


    }
    def save_me()  {

        save(failOnError: true)

    }
    def saveIt()  {
        isValidated=false
        isRejected=false
        save_me()
    }

    static mapping={
        sort date:"desc"
    }

}
