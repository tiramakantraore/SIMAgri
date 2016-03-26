package simagri

class SondageReponse implements Serializable{
    Utilisateur sonde
    Choix reponse
    static constraints = {
        sonde nullable:true
        reponse nullable:false
    }
    static transients=["sondage"]
    def getSondage(){
        reponse.sondage
    }
    static belongsTo=[reponse:Choix]
}
