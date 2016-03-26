package simagri

class Prix implements Serializable{
    String nomProduit
	Marche marche
	Produit produit
	Mesure mesure
    Integer year
    Integer month
	Date date
	String statut
	String categorieTarifaire
	String commentaire
    String commentaireAdministrateur
	BigDecimal montant
	Utilisateur auteur
    Utilisateur enqueteur
    Reseau reseau
    PriceHolder priceHolder
    String globalPrice
	Boolean isFromSMS
    Date  periodeDebut
    Date  periodeFin
    Boolean active
    Boolean isValidated
    Boolean isRejected
    String semaine   //provide the exact column name of the date field
    String mois
    String trimestre
    String annee
    String sourceTag //add field 13-12-2016
    static searchable = {
        produit component: true
        marche reference: true
        mesure reference:true
        reseau parent:true
        auteur parent:true
        except=['priceHolder','year','month','enqueteur']


    }
    static transients=['globalPrice','prixMesure','sourcePrix']
    static constraints = {
        produit(nullable:false)
        marche(nullable:false)
		date(widget:'datePicker')
        montant(nullable:true, bindable: false)
        mesure(nullable:true)
        categorieTarifaire(blank:false,inList:['Gros','Detail'])
        statut(blank:false, inList:['EnCours','Approuve','Rejete'])
        auteur(nullable:false)
		commentaire(nullable:true,maxSize:255,widget:'textarea')
        commentaireAdministrateur(nullable:true,maxSize:255,widget:'textarea')
        priceHolder(nullable:true)
        year(nullable:true)
        month(nullable:true)
		isFromSMS(nullable:true)
        periodeDebut(nullable:true)
        periodeFin(nullable:true)
        enqueteur(nullable:true)
        reseau(nullable:true)
        nomProduit(nullable:true)
         active(nullable:true)
         isValidated(nullable:true)
         isRejected(nullable:true)
        sourceTag nullable: true
    }
	def getSourcePrix(){	
		if (isFromSMS){
			return "SMS"
		}else {
		return "WEB"
		}
	}
	String toString(){
		"Marché : ${marche?.nom?:"NF"} Produit : ${produit?.nom?:"NF"} Prix ${(categorieTarifaire=="Gros")? "de gros":" de détail"}: ${prixMesure} Date : ${date?.toGoodFormat()?:""}"
	}
    def setYear(Integer aYear){
        if (!aYear){
            year=(date.getYear()+1900)

        }
        else{
            year=aYear
        }
    }
    def getPrixMesure() {
        montant?"${montant?.toFCFA()}/${mesure?.code}":"-"
    }
    def setMonth(Integer aMonth){
        if (!aMonth){
            month=date?.getMonth()+1
        }
        else{
            month=aMonth
        }
    }
    String getGlobalPrice(){
        if (montant ){
            return "${montant}"
        }
        else
        {
            return "Négociable"
        }

    }
    static mapping={
        sort date:"desc"
        semaine formula :'WEEK(date)'    //provide the exact column name of the date field
        mois formula :'MONTH(date)'
        trimestre formula :'QUARTER(date)'
        annee formula :'YEAR(date)'

    }



}
