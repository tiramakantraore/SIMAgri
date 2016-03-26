package simagri
import com.tropo.grails.TropoBuilder
import org.apache.commons.io.IOUtils
import org.springframework.context.ApplicationContext

class TropoController {
    def tropoService

    def index = {

        def citiesFile = applicationContext.getResource('classpath:cities.txt').file
        def citiesString = new String(IOUtils.toByteArray(new FileInputStream(citiesFile)))

        def tropo = new TropoBuilder()
        tropo.tropo {
            say(value:"Bienvenue au service d'information...",
                    voice:"Juliette")
            ask(name:'city',mode:"speech",
                    recognizer:"fr-fr", voice:"Juliette") {
                say(value:"Veuillez donner le nom d'une cité")
                choices(value:citiesString)
            }
            on(event:'continue',next:'/tropo/restaurant')
        }
        tropo.render(response)
    }
    def restaurant = {

        def tropoRequest = request.JSON
        def place = tropoRequest?.result?.actions?.value

        def map = restaurantMap(place)
        session["restaurants"] = map
        def restaurants = restaurantList(map)

        def tropo = new TropoBuilder()
        tropo.tropo {
            ask(name:'restaurant', mode:'speech',
                    recognizer:"fr-fr", voice:"Juliette") {
                say(value:"Veuillez donner le nom du restaurant ${place}")
                choices(value:restaurants)
            }
            on(event:'continue',next:'/tropo/info')
        }
        tropo.render(response)
    }

    def restaurantMap(def place) {

        def query = "http://api.11870.com/api/v2/search?appToken=29ea7f63c6f3a2bc8dacc3f6a9a3d84d&l=${place}&categoryOp=or&category=restaurantes"

        def feed = new XmlParser().parse(query)

        def restaurants = []
        def map=[:]
        feed.entry.each {
            restaurants << it.title.text()
            map.put(it.title.text(),it.'oos:telephone'.text())
        }
        map
    }
    def info = {

        def tropoRequest = request?.JSON
        def restaurant = tropoRequest?.result?.actions?.value

        def map = session["restaurants"]
        def phone = map.get(restaurant)

        def tropo = new TropoBuilder()
        tropo.tropo {
            say(value:"Le téléphone du restaurant ${restaurant} est ${phone}...",
                    voice:"Juliette")
            hangup()
        }
        tropo.render(response)
    }
    public void setApplicationContext(ApplicationContext applicationContext) {

        this.applicationContext = applicationContext;
    }

    def restaurantList(def map) {

        map.keySet().join(",")
    }
}
