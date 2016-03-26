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

class gsp_SIMAgri_groupe_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/groupe/_edit.gsp" }
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
invokeTag('eachError','g',16,['bean':(reseauInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',18,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',19,['bean':(reseauInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
expressionOut.print(createLink(controller:'groupe', action:'edit'))
printHtmlPart(13)
invokeTag('message','g',23,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(14)
expressionOut.print(createLink(controller:'groupe', action:'delete'))
printHtmlPart(15)
invokeTag('message','g',28,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(16)
invokeTag('hiddenField','g',32,['name':("parentText"),'value':(reseauInstance?.parent?.nom)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',33,['name':("parentIdSaved"),'value':(reseauInstance?.parent?.id)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',34,['name':("id"),'value':(reseauInstance?.id)],-1)
printHtmlPart(18)
invokeTag('radioBoxList','bill',51,['referenceCollection':(Reseau.findAllByActive(true)),'instanceName':("parent"),'containerClass':("my4Columns"),'labelClass':("labelClass")],-1)
printHtmlPart(19)
invokeTag('render','g',60,['template':("form")],-1)
printHtmlPart(20)
invokeTag('checkBoxList','bill',76,['referenceCollection':(Marche.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("markets")],-1)
printHtmlPart(21)
invokeTag('checkBoxList','bill',90,['referenceCollection':(Produit.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(22)
invokeTag('checkBoxList','bill',108,['referenceCollection':(mesMembres),'containerClass':("my4Columns  limitHeight"),'instanceName':("membres")],-1)
printHtmlPart(23)
invokeTag('render','g',109,['template':("/partials/btnEdit")],-1)
printHtmlPart(24)
})
invokeTag('form','g',109,['class':("form-horizontal"),'name':("edit"),'action':("edit")],1)
printHtmlPart(25)
invokeTag('render','g',113,['template':("groupejs")],-1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448135197690L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
