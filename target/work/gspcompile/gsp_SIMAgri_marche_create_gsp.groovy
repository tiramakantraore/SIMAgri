import simagri.Marche
import  simagri.Produit
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_marche_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/marche/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
invokeTag('message','g',7,['code':("create.marche")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',11,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
if(true && (error in FieldError)) {
printHtmlPart(7)
expressionOut.print(error.field)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('message','g',18,['error':(error)],-1)
printHtmlPart(10)
})
invokeTag('eachError','g',19,['bean':(marcheInstance),'var':("error")],3)
printHtmlPart(11)
})
invokeTag('alert','bootstrap',21,['class':("alert-error")],2)
printHtmlPart(3)
})
invokeTag('hasErrors','g',22,['bean':(marcheInstance)],1)
printHtmlPart(12)
createTagBody(1, {->
printHtmlPart(13)
invokeTag('render','g',25,['template':("/partials/btnCreer")],-1)
printHtmlPart(14)
invokeTag('render','g',40,['template':("form")],-1)
printHtmlPart(15)
invokeTag('checkBoxList','bill',56,['referenceCollection':(Produit.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(16)
invokeTag('render','g',67,['template':("marchejs")],-1)
printHtmlPart(17)
invokeTag('render','g',68,['template':("/partials/btnCreer")],-1)
printHtmlPart(18)
})
invokeTag('form','g',69,['class':("well small form-horizontal"),'action':("create")],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416848359582L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
