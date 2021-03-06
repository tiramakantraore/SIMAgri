import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_alertesjs_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_alertesjs.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(createLink(controller:'autoComplete', action:'updateMarchesFromRegion'))
printHtmlPart(1)
expressionOut.print(createLink(controller:'autoComplete', action:'updateProduitsFromCategories'))
printHtmlPart(2)
expressionOut.print(createLink(controller:'autoComplete', action:'updateDestinatairesFromAlerte'))
printHtmlPart(3)
expressionOut.print(createLink(controller:'applications', action:'updateAlerteByJSON'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'autoComplete', action:'searchDestinataires'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'autoComplete', action:'updateDestinatairesFromReseaux'))
printHtmlPart(6)
expressionOut.print(createLink(controller:'alerteReseau', action:'sendByService'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'alerteReseau', action:'sendByService'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'alerteReseau', action:'deleteByService'))
printHtmlPart(9)
expressionOut.print(createLink(controller:'listeDesAlertes', action:'show'))
printHtmlPart(10)
expressionOut.print(createLink(controller:'reseau', action:'getAlerteTree'))
printHtmlPart(11)
expressionOut.print(createLink(controller:'reseau', action:'getAlerteTree'))
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1456875161221L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
