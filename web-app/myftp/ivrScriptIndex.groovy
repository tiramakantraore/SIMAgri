
/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 27/08/13
 * Time: 01:02
 * To change this template use File | Settings | File Templates.
 */
//def builder=new TropoBuilder([voice:"Juliette",recognizer:"fr-fr"])
//builder.tropo{
//    say("Bienvenue sur la plateforme SIMAgri")
//    //  say("http://ccmixter.org/content/copperhead/copperhead_-_When_We_Do.mp3")
//    ask(name:'question',timeout: 10.0, required: true)  {
//        say("Que voulez-vous faire ? Consulter ou Envoyer ? ")
//        choices(value:'Consulter,Envoyer',attempts: 3)
//        mode:"voice"
//        recognizer:"fr-fr"
//    }
//    on (event:'continue',next:'resultFirst')
//}
def closure=
 """ {say("Bienvenue sur la plateforme SIMAgri")
    ask(name:'question',timeout: 10.0, required: true)  {
        say("Que voulez-vous faire ? Consulter ou Envoyer ? ")
        choices(value:'Consulter,Envoyer',attempts: 3)
        mode:"voice"
        recognizer:"fr-fr"
    }
    on (event:'continue',next:'resultFirst')
    }
     """
return closure

