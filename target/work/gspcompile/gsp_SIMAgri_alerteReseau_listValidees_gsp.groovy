import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_alerteReseau_listValidees_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/alerteReseau/_listValidees.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',5,['code':("list.alerteReseauValidee")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',11,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',17,['update':("listAlerteValideContent"),'action':("list"),'property':("nom"),'title':(message(code: 'alerteReseau.nom.label', default: 'Nom alerte'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',19,['update':("listAlerteValideContent"),'action':("list"),'property':("valide"),'title':(message(code: 'alerte.suspendue.label', default: 'Validé ?'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',21,['update':("listAlerteValideContent"),'action':("list"),'property':("reseaux"),'title':(message(code: 'alerte.reseaux.label', default: 'Réseaux'))],-1)
printHtmlPart(6)
for( alerteInstance in (alerteInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',34,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("applications"),'action':("showAlertesEdit"),'update':("listAlerteValideContent"),'params':("{update:'listAlerteValideContent'}"),'method':("GET"),'id':(alerteInstance?.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: alerteInstance, field: "nom"))
printHtmlPart(10)
if(true && (alerteInstance?.valide)) {
printHtmlPart(11)
}
else if(true && (!alerteInstance?.valide)) {
printHtmlPart(12)
}
printHtmlPart(9)
expressionOut.print(fieldValue(bean: alerteInstance, field: "reseaux"))
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('remotePaginate','util',55,['update':("listAlerteValideContent"),'controller':("alerteReseau"),'action':("listValidees"),'total':(alerteInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(15)
invokeTag('formats','export',58,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(16)
invokeTag('filterPane','filterpane',62,['dialog':("true"),'controller':("alerteReseau"),'associatedProperties':("operateur.login"),'excludeProperties':("recevoirOffres,dateCreation,crontabExpression,dateDernierEnvoi,recevoirPrix,recevoirParEmail,recevoirParSMS,validationDate,dateDemarrage,rejectedDate"),'domain':("simagri.AlerteReseau")],-1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1424978155803L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
