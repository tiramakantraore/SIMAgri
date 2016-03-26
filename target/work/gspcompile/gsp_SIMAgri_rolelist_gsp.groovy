import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_rolelist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/role/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',7,['code':("title.role")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',12,['code':("create.role")],-1)
printHtmlPart(3)
})
invokeTag('link','g',13,['class':("create"),'action':("create")],1)
printHtmlPart(4)
invokeTag('message','g',22,['code':("list.role")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',26,['class':("alert-info")],2)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('filterButton','filterpane',28,['text':("Rechercher")],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',34,['update':("listContent"),'action':("list"),'property':("authority"),'title':(message(code: 'role.authority.label', default: 'Authority'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',36,['update':("listContent"),'action':("list"),'property':("description"),'title':(message(code: 'role.description.label', default: 'Description'))],-1)
printHtmlPart(10)
for( roleInstance in (roleInstanceList) ) {
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('remoteLink','g',47,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(roleInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: roleInstance, field: "authority"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: roleInstance, field: "description"))
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('remotePaginate','util',60,['update':("listContent"),'action':("list"),'total':(roleInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(17)
invokeTag('formats','export',63,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(18)
invokeTag('filterPane','filterpane',67,['dialog':("true"),'domain':("simagri.Role")],-1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993733L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
