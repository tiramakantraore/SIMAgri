package simagri

class PriceHolder implements Serializable{
    String nomProduit
    String nomMarche
    Produit produit
    Marche marche
    Date date
    Integer year
    Integer month
    BigDecimal prixGros
    BigDecimal prixDetail
    Mesure mesureGros
    Mesure mesureDetail
    String commentaire
    String commentaireAdministrateur
    Boolean active
    Utilisateur auteur
    Boolean isValidated
    Boolean isRejected
	Boolean isFromSMS=false
    Date  transactionDate
    Date  periodeDebut
    Date  periodeFin
    String globalPrice
    Reseau reseau
    String semaine   //provide the exact column name of the date field
    String mois
    String trimestre
    String annee
    String sourceTag //add field 13-12-2016
    static transients=['globalPrice','sourcePrix']
    static constraints = {
        produit(nullable:true)
        marche(nullable:true)
        date(nullable:true)
        transactionDate(nullable:true)
        nomProduit(nullable:true)
        prixGros(nullable:true, bindable: false)
        prixDetail(nullable:true, bindable: false)
        mesureGros(nullable:true)
        mesureDetail(nullable:true)
        commentaire(nullable:true,maxSize: 255)
        commentaireAdministrateur(nullable:true,maxSize:255,widget:'textarea')
        active(nullable:true,default:false)
        isValidated(nullable:true,default:false)
        isRejected(nullable:true,default:false)
        nomMarche(nullable:true)
		isFromSMS(nullable:true)
        year(nullable:true)
        month(nullable:true)
        auteur(nullable:true)
        periodeDebut(nullable:true)
        periodeFin(nullable:true)
        reseau(nullable:true)
        sourceTag nullable: true
    }
    Boolean hasDetailPrices(){
         return ((prixDetail?:0.0)>0)
    }
	Boolean hasWholePrices(){
		return ((prixGros?:0.0)>0)
   }
	def getSourcePrix(){
		if (isFromSMS){
			return "SMS"
		}else {
		return "WEB"
		}
	}
    String getGlobalPrice(){
        if (prixGros || prixDetail){
        return "${prixGros?:"-"}/${prixDetail?:"-"}"
        }
        else
        {
            return "Négociable"
        }

    }

    def saveIt(){
        active=true
        isValidated=false
        isRejected=false
        save(flush:true)?true:false
    }
    static mapping={
        sort date:"desc"
        semaine formula :'WEEK(date)'    //provide the exact column name of the date field
        mois formula :'MONTH(date)'
        trimestre formula :'QUARTER(date)'
        annee formula :'YEAR(date)'
    }


    def setYear(Integer aYear){
        if (!aYear){
            year=(date?.getYear()+1900)

        }
        else{
            year=aYear
        }
    }
    def setMonth(Integer aMonth){
        if (!aMonth){
            month=date?.getMonth()+1
        }
        else{
            month=aMonth
        }
    }


    String toString(){
     "${nomProduit} ${nomMarche?:""} ${date?:""} ${globalPrice?:""}"
    }

}
