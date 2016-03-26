package simagri

class SmsToReseaux implements Serializable{
    String yourTextMessage
    static hasMany = [reseaux:Reseau ,destinataires:Utilisateur]
//    static searchable = true
    static constraints = {
        reseaux(nullable:false)
        yourTextMessage(blank: false,size:5..2000,widget:'textarea')
        destinataires(nullable: true)
    }


}
