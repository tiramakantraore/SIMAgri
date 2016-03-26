import simagri.PageUtilisateur
import  simagri.MonImage
import  simagri.PageAccueil
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageAccueil_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageAccueil/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
expressionOut.print(flash.message)
printHtmlPart(3)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(7)
expressionOut.print(error.field)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('message','g',11,['error':(error)],-1)
printHtmlPart(10)
})
invokeTag('eachError','g',11,['bean':(pageAccueilInstance),'var':("error")],2)
printHtmlPart(11)
})
invokeTag('hasErrors','g',11,['bean':(pageAccueilInstance)],1)
printHtmlPart(12)
createTagBody(1, {->
printHtmlPart(13)
invokeTag('render','g',17,['template':("/partials/btnCreer")],-1)
printHtmlPart(13)
invokeTag('render','g',18,['template':("form")],-1)
printHtmlPart(13)
invokeTag('render','g',19,['template':("/partials/btnCreer")],-1)
printHtmlPart(14)
})
invokeTag('form','g',20,['name':("create"),'class':("form-stacked-horizontal")],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278310464L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
