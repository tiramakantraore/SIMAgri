package simagri

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.util.Environment
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder as SCH

class LoginCommand {

    String j_password
    String j_username
    Boolean j_rememberMe
    static constraints = {

        j_password  blank: true, nullable: true
        j_username  blank: true, nullable: true
        j_rememberMe blank:true,nullable: true
    }
}
class LoginController {

	/**
	 * Dependency injection for the authenticationTrustResolver.
	 */
	def authenticationTrustResolver
    def passwordEncoder
	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService
    def rememberMeServices
    def sendSMSService

    def myUtilityService
	/**
	 * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
	 */
	def index = {
		if (springSecurityService.isLoggedIn()) {
			redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
		}
		else {
			redirect action: 'auth', params: params
		}
	}

	/**
	 * Show the login page.
	 */
	def auth = { LoginCommand loginCommand->

		def config = SpringSecurityUtils.securityConfig

		if (springSecurityService.isLoggedIn()) {
			redirect uri: config.successHandler.defaultTargetUrl
		}

		String view = 'auth'
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		render view: view, model: [postUrl: postUrl,
		                           rememberMeParameter: config.rememberMe.parameter,loginCommand: loginCommand]
	}

	/**
	 * The redirect action for Ajax requests.
	 */

    def login = {

        def user=Utilisateur.findByMobilePhone(params.j_username)
        def passWordMatch=passwordEncoder.isPasswordValid(user?.password, params.j_password,null)
        flash.password_changed=""
        flash.user_not_registred=""
        flash.messageEchecLogin=""

        if (!passWordMatch)
        {
            flash.messageEchecLogin=message(code: 'default.created.message', args: [message(code: 'springSecurity.denied.message', default: "L'utilisateur n'a pas été trouvé")])
           redirect(controller:'home', action: 'accueil')
        }
        else {
            def loginCommand=new LoginCommand()
            loginCommand.properties=params
            springSecurityService.reauthenticate (loginCommand.j_username,springSecurityService.encodePassword(loginCommand.j_password))
            if (loginCommand.j_rememberMe)  {
                rememberMeServices.onLoginSuccess(request,response,springSecurityService.authentication)
            }

            redirect(controller:'dashboard', action: 'accueil')

        }

    }
	/**
	 * Show denied page.
	 */
	def denied = {
		if (springSecurityService.isLoggedIn() &&
				authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: 'full', params: params
		}
	}

	/**
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
    def full() {
        def config = SpringSecurityUtils.securityConfig

        render view: 'auth', params: params,
                model: [hasCookie: authenticationTrustResolver.isRememberMe(SecurityContextHolder.context?.authentication),
                        postUrl: "${request.contextPath}${config.apf.filterProcessesUrl}"]

    }

    def resetPassword() {
        switch (request.method) {
            case 'GET':

                def utilisateurInstance = new Utilisateur(params)
                [utilisateurInstance: utilisateurInstance]
                break
            case 'POST':

                myUtilityService?.getCurrent()?.discard()
                def utilisateur = Utilisateur.findByMobilePhone(params.mobilePhone)

                if (utilisateur) {


                        def password = params.mobilePhone?.randomPassWord()
                        utilisateur.password=password
                        utilisateur.encodePassword()
                        utilisateur.save(flush:true)
                        def toPhoneNumber = (utilisateur.mobilePhone?.toString()?.getRightPhone())
                        def messagesms = """
                      Votre nouveau mot de passe : ${password}
                       """
                    println " user : $utilisateur phone : $utilisateur.mobilePhone password $messagesms "
                    if (Environment.current == Environment.PRODUCTION ) {
                        sendSMSService?.execute(messagesms, toPhoneNumber)
                        if (utilisateur.email) {
                            try {
                                sendMail {
                                    to "${utilisateur.email}"
                                    subject " ${grailsApplication.config.nomCourtPlateforme} : Changement de mot de passe"
                                    body """Bonjour ${utilisateur.login}
                                        Vous avez demandé la réinitialisation de votre mot de passe
                                        Voici vos nouveaux paramètres de connexion :
                                        Identifiant : ${utilisateur.mobilePhone}
                                        mot de passe : ${password}
                                        veuillez changer ce mot de passe temporaire le plus tôt possible
                                    """
                                }
                                flash.password_changed = """ Vos nouveaux paramètres ont été envoyés à l'adresse ${
                                    utilisateur.email
                                }
                                                                Veuillez consulter votre dossier spam si vous ne retrouvez pas le mail
                                                                Vous les recevrez aussi par SMS au numéro ${
                                    utilisateur.mobilePhone
                                }
                                                                """
                            } catch (Exception e) {
                                log.error " exception : ${e}"
                            }
                        }
                    }else {
                        log.info(" messagesms : ${messagesms}")
                    }
                        redirect controller: 'home', action: 'accueil'


                } else {
                    println "l'utilisateur n'est pas dans la base"
                    flash.user_not_registred = """ Il n'existe pas d'utilisateur enregistré avec le numéro de téléphone ${utilisateur.mobilePhone}
                                                                Il faut envisager la création d'un compte SIMAgri
                                """
                    redirect controller: 'home', action: 'resetPassword'
                }
                break;
        }
    }

	/**
	 * Callback after a failed login. Redirects to the auth page with a warning message.
	 */
	
	/**
	 * The Ajax success redirect url.
	 */
	
    def quitter = {
        render(controller:"home", action:"accueil")
    }
	/**
	 * The Ajax denied redirect url.
	 */

}
