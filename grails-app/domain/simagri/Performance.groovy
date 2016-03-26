package simagri

class Performance implements Serializable{
    String nom
    BigDecimal nbOffre
    BigDecimal nbPrix
    BigDecimal nbContact
    BigDecimal nbAlerte
    static constraints = {
        nom nullable:true, maxSize:150
        nbOffre  nullable:true, bindable: false
        nbPrix  nullable:true , bindable: false
        nbAlerte  nullable:true , bindable: false
        nbContact  nullable:true, bindable: false
    }
    String toString(){
      return nom
    }
    static mapping = {
        cache true
    }
}
