import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_dashboard_actualites_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dashboard/_actualites.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (topNInfos?.size()?:0>0)) {
printHtmlPart(0)
for( info in (topNInfos) ) {
printHtmlPart(1)
createTagBody(3, {->
printHtmlPart(2)
expressionOut.print(info?.infoTitle)
printHtmlPart(3)
})
invokeTag('link','g',15,['controller':("info"),'action':("showPublic"),'id':(info?.id),'target':("_blank")],3)
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',28,['controller':("info"),'action':("listPublic"),'target':("_blank"),'class':("btn-flat  btn-link")],2)
printHtmlPart(7)
}
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1432271175952L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
