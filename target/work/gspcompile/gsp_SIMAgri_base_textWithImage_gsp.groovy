import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_base_textWithImage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/base/_textWithImage.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (imageUrl)) {
printHtmlPart(0)
if(true && (imagePosition=="Haut")) {
printHtmlPart(1)
expressionOut.print(imageUrl)
printHtmlPart(2)
expressionOut.print(wrappingClass)
printHtmlPart(3)
expressionOut.print(texte)
printHtmlPart(4)
}
else {
printHtmlPart(5)
if(true && (imagePosition=="Bas")) {
printHtmlPart(6)
expressionOut.print(texte)
printHtmlPart(7)
expressionOut.print(imageUrl)
printHtmlPart(2)
expressionOut.print(wrappingClass)
printHtmlPart(8)
}
else {
printHtmlPart(9)
expressionOut.print(imageUrl)
printHtmlPart(2)
expressionOut.print(wrappingClass)
printHtmlPart(10)
expressionOut.print(texte)
printHtmlPart(11)
}
printHtmlPart(0)
}
printHtmlPart(12)
}
else {
printHtmlPart(13)
expressionOut.print(texte)
printHtmlPart(14)
}
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1415843356405L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
