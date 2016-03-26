package simagri

class Sondage implements Serializable{
    String titre
    String description
    Date dateDebut=new Date()
    Integer duree=30
    Date dateFin=new Date()+30
    Boolean actif=false
    List<Choix> details = []
    static constraints = {
        titre(unique:true,maxSize:150,nullable:false,blank:false)
        description(blank:true,nullable:true,maxSize:255)
        dateDebut(nullbale:false)
        duree(nullable:false)
        dateFin(nullable:false)
        actif(nullable:false)
    }
    String toString(){
        titre
    }
    static hasMany=[details:Choix]
    static mapping = {
        details cascade:"all-delete-orphan"
    }

}
