import simagri.Sondage
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sondage_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sondage/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'sondage.label', default: 'Sondage'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("list.sondage")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',18,['code':("title.sondage")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',22,['code':("create.sondage")],-1)
printHtmlPart(7)
})
invokeTag('remoteLink','g',23,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'class':("create")],2)
printHtmlPart(8)
invokeTag('message','g',32,['code':("list.sondage")],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',36,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(11)
invokeTag('filterButton','filterpane',38,['text':("Rechercher")],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',44,['update':("listContent"),'action':("list"),'property':("titre"),'title':(message(code: 'sondage.titre.label', default: 'Titre'))],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',47,['update':("listContent"),'action':("list"),'property':("description"),'title':(message(code: 'sondage.description.label', default: 'Description'))],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',50,['update':("listContent"),'action':("list"),'property':("dateDebut"),'title':(message(code: 'sondage.dateDebut.label', default: 'Date Debut'))],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',52,['update':("listContent"),'action':("list"),'property':("duree"),'title':(message(code: 'sondage.duree.label', default: 'Duree'))],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',55,['update':("listContent"),'action':("list"),'property':("dateFin"),'title':(message(code: 'sondage.dateFin.label', default: 'Date Fin'))],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',57,['update':("listContent"),'action':("list"),'property':("actif"),'title':(message(code: 'sondage.actif.label', default: 'Actif'))],-1)
printHtmlPart(14)
for( sondageInstance in (sondageInstanceList) ) {
printHtmlPart(15)
createClosureForHtmlPart(16, 3)
invokeTag('remoteLink','g',67,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(sondageInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(17)
expressionOut.print(fieldValue(bean: sondageInstance, field: "titre"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: sondageInstance, field: "description"))
printHtmlPart(18)
invokeTag('formatDate','g',74,['date':(sondageInstance.dateDebut)],-1)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: sondageInstance, field: "duree"))
printHtmlPart(18)
invokeTag('formatDate','g',78,['date':(sondageInstance.dateFin)],-1)
printHtmlPart(18)
invokeTag('formatBoolean','g',80,['boolean':(sondageInstance.actif)],-1)
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('remotePaginate','util',88,['update':("listContent"),'action':("list"),'total':(sondageInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(21)
invokeTag('formats','export',91,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(22)
invokeTag('filterPane','filterpane',95,['dialog':("true"),'domain':("simagri.Sondage")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',96,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993490L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
