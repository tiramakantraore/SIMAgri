import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_dashboard_mydashboardjs_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dashboard/_mydashboardjs.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(createLink(controller:'administration', action:'setLastPrices'))
printHtmlPart(1)
expressionOut.print(createLink(controller:'administration', action:'setLastOffres'))
printHtmlPart(2)
expressionOut.print(createLink(controller:'administration', action:'setLastStocks'))
printHtmlPart(3)
expressionOut.print(createLink(controller:'autoComplete', action:'updateMarchesFromRegion'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'autoComplete', action:'updateProduitsFromCategories'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'autoComplete', action:'updateProduitsFromCategories'))
printHtmlPart(6)
expressionOut.print(createLink(controller:'home', action:'setBarStock'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'home', action:'setPieStock'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'administration', action:'actualites'))
printHtmlPart(9)
expressionOut.print(createLink(controller:'home', action:'setPieSeries'))
printHtmlPart(10)
expressionOut.print(createLink(controller:'home', action:'setBarPrix'))
printHtmlPart(11)
expressionOut.print(createLink(controller:'home', action:'setBarOffresSeries'))
printHtmlPart(12)
expressionOut.print(createLink(controller:'home', action:'setPieOffresSeries'))
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457807969315L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
