import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_priceHolder_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/priceHolder/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',6,['code':("list.priceHolder")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',12,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',18,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("marche"),'title':(message(code: 'priceHolder.auteur.label', default: 'Auteur'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',20,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("marche"),'title':(message(code: 'priceHolder.marche.label', default: 'Marche'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',22,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("produit"),'title':(message(code: 'priceHolder.produit.label', default: 'Produit'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',25,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'priceHolder.sourcePrix.label', default: 'Source '))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',27,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("date"),'title':(message(code: 'priceHolder.date.label', default: 'Date'))],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',30,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("prixGros"),'title':(message(code: 'priceHolder.prixGros.label', default: 'Prix Gros'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',32,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("prixDetail"),'title':(message(code: 'priceHolder.prixDetail.label', default: 'Prix Détail'))],-1)
printHtmlPart(8)
for( priceHolderInstance in (priceHolderInstanceList) ) {
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('remoteLink','g',44,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("priceHolder"),'action':("edit"),'update':("listPrixAValiderContent"),'method':("GET"),'id':(priceHolderInstance.id),'params':("{update:'listPrixAValiderContent'}"),'class':("btn-flat  btn-small")],2)
printHtmlPart(11)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "auteur"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "marche"))
printHtmlPart(13)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "produit"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "sourcePrix"))
printHtmlPart(14)
invokeTag('formatDate','g',53,['date':(priceHolderInstance.date),'type':("datetime"),'style':("MEDIUM"),'timeStyle':("MEDIUM")],-1)
printHtmlPart(15)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "prixGros"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "prixDetail"))
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('remotePaginate','util',66,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'total':(priceHolderInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(19)
invokeTag('formats','export',69,['controller':("priceHolder"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(20)
invokeTag('filterPane','filterpane',74,['controller':("priceHolder"),'associatedProperties':("marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"),'excludeProperties':("periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active"),'dialog':("true"),'update':("listPrixAValiderContent"),'domain':("simagri.PriceHolder")],-1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1424995336457L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
