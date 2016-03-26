import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_alerteReseau_listesuspendues_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/alerteReseau/_listesuspendues.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('message','g',12,['code':("desactiver.alerteReseau")],-1)
printHtmlPart(2)
})
invokeTag('link','g',13,['controller':("alerteReseau"),'action':("desactiver"),'onclick':("confirm('Confirmez-vous la suspension de toutes les alertes ? ')")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',18,['code':("activer.alerteReseau")],-1)
printHtmlPart(2)
})
invokeTag('link','g',19,['controller':("alerteReseau"),'action':("activer"),'onclick':("confirm('Confirmez-vous la réactivation de toutes les alertes suspendues ? ')")],1)
printHtmlPart(5)
invokeTag('message','g',27,['code':("list.alerteReseauDesactive")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',35,['class':("alert-info")],2)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('filterButton','filterpane',37,['text':("Rechercher")],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',43,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'alerteReseau.nom.label', default: 'Nom alerte'))],-1)
printHtmlPart(2)
invokeTag('remoteSortableColumn','util',44,['update':("listContent"),'action':("list"),'property':("valide"),'title':(message(code: 'alerte.suspendue.label', default: 'Validé ?'))],-1)
printHtmlPart(2)
invokeTag('remoteSortableColumn','util',45,['update':("listContent"),'action':("list"),'property':("reseaux"),'title':(message(code: 'alerte.reseaux.label', default: 'Réseaux'))],-1)
printHtmlPart(10)
for( alerteInstance in (alerteInstanceList) ) {
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('remoteLink','g',58,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("applications"),'action':("showAlertesEdit"),'update':("listContent"),'method':("GET"),'id':(alerteInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: alerteInstance, field: "nom"))
printHtmlPart(14)
if(true && (alerteInstance?.valide)) {
printHtmlPart(15)
}
else if(true && (!alerteInstance?.valide)) {
printHtmlPart(16)
}
printHtmlPart(17)
expressionOut.print(fieldValue(bean: alerteInstance, field: "reseaux"))
printHtmlPart(18)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',80,['controller':("alerteReseau"),'update':("listContent"),'action':("list"),'total':(alerteInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',83,['controller':("alerteReseau"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(21)
invokeTag('filterPane','filterpane',87,['dialog':("true"),'controller':("alerteReseau"),'associatedProperties':("operateur.login"),'excludeProperties':("recevoirOffres,dateCreation,crontabExpression,dateDernierEnvoi,recevoirPrix,recevoirParEmail,recevoirParSMS,validationDate,dateDemarrage,rejectedDate"),'domain':("simagri.AlerteReseau")],-1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418932155345L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
