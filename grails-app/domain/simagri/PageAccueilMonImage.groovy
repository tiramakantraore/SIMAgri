package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class PageAccueilMonImage implements Serializable{
    PageAccueil pageAccueil
    MonImage monImage
    static constraints = {
    }
    static mapping = {
        table 'pageaccueil_monimage'
        version false
        id composite: ['pageAccueil','monImage']
    }
    static  PageAccueilMonImage create(PageAccueil pageAccueil,MonImage monImage, Boolean flush=false) {
        PageAccueilMonImage pageAccueilMonImage=new  PageAccueilMonImage(monImage:monImage,pageAccueil:pageAccueil)
        pageAccueilMonImage.save(flush:flush,insert:true)
        return pageAccueilMonImage
    }
    static Boolean remove(MonImage monImage,PageAccueil pageAccueil, Boolean flush=false) {
        PageAccueilMonImage pageAccueilMonImage=findByMonImageAndPageAccueil(monImage,pageAccueil)
        return  pageAccueilMonImage ? pageAccueilMonImage.delete(flush:flush):false
    }
    static void removeAll(MonImage monImage) {
        executeUpdate("DELETE FROM PageAccueilMonImage where monImage=:monImage",[monImage:monImage])
    }
    static void removeAll(PageAccueil pageAccueil) {
        executeUpdate("DELETE FROM PageAccueilMonImage where pageAccueil=:pageAccueil",[pageAccueil:pageAccueil])
    }
    static PageAccueilMonImage get (Long PageAccueilId, Long monImageId) {
        find 'from PageAccueilMonImage where pageAccueil.id=:PageAccueilId and monImage.id=:monImageId',[PageAccueilId:PageAccueilId,monImageId:monImageId]
    }
    boolean equals(other) {
        if (! (other instanceof PageAccueilMonImage)){
            return false
        }
        other.monImage?.id==monImage?.id &&
                other.pageAccueil?.id==pageAccueil?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (monImage)
            builder.append(monImage.id)
        if (pageAccueil)
            builder.append(pageAccueil.id)
       return builder.toHashCode()

    }
}
