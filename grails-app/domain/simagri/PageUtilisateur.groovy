package simagri

class PageUtilisateur implements Serializable{
    String nom
    String description
    String contenu
    DestinationType destinationType=DestinationType.PAGE_AUTRE
    TypePartenaire typePartenaire
    Reseau proprietaire
    String adresse
    String email
    String telephone
    String mobile
    Date lastUpdated
    Date dateCreated
    byte[] photo
    TypeAlignement alignement=TypeAlignement.DROITE
    static constraints = {
        nom blank:false, maxSize:225
        destinationType nullable:false
        adresse nullable:true,url:true
        description blank:true,nullable:true, maxSize:1000
        proprietaire nullable:false
        contenu nullable:true, maxSize:5000
        photo(nullable:true, maxSize:1048576)
        alignement nullable:true
        email nullable:true, email:true
        mobile nullable:true
        telephone nullable:true
        lastUpdated nullable:true
        dateCreated nullable:true
        typePartenaire nullable:true
    }
    static mapping = {
        cache true
        sort "nom"
        autoTimestamp true
    }
    String toString(){
        nom
    }
    Boolean removeImage(){
        return alignement==TypeAlignement.NA
    }
}
public enum DestinationType {
    PAGE_ACCEUIL('Page_accueil'),
    PAGE_RESEAU('Page_reseau'),
    PAGE_PARTENAIRE('Page_partenaire'),
    PAGE_AFRIQUE_VERTE('Page_AV'),
    PAGE_SIMAGRI('Page_SIMAgri'),
    PAGE_AVANTAGE('Page_Avantages'),
    PAGE_ESSAIE('Page_Essaie'),
    PAGE_AUTRE('Page_Autre')
    String name

    DestinationType(String name) {
        this.name = name
    }
    static DestinationType valueOfName( String name ) {
        values().find { it.name == name }
    }
}
public enum TypePartenaire {
    PARTENAIRE_ICCO('ICCO'),
    PARTENAIRE_IICD('IICD'),
    PARTENAIRE_TTC('TTC'),
    PARTENAIRE_TFK('TFK'),
    PARTENAIRE_APROSSA('APROSSA'),
    PARTENAIRE_AMASSA('AMASSA'),
    PARTENAIRE_BAMIG('BAMIG'),
    PARTENAIRE_AUTRE('AUTRE')
    String name

    TypePartenaire(String name) {
        this.name = name
    }
    static TypePartenaire valueOfName( String name ) {
        values().find { it.name == name }
    }
}
public enum TypeAlignement {
    GAUCHE('Gauche'),
    DROITE('Droite'),
    HAUT('Haut'),
    BAS('Bas'),
    NA('NA')
    String name

    TypeAlignement(String name) {
        this.name = name
    }
}