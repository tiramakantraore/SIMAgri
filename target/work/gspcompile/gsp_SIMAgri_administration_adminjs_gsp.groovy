import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_adminjs_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_adminjs.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('remoteFunction','g',40,['controller':("administration"),'action':("listPrix"),'onloading':("showSpinner(true);"),'update':("prix"),'params':("{searchvalue: searchText, columnSelected: columnSelectedVal}")],-1)
printHtmlPart(1)
expressionOut.print(createLink(controller:'home', action:'setPerfEnqueteurs'))
printHtmlPart(2)
expressionOut.print(createLink(controller:'home', action:'setPivotPrices'))
printHtmlPart(3)
expressionOut.print(createLink(controller:'home', action:'setPivotOffers'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'home', action:'setPivotStocks'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'home', action:'setBilanSMS'))
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442327518961L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
