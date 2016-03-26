import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offrecreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'offre.label', default: 'Offre'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("create.offre.title")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',14,['code':("create.offre")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',18,['class':("alert-info")],3)
printHtmlPart(6)
}
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(6)
createTagBody(3, {->
printHtmlPart(8)
createTagBody(4, {->
printHtmlPart(9)
if(true && (error in FieldError)) {
printHtmlPart(10)
expressionOut.print(error.field)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('message','g',25,['error':(error)],-1)
printHtmlPart(13)
})
invokeTag('eachError','g',26,['bean':(offreInstance),'var':("error")],4)
printHtmlPart(14)
})
invokeTag('alert','bootstrap',28,['class':("alert-error")],3)
printHtmlPart(6)
})
invokeTag('hasErrors','g',29,['bean':(offreInstance)],2)
printHtmlPart(15)
createTagBody(2, {->
printHtmlPart(16)
invokeTag('render','g',33,['template':("/offre/formCreate")],-1)
printHtmlPart(17)
invokeTag('render','g',34,['template':("/offre/btnEnregistrer")],-1)
printHtmlPart(16)
invokeTag('render','g',36,['template':("/offre/offrejs")],-1)
printHtmlPart(15)
})
invokeTag('form','g',38,['class':("form-stacked-vertical myform"),'action':("create")],2)
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',40,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1413693385367L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
