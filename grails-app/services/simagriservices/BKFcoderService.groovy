package simagriservices

class BKFcoderService {

    boolean transactional = false

    // http://ws.geonames.org/search?name_equals=den&fcode=airp&style=full
    def geocodeAirport(String codeLocation) {
        String base = "http://ws.geonames.org/search?"
        List<String> qs = []
        qs << "name_equals=" + URLEncoder.encode(codeLocation)
        qs << "fcode=PCLI"
        qs << "style=full"
        def url = new URL(base + qs.join("&"))
        def connection = url.openConnection()

        def result = [:]
        if(connection.responseCode == 200){
            def xml = connection.content.text
            def geonames = new XmlSlurper().parseText(xml)
            result.name = geonames.geoname.name as String
            result.lat = geonames.geoname.lat as String
            result.lng = geonames.geoname.lng as String
            result.state = geonames.geoname.adminCode1
            result.country = geonames.geoname.countryCode as String
        }
        else{
            log.error("GeocoderService.geocodeAirport FAILED")
            log.error(url)
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }
        return result
    }
}
