import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_prix_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/prix/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',5,['code':("list.prix")],-1)
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
invokeTag('filterButton','filterpane',12,['text':("Chercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',20,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("categorieTarifaire"),'title':(message(code: 'prix.categorieTarifaire.label', default: 'Type prix'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',22,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("auteur"),'title':(message(code: 'prix.auteur.label', default: 'Auteur'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',24,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("produit"),'title':(message(code: 'prix.produit.label', default: 'Produit'))],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',25,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("mesure"),'title':(message(code: 'prix.mesure.label', default: 'Mesure'))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',26,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("marche"),'title':(message(code: 'prix.marche.label', default: 'Marché'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',28,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("date"),'title':(message(code: 'prix.date.label', default: 'Date'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',30,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'priceHolder.sourcePrix.label', default: 'Source '))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',32,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("montant"),'title':(message(code: 'prix.montant.label', default: 'Montant'))],-1)
printHtmlPart(10)
for( prixInstance in (prixInstanceList) ) {
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('remoteLink','g',42,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("prix"),'action':("edit"),'update':("listPrixValidesContent"),'method':("GET"),'id':(prixInstance.id),'params':("{update:'listPrixValidesContent'}"),'class':("btn-flat  btn-small")],2)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: prixInstance, field: "categorieTarifaire"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: prixInstance, field: "auteur"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: prixInstance, field: "produit"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: prixInstance, field: "mesure"))
printHtmlPart(15)
expressionOut.print(fieldValue(bean: prixInstance, field: "marche"))
printHtmlPart(15)
invokeTag('formatDate','g',55,['date':(prixInstance.date),'type':("datetime"),'style':("MEDIUM"),'timeStyle':("MEDIUM")],-1)
printHtmlPart(16)
expressionOut.print(fieldValue(bean: prixInstance, field: "sourcePrix"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: prixInstance, field: "montant"))
printHtmlPart(18)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',69,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'total':(prixInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',72,['controller':("prix"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(21)
invokeTag('filterPane','filterpane',75,['controller':("prix"),'associatedProperties':("marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"),'excludeProperties':("periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active"),'dialog':("true"),'update':("listPrixValidesContent"),'domain':("simagri.PriceHolder")],-1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1424995336416L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
