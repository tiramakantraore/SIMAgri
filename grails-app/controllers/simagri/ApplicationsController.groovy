package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class ApplicationsController {
    def myUtilityService
    def myFormHelperService
    def myAWSService
    def grailsApplication
    def updateAlerteByJSON(){

        if (params.id!="null"){f

            def marches=AlerteReseauMarche.createCriteria().list {
                eq("alerteReseau.id",new Long (params.id?.toString()?:"0"))
                createAlias('marche','marche')
                projections {
                    distinct("marche.id")
                }
            }

            def resultsmarches=[]
            marches.each{
                resultsmarches <<"markets_alertes_${it}"
            }
            def produits=AlerteReseauProduit.createCriteria().list {
                eq("alerteReseau.id",new Long (params.id?.toString()?:"0"))
                createAlias('produit','produit')
                projections {
                    distinct("produit.id")
                }
            }

            def resultsproduits=[]
            produits.each{
                resultsproduits <<"products_alertes_${it}"
            }

            def destinataires=AlerteReseauDestinataire.createCriteria().list {
                eq("alerteReseau.id",new Long (params.id?.toString()?:"0"))
                createAlias('utilisateur','utilisateur')
                projections {
                    distinct("utilisateur.id")
                }
            }
            def resultsdestinataires=[]
            destinataires.each{
                resultsdestinataires <<"destinataires_alertes_${it}"
            }
            def jsonData = [marches: resultsmarches,produits:resultsproduits,destinataires:resultsdestinataires,isEmpty:false]
            render jsonData as JSON
        }
        else {
            render false
        }

    }

    def showSMS() {

        render template:"sendSMS",model:[ smsToReseauxInstance: new SmsToReseaux(params)]

    }
    def showEmail() {

        render template:"sendEmail"

    }
    def show() {
        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        def user=myUtilityService.getCurrent()

        render view: "index", model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),user:user,activeMenu:'Applications',
                                     pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,searchUpdate: "documents"]


    }
    def showDoc() {
        def s3AssetInstance= new S3Asset(params)
        render template:"createDoc",model:[
                s3AssetInstance: s3AssetInstance]

    }
    def showEvent() {

        render template:"createEvent",model:[eventInstance :new Event(params)]

    }
    def showCarteMarche() {

        Utilisateur user=myUtilityService.getCurrent()
        user?.attach()
        def mesMarches= user?.markets?.sort{[it.nom]}
        if (mesMarches?.size()==0) {
            mesMarches=Marche.list()
        }

        def sizeOfList=mesMarches?.size()?:0
        def latitude=grailsApplication.config.grails.map.latitude
        def zoom=grailsApplication.config.grails.map.zoom
        def longitude=grailsApplication.config.grails.map.longitude

        render template:"carteDesMarches",model:[mesMarches:mesMarches,ctnerTemplate:myFormHelperService.getCheckBoxTemplate(sizeOfList),
                                       latitude:latitude,zoom:zoom,longitude:longitude,
                                       isCreation:true]

    }
    def showCarteMagazin() {

        Utilisateur user=myUtilityService.getCurrent()
        user?.attach()
        def mesMagazins= user?.magazins?.sort{[it.nom]}
        if (mesMagazins?.size()==0) {
            mesMagazins=Magazin.list()
        }
        def sizeOfListMag=mesMagazins?.size()?:0
        def latitude=grailsApplication.config.grails.map.latitude
        def zoom=grailsApplication.config.grails.map.zoom
        def longitude=grailsApplication.config.grails.map.longitude

        render template:"carteDesMagazins",model:[
                                       ecraserDoublons:true,mesMagazins:mesMagazins,ctnerTemplateMag:myFormHelperService.getCheckBoxTemplate(sizeOfListMag),
                                       latitude:latitude,zoom:zoom,longitude:longitude,
                                       isCreation:true]

    }
    def showAlertes() {

        render template:"alerteForm",model:[alerteInstance: new AlerteReseau(params)]

    }
    def showAlertesEdit() {

        render template:"alerteFormEdit",model:[alerteInstance: AlerteReseau.get(params.id)]

    }
    def showSondage() {

        render template:'sondageForm', model: [sondageInstance: new Sondage(params)]

    }
    def showQuizz() {
        def quizzInstance= new Quizz(params)
        render template:'quizzForm', model: [quizzInstance: quizzInstance,nbreponsesQuizz:grailsApplication.config.grails.nbreponsesQuizz?:3]

    }
    def index() {
        def infoInstance= new Info(params)
        def quizzInstance= new Quizz(params)
        def sondageInstance=new Sondage(params)
        def alerteInstance=new AlerteReseau(params)

        Utilisateur user=myUtilityService.getCurrent()
        user?.attach()
        def mesMarches= user?.markets?.sort{[it.nom]}
        def mesMagazins= user?.magazins?.sort{[it.nom]}
        def sizeOfList=mesMarches?.size()?:0
        def sizeOfListMag=mesMagazins?.size()?:0
        def sizeOfProdList=Produit.list().size()?:0
        def latitude=grailsApplication.config.grails.map.latitude
        def zoom=grailsApplication.config.grails.map.zoom
        def longitude=grailsApplication.config.grails.map.longitude

         render template:'index', model: [quizzInstance: quizzInstance,user:user,mesMarches:mesMarches,ctnerTemplateProd:myFormHelperService.getCheckBoxTemplate(sizeOfProdList),ctnerTemplate:myFormHelperService.getCheckBoxTemplate(sizeOfList),nbreponsesQuizz:grailsApplication.config.grails.nbreponsesQuizz?:3,
         infoInstance: infoInstance,ecraserDoublons:true,eventInstance :new Event(params),mesMagazins:mesMagazins,ctnerTemplateMag:myFormHelperService.getCheckBoxTemplate(sizeOfListMag),
         smsToReseauxInstance: new SmsToReseaux(params),s3AssetInstance:new S3Asset(params),latitude:latitude,zoom:zoom,longitude:longitude,
         activepage: params.activepage?:"IdSMS",alerteInstance: alerteInstance,isCreation:true,sondageInstance: sondageInstance,numero:1]

    }
    def saveDoc = {
        def user = myUtilityService.getCurrent()
        def newParams= [:] << params
        newParams.remove('localPath')

            def f = request.getFile('localPath')

            if(!f.empty) {
                    myAWSService.uploadDocument(f, newParams, ["reseauId": user?.reseauPrincipalId?.toString()])
                    flash.message="Fichier téléchargé avec succès"
                    def result=[id:1]
                    render result as JSON
                } else {
                def s3Asset = new S3Asset(newParams)
                if(!s3Asset.hasErrors()) {
                s3Asset.properties=newParams
                s3Asset.save(flush: true)
                def result=[id:s3Asset.id]
                 render result as JSON
              }

        }
    }

}
