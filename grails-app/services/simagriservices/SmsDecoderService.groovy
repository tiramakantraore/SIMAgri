package simagriservices

import grails.transaction.Transactional
import simagri.Info
import simagri.Magazin
import simagri.MagazinProduit
import simagri.Marche
import simagri.MarcheProduit
import simagri.Mesure
import simagri.Offre
import simagri.PriceHolder
import simagri.Prix
import simagri.Produit
import simagri.Reseau
import simagri.SMSLogger
import simagri.StockMagazin
import simagri.Utilisateur
import simagri.UtilisateurReseau

@Transactional
class SmsDecoderService {
    def sendSMSService
    def userService
    def grailsApplication
    def marcheService
    def magazinService
    def getPriceFromVoice(def codeProduit, def codeMarche){
            def marchestr="NA"
            if (codeProduit){
                def sortIndex = 'date'
                def sortOrder  ='desc'
                def maxRows =1
                def listMarches=Marche.list()
                marchestr=""
                listMarches.eachWithIndex{mar,k->
                    if (k>0)
                        marchestr+=","
                    marchestr+= mar.code
                }


                        def marche=Marche.findByCodeIlike(codeMarche)
                        def produit=Produit.findByCodeIlike(codeProduit)

                        if (produit && marche){
                            def datePrixTab = Prix.withCriteria {

                                    eq("marche",marche)
                                    and {
                                        eq('produit',produit)
                                    }
                                    and {
                                        gt('montant',0.0)
                                    }

                                projections {
                                    property('date','date')
                                }

                                maxResults(maxRows)
                                order(sortIndex, sortOrder)
                            }

                            def datePrix=datePrixTab[0]
                            if (datePrix) {

                                def prixDetailTab = Prix.withCriteria {
                                       eq("marche",marche)
                                    and {
                                        eq('produit',produit)
                                    }
                                    and {
                                        eq('date',datePrix)
                                    }
                                    and {
                                        eq('categorieTarifaire','Detail')
                                    }
                                    and {
                                        gt('montant',0.0)
                                    }

                                    projections {
                                        property('montant','montant')
                                    }

                                }

                                def prixGrosTab = Prix.withCriteria {
                                     eq("marche",marche)

                                    and {
                                        eq('produit',produit)
                                    }
                                    and {
                                        eq('date',datePrix)
                                    }
                                    and {
                                        eq('categorieTarifaire','Gros')
                                    }
                                    and {
                                        gt('montant',0.0)
                                    }

                                    projections {
                                        property('montant','montant')
                                    }

                                }
                                BigDecimal prixGros=prixGrosTab[0]?:0.0
                                BigDecimal prixDetail=prixDetailTab[0]?:0.0


                                if (prixGros || prixDetail)  {

                                   return "Le produit  ${produit?.nom} coûte ${prixGros.roundIt(0)} francs CFA ${produit?.mesure?.name} en gros et ${prixDetail.roundIt(0)} francs CFA  ${produit?.mesure?.name} en détail dans le marché ${marche?.nom}"

                                }else
                                    return "Aucune reponse trouvée pour le produit ${produit?.nom} et dans le marché ${marche?.nom} "
                             }
                            else
                                return "Aucune reponse trouvée pour le produit ${produit?.nom} et le marche ${marche?.nom} "
                       }
            }
    }
    def getPrix(def message, def userFound, def from_phone){
        List<String> listeParam=message.tokenize('#')
        def lineSeparator=System.getProperty('line.separator')
        def resultatProduit=""
        def resultatMarche=""
        def isAllMarche=false
        if (listeParam.size()>=2){

            def produitsstr=listeParam[1]
			def marchestr="NA"
            def enteteMessage="Prix=Gros/Détail".sansAccent()+lineSeparator
            enteteMessage+="Devise=FCFA"+lineSeparator
			if (listeParam.size()>2){

            marchestr=listeParam[2].toString()
			}

            def isAlerte=(listeParam[listeParam.size()-1].toString()=="isAlerte")
            def delaiValiditeEnJours = grailsApplication.config.grails.delaiValiditeAlertesEnJours?:30
            def produitsList = produitsstr.tokenize(',')
            def listProduit=[]
            produitsList?.eachWithIndex { m, index ->
                def prod=Produit.findByCodeIlike(m.toUpperCase())
                if (prod)
                listProduit<< prod?.id
            }
            def marchesList
            Mesure.findByCode(marchestr)?marchestr="NA":marchestr
            if (marchestr=="NA"){
                def  marcheProd=MarcheProduit.createCriteria().list(){
                    'in'('produit.id',listProduit)
                }
                marchestr=""

                marcheProd.eachWithIndex{mar,k->
                    if (k>0)
                        marchestr+=","
                    marchestr+= mar.marche?.code
                }



                marchesList = marchestr.tokenize(',')
                isAlerte=true
                isAllMarche=true

            }else{
                marchesList = marchestr.tokenize(',')
            }

			def mesure=null
            if (produitsstr){

				if (listeParam.size()>3){
					def mesurestr=listeParam[3].toString()
					mesure=Mesure.findByCode(mesurestr)
				}else {
                    if (listeParam?.size()>=2) {
                        mesure = Mesure.findByCode(listeParam[2])
                    }
                }
				
                def sortIndex = 'date'
                def sortOrder  ='desc'
                def maxRows =1

                marchesList?.each{ mar ->
                String codeMarche=mar.toString().toUpperCase()
                def dateDuJour=new Date()
                def marche=Marche.findByCodeIlike(codeMarche)

                def nombreProduit=produitsList?.size()
                   resultatProduit=""

                produitsList?.eachWithIndex { m,index ->
                   def produit=Produit.findByCodeIlike(m.toUpperCase())
                  
                    if (produit){
                    def datePrixTab = Prix.withCriteria {
					if (marche){
                     eq("marche",marche)
                        and {
                            eq('produit',produit)
                        }
						and {							
							gt('montant',0.0)
						}

					}else {
						eq('produit',produit)
						and {
							gt('montant',0.0)
						}

						}
                        projections {
                            property('date','date')
                        }

                       maxResults(maxRows)
                        order(sortIndex, sortOrder)
                    }
                        def datePrix
                    if (datePrixTab.size()>0)
					datePrix=datePrixTab[0]
                        else
                        datePrix= new Date()
							if (datePrix) {
					
					def prixDetailTab = Prix.withCriteria {
                        if (marche){

                            eq("marche",marche)

                        }
						and {
							eq('produit',produit)
						}

                         and {
                                between("date", datePrix-1, datePrix+1)
                            }

						and {
                            eq('categorieTarifaire','Detail')
						}
                        and {
                            gt('montant',0.0)
                        }

						projections {
								property('montant','montant')
							}
	
						}

					def prixGrosTab = Prix.withCriteria {
                        if (marche){
                          eq("marche",marche)

                        }
						and {
							eq('produit',produit)
						}

						and {
                            eq('categorieTarifaire','Gros')
						}
						and {
							gt('montant',0.0)
						}
                        and {
                                between("date", datePrix-1, datePrix+1)
                            }


						projections {
								property('montant','montant')
							}

						}
                        BigDecimal prixGros=prixGrosTab[0]?:0.0
                        BigDecimal prixDetail=prixDetailTab[0]?:0.0
                        def texteMesure
                        def separator="/"
						if (mesure){
		                     prixGros=(prixGrosTab[0]?:0.0)*(mesure.valeur?mesure.valeur:1)
                             prixDetail=(prixDetailTab[0]?:0.0)*(mesure.valeur?mesure.valeur:1)
                            texteMesure=" ${mesure?.code}"
                         }
                        else
                            texteMesure=" ${produit?.mesure?.code}"

                         if ((prixGros==0.0)&&(prixDetail==0.0)){
                             if (!isAllMarche)
                             resultatProduit+=lineSeparator+"Le produit $produit n'est  pas encore renseigné sur le marché $marche "
                         }
                        else {
                             if (marche)
                                 resultatProduit+=lineSeparator+"${produit?.nom?.sansAccent()} ${prixGros?prixGros.toFCFA():"-"}${separator}${prixDetail?prixDetail.toFCFA():"-"}${texteMesure} ${datePrix.format("dd/MM/yy")}"
                             else {
                                 resultatProduit=lineSeparator+" marché inexistant, veuillez saisir le bon code du marché "
                                 resultatMarche=resultatProduit
                                 enteteMessage=""
                             }


                         }

					   }
                        else
                        if (!isAlerte){
                            resultatProduit+=lineSeparator+"Aucune date trouvée pour le produit ${produit?.nom} et le marche ${marche?.nom} datePrix ${datePrix}"

                        }

                  }
                    else
                    if (!isAlerte)
                        resultatProduit+=lineSeparator+"Le produit ${m.toUpperCase()} n'existe pas "
				 }
                    if (resultatProduit){
                    if (marche){
                    resultatMarche+="Marche=${marche?.nom?.sansAccent()}"
                        resultatMarche+=resultatProduit
                    }


                    }
             }


                def bigMessage=enteteMessage+resultatMarche
                def listOfSMS= bigMessage.listiFySMS("Marche=")
                listOfSMS.each{leSMS->
                    sendSMSService.execute(leSMS,from_phone.toString())
                }

            }else
                sendSMSService.execute("Le(s) produits ne sont pas correctement renseignes dans la requete ${message}",from_phone.toString())

        } else
            sendSMSService.execute("la requette ${message} est mal formee",from_phone.toString())

    }
    def getStock(def message, def userFound, def from_phone){
        List<String> listeParam=message.tokenize('#')
        def lineSeparator=System.getProperty('line.separator')
        def resultatProduit=""
        def resultatMarche=""
        def isAllMagazin=false
        def enteteMessage=""
        if (listeParam.size()>=2){

            def produitsstr=listeParam[1]
            def magazinstr="NA"

            if (listeParam.size()>2){

                magazinstr=listeParam[2].toString()
            }

            def isAlerte=(listeParam[listeParam.size()-1].toString()=="isAlerte")
            def delaiValiditeEnJours = grailsApplication.config.grails.delaiValiditeAlertesEnJours?:30
            def produitsList = produitsstr.tokenize(',')
            def listProduit=[]
            produitsList?.eachWithIndex { m, index ->
                def prod=Produit.findByCodeIlike(m.toUpperCase())
                if (prod)
                    listProduit<< prod?.id
            }
            def magazinsList
            Mesure.findByCode(magazinstr)?magazinstr="NA":magazinstr
            if (magazinstr=="NA"){
                def  magazinProd=MagazinProduit.createCriteria().list(){
                    'in'('produit.id',listProduit)
                }
                magazinstr=""

                magazinProd.eachWithIndex{mag,k->
                    if (k>0)
                        magazinstr+=","
                    magazinstr+= mag.magazin?.code
                }



                magazinsList = magazinstr.tokenize(',')
                isAlerte=true
                isAllMagazin=true

            }else{
                magazinsList = magazinstr.tokenize(',')
            }

            def mesure=null
            if (produitsstr){

                if (listeParam.size()>3){
                    def mesurestr=listeParam[3].toString()
                    mesure=Mesure.findByCode(mesurestr)
                }else {
                    if (listeParam?.size()>=2) {
                        mesure = Mesure.findByCode(listeParam[2])
                    }
                }

                def sortIndex = 'date'
                def sortOrder  ='desc'
                def maxRows =1

                magazinsList?.each{ mag ->
                    String codeMagazin=mag.toString().toUpperCase()
                    def dateDuJour=new Date()
                    def magazin=Magazin.findByCodeIlike(codeMagazin)

                    def nombreProduit=produitsList?.size()
                    resultatProduit=""

                    produitsList?.eachWithIndex { m,index ->
                        def produit=Produit.findByCodeIlike(m.toUpperCase())

                        if (produit){
                            def datePrixTab = StockMagazin.withCriteria {
                                if (magazin){
                                    eq("magazin",magazin)
                                    and {
                                        eq('produit',produit)
                                    }
                                    and {
                                        gt('montant',0.0)
                                    }

                                }else {
                                    eq('produit',produit)
                                    and {
                                        gt('montant',0.0)
                                    }

                                }
                                projections {
                                    property('date','date')
                                }

                                maxResults(maxRows)
                                order(sortIndex, sortOrder)
                            }
                            def datePrix
                            if (datePrixTab.size()>0)
                                datePrix=datePrixTab[0]
                            else
                                datePrix= new Date()
                            if (datePrix) {

                                def stockList = StockMagazin.withCriteria {
                                    if (magazin){

                                        eq("magazin",magazin)

                                    }
                                    and {
                                        eq('produit',produit)
                                    }

                                     and {
                                            between("date", datePrix-1, datePrix+1)
                                        }

                                    and {
                                        gt('stock',0.0)
                                    }

                                    projections {
                                        property('stock','stock')
                                    }

                                }

                                BigDecimal stockProduit=stockList[0]?:0.0
                                def texteMesure
                                def separator="/"
                                if (mesure){
                                    stockProduit=(stockProduit?:0.0)*(mesure.valeur?mesure.valeur:1)
                                    texteMesure=" ${mesure?.code}"
                                }
                                else
                                    texteMesure=" ${produit?.mesure?.code}"

                                if (stockProduit==0.0){
                                    if (!isAllMagazin)
                                        resultatProduit+=lineSeparator+"Le $produit n'est  pas encore renseigné dans $magazin "
                                }
                                else
                                        resultatProduit+=lineSeparator+"${produit?.nom?.sansAccent()} ${stockProduit:"-"}${texteMesure} ${datePrix.format("dd/MM/yy")}"

                            }
                            else
                            if (!isAlerte)
                                resultatProduit+=lineSeparator+"Aucune date trouvée pour le produit ${produit?.nom} et ${magazin?.nom} a la date ${datePrix}"
                        }
                        else
                        if (!isAlerte)
                            resultatProduit+=lineSeparator+"Le produit ${m.toUpperCase()} n'existe pas "
                    }
                    if (resultatProduit){
                        resultatMarche+="Magazin=${magazin?.code?.sansAccent()}"
                        resultatMarche+=resultatProduit
                    }
                }


                def bigMessage=enteteMessage+resultatMarche
                def listOfSMS= bigMessage.listiFySMS("Marche=")
                listOfSMS.each{leSMS->
                    sendSMSService.execute(leSMS,from_phone.toString())
                }

            }else
                sendSMSService.execute("Le(s) produits ne sont pas correctement renseignes dans la requete ${message}",from_phone.toString())

        } else
            sendSMSService.execute("la requette ${message} est mal formee",from_phone.toString())

    }
    def setPrix(def message, def userFound, def from_phone){
        
        List<String> listeNote=message.tokenize("__")
		List<String> listeParam=listeNote[0].tokenize("#")
		
        def note=(listeNote.size()>0)? listeNote[1]:""
	
        if (listeParam.size()>=3){
            String newMessage=listeParam[1].toString()
            def priceMap=[:]
			def mesurestr=""


            newMessage.split(',').each {param ->
                def nameAndValue = param.split('=')
                priceMap[nameAndValue[0]] = nameAndValue[1]
            }
            def produitsList=listeParam[1].tokenize(',')
            def marchestr=listeParam[2]
			if (listeParam.size()==4){
				mesurestr=listeParam[3]
			}
            BigDecimal prixGros=0.0
            BigDecimal priceDetail=0.0
            def isInserted=false
            def mesure=null
            if (marchestr && produitsList){
                def marche=Marche.findByCode(marchestr)
				if (!marche)
				marche=Marche.findByNom(marchestr.trim())
				if (marche){
                if (marcheService.canWriteToMarket(userFound,marche?.id?:new Long("0"))){
                isInserted=false
                priceMap?.each {
                    def produit=Produit.findByCodeIlike(it.key.toString())

					if (produit){
                    def price=it.value.tokenize('/')
                    String priceStr=it.value

                    if (price.size()>1){
                        prixGros=new BigDecimal(price[0])
                        priceDetail=new BigDecimal(price[1])
                    }
                    else {
                        if (priceStr.startsWith('/'))  {
                            priceDetail=new BigDecimal(price[0])
                        }
                        else
                        {prixGros=new BigDecimal(price[0])
                           priceDetail=0.0
                        }
                    }

					if (mesurestr)
					   mesure=Mesure.findByName(mesurestr)

                    if ((!mesure))
					   mesure=produit.mesure
                        def  prixRenseigne = !((prixGros=="0") && (priceDetail=="0"))
                        if (prixRenseigne) {
                            def priceHolder=new PriceHolder(nomProduit:produit?.nom,nomMarche:marche?.nom, produit:produit,
                                    marche:marche,date:new Date(),mesureGros:mesure,mesureDetail:mesure,prixGros:prixGros,prixDetail:priceDetail,
                                    auteur:userFound,active:true,isValidated:false,isRejected:false,reseau:userFound.reseauPrincipal,transactionDate:new Date(),isFromSMS:true,periodeDebut:new Date(),periodeFin:new Date(),commentaire:note)

                            priceHolder.prixGros=prixGros
                            priceHolder.prixDetail=priceDetail
                            priceHolder.save(flush:true)
                            isInserted=true
                        }

                   }
                  }
                   if (isInserted)
                    sendSMSService.execute("Prix enregistrés avec succès",from_phone.toString())
                }
                else
                    sendSMSService.execute("Vous n'êtes pas configuré(e) pour envoyer des prix dans le marché ${marche}",from_phone.toString())
 				}
                else
                    sendSMSService.execute("Le marché ${marchestr} n'existe pas",from_phone.toString())

            }  else
                sendSMSService.execute("Pour l'envoi de prix les produits et les marchés doivent être renseignés ",from_phone.toString())

        }
        else
            sendSMSService.execute("Votre requête ${message} est mal formée",from_phone.toString())

    }



