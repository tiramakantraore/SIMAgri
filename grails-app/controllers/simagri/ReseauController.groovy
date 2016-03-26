package simagri

import grails.converters.JSON
import groovy.json.JsonBuilder
import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException

import java.lang.reflect.Array


class ReseauController {
    def exportService
    def filterPaneService
    def myUtilityService
    def reseauService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def mettreAJour() {
        Reseau.updateParent()
        renderList()
    }
    def invitation() {

    }
    def updateByJSON(){
        if (params.id!="null"){

            def marches=ReseauMarche.createCriteria().list {
                eq("reseau.id",new Long (params.id?.toString()))
                createAlias('marche','marche')
                projections {
                    distinct("marche.id")
                }
            }
            def resultsmarches=[]
            marches.each{
                resultsmarches <<"markets_${it}"
            }
            def produits=ReseauProduit.createCriteria().list {
                eq("reseau.id",new Long (params.id?.toString()))
                createAlias('produit','produit')
                projections {
                    distinct("produit.id")
                }
            }
            def membres=UtilisateurReseau.createCriteria().list {
                eq("reseau.id",new Long (params.id?.toString()))
                createAlias('utilisateur','utilisateur')
                projections {
                    distinct("utilisateur.id")
                }
            }
            def resultsmembres=[]
            membres.each{
                resultsmembres <<"membres_${it}"
            }

            def resultsproduits=[]
            produits.each{
                resultsproduits <<"produits_${it}"
            }
            def parent=new Long("0")
            if (params.isGroupe){
                def groupe=Reseau.get(new Long(params.id))
                parent=groupe?.parentId
            }
            def jsonData = [marches: resultsmarches,produits:resultsproduits,membres:resultsmembres,parent:parent,isEmpty:false]
            render jsonData as JSON
        }
        else {
            render false
        }

    }
    def deepListEdit = {checkedNodes,reseau, callback ->
        def theChildNodes = Reseau.createCriteria().list(){
            eq("parent.id",reseau.id)
            and {
                eq("active",true)
            }
            order('nom','asc')
        }
        if (theChildNodes?.size()==0) {
            [
                    id: reseau?.id,
                    checked:checkedNodes?.find{it==reseau?.id}?true:false,
                    text: "${reseau?.nom} (${reseau?.nbTotalMembres})"
            ]
        }else
            [
                    id: reseau?.id,
                    checked:checkedNodes?.find{it==reseau?.id}?true:false,
                    text: "${reseau?.nom} (${reseau?.nbTotalMembres})",
                    items: theChildNodes?.collect {callback(checkedNodes,it, callback)}
            ]
    }
    def oneLeveldeepList = {checkedNodes,reseau, callback ->

       if (!reseau?.estArrierePetitFils()){
        def theChildNodes = Reseau.createCriteria().list(){
            eq("parent.id",reseau.id)
            and {
                eq("active",true)
            }
            order('nom','asc')
        }
        if (theChildNodes?.size()==0) {
            [
                    id: reseau?.id,
                    checked:checkedNodes?.find{it==reseau?.id}?true:false,
                    text: "${reseau?.nom}"
            ]
        }else
            [
                    id: reseau?.id,
                    checked:checkedNodes?.find{it==reseau?.id}?true:false,
                    text: "${reseau?.nom}",
                    items: theChildNodes?.collect {callback(checkedNodes,it, callback)}
            ]

        }
    }
    boolean isCollectionOrArray(object) {
        [Collection, Object[]].any { it.isAssignableFrom(object.getClass()) }
    }
    def simpledeepList = {checkedNodes,reseau, callback ->
        Boolean childEmpty
        def theChildNodes = Reseau.createCriteria().list(){
            eq("parent.id",reseau.id)
            and {
                eq("active",true)
            }
            order('nom','asc')
        }

       if  (theChildNodes instanceof ArrayList)
           childEmpty=(theChildNodes?.size()==0)
       else
           childEmpty=!theChildNodes
        if (childEmpty) {
            [
                    id: reseau?.id,
                    checked:checkedNodes?.find{it==reseau?.id}?true:false,
                    text: "${reseau?.nom}"
            ]
        }else
            [
                    id: reseau?.id,
                    checked:checkedNodes?.find{it==reseau?.id}?true:false,
                    text: "${reseau?.nom}",
                    items: theChildNodes?.collect {callback(checkedNodes,it, callback)}
            ]
    }


