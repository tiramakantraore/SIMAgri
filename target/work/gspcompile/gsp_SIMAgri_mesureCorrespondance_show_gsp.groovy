import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mesureCorrespondance_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mesureCorrespondance/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',7,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('display','f',10,['bean':("mesureCorrespondanceInstance"),'property':("mesureDepart")],-1)
printHtmlPart(3)
invokeTag('display','f',11,['bean':("mesureCorrespondanceInstance"),'property':("mesureDestination")],-1)
printHtmlPart(3)
invokeTag('display','f',12,['bean':("mesureCorrespondanceInstance"),'property':("equivalence")],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',16,['name':("id"),'value':(mesureCorrespondanceInstance?.id)],-1)
printHtmlPart(5)
invokeTag('render','g',17,['template':("/partials/btnShow")],-1)
printHtmlPart(2)
})
invokeTag('form','g',18,[:],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419280149694L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
