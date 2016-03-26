import simagri.MonImage
import  simagri.PageUtilisateur
import  simagri.PageAccueil
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageAccueil_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageAccueil/_edit.gsp" }
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
invokeTag('alert','bootstrap',7,['class':("alert-info")],2)
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
invokeTag('message','g',14,['error':(error)],-1)
printHtmlPart(10)
})
invokeTag('eachError','g',15,['bean':(pageUtilisateurInstance),'var':("error")],3)
printHtmlPart(11)
})
invokeTag('alert','bootstrap',17,['class':("alert-error")],2)
printHtmlPart(3)
})
invokeTag('hasErrors','g',18,['bean':(pageUtilisateurInstance)],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('hiddenField','g',21,['name':("version"),'value':(pageAccueilInstance?.version)],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',22,['name':("theId"),'value':(pageAccueilInstance?.id)],-1)
printHtmlPart(13)
invokeTag('render','g',24,['template':("form")],-1)
printHtmlPart(14)
expressionOut.print(createLink(controller:'pageAccueil', action:'edit'))
printHtmlPart(15)
invokeTag('message','g',30,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(16)
expressionOut.print(createLink(controller:'pageAccueil', action:'delete'))
printHtmlPart(17)
invokeTag('message','g',35,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(18)
})
invokeTag('form','g',38,['name':("edit"),'method':("POST"),'class':("form-horizontal")],1)
printHtmlPart(19)
expressionOut.print(createLink(controller:'pageAccueil', action:'updateByJSON'))
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458353745710L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
