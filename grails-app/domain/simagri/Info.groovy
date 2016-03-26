package simagri

class Info implements Serializable{
    String titre
	String contenu
	String url
    Utilisateur auteur
    Date date=new Date()
    Integer duree=15
    Boolean actif=false
    Boolean publique=true
    Boolean isRejected=false
    Date dateExpiration=new Date()+15
    String sourceTag //add field 13-12-2016
    Reseau reseau
		 static constraints = {
             titre(nullable:false,blank:false,unique:true,maxSize: 150)
             contenu(nullable:true,blank:true,maxSize:5000,widget:'textarea')
             auteur(nullable:false)
             date(nullable:true)
             duree(nullable:true)
             actif(nullable:true,blank:true)
			 url nullable:true, url:true
             publique nullable:true
             isRejected nullable:true
             dateExpiration(nullable:true)
             reseau(nullable:true)
             sourceTag nullable: true
			 }
		 String toString()
		 {
			 """message: ${contenu?(titre?:"Pas de titre"):""}
                auteur: ${auteur}
                date de publication :${date}
             """
		 }
    static transients = ['infoTitle','preambule']
    def getInfoTitle(){
        "${titre}"
    }
    def getPreambule(){
       def tailleMess=contenu.size()
        def limiteSup=(tailleMess*2/3).toInteger()
        "${contenu.substring(0,limiteSup)} ..."
    }
    static mapping = {
        sort date:"desc"
    }

}
