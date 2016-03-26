import simagri.Marche
import  simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_statistiques_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_statistiques.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('hiddenField','g',49,['id':("periodeSelected"),'name':("periodeSelected"),'value':("Le mois")],-1)
printHtmlPart(2)
invokeTag('radioBoxList','bill',51,['referenceCollection':(['Aujourd\'hui','La semaine','Le mois','Le trimestre','L\'année']),'radioName':("filterPeriodeOption"),'defaultValue':("Le mois"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickPeriodeOption();")],-1)
printHtmlPart(3)
invokeTag('hiddenField','g',80,['id':("typeVisiteurs"),'name':("typeVisiteurs")],-1)
printHtmlPart(4)
invokeTag('radioBoxList','bill',82,['referenceCollection':(['Oui','Non']),'radioName':("filterTypeVisiteurs"),'defaultValue':("Oui"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickTypeVisiteurs();")],-1)
printHtmlPart(5)
invokeTag('render','g',106,['template':("statistiquesInscrits")],-1)
printHtmlPart(6)
invokeTag('render','g',114,['template':("statistiquesSMSOperateurs")],-1)
printHtmlPart(7)
invokeTag('render','g',121,['template':("statistiquesjs")],-1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1429146388093L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
