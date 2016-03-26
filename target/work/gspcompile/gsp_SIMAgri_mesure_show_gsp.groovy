import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mesure_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mesure/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',8,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('display','f',11,['bean':("mesureInstance"),'property':("code")],-1)
printHtmlPart(3)
invokeTag('display','f',12,['bean':("mesureInstance"),'property':("name")],-1)
printHtmlPart(3)
invokeTag('display','f',13,['bean':("mesureInstance"),'property':("isLocal")],-1)
printHtmlPart(3)
invokeTag('display','f',14,['bean':("mesureInstance"),'property':("description")],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',18,['name':("id"),'value':(mesureInstance?.id)],-1)
printHtmlPart(5)
invokeTag('render','g',19,['template':("/partials/btnShow")],-1)
printHtmlPart(2)
})
invokeTag('form','g',20,[:],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419280149963L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
