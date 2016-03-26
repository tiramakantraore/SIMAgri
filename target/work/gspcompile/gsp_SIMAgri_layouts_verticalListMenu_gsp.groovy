import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_verticalListMenu_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_verticalListMenu.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('remoteLink','g',12,['controller':("pageAccueil"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'id':("pageAccueil"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('remoteLink','g',16,['controller':("pageUtilisateur"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'id':("pageUtilisateur"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(4)
createClosureForHtmlPart(5, 1)
invokeTag('remoteLink','g',19,['controller':("monImage"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'id':("monImage"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(6)
createClosureForHtmlPart(7, 1)
invokeTag('remoteLink','g',31,['controller':("utilisateur"),'action':("list"),'data-type':("item"),'params':("{userType:'anonyme'}"),'class':("list-group-item small"),'id':("utilisateurs"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(8)
createClosureForHtmlPart(9, 1)
invokeTag('remoteLink','g',35,['controller':("utilisateur"),'action':("list"),'data-type':("item"),'params':("{userType:'public'}"),'class':("list-group-item small"),'id':("utilisateurs"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(10)
createTagBody(1, {->
invokeTag('message','g',38,['code':("list.enqueteur")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',39,['controller':("utilisateur"),'action':("list"),'data-type':("item"),'params':("{userType:'enqueteur'}"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(12, 1)
invokeTag('remoteLink','g',42,['controller':("performance"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(13, 1)
invokeTag('remoteLink','g',45,['controller':("utilisateur"),'action':("list"),'data-type':("item"),'params':("{userType:'admin'}"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(14, 1)
invokeTag('remoteLink','g',49,['controller':("utilisateur"),'action':("list"),'data-type':("item"),'params':("{userType:'fournisseur'}"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(11)
createClosureForHtmlPart(15, 2)
invokeTag('remoteLink','g',52,['controller':("utilisateur"),'action':("list"),'data-type':("item"),'params':("{userType:'superviseur'}"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(11)
})
invokeTag('ifSuperViseur','sec',52,[:],1)
printHtmlPart(11)
createClosureForHtmlPart(16, 1)
invokeTag('remoteLink','g',55,['controller':("groupe"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(11)
createTagBody(2, {->
invokeTag('message','g',58,['code':("list.reseau")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',59,['controller':("reseau"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(11)
})
invokeTag('ifSuperViseur','sec',60,[:],1)
printHtmlPart(17)
createClosureForHtmlPart(18, 1)
invokeTag('remoteLink','g',66,['controller':("s3Asset"),'action':("list"),'data-type':("item"),'params':("{userType:'public'}"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(19, 1)
invokeTag('remoteLink','g',74,['controller':("listeDesPrix"),'action':("show"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(20, 1)
invokeTag('remoteLink','g',76,['controller':("listeDesStocks"),'action':("show"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(21, 1)
invokeTag('remoteLink','g',79,['controller':("listeDesOffres"),'action':("show"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(22, 1)
invokeTag('remoteLink','g',82,['controller':("listeDesAlertes"),'action':("show"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(23, 1)
invokeTag('remoteLink','g',85,['controller':("listeDesInfos"),'action':("show"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(24, 1)
invokeTag('remoteLink','g',88,['controller':("listeNoteMarche"),'action':("show"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(25, 1)
invokeTag('remoteLink','g',91,['controller':("SMSLog"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'id':("sendsms"),'method':("GET"),'update':("listContent"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(26)
invokeTag('message','g',102,['code':("list.parametres")],-1)
printHtmlPart(27)
createTagBody(1, {->
printHtmlPart(11)
createClosureForHtmlPart(28, 2)
invokeTag('remoteLink','g',106,['controller':("operateur"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(29)
})
invokeTag('ifSuperAdmin','sec',108,[:],1)
printHtmlPart(30)
createTagBody(1, {->
invokeTag('message','g',111,['code':("list.civlites")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',111,['controller':("civilite"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(31, 1)
invokeTag('remoteLink','g',114,['controller':("activite"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(32, 1)
invokeTag('remoteLink','g',116,['controller':("entreprise"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(33, 1)
invokeTag('remoteLink','g',118,['controller':("produit"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createTagBody(1, {->
invokeTag('message','g',121,['code':("list.categorieProduit")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',121,['controller':("categorieProduit"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createTagBody(1, {->
invokeTag('message','g',124,['code':("list.qualites")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',124,['controller':("qualite"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createTagBody(1, {->
invokeTag('message','g',129,['code':("list.unitesmesure")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',129,['controller':("mesure"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createTagBody(1, {->
invokeTag('message','g',132,['code':("list.tableEquivalenceMesure")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',132,['controller':("mesureCorrespondance"),'data-type':("item"),'action':("saisie"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(30)
createTagBody(1, {->
invokeTag('message','g',135,['code':("list.marche")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',135,['controller':("marche"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(34, 1)
invokeTag('remoteLink','g',138,['controller':("magazin"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(35, 1)
invokeTag('remoteLink','g',140,['controller':("service"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(36, 1)
invokeTag('remoteLink','g',143,['controller':("lieux"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(37, 1)
invokeTag('remoteLink','g',146,['controller':("commune"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createClosureForHtmlPart(38, 1)
invokeTag('remoteLink','g',153,['controller':("province"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(11)
createTagBody(1, {->
invokeTag('message','g',158,['code':("list.region")],-1)
printHtmlPart(11)
})
invokeTag('remoteLink','g',158,['controller':("region"),'action':("list"),'data-type':("item"),'class':("list-group-item small"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(39)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458353953697L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
