import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mesureCorrespondance_tableCorrespondance_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mesureCorrespondance/_tableCorrespondance.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
invokeTag('message','g',4,['code':("tableEquivMesure.label")],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',22,['name':("selectedList")],-1)
printHtmlPart(2)
})
invokeTag('form','g',33,['class':("form-stacked-vertical myform"),'controller':("mesureCorrespondance"),'action':("saisie")],1)
printHtmlPart(3)
expressionOut.print(createLink(controller:'mesureCorrespondance', action:'populate'))
printHtmlPart(4)
expressionOut.print(createLink(controller:"mesure",action:"listOfMesure"))
printHtmlPart(5)
expressionOut.print(createLink(controller:"mesure",action:"listOfMesure"))
printHtmlPart(6)
expressionOut.print(createLink(controller:'mesureCorrespondance', action:'saisie'))
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328105175L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
