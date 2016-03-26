import simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_listeDesInfos_index_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/listeDesInfos/_index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(g.message(code:"infoAValider.text", default:"Infos à valider"))
printHtmlPart(1)
expressionOut.print(g.message(code:"infoAValider.text", default:"Infos à valider"))
printHtmlPart(2)
expressionOut.print(g.message(code:"infoValides.text", default:"Infos validées"))
printHtmlPart(1)
expressionOut.print(g.message(code:"infoValides.text", default:"Infos validées"))
printHtmlPart(3)
expressionOut.print(g.message(code:"infoRejetes.text", default:"Infos rejetées"))
printHtmlPart(1)
expressionOut.print(g.message(code:"infoRejetes.text", default:"Infos rejetées"))
printHtmlPart(4)
invokeTag('message','g',20,['code':("list.infoAValider")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',24,['class':("alert-info")],2)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('filterButton','filterpane',28,['text':("Chercher")],-1)
printHtmlPart(9)
for( infoInstance in (infoInstanceList) ) {
printHtmlPart(10)
if(true && (infoInstance.contenu)) {
printHtmlPart(11)
expressionOut.print(infoInstance.id)
printHtmlPart(12)
expressionOut.print(infoInstance.titre)
printHtmlPart(13)
expressionOut.print(infoInstance.contenu.decodeHTML())
printHtmlPart(14)
invokeTag('fieldValue','g',39,['bean':(infoInstance),'field':("url")],-1)
printHtmlPart(15)
invokeTag('display','prettytime',41,['date':(infoInstance?.date)],-1)
printHtmlPart(16)
}
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('remoteLink','g',46,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("edit"),'update':("listInfoContent"),'method':("GET"),'id':(infoInstance.id),'params':("{update:'listInfoContent'}"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(7)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',52,['update':("listInfoContent"),'controller':("info"),'action':("list"),'total':(infoInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',54,['controller':("info"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(21)
invokeTag('filterPane','filterpane',59,['dialog':("true"),'controller':("info"),'domain':("simagri.Info")],-1)
printHtmlPart(22)
invokeTag('filterButton','filterpane',66,['text':("Chercher")],-1)
printHtmlPart(23)
invokeTag('message','g',72,['code':("list.infoValidees")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',76,['class':("alert-info")],2)
printHtmlPart(7)
}
printHtmlPart(24)
for( infoInstance in (infoValideInstanceList) ) {
printHtmlPart(10)
if(true && (infoInstance.contenu)) {
printHtmlPart(25)
createTagBody(3, {->
printHtmlPart(26)
expressionOut.print(infoInstance.titre)
})
invokeTag('remoteLink','g',89,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("show"),'update':("listInfoContent"),'method':("GET"),'id':(infoInstance.id),'params':("{update:'listInfoContent'}"),'class':("link")],3)
printHtmlPart(27)
expressionOut.print(infoInstance.contenu.decodeHTML())
printHtmlPart(14)
invokeTag('fieldValue','g',92,['bean':(infoInstance),'field':("url")],-1)
printHtmlPart(15)
invokeTag('display','prettytime',94,['date':(infoInstance?.date)],-1)
printHtmlPart(16)
}
else if(true && (infoInstance.url)) {
printHtmlPart(28)
createTagBody(3, {->
printHtmlPart(26)
expressionOut.print(infoInstance.titre)
})
invokeTag('remoteLink','g',102,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("show"),'update':("listInfoContent"),'method':("GET"),'id':(infoInstance.id),'params':("{update:'listInfoContent'}"),'class':("link")],3)
printHtmlPart(13)
if(true && (infoInstance?.url?.contains('youtube'))) {
printHtmlPart(26)
invokeTag('video','g',105,['videoKey':(infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""),'width':("100%"),'height':("350px")],-1)
printHtmlPart(29)
}
else if(true && (infoInstance.url)) {
printHtmlPart(30)
invokeTag('fieldValue','g',108,['bean':(infoInstance),'field':("url")],-1)
printHtmlPart(31)
}
printHtmlPart(32)
invokeTag('display','prettytime',111,['date':(infoInstance?.date)],-1)
printHtmlPart(33)
}
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('remoteLink','g',120,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("edit"),'update':("listInfoValideContent"),'method':("GET"),'id':(infoInstance.id),'params':("{update:'listInfoValideContent'}"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(7)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',125,['controller':("info"),'update':("listInfoValideContent"),'action':("listValidees"),'total':(infoValideInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',127,['controller':("info"),'action':("listValidees"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(34)
invokeTag('filterPane','filterpane',130,['controller':("info"),'dialog':("true"),'domain':("simagri.Info")],-1)
printHtmlPart(35)
invokeTag('render','g',133,['template':("/info/abandonner")],-1)
printHtmlPart(36)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457973685641L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
