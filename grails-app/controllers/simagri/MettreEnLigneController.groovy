package simagri

import grails.converters.JSON
import org.joda.time.DateTime
import org.springframework.web.multipart.MultipartFile

class MettreEnLigneController {
def myUtilityService
def myFormHelperService
def userImportService
def productImportService
def marketImportService
def exportService
def filterPaneService
def priceCreateService
def marcheService
    def index() {
        redirect action:'show'
    }
    def show() {
        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        def user=myUtilityService.getCurrent()

        render view: "index", model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),user:user,activeMenu:'MiseEnLigne',
                                       pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,searchUpdate: "documents"]

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
    def renderUtilisateurList() {
        def userType=params.userType?:"public"
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        if (params?.format && params.format != "html") {
            params.max = Utilisateur.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Utilisateurs".uniquify(".${params.extension}"))


            List fields = [ "mobilePhone","lastName","firstName","reseaux","role"]
            Map labels = ["reseaux": "RESEAUX","lastName":"NOM", "firstName": "PRENOM(S)", "mobilePhone": "N° MOBILE","role":"PROFILE"]

            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }
            Map formatters = [login:humanCase,lastName:humanCase,firstName:humanCase]

            Map parameters = [title: "LISTE DES UTILISATEURS", "column.widths": [0.15, 0.15, 0.25, 0.25, 0.1],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Utilisateur.list(params), fields, labels,formatters,parameters)
        }

        def newParams=params
        if (userType=='public') {
            newParams=params+[ 'filter.op.role' : 'InList',filter:['op.role':'InList', op:[role:'InList'], 'role':['anonyme','enqueteur','decideur','public']]]
        }else {
            newParams=params+[ 'filter.op.role' : 'Equal',filter:['op.role':'Equal', op:[role:'Equal'], 'role':userType]]
        }
        session.userType=userType
        render template:"/utilisateur/list" ,model:[utilisateurInstanceList: filterPaneService.filter(newParams, Utilisateur), utilisateurInstanceTotal: filterPaneService.count(newParams, Utilisateur), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams,suffixe:getSuffixe(userType),userType:userType]

    }
    def renderMarcheList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Marche.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Marche".uniquify(".${params.extension}"))

            List fields = ["code","nom","region","location","produits"]
            Map labels = ["code":"CODE","nom":"NOM","region":"REGION", "location": "LIEUX","produits":"PRODUITS"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [code:humanCase,nom:humanCase]
            Map parameters = [title: "Marchés", "column.widths": [0.1,0.2, 0.2, 0.15, 0.35],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Marche.list(params), fields, labels,formatters,parameters)
        }
        render template:"/marche/list" ,model:[marcheInstanceList: filterPaneService.filter(params, Marche), marcheInstanceTotal: filterPaneService.count(params, Marche), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def renderProduitList() {
        params.max = 25
         render template:"/produit/list" ,model:[produitInstanceList: Produit.list(), produitInstanceTotal: Produit.count, params: params]

    }
    def savePrix() {

        def result=priceCreateService.mettreAJourPrix(params)
        render result

    }
    def  canWriteToMarket = {
        Long marketId
        if (params.marketId){
            marketId= params.marketId?.toLong()?:new Long("-1")
        }
        else
            marketId= new Long("-1")
        def user=myUtilityService.current
        render marcheService.canWriteToMarket(user,marketId)

    }
    def  populatePrix = {
        try {
            def maxRows =Math.min(params.rows ? params.int('rows') : 500, 500)
            def currentPage = Integer.valueOf(params.page?:1)

            def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
            def results
            def idTemp=-1
            def marketId
            if (params.marketId){
                marketId= params.marketId?.toLong()?:new Long("-1")
            }
            else
                marketId= new Long("-1")
            def marche=Marche.get(marketId)
            def totalRows =0

//            def prix = Prix.createCriteria().list(max: maxRows, offset: rowOffset) {
//                eq('auteur',user)
//                and {
//                    eq('marche.id',marketId)
//                }
//                and {
//                    eq('statut','EnCours')
//
//                }
//                createAlias('produit','produit')
//                order('produit.nom', 'asc')
//            }


//            def totalRows = prix?.totalCount?:0
//            if (totalRows>0){
//                results = prix?.collect {
//                    if (params.nomProduit) ilike('nomProduit', "%${params.nomProduit}%")
//
//                    [ cell: [it.id, it.produit?.nom,it.prixGros?:"",it.mesureGros?.code,it.prixDetail?:"",it.mesureDetail?.code,it.commentaire], id: it.id ]
//                }
//            }else{
            def listeProduits=marche?.mesProduits
//                if (listeProduits==[]) {
//                    listeProduits=user?.mesProduits
//                }
            def sortedProduits=listeProduits?.sort{it.nom}

            results =sortedProduits.collect{
                [ cell: [idTemp--, it.nom,"",it.mesure?.code,"",it.mesure?.code,it.commentaire], id: idTemp-- ]

            }

//            }
            totalRows = results?.size()
            def numberOfPages = Math.ceil(totalRows?:0 / maxRows as double)
            def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

            render jsonData as JSON
        }catch(Exception e){
            log.error  " exception ${e}"
            render false
        }

    }

    def showPrix() {

        def user=myUtilityService.getCurrent()
        user?.attach()
        def userMarketsList=[]
        def userMarketId=user?.mesMarches?.id?.flatten()
//        if (!userMarketId)
//            userMarketId=Marche.list().id?.flatten();
        if (userMarketId){
            userMarketsList=Marche.createCriteria().list() {
                'in' ('id',userMarketId)
                and {
                    eq('actif',true)
                }
                order("nom", "asc")
            }
        }


        def sizeOfMarketsList=userMarketsList?.size()?:0
        def lst = Mesure.findAll()


        render template:"prix" ,model:
                [userMarketsList:userMarketsList,user:user,
                 ctnerTemplate:myFormHelperService.getCheckBoxTemplate(sizeOfMarketsList)]
    }
    def showOffres() {

        def user=myUtilityService.getCurrent()
        user?.attach()

        def offreInstance = new Offre(params)
        def theDate=new DateTime()

        offreInstance.operateur=user
        offreInstance.date=theDate.toDate()
        offreInstance.dateExpiration=new Date(theDate.plusDays(15).toDate().toString())
        offreInstance.statut="En_cours"

        render template:"offres" ,model:
                [offreInstance:offreInstance]
    }
    def showStocks() {

        def user=myUtilityService.getCurrent()
        user?.attach()


        def userMagazinList=[]
        def userMagazinId=user?.magazins?.id?.flatten()
        if (!userMagazinId)
            userMagazinId=Magazin.list().id?.flatten();
        if (userMagazinId){
            userMagazinList=Magazin.createCriteria().list() {
                'in' ('id',userMagazinId)
                order("nom", "asc")
            }
        }
        def sizeOfMagazinList=userMagazinList?.size()?:0

        render template:"stocks" ,model:
                [userMagazinList:userMagazinList,
                 ctnerTemplateMag:myFormHelperService.getCheckBoxTemplate(sizeOfMagazinList)]
    }
    def showInfos() {

        def user=myUtilityService.getCurrent()
        user?.attach()

        def infoInstance= new Info(params)
        infoInstance.auteur=user
        render template:"infos" ,model:
                [user:user,infoInstance: infoInstance]
    }
    def editInfos() {
      def infoInstance= Info.get(params.id)
        render template:"infos" ,model:
                [infoInstance: infoInstance]
    }
    def showUsers() {
        render template:"importUsers" ,model:
                [ecraserDoublons:true,
                 ]
    }
    def showMarches() {
        render template:"importMarches" ,model:
                [ecraserDoublons:true
                 ]
    }
    def showProduits() {

        render template:"importProduits" ,model:
                [ecraserDoublons:true,
                 ]
    }

    def uploadProduits(){
        try {
            if (!params.myProductFile?.isEmpty()) {
                MultipartFile f = params.myProductFile
            def webRootDir = servletContext.getRealPath("/")

            def okcontents = ['excel/xls', 'excel-x/xlsx','application/vnd.ms-excel']
            if (! okcontents.contains(f.getContentType())) {
                flash.message = "Le type du fichier doit être compris dans: ${okcontents}"
                showProduits()
            }

            def fileName="produit_".uniquify(".xls")
            def dir=new File("${webRootDir}excell")//some path...
            if(!dir.exists()){
                dir.mkdirs()
            }
            def produitDir = new File("${webRootDir}excell",fileName)

            f.transferTo(produitDir)
            productImportService.importFile(produitDir.absolutePath)

            flash.message = message(code: 'reussite.importation')
            renderProduitList()
            }else {
                flash.message = 'Vous devez sélectionner un fichier'
                showProduits()
            }
        }catch (Exception e){
            flash.message = message(code: 'echec.importation')+" cause : ${e}"
            showProduits()
        }
    }
    def uploadMarches(){
        try {
            if (!params.myMarketFile?.isEmpty()) {
                MultipartFile f = params.myMarketFile
                def webRootDir = servletContext.getRealPath("/")

                def okcontents = ['excel/xls', 'excel-x/xlsx', 'application/vnd.ms-excel']
                if (!okcontents.contains(f.getContentType())) {
                    flash.message = "Le type du fichier doit être compris dans: ${okcontents}"
                    showMarches()
                }

                def fileName = "marche_".uniquify(".xls")
                def dir = new File("${webRootDir}excell")//some path...
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                def marcheDir = new File("${webRootDir}excell", fileName)

                f.transferTo(marcheDir)
                marketImportService.importFile(marcheDir.absolutePath)
                flash.message = message(code: 'reussite.importation')
                renderMarcheList()
            }else {
                    flash.message = 'Vous devez sélectionner un fichier'
                    showMarches()


            }
        }catch (Exception e){
            flash.message = message(code: 'echec.importation')+" cause : ${e}"
            showMarches()
        }
    }
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def uploadUsers() {

        def ecraserDoublons=params.ecraserDoublons?true:false

        def listeParam=params?.ReseauxIds?.tokenize(',')?.collect{it as Long}
        def idReseaux=toList(listeParam?:[])

        def reseauxIdList =[]
        if (idReseaux) {
            reseauxIdList=Reseau.createCriteria().list(){
                inList("id",idReseaux)
            }
        }
        if (!params.myUserFile?.isEmpty()) {
            if (reseauxIdList?.size() > 0) {

                try {
                    MultipartFile f = params.myUserFile

                    def webRootDir = servletContext.getRealPath("/")
                    if (f?.empty) {
                        flash.message = 'Vous devez sélectionner un fichier'
                        showUsers()
                    }

                    def okcontents = ['excel/xls', 'excel-x/xlsx', 'application/vnd.ms-excel']
                    if (!okcontents.contains(f.getContentType())) {
                        flash.message = "Le type du fichier doit être compris dans: ${okcontents}"
                        showUsers()
                    }

                    def fileName = "user_".uniquify(".xls")
                    def dir = new File("${webRootDir}excell")//some path...
                    if (!dir.exists()) {
                        dir.mkdirs()
                    }
                    def userDir = new File("${webRootDir}excell", fileName)

                    f.transferTo(userDir)
                    userImportService.importFile(userDir.absolutePath, params, ecraserDoublons)
                    flash.message = message(code: 'reussite.importation')
//                    renderUtilisateurList()
//                    def result=[id:magazinInstance.id]
                    showUsers()

                } catch (Exception e) {
                    flash.message = message(code: 'echec.importation') + " cause : ${e}"
                    showUsers()
                }
            } else {
                flash.message = message(code: 'default.reseauNonSelectionne.message')
                showUsers()
            }
        }else {
            flash.message = 'Vous devez sélectionner un fichier'
            showUsers()
        }
    }
}