    def setStock(def message, def userFound, def from_phone){

        List<String> listeNote=message.tokenize("__")
        List<String> listeParam=listeNote[0].tokenize("#")

        def note=(listeNote.size()>0)? listeNote[1]:""
        def priceMap=[:]

        String newMessage=listeParam[1].toString()
        newMessage.split(',').each {param ->
            def nameAndValue = param.split('=')
            priceMap[nameAndValue[0]] = nameAndValue[1]
        }


        if (listeParam.size()>=3){
            String stockStr=listeParam[1].toString()



            def mesurestr=""



            def produitsList=listeParam[1].tokenize(',')
            def magazinstr=listeParam[2]
            if (listeParam.size()==4){
                mesurestr=listeParam[3]
            }
            BigDecimal stock=0.0
            def isInserted=false
            def mesure=null
            if (magazinstr && produitsList){
                def magazin=Magazin.findByCode(magazinstr)
                if (!magazin)
                    magazin=Magazin.findByNom(magazinstr.trim())
                if (magazin){
                    if (magazinService.canWriteToMagazin(userFound,magazin?.id?:new Long("0"))){
                        isInserted=false

                        priceMap?.each {
                            def produit = Produit.findByCodeIlike(it.key.toString())

                            if (produit) {
                                stock = new BigDecimal(it.value.toString())
                                println " code Produit $it.key  stock $it.value"

                                if (mesurestr)
                                    mesure = Mesure.findByName(mesurestr)

                                if ((!mesure))
                                    mesure = produit.mesure
                                if (stock) {
                                    def stockHolder = new StockMagazin(produit: produit,
                                            magazin: magazin, date: new Date(), mesure: mesure, stock: stock,
                                            auteur: userFound, isValidated: false, isRejected: false, reseau: userFound.reseauPrincipal, commentaire: note)

                                    stockHolder.stock = stock
                                    stockHolder.save(flush: true)
                                    isInserted = true
                                }

                            }

                            if (isInserted)
                                sendSMSService.execute("Stocks enregistrés avec succès", from_phone.toString())
                        }
                    }
                    else
                        sendSMSService.execute("Vous n'êtes pas configuré(e) pour envoyer des prix dans le magazin ${magazin}",from_phone.toString())
                }
                else
                    sendSMSService.execute("Le magazin ${magazinstr} n'existe pas",from_phone.toString())

            }  else
                sendSMSService.execute("Pour l'envoi de stocks les produits et les magazins doivent être renseignés ",from_phone.toString())

        }
        else
            sendSMSService.execute("Votre requête ${message} est mal formée",from_phone.toString())

    }
    def envPrix(def message, def userFound, def from_phone){

        List<String> listeNote=message.tokenize("__")
        List<String> listeParam=listeNote[0].tokenize("#")

        def note=(listeNote.size()>0)? listeNote[1]:""

        if (listeParam.size()>=3){
            String newMessage=listeParam[1].toString()
            def priceMap=[:]
            def mesurestr=""


            newMessage.split(',').each {param ->
                def nameAndValue = param.split(' ')
                priceMap[nameAndValue[0]] = nameAndValue[1]
            }
            def produitsList=listeParam[1].tokenize(',')
            def marchestr=listeParam[2]
            if (listeParam.size()==4){
                mesurestr=listeParam[3]
            }
            def prixGros=0.0
            def priceDetail=0.0
            def isInserted=false
            def mesure=null
            if (marchestr && produitsList){
                def marche=Marche.findByCode(marchestr)
                if (!marche)
                    marche=Marche.findByNom(marchestr.trim())
                if (marche){
                    if (marcheService.canWriteToMarket(userFound,marche?.id?:new Long("0"))){
                        isInserted=false
                        priceMap?.each {
                            def produit=Produit.findByCodeIlike(it.key.toString())

                            if (produit){
                                def price=it.value.tokenize('/')

                                if (price.size()==1){
                                    price=it.value.tokenize('-')
                                }
                                String priceStr=it.value
                                if (price.size()>1){
                                    prixGros=new BigDecimal(price[0])
                                    priceDetail=new BigDecimal(price[1])
                                }
                                else {
                                    if (priceStr.startsWith('/'))  {
                                        priceDetail=new BigDecimal(price[0])
                                    }
                                    else
                                    {prixGros=new BigDecimal(price[0])
                                        priceDetail=0.0
                                    }
                                }

                                if (mesurestr)
                                    mesure=Mesure.findByName(mesurestr)

                                if ((!mesure))
                                    mesure=produit.mesure
                                def  prixRenseigne = !((prixGros=="0") && (priceDetail=="0"))
                                if (prixRenseigne) {
                                    def priceHolder=new PriceHolder(nomProduit:produit?.nom,nomMarche:marche?.nom, produit:produit,
                                            marche:marche,date:new Date(),mesureGros:mesure,mesureDetail:mesure,prixGros:prixGros,prixDetail:priceDetail,
                                            auteur:userFound,active:true,isValidated:false,isRejected:false,reseau:userFound.reseauPrincipal,transactionDate:new Date(),isFromSMS:true,periodeDebut:new Date(),periodeFin:new Date(),commentaire:note)
                                    priceHolder.prixGros=prixGros
                                    priceHolder.prixDetail=priceDetail
                                    priceHolder.save(flush:true)
                                    isInserted=true
                                }

                            }
                        }
                        if (isInserted)
                            sendSMSService.execute("Prix enregistrés avec succès",from_phone.toString())
                    }
                    else
                        sendSMSService.execute("Vous n'êtes pas configuré(e) pour envoyer des prix dans le marché ${marche}",from_phone.toString())
                }
                else
                    sendSMSService.execute("Le marché ${marchestr} n'existe pas",from_phone.toString())

            }  else
                sendSMSService.execute("Pour l'envoi de prix les produits et les marchés doivent être renseignés ",from_phone.toString())

        }
        else
            sendSMSService.execute("Votre requête ${message} est mal formée",from_phone.toString())

    }
    def getOffre(def message, def userFound, def from_phone){

        def listeParam=message.tokenize('#')
        def isAlerte=(listeParam[listeParam.size()-1].toString()=="isAlerte")
        def delaiValiditeEnJours = grailsApplication.config.grails.delaiValiditeAlertesEnJours?:30
        if (listeParam.size()>=3){

            def typeOffre=listeParam[1].toUpperCase()=='A'?'Achat':'Vente'
            def produitsstr=listeParam[2]
            def produitsList = produitsstr.tokenize(',')

            if ( produitsstr){
                def sortIndex = 'date'
                def sortOrder  ='desc'
                def maxRows =4
                def dateDuJour=new Date()
                def dateLimite=dateDuJour.minus(delaiValiditeEnJours)
                def listeOffres=[]
                produitsList?.eachWithIndex { m,index ->
                    def prod=Produit.findByCodeIlike(m.toUpperCase())
                    if (prod){
                         listeOffres = Offre.createCriteria().list() {
                             eq("isValidated",true)
                             and {
                                 eq('produit',prod)
                             }
                             and {
                                 eq('typeOffre',typeOffre)
                             }

                             if (isAlerte){
                                 and {
                                     ge("date", dateLimite)
                                 }
                             }else {
                                 and {
                                     ge("dateExpiration", new Date())

                                 }
                             }

                            projections{
                                property('id','id')


                            }

                           maxResults(maxRows)
                           order(sortIndex, sortOrder)
                        }
						 

                        listeOffres.each{
                            def offre=Offre.get(it)
                            def prixunitaire=offre?.prixUnitGrosDetail
                            def prixglobal=offre?.montantGlobalGrosDetail

                            def auteur="${offre?.auteur?.lastName?:""} ${offre?.auteur?.firstName?:""}"
                            if (auteur.equals("")){
                                auteur="${offre?.auteur?.toString()}"
                            }
                            def civilite=offre?.auteur?.civilite?:""
                            def contact="${offre.auteur?.mobilePhone}"
                            def nomProduit="${offre?.produit?.nom}"
                            def quantite="${offre?.quantite}"
                            def unitMes="${offre?.mesure?.code}"
                            def nbjrsExp=offre.nbJoursExpiration
                            if (nbjrsExp) {
                                def texteOperation=(typeOffre=="Achat")?"veut acheter":"Veut vendre"
                                def texteVente=(typeOffre=="Vente")?" à ${prixunitaire} / ${unitMes}, Total HT ${prixglobal}":""
                                String resultat="${civilite} ${auteur} contact:${contact} ${texteOperation} ${quantite} ${unitMes} ${nomProduit} ${texteVente} contact:${contact} l'offre expire dans ${nbjrsExp} jrs"
							
                                sendSMSService.execute(resultat,from_phone.toString())
                            }
                        }
						if ((listeOffres.size()==0)
                            && (!isAlerte) )
						sendSMSService.execute("Aucune réponse trouvée pour votre requete ${message} ",from_phone.toString())
                    }

                }
            }  else
            if  (!isAlerte)
                sendSMSService.execute("Vous n'avez pas fourni de produit",from_phone.toString())
        } else
        if  (!isAlerte)
            sendSMSService.execute("Votre requete ${message} est mal formée",from_phone.toString())

        

    }
    def setOffre(def message, def userFound, def from_phone){
       
		List<String> listeNote=message.tokenize("__")
		List<String> listeParam=listeNote[0].tokenize("#")
		
        def conditions=(listeNote.size()>0)? listeNote[1]:""
		
		

        if (listeParam.size()>=3){
            def typeOffre=listeParam[1].toUpperCase()=='A'?'Achat':'Vente'
            String newMessage=listeParam[2]
            def offreMap=[:]
            newMessage.split(',').each {param ->
                def nameAndValue = param.split("=")
                offreMap[nameAndValue[0]] = nameAndValue[1]
            }
            def produitsList=listeParam[2].tokenize(',')
            def contact=listeParam[3]?listeParam[3]:userFound.mobilePhone
            def auteur=listeParam[3]?Utilisateur.findByMobilePhone(listeParam[3]):userFound
            BigDecimal prix=0.0
            String codemesure= ""
            String typePrix=""
            BigDecimal quantite=0.0
            String quantiteMesure=""
            String prixTypePrix=""
            BigDecimal prixUnitaire=0.0
            BigDecimal prixTotal=0.0
            if (produitsList){
                offreMap?.each {

                    def produit=Produit.findByCodeIlike(it.key.toString())
					if (produit) {
                    def valueOffre=it.value.tokenize('+')

                    quantiteMesure=valueOffre[0]
                    prixTypePrix =valueOffre[1]
                    quantite =new BigDecimal(quantiteMesure.tokenize('/')[0])
                    prix=new BigDecimal(prixTypePrix.tokenize('/')[0])
                        typePrix=prixTypePrix.tokenize('/')[1]

                    codemesure=quantiteMesure.tokenize('/')[1]
                    if (typePrix?.trim()?.toUpperCase()?.contains("PU")){
                        prixUnitaire=prix
                        prixTotal=prix*quantite
                    }else {
                        prixTotal=prix
                        prixUnitaire=(quantite>0)?(prixTotal/quantite):0
                    }
                    def mesure=Mesure.findByCodeIlike(codemesure.toUpperCase())
                   def offre= new Offre(produit:produit,categorieProduit:produit.categorie,
                            statut:'En_cours',date:new Date(),dateExpiration:new Date()+15,typeOffre:typeOffre,modePaiement:'Especes',delaiEnJours:15,
                            auteur:auteur,reseau:userFound?.reseauPrincipal,mesure:mesure,contact:contact,operateur:auteur,conditions:conditions, isValidated:false,
                           isRejected:false)
                        offre.quantite=quantite
                        offre.prixUnitaireGros=prixUnitaire
                        offre.prixTotalGros=prixTotal
                        offre.save(flush:true)
                            sendSMSService.execute("Offre [$typeOffre] pour le produit ${produit.nom} enregistree avec succes",from_phone.toString())

                    }  else
                        sendSMSService.execute("Le produit ${it.key.toString()} n'existe pas",from_phone.toString())

                }

            }  else {
                sendSMSService.execute("Vous n'avez pas précisé de produit",from_phone.toString())

            }
        }
        else {
            sendSMSService.execute("Votre requete ${message} est mal formee",from_phone.toString())

        }

    }

