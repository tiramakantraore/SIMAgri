import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_smsToReseauxlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smsToReseaux/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'smsToReseaux.label', default: 'SmsToReseaux'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.smsToReseaux")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',16,['code':("title.smsToReseaux")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',21,['code':("create.smsToReseaux")],-1)
printHtmlPart(6)
})
invokeTag('remoteLink','g',22,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'class':("create")],2)
printHtmlPart(7)
invokeTag('message','g',31,['code':("list.smsToReseaux")],-1)
printHtmlPart(8)
if(true && (flash.message)) {
printHtmlPart(9)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',38,['class':("alert-info")],3)
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('filterButton','filterpane',40,['text':("Rechercher")],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',46,['update':("listContent"),'action':("list"),'property':("yourTextMessage"),'title':(message(code: 'smsToReseaux.yourTextMessage.label', default: 'Your Text Message'))],-1)
printHtmlPart(12)
for( smsToReseauxInstance in (smsToReseauxInstanceList) ) {
printHtmlPart(13)
createClosureForHtmlPart(14, 3)
invokeTag('link','g',55,['action':("show"),'id':(smsToReseauxInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(15)
expressionOut.print(fieldValue(bean: smsToReseauxInstance, field: "yourTextMessage"))
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('remotePaginate','util',66,['update':("listContent"),'action':("list"),'total':(smsToReseauxInstanceTotal)],-1)
printHtmlPart(18)
invokeTag('filterPane','filterpane',73,['dialog':("true"),'domain':("simagri.SmsToReseaux")],-1)
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',73,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993847L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
