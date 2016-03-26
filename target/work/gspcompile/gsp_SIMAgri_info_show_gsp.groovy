import simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_info_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/info/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',7,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('display','f',11,['bean':("infoInstance"),'property':("titre")],-1)
printHtmlPart(5)
invokeTag('display','f',16,['bean':("infoInstance"),'property':("contenu")],-1)
printHtmlPart(2)
invokeTag('display','f',17,['bean':("infoInstance"),'property':("auteur")],-1)
printHtmlPart(2)
invokeTag('display','f',18,['bean':("infoInstance"),'property':("date")],-1)
printHtmlPart(2)
invokeTag('display','f',19,['bean':("infoInstance"),'property':("dateExpiration")],-1)
printHtmlPart(2)
invokeTag('display','f',20,['bean':("infoInstance"),'property':("actif")],-1)
printHtmlPart(2)
invokeTag('display','f',21,['bean':("infoInstance"),'property':("url")],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',23,['name':("id"),'value':(infoInstance?.id)],-1)
printHtmlPart(7)
invokeTag('render','g',24,['template':("/partials/btnShow")],-1)
printHtmlPart(8)
})
invokeTag('form','g',26,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419280149891L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
