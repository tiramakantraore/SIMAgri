import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_produit_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/produit/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
invokeTag('message','g',7,['code':("edit.produit")],-1)
printHtmlPart(2)
expressionOut.print(produitInstance)
printHtmlPart(3)
if(true && (flash.message)) {
printHtmlPart(4)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',11,['class':("alert-info")],2)
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(6)
createTagBody(3, {->
printHtmlPart(7)
if(true && (error in FieldError)) {
printHtmlPart(8)
expressionOut.print(error.field)
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('message','g',18,['error':(error)],-1)
printHtmlPart(11)
})
invokeTag('eachError','g',19,['bean':(produitInstance),'var':("error")],3)
printHtmlPart(12)
})
invokeTag('alert','bootstrap',21,['class':("alert-error")],2)
printHtmlPart(4)
})
invokeTag('hasErrors','g',22,['bean':(produitInstance)],1)
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(14)
invokeTag('hiddenField','g',25,['name':("version"),'value':(produitInstance?.version)],-1)
printHtmlPart(15)
invokeTag('hiddenField','g',26,['name':("id"),'value':(produitInstance?.id)],-1)
printHtmlPart(16)
invokeTag('render','g',27,['template':("form")],-1)
printHtmlPart(16)
invokeTag('render','g',28,['template':("/partials/btnEdit")],-1)
printHtmlPart(17)
})
invokeTag('form','g',29,['class':("well small form-horizontal"),'action':("edit")],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416848208663L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
