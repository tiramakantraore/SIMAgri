package simagri

import grails.converters.JSON
import grails.transaction.Transactional
import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException
import grails.util.Environment
import org.springframework.web.multipart.MultipartFile

@Transactional(readOnly = true)
class UtilisateurController {
    def exportService
    def myUtilityService
    def filterPaneService
    def userService
    def grailsApplication
    def aws
    def sendSMSService
    def mailService
    def myFormHelperService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def updateByJSON(){
        if (params.id!="null"){

           def marches=UtilisateurMarche.createCriteria().list {
               eq("utilisateur.id",new Long (params.id?.toString()?:"0"))
               createAlias('marche','marche')
               projections {
                   distinct("marche.id")
               }
           }

            def marchesEcriture=UtilisateurMarcheEcriture.createCriteria().list {
                eq("utilisateur.id",new Long (params.id?.toString()?:"0"))
                createAlias('marche','marche')
                projections {
                    distinct("marche.id")
                }
            }
            def resultsmarches=[]
            marches.each{
                resultsmarches <<"markets_${it}"
            }
            def resultsmarchesEcriture=[]
            marchesEcriture.each{
                resultsmarchesEcriture <<"marchesEcriture_${it}"
            }
            def produits=UtilisateurProduit.createCriteria().list {
                eq("utilisateur.id",new Long (params.id?.toString()?:"0"))
                createAlias('produit','produit')
                projections {
                    distinct("produit.id")
                }
            }

            def resultsproduits=[]
            produits.each{
                resultsproduits <<"produits_${it}"
            }
            def categorieProduits=UtilisateurCategorieProduit.createCriteria().list {
                eq("utilisateur.id",new Long (params.id?.toString()?:"0"))
                createAlias('categorieProduit','categorieProduit')
                projections {
                    distinct("categorieProduit.id")
                }
            }
            def resultsCategorieproduits=[]
            produits.each{
                resultsCategorieproduits <<"categories_${it}"
            }

            def regions=UtilisateurRegion.createCriteria().list {
                eq("utilisateur.id",new Long (params.id?.toString()?:"0"))
                createAlias('region','region')
                projections {
                    distinct("region.id")
                }
            }


            def magazins=UtilisateurMagazin.createCriteria().list {
                eq("utilisateur.id",new Long (params.id?.toString()?:"0"))
                createAlias('magazin','magazin')
                projections {
                    distinct("magazin.id")
                }
            }

            def resultsmagazins=[]
            magazins.each{
                resultsmagazins <<"magazins_${it}"
            }
            def services=UtilisateurService.createCriteria().list {
                eq("utilisateur.id",new Long (params.id?.toString()?:"0"))
                createAlias('service','service')
                projections {
                    distinct("service.id")
                }
            }

            def resultsservices=[]
            services.each{
                resultsservices <<"services_${it}"
            }
            def jsonData = [marches: resultsmarches,marchesEcriture:resultsmarchesEcriture,produits:resultsproduits,categorieproduits:resultsCategorieproduits,magazins:resultsmagazins,services:resultsservices,isEmpty:false]
            render jsonData as JSON
        }
        else {
            render false
        }

    }

    def renderList() {
        def userType=params.userType?:"public"
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        if (params?.format && params.format != "html") {
            params.max = Utilisateur.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Utilisateurs".uniquify(".${params.extension}"))


            List fields = [ "lastName","firstName","mobilePhone","gender","email","country","town","entreprise","activite","dateOfBirth","reseauPrincipal","role"]
            Map labels = ["reseauPrincipal": "Réseau principal","entreprise":"Entreprise","dateOfBirth":"Date de naissance","gender":"Sexe","lastName":"Nom","country":"Pays", "firstName": "Prénom(s)", "mobilePhone": "Mobile","role":"Role","town":"Ville"]

            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }
            Map formatters = [login:humanCase,lastName:humanCase,firstName:humanCase]

            Map parameters = [title: "Contacts", "column.widths": [0.22, 0.15, 0.10, 0.10, 0.05,0.05,0.05,0.05,0.18,0.05],"pdf.orientation":"paysage"]
            exportService.export(params.format, response.outputStream, Utilisateur.list(params), fields, labels,formatters,parameters)
        }

        def newParams
        if (userType=='public') {
            newParams=params+[ 'filter.op.role' : 'InList',filter:['op.role':'InList', op:[role:'InList'], 'role':['anonyme','enqueteur','public']]]
        }else {
            newParams=params+[ 'filter.op.role' : 'Equal',filter:['op.role':'Equal', op:[role:'Equal'], 'role':userType]]
        }
        session.userType=userType
        def reseauList=Reseau.findAllByEstReseauAndActive(true,true)

