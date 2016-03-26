import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_partials_showHeader_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/partials/_showHeader.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (!isList)) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
invokeTag('message','g',9,['code':("list.${controllerName}")],-1)
printHtmlPart(3)
})
invokeTag('remoteLink','g',10,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("list"),'update':(update?:'listContent'),'method':("GET"),'class':("list")],2)
printHtmlPart(4)
}
printHtmlPart(5)
if(true && (canCreate || (canCreate==null))) {
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',18,['code':("create.${controllerName}")],-1)
printHtmlPart(3)
})
invokeTag('remoteLink','g',19,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':(update?:'listContent'),'method':("GET"),'class':("create")],2)
printHtmlPart(7)
}
printHtmlPart(8)
if(true && (titre)) {
printHtmlPart(9)
expressionOut.print(titre)
printHtmlPart(10)
}
else {
printHtmlPart(11)
if(true && (actionName!="filter")) {
printHtmlPart(12)
invokeTag('message','g',32,['code':("${actionName}.${controllerName}")],-1)
printHtmlPart(13)
}
else {
printHtmlPart(12)
invokeTag('message','g',35,['code':("list.${controllerName}")],-1)
printHtmlPart(13)
}
printHtmlPart(14)
}
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423446639204L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
