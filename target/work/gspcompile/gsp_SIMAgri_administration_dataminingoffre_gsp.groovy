import simagri.Mesure
import  simagri.Marche
import  simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_dataminingoffre_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_dataminingoffre.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',5,['template':("periode")],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',32,['id':("mesureSelected"),'name':("mesureSelected")],-1)
printHtmlPart(2)
invokeTag('radioBoxList','bill',34,['referenceCollection':(Mesure.list()),'instanceName':("mesure"),'containerClass':("my4Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickMesureOption();"),'emptyText':("Il n y a pas de mesures, veuillez vérifier votre profil")],-1)
printHtmlPart(3)
invokeTag('render','g',51,['template':("dataminingoffrejs")],-1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419972084510L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
