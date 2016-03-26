import simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_info_listValidees_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/info/_listValidees.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('filterButton','filterpane',2,['text':("Chercher")],-1)
printHtmlPart(1)
invokeTag('message','g',6,['code':("list.infoValidees")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(4)
}
printHtmlPart(5)
for( infoInstance in (infoValideInstanceList) ) {
printHtmlPart(6)
if(true && (infoInstance.contenu)) {
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
expressionOut.print(infoInstance.titre)
})
invokeTag('remoteLink','g',24,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("show"),'update':("listInfoValideContent"),'method':("GET"),'id':(infoInstance.id),'params':("{update:'listInfoValideContent'}"),'class':("btn-flat  btn-primary")],3)
printHtmlPart(9)
expressionOut.print(infoInstance.contenu.decodeHTML())
printHtmlPart(10)
invokeTag('fieldValue','g',28,['bean':(infoInstance),'field':("url")],-1)
printHtmlPart(11)
invokeTag('display','prettytime',31,['date':(infoInstance?.date)],-1)
printHtmlPart(12)
}
else if(true && (infoInstance.url)) {
printHtmlPart(13)
createTagBody(3, {->
printHtmlPart(14)
expressionOut.print(infoInstance.titre)
})
invokeTag('remoteLink','g',42,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("show"),'update':("listInfoValideContent"),'method':("GET"),'id':(infoInstance.id),'params':("{update:'listInfoValideContent'}"),'class':("btn-flat  btn-primary")],3)
printHtmlPart(15)
if(true && (infoInstance?.url?.contains('youtube'))) {
printHtmlPart(14)
invokeTag('video','g',47,['videoKey':(infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""),'width':("100%"),'height':("350px")],-1)
printHtmlPart(16)
}
else if(true && (infoInstance.url)) {
printHtmlPart(17)
invokeTag('fieldValue','g',48,['bean':(infoInstance),'field':("url")],-1)
printHtmlPart(18)
}
printHtmlPart(19)
invokeTag('display','prettytime',52,['date':(infoInstance?.date)],-1)
printHtmlPart(20)
}
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('remoteLink','g',62,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("edit"),'update':("listInfoValideContent"),'method':("GET"),'id':(infoInstance.id),'params':("{update:'listInfoValideContent'}"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(4)
}
printHtmlPart(23)
invokeTag('remotePaginate','util',65,['controller':("info"),'update':("listInfoValideContent"),'action':("listValidees"),'total':(infoValideInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(24)
invokeTag('formats','export',69,['controller':("info"),'action':("listValidees"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(25)
invokeTag('filterPane','filterpane',69,['controller':("info"),'dialog':("true"),'domain':("simagri.Info")],-1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1446287884499L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
