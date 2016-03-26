import simagri.SMSLogger
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_SMSLog_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/SMSLog/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',6,['code':("list.SMSLogger")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
invokeTag('hiddenField','g',28,['id':("reseauSelected"),'name':("reseauSelected")],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',30,['id':("isFirstLoad"),'name':("isFirstLoad"),'value':(isFirstLoad)],-1)
printHtmlPart(5)
invokeTag('radioBoxList','bill',31,['referenceCollection':(reseauList),'instanceName':("reseau"),'containerClass':("${ctnerTemplate} limitHeight"),'labelClass':("labelClass"),'onClickRadio':("onclickReseauOption();"),'emptyText':("Il n y a pas de réseaux")],-1)
printHtmlPart(6)
invokeTag('message','g',41,['code':("periode.text"),'default':("Période")],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',48,['id':("periodeSelected"),'name':("periodeSelected"),'value':("Le mois")],-1)
printHtmlPart(8)
invokeTag('radioBoxList','bill',51,['referenceCollection':(['Aujourd\'hui','La semaine','Le mois','Le trimestre','L\'année']),'radioName':("filterPeriodeOptionLog"),'defaultValue':("Le mois"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickPeriodeOption();")],-1)
printHtmlPart(9)
invokeTag('filterButton','filterpane',72,['text':("Rechercher")],-1)
printHtmlPart(10)
invokeTag('render','g',75,['template':("tableau")],-1)
printHtmlPart(11)
invokeTag('formats','export',78,['action':("listWithPeriode"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(12)
invokeTag('filterPane','filterpane',79,['dialog':("true"),'domain':("simagri.SMSLogger")],-1)
printHtmlPart(13)
expressionOut.print(createLink(controller:'SMSLog', action:'renderListWithPeriode'))
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328650486L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
