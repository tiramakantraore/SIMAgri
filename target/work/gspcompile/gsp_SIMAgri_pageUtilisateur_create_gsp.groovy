import simagri.PageUtilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageUtilisateur_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageUtilisateur/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',6,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',14,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(6)
expressionOut.print(error.field)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',21,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',22,['bean':(pageUtilisateurInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',24,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',25,['bean':(pageUtilisateurInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('render','g',28,['template':("/partials/btnCreerWithCkEditor")],-1)
printHtmlPart(12)
invokeTag('render','g',29,['template':("form")],-1)
printHtmlPart(12)
invokeTag('render','g',30,['template':("/partials/btnCreerWithCkEditor")],-1)
printHtmlPart(13)
})
invokeTag('form','g',30,['name':("create"),'id':("myForm"),'class':("well small form-horizontal")],1)
printHtmlPart(14)
expressionOut.print(createLink(controller:'pageUtilisateur', action:'postcreate'))
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442325558502L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
