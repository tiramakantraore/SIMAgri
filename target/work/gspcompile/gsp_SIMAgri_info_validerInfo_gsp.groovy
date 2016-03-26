import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_info_validerInfo_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/info/_validerInfo.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',5,['code':("validerInfos.label")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('render','g',6,['template':("validatedBtn")],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',6,['name':("selectedList")],-1)
printHtmlPart(3)
invokeTag('render','g',20,['template':("validatedBtn")],-1)
printHtmlPart(4)
})
invokeTag('form','g',20,['class':("form-horizontal"),'action':("validerInfo")],1)
printHtmlPart(5)
expressionOut.print(createLink(controller:'info', action:'populate'))
printHtmlPart(6)
expressionOut.print(createLink(controller:'info', action:'valider'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'info', action:'populate'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'home', action:'accueil'))
printHtmlPart(9)
expressionOut.print(createLink(controller:'info', action:'rejeter'))
printHtmlPart(10)
expressionOut.print(createLink(controller:'info', action:'populate'))
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328105097L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
