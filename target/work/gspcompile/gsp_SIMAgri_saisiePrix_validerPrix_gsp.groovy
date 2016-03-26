import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_saisiePrix_validerPrix_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/saisiePrix/_validerPrix.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('message','g',6,['code':("priceValidation.label")],-1)
printHtmlPart(2)
invokeTag('message','g',20,['code':("quitter.label"),'default':("Quitter")],-1)
printHtmlPart(3)
invokeTag('hiddenField','g',25,['name':("selectedList")],-1)
printHtmlPart(4)
invokeTag('message','g',53,['code':("quitter.label"),'default':("Quitter")],-1)
printHtmlPart(5)
})
invokeTag('form','g',56,['class':("form-horizontal"),'action':("validerPrix")],1)
printHtmlPart(6)
expressionOut.print(createLink(controller:'saisiePrix', action:'populateValidateJSON'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'saisiePrix', action:'valider'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'saisiePrix', action:'populateValidateJSON'))
printHtmlPart(9)
expressionOut.print(createLink(controller:'home', action:'accueil'))
printHtmlPart(10)
expressionOut.print(createLink(controller:'saisiePrix', action:'rejeter'))
printHtmlPart(11)
expressionOut.print(createLink(controller:'saisiePrix', action:'populateValidateJSON'))
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328650369L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
