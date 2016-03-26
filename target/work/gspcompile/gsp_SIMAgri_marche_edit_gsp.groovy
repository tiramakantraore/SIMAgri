import simagri.Marche
import  simagri.Produit
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_marche_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/marche/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('set','g',2,['var':("titreMarche"),'value':("Modifier le marché $marcheInstance")],-1)
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader"),'model':([titre:titreMarche])],-1)
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
invokeTag('eachError','g',16,['bean':(marcheInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',18,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',19,['bean':(marcheInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('render','g',22,['template':("/partials/btnEdit")],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',23,['name':("version"),'value':(marcheInstance?.version)],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',24,['name':("id"),'value':(marcheInstance?.id)],-1)
printHtmlPart(14)
invokeTag('render','g',41,['template':("form")],-1)
printHtmlPart(15)
invokeTag('checkBoxList','bill',56,['referenceCollection':(Produit.list(sort: 'nom', order: 'asc')),'containerClass':("my4Columns  limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(16)
invokeTag('checkBoxList','bill',69,['referenceCollection':(utilisateursQuiSuivent),'containerClass':("my4Columns  limitHeight"),'instanceName':("utilisateursQuiSuivent")],-1)
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
invokeTag('checkBoxList','bill',79,['referenceCollection':(mesMembresAutorises),'containerClass':("my4Columns  limitHeight"),'instanceName':("mesMembresAutorises")],-1)
printHtmlPart(17)
})
invokeTag('ifSuperViseur','sec',80,[:],2)
printHtmlPart(19)
invokeTag('render','g',82,['template':("marchejs")],-1)
printHtmlPart(20)
invokeTag('render','g',83,['template':("/partials/btnEdit")],-1)
printHtmlPart(21)
})
invokeTag('form','g',83,['class':("well small form-horizontal"),'action':("edit")],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423702337831L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
