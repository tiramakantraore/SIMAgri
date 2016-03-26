package simagri

import grails.converters.JSON


class AdministrationController {
    def myUtilityService
    def actualites(){
         try{
            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
            def listefromDate=fromDateStr?.tokenize('/')
            def listetoDate=toDateStr?.tokenize('/')
             def fromDay
             def fromMonth
             def fromYear
             def toDay
             def toMonth
             def toYear
             def fromDate
             def toDate
             if (listefromDate){
                 fromDay=listefromDate[0].toInteger()?:1
                 fromMonth=listefromDate[1].toInteger()?:1
                 fromYear=listefromDate[2].toInteger()?:1890
                 toDay=listetoDate[0].toInteger()?:1
                 toMonth=listetoDate[1].toInteger()?:1
                 toYear=listetoDate[2].toInteger()?:1890
                 fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
                 toDate=new Date(toYear-1900,toMonth-1,toDay)
             }





            def reseauId=params.reseauId?new Long(params.reseauId as String):null



            def topNInfos = Info.createCriteria().list(params) {
                order('date', 'desc')
                and {
                    ge("dateExpiration", new Date())

                }
                if (fromDate && toDate) {
                        and {

                            between("date", fromDate-1, toDate+1)
                        }
                }
                if (reseauId){
                    and {
                        eq('reseau.id', reseauId)
                    }
                }
            }

            render template: "/dashboard/actualitesList", model: [lesInfos: topNInfos]

        }catch (Exception e) {
             println " Exception $e"
            log.error  " exception ${e}"
            render  false
        }

    }
    def setLastPrices={

        try{
            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
            def typePrix=params.typePrix
            def listefromDate=fromDateStr?.tokenize('/')
            def listetoDate=toDateStr?.tokenize('/')
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            def toDate=new Date(toYear-1900,toMonth-1,toDay)

            def  produitsIds=myUtilityService.toLong(myUtilityService.toList(params.'produitsIds[]'))
            def marchesIds=myUtilityService.toLong(myUtilityService.toList(params.'marchesIds[]'))
            def reseauId=params.reseauId?new Long(params.reseauId as String):null


            def sortIndex = 'date'
            def sortOrder  ='desc'
            def  columnNames = ['Marche', 'Produit', 'Moyenne','TypePrix']

            def user=myUtilityService.getCurrent()

            def query = {
                createAlias('produit','produit')
                createAlias('marche','marche')
                createAlias('auteur','auteur')
                createAlias('reseau','reseau')
                switch (params.columnSelected){
              case "Auteur":
                        if (params.searchvalue) {
                            def prenom
                            def nom
                            if (params.searchvalue.contains(' ')){
                                prenom= "${params.searchvalue.split(' ')[1]}%"
                                nom="${params.searchvalue.split(' ')[0]}%"
                            }else {
                                nom="${params.searchvalue}%"
                            }
                            if (params?.searchvalue?.size()>2){
                                if  (params?.searchvalue?.substring(1,2)?.matches("[0-9]")) {
                                    like("auteur.mobilePhone", "%${params.searchvalue}%")
                                } else {
                                    if (params.searchvalue.contains(' ')){
                                        and {
                                            ilike("auteur.lastName", nom)
                                        }
                                        and {
                                            ilike("auteur.firstName", prenom)
                                        }
                                    }else {
                                        ilike("auteur.lastName", nom)
                                    }
                                }
                            }
                        }
                        break
                    case "Note":
                        if (params.searchvalue) {
                            ilike("commentaire", '%' + params.searchvalue + '%')
                        }
                        break
                }
                if (produitsIds){
                    and {
                        'in'('produit.id', produitsIds)
                    }
                }
                if (reseauId){
                    and {
                        eq('reseau.id', reseauId)
                    }
                }

                if (marchesIds){
                    and {
                        'in'('marche.id', marchesIds)
                    }
                }
                and {
                    between("date", fromDate-1, toDate+1)
                }
                if (typePrix){
                    and {
                        eq('categorieTarifaire', typePrix)
                    }
                }
                and{
                    gt('montant',0.0)
                }

                if (params.sort){
                    order(sortIndex,sortOrder)
                }
            }

            def criteria = Prix.createCriteria()
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            def priceHolders = criteria.list(query)


            def collectedPrices= priceHolders.collect {
                [it?.marche?.nom,"${it?.produit?.nom} (${it?.produit?.mesure?.code})",it.montant,(it.categorieTarifaire=="Gros")?'Prix de gros':'Prix de détail']
            }
            def jsonData=[columnNames]+collectedPrices

            render jsonData as JSON



        }catch (Exception e) {
            log.error  " exception ${e}"
            render  false
        }

    }
    def setLastOffres={
        try{
            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
            def typeOffre=params.typeOffre
            def idMesure=params.idMesure
            def listefromDate=fromDateStr?.tokenize('/')
            def listetoDate=toDateStr?.tokenize('/')
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890
            def  produitsIds=myUtilityService.toLong(myUtilityService.toList(params.'produitsIds[]'))
            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            def toDate=new Date(toYear-1900,toMonth-1,toDay)

            def reseauId=params.reseauId?new Long(params.reseauId as String):null


            def sortIndex = 'date'
            def sortOrder  ='desc'

            def  columnNames = ['Auteur','Mobile', 'Produit', 'Mesure','Qte','PU' ,'Total','Categorie','TypeOffre','Lieux']


            def query = {
                createAlias('produit','produit')
                createAlias('auteur','auteur')
                createAlias('auteur.reseauPrincipal','reseau')
                switch (params.columnSelected){
                    case "Auteur":
                        if (params.searchvalue) {
                            def prenom
                            def nom
                            if (params.searchvalue.contains(' ')){
                                prenom= "${params.searchvalue.split(' ')[1]}%"
                                nom="${params.searchvalue.split(' ')[0]}%"
                            }else {
                                nom="${params.searchvalue}%"
                            }
                            if (params?.searchvalue?.size()>2){
                                if  (params?.searchvalue?.substring(1,2)?.matches("[0-9]")) {
                                    like("auteur.mobilePhone", "%${params.searchvalue}%")
                                } else {
                                    if (params.searchvalue.contains(' ')){
                                        and {
                                            ilike("auteur.lastName", nom)
                                        }
                                        and {
                                            ilike("auteur.firstName", prenom)
                                        }
                                    }else {
                                        ilike("auteur.lastName", nom)
                                    }
                                }
                            }
                        }
                        break
                    case "Note":
                        if (params.searchvalue) {
                            ilike("commentaire", '%' + params.searchvalue + '%')
                        }
                        break
                }
                if (produitsIds){
                    and {
                        'in'('produit.id', produitsIds)
                    }
                }
                if (reseauId){
                    and {
                        eq('reseau.id', reseauId)
                    }
                }
                and {
                    between("date", fromDate-1, toDate+1)
                }
                and {
                    ne("auteur.role", "superAdmin")
                }
                if (typeOffre){
                    and {
                        eq('typeOffre', typeOffre)
                    }
                }

                if (params.sort){
                    order(sortIndex,sortOrder)
                }
            }

            def criteria = Offre.createCriteria()
            params.max = Math.min(params.max ? params.int('max') : 25, 100)
            def offreHolders = criteria.list(query, max: params.max, offset: params.offset)


            def collectedPrices= offreHolders.collect {
                [it?.auteur?.toString(),it.auteur?.mobilePhone,"${it?.produit?.nom}", "${it?.getMesurenorme(idMesure)}",it.getQuantiteNorme(idMesure)?:0.0,it.getPunorme(idMesure)?:0.0,it.prixTotalGros?:0.0,it.categorieProduit,(it.typeOffre=="Achat")?"Offre d'achat":"Offre de vente",(it.typeOffre=="Achat")?it.lieuStockage?.nom?:"NP":it.lieuLivraison?.nom?:"NP"]
            }
            def jsonData=[columnNames]+collectedPrices
            render jsonData as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            render  false
        }

    }
    def setLastStocks={
        try{
            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
            def idMesure=params.idMesure
            def listefromDate=fromDateStr?.tokenize('/')
            def listetoDate=toDateStr?.tokenize('/')
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)

            def  produitsIds=myUtilityService.toLong(myUtilityService.toList(params.'produitsIds[]'))
            def reseauId=params.reseauId?new Long(params.reseauId as String):null
            def magazinsIds=myUtilityService.toLong(myUtilityService.toList(params.'magazinsIds[]'))
            def sortIndex = 'date'
            def sortOrder  ='desc'

            def  columnNames = ['Magazin', 'Produit','Mesure', 'Stock']



            def query = {
                switch (params.columnSelected){
                    case "Auteur":
                        if (params.searchvalue) {
                            def prenom
                            def nom
                            if (params.searchvalue.contains(' ')){
                                prenom= "${params.searchvalue.split(' ')[1]}%"
                                nom="${params.searchvalue.split(' ')[0]}%"
                            }else {
                                nom="${params.searchvalue}%"
                            }
                            if (params?.searchvalue?.size()>2){
                                if  (params?.searchvalue?.substring(1,2)?.matches("[0-9]")) {
                                    like("auteur.mobilePhone", "%${params.searchvalue}%")
                                } else {
                                    if (params.searchvalue.contains(' ')){
                                        and {
                                            ilike("nom", nom)
                                        }
                                        and {
                                            ilike("prenom", prenom)
                                        }
                                    }else {
                                        ilike("prenom", nom)
                                    }
                                }
                            }
                        }
                        break
                    case "Note":
                        if (params.searchvalue) {
                            ilike("commentaire", '%' + params.searchvalue + '%')
                        }
                        break
                }
                if (produitsIds){
                    and {
                        'in'('produitid', produitsIds)
                    }
                }
                if (reseauId){
                    and {
                        eq('reseauid', reseauId)
                    }
                }
                if (magazinsIds){
                    and {
                        'in'('magazinid', magazinsIds)
                    }
                }

                and {
                    ge("maxdate", fromDate)
                }

                if (params.sort){
                    order(sortIndex,sortOrder)
                }
            }

            def criteria = ViewStocksMagazin.createCriteria()
            params.max = Math.min(params.max ? params.int('max') : 50, 100)
            def stockHolders = criteria.listDistinct(query)


            def collectedPrices= stockHolders.collect {
                [it?.magazin,"${it?.produit}","${it?.getMesurenorme(idMesure)}",it.getStockNorme(idMesure)?:0.0]
            }
            def jsonData=[columnNames]+collectedPrices
            render jsonData as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            render  false
        }

    }
    def show() {
        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        def user=myUtilityService.getCurrent()


        render view: "index", model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),user:user,activeMenu:'Administration',
                                    pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires,searchUpdate: "documents"]

    }
    def statistiques() {

        def smsMTQuery = SMSLogger.createCriteria().list(){
            eq("isIn",true)
            createAlias('user','user')
            createAlias('user.operateur','operateur')
            projections{
                groupProperty ("operateur.nom", "nomOperateur")
                count "id",'smsCount'

            }
            order('operateur.nom','asc')
        }
        def smsMOQuery = SMSLogger.createCriteria().list(){
            eq("isIn",false)
            createAlias('user','user')
            createAlias('user.operateur','operateur')
            projections{
                groupProperty ("operateur.nom", "nomOperateur")
                count "id",'smsCount'

            }
            order('smsCount','desc')
        }
        def props = ['operateur','smsCount']
        def smsMO = smsMOQuery?.collect{ row->
            def cols = [:]
            row.eachWithIndex{colVal, ind->
                cols[props[ind]] = colVal
            }
            cols
        }

        def smsMT = smsMTQuery?.collect{ row->
            def cols = [:]
            row.eachWithIndex{colVal, ind->
                cols[props[ind]] = colVal
            }
            cols
        }
        def nombreMembres=0
        def nombreReelMembres=Utilisateur.count()

        def nombreNoeuds=Reseau.countByActive(true)
        def lesReseaux = Reseau.createCriteria().list(){
            eq("estReseau",true)
            and {
                eq('active',true)
            }

        }
        lesReseaux.each{
            nombreMembres+=it.nbTotalMembres
        }
        def lesGroupes = Reseau.createCriteria().list(){
            inList("parent",lesReseaux)
            and {
                eq('active',true)
            }

        }

        def nombreReseaux=lesReseaux.size()
        def nombreGroupe=lesGroupes.size()
        def nombreSousGroupe=nombreNoeuds-(nombreReseaux+nombreGroupe)
        render template:"statistiques",model:[nombreMembres:nombreMembres,nombreReseaux:nombreReseaux,nombreGroupe:nombreGroupe,nombreSousGroupe:nombreSousGroupe,
                                              nombreReelMembres:nombreReelMembres,
                                       smsMT:smsMT,smsMO:smsMO]
    }
    def dataminingprix() {
        def userProductsList=[]
        def userMarketsList=[]

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
        render template:"dataminingprix",model:[userProductsList:userProductsList,userMarketsList:userMarketsList]
    }
    def dataminingoffre() {

        render template:"dataminingoffre",model:[]
    }
    def dataminingstock() {

        render template:"dataminingstock",model:[]
    }
    def dataminingperformance() {
        def groupes=["Annee","Trimestre","Semaine","Mois"]
        def aggregats=["Prix","Notes"]
        render template:"dataminingperformance",model:[groupes:groupes,aggregats:aggregats]
    }
    def dataminingsms() {

        render template:"dataminingsms",model:[]
    }
    def index() {

        def smsMTQuery = SMSLogger.createCriteria().list(){
            eq("isIn",true)
            createAlias('user','user')
            createAlias('user.operateur','operateur')
            projections{
                groupProperty ("operateur.nom", "nomOperateur")
                count "id",'smsCount'

            }
            order('operateur.nom','asc')
        }
        def smsMOQuery = SMSLogger.createCriteria().list(){
            eq("isIn",false)
            createAlias('user','user')
            createAlias('user.operateur','operateur')
            projections{
                groupProperty ("operateur.nom", "nomOperateur")
                count "id",'smsCount'

            }
            order('smsCount','desc')
        }
        def props = ['operateur','smsCount']
        def smsMO = smsMOQuery?.collect{ row->
            def cols = [:]
            row.eachWithIndex{colVal, ind->
                cols[props[ind]] = colVal
            }
            cols
        }

        def smsMT = smsMTQuery?.collect{ row->
            def cols = [:]
            row.eachWithIndex{colVal, ind->
                cols[props[ind]] = colVal
            }
            cols
        }
        def user=myUtilityService.getCurrent()
        user?.attach()
        def listeMarches=user?.markets
        def groupes=["Annee","Trimestre","Semaine","Mois"]
        def aggregats=["Prix","Notes"]

        def criteria = Prix.createCriteria()
        def query = {
            if (params.sort){
                order(params.sort,params.order)
            }
        }
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        def listePrix = criteria.list(query, max: params.max, offset: params.offset)
        render template:'index',model:[pageAccueilInstance:PageAccueil.findByEstPrincipal(true), prixTotal: listePrix.totalCount,
         smsMT:smsMT,smsMO:smsMO,user:user,groupes:groupes,aggregats:aggregats,listePrix:listePrix,listeMarches:listeMarches]
    }
}
