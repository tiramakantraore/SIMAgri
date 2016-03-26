package simagri

class Choix {
    String choix
    Boolean actif=true
    Boolean deleted=false
    Integer numOrdre=0
    static transients = [ 'deleted','titreSondage' ]
    static constraints = {
        choix(nullable:false,maxSize:150)
        actif(nullable:false)
        numOrdre(blank:false, min:0)
    }
    String toString(){
        return "(${numOrdre}) ${choix} "
    }
    String getTitreSondage(){
        sondage?.titre
    }
    static belongsTo=[sondage:Sondage]
    static hasMany = [reponses:SondageReponse]
    static mapping = {
        reponses cascade:"all-delete-orphan"
    }
}
