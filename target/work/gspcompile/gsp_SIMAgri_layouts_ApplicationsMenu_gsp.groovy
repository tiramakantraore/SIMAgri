import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_ApplicationsMenu_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_ApplicationsMenu.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createClosureForHtmlPart(2, 2)
invokeTag('remoteLink','g',11,['controller':("applications"),'action':("showSMS"),'data-type':("item"),'class':("list-group-item small"),'id':("sms"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(3)
createClosureForHtmlPart(4, 2)
invokeTag('remoteLink','g',15,['controller':("applications"),'action':("showEmail"),'data-type':("item"),'class':("list-group-item small"),'id':("sms"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(3)
createClosureForHtmlPart(5, 2)
invokeTag('remoteLink','g',19,['controller':("applications"),'action':("showAlertes"),'data-type':("item"),'class':("list-group-item small"),'id':("alertes"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],2)
printHtmlPart(6)
})
invokeTag('ifAdmin','sec',20,[:],1)
printHtmlPart(1)
createTagBody(1, {->
invokeTag('message','g',22,['code':("carteDesMarches.text"),'default':("Carte des marchés")],-1)
printHtmlPart(1)
})
invokeTag('remoteLink','g',23,['controller':("applications"),'action':("showCarteMarche"),'data-type':("item"),'class':("list-group-item small"),'id':("carteMarches"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(1)
createClosureForHtmlPart(7, 1)
invokeTag('remoteLink','g',26,['controller':("applications"),'action':("showCarteMagazin"),'data-type':("item"),'class':("list-group-item small"),'id':("cateMagazin"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(1)
createTagBody(1, {->
invokeTag('message','g',28,['code':("evenements.text"),'default':("Evénements")],-1)
printHtmlPart(1)
})
invokeTag('remoteLink','g',29,['controller':("applications"),'action':("showEvent"),'data-type':("item"),'class':("list-group-item small"),'id':("events"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(1)
createClosureForHtmlPart(8, 1)
invokeTag('remoteLink','g',32,['controller':("applications"),'action':("showDoc"),'data-type':("item"),'class':("list-group-item small"),'id':("docs"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(1)
createClosureForHtmlPart(9, 1)
invokeTag('remoteLink','g',35,['controller':("applications"),'action':("showQuizz"),'data-type':("item"),'class':("list-group-item small"),'id':("quizz"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(10)
createClosureForHtmlPart(11, 1)
invokeTag('remoteLink','g',38,['controller':("applications"),'action':("showSondage"),'data-type':("item"),'class':("list-group-item small"),'id':("quizz"),'update':("listContent"),'method':("GET"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner('listContent');")],1)
printHtmlPart(12)
invokeTag('message','g',41,['code':("telecharger.label"),'default':("Télécharger")],-1)
printHtmlPart(13)
createClosureForHtmlPart(14, 1)
invokeTag('link','g',45,['controller':("home"),'action':("downloadSIMAgriMob"),'class':("list-group-item small")],1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458353953756L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
