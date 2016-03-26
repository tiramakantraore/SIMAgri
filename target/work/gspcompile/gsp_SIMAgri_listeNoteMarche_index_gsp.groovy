import simagri.NoteMarche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_listeNoteMarche_index_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/listeNoteMarche/_index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(g.message(code:"noteMarcheAValider.text", default:"Notes de marché à valider"))
printHtmlPart(1)
expressionOut.print(g.message(code:"noteMarcheAValider.text", default:"Notes de marché à valider"))
printHtmlPart(2)
expressionOut.print(g.message(code:"noteMarcheValides.text", default:"Notes de marché validées"))
printHtmlPart(3)
expressionOut.print(g.message(code:"noteMarcheValides.text", default:"Notes de marché validées"))
printHtmlPart(4)
expressionOut.print(g.message(code:"noteMarcheRejetes.text", default:"Notes de marché rejetées"))
printHtmlPart(3)
expressionOut.print(g.message(code:"noteMarcheRejetes.text", default:"Notes de marché rejetées"))
printHtmlPart(5)
invokeTag('message','g',21,['code':("list.notesAValider")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',25,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('filterButton','filterpane',29,['text':("Chercher")],-1)
printHtmlPart(10)
for( noteMarcheInstance in (noteMarcheInstanceList) ) {
printHtmlPart(11)
if(true && (noteMarcheInstance.contenu)) {
printHtmlPart(12)
createTagBody(3, {->
printHtmlPart(13)
expressionOut.print(noteMarcheInstance.titre)
})
invokeTag('remoteLink','g',37,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("noteMarche"),'action':("show"),'update':("listInfoContent"),'method':("GET"),'id':(noteMarcheInstance.id),'params':("{update:'listInfoContent'}"),'class':("link")],3)
printHtmlPart(14)
expressionOut.print(noteMarcheInstance.contenu.decodeHTML())
printHtmlPart(15)
invokeTag('display','prettytime',39,['date':(noteMarcheInstance?.date)],-1)
printHtmlPart(16)
}
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('remoteLink','g',44,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("noteMarche"),'action':("edit"),'update':("listInfoContent"),'method':("GET"),'id':(noteMarcheInstance.id),'params':("{update:'listInfoContent'}"),'class':("btn-flat  btn-primary")],2)
printHtmlPart(8)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',47,['update':("listInfoContent"),'controller':("noteMarche"),'action':("list"),'total':(noteMarcheInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',50,['controller':("noteMarche"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(21)
invokeTag('filterPane','filterpane',56,['dialog':("true"),'controller':("noteMarche"),'domain':("simagri.NoteMarche")],-1)
printHtmlPart(22)
invokeTag('filterButton','filterpane',64,['text':("Chercher")],-1)
printHtmlPart(23)
invokeTag('message','g',68,['code':("list.notesValidees")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',72,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(24)
for( noteMarcheInstance in (noteMarcheValideInstanceList) ) {
printHtmlPart(25)
if(true && (noteMarcheInstance.contenu)) {
printHtmlPart(26)
createTagBody(3, {->
printHtmlPart(13)
expressionOut.print(noteMarcheInstance.titre)
})
invokeTag('remoteLink','g',82,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("noteMarche"),'action':("show"),'update':("listInfoContent"),'method':("GET"),'id':(noteMarcheInstance.id),'params':("{update:'listInfoContent'}"),'class':("link")],3)
printHtmlPart(27)
expressionOut.print(noteMarcheInstance.contenu.decodeHTML())
printHtmlPart(28)
invokeTag('display','prettytime',86,['date':(noteMarcheInstance?.date)],-1)
printHtmlPart(29)
}
printHtmlPart(30)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',92,['controller':("noteMarche"),'update':("listInfoValideContent"),'action':("listValidees"),'total':(noteMarcheValideInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',95,['controller':("noteMarche"),'action':("listValidees"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(31)
invokeTag('filterPane','filterpane',99,['controller':("noteMarche"),'dialog':("true"),'domain':("simagri.NoteMarche")],-1)
printHtmlPart(32)
invokeTag('render','g',104,['template':("/noteMarche/abandonner")],-1)
printHtmlPart(33)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457804724318L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
