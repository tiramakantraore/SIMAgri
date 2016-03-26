import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_partials_btnShow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/partials/_btnShow.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (instance)) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
invokeTag('message','g',6,['code':("edit.$controllerName")],-1)
printHtmlPart(1)
})
invokeTag('remoteLink','g',7,['onLoading':("showSpinner();"),'params':([update:update?:'listContent',id:instance?.id]),'onComplete':("hideSpinner()"),'action':("edit"),'update':(update?:'listContent'),'method':("GET"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(3)
}
else {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
invokeTag('message','g',13,['code':("edit.$controllerName")],-1)
printHtmlPart(1)
})
invokeTag('remoteLink','g',14,['onLoading':("showSpinner();"),'params':("{id:\$('#id').val()}"),'onComplete':("hideSpinner()"),'action':("edit"),'update':(update?:'listContent'),'method':("GET"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(3)
}
printHtmlPart(4)
expressionOut.print(createLink(controller:controllerName, action:'delete'))
printHtmlPart(5)
expressionOut.print(update?:'listContent')
printHtmlPart(6)
invokeTag('message','g',19,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442325557771L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
