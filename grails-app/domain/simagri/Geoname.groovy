package simagri

class Geoname implements Serializable{
    Integer geonameId
    String name
    String asciiname
    BigDecimal latitude
    BigDecimal longitude
    String featureClass
    String featureCode
    String countryCode
    Integer population
    Integer elevation
    Integer dem
    String timeZone
    Date modificationDate
    static constraints = {
        geonameId(nullable:false)
        name(nullable:true)
        asciiname(nullable:true)
        latitude(nullable:true)
        longitude(nullable:true)
        featureClass(nullable:true)
        featureCode(nullable:true)
        countryCode(nullable:true)
        population(nullable:true)
        elevation(nullable:true)
        dem(nullable:true)
        timeZone(nullable:true)
        modificationDate(nullable:true)

    }
    static mapping = {
        cache usage:'read-only'
    }
}
