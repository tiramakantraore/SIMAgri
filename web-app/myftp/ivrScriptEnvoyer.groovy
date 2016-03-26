
/**
 * Created with IntelliJ IDEA.
 * User: Tiramakan
 * Date: 27/08/13
 * Time: 01:02
 * To change this template use File | Settings | File Templates.
 */
closure= """
   {
     say("Vous avez choisi de faire une mise en ligne ")
        ask(name:'question', required: true)  {
            say("Que voulez-vous mettre en ligne ? dites 1 pour mettre en lignes des prix et 2 pour les offres")
            choices(value:"Prix,Offre",timeout: 10.0,
                    attempts: 3)
            mode:"voice"
        }
        on (event:'continue',next:'resultSecond')
    }
"""
return closure


