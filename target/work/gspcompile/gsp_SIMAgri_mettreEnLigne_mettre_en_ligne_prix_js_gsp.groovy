import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mettreEnLigne_mettre_en_ligne_prix_js_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mettreEnLigne/_mettre_en_ligne_prix_js.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(createLink(controller:"mettreEnLigne", action:"populatePrix"))
printHtmlPart(1)
expressionOut.print(createLink(controller:'mettreEnLigne', action:'canWriteToMarket'))
printHtmlPart(2)
expressionOut.print(createLink(controller:'mettreEnLigne', action:'savePrix'))
printHtmlPart(3)
expressionOut.print(createLink(controller:"mettreEnLigne", action:"populatePrix"))
printHtmlPart(4)
expressionOut.print(createLink(controller:"mesure",action:"listOfMesure"))
printHtmlPart(5)
expressionOut.print(createLink(controller:"mesure",action:"listOfMesure"))
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444686185842L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
