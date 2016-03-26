import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_saisiePrixlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/saisiePrix/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'priceHolder.label', default: 'PriceHolder'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.priceHolder")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',20,['code':("list.priceHolder")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',22,['class':("alert-info")],3)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('filterButton','filterpane',23,['text':("Rechercher")],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',36,['update':("listContent"),'action':("list"),'property':("marche"),'title':(message(code: 'priceHolder.auteur.label', default: 'Auteur'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',40,['update':("listContent"),'action':("list"),'property':("marche"),'title':(message(code: 'priceHolder.marche.label', default: 'Marche'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',45,['update':("listContent"),'action':("list"),'property':("produit"),'title':(message(code: 'priceHolder.produit.label', default: 'Produit'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',47,['update':("listContent"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'priceHolder.sourcePrix.label', default: 'Source '))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',49,['update':("listContent"),'action':("list"),'property':("active"),'title':(message(code: 'priceHolder.active.label', default: 'Actif'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',52,['update':("listContent"),'action':("list"),'property':("isRejected"),'title':(message(code: 'priceHolder.isRejected.label', default: 'Est rejeté'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',54,['update':("listContent"),'action':("list"),'property':("date"),'title':(message(code: 'priceHolder.date.label', default: 'Date'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',56,['update':("listContent"),'action':("list"),'property':("prixGros"),'title':(message(code: 'priceHolder.prixGros.label', default: 'Prix Gros'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',59,['update':("listContent"),'action':("list"),'property':("prixDetail"),'title':(message(code: 'priceHolder.prixDetail.label', default: 'Prix Détail'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',62,['update':("listContent"),'action':("list"),'property':("commentaire"),'title':(message(code: 'priceHolder.commentaire.label', default: 'Commentaires'))],-1)
printHtmlPart(12)
for( priceHolderInstance in (priceHolderInstanceList) ) {
printHtmlPart(13)
createClosureForHtmlPart(14, 3)
invokeTag('link','g',66,['action':("show"),'id':(priceHolderInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(15)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "auteur"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "marche"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "produit"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "sourcePrix"))
printHtmlPart(18)
if(true && (priceHolderInstance?.active)) {
printHtmlPart(19)
}
else if(true && (!priceHolderInstance?.active)) {
printHtmlPart(20)
}
printHtmlPart(21)
if(true && (priceHolderInstance?.isRejected)) {
printHtmlPart(19)
}
else if(true && (!priceHolderInstance?.isRejected)) {
printHtmlPart(20)
}
printHtmlPart(22)
invokeTag('formatDate','g',89,['date':(priceHolderInstance.date),'type':("datetime"),'style':("MEDIUM"),'timeStyle':("MEDIUM")],-1)
printHtmlPart(23)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "prixGros"))
printHtmlPart(24)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "prixDetail"))
printHtmlPart(24)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "commentaire"))
printHtmlPart(25)
}
printHtmlPart(26)
invokeTag('remotePaginate','util',101,['update':("listContent"),'action':("list"),'total':(priceHolderInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(27)
invokeTag('formats','export',103,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(28)
invokeTag('filterPane','filterpane',112,['associatedProperties':("marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"),'excludeProperties':("periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active"),'dialog':("true"),'domain':("simagri.PriceHolder")],-1)
printHtmlPart(29)
})
invokeTag('captureBody','sitemesh',114,[:],1)
printHtmlPart(30)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993358L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