    private def createOneLevelTree(Utilisateur user) {
        def reseauxParent
        def checkedNodes=[]
        def connectedUser=myUtilityService.getCurrent()
        if (!(connectedUser?.isSuperviseur || connectedUser?.isSuperAdmin))
            reseauxParent=connectedUser?.noeudsRacine?.unique()?.flatten()
        else
            reseauxParent=Reseau.createCriteria().list(){
                eq("estReseau",true)
                and {
                    eq("active",true)
                }
                order('nom','asc')
            }

        if (user)
            checkedNodes=user?.reseaux*.id?:[]
        if (!reseauxParent){
            reseauxParent=Reseau.createCriteria().list(){
                eq("estReseau",true)
                and {
                    eq("active",true)
                }
                order('nom','asc')
            }
        }
        def root = reseauxParent?.flatten()
        root.collect {
            oneLeveldeepList(checkedNodes,it, oneLeveldeepList)
        }
    }
    private def createNoteTree(Utilisateur user) {

        def reseauxParent=[]
        def checkedNodes=[]
        def connectedUser=myUtilityService.getCurrent()
        if (!(connectedUser?.isSuperviseur || connectedUser?.isSuperAdmin))
            reseauxParent=connectedUser?.noeudsRacine
        else
            reseauxParent=Reseau.createCriteria().list(){
                eq("estReseau",true)
                and {
                    eq("active",true)
                }
                order('nom','asc')
            }
        if (user)
         checkedNodes=user?.reseaux*.id?:[]
        if (!reseauxParent){
            reseauxParent=Reseau.createCriteria().list(){
                eq("estReseau",true)
                and {
                    eq("active",true)
                }
                order('nom','asc')
            }
        }
        def root = reseauxParent?.flatten()
        root.collect {
            deepListEdit(checkedNodes,it, deepListEdit)
        }
    }
    def getTree(){

            def userId=params?.userId
        if (userId)  {
            def user=Utilisateur.get(userId?.toLong())
            def resultat=createNoteTree(user)
            render resultat as JSON
        }else {
            def resultat=createNoteTree(null)
            render resultat as JSON
        }
    }
    def getOneLevelTree(){

        def userId=params?.userId
            if (userId)  {
                def user=Utilisateur.get(userId?.toLong())
                def resultat=createOneLevelTree(user)
                render resultat as JSON
            }
//            else {
//                if (alertId)  {
//                    def alerte=AlerteReseau.get(alertId)
//                    if (alerte){
//                        def checkedNodes=alerte?.reseaux*.id?:[]
//                        def resultat= []
//                        AlerteReseauReseau?.findAllByAlerteReseau(alerte)?.reseau*.collect {
//                            resultat<< oneLeveldeepList(checkedNodes,it, oneLeveldeepList)
//                         }
//                        println "resultat ${resultat}"
//                        render resultat as JSON
//                    }
//
//                }
                else {
                    def resultat=createOneLevelTree(null)
                    render resultat as JSON
                }


    }

