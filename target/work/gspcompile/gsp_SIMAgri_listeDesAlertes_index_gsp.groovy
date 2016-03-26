import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_listeDesAlertes_index_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/listeDesAlertes/_index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',17,['code':("list.alerteAValider")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',20,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',28,['update':("listAlerteContent"),'action':("list"),'property':("nom"),'title':(message(code: 'alerteReseau.nom.label', default: 'Nom alerte'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',30,['update':("listAlerteContent"),'action':("list"),'property':("valide"),'title':(message(code: 'alerte.suspendue.label', default: 'Validé ?'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',32,['update':("listAlerteContent"),'action':("list"),'property':("reseaux"),'title':(message(code: 'alerte.reseaux.label', default: 'Réseaux'))],-1)
printHtmlPart(6)
for( alerteInstance in (alerteInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',45,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("applications"),'action':("showAlertesEdit"),'update':("listAlerteContent"),'params':("{update:'listAlerteContent'}"),'method':("GET"),'id':(alerteInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: alerteInstance, field: "nom"))
printHtmlPart(10)
if(true && (alerteInstance?.valide)) {
printHtmlPart(11)
}
else if(true && (!alerteInstance?.valide)) {
printHtmlPart(12)
}
printHtmlPart(13)
expressionOut.print(fieldValue(bean: alerteInstance, field: "reseaux"))
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('remotePaginate','util',66,['controller':("alerteReseau"),'update':("listAlerteContent"),'action':("list"),'total':(alerteInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(16)
invokeTag('formats','export',69,['controller':("alerteReseau"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(17)
invokeTag('message','g',77,['code':("list.alerteReseauValidee")],-1)
printHtmlPart(18)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',81,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',89,['update':("listAlerteValideContent"),'action':("list"),'property':("nom"),'title':(message(code: 'alerteReseau.nom.label', default: 'Nom alerte'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',93,['update':("listAlerteValideContent"),'action':("list"),'property':("valide"),'title':(message(code: 'alerte.suspendue.label', default: 'Validé ?'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',95,['update':("listAlerteValideContent"),'action':("list"),'property':("reseaux"),'title':(message(code: 'alerte.reseaux.label', default: 'Réseaux'))],-1)
printHtmlPart(19)
for( alerteInstance in (alerteValideInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(20, 2)
invokeTag('remoteLink','g',108,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("applications"),'action':("showAlertesEdit"),'update':("listAlerteValideContent"),'params':("{update:'listAlerteValideContent'}"),'method':("GET"),'id':(alerteInstance?.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: alerteInstance, field: "nom"))
printHtmlPart(10)
if(true && (alerteInstance?.valide)) {
printHtmlPart(11)
}
else if(true && (!alerteInstance?.valide)) {
printHtmlPart(12)
}
printHtmlPart(13)
expressionOut.print(fieldValue(bean: alerteInstance, field: "reseaux"))
printHtmlPart(21)
}
printHtmlPart(15)
invokeTag('remotePaginate','util',126,['update':("listAlerteValideContent"),'controller':("alerteReseau"),'action':("listValidees"),'total':(alerteValideInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(16)
invokeTag('formats','export',131,['controller':("alerteReseau"),'action':("listValidees"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(22)
invokeTag('render','g',133,['template':("/alerteReseau/abandonner")],-1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1426285836058L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
