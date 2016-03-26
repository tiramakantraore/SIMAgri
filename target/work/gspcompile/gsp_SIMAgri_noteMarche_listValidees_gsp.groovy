import simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_listValidees_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_listValidees.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('filterButton','filterpane',2,['text':("Chercher")],-1)
printHtmlPart(1)
invokeTag('message','g',6,['code':("list.noteMarcheValidees")],-1)
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
for( noteMarcheInstance in (noteMarcheValideInstanceList) ) {
printHtmlPart(6)
if(true && (noteMarcheInstance.contenu)) {
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
expressionOut.print(noteMarcheInstance.titre)
})
invokeTag('remoteLink','g',24,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("show"),'update':("listNoteMarcheValideContent"),'method':("GET"),'id':(noteMarcheInstance.id),'params':("{update:'listInfoValideContent'}"),'class':("btn-flat  btn-primary")],3)
printHtmlPart(9)
expressionOut.print(noteMarcheInstance.contenu.decodeHTML())
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('display','prettytime',33,['date':(noteMarcheInstance?.date)],-1)
printHtmlPart(12)
createClosureForHtmlPart(13, 2)
invokeTag('remoteLink','g',36,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("info"),'action':("edit"),'update':("listInfoValideContent"),'method':("GET"),'id':(noteMarcheInstance.id),'params':("{update:'listInfoValideContent'}"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(4)
}
printHtmlPart(14)
invokeTag('remotePaginate','util',42,['controller':("noteMarche"),'update':("listInfoValideContent"),'action':("listValidees"),'total':(noteMarcheValideInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(15)
invokeTag('formats','export',45,['controller':("info"),'action':("listValidees"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(16)
invokeTag('filterPane','filterpane',47,['controller':("info"),'dialog':("true"),'domain':("simagri.NoteMarche")],-1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455376337967L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
