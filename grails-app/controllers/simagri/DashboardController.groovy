package simagri

import com.dbconfig.ConfigProperty
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.util.Environment
import javassist.tools.rmi.ObjectNotFoundException
import org.springframework.security.core.context.SecurityContextHolder

class DashboardController {
    def myUtilityService
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
    def myFormHelperService
    def searchAll () {
        def query = {

            if (params.query) {
            or {

                    ilike('titre', "%${params.query}%")
                    ilike('contenu', "%${params.query}%")

            }
            }else {
                eq('id',new Long("0"))
            }
        }

       def topNInfos= Info.createCriteria().list(query)
        render(template:"/dashboard/actualitesList" ,model:[lesInfos: topNInfos])

    }


    def filterInfos ={
        def topNInfos = Info.createCriteria().list(params) {
            order('date', 'desc')
        }
        render(template:"actualitesList" ,model:[ topNInfos: topNInfos])
    }
    def filterNotes ={
        def topNotes = NoteMarche.createCriteria().list(params) {
            order('date', 'desc')
        }
        render(template:"notesList" ,model:[ topNotes: topNotes])
    }
    def filterOffres ={
        def sortIndex='date'
        def sortOrder='desc'
        def topNOffres = Offre.createCriteria().list(params) {
            eq("isValidated", true)
            and {
                ge("dateExpiration", new Date())

            }
            order(sortIndex, sortOrder)
        }
        render(template:"offresList" ,model:[ offreList: topNOffres])
    }
    def filterReseaux ={
        def reseauxList = Reseau.createCriteria().list(params) {
            eq('estReseau',true)
            order('nom', 'asc')
        }
        render(template:"reseauxList" ,model:[ reseauxList: reseauxList])
    }
    def setPieSeries={
        render dataMiningService.setPieSeries(params)


    }
    def filterQuiz ={
        def quizList = Quizz.createCriteria().list(params) {
            eq('actif',true)
            order('titre', 'asc')
        }
        render(template:"quizList" ,model:[quizList: quizList])
    }
    def setLastPrices={
        render dataMiningService.setLastPrices(params)
    }
    def show(){

        try {
            def userProductsList=[]
            def userMarketsList=[]
            def user=myUtilityService.getCurrent()
            user?.attach()


            def userProductId=user?.mesProduits?.id?.flatten()
            if (userProductId){
                userProductsList=Produit.createCriteria().list() {
                    'in' ('id',userProductId)
                    and {
                        eq('actif',true)
                    }

                    order("nom", "asc")
                }
            }
            def userMarketId=user?.mesMarches?.id?.flatten()
            if (userMarketId){
                userMarketsList=Marche.createCriteria().list() {
                    'in' ('id',userMarketId)
                    and {
                        eq('actif',true)
                    }
                    order("nom", "asc")
                }
            }


            def nomProfile="${user.toString()}"
            def nombreMoisDerniersPrix=grailsApplication.config.grails.nbMoisDerniersPrix
            def   nombreMoisDernieresOffres=grailsApplication.config.grails.nombreMoisDernieresOffres?:3
            def   nombreMoisDerniersStocks=grailsApplication.config.grails.nombreMoisDerniersStocks?:3

            def sortIndex='date'
            def sortOrder='desc'
            def topNOffres = Offre.createCriteria().list() {
                eq("isValidated", true)
                and {
                    ge("dateExpiration", new Date())

                }
                order(sortIndex, sortOrder)
            }
            def offreValideCount=Offre.withCriteria {
                eq("isValidated", true)
                and {
                    ge("dateExpiration", new Date())

                }
                order(sortIndex, sortOrder)
            }.size()
            def topNDocuments = S3Asset.createCriteria().list() {
                ne("status", "removed")
                optionList{
                    eq("name","reseauId")
                    eq("value",user?.reseauPrincipalId?.toString())
                }
                maxResults(5)
                order("id", "desc")
            }

            def topNInfos= Info.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
                eq("actif", true)
                order(sortIndex, sortOrder)
            }

            def reseauxList = Reseau.createCriteria().list() {
                eq('estReseau',true)
                order('nom', 'asc')
            }
            def criteria = Prix.createCriteria()
            def query = {
                if (params.name) {
                    ilike('lastName', '%' + params.name + '%')
                }
                if (params.sort){
                    order(params.sort,params.order)
                }
            }
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def listePrix = criteria.list(query, max: params.max, offset: params.offset)

            render template:"dashboard" ,
                    model:[nomUtilisateur:nomProfile,prixTotal: listePrix.totalCount,userProductsList:userProductsList,userMarketsList:userMarketsList,nombreMoisDerniersPrix:nombreMoisDerniersPrix,
                            nombreMoisDernieresOffres:nombreMoisDernieresOffres,nombreMoisDerniersStocks:nombreMoisDerniersStocks,listePrix:listePrix,searchUpdate:"actualitesUpdate",
                            offreList:topNOffres,topNInfos:topNInfos,reseauxList:reseauxList,offreValideCount:offreValideCount,documentList:topNDocuments]

        }

