import simagri.Produit
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_magazin_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/magazin/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',8,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
if(true && (error in FieldError)) {
printHtmlPart(6)
expressionOut.print(error.field)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',15,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',16,['bean':(magazinInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',18,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',19,['bean':(magazinInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('hiddenField','g',22,['name':("version"),'value':(magazinInstance?.version)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',23,['name':("id"),'value':(magazinInstance?.id)],-1)
printHtmlPart(14)
invokeTag('render','g',38,['template':("form")],-1)
printHtmlPart(15)
invokeTag('checkBoxList','bill',54,['referenceCollection':(Produit.list(sort: 'nom', order: 'asc')),'instanceName':("produits"),'containerClass':("my4Columns  limitHeight")],-1)
printHtmlPart(16)
invokeTag('render','g',65,['template':("magazinjs")],-1)
printHtmlPart(17)
invokeTag('render','g',66,['template':("/partials/btnEdit")],-1)
printHtmlPart(18)
})
invokeTag('form','g',67,['class':("well small form-horizontal"),'action':("edit")],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416856675040L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
