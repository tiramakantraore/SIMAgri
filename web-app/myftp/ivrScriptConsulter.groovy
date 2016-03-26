import com.tropo.grails.TropoBuilder
import grails.converters.JSON
/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 27/08/13
 * Time: 01:02
 * To change this template use File | Settings | File Templates.
 */

def closure=
   {say("Merci nous avons enregistré que vous vous voulez ${input} ")
        ask(name:'question', required: true)  {
            say("Que voulez-vous consulter ?  les prix ou les offres ? ")
            choices(value:"Prix,Offre",timeout: 10.0,attempts: 3)
            mode:"voice"
            say("http://ccmixter.org/content/copperhead/copperhead_-_When_We_Do.mp3")

        }
        on (event:'continue',next:'resultSecond')
    }

return closure




