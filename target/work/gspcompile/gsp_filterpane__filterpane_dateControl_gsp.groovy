import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='filterpane', version='2.4.5')
class gsp_filterpane__filterpane_dateControl_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/filterpane-2.4.5/grails-app/views/_filterpane/_dateControl.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(ctrlAttrs.id)
printHtmlPart(1)
expressionOut.print(ctrlAttrs.style)
printHtmlPart(2)
invokeTag('datePicker','filterpane',2,['ctrlAttrs':(ctrlAttrs)],-1)
printHtmlPart(3)
if(true && (ctrlAttrs.name?.endsWith('To'))) {
printHtmlPart(4)
expressionOut.print(ctrlAttrs.domain)
printHtmlPart(5)
expressionOut.print(ctrlAttrs.propertyName)
printHtmlPart(6)
expressionOut.print(ctrlAttrs.isDayPrecision)
printHtmlPart(7)
}
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1406203364000L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
