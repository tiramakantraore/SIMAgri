import simagri.Performance
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_performance_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/performance/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('render','g',4,['template':("/partials/showHeader")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('display','f',12,['bean':("performanceInstance"),'property':("nom"),'input-class':("form-control")],-1)
printHtmlPart(4)
invokeTag('display','f',13,['bean':("performanceInstance"),'property':("nbOffre"),'input-class':("form-control")],-1)
printHtmlPart(4)
invokeTag('display','f',14,['bean':("performanceInstance"),'property':("nbPrix"),'input-class':("form-control")],-1)
printHtmlPart(4)
invokeTag('display','f',15,['bean':("performanceInstance"),'property':("nbAlerte"),'input-class':("form-control")],-1)
printHtmlPart(4)
invokeTag('display','f',16,['bean':("performanceInstance"),'property':("nbContact"),'input-class':("form-control")],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',19,['name':("id"),'value':(performanceInstance?.id)],-1)
printHtmlPart(6)
invokeTag('render','g',20,['template':("/partials/btnShow")],-1)
printHtmlPart(3)
})
invokeTag('form','g',21,[:],1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419280149669L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
