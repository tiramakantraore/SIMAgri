import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configusersLoad_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/config/usersLoad.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
invokeTag('captureTitle','sitemesh',6,[:],-1)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(4)
invokeTag('img','g',12,['dir':("images"),'file':("attente.gif"),'width':("300"),'height':("300")],-1)
printHtmlPart(5)
invokeTag('submitButton','g',17,['name':("progressButton"),'value':("Initialiser les utilisateurs (1 ere partie)")],-1)
printHtmlPart(3)
})
invokeTag('form','g',17,['action':("ExecusersLoad")],2)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',17,[:],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1345660188602L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