    private def createSimpleNoteTree(Utilisateur user) {

        def reseauxParent
        def checkedNodes=[]
        def connectedUser=myUtilityService.getCurrent()
        if (connectedUser &&(!(connectedUser?.isSuperviseur || connectedUser?.isSuperAdmin))) {
            reseauxParent=connectedUser?.noeudsRacine?.flatten()
            if (reseauxParent.isEmpty()) {
                    reseauxParent=Reseau.createCriteria().list(){
                        eq("estReseau",true)
                        and {
                            eq("active",true)
                        }
                        order('nom','asc')
                    }
            }
        }
        else
            reseauxParent=Reseau.createCriteria().list(){
                eq("estReseau",true)
                and {
                    eq("active",true)
                }
                order('nom','asc')
            }
        if (user)
            checkedNodes=user?.reseaux*.id?:[]
        def root = reseauxParent?.flatten()
        root.collect {
            simpledeepList(checkedNodes,it, simpledeepList)
        }
    }
    private def createSimpleNoteTreeForAlerte(AlerteReseau alerte) {

        def reseauxParent=[]
        def checkedNodes
        def connectedUser=myUtilityService.getCurrent()
        connectedUser.attach()
        if (!(connectedUser?.isSuperviseur || connectedUser?.isSuperAdmin))
            reseauxParent=connectedUser?.noeudsRacine
        else  {
            reseauxParent=Reseau.createCriteria().list(){
                eq("estReseau",true)
                and {
                    eq("active",true)
                }
                order('nom','asc')
            }
        }
        if (alerte){
            if (alerte?.reseaux*.id.size()>0)
            checkedNodes=alerte?.reseaux*.id?:null

        }
        def root = reseauxParent?.flatten()
        root.collect {
            simpledeepList(checkedNodes,it, simpledeepList)
        }
    }
    def getSimpleTree(){

        def userId=params?.userId
        def alertId = params?.alertId
        if (alertId) {
            def alerte=AlerteReseau.get(alertId)
            def resultat=createSimpleNoteTreeForAlerte(alerte)
            render resultat as JSON
        }else {
                    if (userId)  {
                        def user=Utilisateur.get(userId?.toLong())
                        def resultat=createSimpleNoteTree(user)
                        render resultat as JSON
                    }else {
                        def resultat=createSimpleNoteTree(null)
                        render resultat as JSON
                    }
        }
    }
    def getSignUpTree(){
         def resultat=createSignUpTree()
         render resultat as JSON

    }
    private def createSignUpTree() {

        def checkedNodes=[]

         def   reseauxParent=Reseau.createCriteria().list(){
                eq("estReseau",true)
                and {
                    eq("active",true)
                }
                order('nom','asc')
            }
        def root = reseauxParent
        root.collect {
            simpledeepList(checkedNodes,it, simpledeepList)
        }
    }
    def getAlerteTree(){

        Long alertId = params?.alertId?.toLong()
            def alerte=AlerteReseau.get(alertId)
            def resultat=createSimpleNoteTreeForAlerte(alerte)
            render resultat as JSON

    }


    def getGroupeTree(){

        def parentId=params?.parentId?:new Long("0")
        def resultat=createSimpleNoteTree(null)
        def parentName=Reseau.get(parentId)?.nom?:""
        def data=[tree:resultat,parentText:parentName]
        render data as JSON


    }

    def list() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Reseau.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Groupes".uniquify(".${params.extension}"))



