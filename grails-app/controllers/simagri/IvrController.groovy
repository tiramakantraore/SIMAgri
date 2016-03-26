package simagri

import com.dbconfig.ConfigProperty
import com.tropo.grails.TropoBuilder
import org.apache.commons.io.IOUtils
class IvrController {
    def tropoService
    def smsDecoderService
    def grailsApplication
    def appeler = {

        tropoService.launchSession(token:
                grailsApplication.config.tropoWebServiceId)

    }
    def index() {
        def country =session.country?:ConfigProperty.findByConfigKey('grails.defaultCountry')?.value?:grailsApplication.config.grails.defaultCountry
        def contryPath="$country/"
        def tropo=new TropoBuilder([recognizer:"fr-fr",voice:"Juliette"])
        def webRootDir = servletContext.getRealPath("/")
        def produitsFile = new File("${webRootDir}${grailsApplication.config.myftp.dir}${contryPath}produits.txt")
        def produitsString = new String(IOUtils.toByteArray(new FileInputStream(produitsFile)))

        //   def produitsString =Produit.list().nom.toListString()

//        String produitsString = ""
//        Produit.list().collect { produitsString += "$it.nom,"  }

//        println "prods : ${Produit.list().nom.join(", ")}  "
//
//        println "marches : ${new String(Marche.list().nom.join(", "))}  "
        tropo?.tropo{
            say(value:"Bienvenue sur le serveur vocal de la plateforme SIMAgri",voice:"Juliette")
            ask(name:'prix', mode:'speech',
                    recognizer:"fr-fr", voice:"Juliette", bargein: true,timeout: 40, attempts: 3) {
                say([[event: 'timeout', value: "Désolé, Je n\'ai rien entendu ."],
                     [event: 'nomatch:1 nomatch:2 nomatch:3', value: "Désolé, le produit que vous avez choisi n 'existe pas ou a été mal prononcé" ],
                     [value: "Donnez le nom d'un produit"],
                     [event: 'nomatch:3', value: 'Ceci est votre dernière chance']])
                choices(value:produitsString)
            }
            on(event:'continue',next:'/ivr/marche')
        }

        tropo?.render (response)

    }



    def marche() {
        def json=request?.JSON
        def nomProduit=json?.result?.actions?.concept
        def country =session.country?:ConfigProperty.findByConfigKey('grails.defaultCountry')?.value?:grailsApplication.config.grails.defaultCountry
        def contryPath="$country/"
        def webRootDir = servletContext.getRealPath("/")
        session["nomProduit"] = nomProduit
        def produit=Produit.findByNomIlike(nomProduit)
        def marchesFile = new File("${webRootDir}${grailsApplication.config.myftp.dir}${contryPath}marches.txt")
        def marchesString = new String(IOUtils.toByteArray(new FileInputStream(marchesFile)))
        def tropo=new TropoBuilder([recognizer:"fr-fr",voice:"Juliette"])
        if (produit) {
        tropo.tropo{
            say(value:"Vous avez choisi le produit ${nomProduit}",voice:"Juliette")
            ask(name:'prix', mode:'speech',
                    recognizer:"fr-fr", voice:"Juliette", required: true, bargein: true, timeout: 40, silenceTimeout: 10, attempts: 3) {
             //   say(value:"Veuillez à présent préciser le marché pour lequel vous voulez les prix ")
                say([[event: 'timeout', value: 'Désolé, Je n\'ai rien entendu .'],
                        [event: 'nomatch:1 nomatch:2 nomatch:3 ', value: "Désolé, le marché que vous avez choisi n 'existe pas ou a été mal prononcé" ],
                        [value: "Veuillez à présent préciser le marché pour lequel vous voulez les prix"],
                        [event: 'nomatch:3', value: 'Ceci est votre dernière chance']])
                choices(value:marchesString)
            }
            on(event:'continue',next:'/ivr/reponse')
        }
        }else {
            tropo.tropo{
                say(value:"Le produit que vous avez choisi n'a pas été retrouvé veuillez mieux prononcer au prochain appel",voice:"Juliette")
                hangup()
            }
        }

        tropo.render (response)

    }
    def restart() {
        def json=request?.JSON
        def restart=json?.result?.actions?.concept
        session["restart"] = restart

        def tropo=new TropoBuilder([recognizer:"fr-fr",voice:"Juliette"])
        if (restart=="oui") {
            tropo.tropo{
                say(value:"Vous avez choisi de recommencer, ")
                transfer(to: 'sip:9990097646')
            }
        }else {
            tropo.tropo{
                hangup()
            }
        }

        tropo.render (response)

    }
    def reponse={
        def json=request?.JSON
        String nomProduit=session["nomProduit"] as String
        String nomMarche=json?.result?.actions?.concept
        def produit=Produit.findByNomIlike(nomProduit)
        def marche=Marche.findByNomIlike(nomMarche)
        def tropo=new TropoBuilder([recognizer:"fr-fr",voice:"Juliette"])
        if (produit && marche)  {
        def reponsePrix=smsDecoderService.getPriceFromVoice(produit.code,marche.code)
            tropo.tropo {
            say(value:"La réponse est : ${reponsePrix} ")
            hangup()
        }
        }else {
            tropo.tropo {
                say(value:" le marché que vous avez choisi  n''a pas été retrouvé, veuillez mieux prononcer au prochain appel")
                hangup()
            }
        }

        tropo.render (response)
    }
}
