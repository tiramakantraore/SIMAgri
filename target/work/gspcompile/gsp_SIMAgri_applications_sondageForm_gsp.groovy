import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_sondageForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_sondageForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
expressionOut.print(sondageInstance?.titre)
printHtmlPart(3)
})
invokeTag('field','f',15,['bean':("sondageInstance"),'property':("titre")],2)
printHtmlPart(4)
invokeTag('hiddenField','g',20,['name':("details[${i}]?.id"),'value':(detail?.id)],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',21,['name':("details[${i}]?.deleted"),'value':("false")],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',22,['name':("details[${i}]?.new"),'value':(detail?.id == null?'true':'false')],-1)
printHtmlPart(6)
invokeTag('render','g',25,['template':("/sondage/details"),'model':(['sondageInstance':sondageInstance])],-1)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
expressionOut.print(sondageInstance.dateFin?.format('dd/MM/yyyy'))
printHtmlPart(9)
})
invokeTag('field','f',36,['bean':("sondageInstance"),'property':("dateFin")],2)
printHtmlPart(10)
})
invokeTag('form','g',42,['id':("sondage"),'class':("form-horizontal"),'role':("form"),'name':("createSondage")],1)
printHtmlPart(11)
invokeTag('message','g',45,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(12)
invokeTag('render','g',47,['template':("/sondage/detail"),'model':(['detail':null,'i':'_clone','hidden':true])],-1)
printHtmlPart(13)
invokeTag('render','g',1,['template':("/applications/sondagejs")],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411460L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
