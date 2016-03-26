import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_alerteReseau_listRejets_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/alerteReseau/_listRejets.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('message','g',11,['code':("abondonnerRejets.alerte")],-1)
printHtmlPart(2)
})
invokeTag('remoteLink','g',12,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("alerteReseau"),'action':("abandonner"),'onclick':("confirm('Confirmez-vous la suppression des alertes rejetés ? ')"),'update':("listContent"),'method':("GET"),'class':("create")],1)
printHtmlPart(3)
invokeTag('message','g',21,['code':("list.alerteRejetes")],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',29,['class':("alert-info")],2)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('filterButton','filterpane',31,['text':("Rechercher")],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',36,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'alerteReseau.nom.label', default: 'Nom alerte'))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',38,['update':("listContent"),'action':("list"),'property':("valide"),'title':(message(code: 'alerte.suspendue.label', default: 'Validé ?'))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',40,['update':("listContent"),'action':("list"),'property':("reseaux"),'title':(message(code: 'alerte.reseaux.label', default: 'Réseaux'))],-1)
printHtmlPart(9)
for( alerteInstance in (alerteInstanceList) ) {
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('remoteLink','g',50,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("applications"),'action':("showAlertesEdit"),'update':("listContent"),'method':("GET"),'id':(alerteInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(12)
expressionOut.print(fieldValue(bean: alerteInstance, field: "nom"))
printHtmlPart(13)
if(true && (alerteInstance?.valide)) {
printHtmlPart(14)
}
else if(true && (!alerteInstance?.valide)) {
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(fieldValue(bean: alerteInstance, field: "reseaux"))
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('remotePaginate','util',72,['controller':("alerteReseau"),'update':("listContent"),'action':("list"),'total':(alerteInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(19)
invokeTag('formats','export',75,['controller':("alerteReseau"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(20)
invokeTag('filterPane','filterpane',79,['dialog':("true"),'controller':("alerteReseau"),'associatedProperties':("operateur.login"),'excludeProperties':("recevoirOffres,dateCreation,crontabExpression,dateDernierEnvoi,recevoirPrix,recevoirParEmail,recevoirParSMS,validationDate,dateDemarrage,rejectedDate"),'domain':("simagri.AlerteReseau")],-1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418932155400L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
