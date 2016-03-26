import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sondage_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sondage/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(sondageInstance?.titre)
printHtmlPart(2)
})
invokeTag('field','f',9,['bean':("sondageInstance"),'property':("titre")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(formatDate(format:'dd/MM/yyyy',date:sondageInstance.dateDebut))
printHtmlPart(5)
})
invokeTag('field','f',26,['bean':("sondageInstance"),'property':("dateDebut")],1)
printHtmlPart(6)
invokeTag('field','f',29,['bean':("sondageInstance"),'property':("duree"),'input-class':("form-control")],-1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(sondageInstance.dateFin?.format('dd/MM/yyyy'))
printHtmlPart(5)
})
invokeTag('field','f',38,['bean':("sondageInstance"),'property':("dateFin")],1)
printHtmlPart(9)
invokeTag('message','g',46,['code':("sondage.actif.label"),'default':("Actif ?")],-1)
printHtmlPart(10)
invokeTag('input','f',48,['bean':("sondageInstance"),'property':("actif"),'id':("actif")],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',57,['name':("details[${i}]?.id"),'value':(detail?.id)],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',60,['name':("details[${i}]?.deleted"),'value':("false")],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',61,['name':("details[${i}]?.new"),'value':(detail?.id == null?'true':'false')],-1)
printHtmlPart(13)
invokeTag('render','g',65,['template':("details"),'model':(['sondageInstance':sondageInstance])],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1411759002196L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
