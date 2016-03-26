import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_cartesGeo_carteDesStocks_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/cartesGeo/_carteDesStocks.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
loop:{
int i = 0
for( magazin in (magazinList) ) {
printHtmlPart(2)
expressionOut.print(magazin.id)
printHtmlPart(3)
expressionOut.print(magazin.localite?.latitude?:0)
printHtmlPart(4)
expressionOut.print(magazin.localite?.longitude?:0)
printHtmlPart(5)
expressionOut.print(magazin.nom)
printHtmlPart(6)
expressionOut.print(magazin.id)
printHtmlPart(7)
expressionOut.print(magazin.id)
printHtmlPart(8)
expressionOut.print(magazin.id)
printHtmlPart(9)
expressionOut.print(magazin.nom)
printHtmlPart(10)
expressionOut.print(magazin.titrecontenu)
printHtmlPart(11)
expressionOut.print(magazin.contenu)
printHtmlPart(12)
expressionOut.print(magazin.id)
printHtmlPart(13)
expressionOut.print(magazin.id)
printHtmlPart(14)
expressionOut.print(magazin.id)
printHtmlPart(15)
expressionOut.print(magazin.id)
printHtmlPart(16)
i++
}
}
printHtmlPart(17)
})
invokeTag('captureHead','sitemesh',91,[:],1)
printHtmlPart(18)
createClosureForHtmlPart(19, 1)
invokeTag('captureBody','sitemesh',96,['onload':("initialize()")],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1379442909224L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