        catch (Exception e) {
            log.error " exception ${e}"
        }
    }

    def accueil(){

        try {
        def userProductsList=[]
        def userMarketsList=[]
        def userMagazinList=[]

		 def user=myUtilityService.getCurrent()
         user?.attach()
         def userProductId=user?.mesProduits?.id?.flatten()
            if (userProductId){
                    userProductsList=Produit.createCriteria().list() {
                        'in' ('id',userProductId)
                        order("nom", "asc")
                    }
            }
         def userMarketId=user?.mesMarches?.id?.flatten()
            if (userMarketId){
                userMarketsList=Marche.createCriteria().list() {
                    'in' ('id',userMarketId)
                    and {
                        eq('actif',true)
                    }
                    order("nom", "asc")
                }
            }
        def userMagazinId=user?.mesMagazins?.id?.flatten()
            if (userMagazinId){
                userMagazinList=Magazin.createCriteria().list() {
                    'in' ('id',userMagazinId)
                    and {
                        eq('actif',true)
                    }
                    order("nom", "asc")
                }
            }

         def nomProfile="${user.toString()}"
         def nombreMoisDerniersPrix=grailsApplication.config.grails.nbMoisDerniersPrix
         def   nombreMoisDerniersStocks=grailsApplication.config.grails.nombreMoisDerniersStocks?:3

         def sortIndex='date'
         def sortOrder='desc'

            def topNDocuments = S3Asset.createCriteria().list() {
                ne("status", "removed")
                optionList{
                    eq("name","reseauId")
                    eq("value",user?.reseauPrincipalId?.toString())
                }
                maxResults(5)
                order("id", "desc")
            }

            def topNInfos= Info.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
                eq("actif", true)
                order(sortIndex, sortOrder)
            }


            def reseauxList = Reseau.createCriteria().list() {
                eq('estReseau',true)
                and {
                    eq('active',true)
                }
                order('nom', 'asc')
            }
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def isNewInfo = Info.createCriteria().list(params) {
                order('date', 'desc')
                and {
                    between("date", (new Date())-1, (new Date()))

                }
            }
            def topNotes = NoteMarche.createCriteria().list(max: grailsApplication.config.grails.dernieresInfos) {
                eq("actif", true)
                and {
                    eq("actif", true)
                }
                order(sortIndex, sortOrder)
            }
            def isNewNote = NoteMarche.createCriteria().list(params) {
                order('date', 'desc')
                and {
                    between("date", (new Date())-1, (new Date()))

                }
                and {
                    eq("actif", true)
                }
            }
// Within an action you can increment a counter
           // if (!user?.isSuperAdmin)
            counterService.increment([name:'Page:Accueil',user: user?.login,connected:user])



            [pageAccueilInstance:PageAccueil.findByEstPrincipal(true),ctnerTemplateProd:myFormHelperService.getCheckBoxTemplate(userProductsList?.size()),
             activeMenu:'Tableau',userMagazinList:userMagazinList,
             nomUtilisateur:nomProfile,user:user,userProductsList:userProductsList,userMarketsList:userMarketsList,nombreMoisDerniersPrix:nombreMoisDerniersPrix,
                    nombreMoisDerniersStocks:nombreMoisDerniersStocks,searchUpdate:"actualitesUpdate",topNotes:topNotes,
                    topNInfos:topNInfos,reseauxList:reseauxList,documentList:topNDocuments,isNewInfo:(isNewInfo?.size()>0),isNewNote:(isNewNote?.size()>0)]
        }

        catch (Exception e) {
            log.error " exception ${e}"
        }
    }

    def download={
        def webRootDir = servletContext.getRealPath("/")
        def file = new File("${webRootDir}${grailsApplication.config.myftp.dir}SIMAgriMob.jar")

        if (file.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "filename=SIMAgriMob.jar")
            response.outputStream << file.bytes
        }
        else {
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
                def message="""
                      ${params.nom} a laissé le message suivant
                       ${params.message}
                 """
                try{
                sendMail {
                    async true
                    to grailsApplication.config.administrator.email
                    cc grailsApplication.config.administratorCC.email, grailsApplication.config.administratorCC2.email
                    subject "${grailsApplication.config.nomCourtPlateforme} :Nouveau contact"
                    body message
                }
                }catch(Exception e) {
                log.error " exception : ${e}"
                }
                redirect  action: 'index'
                break
        }
    }
    def runquiz(){

                def titreQuiz=Quizz.get(params.id)?.titre
                [titreQuiz:titreQuiz,quizId:params.id]
     }

    def supprimerAlertes()  {
        AlerteReseau.list()*.delete()
    }
    def updateUser(){
        try{
            Utilisateur.unformatMobilePhone()
          flash.message="La configuration des opérateurs s'est bien passée"

            redirect controller:"home", action:'accueil'
        }
        catch(Exception e){
            flash.message=message(code: 'default.echecMaintenance.message', args: [e.message])
        }
    }
	def updateDatabase(){
        try{
			
			def smsLogList=SMSLogger.createCriteria().list() {

			
			}
            smsLogList.each{smsLog->
                def prefix=smsLog.getOperatorPrefix()	
				
                Operateur.list().each{oper->
					
                    if (oper.isPrefixInList(prefix)) {
                        smsLog.operateur=oper.nom
                    }
					
                }
				smsLog.direction=smsLog.isIn?"MO":"MT"
				smsLog.save(flush:true)
            }
			

            flash.message="Les travaux de maintenance de la base se sont bien passes"
           Utilisateur.normaliserMobilePhones()
            redirect controller:"application", action:'list'
        }
        catch(Exception e){
            flash.message=message(code: 'default.echecMaintenance.message', args: [e.message])
        }
	}

    def myquizJson={
        def thequiz=Quizz.get(params.quizId)
        def jquizMe=  thequiz?.toJSON()
        render jquizMe as JSON
    }
    def toLong(def value){
        def valueLong=[]
        value.each{

            valueLong<<it.toLong()
        }

        return valueLong
    }
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def updateMap={
      def marketIds=params.marketIds
      def sortIndex = 'nom'
      def sortOrder  ='asc'
      def listeMarcheUser=toLong(marketIds?.tokenize(','))
        def marketList = Marche.createCriteria().list() {
            inList('id',listeMarcheUser.size()>0?listeMarcheUser:[new Long(-1)])
            order(sortIndex, sortOrder)
        }
     //   [marketList:marketList.flatten()]
        render  marketList as JSON
    }

    def getMarche={
        def marketId=params.marketId
        def marche = Marche.createCriteria().list() {
            eq('id',marketId?marketId.toLong():new Long(-1))
        }
        marche.collect{[latitude:it.location?.latitude?:0,longitude:it.location?.longitude?:0,nom:it.nom?:"",titrecontenu:it.titrecontenu?:"",contenu:it.contenu?:""]}

        render  marche as JSON
    }


    def findUserByMobile(){

        def userName
        def isValid=true

        if (phoneNumberService?.format(params.mobilePhone)){
            userName=Utilisateur.findByMobilePhone(params.mobilePhone)?.login
         }else {
            isValid=false
        }

        def jsonData=[userName:userName,isEmpty:!userName,isValid:isValid]
        render jsonData as JSON
    }
    def resetPassword() {
        switch (request.method) {
            case 'GET':
                def utilisateurInstance
                if (params.j_username){
                    utilisateurInstance = Utilisateur.findByMobilePhone(params.j_username)

                }else {
                    utilisateurInstance = new Utilisateur(params)
                }

                [utilisateurInstance: utilisateurInstance]
                break

        }
    }




    def comingSoon(){

    }
    def bienvenue(){

    }
    def page_404(){

    }
    def pricing(){
    }
    def portfolio(){
        redirect action:"comingSoon"
    }

    def setSondageForm={
        render (template: "sondageForm", contentType:"text/html")
    }
    def alertePrix(){
        pushPricesReseauService.fire()
        flash.message="Les alertes de prix ont été bien exécutées"
    }
    def alerteOffres(){
        pushOffresReseauService.fire()
        flash.message="Les alertes d'offres  ont été bien exécutées"
    }

    def buildSondageChart={
          render dataMiningService.buildSondageChart(params)
     }
    def setTableauBordMensuel={
        render dataMiningService.setTableauBordMensuel(params)

    }
    def setBilanSMS={
        render dataMiningService.setBilanSMS(params)
    }
    def setVote={
        def user=myUtilityService.getCurrent()

        if (params.sondageSelected) {
        def sondage=Sondage.findByActif(true)
        def reponse=Choix.findByChoixAndSondage(params.sondageSelected,sondage)
            if (sondage && reponse) {
              def vote=new  SondageReponse(sonde:user,reponse:reponse)
                vote.save(flush:true)
            }

        }
       render true
    }
    def pagedeGarde() {

    }

    def setBarPrix={

        render dataMiningService.setBarPrix(params)

    }
    def setCourbesStock={

       render dataMiningService.setCourbesStock(params)

    }
    def setBarOffresSeries={
        render dataMiningService.setBarOffresSeries(params)

    }
    def addPost={
        render postManagerService.addPost(params)

    }
    def setBarStock={
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
        end-start
    }
    def setPieStock={
        render dataMiningService.setPieStock(params)
    }

    def setPivotPrices={

        render dataMiningService.setPivotPrices(params)
    }
    def setPerfEnqueteurs={
        render dataMiningService.setPerfEnqueteurs(params)

    }

    def setPivotOffers={

        render dataMiningService.setPivotOffers(params)
    }
    def setPivotStocks={

        render dataMiningService.setPivotStocks(params)
    }

    def setPieOffresSeries={
        render dataMiningService.setPieOffresSeries(params)
    }
    def setCourbeOffres={
        render dataMiningService.setCourbeOffres(params)
    }
    def setOffresEvolution={
        render dataMiningService.setOffresEvolution(params)
    }



    def setCourbePrix={
        render dataMiningService.setCourbePrix(params)

    }

	def setPrixEvolution={
        render  dataMiningService.setPrixEvolution(params)

	}
    def setPieStocks={
        render  dataMiningService.setPieStock(params)

    }

    def setStockEvolution={
        render  dataMiningService.setStockEvolution(params)

    }


    def updateDashboardCategory={

            def utilisateur=myUtilityService.getCurrent()

             def marketList=utilisateur.reseaux?.markets as List
            def categoryJSon =
                     marketList?.collect{

                              it.nom

                    }


            render categoryJSon

    }

}
