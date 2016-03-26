import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_listeDesOffres_index_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/listeDesOffres/_index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(g.message(code:"offreAValider.text", default:"Offres à valider"))
printHtmlPart(1)
expressionOut.print(g.message(code:"offreAValider.text", default:"Offres à valider"))
printHtmlPart(2)
expressionOut.print(g.message(code:"offreValides.text", default:"Offres validées "))
printHtmlPart(3)
expressionOut.print(g.message(code:"offreValides.text", default:"Offres validées "))
printHtmlPart(4)
expressionOut.print(g.message(code:"offreRejetes.text", default:"Offres rejétées "))
printHtmlPart(3)
expressionOut.print(g.message(code:"offreRejetes.text", default:"Offres rejétées "))
printHtmlPart(5)
invokeTag('message','g',19,['code':("list.offreAValider")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',23,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',30,['update':("listOffresAValiderContent"),'action':("list"),'property':("date"),'title':(message(code: 'offre.date.label', default: 'Date de mise en ligne'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',32,['update':("listOffresAValiderContent"),'action':("list"),'property':("typeOffre"),'title':(message(code: 'offre.typeOffre.label', default: 'Type Offre'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',35,['update':("listOffresAValiderContent"),'action':("list"),'property':("auteur"),'title':(message(code: 'offre.auteur.label', default: 'Statut'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',37,['update':("listOffresAValiderContent"),'action':("list"),'property':("produit"),'title':(message(code: 'offre.produit.label', default: 'Produit'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',39,['update':("listOffresAValiderContent"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'offre.sourceOffre.label', default: 'Source '))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',40,['update':("listOffresAValiderContent"),'action':("list"),'property':("quantite"),'title':(message(code: 'offre.quantite.label', default: 'Source '))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',41,['update':("listOffresAValiderContent"),'action':("list"),'property':("mesure"),'title':(message(code: 'offre.mesure.label', default: 'Source '))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',42,['update':("listOffresAValiderContent"),'action':("list"),'property':("prixUnitaire"),'title':(message(code: 'offre.prixUnitaire.label', default: 'Source '))],-1)
printHtmlPart(13)
invokeTag('message','g',44,['code':("offre.montantGlobalGrosDetail.label"),'default':("Montant total")],-1)
printHtmlPart(14)
for( offreInstance in (offreInstanceList) ) {
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('remoteLink','g',53,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("offre"),'action':("edit"),'update':("listOffresAValiderContent"),'method':("GET"),'id':(offreInstance.id),'params':([update:'listOffresAValiderContent']),'class':("btn-flat  btn-small")],2)
printHtmlPart(17)
expressionOut.print(fieldValue(bean: offreInstance, field: "date"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: offreInstance, field: "typeOffre"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: offreInstance, field: "auteur"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: offreInstance, field: "produit"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: offreInstance, field: "sourceOffre"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: offreInstance, field: "quantite"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: offreInstance, field: "mesure"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: offreInstance, field: "prixUnitaire"))
printHtmlPart(21)
expressionOut.print(fieldValue(bean: offreInstance, field: "montantGlobalGrosDetail"))
printHtmlPart(22)
}
printHtmlPart(23)
invokeTag('remotePaginate','util',77,['update':("listOffresAValiderContent"),'controller':("offre"),'action':("list"),'total':(offreInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(24)
invokeTag('formats','export',81,['controller':("offre"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(25)
invokeTag('message','g',90,['code':("list.offreValidees")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',91,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(26)
invokeTag('remoteSortableColumn','util',100,['update':("listOffresValidesContent"),'action':("list"),'property':("date"),'title':(message(code: 'offre.date.label', default: 'Date de mise en ligne'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',106,['update':("listOffresValidesContent"),'action':("list"),'property':("typeOffre"),'title':(message(code: 'offre.typeOffre.label', default: 'Type Offre'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',110,['update':("listOffresValidesContent"),'action':("list"),'property':("auteur"),'title':(message(code: 'offre.auteur.label', default: 'Statut'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',114,['update':("listOffresValidesContent"),'action':("list"),'property':("produit"),'title':(message(code: 'offre.produit.label', default: 'Produit'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',115,['update':("listOffresValidesContent"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'offre.sourceOffre.label', default: 'Source '))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',117,['update':("listOffresAValiderContent"),'action':("list"),'property':("quantite"),'title':(message(code: 'offre.quantite.label', default: 'Source '))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',120,['update':("listOffresAValiderContent"),'action':("list"),'property':("mesure"),'title':(message(code: 'offre.mesure.label', default: 'Source '))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',122,['update':("listOffresAValiderContent"),'action':("list"),'property':("prixUnitaire"),'title':(message(code: 'offre.prixUnitaire.label', default: 'Source '))],-1)
printHtmlPart(13)
invokeTag('message','g',124,['code':("offre.montantGlobalGrosDetail.label"),'default':("Montant total")],-1)
printHtmlPart(14)
for( offreInstance in (offreValideInstanceList) ) {
printHtmlPart(15)
createClosureForHtmlPart(16, 2)
invokeTag('remoteLink','g',130,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("offre"),'action':("edit"),'update':("listOffresValidesContent"),'method':("GET"),'id':(offreInstance.id),'params':([update:'listOffresValidesContent']),'class':("btn-flat  btn-small")],2)
printHtmlPart(17)
expressionOut.print(fieldValue(bean: offreInstance, field: "date"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: offreInstance, field: "typeOffre"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: offreInstance, field: "auteur"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: offreInstance, field: "produit"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: offreInstance, field: "sourceOffre"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: offreInstance, field: "quantite"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: offreInstance, field: "mesure"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: offreInstance, field: "prixUnitaire"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: offreInstance, field: "montantGlobalGrosDetail"))
printHtmlPart(22)
}
printHtmlPart(23)
invokeTag('remotePaginate','util',151,['controller':("offre"),'update':("listOffresValidesContent"),'action':("listValidees"),'total':(offreValideInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(27)
invokeTag('formats','export',152,['controller':("offreValidee"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(28)
invokeTag('render','g',158,['template':("/offre/abandonner")],-1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428775145627L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
