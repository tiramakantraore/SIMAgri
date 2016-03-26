import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',7,['code':("list.offreAValider")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',11,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',13,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',18,['update':("listOffresAValiderContent"),'action':("list"),'property':("date"),'title':(message(code: 'offre.date.label', default: 'Date de mise en ligne'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',20,['update':("listOffresAValiderContent"),'action':("list"),'property':("typeOffre"),'title':(message(code: 'offre.typeOffre.label', default: 'Type Offre'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',23,['update':("listOffresAValiderContent"),'action':("list"),'property':("auteur"),'title':(message(code: 'offre.auteur.label', default: 'Statut'))],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',25,['update':("listOffresAValiderContent"),'action':("list"),'property':("produit"),'title':(message(code: 'offre.produit.label', default: 'Produit'))],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',27,['update':("listOffresAValiderContent"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'offre.sourceOffre.label', default: 'Source '))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',28,['update':("listOffresAValiderContent"),'action':("list"),'property':("quantite"),'title':(message(code: 'offre.quantite.label', default: 'Source '))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',29,['update':("listOffresAValiderContent"),'action':("list"),'property':("mesure"),'title':(message(code: 'offre.mesure.label', default: 'Source '))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',30,['update':("listOffresAValiderContent"),'action':("list"),'property':("prixUnitaire"),'title':(message(code: 'offre.prixUnitaire.label', default: 'Source '))],-1)
printHtmlPart(9)
invokeTag('message','g',32,['code':("offre.montantGlobalGrosDetail.label"),'default':("Montant total")],-1)
printHtmlPart(10)
for( offreInstance in (offreInstanceList) ) {
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('remoteLink','g',41,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("offre"),'action':("edit"),'update':("listOffresAValiderContent"),'method':("GET"),'id':(offreInstance.id),'params':([update:'listOffresAValiderContent']),'class':("btn-flat  btn-small")],2)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: offreInstance, field: "date"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: offreInstance, field: "typeOffre"))
printHtmlPart(15)
expressionOut.print(fieldValue(bean: offreInstance, field: "auteur"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: offreInstance, field: "produit"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: offreInstance, field: "sourceOffre"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: offreInstance, field: "quantite"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: offreInstance, field: "mesure"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: offreInstance, field: "prixUnitaire"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: offreInstance, field: "montantGlobalGrosDetail"))
printHtmlPart(20)
}
printHtmlPart(21)
invokeTag('remotePaginate','util',62,['update':("listOffresAValiderContent"),'controller':("offre"),'action':("list"),'total':(offreInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(22)
invokeTag('formats','export',65,['controller':("offre"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(23)
invokeTag('filterPane','filterpane',78,['controller':("offre"),'associatedProperties':("produit.nom, lieuStockage.nom,mesure.nom,qualite.nom,origine.nom,lieuLivraison.nom,auteur.login"),'excludeProperties':("email,delaiEnJours,quantite,dateValidation, dateExpiration,delaiLivraison,isValidated,isRejected,coutTransport,commentaire,conditions,prixUnitaireGros,prixTotalGros,contact,IsForSell"),'dialog':("true"),'domain':("simagri.Offre")],-1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419569532860L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
