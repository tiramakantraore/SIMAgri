package simagri

class NoteMarche {
    String titre
	String contenu
    Utilisateur auteur
    Date date=new Date()
    Integer duree=15
    Boolean actif=false
    Boolean publique=true
    Boolean isRejected=false
    BigDecimal longitude=0.0//add field 22-02-2015
    BigDecimal latitude=0.0//add field 22-02-2015
    Date dateExpiration=new Date()+15
    String sourceTag //add field 10--2015
    byte[] photo
    Reseau reseau
		 static constraints = {
             titre(nullable:false,blank:false,unique:true,maxSize: 150)
             contenu(nullable:true,blank:true,maxSize:5000,widget:'textarea')
             auteur(nullable:false)
             date(nullable:true)
             duree(nullable:true)
             actif(nullable:true,blank:true)
             publique nullable:true
             isRejected nullable:true
             dateExpiration(nullable:true)
             reseau(nullable:true)
             sourceTag nullable:true
             photo(nullable:true, maxSize:10480576)
             longitude nullable:true, scale: 10, bindable: false
             latitude nullable:true, scale: 10,bindable: false
			 }
		 String toString()
		 {
			 """message: ${contenu?(titre?:"Pas de titre"):""}
                auteur: ${auteur}
                date de publication :${date}
             """
		 }

    static mapping = {
        sort date:"desc"
    }

}
