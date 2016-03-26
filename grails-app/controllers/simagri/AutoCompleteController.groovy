package simagri

import grails.converters.JSON


class AutoCompleteController {
    def autoCompleteService
    def autoCompleteLocaliteService
    def autoCompleteTransporterService
    def autoCompleteProductService
    def myFormHelperService

    def searchOriginJSON() {
        render  Lieux.listOrderByNom() as JSON

    }
    def searchStorageLocationJSON() {
        render  Lieux.listOrderByNom() as JSON
    }
    def searchDeliveryLocationJSON() {
        render  Lieux.listOrderByNom() as JSON
    }
    def reseauxJSON() {
    def sortIndex="nom"
    def sortOrder="asc"
    def results  =[]
    def listReseaux = Reseau.createCriteria().list() {

        order(sortIndex, sortOrder)
    }
    def reseauxArbre= listReseaux.collect {
        [it.id, it?.nom,it?.estParent]
    }
        reseauxArbre.each{

           results <<[id:it[0],nom: it[1],estParent: it[2]]
        }
        def jsonData = [aaData: results]

        render  jsonData as JSON
    }

    def marcheJSON(){
     if (params.selectedList!="null"){
            def reseauxList=[]
            def marches=[]

            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                reseauxList<<it.id
            }
            reseauxList.each{it->
                def reseau=Reseau.get(it)
                if (reseau)
                    marches+=reseau?.markets?.flatten()
            }
            def results=[]
            marches.each{
                results <<"markets_${it.id}"
            }
            def jsonData = [data: results,isEmpty:false]

         render jsonData as JSON
        }
        else {
         render false
        }

    }

    def produitJSON(){
        if (params.selectedList!="null"){
            def reseauxList=[]
            def produits=[]

            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                reseauxList<<it.id
            }
            reseauxList.each{it->
                def reseau=Reseau.get(it)
                if (reseau)
                    produits+=reseau?.produits?.flatten()
            }
            def results=[]
            produits.each{
                results <<"produits_${it.id}"
            }
            def jsonData = [data: results,isEmpty:false]

            render jsonData as JSON
        }
        else {
            render false
        }

    }
    def updateProduct(){
        def ProductCategoryId=params.id
        if (ProductCategoryId!="null"){
        def categorieSelected = CategorieProduit.findById(ProductCategoryId)
        if (categorieSelected){
            render g.select(optionKey: 'id', from: Produit.findAllByCategorie(categorieSelected), id: 'product', name: "product.id")
        }
        else
            render g.select(optionKey: 'id', from: Produit.list(), name: "product.id")
        }
        else
            render g.select(optionKey: 'id', from: Produit.list(),  name: "product.id")
    }
    def updateMesure(){
        def ProductCategoryId=params.id
        if (ProductCategoryId!="null"){
            def categorieSelected = CategorieProduit.findById(ProductCategoryId)
            if (categorieSelected){
                render g.select(optionKey: 'id', from: categorieSelected.mesures, id: 'mesure', name: "mesure.id")
            }
            else
                render g.select(optionKey: 'id', from: Mesure.list(), name: "mesure.id")
        }
        else
            render g.select(optionKey: 'id', from: Mesure.list(),  name: "mesure.id")
    }
    def updateProductMN(){

        def ProductCategoryId=params.id
        if (ProductCategoryId!="null"){
        def categorieSelected = CategorieProduit.findById(ProductCategoryId)
        if (categorieSelected){
            render g.select(optionKey: 'id', from: Produit.findAllByCategorie(categorieSelected),  id: 'produits',multiple:'multiple', name: "produits")
        }
        else
            render g.select(optionKey: 'id', from: Produit.list(),multiple:'multiple' , name: "produits")
        }else
            render g.select(optionKey: 'id', from: Produit.list(), multiple:'multiple' , name: "produits")

    }

    def updateMarketsFromReseaux(){


        if (params.selectedList!="null"){
            def reseauxList=[]
            def listeMarketIds=[]
            def marches=[]

            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                reseauxList<<it.id
            }
            reseauxList.each{it->
                def reseau=Reseau.get(it)
                if (reseau)
                marches+=reseau?.markets?.flatten()
            }
            render g.selectWithOptGroup( name: 'markets',
                    from : marches?.unique(),
                    optionKey :'id',
                    size:'5',
                    multiple:'multiple',
                    optionValue: 'nom',
                    groupBy:'regionName'
            )
          //  render g.select(optionKey: 'id', from: marches?.unique(),multiple:'multiple' , name: "markets")


        }
    }

    def updateProduitsFromCategories(){

        def produits=[]
        if (params.categories!="null"){
            List<JSON> selectedList = JSON.parse(params.categories?.toString()) //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                def categorie=CategorieProduit.get(it.toLong())
                produits+=categorie.produits?.flatten()
            }
            def productCount=produits?.unique()?.size()
            def ctnerTemplateProd=params.ctnerTemplateProd?:myFormHelperService.getCheckBoxTemplate(productCount)
            if (productCount>0){
                if (params.isRadio) {
                    render template:"/partials/produitRadioBoxTemplate", model:[produits:produits?.unique(),instanceName:params.instanceName?:"produits",ctnerTemplateProd:ctnerTemplateProd]
                }else
                render template:"/partials/produitsTemplate", model:[produits:produits?.unique(),checked:true,instanceName:params.instanceName?:"produits",ctnerTemplateProd:ctnerTemplateProd]
            }
            else {
                render ""
            }
        }
    }
    def updateMarchesFromRegion(){

        def marches=[]
        if (params.regions!="null"){
            List<JSON> selectedList = JSON.parse(params.regions?.toString()) //students in request params is parsed to json objects and stored in the List
            selectedList.each{

                def marchesRegion=Region.get(it.toLong())?.getMarches()
                marches+=marchesRegion?.flatten()
            }
            def marcheCount=marches?.unique()?.size()
            def ctnerTemplateProd=params.ctnerTemplateProd?:myFormHelperService.getCheckBoxTemplate(marcheCount)
            if (marcheCount>0){
                render template:"/partials/marchesTemplate", model:[marches:marches?.unique(),checked:true,instanceName:params.instanceName?:"markets",ctnerTemplateProd:ctnerTemplateProd]
            }
            else {
                render ""
            }
        }
    }
    def updateProduitsFromReseaux(){

        def reseauxList=[]
        def produits=[]
        if (params.selectedList!="null"){
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                def reseau=Reseau.get(it.id?.toLong())
                produits+=reseau.produits?.flatten()
            }
                def productCount=produits?.unique()?.size()
                def ctnerTemplateProd=myFormHelperService.getCheckBoxTemplate(productCount)
            if (productCount>0){
                render template:"/partials/produitsTemplate", model:[produits:produits?.unique(),instanceName:params.instanceName?:"produits",ctnerTemplateProd:"my2Columns"]
            }
            else {
                render ""
            }
        }
    }


    def updateDestinatairesFromAlerte(){
        def idAlerte=params.idAlerte?.toLong()
        def alerteReseau=AlerteReseau.get(idAlerte)
        List<Utilisateur> destinataires =[]
           alerteReseau.reseaux?.each{
                def reseau=Reseau.get(it.id?.toLong())
                if (reseau)  {
                    destinataires+=reseau?.membres?.flatten() as List<Utilisateur>
                }
            }

            render template:"/partials/destinatairesTemplate", model:[listeUtilisateur:destinataires?.unique()?.sort{it.nomComplet},instanceName:params.instanceName?:"destinataires",ctnerTemplateDest:myFormHelperService.getCheckBoxTemplate(destinataires?.unique()?.size()?:0)]

    }

    def updateDestinatairesFromReseaux(){

        def reseauxList=[]
        List<Utilisateur> destinataires =[]
        if (params.selectedList!="null"){
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON> //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                def reseau=Reseau.get(it.id?.toLong())
                if (reseau)  {
                    destinataires+=reseau?.membres?.flatten() as List<Utilisateur>
                }
            }
              render template:"/partials/destinatairesTemplate", model:[listeUtilisateur:destinataires?.unique()?.sort{it.nomComplet},instanceName:params.instanceName?:"destinataires",ctnerTemplateDest:myFormHelperService.getCheckBoxTemplate(destinataires?.unique()?.size()?:0)]



        }
    }

    def searchDestinataires(){
     //   println " in searchDestinataires()"
        def prenom
        def nom
        if (params.query.contains(' ')){
            prenom= "${params.query.split(' ')[1]}%"
            nom="${params.query.split(' ')[0]}%"
        }else {
            nom="${params.query}%"
        }
            def query = {
                if (params?.query?.size()>2){
                   if  (params?.query?.substring(1,2)?.matches("[0-9]")) {
                       like("mobilePhone", "%${params.query}%")
                   } else {
                       if (params.query.contains(' ')){

                           and {
                               ilike("lastName", nom)
                           }
                           and {
                               ilike("firstName", prenom)
                           }
                       }else {
                           ilike("lastName", nom)
                       }

                   }
                }

            }
            def destinataires= Utilisateur.createCriteria().list(query).sort{it.nomComplet}

            render template:"/partials/destinatairesTemplate", model:[listeUtilisateur:destinataires,instanceName:params.instanceName?:"destinataires",ctnerTemplateDest:myFormHelperService.getCheckBoxTemplate(destinataires?.size()?:0)]

    }

    def updateUsersFromReseaux(){

        def reseauxList=[]
        List<Utilisateur> destinataires =[]
        if (params.selectedList!="null"){
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON> //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                reseauxList<<it.id
            }
            reseauxList.each{it->
                def reseau=Reseau.get(it)
                if (reseau)  {
                    destinataires+=reseau?.membres?.flatten() as List<Utilisateur>
                }
            }

            render destinataires?.unique() as JSON


        }
    }


    def updateProductFromCategories(){
        def categList=[]
        def produits=[]
        def results=[]
        def prod=[]
        if (params.selectedList!="null"){
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) //students in request params is parsed to json objects and stored in the List
            selectedList.each{
                categList<<it.id
            }
            categList.each{categ->
               prod = Produit.createCriteria().list{
               eq('categorie.id', categ.toString().toLong())

                 }
                produits+=prod
            }

            render g.selectWithOptGroup( name: 'produits',
                    from : produits?.unique(),
                    optionKey :'id',
                    size:'5',
                    multiple:'multiple',
                    optionValue: 'nom',
                    groupBy:'nomCategorie'
            )

    }
    }

    def updateMeasure(){
        def productSelectedId=params.id
        if (productSelectedId!="null"){
        def productSelected = Produit.findById(productSelectedId)
        if (productSelected){
            render g.select(optionKey: 'id', from: Mesure.list(), id: 'mesure', name: "mesure.id", value:"${productSelected.mesureId}")
        }
        else
            render g.select(optionKey: 'id', from: Mesure.list(), id: 'mesure', name: "mesure.id")
        }
        else
            render g.select(optionKey: 'id', from: Mesure.list(), id: 'mesure', name: "mesure.id")
    }
    def updatePricesFromMarket(){
        def marketSelectedId=params.id
        if (marketSelectedId!="null"){
            def marketSelected = Marche.findById(marketSelectedId)
            if (marketSelected){

                render g.select(optionKey: 'id', from: PriceHolderHeader.findAllByMarcheAndIsValidated(marketSelected,false), id: 'priceHeader', name: "priceHeader.id")
            }
            else
                render g.select(optionKey: 'id', from: PriceHolderHeader.findAllByMarcheAndIsValidated(null,false), id: 'priceHeader', name: "priceHeader.id")
        }
        else
            render g.select(optionKey: 'id', from: PriceHolderHeader.findAllByMarcheAndIsValidated(null,false), id: 'priceHeader', name: "priceHeader.id")
    }

    def updateStocksFromMarket(){
        def marketSelectedId=params.id
        if (marketSelectedId!="null"){
            def marketSelected = Marche.findById(marketSelectedId)
            if (marketSelected){
                render g.select(optionKey: 'id', from: StockMarcheHeader.findAllByMarcheAndIsValidated(marketSelected,false), id: 'stockMarcheHeader', name: "stockMarcheHeader.id")
            }
            else
                render g.select(optionKey: 'id', from: StockMarcheHeader.findAllByMarcheAndIsValidated(null,false),  id: 'stockMarcheHeader', name: "stockMarcheHeader.id")
        }
        else
            render g.select(optionKey: 'id', from: StockMarcheHeader.findAllByMarcheAndIsValidated(null,false), id: 'stockMarcheHeader', name: "stockMarcheHeader.id")
    }

    def updateStocksFromMagazin(){
        def magazinSelectedId=params.id
        if (magazinSelectedId!="null"){
            def magazinSelected = Magazin.findById(magazinSelectedId)
            if (magazinSelected){
                render g.select(optionKey: 'id', from: StockMagazinHeader.findAllByMagazinAndIsValidated(magazinSelected,false), id: 'stockMagazinHeader', name: "stockMagazinHeader.id")
            }
            else
                render g.select(optionKey: 'id', from: StockMagazinHeader.findAllByMagazinAndIsValidated(null,false),  id: 'stockMagazinHeader', name: "stockMagazinHeader.id")
        }
        else
            render g.select(optionKey: 'id', from: StockMagazinHeader.findAllByMagazinAndIsValidated(null,false), id: 'stockMagazinHeader', name: "stockMagazinHeader.id")
    }
    def updateOfferType(){
        def ProductCategoryId=params.id
        if (ProductCategoryId!="null"){
        def categorieSelected = CategorieProduit.findById(ProductCategoryId)
        if (categorieSelected){
            render g.select(optionKey: 'id', from: Produit.findAllByCategorie(categorieSelected), id: 'product', name: "product.id")
        }
        else
            render g.select(optionKey: 'id', from: Produit.list(), id: 'product', name: "product.id")
        }
        else
            render g.select(optionKey: 'id', from: Produit.list(), id: 'product', name: "product.id")
    }
    def offerOwnerList = {
        render autoCompleteService.list(params) as JSON
    }
    def locationList = {
        render autoCompleteLocaliteService.list(params) as JSON
    }
    def transporterList = {
        render autoCompleteTransporterService.list(params) as JSON
    }
    def productList = {
        render autoCompleteProductService.list(params) as JSON
    }




}