            List fields = ["nom","nomParent","produits","markets"]
            Map labels = ["nom":"NOM GROUPE","nomParent":"GROUPE PARENT", "produits":"PRODUITS","markets":"MARCHES"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [nom:humanCase,nomParent:humanCase]
            Map parameters = [title: "LISTE DES GROUPES", "column.widths": [0.3,0.2, 0.25, 0.25],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Reseau.list(params), fields, labels,formatters,parameters)
        }
        def user=myUtilityService.getCurrent()
        def newParams=params
        newParams=params+[ 'filter.op.estReseau' : 'Equal',filter:['op.estReseau':'Equal', op:[estReseau:'Equal'], 'estReseau':true]]
        render template:"list" , model:[reseauInstanceList: filterPaneService.filter(newParams, Reseau), reseauInstanceTotal: filterPaneService.count(newParams, Reseau), filterParams: FilterPaneUtils.extractFilterParams(newParams), params: newParams]

    }
    def listJSON() {
        def resauxHolders = Reseau.createCriteria().list() {
            eq('estReseau',true)
            order('nom', 'asc')
        }

        def callback=resauxHolders.collect{[reseauId:it.id,reseauName:it.nom]}


        render   callback as JSON

    }
    def listAsTree = {
        def results=[]
        def resauxHolders = Reseau.createCriteria().list() {
            projections {
                createAlias('parent','parent')
                property('nom','nom')
                property('parent.nom','parent.nom')
            }
            order('nom', 'asc')
        }

        JSON.use('deep')
        render resauxHolders as JSON
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [reseauInstanceList: filterPaneService.filter(params, Reseau),
                        reseauInstanceTotal: filterPaneService.count(params, Reseau),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                  def reseauInstance= new Reseau(params)
                reseauInstance.active=true
                reseauInstance.estReseau=true

                render template:'create',model:[reseauInstance: reseauInstance,isCreation:true]
                break
            case 'POST':
                def reseauInstance = new Reseau(params)
                def marketIds=[]
                def produitsIds=[]

                params.each {
                    if (it.key.startsWith("markets_"))
                        marketIds << (it.key - "markets_")

                }
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                def newparams=params + ['markets': marketIds]+ ['produits': produitsIds]
                reseauInstance.estReseau=true

                if (!reseauService.save(newparams,reseauInstance)) {
                    render template: 'create', model: [reseauInstance: reseauInstance,isCreation:true]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'reseau.label', default: 'Reseau'), reseauInstance.toString()])
                def result=[id:reseauInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def reseauInstance = Reseau.get(params.id)
        if (!reseauInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reseau.label', default: 'Reseau'), params.id])
            renderList()
        }

       render template:'show', model:[reseauInstance: reseauInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def reseauInstance = Reseau.get(params.id)

                if (!reseauInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'reseau.label', default: 'Reseau'), params.id])
                    renderList()
                }
                def marketsId=reseauInstance.markets?.id
                def produitsId=reseauInstance.produits?.id

                render template:'edit', model:[reseauInstance: reseauInstance,isCreation:false,marketsId:marketsId,produitsId:produitsId]
                break
            case 'POST':
                def reseauInstance = Reseau.get(params?.id)
                def marketIds=[]
                def produitsIds=[]

                params.each {
                    if (it.key.startsWith("markets_"))
                        marketIds << (it.key - "markets_")

                }
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                def newparams=params + ['markets': marketIds]+ ['produits': produitsIds]



                if (!reseauInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'reseau.label', default: 'Reseau'), params.id])
                    renderList()
                }

                if (newparams.version) {
                    def version = newparams.version.toLong()
                    if (reseauInstance.version > version) {
                        reseauInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'reseau.label', default: 'Reseau')] as Object[],
                                "Another user has updated this Reseau while you were editing")
                        render template: 'edit', model: [reseauInstance: reseauInstance]
                    }
                }

                if (!reseauService.update(newparams,reseauInstance)) {
                    render template: 'edit', model: [reseauInstance: reseauInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'reseau.label', default: 'Reseau'), reseauInstance.toString()])
                render template: 'show', model: [reseauInstance: reseauInstance]
                break
        }
    }
    def delete() {
        def reseauInstance = Reseau.get(params.id)
        def user = myUtilityService.getCurrent()
        if (user.isAdmin) {
            ReseauMarche.removeAll(reseauInstance)
            ReseauProduit.removeAll(reseauInstance)
            if (!reseauInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reseau.label', default: 'Reseau'), params.id])
                renderList()
            }

            try {
                reseauInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reseau.label', default: 'Reseau'), params.id])
                renderList()
            }
            catch (DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reseau.label', default: 'Reseau'), params.id, e.message])
                render template: 'show', model:[reseauInstance: Reseau.get(params.id)]
            }
        }else {
            flash.message = message(code: 'not.authorized.toDelete')
        }
    }

}
