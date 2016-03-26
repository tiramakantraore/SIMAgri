package simagri

class LogoProduit extends MyImage {
    Boolean actif =true
    String legende
    static constraints ={
        actif(nullable:false)
        legende(nullable:true,blank:true)
    }
    static belongsTo = [CategorieProduit]
    String toString(){
        "${legende?:"Avatar"}"
    }

}
