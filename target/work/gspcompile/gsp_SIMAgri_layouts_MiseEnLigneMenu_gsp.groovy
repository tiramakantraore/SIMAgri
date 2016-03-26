import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_MiseEnLigneMenu_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_MiseEnLigneMenu.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('remoteLink','g',10,['controller':("mettreEnLigne"),'action':("showPrix"),'data-type':("item"),'class':("list-group-item small"),'id':("mettreEnLignePrix"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('remoteLink','g',13,['controller':("mettreEnLigne"),'action':("showOffres"),'data-type':("item"),'class':("list-group-item small"),'id':("offres"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}")],1)
printHtmlPart(2)
createClosureForHtmlPart(4, 1)
invokeTag('remoteLink','g',16,['controller':("mettreEnLigne"),'action':("showStocks"),'data-type':("item"),'class':("list-group-item small"),'id':("stocks"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}")],1)
printHtmlPart(2)
createClosureForHtmlPart(5, 1)
invokeTag('remoteLink','g',19,['controller':("mettreEnLigne"),'action':("showInfos"),'data-type':("item"),'class':("list-group-item small"),'id':("infos"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}")],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('remoteLink','g',23,['controller':("mettreEnLigne"),'action':("showProduits"),'data-type':("item"),'class':("list-group-item small"),'id':("produits"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}")],2)
printHtmlPart(6)
createTagBody(2, {->
invokeTag('message','g',25,['code':("miseEnLigne.marches.text"),'default':("Mise en Ligne des marchés")],-1)
printHtmlPart(6)
})
invokeTag('remoteLink','g',26,['controller':("mettreEnLigne"),'action':("showMarches"),'data-type':("item"),'class':("list-group-item small"),'id':("marches"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}")],2)
printHtmlPart(6)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',29,['controller':("mettreEnLigne"),'action':("showUsers"),'data-type':("item"),'class':("list-group-item small"),'id':("users"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'mettreEnLigne', action:'list')}');}")],2)
printHtmlPart(9)
})
invokeTag('ifAdmin','sec',30,[:],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457836571884L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
