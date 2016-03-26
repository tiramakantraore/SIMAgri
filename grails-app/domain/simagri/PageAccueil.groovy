package simagri

class PageAccueil implements Serializable{
    String nom
    PageUtilisateur banniere
    PageUtilisateur pagePrix
    PageUtilisateur pageOffre
    PageUtilisateur pageStock
    PageUtilisateur pageReseau
    Boolean estPrincipal=true
    static constraints = {
        nom nullable: true,maxSize:25
        estPrincipal nullable: true
        pagePrix nullable: true
        pageOffre nullable: true
        pageStock nullable: true
        pageReseau nullable: true
        banniere nullable: true

    }
    String toString(){
        nom
    }
    Set<MonImage>  getMesImages(){
        try {
            return  PageAccueilMonImage.findAllByPageAccueil(this).collect{it.monImage} as Set
        } catch ( e){
            log.debug(e)
            return []
        }


    }
    Set<MonImage>  getMesImageRandomize(){
        try {
            Set<MonImage> ranomizesImages
            ranomizesImages = PageAccueilMonImage.withCriteria {
                eq('pageAccueil.id', this.id)
            }.collect { it.monImage } as Set

            return ranomizesImages
        } catch ( e){
            log.debug(e)
            return []
        }

    }
    static mapping = {
        cache true
    }
  //  static fetchMode = [pagePrix: 'eager',pageOffre: 'eager',pageStock: 'eager',pageReseau: 'eager']
}