import simagri.Reseau
import  simagri.Marche
import  simagri.Produit
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_groupe_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/groupe/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',7,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(3)
createTagBody(3, {->
printHtmlPart(4)
if(true && (error in FieldError)) {
printHtmlPart(5)
expressionOut.print(error.field)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('message','g',11,['error':(error)],-1)
printHtmlPart(8)
})
invokeTag('eachError','g',11,['bean':(reseauInstance),'var':("error")],3)
printHtmlPart(9)
})
invokeTag('alert','bootstrap',14,['class':("alert-error")],2)
printHtmlPart(1)
})
invokeTag('hasErrors','g',14,['bean':(reseauInstance)],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(11)
invokeTag('render','g',18,['template':("/partials/btnShow")],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',22,['name':("parentText"),'value':(reseauInstance?.parent?.nom)],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',24,['name':("parentIdSaved"),'value':(reseauInstance?.parent?.id)],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',25,['name':("theId"),'value':(reseauInstance?.id)],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',29,['name':("id"),'value':(reseauInstance?.id)],-1)
printHtmlPart(13)
invokeTag('radioBoxList','bill',45,['referenceCollection':(Reseau.findAllByActive(true)),'instanceName':("parent"),'containerClass':("my4Columns"),'labelClass':("labelClass")],-1)
printHtmlPart(14)
invokeTag('render','g',52,['template':("form")],-1)
printHtmlPart(15)
invokeTag('checkBoxList','bill',61,['referenceCollection':(Marche.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("markets")],-1)
printHtmlPart(16)
invokeTag('checkBoxList','bill',69,['referenceCollection':(Produit.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(17)
invokeTag('checkBoxList','bill',75,['referenceCollection':(mesMembres),'containerClass':("my4Columns  limitHeight"),'instanceName':("membres")],-1)
printHtmlPart(18)
invokeTag('render','g',79,['template':("/partials/btnShow")],-1)
printHtmlPart(19)
})
invokeTag('form','g',81,['class':("form-horizontal"),'action':("show")],1)
printHtmlPart(20)
invokeTag('render','g',81,['template':("groupejs")],-1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422390925372L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
