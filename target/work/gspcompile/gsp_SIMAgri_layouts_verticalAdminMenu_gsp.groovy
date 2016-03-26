import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_verticalAdminMenu_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_verticalAdminMenu.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('remoteLink','g',11,['controller':("saisiePrix"),'action':("validerPrix"),'data-type':("item"),'class':("list-group-item small"),'id':("Prix"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('remoteLink','g',14,['controller':("offre"),'action':("validerOffre"),'data-type':("item"),'class':("list-group-item small"),'id':("offre"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(4, 1)
invokeTag('remoteLink','g',17,['controller':("stockMagazin"),'action':("validerStocks"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'id':("stocks"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(5, 1)
invokeTag('remoteLink','g',20,['controller':("alerteReseau"),'action':("validerAlerte"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'id':("alertes"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(6, 1)
invokeTag('remoteLink','g',23,['controller':("info"),'action':("validerInfo"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'id':("infos"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(7)
createClosureForHtmlPart(8, 1)
invokeTag('remoteLink','g',27,['controller':("noteMarche"),'action':("validerNote"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'id':("infos"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(9)
createClosureForHtmlPart(10, 1)
invokeTag('remoteLink','g',37,['controller':("administration"),'action':("statistiques"),'data-type':("item"),'class':("list-group-item small"),'id':("Statistiques"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(7)
createClosureForHtmlPart(11, 1)
invokeTag('remoteLink','g',41,['controller':("administration"),'action':("dataminingprix"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(12, 1)
invokeTag('remoteLink','g',44,['controller':("administration"),'action':("dataminingoffre"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(13, 1)
invokeTag('remoteLink','g',47,['controller':("administration"),'action':("dataminingstock"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createTagBody(1, {->
expressionOut.print(g.message(code:"analysePerfEnqueteurs.text", default:" Analyse des Performances des enquêteurs"))
printHtmlPart(2)
})
invokeTag('remoteLink','g',50,['controller':("administration"),'action':("dataminingperformance"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(14, 1)
invokeTag('remoteLink','g',53,['controller':("administration"),'action':("dataminingsms"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(15)
createTagBody(1, {->
printHtmlPart(16)
createClosureForHtmlPart(17, 2)
invokeTag('remoteLink','g',71,['controller':("quartz"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'id':("myJobConfig"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(2)
createClosureForHtmlPart(18, 2)
invokeTag('remoteLink','g',73,['controller':("sendsms"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'id':("sendsms"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(g.message(code:"mettreAJourRole.text", default:"Mettre à jour les rôles"))
printHtmlPart(2)
})
invokeTag('remoteLink','g',76,['controller':("home"),'data-type':("item"),'action':("mettre_a_jour_les_roles"),'class':("list-group-item small"),'id':("mettre_a_jour_les_roles"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('remoteLink','g',81,['controller':("home"),'data-type':("item"),'action':("supprimerAlertes"),'class':("list-group-item small"),'id':("supprimerAlertes"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(21)
createTagBody(2, {->
expressionOut.print(g.message(code:"gestionParamnDyn.text", default:"Gestion des paramètres dynamiques"))
printHtmlPart(2)
})
invokeTag('remoteLink','g',86,['controller':("ConfigProperty"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'id':("requestmap"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(2)
createClosureForHtmlPart(22, 2)
invokeTag('remoteLink','g',88,['controller':("alerteReseau"),'action':("listeexecute"),'data-type':("item"),'class':("list-group-item small"),'method':("GET"),'id':("listeexecute"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(23)
})
invokeTag('ifSuperAdmin','sec',89,[:],1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458353953732L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
