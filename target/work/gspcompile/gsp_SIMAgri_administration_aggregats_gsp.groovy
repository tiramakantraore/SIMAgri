import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_aggregats_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_aggregats.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
loop:{
int i = 0
for( aggregat in (aggregats) ) {
printHtmlPart(1)
invokeTag('radio','g',6,['id':("${aggregat}_${i}"),'name':("aggregat"),'value':(aggregat),'checked':(aggregat == "Prix"),'class':("myRadiostyle"),'onclick':("ongroupeclick();")],-1)
printHtmlPart(2)
expressionOut.print(aggregat)
printHtmlPart(3)
expressionOut.print(i)
printHtmlPart(4)
expressionOut.print(aggregat)
printHtmlPart(5)
expressionOut.print(aggregat)
printHtmlPart(6)
i++
}
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1413759270884L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
