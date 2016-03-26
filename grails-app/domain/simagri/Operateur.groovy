package simagri

class Operateur implements Serializable {
    String nom
    String prefixes
    String emailContact
    String telephoneContact
    String siteWeb
    static constraints = {
        nom (blank: false,maxSize:150)
        prefixes (blank: false,maxSize:300)
        emailContact(email:true,blank:true)
        telephoneContact (blank:true)
        siteWeb(url:true,blank:true)

    }
     Boolean isPrefixInList(String prefix) {
        def listePrefix=prefixes?.split(';')
        return listePrefix.find{it.equals(prefix)}

    }
    static Operateur getTheOperator(String prefix) {
        def listOperateurs=Operateur.findAll()
        Operateur operateur=Operateur.get(1)
        listOperateurs.each{
            if (it?.isPrefixInList(prefix)) {
                operateur=it
            }
        }
      return operateur

    }
    String toString(){
        nom
    }
    static mapping = {
        sort "nom"
        cache true
    }
}