        if (params.isFullHtml) {
            render view:"list" ,model:[utilisateurInstanceList: filterPaneService.filter(newParams, Utilisateur).sort{it.reseauPrincipal},reseauList:reseauList,ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()), utilisateurInstanceTotal: filterPaneService.count(newParams, Utilisateur), filterParams: FilterPaneUtils.extractFilterParams(newParams), params: newParams,isFirstLoad:true,suffixe:getSuffixe(userType),userType:userType]

        }else {
            render template: "list", model: [utilisateurInstanceList: filterPaneService.filter(newParams, Utilisateur).sort {
                it.reseauPrincipal}, reseauList: reseauList, ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()), utilisateurInstanceTotal: filterPaneService.count(newParams, Utilisateur), filterParams: FilterPaneUtils.extractFilterParams(newParams), params: newParams, isFirstLoad: true, suffixe: getSuffixe(userType), userType: userType]
        }
    }

    def renderListWithReseau() {
        def userType=params.userType?:"public"
        String reseauId=params.reseauId?:"1"
        params.max = Math.min(params.max ? params.int('max') : 20, 100)

        def utilisateurInstanceTotal=Utilisateur.createCriteria().list {
            eq('role',userType)
            and {
                eq('reseauPrincipal.id',new Long(reseauId?:"1"))
            }
        }?.size()
          def utilisateurInstanceList=Utilisateur.createCriteria().list(max: params.max) {
              eq('role',userType)
                 and {
                     eq('reseauPrincipal.id',new Long(reseauId?:"1"))
                 }
          }
        // println "reseau : ${Reseau.get(new Long(reseauId))} utilisateurInstanceTotal:${utilisateurInstanceTotal}"
        session.userType=userType
        def reseauList=Reseau.findAllByEstReseauAndActive(true,true)
        render template:"tableau" ,model:[utilisateurInstanceList: utilisateurInstanceList,reseauList:reseauList,ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()), utilisateurInstanceTotal: utilisateurInstanceTotal, suffixe:getSuffixe(userType),userType:userType]

    }

    def list() {
            renderList()

    }
    def renderListPaginate() {
        def userType=params.userType?:"public"
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        if (params?.format && params.format != "html") {
            params.max = Utilisateur.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Utilisateurs".uniquify(".${params.extension}"))


            List fields = [ "lastName","firstName","mobilePhone","gender","email","country","town","entreprise","reseauPrincipal","role"]
            Map labels = ["reseauPrincipal": "Réseau principal","gender":"Sexe","lastName":"Nom","country":"Pays", "firstName": "Prénom(s)", "mobilePhone": "Mobile","role":"Role","town":"Ville"]

            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }
            Map formatters = [login:humanCase,lastName:humanCase,firstName:humanCase]

            Map parameters = [title: "Contacts", "column.widths": [0.22, 0.15, 0.10, 0.10, 0.05,0.05,0.05,0.05,0.18,0.05],"pdf.orientation":"paysage"]
            exportService.export(params.format, response.outputStream, Utilisateur.list(params), fields, labels,formatters,parameters)
        }

        def newParams
        if (userType=='public') {
            newParams=params+[ 'filter.op.role' : 'InList',filter:['op.role':'InList', op:[role:'InList'], 'role':['anonyme','public']]]
        }else {
            newParams=params+[ 'filter.op.role' : 'Equal',filter:['op.role':'Equal', op:[role:'Equal'], 'role':userType]]
        }
        session.userType=userType
        def reseauList=Reseau.findAllByEstReseauAndActive(true,true)

        render template:"list" ,model:[utilisateurInstanceList: filterPaneService.filter(newParams, Utilisateur).sort{it.reseauPrincipal},reseauList:reseauList,ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()), utilisateurInstanceTotal: filterPaneService.count(newParams, Utilisateur), filterParams: FilterPaneUtils.extractFilterParams(newParams), params: newParams,isFirstLoad:false,suffixe:getSuffixe(userType),userType:userType]

    }
    def listPaginate() {
        renderListPaginate()
    }

    def export = {attrs ->

        def response = attrs.response
        List fields = [ "mobilePhone","lastName","firstName","reseaux","role"]
        Map labels = ["reseaux": "RESEAUX","lastName":"NOM", "firstName": "PRENOM(S)", "mobilePhone": "N° MOBILE","role":"PROFILE"]

        def humanCase = {
            domain, value ->
                return value?.humanify()
        }
        Map formatters = [login:humanCase,lastName:humanCase,firstName:humanCase]

        Map parameters = [title: "LISTE DES UTILISATEURS", "column.widths": [0.15, 0.15, 0.25, 0.25, 0.1],"pdf.orientation":"portrait"]

        exportService.export(attrs.format, response.outputStream, attrs.exportList, fields, labels,formatters,parameters)
    }

    def filter = {

        def userType=session.userType?:"public"
        if (!params.max) params.max = 10

        if (params.filter) {
            params.filter.op.role = "Equal"
            params.filter.role = userType
        }

        def utilisateurInstanceList= filterPaneService.filter(params, Utilisateur)
        def utilisateurInstanceTotal= filterPaneService.count(params, Utilisateur)
        if(params?.format && params.format != "html" && session.filterParams)
        {
            def exportList = filterPaneService.filter( session.filterParams?:params, Utilisateur )

            export(response: response, extension: params.extension, format: params.format, exportList: exportList)
        }
        session.filterParams = params
        def reseauList=Reseau.findAllByEstReseauAndActive(true,true)
        render( template:'list',
                model: [utilisateurInstanceList: utilisateurInstanceList,reseauList:reseauList,ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()),
                        utilisateurInstanceTotal: utilisateurInstanceTotal,
                        filterParams: params,
                        params: params,suffixe:getSuffixe(userType),userType:userType])


    }
    def imprimerPdf() {
    }

    @Transactional
    def create() {
        def user=myUtilityService.getCurrent()
            def roleList=['enqueteur','public','fournisseur','admin']
        if (user?.isSuperviseur) {
                roleList=['enqueteur','public','fournisseur' ,'admin','superviseur']
        }

        if ((Environment.current == Environment.DEVELOPMENT)||(Environment.current.name == 'devmali' )||(Environment.current.name == 'demo' )) {
            roleList=['enqueteur','public','fournisseur' ,'admin','superviseur','superAdmin']
        }
        List<Marche> MarcheList
        List<Produit> ProduitList
        List<Magazin> MagazinList
        List<Service> ServicesList
        ProduitList=Produit.createCriteria().list() {
            eq('actif',true)
            order("nom", "asc")
        } as List<Produit>
        MarcheList=Marche.createCriteria().list() {
            eq('actif',true)
            order("nom", "asc")
        } as List<Marche>
        MagazinList=Magazin.createCriteria().list() {
            eq('actif',true)
            order("nom", "asc")
        } as List<Magazin>
        ServicesList=Service.createCriteria().list() {
            eq('actif',true)
            order("nom", "asc")
        } as List<Service>
        def userType=params.userType?:"public"

        switch (request.method) {
            case 'GET':
                def utilisateurInstance = new Utilisateur(params)
                utilisateurInstance.role=userType
                utilisateurInstance.enabled=true
                utilisateurInstance.accountExpired=false
                utilisateurInstance.enabled=true
                utilisateurInstance.accountLocked=false
                utilisateurInstance.passwordExpired=false
                utilisateurInstance.country=grailsApplication.config.grails.defaultCountry
                String defCurrency=grailsApplication.config.DefaultCurrency
                utilisateurInstance.devise=Currency.getInstance(defCurrency)

                render template:'create', model:[utilisateurInstance: utilisateurInstance,theUserId:0,roleList:roleList,isCreation:true,suffixe:getSuffixe(userType),MarcheList:MarcheList,ProduitList:ProduitList,MagazinList:MagazinList,ServicesList:ServicesList,
                 userType:userType,ctnerTemplateProd:myFormHelperService.getCheckBoxTemplate(ProduitList?.size()),ctnerTemplate:myFormHelperService.getCheckBoxTemplate(MarcheList?.size()),ctnerTemplateMag:myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),
                 ctnerTemplateService:myFormHelperService.getCheckBoxTemplate(ServicesList?.size())]
            break
            case 'POST':
                def cleanParams=[:]
                params.each {
                    if (!it.key.startsWith("photo"))
                        cleanParams=cleanParams+["${it.key}":it.value]
                }
                def utilisateurInstance = new Utilisateur(params)
                if (!utilisateurInstance.validate()) {
                    renderCreate(utilisateurInstance, roleList, MarcheList, ProduitList, MagazinList,ServicesList, userType)

                }

                def newparams = update_data(cleanParams,utilisateurInstance) as Map

               def userExist=Utilisateur.findByMobilePhone(newparams.mobilePhone?.toString()?.getRightPhone())
                if (!userExist) {
                    if (params.confirmPassword) {
                        utilisateurInstance.password=params.confirmPassword.encodeAsSHA1()
                    }
                    userExist=userService.createAndSave(newparams)
               if (!userExist) {
                   flash.message ="user not created ${userExist}"
                   renderCreate(userExist, roleList, MarcheList, ProduitList, MagazinList,ServicesList, userType)


                }
                flash.message = message(code: 'default.created.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), userExist?.toString()])
                    def result=[id:userExist.id]
                    render result as JSON
                break
                }else
                {
                    flash.message = message(code: 'userExist.message', args: [userExist.toString(),params.mobilePhone?.toString()?.getRightPhone()])
                    renderCreate(userExist, roleList, MarcheList, ProduitList, MagazinList,ServicesList, userType)
                }
        }
    }
    protected renderCreate(Utilisateur utilisateurInstance, ArrayList<String> roleList, List<Marche> MarcheList, List<Produit> ProduitList, List<Magazin>  MagazinList,  List<Service> ServicesList, String userType) {
        render template: 'create', model: [utilisateurInstance: utilisateurInstance, roleList: roleList, MarcheList: MarcheList, ProduitList: ProduitList, MagazinList: MagazinList, ServicesList:ServicesList,isCreation: true
                                           , ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()), ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()),
                                           ctnerTemplateMag   : myFormHelperService.getCheckBoxTemplate(MagazinList?.size()), suffixe: getSuffixe(userType), userType: userType]
    }

    def getSuffixe(def userType) {
        switch(userType) {

            case "public":
               "utilisateur"
                break
            case "admin":
                "administrateur"
                break
            case "enqueteur":
                "enqueteur"
                break
            case "superviseur":
                "superviseur"
                break
            case "fournisseur":
                "fournisseur"
                break
             default:
                "utilisateur"
        }
    }
    def imagePage = {
        def utilisateurInstance = Utilisateur.get(params.id)
        def img=utilisateurInstance?.photo // a byte[], File or InputStream
        render(file: img, contentType: 'image/png')
    }
    @Transactional
    def editProfile() {
        try {
            Boolean isChange = params.isChange=="true"
            def user = myUtilityService.getCurrent()
            user?.attach()
            def roleList = ['anonyme', 'public', 'enqueteur']
            if (user?.isSuperviseur) {
                roleList = ['anonyme', 'enqueteur', 'decideur', 'fournisseur', 'public', 'admin', 'superviseur']
            } else if (user?.isAdmin) {
                roleList = ['anonyme', 'enqueteur', 'decideur', 'fournisseur', 'public', 'admin']
            }
//            if ((Environment.current == Environment.DEVELOPMENT)||(Environment.current.name == 'devmali' )) {
//                roleList = ['anonyme', 'enqueteur', 'decideur', 'fournisseur', 'public', 'admin', 'grandAdmin', 'superviseur', 'superAdmin']
//            }
            List<Marche> MarcheList
            List<Produit> ProduitList
            List<Magazin> MagazinList
            List<Service> ServicesList
            ProduitList=Produit.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Produit>
            MarcheList=Marche.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Marche>
            MagazinList=Magazin.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Magazin>
            ServicesList=Service.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Service>
            def userType = params.userType ?: "public"

            def pagesAv =PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
            def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
            def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)
            def pageAccueilInstance=PageAccueil.findByEstPrincipal(true)


            def photoUrl
            def photoUrlValue
                      switch (request.method) {
                case 'GET':
                    def utilisateurInstance
                    if (isChange) {
                        def userId = myUtilityService?.getPrincipal()
                        utilisateurInstance = Utilisateur.findById(userId)
                    } else {
                        utilisateurInstance = Utilisateur.get(params.id)
                    }


                    try {

                        photoUrlValue = "${createLink(controller: 'utilisateur', action: 'imagePage',params:[id:utilisateurInstance?.id])}"

                    }
                    catch (FileNotFoundException fnf) {
                        println "fichier non trouvé ${fnf}"
                        photoUrlValue = ""
                    }
                    if ((photoUrlValue?.contains("/null")) || (photoUrlValue?.contains(".null"))) {
                        photoUrl = ""
                    } else {
                        photoUrl = photoUrlValue
                    }


                    render view: 'editProfile', model: [utilisateurInstance: utilisateurInstance,user:user,activeMenu:'Profil',pageAccueilInstance: pageAccueilInstance,
                                                                                                 pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,searchUpdate: "documents",ProduitList:ProduitList,MagazinList: MagazinList,ServicesList:ServicesList,
                                                                                                 ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()), ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()), ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),ReseauxIds: "",
                                                                                                 roleList: roleList, MarcheList: MarcheList, isCreation: false, isChange: isChange, suffixe: getSuffixe(userType), userType: userType, photoUrl: photoUrl]
                     break
                case 'POST':
                    def cleanParams = [:]
                    params.each {
                        if (!it.key.startsWith("photo"))
                            cleanParams = cleanParams + ["${it.key}": it.value]
                    }
                    myUtilityService?.getCurrent()?.discard()
                    def utilisateurInstance
                    if (isChange) {
                        def userId = myUtilityService?.getPrincipal()
                        utilisateurInstance = Utilisateur.findById(userId)
                    } else {
                        utilisateurInstance = Utilisateur.get(params.id)
                    }


                    try {

                        photoUrlValue = "${createLink(controller: 'utilisateur', action: 'imagePage',params:[id:utilisateurInstance?.id])}"

                    }
                    catch (FileNotFoundException fnf) {
                        println "fichier non trouvé ${fnf}"
                        photoUrlValue = ""
                    }
                    if ((photoUrlValue?.contains("/null")) || (photoUrlValue?.contains(".null"))) {
                        photoUrl = ""
                    } else {
                        photoUrl = photoUrlValue
                    }


                    isChange = params.isChange=="true"
                    def newparams = update_data(cleanParams, utilisateurInstance) as Map
                    ProduitList=Produit.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Produit>
                    MarcheList=Marche.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Marche>
                    MagazinList=Magazin.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Magazin>
                    ServicesList=Service.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Service>
                    def isChangingPassword = params.changePassword
                    def motdepasseOk = false
                    if (isChangingPassword) {
                        motdepasseOk = (newparams.passwordtyped == newparams.confirmPassword)


                        if (!motdepasseOk) {
                            flash.messageErreur = message(code: 'nonConcordanceMotdepasse.text')
                            render view: 'editProfile', model: [utilisateurInstance: utilisateurInstance,user:user,activeMenu:'Profil',pageAccueilInstance: pageAccueilInstance,
                                                                ProduitList:ProduitList,MagazinList: MagazinList,
                                                                 pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,searchUpdate: "documents",
                                                                ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()), ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()), ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),
                                                                ReseauxIds: "",
                                                                 roleList: roleList, MarcheList: MarcheList, isCreation: false, isChange: isChange, suffixe: getSuffixe(userType), userType: userType, photoUrl: photoUrl]


                        }
                    }


                    if (!utilisateurInstance) {
                        flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), newparams.id])
                        renderList()
                    }
                    if (!userService.update(newparams, utilisateurInstance)) {
                        render view: 'editProfile', model: [utilisateurInstance: utilisateurInstance,user:user,activeMenu:'Profil',pageAccueilInstance: pageAccueilInstance,ProduitList:ProduitList,MagazinList: MagazinList,
                                                             pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,searchUpdate: "documents",
                                                            ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()), ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()), ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),
                                                            roleList: roleList, MarcheList: MarcheList, isCreation: false, isChange: isChange, suffixe: getSuffixe(userType), userType: userType, photoUrl: photoUrl]

                    }
                    if (isChangingPassword && motdepasseOk) {
                        def userchangePwd = Utilisateur.findByMobilePhone(params.mobilePhone)
                        userchangePwd.password = newparams.confirmPassword
                        userchangePwd.encodePassword()
                        userchangePwd.save(flush: true)
                        def toPhoneNumber = userchangePwd.mobilePhone
                        def messagesms = """
                      Votre nouveau mot de passe : ${newparams.confirmPassword}
                       """
                        if (Environment.current == Environment.PRODUCTION) {
                            sendSMSService?.execute(messagesms, toPhoneNumber)
                            if (userchangePwd.email) {
                                try {
                                    mailService?.sendMail {
                                       
                                        to "${userchangePwd.email}"
                                        subject " ${grailsApplication.config.nomCourtPlateforme} : Changement de mot de passe"
                                        body """Bonjour ${utilisateur.login}
                                        Vous avez changé votre mot de passe
                                        Voici vos nouveaux paramètres de connexion :
                                        Identifiant : ${userchangePwd.mobilePhone}
                                        mot de passe : ${newparams.confirmPassword}
                                    """
                                    }
                                    flash.password_changed = """ Vos nouveaux paramètres ont été envoyés à l'adresse ${
                                        userchangePwd.email
                                    }
                                                                Veuillez consulter votre dossier spam si vous ne retrouvez pas le mail
                                                                Vous les recevrez aussi par SMS au numéro ${
                                        userchangePwd.mobilePhone
                                    }
                                                                """
                                } catch (Exception e) {
                                    log.error " exception : ${e}"
                                }
                            }
                        } else {
                            println "messagesms ${messagesms}"
                        }
                    }

                    flash.message = message(code: 'default.updated.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), utilisateurInstance.toString()])

                    try {

                        photoUrlValue = "${createLink(controller: 'utilisateur', action: 'imagePage',params:[id:utilisateurInstance?.id])}"

                    }
                    catch (FileNotFoundException fnf) {
                        println "fichier non trouvé ${fnf}"
                        photoUrlValue = ""
                    }
                    if ((photoUrlValue?.contains("/null")) || (photoUrlValue?.contains(".null"))) {
                        photoUrl = ""
                    } else {
                        photoUrl = photoUrlValue
                    }
//                    render result as JSON
                    render template: 'show', model: [utilisateurInstance: utilisateurInstance, ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()),
                                                     ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()), ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),
                                    roleList: roleList, MarcheList: MarcheList,ProduitList:ProduitList,MagazinList: MagazinList, isCreation: false,
                                                     isChange: isChange, suffixe: getSuffixe(userType), userType: userType, photoUrl: photoUrl]


            }
        }
        catch (MissingPropertyException e){
            println "error : ${e}"
            redirect controller: 'home', action: 'accueil'
        }
    }
    @Transactional
    def edit() {
        try {
            Boolean isChange = params.isChange=="true"
            def user = myUtilityService.getCurrent()
            user?.attach()
            def roleList = ['anonyme',  'public']
            if (user?.isOnlySuperviseur) {
                roleList = ['anonyme', 'enqueteur', 'fournisseur', 'public', 'admin', 'superviseur']
            } else if (user?.isAdmin) {
                roleList = ['anonyme', 'enqueteur',  'fournisseur', 'public', 'admin']
            }
            if (Environment.current == Environment.DEVELOPMENT) {
                roleList = ['anonyme', 'enqueteur',  'fournisseur', 'public', 'admin',  'superviseur', 'superAdmin']
            }

            def userType = params.userType ?: "public"
            List<Marche> MarcheList
            List<Produit> ProduitList
            List<Magazin> MagazinList
            List<Service> ServicesList
            ProduitList=Produit.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Produit>
            MarcheList=Marche.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Marche>
            MagazinList=Magazin.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Magazin>
            ServicesList=Service.createCriteria().list() {
                eq('actif',true)
                order("nom", "asc")
            } as List<Service>
            def photoUrl
            def photoUrlValue
            def utilisateurInstance
            if (isChange) {
                def userId = myUtilityService?.getPrincipal()
                utilisateurInstance = Utilisateur.findById(userId)
            } else {
                utilisateurInstance = Utilisateur.get(params.id)
            }

            try {

                photoUrlValue = "${createLink(controller: 'utilisateur', action: 'imagePage',params:[id:utilisateurInstance?.id])}"

            }
            catch (FileNotFoundException fnf) {
                println "fichier non trouvé ${fnf}"
                photoUrlValue = ""
            }
            if ((photoUrlValue?.contains("/null")) || (photoUrlValue?.contains(".null"))) {
                photoUrl = ""
            } else {
                photoUrl = photoUrlValue
            }
            switch (request.method) {
                case 'GET':


                    render template: 'edit', model: [utilisateurInstance: utilisateurInstance, ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()), ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()),
                                                     ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()), ReseauxIds: "", roleList: roleList, MarcheList: MarcheList,ProduitList:ProduitList,MagazinList: MagazinList, ServicesList:ServicesList,isCreation: false, isChange: isChange, suffixe: getSuffixe(userType),
                                                     userType: userType, photoUrl: photoUrl]
                    break
                case 'POST':
                    def cleanParams = [:]
                    params.each {
                        if (!it.key.startsWith("photo"))
                            cleanParams = cleanParams + ["${it.key}": it.value]
                    }
                    myUtilityService?.getCurrent()?.discard()
                    ProduitList=Produit.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Produit>
                    MarcheList=Marche.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Marche>
                    MagazinList=Magazin.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Magazin>
                    ServicesList=Service.createCriteria().list() {
                        eq('actif',true)
                        order("nom", "asc")
                    } as List<Service>
                    if (isChange) {
                        def userId = myUtilityService?.getPrincipal()
                        utilisateurInstance = Utilisateur.findById(userId)
                    } else {
                        utilisateurInstance = Utilisateur.get(params.id)
                    }

                    try {

                        photoUrlValue = "${createLink(controller: 'utilisateur', action: 'imagePage',params:[id:utilisateurInstance?.id])}"

                    }
                    catch (FileNotFoundException fnf) {
                        println "fichier non trouvé ${fnf}"
                        photoUrlValue = ""
                    }
                    if ((photoUrlValue?.contains("/null")) || (photoUrlValue?.contains(".null"))) {
                        photoUrl = ""
                    } else {
                        photoUrl = photoUrlValue
                    }
                    def newparams = update_data(cleanParams, utilisateurInstance) as Map

                    def isChangingPassword = params.changePassword
                    def motdepasseOk = false
                    if (isChangingPassword) {
                        motdepasseOk = (newparams.passwordtyped == newparams.confirmPassword)


                        if (!motdepasseOk) {
                            flash.messageErreur = message(code: 'nonConcordanceMotdepasse.text')
                            render template: 'edit', model: [utilisateurInstance: utilisateurInstance, ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()), ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()),
                                                             ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),
                                                             ReseauxIds: "",
                                                             roleList: roleList, MarcheList: MarcheList,ProduitList:ProduitList,MagazinList: MagazinList, isCreation: false, isChange: isChange, suffixe: getSuffixe(userType),
                                                             userType: userType, photoUrl: photoUrl]

                        }
                    }


                    if (!utilisateurInstance) {
                        flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), newparams.id])
                        renderList()
                    }
                    if (!userService.update(newparams, utilisateurInstance)) {
                        render template: 'edit', model: [utilisateurInstance: utilisateurInstance, roleList: roleList, ReseauxIds: utilisateurInstance.reseaux*.id, MarcheList: MarcheList, isCreation: false, isChange: isChange, suffixe: getSuffixe(userType)]
              
                    }
                    if (isChangingPassword && motdepasseOk) {
                        def userchangePwd = Utilisateur.findByMobilePhone(params.mobilePhone)
                        userchangePwd.password = newparams.confirmPassword
                        userchangePwd.encodePassword()
                        userchangePwd.save(flush: true)
                        def toPhoneNumber = userchangePwd.mobilePhone
                        def messagesms = """
                      Votre nouveau mot de passe : ${newparams.confirmPassword}
                       """
                        if (Environment.current == Environment.PRODUCTION) {
                            sendSMSService?.execute(messagesms, toPhoneNumber)
                            if (userchangePwd.email) {
                                try {
                                    mailService?.sendMail {
                                       
                                        to "${userchangePwd.email}"
                                        subject " ${grailsApplication.config.nomCourtPlateforme} : Changement de mot de passe"
                                        body """Bonjour ${utilisateur.login}
                                        Vous avez changé votre mot de passe
                                        Voici vos nouveaux paramètres de connexion :
                                        Identifiant : ${userchangePwd.mobilePhone}
                                        mot de passe : ${newparams.confirmPassword}
                                    """
                                    }
                                    flash.password_changed = """ Vos nouveaux paramètres ont été envoyés à l'adresse ${
                                        userchangePwd.email
                                    }
                                                                Veuillez consulter votre dossier spam si vous ne retrouvez pas le mail
                                                                Vous les recevrez aussi par SMS au numéro ${
                                        userchangePwd.mobilePhone
                                    }
                                                                """
                                } catch (Exception e) {
                                    log.error " exception : ${e}"
                                }
                            }
                        } else {
                            println "messagesms ${messagesms}"
                        }
                    }

                    flash.message = message(code: 'default.updated.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), utilisateurInstance.toString()])
                    render template: 'show', model: [utilisateurInstance: utilisateurInstance, ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()), ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()),
                                                     ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),
                                                     ReseauxIds: "",
                                                     roleList: roleList, MarcheList: MarcheList,ProduitList:ProduitList,MagazinList: MagazinList, isCreation: false, isChange: isChange, suffixe: getSuffixe(userType),
                                                     userType: userType, photoUrl: photoUrl]


            }
        }
        catch (MissingPropertyException e){
            println "error : ${e}"
            redirect controller: 'home', action: 'index'
        }
    }

    def update_data(cleanParams,Utilisateur utilisateurInstance) {
        def marketIds = []
        def marchesEcritureIds = []
        def produitsIds = []
        def magazinIds = []
        def categoriesIds=[]
        def regionsIds=[]
//        def file
       // CommonsMultipartFile photo
        File file
        def Extention
        def servicesIds=[]
        def uploadedFile
        cleanParams.each {
            if (it.key.startsWith("markets_"))
                marketIds << (it.key - "markets_")

        }
        cleanParams.each {
            if (it.key.startsWith("marchesEcriture_"))
                marchesEcritureIds << (it.key - "marchesEcriture_")

        }
        cleanParams.each {
            if (it.key.startsWith("categories_"))
                categoriesIds << (it.key - "categories_")

        }
        cleanParams.each {
            if (it.key.startsWith("regions_"))
                regionsIds << (it.key - "regions_")

        }
        cleanParams.each {
            if (it.key.startsWith("produits_"))
                produitsIds << (it.key - "produits_")

        }
        cleanParams.each {
            if (it.key.startsWith("magazins_"))
                magazinIds << (it.key - "magazins_")

        }
        cleanParams.each {
            if (it.key.startsWith("services_"))
                servicesIds << (it.key - "services_")

        }
        def avatar=""


        if (!params.photo?.isEmpty()) {
            MultipartFile photo = params.photo
            if (photo?.getBytes()?.size() > 0) {
                utilisateurInstance.photo = photo.getBytes()
            }
           }

        def paramswithithoutpwd= [:] << params
        paramswithithoutpwd.remove('password')
        return (paramswithithoutpwd+ ['markets': marketIds] +  ['marchesEcriture': marchesEcritureIds]+['produits': produitsIds] + ['magazins': magazinIds]+ ['services': servicesIds]  + ['ReseauxIds': params.groupsId]+[categories:categoriesIds]+[avatar:avatar]+[regions:regionsIds])
    }


    def normaliserPhones(){
       Utilisateur.normaliserMobilePhones()
       renderList()
   }
    def normaliserReseauPrincipal(){
        Utilisateur.normaliser_reseau_principal()
        renderList()
    }
    def createMulti(){
        redirect controller:'errors', action:'notYetDevelopped'
    }
    def show() {
        def userType = params.userType ?: "public"
        def utilisateurInstance = Utilisateur.get(params.id)
        List<Marche> MarcheList
        List<Produit> ProduitList
        List<Magazin> MagazinList
        List<Service> ServicesList
        ProduitList = Produit.createCriteria().list() {
            eq('actif', true)
            order("nom", "asc")
        } as List<Produit>
        MarcheList = Marche.createCriteria().list() {
            eq('actif', true)
            order("nom", "asc")
        } as List<Marche>
        MagazinList = Magazin.createCriteria().list() {
            eq('actif', true)
            order("nom", "asc")
        } as List<Magazin>
        ServicesList = Service.createCriteria().list() {
            eq('actif', true)
            order("nom", "asc")
        } as List<Service>

        switch (request.method) {
            case 'GET':
                def photoUrl
                def photoUrlValue

                try {

                    photoUrlValue = "${createLink(controller: 'utilisateur', action: 'imagePage', params: [id: utilisateurInstance?.id])}"

                }
                catch (FileNotFoundException fnf) {
                    println "fichier non trouvé ${fnf}"
                    photoUrlValue = ""
                }
                if ((photoUrlValue?.contains("/null")) || (photoUrlValue?.contains(".null"))) {
                    photoUrl = ""
                } else {
                    photoUrl = photoUrlValue
                }
                if (!utilisateurInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), params.id])
                    renderList()
                }
                render view: 'show', model: [utilisateurInstance: utilisateurInstance, ctnerTemplateService: myFormHelperService.getCheckBoxTemplate(ServicesList?.size()), ctnerTemplateProd: myFormHelperService.getCheckBoxTemplate(ProduitList?.size()), ctnerTemplate: myFormHelperService.getCheckBoxTemplate(MarcheList?.size()),
                                             ctnerTemplateMag: myFormHelperService.getCheckBoxTemplate(MagazinList?.size()),
                                             ReseauxIds      : "", MarcheList: MarcheList, ProduitList: ProduitList, MagazinList: MagazinList, isCreation: false,suffixe: getSuffixe(userType),
                                             userType        : userType, photoUrl: photoUrl]

        }
    }
    def showParametres() {
        def utilisateurInstance = Utilisateur.get(params.id)
        def photo=utilisateurInstance?.photo?.amazonFile
        def photoUrl=photo?.url()
        if (!utilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), params.id])
            renderList()
        }

        [utilisateurInstance: utilisateurInstance,photoUrl:photoUrl]
    }
    def showConnected() {
            def user=myUtilityService.getCurrent()
            redirect action:'showParametres',id:user.id


    }
    def viewImage = {

            def user=Utilisateur.get(params.id)
             def photo=user?.photo?.amazonFile
             def statut=photo?.status
             def photoUrl=photo?.url()
             [photoUrl:photoUrl]
    }


    def delete() {
        def utilisateurInstance = Utilisateur.get(params.id)
        if (!utilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), params.id])
            renderList()
        }

        try {
            utilisateurInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'utilisateur.label', default: 'Utilisateur'), params.id, e.message])
            render template: 'show', model:[utilisateurInstance: utilisateurInstance]
        }
    }
}

