import simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_infolistPublic_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/info/listPublic.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'info.label', default: 'Info'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.info")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('filterButton','filterpane',21,['text':("Chercher")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',25,['class':("alert-info")],3)
printHtmlPart(7)
}
printHtmlPart(8)
for( infoInstance in (infoInstanceList) ) {
printHtmlPart(9)
if(true && (infoInstance.contenu)) {
printHtmlPart(10)
expressionOut.print(infoInstance.id)
printHtmlPart(11)
createTagBody(4, {->
expressionOut.print(infoInstance.titre)
})
invokeTag('link','g',44,['action':("showPublic"),'id':(infoInstance.id)],4)
printHtmlPart(12)
expressionOut.print(infoInstance.contenu.decodeHTML())
printHtmlPart(13)
invokeTag('fieldValue','g',48,['bean':(infoInstance),'field':("url")],-1)
printHtmlPart(14)
invokeTag('display','prettytime',50,['date':(infoInstance?.date)],-1)
printHtmlPart(15)
}
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('remotePaginate','util',53,['update':("listContent"),'action':("list"),'total':(infoInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(18)
invokeTag('formats','export',54,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(19)
invokeTag('filterPane','filterpane',64,['dialog':("true"),'domain':("simagri.Info")],-1)
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',64,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419205733429L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
