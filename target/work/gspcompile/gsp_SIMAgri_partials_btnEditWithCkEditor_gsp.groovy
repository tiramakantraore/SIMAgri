import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_partials_btnEditWithCkEditor_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/partials/_btnEditWithCkEditor.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(createLink(controller:controllerName, action:actionName))
printHtmlPart(1)
expressionOut.print(update?:'listContent')
printHtmlPart(2)
invokeTag('message','g',5,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(3)
expressionOut.print(createLink(controller:controllerName, action:'delete'))
printHtmlPart(4)
expressionOut.print(update?:'listContent')
printHtmlPart(5)
invokeTag('message','g',11,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410341L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
