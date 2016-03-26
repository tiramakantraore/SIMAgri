import simagri.PageAccueil
import  simagri.Produit
import  simagri.Marche
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_reseau_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reseau/_show.gsp" }
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
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
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
invokeTag('message','g',17,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',18,['bean':(reseauInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',20,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',21,['bean':(reseauInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('render','g',24,['template':("/partials/btnShow")],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',25,['name':("parentText"),'value':(reseauInstance?.parent?.nom)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',26,['name':("parentIdSaved"),'value':(reseauInstance?.parent?.id)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',27,['name':("theId"),'value':(reseauInstance?.id)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',28,['name':("id"),'value':(reseauInstance?.id)],-1)
printHtmlPart(14)
invokeTag('render','g',47,['template':("formshow")],-1)
printHtmlPart(15)
invokeTag('radioBoxList','bill',55,['referenceCollection':(PageAccueil.findAllByEstPrincipal(false)),'instanceName':("pageAccueil"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'title':("Page d'accueil personnalisée")],-1)
printHtmlPart(16)
invokeTag('checkBoxList','bill',63,['referenceCollection':(Marche.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("markets")],-1)
printHtmlPart(17)
invokeTag('checkBoxList','bill',69,['referenceCollection':(Produit.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(18)
invokeTag('render','g',80,['template':("/partials/btnShow")],-1)
printHtmlPart(11)
})
invokeTag('form','g',82,['class':("form-horizontal"),'id':(reseauInstance?.id)],1)
printHtmlPart(19)
invokeTag('render','g',89,['template':("groupeshowjs")],-1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417062227695L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