    def envOffre(def message, def userFound, def from_phone){

        List<String> listeNote=message.tokenize("__")
        List<String> listeParam=listeNote[0].tokenize("#")

        def conditions=(listeNote.size()>0)? listeNote[1]:""



        if (listeParam.size()>=3){
            def typeOffre=listeParam[1].toUpperCase()=='A'?'Achat':'Vente'
            String newMessage=listeParam[2]
            def offreMap=[:]
            newMessage.split(',').each {param ->
                def nameAndValue = param.split(" ")
				offreMap[nameAndValue[0]] = nameAndValue[1]
            }
            def produitsList=listeParam[2].tokenize(',')
            def contact=listeParam[3]?listeParam[3]:userFound.mobilePhone
            def auteur=listeParam[3]?Utilisateur.findByMobilePhone(listeParam[3]):userFound
            BigDecimal prix=0.0
            String codemesure= ""
            String TypePrix=""
            BigDecimal quantite=0.0
            String quantiteMesure=""
            String prixTypePrix=""
            BigDecimal prixUnitaire=0.0
            BigDecimal prixTotal=0.0
            if (produitsList){
                offreMap?.each {

                    def produit=Produit.findByCodeIlike(it.key.toString())
                    if (produit) {
						
						def valueOffre=it.value.tokenize('/')
						
							if (valueOffre.size()==1){
								valueOffre=it.value.tokenize('-')
							}
														
							
                        quantiteMesure=valueOffre[0]
                        prixTypePrix =valueOffre[1]
						quantite =((quantiteMesure.extractDouble()[0]) as BigDecimal)?:0.0
						prix=((prixTypePrix.extractDouble()[0]) as BigDecimal)?:0.0
						codemesure=quantiteMesure?.extractString()[0]?:"kg"

                        TypePrix=prixTypePrix.extractString()?:"PT"
                        if (TypePrix?.toUpperCase()=="PU"){
                            prixUnitaire=prix as BigDecimal
                            prixTotal=prix*quantite as BigDecimal
                        }else {
                            prixTotal=prix  as BigDecimal
                            prixUnitaire=(quantite>0)?prixTotal/quantite:0.0
                        }
                        def mesure=Mesure.findByCodeIlike(codemesure.toUpperCase())

                         def offre= new Offre(produit:produit,categorieProduit:produit.categorie,
                                statut:'En_cours',date:new Date(),dateExpiration:new Date()+15,typeOffre:typeOffre,modePaiement:'Especes',delaiEnJours:15,
                                auteur:auteur,reseau:userFound.reseauPrincipal,mesure:mesure,contact:contact,operateur:auteur,conditions:conditions, isValidated:false,
                                isRejected:false)
                        offre.quantite=quantite
                        offre.prixUnitaireGros=prixUnitaire
                        offre.prixTotalGros=prixTotal
                        offre.save(flush:true)
                        sendSMSService.execute("Offre enregistre avec succes",from_phone.toString())

                    }  else
                        sendSMSService.execute("Le produit ${it.key.toString()} n'existe pas",from_phone.toString())

                }

            }  else {
                sendSMSService.execute("Vous n'avez pas précisé de produit",from_phone.toString())

            }
        }
        else {
            sendSMSService.execute("Votre requete ${message} est mal formee",from_phone.toString())

        }
    }

