package simagri

class StockMagazin implements Serializable{
   	Mesure mesure
	Produit produit
	Date date
    Integer stock
    Utilisateur auteur
	Date dateCreated
	Date lastUpdated
 	Magazin magazin
    Reseau reseau
    String commentaire
    Boolean isValidated=false
    Boolean isRejected=false
    String sourceTag //add field 13-12-2016
	static belongsTo=[magazin:Magazin]
//    static searchable = true
    static constraints = {
        magazin(nullable:false)
        produit(nullable:false)
		date(nullable:false)
        stock(nullable:false,validator:{Integer qte -> return qte>0})
        mesure(nullable:false)
        reseau(nullable:true)
        commentaire(nullable:true,maxSize: 255)
        isValidated(nullable:false)
        isRejected(nullable:true,default:false)
        sourceTag nullable: true

    }
    String toString(){
        "${produit?.nom} ${magazin.nom} ${date}"
    }
    def saveIt(){
        isValidated=false
        save()?true:false
    }

    static mapping={
        sort date:"desc"

    }
    def getMesurenorme(idMesure){
        def mesureFiltre=idMesure?Mesure.get(idMesure):produit?.mesure

        if (mesure?.code==mesureFiltre?.code) {
            return mesure
        }else {
            return mesureFiltre

        }

    }
    def getStockNorme(idMesure){
        def mesureFiltre=idMesure?Mesure.get(idMesure):produit?.mesure
        if (mesure?.code==mesureFiltre?.code) {
            return stock
        }else {
            def mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesure,mesureFiltre)
            if (mesCorresp){
                def equivalence=mesCorresp?.equivalence

                if (equivalence){
                    //    println " ${produit} saisi en ${mesure.code} equivalence ${equivalence} prixUnitaireGros ${prixUnitaireGros} newPU ${prixUnitaireGros/equivalence}"
                    return stock/equivalence
                }else {
                    return stock
                }
            }else {
                mesCorresp=MesureCorrespondance.findByMesureDepartAndMesureDestination(mesureFiltre,mesure)
                if (mesCorresp){
                    def equivalence=1
                    if (mesCorresp?.equivalence>0)
                    equivalence=mesCorresp?.equivalence

                    if (equivalence){
                        //    println " ${produit} saisi en ${mesure.code} equivalence ${equivalence} prixUnitaireGros ${prixUnitaireGros} newPU ${prixUnitaireGros/equivalence}"
                        return stock/equivalence
                    }else {
                        return stock
                    }
                }else
                    return stock

            }


        }

    }

}
