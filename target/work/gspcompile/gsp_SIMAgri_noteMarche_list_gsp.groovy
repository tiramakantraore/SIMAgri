import simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('message','g',8,['code':("list.infoAValider")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',12,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
invokeTag('filterButton','filterpane',16,['text':("Chercher")],-1)
printHtmlPart(5)
for( noteMarcheInstance in (noteMarcheInstanceList) ) {
printHtmlPart(6)
if(true && (noteMarcheInstance.contenu)) {
printHtmlPart(7)
expressionOut.print(noteMarcheInstance.id)
printHtmlPart(8)
expressionOut.print(noteMarcheInstance.titre)
printHtmlPart(9)
expressionOut.print(noteMarcheInstance.contenu.decodeHTML())
printHtmlPart(10)
invokeTag('display','prettytime',27,['date':(noteMarcheInstance?.date)],-1)
printHtmlPart(11)
}
printHtmlPart(12)
createClosureForHtmlPart(13, 2)
invokeTag('remoteLink','g',31,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("edit"),'update':("listInfoContent"),'method':("GET"),'id':(noteMarcheInstance.id),'params':("{update:'listInfoContent'}"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('remotePaginate','util',37,['update':("listInfoContent"),'controller':("info"),'action':("list"),'total':(noteMarcheInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(16)
invokeTag('formats','export',40,['controller':("info"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(17)
invokeTag('filterPane','filterpane',44,['dialog':("true"),'controller':("info"),'domain':("simagri.Info")],-1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455374984833L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
