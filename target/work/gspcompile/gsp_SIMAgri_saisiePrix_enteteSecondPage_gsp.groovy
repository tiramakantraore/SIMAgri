import simagri.Marche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_saisiePrix_enteteSecondPage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/saisiePrix/_enteteSecondPage.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (marche)) {
printHtmlPart(1)
invokeTag('message','g',7,['code':("price.market.label"),'default':("Nom marché")],-1)
printHtmlPart(2)
invokeTag('fieldValue','g',7,['bean':(marche),'field':("nom")],-1)
printHtmlPart(3)
}
printHtmlPart(4)
if(true && (priceInstance?.date)) {
printHtmlPart(1)
invokeTag('message','g',13,['code':("price.date.label"),'default':("Date")],-1)
printHtmlPart(5)
invokeTag('fieldValue','g',14,['bean':(priceInstance),'field':("date")],-1)
printHtmlPart(3)
}
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1407035061204L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
