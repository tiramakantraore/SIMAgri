package simagriservices

import grails.converters.JSON
import groovy.time.TimeCategory
import simagri.Choix
import simagri.Magazin
import simagri.Marche
import simagri.Offre
import simagri.PriceHolder
import simagri.Prix
import simagri.Produit
import simagri.Sondage
import simagri.SondageReponse
import simagri.StockMagazin
import simagri.Utilisateur
import simagri.ViewSMS
import simagri.ViewStocksMagazin
import simagri.ViewVisites
import simagriclasses.MyGroovyUtils
import java.text.SimpleDateFormat

class DataMiningService {
    def myUtilityService
    def grailsApplication
    def benchmark= { closure ->
        Long start = System.currentTimeMillis()
        def resultat=closure()
        Long end = System.currentTimeMillis()
        return resultat
    }

    def toList(value) {
        [value].flatten().findAll { it != null }

    }
    JSON setLastPrices(def params) {

        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def marketIds=params.marketIds

            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            Date fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            Date toDate=new Date(toYear-1900,toMonth-1,toDay)

            def sortIndex = 'date'
            def sortOrder  ='desc'
            def  columnNames = ['Region', 'Marche', 'Produit', 'Date', 'Prix', 'Type']

            def listeMarcheUser=myUtilityService.toLong(marketIds?.tokenize(',')?:"0")
            def produitsIds=params.produitsIds
            //   def listeProduitsUser=toLong(produitsIds?.tokenize(','))

            def user=myUtilityService.getCurrent()
            user?.attach()



            def priceHolders = Prix.createCriteria().list() {
                between("date", fromDate-1, toDate+1)



                order(sortIndex, sortOrder)
            } as List<Prix>


            def collectedPrices= priceHolders.collect {
                [it?.marche?.region?.nom?:"NA", it?.marche?.nom,"${it?.produit?.nom} (${it?.produit?.mesure?.code})",it.date,it.montant,it.categorieTarifaire]
            }
            def jsonData=[columnNames]+collectedPrices
            return jsonData as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }
    JSON setStatistiquesVisites(def params){
        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890
            def isUniqueVisitors=params.isUniqueVisitors

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            def toDate=new Date(toYear-1900,toMonth-1,toDay)



            def	visitsCountHolders = ViewVisites.createCriteria().list() {
               and {
                   between("date", fromDate-1, toDate+1)
               }
                if (isUniqueVisitors=='Oui') {
                    projections {
                            countDistinct("nomcomplet")
                          groupProperty "date"
                    }
                }else {
                    projections {
                        countDistinct("id")
                        groupProperty "date"

                    }
                }

                order('date', 'asc')
            }
            def	visitsCountPeriode = ViewVisites.createCriteria().list() {
                and {
                    between("date", fromDate-1, toDate+1)
                }
                if (isUniqueVisitors=='Oui') {
                    projections {
                        countDistinct("nomcomplet")
                    }
                }else {
                    projections {
                        countDistinct("id")

                    }
                }

                order('date', 'asc')
            }
            def jsonData = [aaData:visitsCountHolders.collect{[it[1].time,it[0]]},isEmpty:(visitsCountHolders.isEmpty()),count:visitsCountPeriode]
            return jsonData  as JSON

        }
        catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }
    JSON setBilanSMS(def params) {

        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')


            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890
            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            Date fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            Date toDate=new Date(toYear-1900,toMonth-1,toDay)

            def user=myUtilityService.getCurrent()
            user?.attach()
            def pivot=[]

                def smsQuery=ViewSMS.withCriteria {
                     between("date", fromDate - 1, toDate + 1)
                    projections{
                        groupProperty ("reseau", "reseau")
                        groupProperty ("groupe", "groupe")
                        groupProperty ("operateur", "operateur")
                        groupProperty ("nomcomplet", "nomcomplet")
                        groupProperty ("telephone", "telephone")
                        groupProperty ("direction", "direction")
                        groupProperty("smsin","smsin")
                        count 'id','smsCount'

                    }

                    }

            def props = ['reseau','groupe','operateur','auteur','telephone','direction','isIn','smsCount']

            def smsEmis = smsQuery?.collect{ row->
                def cols = [:]
                row.eachWithIndex{colVal, ind->
                    cols[props[ind]] = colVal
                }
                cols
            }
                 pivot=smsEmis.collect{
                     ["Réseau":it.reseau?:"NF","Groupe":it.groupe?:"NF","Opérateur":it.operateur?:"NF",Auteur:it.auteur,'N° Mobile':it.telephone,direction:it.direction,"Est Entrant ?":it.isIn?"Oui":"Non","Nbre SMS":it.smsCount?:0]
            }

                    def jsonData=[isEmpty:(pivot.size()==0),data:pivot]
                    return jsonData as JSON

        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }
    JSON setPerfEnqueteurs(def params) {
        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def groupBy=params.groupBy
            def aggregat=params.aggregat
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890
            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            Date fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            Date toDate=new Date(toYear-1900,toMonth-1,toDay)
            def sortIndex = 'date'
            def sortOrder  ='desc'
            def user=myUtilityService.getCurrent()
            user?.attach()
            def pivot=[]
            def prixDates=[]
                def prixDatesQuery = PriceHolder.createCriteria().list() {
                    createAlias('auteur','auteur')
                    createAlias('auteur.marcheEnqueteur','marche')
                    createAlias('auteur.reseauPrincipal','reseau')
                    between("date", fromDate-1, toDate+1)
                    and {
                        eq("isValidated",true)
                    }
                    and {
                        eq("auteur.role",'enqueteur')
                    }
                    switch(aggregat){
                        case "Note":
                            and {
                                isNotNull("commentaire")
                            }
                            and {
                                gt("commentaire","")
                            }
                            break;
                    }

//                    createAlias('auteur.marcheEnqueteur','marche')
                    projections{
                        groupProperty('auteur.nomComplet','nomEnqueteur')
                        groupProperty('auteur.mobilePhone','mobilePhone')
                        groupProperty('marche.nom','marcheEnqueteur')
                        groupProperty('reseau.nom','reseau')
                        switch(groupBy){
                            case "Semaine":
                                groupProperty('annee','annee')
                                groupProperty('mois','mois')
                                groupProperty('semaine','semaine')
                                break;
                            case "Mois":
                                groupProperty('annee','annee')
                                groupProperty('mois','mois')
                                break;
                            case "Trimestre":
                                groupProperty('annee','annee')
                                groupProperty('trimestre','trimestre')
                                break;
                            case "Annee":
                                groupProperty('annee','annee')
                                break;
                        }
                        count "id",'nbPrix'

                    }

                }
                def props

                switch(groupBy){
                    case "Semaine":
                        props = ['nomEnqueteur','mobilePhone','marcheEnqueteur','reseau','annee','semaine','nbPrix']
                        prixDates = prixDatesQuery?.collect{ row->
                            def cols = [:]
                            row.eachWithIndex{colVal, ind->
                                cols[props[ind]] = colVal
                            }
                            cols
                        }
                        break;
                    case "Mois":
                        props = ['nomEnqueteur','mobilePhone','marcheEnqueteur','reseau','annee','mois','nbPrix']
                        prixDates = prixDatesQuery?.collect{ row->
                            def cols = [:]
                            row.eachWithIndex{colVal, ind->
                                cols[props[ind]] = colVal
                            }
                            cols
                        }
                        break;
                    case "Trimestre":
                        props = ['nomEnqueteur','mobilePhone','marcheEnqueteur','reseau','annee','trimestre','nbPrix']
                        prixDates = prixDatesQuery?.collect{ row->
                            def cols = [:]
                            row.eachWithIndex{colVal, ind->
                                cols[props[ind]] = colVal
                            }
                            cols
                        }
                        break;
                    case "Annee":
                        props = ['nomEnqueteur','mobilePhone','marcheEnqueteur','reseau','annee','nbPrix']
                        prixDates = prixDatesQuery?.collect{ row->
                            def cols = [:]
                            row.eachWithIndex{colVal, ind->
                                cols[props[ind]] = colVal
                            }
                            cols
                        }
                        break;
                }

                prixDates?.each{
                    def mois=MyGroovyUtils.getLongMonthName(it.mois?.toInteger()?:0)
                    def semaine=MyGroovyUtils.getWeekName(it.semaine?.toInteger()?:0)
                    def trimestre=MyGroovyUtils.getLongQuarter(it.trimestre?.toInteger()?:0)
                    def annee=it.annee?"Année:$it.annee ":""
                    pivot<<["Enqueteur":it.nomEnqueteur,"Réseau":it.reseau,"Carte SIM":it.mobilePhone,"Marché":it.marcheEnqueteur?:"NA","Période":"${semaine}${mois}${trimestre}${annee}","Nombre":it.nbPrix,"Aggrégat":aggregat]
                }

            def jsonData=[isEmpty:(pivot.size()==0),data:pivot]
            return jsonData as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }

    JSON setTableauBordMensuel(def params) {
        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def marketIds=params.marketIds
            def enqueteursIds=params.enqueteursIds
//            def codeWeekend
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890
            Calendar cal = Calendar.getInstance(Locale.FRENCH);
            Date fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            Date toDate=new Date(toYear-1900,toMonth-1,toDay)
            def sortIndex = 'date'
            def sortOrder  ='desc'
            def  columnNames = ['Enqueteur', 'Carte SIM','Marche', 'Date','Semaine','Mois','Trimestre','Annee','Nb Prix', 'Nb Note','Norme Offres','Norme Prix','Norme Contacts','Norme Alertes','Nom Norme']
            SimpleDateFormat formatter = new SimpleDateFormat('E, dd MMM yyyy', Locale.FRENCH)
            def listeMarcheUser=myUtilityService.toLong(marketIds?.tokenize(','))



            def user=myUtilityService.getCurrent()
            user?.attach()

            if (listeMarcheUser.size()==0)  {
                def userMarketId=user?.mesMarches?.id?.flatten()
                if (userMarketId){
                    listeMarcheUser=Marche.createCriteria().list() {
                        'in' ('id',userMarketId)

                    }.id
                }
            }


            def listeEnqueteursParam=[]
            if (enqueteursIds)
            listeEnqueteursParam=myUtilityService.toLong(enqueteursIds?.tokenize(','))
            def listeEnqueteurs
            def collectedPrices=[]
            if (listeEnqueteursParam.size()>0){
                listeEnqueteurs= Utilisateur.createCriteria().list() {
                    inList('id',listeEnqueteursParam)
                }
            } else {
                listeEnqueteurs=Utilisateur.findAllByRole("enqueteur")
            }

            listeEnqueteurs.each{ enqueteur->
                def objectif=enqueteur.performance
                def prixSemaine = PriceHolder.withCriteria {
                    between("date", fromDate-1, toDate+1)
                    and {
                        eq("auteur",enqueteur)
                    }
                    and {
                        eq("isValidated",true)
                    }
                    and {
                        eq("marche",enqueteur.marcheEnqueteur)
                    }

                    if (listeMarcheUser?.size()>0) {
                        and{
                            inList('marche.id',listeMarcheUser)
                        }

                    }
                    projections {
                        property("date")
                    }
                    order(sortIndex, sortOrder)
                }

                def codeWeekend
                prixSemaine.each{prixsem->
                Date datePrix=prixsem
                    // set the date

                    cal.setTime(datePrix);
                    // "calculate" the start date of the week
                    Calendar first = (Calendar) cal.clone();
                    first.add(Calendar.DAY_OF_WEEK,
                            first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

                    // and add six days to the end date
                    Calendar last = (Calendar) first.clone();
                    last.add(Calendar.DAY_OF_YEAR, 6);
                    if (first.get(Calendar.YEAR)!=last.get(Calendar.YEAR))    {
                        def start = formatter.format(first.getTime())
                        def end = formatter.format(last.getTime())
                        codeWeekend="${start}-${end}"
                    }else {

                        if (first.get(Calendar.MONTH)!=last.get(Calendar.MONTH))    {
                            codeWeekend="${first.get(Calendar.DAY_OF_MONTH)}/${first.get(Calendar.MONTH)+1}-${last.get(Calendar.DAY_OF_MONTH)}/${last.get(Calendar.MONTH)+1}/${last.get(Calendar.YEAR)} "
                        }else {
                            codeWeekend="${first.get(Calendar.DAY_OF_MONTH)}-${last.get(Calendar.DAY_OF_MONTH)}/${first.get(Calendar.MONTH)+1}/${last.get(Calendar.YEAR)}"
                        }
                    }
                 def mois=cal.get(Calendar.MONTH)+1
                 def annee=cal.get(Calendar.YEAR);

                 def trim= (cal.get(Calendar.MONTH) / 3) + 1
                 def trimestre =trim.roundIt(0)

                def nbPrix = PriceHolder.withCriteria {
                    between("date", datePrix-1, datePrix+1)
                and {
                   eq("auteur",enqueteur)
                }
                    and {
                        eq("marche",enqueteur.marcheEnqueteur)
                    }
                    and {
                        eq("isValidated",true)
                    }


                projections{
                    count('id')
                }
            }
                def nbNotes = PriceHolder.withCriteria {
                    eq("auteur",enqueteur)
                    and {
                        between("date", datePrix-1, datePrix+1)
                    }
                    and {
                        eq("auteur",enqueteur)
                    }
                    and {
                        eq("marche",enqueteur.marcheEnqueteur)
                    }

                    and {
                        eq("isValidated",true)
                    }
                    and {
                        isNotNull("commentaire")
                    }
                    and {
                        gt("commentaire","")
                    }

                    projections{
                        count('id')
                    }
                }

                collectedPrices<<[enqueteur.login,enqueteur.mobilePhone,enqueteur.marcheEnqueteur?.nom?:"NA",datePrix,codeWeekend,MyGroovyUtils.getLongMonthName(mois),MyGroovyUtils.getLongQuarter(trimestre),annee,(nbPrix[0]==[null])?0:nbPrix[0],(nbNotes[0]==[null])?0:nbNotes[0],objectif?.nbOffre?:0.0,objectif?.nbPrix?:0.0,objectif?.nbContact?:0.0,objectif?.nbAlerte?:0.0,objectif?.nom?:"NA"]

                }
            }
            return ([columnNames]+collectedPrices) as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }
    }
    JSON getMagazinsForMap(def params) {
        def magazinsIds=params.magazinsIds
        def sortIndex = 'nom'
        def sortOrder  ='asc'
        def listeMagazins=myUtilityService.toLong(magazinsIds?.tokenize(','))
        def magazinList=[]
        def user=myUtilityService.current
        user?.attach()
        if (listeMagazins.isEmpty()) {
            magazinList=Magazin.list()
        }
        else   {
            magazinList = Magazin.createCriteria().list() {
                inList('id',listeMagazins)
                order(sortIndex, sortOrder)
            }
        }

        def magazinData=[]
        magazinList.each{
            if (it.localite?.latitude && it.localite?.longitude)
                magazinData<<[nom:it.nom,titrecontenu:it.titrecontenu,contenu:it.contenu,latitude:it.localite?.latitude,longitude: it.localite?.longitude]
        }

        def data=[isEmpty:(magazinList.isEmpty()),magazins:magazinData]
        return  data as JSON
    }
    JSON getDataForMap(def params) {
        def sortIndex = 'nom'
        def sortOrder  ='asc'
        def lookupIds=params?.lookupIds?.minus("[")?.minus("]")
        def longLookupIds
        if (params?.lookupIds){
            longLookupIds=myUtilityService.toLong(lookupIds?.tokenize(','))
        }else {
            longLookupIds=[0]
        }


        Boolean isForMarket=new Boolean(params.isForMarket)


        def lookupList=[]
         if (isForMarket) {
             lookupList = Marche.createCriteria().list() {
                 inList('id',longLookupIds)
                 order(sortIndex, sortOrder)
             }
         }
         else{
             lookupList = Magazin.createCriteria().list() {
                 inList('id',longLookupIds)
                 order(sortIndex, sortOrder)
             }
         }

        def data=[]
        lookupList.each{

            if (isForMarket) {
            if (it.location?.latitude && it.location?.longitude)
                data<<[nom:it.nom,titrecontenu:it.titrecontenu,contenu:it.contenu,latitude:it.location?.latitude,longitude: it.location?.longitude]
              }
            else
            {

                if (it.localite?.latitude && it.localite?.longitude)
                    data<<[nom:it.nom,titrecontenu:it.titrecontenu,contenu:it.contenu,latitude:it.localite?.latitude,longitude: it.localite?.longitude]

            }
        }
        def donnees=[isEmpty:(lookupList.size()==0),data:data]
        return  donnees as JSON
  }

    JSON setPivotPrices(def params) {

        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')

            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            Date fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            Date toDate=new Date(toYear-1900,toMonth-1,toDay)

            def sortIndex = 'date'
            def sortOrder  ='desc'

            List<Prix> priceHolders = Prix.createCriteria().list() {
                between("date", fromDate-1, toDate+1)
                order(sortIndex, sortOrder)
            }

            def pivot=[]
            priceHolders.each {
                pivot<<["Réseau":it?.reseau?.nom?:"NA","Auteur":it?.auteur?.toString()?:"NA","Région":it?.marche?.region?.nom?:"NA", "Marché":it?.marche?.nom,"Produit":"${it?.produit?.nom}","Type Prix":it.categorieTarifaire,"Prix":it.montant]

            }
            def jsonData=[isEmpty:(pivot.size()==0),data:pivot]
            return jsonData as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }

    JSON setPivotOffers(def params) {

        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def idMesure=params.idMesure
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890
            def listeMarcheUser
            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            Date fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            Date toDate=new Date(toYear-1900,toMonth-1,toDay)

            def sortIndex = 'date'
            def sortOrder  ='desc'
            def user=myUtilityService.getCurrent()
            user?.attach()


            def offreHolders = Offre.createCriteria().list() {
                between("date", fromDate-1, toDate+1)
                and {
                    eq('isValidated',true)
                }
//                if (listeMarcheUser?.size()>0) {
//                    and{
//                        inList('marche.id',listeMarcheUser)
//                    }
//                }


                order(sortIndex, sortOrder)
            } as List<Offre>

            def pivot=[]
            offreHolders.each {
                pivot<<["Réseau":it?.reseau?.nom,Produit:"${it?.produit?.nom}",Auteur:it?.auteur?.toString()?:"NA","Lieu de livraison":it.lieuLivraison?.toString()?:"NA","Lieu de stockage":it.lieuStockage?.toString()?:"NA","Origine":it.origine?.toString()?:"NA","Type Offre":it.typeOffre,"Prix Unitaire":it.prixUnitaireGros,Quantite:it.getQuantiteNorme(idMesure) ,Mesure:it?.getMesurenorme(idMesure)?.code]
            }
            def jsonData=[isEmpty:(pivot.size()==0),data:pivot]
            return jsonData as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }
    JSON setPivotStocks(def params) {

        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')

            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890
            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            Date fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            Date toDate=new Date(toYear-1900,toMonth-1,toDay)
            def idMesure=params.idMesure
            def sortIndex = 'date'
            def sortOrder  ='desc'
//            def user=myUtilityService.getCurrent()
//            user?.attach()


//            def userMagazinId=user?.mesMagazins?.id?.flatten()
//            if (userMagazinId){
//                listeMagazinUser=Magazin.createCriteria().list() {
//                    'in' ('id',userMagazinId)
//                    maxResults(10)
//                }.id
//            }
            List<StockMagazin> magazinHolders = StockMagazin.createCriteria().list() {
                between("date", fromDate-1, toDate+1)

//                if (listeMagazinUser?.size()>0) {
//                    and{
//                        inList('magazin.id',listeMagazinUser)
//                    }
//                }


                order(sortIndex, sortOrder)
            }


            def pivot=[]
            magazinHolders.each {
                pivot<<["Réseau":it?.reseau?.nom?:"NA", Magazin:it?.magazin?.nom,Produit:it.produit?.nom,Stock:it.getStockNorme(idMesure),"Auteur":it.auteur.toString() ,Mesure:it?.getMesurenorme(idMesure)?.code]
            }
            def jsonData=[isEmpty:(pivot.size()==0),data:pivot]
            return jsonData as JSON


        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }


    JSON buildSondageChart(def params){
        def results =[]
        def sondage=Sondage.findByActif(true)
        def reponse=Choix.findByChoixAndSondage(params.sondageSelected,sondage)
        def listeChoixSondage=sondage.details
        def voteData=[]
        def listeVoix=[]
        def listeVoixNom=[]

        def listeSondage=listeChoixSondage?.flatten()?.collect {
            it.choix?.trim()

        }
        def totalVoix=0
        listeChoixSondage?.flatten()?.each { choixSondage ->
            def nbVoteChoix =SondageReponse.countByReponse(choixSondage)
            totalVoix+=nbVoteChoix
            listeVoix<<nbVoteChoix
            listeVoixNom<<choixSondage.choix

        }
        listeVoix.eachWithIndex{voix,i->
            BigDecimal part=(totalVoix>0)?voix*100/totalVoix:0.0
            voteData<<[name:listeVoixNom[i],y:part.roundIt(2)]
        }

        results <<[data: voteData]


        def jsonData = [aaData: results,categories:listeSondage,isEmpty:false,name:"Sondage"]
        return jsonData as JSON
    }


    JSON setPieStock(def params){
        try{

            def  listeMagazinId=myUtilityService.toLong(myUtilityService.toList(params.'magazinsIds[]'))
            def results=[]


            def fromDateStr=params.fromDate
            def toDateStr=params.toDate

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

            def stockHolders=[]

            def price=[]
            def maxOfPrice=0.0
            def indexOfMax=0
            def stockData=[]
            def totalStock=0
            def magazinList = StockMagazin.createCriteria().list() {

                if (listeMagazinId?.size()>0) {
                    and{
                        inList('magazin.id',listeMagazinId)
                    }
                }
                if (produitsIds){
                    and {
                        'in'('produit.id', produitsIds)
                    }
                }
                and {
                    between("date", fromDate-1, toDate+1)
                }
                createAlias('magazin','magazin')
                projections {
                    distinct("magazin")
                }
            }
            magazinList?.flatten()?.each {
                price<<0

            }

            def   stockGlobal = StockMagazin.createCriteria().list() {
                if (produitsIds){
                    and {
                        'in'('produit.id', produitsIds)
                    }
                }

                and {
                    between("date", fromDate-1, toDate+1)
                }

                projections {
                    sum('stock')

                }

            }

              totalStock=stockGlobal[0]?:0


            magazinList?.flatten()?.eachWithIndex { mag,index ->

                stockHolders = StockMagazin.createCriteria().list() {
                    if (produitsIds){
                        and {
                            'in'('produit.id', produitsIds)
                        }
                    }
                    and {
                        eq("magazin.id", mag.id)

                    }
                    and
                        {
                    between("date", fromDate-1, toDate+1)
                            }
                    projections {
                        sum('stock')

                    }

                }
                def stockMagazin=0
                stockHolders.each{
                    if (it) {
                        stockMagazin+=it
                    }
                }
                if (stockMagazin>0)   {
                    price[index]=stockMagazin*100/totalStock

                }
                else{
                    price[index]=0
                }

                stockData<<[name:mag.nom,y: price[index]]
            }
            maxOfPrice=0
            indexOfMax=1
            stockData.eachWithIndex { m,index ->
                if (m.y>maxOfPrice)
                {
                    maxOfPrice=m.y
                    indexOfMax=index
                }
            }
            stockData[indexOfMax]?.sliced=true
            stockData[indexOfMax]?.selected=true
            results <<[name:"Repartition",type: 'pie',data: stockData]
            def isEmpty= ((price?.sum()?:0.0)==0.0)
            def jsonData = [aaData: stockData,isEmpty:isEmpty]

            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }
    }

    JSON setStockEvolution(def params){
        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            def toDate=new Date(toYear-1900,toMonth-1,toDay)

            def listeMagazinId=[]
            if (params.magazinsId!="NA")
                listeMagazinId=myUtilityService.toLong(params.magazinsId?.tokenize(','))

            def	stockHolders = StockMagazin.createCriteria().list() {

                if (listeMagazinId?.size()>0) {
                    and{
                        inList('magazin.id',listeMagazinId)
                    }
                }


                and {
                    between("date", fromDate-1, toDate+1)
                }

                projections {
                    avg('stock')
                    groupProperty "date"

                }
                order('date', 'asc')
            }


            def jsonData = [aaData:stockHolders.collect{[it[1].time,it[0]]},isEmpty:stockHolders.isEmpty()]
            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }
	
	JSON setPrixEvolution(def params){
		try{
			def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def marketIds=params.marketIds
			def categorieTarifaire=params.categorieTarifaire?.trim()?:'Gros'
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            def toDate=new Date(toYear-1900,toMonth-1,toDay)

            def listeProduitsUser=myUtilityService.toLong(params.'produitsIds'?.tokenize(','))
            def listeMarcheUser=myUtilityService.toLong(marketIds?.tokenize(','))


            def user=myUtilityService.getCurrent()
            user?.attach()
            if (listeProduitsUser.size()==0)  {
                def userProductId=user?.mesProduits?.id?.flatten()
                if (userProductId){
                    listeProduitsUser=Produit.createCriteria().list() {
                        'in' ('id',userProductId)

                    }.id
                }
            }

            if (listeMarcheUser.size()==0)  {
                def userMarketId=user?.mesMarches?.id?.flatten()
                if (userMarketId){
                    listeMarcheUser=Marche.createCriteria().list() {
                        'in' ('id',userMarketId)
                        maxResults(10)
                    }.id
                }
            }

		
						def	priceHolders = Prix.createCriteria().list() {
									

									if (listeMarcheUser?.size()>0) {
										and{
											inList('marche.id',listeMarcheUser)
										}
									}

									and {
										eq("categorieTarifaire", categorieTarifaire)

									}
									and {
										between("date", fromDate-1, toDate+1)
									}
		
									projections {
										avg('montant')
										groupProperty "date"
		
									}
                                order('date', 'asc')
						     }

						def jsonData = [aaData:priceHolders.collect{[it[1].time,it[0]]},isEmpty:(priceHolders.isEmpty())]
						return jsonData  as JSON
		
					}
					catch (Exception e) {
						 log.error  " exception ${e}"
						return  [aaData:null] as JSON
					}
			
			}

    JSON setOffresEvolution(def params){
        try{
            def listefromDate=params.fromDate?.tokenize('/')
            def listetoDate=params.toDate?.tokenize('/')
            def typeOffre=params.typeOffre?:'Vente'
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890

            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            def toDate=new Date(toYear-1900,toMonth-1,toDay)


            def	priceHolders = Offre.createCriteria().list() {

                and {
                    eq("typeOffre", typeOffre)

                }

                and {
                    between("date", fromDate-1, toDate+1)
                }
                if (typeOffre=="Vente") {
                    and {
                        gt("prixTotal", 0.0)

                    }
                }

                projections {
                    avg('prixTotal')
                    groupProperty "date"

                }
                order('date', 'asc')
            }
             def   resultat= priceHolders.collect{[it[1].time,it[0]?:0.0]}
            def jsonData = [aaData:resultat,isEmpty:priceHolders.isEmpty()]
            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }
    JSON setCourbePrix(def params){


        def listeMarcheUser=[]
        if (params.marketIds!="NA")
            listeMarcheUser=myUtilityService.toLong(params.marketIds?.tokenize(','))

        def results=[]


        def fromDateStr=params.fromDate
        def toDateStr=params.toDate
        def categorieTarifaire=params.categorieTarifaire?:'Gros'


        def listefromDate=fromDateStr?.tokenize('/')
        def listetoDate=toDateStr?.tokenize('/')
        def fromDay=listefromDate[0].toInteger()?:1
        def fromMonth=listefromDate[1].toInteger()?:1
        def fromYear=listefromDate[2].toInteger()?:1890

        def toDay=listetoDate[0].toInteger()?:1
        def toMonth=listetoDate[1].toInteger()?:1
        def toYear=listetoDate[2].toInteger()?:1890

        def toDate=new Date(toYear-1900,toMonth-1,toDay)
        def initDate= new Date(fromYear-1900,1,fromDay)
        def finishDate=new Date(fromYear-1900,12,31)
        def endDate=toDate
        def user=myUtilityService.getCurrent()
        user?.attach()


        if (listeMarcheUser.size()==0)  {
            def userMarketId=user?.mesMarches?.id?.flatten()
            if (userMarketId){
                listeMarcheUser=Marche.createCriteria().list() {
                    'in' ('id',userMarketId)
                    maxResults(10)
                }.id
            }
        }

            try{

                def priceHolders=[]

               def marcheList = Prix.createCriteria().list() {

                    eq("categorieTarifaire", categorieTarifaire)
                    if (listeMarcheUser?.size()>0) {
                    and{
                        inList('marche.id',listeMarcheUser)
                    }
                    }
                    and {
                        between("date", initDate, toDate)
                    }
                    createAlias('marche','marche')
                    projections {
                        distinct("marche")
                    }
                }
                def price=[0,0,0,0,0,0,0,0,0,0,0,0]
                def priceSum=0.0

                marcheList.unique().flatten().each { m ->
                    price=[0,0,0,0,0,0,0,0,0,0,0,0]
                    def startDate=initDate
                    endDate=startDate
                    (1..12).each { i ->

                        startDate=endDate
                        use (TimeCategory) {
                            endDate=startDate +1.month
                        }

                        Calendar cal = Calendar.getInstance(Locale.FRENCH);
                        cal.setTime(endDate)

                        int theMonth=cal.get(Calendar.MONTH)+1

                        priceHolders = Prix.createCriteria().list() {

                           eq("categorieTarifaire", categorieTarifaire)

                            and {
                                between("date", startDate, endDate)
                            }

                            and {
                                eq("marche.id", m.id)

                            }
                            projections {
                                avg('montant')

                            }

                        }

                        if (priceHolders != [null]){
                            price[theMonth] = priceHolders[0]?:0.0
                        }
                        else {
                            price[theMonth] = 0.0

                        }
                        priceSum+=price[theMonth]
                        use (TimeCategory) {
                            startDate=endDate +1.day
                        }
                    }


                    results <<[name:m.nom.trim(),data: [[y: price[1]],[y: price[2]],[y: price[3]],[y: price[4]],[y: price[5]],[y: price[6]],[y: price[7]],[y: price[8]],[y: price[9]],[y: price[10]],[y: price[11]],[y:price[12]]]]
                }

                SimpleDateFormat formatter = new SimpleDateFormat('E, dd MMM yyyy', Locale.FRENCH)
                def dateDebut = formatter.format(initDate)
                def dateFin = formatter.format(finishDate)
                def jsonData = [aaData: results,isEmpty:(priceSum==0.0),dateDebut:dateDebut.toString(),dateFin:dateFin.toString(),devise:grailsApplication.config.devise]
                return jsonData  as JSON

            }
            catch (Exception e) {
                 log.error  " exception ${e}"
                return  [aaData:null] as JSON
            }

    }
    JSON setCourbesStock(def params){


        def listeMagazinId=[]
        if (params.magazinsId!="NA")
            listeMagazinId=myUtilityService.toLong(params.magazinsId?.tokenize(','))

        def results=[]

        def fromDateStr=params.fromDate
        def toDateStr=params.toDate


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
        def listeProduitsUser=myUtilityService.toLong(params.'produitsIds'?.tokenize(','))
        def initDate= new Date(fromYear-1900,0,fromDay)
        def finishDate=new Date(fromYear-1900,11,31)
        def endDate=toDate

        try{

            def stockHolders=[]
            def magazinList = StockMagazin.createCriteria().list() {

                if (listeMagazinId?.size()>0) {
                    and{
                        inList('magazin.id',listeMagazinId)
                    }
                }
                and {
                    between("date", initDate-1, toDate+1)
                }
                createAlias('magazin','magazin')
                projections {
                    distinct("magazin")
                }
            }
            def price=[0,0,0,0,0,0,0,0,0,0,0,0]
            def priceSum=0.0

            magazinList.flatten().each { m ->
                price=[0,0,0,0,0,0,0,0,0,0,0,0]
                def startDate=initDate

                (1..12).each { i ->

                    use (TimeCategory) {
                        endDate=startDate +1.month
                    }


                    int theMonth=startDate.month+1
                    stockHolders = StockMagazin.createCriteria().list() {

                            between("date", startDate, endDate)

                        and {
                            eq("magazin.id", m.id)

                        }

                        projections {
                           sum('stock')
                    }

                    if (stockHolders != [null]){
                        price[theMonth] = stockHolders[0]?:0
                    }
                    else {
                        price[theMonth] = 0

                    }
                    priceSum+=price[theMonth]
                  }
                    use (TimeCategory) {
                        startDate=endDate +1.day
                    }
                }
                results <<[name:m.nom.trim(),data: [[y: price[1]],[y: price[2]],[y: price[3]],[y: price[4]],[y: price[5]],[y: price[6]],[y: price[7]],[y: price[8]],[y: price[9]],[y: price[10]],[y: price[11]],[y:price[12]]]]
            }
            SimpleDateFormat formatter = new SimpleDateFormat('E, d M yyyy', Locale.FRENCH)
            def dateDebut = formatter.format(initDate)
            def dateFin = formatter.format(finishDate)
            def jsonData = [aaData: results,isEmpty:(priceSum==0),dateDebut:dateDebut.toString(),dateFin:dateFin.toString()]

            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }


    JSON setBarStock(def params){
        try{
//            def listeMagazinId=[]
//            if (params.magazinsId!="NA")
//                listeMagazinId=myUtilityService.toLong(params.magazinsId?.tokenize(','))
            def results=[]

            def reseauId=params.reseauId?new Long(params.reseauId as String):null

            def produitId=params.produitSelected?new Long(params.produitSelected as String):null
            def fromDateStr=params.fromDate
            def toDateStr=params.toDate


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


            def user=myUtilityService.getCurrent()
            user?.attach()
            def  listeMagazinId=myUtilityService.toLong(myUtilityService.toList(params.'magazinsIds[]'))
            def priceHolders=[]

            def price=[]

            def magazinList = ViewStocksMagazin.createCriteria().list() {

                if (listeMagazinId?.size()>0) {
                    and{
                        inList('magazinid',listeMagazinId)
                    }
                }
                if (produitId){
                    and {
                        eq('produitid', produitId)
                    }
                }
                and {
                    between("maxdate", fromDate-1, toDate+1)
                }
                if (reseauId){
                    and {
                        eq('reseauid', reseauId)
                    }
                }
                projections {
                    distinct("magazin")
                }
            }


            def listeMagazin=magazinList?.unique()?.flatten()?.collect {
                it.trim()

            }
            listeMagazin?.unique()?.flatten()?.each {
                price<<0
            }
            magazinList?.unique()?.flatten()?.eachWithIndex { m,index ->

                priceHolders = ViewStocksMagazin.createCriteria().list() {

                        eq("magazinid", m.id)

                    if (reseauId){
                        and {
                            eq('reseauid', reseauId)
                        }
                    }
                    if (produitId){
                        and {
                            eq('produitid', produitId)
                        }
                    }
                    and {
                        between("maxdate", fromDate-1, toDate+1)
                    }
                    projections {
                        avg('stock')

                    }

                }
                if (priceHolders.size()>0)
                    price[index]=priceHolders[0]?:0.0
                else{
                    price[index]=0.0

                }
                results <<[y: price[index]]

            }

            def isEmpty= ((price?.sum()?:0.0)==0.0)
            def jsonData = [aaData: results,isEmpty:isEmpty,categories:listeMagazin,name:"Histogramme des stocks"]
            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return [aaData:null] as JSON

        }
    }



    JSON setCourbeOffres(def params){
        def results=[]

        def fromDateStr=params.fromDate
        def toDateStr=params.toDate
        def typeOffre=params.typeOffre?:'Achat'


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
   //     def produitsIds=params.produitsIds
        def initDate= fromDate
        def endDate=initDate
        def finishDate=toDate
        use (TimeCategory) {
            initDate=finishDate -12.month
        }
        def  produitsIds=myUtilityService.toLong(myUtilityService.toList(params.'produitsIds[]'))
        def user=myUtilityService.getCurrent()
        user?.attach()


        try{
            def priceHolders=[]
            def produits = Offre.createCriteria().list() {

                eq("typeOffre", typeOffre)
                if (produitsIds){
                    and {
                        inList("produit.id",produitsIds)
                    }
                }

                and {
                    eq("isValidated", true)
                }
                and {
                    between("date", initDate-1, toDate+1)
                }

                projections {
                    distinct("produit")
                }
            }
            def produitListe=produits?.flatten()?.unique()
            def price=[0,0,0,0,0,0,0,0,0,0,0,0]
            def priceSum=0.0

            produitListe.each { produit ->

                price=[0,0,0,0,0,0,0,0,0,0,0,0]

                def startDate=initDate
                endDate=startDate
                (1..12).each { i ->

                    startDate=endDate
                    use (TimeCategory) {
                        endDate=startDate +1.month
                    }

                    Calendar cal = Calendar.getInstance(Locale.FRENCH);
                    cal.setTime(endDate)

                    Integer theMonth=cal.get(Calendar.MONTH)+1

                    priceHolders = Offre.createCriteria().list() {
                        eq("produit", produit)

                        and {
                            eq("typeOffre", typeOffre)

                        }
                        and {
                                between("date", startDate, endDate)
                            }

                        projections {
                            sum('prixTotal')

                        }

                    }

                    if (priceHolders != [null]){
                        price[theMonth] = priceHolders[0]?:0.0
                    }
                    else {
                        price[theMonth] = 0.0

                    }
                    priceSum+=price[theMonth]
                }

                results <<[name:produit.nom.trim(),data: [[y: price[1]],[y: price[2]],[y: price[3]],[y: price[4]],[y: price[5]],[y: price[6]],[y: price[7]],[y: price[8]],[y: price[9]],[y: price[10]],[y: price[11]],[y:price[12]]]]
            }

            SimpleDateFormat formatter = new SimpleDateFormat('E, d M yyyy', Locale.FRENCH)
            def dateDebut = formatter.format(initDate)
            def dateFin = formatter.format(finishDate)
            def jsonData = [aaData: results,isEmpty:(priceSum==0.0),dateDebut:dateDebut,dateFin:dateFin]
            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }

    }
    JSON setPieOffresSeries(def params){
        try{

            def reseauId=params.reseauId?new Long(params.reseauId as String):null

            def results=[]


            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
            def typeOffre=params.typeOffre?:'Vente'

            def listefromDate=fromDateStr?.tokenize('/')
            def listetoDate=toDateStr?.tokenize('/')
            def fromDay=listefromDate[0].toInteger()?:1
            def fromMonth=listefromDate[1].toInteger()?:1
            def fromYear=listefromDate[2].toInteger()?:1890

            def toDay=listetoDate[0].toInteger()?:1
            def toMonth=listetoDate[1].toInteger()?:1
            def toYear=listetoDate[2].toInteger()?:1890
            def  produitsIds=myUtilityService.toLong(myUtilityService.toList(params.'produitsIds[]'))
            def fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            def toDate=new Date(toYear-1900,toMonth-1,toDay)


            def price=[]
            def maxOfPrice=0.0
            def indexOfMax=0
            def offresData=[]
            def volumeOffres
            def produitListe = Offre.createCriteria().list() {
                createAlias('auteur','auteur')
                createAlias('auteur.reseauPrincipal','reseau')
                eq("typeOffre", typeOffre)

                if (produitsIds){
                    and {
                        'in'('produit.id', produitsIds)
                    }
                }
                and {
                    eq("isValidated", true)
                }
                and {
                    between("date", fromDate-1, toDate+1)
                }
                if (reseauId){
                    and {
                        eq('reseau.id', reseauId)
                    }
                }
                projections {
                    distinct("produit")
                }
            }

            def listeDesProduits=produitListe.flatten()?.unique()?.collect {
                it.nom?.trim()

            }
            listeDesProduits?.flatten()?.unique()?.each {
                price<<0

            }
            def totalOffres= Offre.createCriteria().list() {
                createAlias('auteur','auteur')
                createAlias('auteur.reseauPrincipal','reseau')
                    eq("typeOffre", typeOffre)


                and {
                    between("date", fromDate-1, toDate+1)
                }
                projections {
                    count('id')

                }


            }

            produitListe.flatten()?.unique()?.eachWithIndex { produit,index ->
                volumeOffres = Offre.createCriteria().list() {
                    createAlias('auteur','auteur')
                    createAlias('auteur.reseauPrincipal','reseau')
                    eq("produit", produit)

                    and {
                        eq("typeOffre", typeOffre)

                    }

                    and {
                        between("date", fromDate-1, toDate+1)
                    }
                    if (reseauId){
                        and {
                            eq('reseau.id', reseauId)
                        }
                    }
                    projections {
                        count "id",'mycount'
                    }
                    order('mycount','desc')

                }
                def montantOffre=0
                volumeOffres.each{
                    if (it) {
                        montantOffre+=it
                    }
                }
                if (totalOffres[0]>0)   {
                    price[index]=(montantOffre*100/totalOffres[0])

                }
                else{
                    price[index]=0
                }

                offresData<<[name:produit.nom,y: price[index]]
            }
            maxOfPrice=0
            indexOfMax=1
            offresData.eachWithIndex { m,index ->
                if (m.y>maxOfPrice)
                {
                    maxOfPrice=m.y
                    indexOfMax=index
                }
            }
            offresData[indexOfMax]?.sliced=true
            offresData[indexOfMax]?.selected=true


            results <<[name:"Repartition",type: 'pie',data: offresData]

            def isEmpty= ((price?.sum()?:0.0)==0.0)
            def jsonData = [aaData: offresData,isEmpty:isEmpty]
            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }
    }
    JSON setBarOffresSeries(def params){
        try{
            def reseauId=params.reseauId?new Long(params.reseauId as String):null
            def results=[]
            def offersCounter=[]
            def  produitsIds=myUtilityService.toLong(myUtilityService.toList(params.'produitsIds[]'))
            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
            def typeOffre=params.typeOffre?:'Vente'


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




            def volumeOffres=[]

            def price=[]
            def counter=[]
            def reseauxData=[]
            def offreCounterData=[]
            def montantOffres=[]

            def produitListe = Offre.createCriteria().list() {
                createAlias('auteur','auteur')
                createAlias('auteur.reseauPrincipal','reseau')
                eq("typeOffre", typeOffre)

                and {
                    eq("isValidated", true)
                }
                if (produitsIds){
                    and {
                        'in'('produit.id', produitsIds)
                    }
                }
                and {
                    between("date", fromDate-1, toDate+1)
                }
                if (reseauId){
                    and {
                        eq('reseau.id', reseauId)
                    }
                }
                projections {
                    distinct("produit")
                }
            }

            def listeProduits=produitListe?.unique()?.flatten()?.collect {
                it.nom?.trim()

            }
            listeProduits?.each {
                price<<0
                counter<<0
            }


            produitListe?.unique()?.flatten()?.eachWithIndex { produit,index ->
                montantOffres = Offre.createCriteria().list() {
                    createAlias('auteur','auteur')
                    createAlias('auteur.reseauPrincipal','reseau')
                   eq("produit", produit)

                    and {
                        eq("typeOffre", typeOffre)

                    }

                    and {
                        between("date", fromDate-1, toDate+1)
                    }
                    if (reseauId){
                        and {
                            eq('reseau.id', reseauId)
                        }
                    }
                    projections {
                        sum('prixTotal')

                    }

                }

                volumeOffres = Offre.createCriteria().list() {
                    createAlias('auteur','auteur')
                    createAlias('auteur.reseauPrincipal','reseau')
                    eq("produit", produit)

                    and {
                        eq("typeOffre", typeOffre)

                    }

                    if (reseauId){
                        and {
                            eq('reseau.id', reseauId)
                        }
                    }
                    and {
                        between("date", fromDate-1, toDate+1)
                    }
                    projections {
                        count('id')

                    }

                }
                if (montantOffres.size()>0)
                    price[index]=montantOffres[0]?:0.0
                else{
                    price[index]=0.0
                }
                counter[index] =volumeOffres[0]
                reseauxData<<[y: (price[index]==null)?0.0:price[index]]
                offreCounterData<<[y: (counter[index]==null)?0.0:counter[index]]
            }
            results <<[name:"Montant des offres",data: reseauxData]
            offersCounter <<[name:"Nombre des offres",data:offreCounterData]


            def isEmpty= (reseauxData?.isEmpty())
            def jsonData = [counter:offersCounter,categories:listeProduits,isEmpty:isEmpty]

            return jsonData  as JSON

        }
        catch (Exception e) {
             log.error  " exception ${e}"
            return [aaData:null] as JSON

        }
    }
    JSON setPieSeries(def params){
        try{


            def listeMarcheUser=[]
            if (params.marketIds!="NA")
                listeMarcheUser=myUtilityService.toLong(myUtilityService.toList(params.'marketIds[]'))
            def  produitsIds=myUtilityService.toLong(myUtilityService.toList(params.'produitsIds[]'))

            def results=[]
            def reseauId=params.reseauId?new Long(params.reseauId as String):null


            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
            def categorieTarifaire=params.categorieTarifaire?:'Gros'


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

            def user=myUtilityService.getCurrent()
            user?.attach()


            def priceHolders=[]

            def marcheList = Prix.createCriteria().list() {
                createAlias('auteur','auteur')
                createAlias('auteur.reseauPrincipal','reseau')
                between("date", fromDate-1, toDate+1)
                if (listeMarcheUser?.size()>0) {
                    and{
                        inList('marche.id',listeMarcheUser)
                    }
                }

                if (produitsIds){
                    and {
                        inList('produit.id',produitsIds)
                    }
                }

                and {
                    eq("categorieTarifaire", categorieTarifaire)

                }
                if (reseauId){
                    and {
                        eq('reseau.id', reseauId)
                    }
                }
                createAlias('marche','marche')
                projections {
                    distinct("marche")
                }
            }

            def price=[]
            def maxOfPrice=0.0
            def indexOfMax=0
            def marketData=[]

            marcheList?.flatten()?.each {
                price<<0

            }
            def totalPrix=0

            def   priceMoyensGlobaux = Prix.createCriteria().list() {

                and {
                    eq("categorieTarifaire", categorieTarifaire)

                }
                if (produitsIds){
                    and {
                        inList('produit.id',produitsIds)
                    }
                }

                if (listeMarcheUser?.size()>0) {
                    and{
                        inList('marche.id',listeMarcheUser)
                    }
                }
                and {
                    between("date", fromDate-1, toDate+1)
                }

                projections {
                    avg('montant')

                }

            }

            priceMoyensGlobaux?.each{
                if (priceMoyensGlobaux?.size()>0)
                    totalPrix+=priceMoyensGlobaux[0]?:0.0
            }
            marcheList?.flatten()?.eachWithIndex { m,index ->

                priceHolders = Prix.createCriteria().list() {
                    if (produitsIds){
                        and {
                            inList('produit.id',produitsIds)
                        }
                    }

                    and {
                        eq("categorieTarifaire", categorieTarifaire)

                    }

                    and {
                        eq("marche.id", m.id)

                    }
                    between("date", fromDate-1, toDate+1)
                    projections {
                        avg('montant')

                    }

                }
                def prixMarche=0
                priceHolders.each{
                    if (it) {
                        prixMarche+=it
                    }
                }
                if (prixMarche>0.0)   {
                    price[index]=prixMarche*100/totalPrix

                }
                else{
                    price[index]=0
                }

                marketData<<[name:m.nom,y: price[index]]
            }
            maxOfPrice=0
            indexOfMax=1
            marketData.eachWithIndex { m,index ->
                if (m.y>maxOfPrice)
                {
                    maxOfPrice=m.y
                    indexOfMax=index
                }
            }
            marketData[indexOfMax]?.sliced=true
            marketData[indexOfMax]?.selected=true

            def isEmpty= ((price?.sum()?:0.0)==0.0)
            def jsonData = [aaData: marketData,isEmpty:isEmpty]
            return jsonData  as JSON

        }
        catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }
    }

    JSON setBarPrix(def params){
        try{
            def listeMarcheUser=[]
            if (params.marketIds!="NA")
//                listeMarcheUser=myUtilityService.toLong(params.marketIds?.tokenize(','))
            listeMarcheUser=myUtilityService.toLong(myUtilityService.toList(params.'marketIds[]'))
            def results=[]




            def categorieTarifaire=params.categorieTarifaire?:'Gros'
            def produitId=params.produitSelected?new Long(params.produitSelected as String):null
            def reseauId=params.reseauId?new Long(params.reseauId as String):null
            def fromDateStr=params.fromDate
            def toDateStr=params.toDate
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

            def user=myUtilityService.getCurrent()
            user?.attach()


            if (listeMarcheUser.size()==0)  {
                def userMarketId=user?.mesMarches?.id?.flatten()
                if (userMarketId){
                    listeMarcheUser=Marche.createCriteria().list() {
                        'in' ('id',userMarketId)

                    }.id
                }
            }

                def priceHolders=[]

                def price=[]

                def marcheList = Prix.createCriteria().list() {

                    eq("categorieTarifaire", categorieTarifaire)

                    if (produitId){
                        and {
                            eq('produit.id', produitId)
                        }
                    }
                    if (listeMarcheUser?.size()>0) {
                        and{
                            inList('marche.id',listeMarcheUser)
                        }
                    }
                    and {
                        between("date", fromDate-1, toDate+1)
                    }
                    if (reseauId){
                        and {
                            eq('reseau.id', reseauId)
                        }
                    }
                    createAlias('marche','marche')
                    projections {
                        distinct("marche")
                    }
                }

                def listeMarche=marcheList?.unique()?.flatten()?.collect {
                    it.nom?.trim()

                }
                marcheList?.flatten()?.unique()?.each {
                    price<<0
                }
                marcheList?.flatten()?.unique()?.eachWithIndex { m,index ->

                    priceHolders = Prix.createCriteria().list() {
                       eq("categorieTarifaire", categorieTarifaire)

                        and {
                            eq("marche.id", m.id)

                        }
                        if (produitId){
                            and {
                                eq('produit.id', produitId)
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
                        projections {
                            avg('montant')

                        }

                    }

                    if (priceHolders.size()>0)
                        price[index]=priceHolders[0]?:0.0
                    else{
                        price[index]=0.0

                    }
                     results <<[name:m?.nom,y: price[index]]




                }
                def isEmpty= (priceHolders?.sum()==0)
                def jsonData = [aaData: results,isEmpty:isEmpty,categories:listeMarche,name:"Histogramme des prix"]
                return jsonData  as JSON

        }
        catch (Exception e) {
              log.error  " exception ${e}"
            return [aaData:null] as JSON

        }
    }
}
