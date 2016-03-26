import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_statistiquesSMSOperateurs_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_statistiquesSMSOperateurs.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (smsMO)) {
printHtmlPart(1)
for( g in (smsMO) ) {
printHtmlPart(2)
expressionOut.print(g.operateur)
printHtmlPart(3)
invokeTag('formatNumber','g',17,['number':(g.smsCount),'format':("###,##0")],-1)
printHtmlPart(4)
}
printHtmlPart(5)
}
printHtmlPart(6)
if(true && (smsMT)) {
printHtmlPart(7)
for( g in (smsMT) ) {
printHtmlPart(2)
expressionOut.print(g.operateur)
printHtmlPart(3)
invokeTag('formatNumber','g',34,['number':(g.smsCount),'format':("###,##0")],-1)
printHtmlPart(4)
}
printHtmlPart(5)
}
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423226416908L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
