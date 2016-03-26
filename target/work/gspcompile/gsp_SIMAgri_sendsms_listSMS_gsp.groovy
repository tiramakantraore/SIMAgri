import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sendsms_listSMS_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sendsms/_listSMS.gsp" }
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
invokeTag('alert','bootstrap',6,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
invokeTag('filterButton','filterpane',8,['text':("Rechercher")],-1)
printHtmlPart(3)
invokeTag('remoteSortableColumn','util',14,['update':("listContent"),'action':("list"),'property':("yourTextMessage"),'title':(message(code: 'smsToReseaux.yourTextMessage.label', default: 'Your Text Message'))],-1)
printHtmlPart(4)
for( smsToReseauxInstance in (smsToReseauxInstanceList) ) {
printHtmlPart(5)
expressionOut.print(fieldValue(bean: smsToReseauxInstance, field: "yourTextMessage"))
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('remotePaginate','util',26,['update':("listContent"),'action':("list"),'total':(smsToReseauxInstanceTotal)],-1)
printHtmlPart(8)
invokeTag('filterPane','filterpane',29,['dialog':("true"),'domain':("simagri.SmsToReseaux")],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1425324211347L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