    def setInfos(def message, def userFound, def from_phone){
        def listeParam=message.tokenize('#')
        def infos=listeParam[1]
        def auteur=userFound
        if (userFound) {
        new Info(titre:"Info postée par "+userFound.mobilePhone,
                contenu:infos,auteur:auteur).save(flush:true)
        }
        sendSMSService.execute("Information enregistrée avec succès",from_phone.toString())
    }
    def setUser(def message,  def from_phone){
        def listeParam=message.tokenize('#')
        def nomComplet=listeParam[1]
        def nomEtPrenom=  nomComplet?.tokenize()
        def nom= nomEtPrenom[0]?:""
        def prenom= nomEtPrenom[1]?:""
        def userPhone
        String nomGroupe
        def reseauxIds
        String defCurrency=grailsApplication.config.DefaultCurrency
        def devise=Currency.getInstance(defCurrency)
        def password=grailsApplication.config.grails.simAgriDefaultPassword.encodeAsSHA1()
        if (listeParam.size()>2 )  {
            userPhone=listeParam[2]
        }else
            userPhone=from_phone
        if (listeParam.size()>3 )  {
            nomGroupe=listeParam[3]
        }else
            nomGroupe="LANCEMENT SIMAGRI 2015"
        if (nomGroupe) {
            reseauxIds=Reseau.findByNom(nomGroupe)?.id?.toLong()
            if (!reseauxIds) {
               def reseau = new Reseau(estReseau:true,nom:nomGroupe,active: true).save(flush: true)
                reseauxIds=reseau?.id?.toLong()
            }
        }else {
            reseauxIds=new Long("1")
        }

        def parametres=[lastName:nom, firstName:prenom,username:nomComplet,login:nomComplet,devise:devise,mobilePhone:userPhone,role:"public",country:"bfa",password:password,gender:"male",ReseauxIds:reseauxIds?:"1"]
        def user=userService.createAndSave(parametres)

        if (user)
            sendSMSService.execute("Vous avez été enregistré avec succès, login ou pseudo:${user?.mobilePhone},mot de passe:${grailsApplication.config.grails.simAgriDefaultPassword}, votre groupe est : ${user.getReseaux()}, votre reseau est : ${user.reseauPrincipal} ",from_phone.toString())
        else
            sendSMSService.execute("Votre demande d'enregistrement de l'utilisateur ${nomComplet} a échoué ",from_phone.toString())

    }
    def decodeIt( def message, def from_phone){
        if (from_phone && message){
			
            def userFound=Utilisateur.findByMobilePhone(from_phone)
            if (!userFound){
                userFound=Utilisateur.findBySecondPhone(from_phone)

            }
            def canPostData=userFound?.canPostData


            def listeParam=message.tokenize('#')
            def req=listeParam[0].trim()

            if (userFound || (req.toUpperCase()=="SETUSER") || (req.toUpperCase()=="GETPRIX") || (req.toUpperCase()=="DEMPRIX")

                    || (req.toUpperCase()=="GETOFFRE")  || (req.toUpperCase()=="DEMOFFRE")|| (req.toUpperCase()=="SIMAGRI")) {

            if (listeParam.size()>0 ) {

                switch(req.toUpperCase()) {
                    case "SIMAGRI":

//                        if (!userFound) {
//                            setUser(message,from_phone)
//                        }else {
//                               def reseau=Reseau.findByNom("LANCEMENT SIMAGRI 2015")?:new Reseau(estReseau:true,nom:"LANCEMENT SIMAGRI 2015",active: true).save(flush: true)
//                                UtilisateurReseau.create(userFound, reseau, true)
//                              }

                        sendSMSService.execute(""" SIMAgri : Systeme d'Information de Marche
                                                   Agricole. C'est une plateforme communautaire WEB to SMS.
                                                   Pour plus d'information visitez htpp://www.simagri.net """,from_phone.toString())
                        break
                    case "GETPRIX":
                        return getPrix(message,userFound, from_phone)

                        break
                    case "GETSTOCK":
                        return getStock(message,userFound, from_phone)

                        break
                    case "DEMPRIX":
                        return getPrix(message,userFound, from_phone)
                        break
                    case "GETOFFRE":
                        if (userFound)
                        return getOffre(message,userFound, from_phone)
                        else
                            sendSMSService.execute("Vous devez etre enregistré pour demander les offres",from_phone.toString())

                        break
                    case "DEMOFFRE":
                        if (userFound)
                        return getOffre(message,userFound, from_phone)
                        else
                        sendSMSService.execute("Vous devez etre enregistré pour demander les offres",from_phone.toString())

                        break
                    case "SETPRIX":
                        if (canPostData)
                            return setPrix(message,userFound, from_phone)
                        else
                            sendSMSService.execute("Vous n'etes pas autorise a mettre en ligne des prix, veuillez contacter l'administrateur",from_phone.toString())

                        break
                    case "SETSTOCK":
                       if (canPostData)
                            return setStock(message,userFound, from_phone)
                        else
                            sendSMSService.execute("Vous n'etes pas autorise a mettre en ligne des stocks, veuillez contacter l'administrateur",from_phone.toString())

                        break
                    case "ENVPRIX":
                        if (canPostData)
                            return envPrix(message,userFound, from_phone)
                        else
                            sendSMSService.execute("Vous n'etes pas autorise a poster, veuillez contacter l'administrateur",from_phone.toString())

                        break
                    case "SETINFOS":
                            return setInfos(message,userFound, from_phone)
                        break
                    case "SETUSER":
                        def userPhone
                        if (listeParam.size()>2 )
                        userPhone=listeParam[2]
                        if (userPhone) {
                            userFound=Utilisateur.findByMobilePhone(userPhone)
                            if (!userFound)
                              return setUser(message, from_phone)
                            else
                               sendSMSService.execute("L'utilisateur ${userFound} est déjà enregistré ",userPhone.toString())
                        }else {
                        if (!userFound)
                            return setUser(message, from_phone)
                        else
                            sendSMSService.execute("L'utilisateur ${userFound} est déjà enregistré ",from_phone.toString())
                        }
                        break
                    case "SETOFFRE":
                            return setOffre(message,userFound, from_phone)
                        break
                    case "ENVOFFRE":
                            return envOffre(message,userFound, from_phone)
                        break
                    default:
                        sendSMSService.execute("Mot cle ${req.toUpperCase()} inconnu, Voici la liste des mots clés valables [GETPRIX,SETPRIX,GETOFFRE,SETOFFRE,SETINFOS,SETUSER] ",from_phone.toString())

                }
            } else
                sendSMSService.execute("la requete ${message} semble mal formee, veuillez contacter l'administrateur pour recevoir une formation",from_phone.toString())


            } else
                sendSMSService.execute("le numero ${from_phone} n'est pas enregistre sur la plateforme, veuillez contacter l'administrateur",from_phone.toString())

        }

    }
}
