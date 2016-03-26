import simagri.MonImage
import  simagri.PageUtilisateur
import  simagri.PageAccueil
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageAccueil_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageAccueil/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader"),'model':([beanName:'pageAccueil'])],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',8,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(7)
expressionOut.print(error.field)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('message','g',15,['error':(error)],-1)
printHtmlPart(10)
})
invokeTag('eachError','g',16,['bean':(pageUtilisateurInstance),'var':("error")],3)
printHtmlPart(11)
})
invokeTag('alert','bootstrap',18,['class':("alert-error")],2)
printHtmlPart(3)
})
invokeTag('hasErrors','g',19,['bean':(pageUtilisateurInstance)],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('hiddenField','g',21,['name':("version"),'value':(pageAccueilInstance?.version)],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',22,['name':("theId"),'value':(pageAccueilInstance?.id)],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',23,['name':("id"),'value':(pageAccueilInstance?.id)],-1)
printHtmlPart(12)
invokeTag('display','f',43,['bean':("pageAccueilInstance"),'property':("nom")],-1)
printHtmlPart(13)
invokeTag('display','f',44,['bean':("pageAccueilInstance"),'property':("estPrincipal")],-1)
printHtmlPart(14)
invokeTag('checkBoxList','bill',58,['referenceCollection':(MonImage.list(sort: 'nom', order: 'asc')),'containerClass':(ctnerTemplateImage),'instanceName':("mesImages")],-1)
printHtmlPart(15)
invokeTag('radioBoxList','bill',62,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pagePrix"),'defaultValue':(pageAccueilInstance?.pagePrix?.nom)],-1)
printHtmlPart(16)
invokeTag('radioBoxList','bill',66,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pageOffre"),'defaultValue':(pageAccueilInstance?.pageOffre?.nom)],-1)
printHtmlPart(17)
invokeTag('radioBoxList','bill',70,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pageStock"),'defaultValue':(pageAccueilInstance?.pageStock?.nom)],-1)
printHtmlPart(18)
invokeTag('radioBoxList','bill',74,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("pageReseau"),'defaultValue':(pageAccueilInstance?.pageReseau?.nom)],-1)
printHtmlPart(19)
invokeTag('radioBoxList','bill',78,['referenceCollection':(PageUtilisateur.list(sort: 'nom', order: 'asc')),'instanceName':("banniere"),'defaultValue':(pageAccueilInstance?.banniere?.nom)],-1)
printHtmlPart(20)
invokeTag('render','g',84,['template':("/partials/btnShow")],-1)
printHtmlPart(3)
})
invokeTag('form','g',85,['class':("form-horizontal"),'action':("show")],1)
printHtmlPart(21)
expressionOut.print(createLink(controller:'pageAccueil', action:'updateByJSON'))
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328650307L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