/*


You can see how the fields are now set explicitly. You can use labels to customize the output of the fields e.g. for i18n. Using formatters it is possible to customize how the resulting values are generated which can be used to specify a particular date format etc. by just mapping an attribute name to a closure. It is also possible to specify nonexistant fields and add formatters for those fields to produce static content like today's date.

Version 0.7 changes formatter closures. To upgrade from a previous release you need to add the domain argument to your closures as shown in the code sample below.

With the new formatter closure you can combine multiple attributes:

// Formatter closure
def title = { domain, value ->
    return domain?.author + ": " + domain?.title
}

PDF export supports some additonal parameters which can be used just like the title attribute in the code sample above. The following parameters are supported:

pdf.encoding (specifies font encoding, defaults to "Cp1252" (=latin 1), allowed values: "Cp1250", "Cp1252" (=latin 2), "Cp1257", "Identity-H", "Identity-V", "MacRoman") see http://itextdocs.lowagie.com/tutorial/fonts/index.php for further information about encodings
        title.encoding (same as pdf.encoding but for title font)
header.encoding (same as pdf.encoding but for header font)
text.encoding (same as pdf.encoding but for text font)
title.font.size (determines title font size, defaults to "10",allowed values: a number as String)
header.font.size (determines header font size, defaults to "10", allowed values: a number as String)
text.font.size (determines text font size, defaults to "10", allowed values: a number as String)
font.family (determines global font family, allowed values: constants defined in http://www.1t3xt.info/api/com/lowagie/text/FontFactory.html)
        title.font.family (determines title font family, defaults to com.lowagie.text.FontFactory.HELVETICA, allowed values: constants defined in http://www.1t3xt.info/api/com/lowagie/text/FontFactory.html)
                header.font.family (determines header font family, defaults to com.lowagie.text.FontFactory.HELVETICA, allowed values: constants defined in http://www.1t3xt.info/api/com/lowagie/text/FontFactory.html)
                        text.font.family (determines text font family, defaults to com.lowagie.text.FontFactory.HELVETICA, allowed values: constants defined in http://www.1t3xt.info/api/com/lowagie/text/FontFactory.html)
                                title.font.style (determines title font style, defaults to "bold", allowed values: "bold", "italic", "normal", "bolditalic")
                                header.font.style (determines header font style, defaults to "bold", allowed values: "bold", "italic", "normal", "bolditalic")
                                text.font.style (determines text font style, defaults to "normal", allowed values: "bold", "italic", "normal", "bolditalic")
                                border.color (determines table border color, defaults to: new Color(163, 163, 163), allowed values: a java.awt.Color object e.g. Color.RED)
                                separator.color (determines table row separator color, defaults to: new Color(238, 238, 238), allowed values: a java.awt.Color object e.g. new Color(100, 100, 100))
                                column.widths (specifies column widths in percent, defaults to equal size for all columns, allowed values: List of floats e.g.

[0.2, 0.3, 0.5]

for three columns)
header.rows (number of header rows, defaults to 1, allowed values: a number as String)
header (header fields, defaults to fields, allowed values: list of list of Strings e.g.

[["Intended times"], ["Actual times", "Duration"]]

)
header.parameters (header parameters currently supported colspan, defaults to no colspan, allowed values: list of maps e.g.

[["colspan0": 3], ["colspan0": 1, "colcol-sm-1 col-md-1": 2]]

)
pdf.orientation (page orientation, defaults to landscape, allowed values: "portrait")
header.enabled (enable/disable header output, boolean true or false, defaults to true)

For chinese characters in PDF use the following parameters:

...

Map parameters = ["pdf.encoding":"UniGB-UCS2-H", "font.family": "STSong-Light"]

...

CSV export also supports some additional parameters:

encoding (encoding, defaults to JVM default encoding, allowed values: http://java.sun.com/javase/6/docs/api/java/nio/charset/Charset.html e.g. "UTF-8")
separator (specifies the field separator char, defaults to ';', allowed values: a single character)
quoteCharacter (specifies the quote character, defaults to '"', use 'u0000' for no quote character and put a slash before the four 0s, allowed values: a single character)
lineEnd (specifies the line ending, defaults to the default platform line ending, allowed values: a String e.g. "rn")
header.enabled (enable/disable header output, boolean true or false, defaults to true)

Excel

column.widths (specifies column widths in percent, defaults to equal size for all columns, allowed values: List of floats e.g.

[0.2, 0.3, 0.5]

for three columns)
header.enabled (enable/disable header output, boolean true or false, defaults to true)

RTF

supports mainly the same parameters as PDF
rtf.encoding (specifies font encoding, defaults to "Cp1252" (=latin 1), allowed values: "Cp1250", "Cp1252" (=latin 2), "Cp1257", "Identity-H", "Identity-V", "MacRoman") see http://itextdocs.lowagie.com/tutorial/fonts/index.php for further information about encodings
title.encoding (same as rtf.encoding but for title font)
header.encoding (same as rtf.encoding but for header font)
text.encoding (same as rtf.encoding but for text font)
header.enabled (enable/disable header output, boolean true or false, defaults to true)

XML

encoding (encoding, defaults to JVM default encoding, allowed values: http://java.sun.com/javase/6/docs/api/java/nio/charset/Charset.html e.g. "UTF-8")
xml.root (specify root element name, defaults to object class name, a String)
depth (depth (as integer) for building tree affects how collections and relationships are exported, defaults to 1, 1 means only direct domain attributes 2 or more collection attributes as well)
*/
