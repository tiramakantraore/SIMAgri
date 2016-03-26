import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_listeDesPrix_index_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/listeDesPrix/_index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(g.message(code:"prixAValider.text", default:"Prix à valider"))
printHtmlPart(1)
expressionOut.print(g.message(code:"prixAValider.text", default:"Prix à valider"))
printHtmlPart(2)
expressionOut.print(g.message(code:"prixAValider.text", default:"Prix validés"))
printHtmlPart(3)
expressionOut.print(g.message(code:"prixAValider.text", default:"Prix validés"))
printHtmlPart(4)
expressionOut.print(g.message(code:"prixRejetes.text", default:"Prix rejétés"))
printHtmlPart(3)
expressionOut.print(g.message(code:"prixRejetes.text", default:"Prix rejétés"))
printHtmlPart(5)
invokeTag('message','g',18,['code':("list.priceHolder")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',22,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',30,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("marche"),'title':(message(code: 'priceHolder.auteur.label', default: 'Auteur'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',32,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("marche"),'title':(message(code: 'priceHolder.marche.label', default: 'Marche'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',34,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("produit"),'title':(message(code: 'priceHolder.produit.label', default: 'Produit'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',37,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'priceHolder.sourcePrix.label', default: 'Source '))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',39,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("date"),'title':(message(code: 'priceHolder.date.label', default: 'Date'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',42,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("prixGros"),'title':(message(code: 'priceHolder.prixGros.label', default: 'Prix Gros'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',44,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'property':("prixDetail"),'title':(message(code: 'priceHolder.prixDetail.label', default: 'Prix Détail'))],-1)
printHtmlPart(12)
for( priceHolderInstance in (priceHolderInstanceList) ) {
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('remoteLink','g',56,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("priceHolder"),'action':("edit"),'update':("listPrixAValiderContent"),'method':("GET"),'id':(priceHolderInstance.id),'params':("{update:'listPrixAValiderContent'}"),'class':("btn-flat  btn-small")],2)
printHtmlPart(15)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "auteur"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "marche"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "produit"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "sourcePrix"))
printHtmlPart(17)
invokeTag('formatDate','g',65,['date':(priceHolderInstance.date),'type':("datetime"),'style':("MEDIUM"),'timeStyle':("MEDIUM")],-1)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "prixGros"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: priceHolderInstance, field: "prixDetail"))
printHtmlPart(20)
}
printHtmlPart(21)
invokeTag('remotePaginate','util',78,['update':("listPrixAValiderContent"),'controller':("priceHolder"),'action':("list"),'total':(priceHolderInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(22)
invokeTag('formats','export',81,['controller':("priceHolder"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(23)
invokeTag('message','g',86,['code':("list.prix")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',92,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(24)
invokeTag('remoteSortableColumn','util',102,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("categorieTarifaire"),'title':(message(code: 'prix.categorieTarifaire.label', default: 'Type prix'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',109,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("auteur"),'title':(message(code: 'prix.auteur.label', default: 'Auteur'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',113,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("produit"),'title':(message(code: 'prix.produit.label', default: 'Produit'))],-1)
printHtmlPart(25)
invokeTag('remoteSortableColumn','util',115,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("mesure"),'title':(message(code: 'prix.mesure.label', default: 'Mesure'))],-1)
printHtmlPart(25)
invokeTag('remoteSortableColumn','util',117,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("marche"),'title':(message(code: 'prix.marche.label', default: 'Marché'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',118,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("date"),'title':(message(code: 'prix.date.label', default: 'Date'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',119,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("isFromSMS"),'title':(message(code: 'priceHolder.sourcePrix.label', default: 'Source '))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',121,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'property':("montant"),'title':(message(code: 'prix.montant.label', default: 'Montant'))],-1)
printHtmlPart(26)
for( prixInstance in (prixInstanceList) ) {
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('remoteLink','g',130,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("prix"),'action':("edit"),'update':("listPrixValidesContent"),'method':("GET"),'id':(prixInstance.id),'params':("{update:'listPrixValidesContent'}"),'class':("btn-flat  btn-small")],2)
printHtmlPart(27)
expressionOut.print(fieldValue(bean: prixInstance, field: "categorieTarifaire"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: prixInstance, field: "auteur"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: prixInstance, field: "produit"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: prixInstance, field: "mesure"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: prixInstance, field: "marche"))
printHtmlPart(19)
invokeTag('formatDate','g',138,['date':(prixInstance.date),'type':("datetime"),'style':("MEDIUM"),'timeStyle':("MEDIUM")],-1)
printHtmlPart(19)
expressionOut.print(fieldValue(bean: prixInstance, field: "sourcePrix"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: prixInstance, field: "montant"))
printHtmlPart(28)
}
printHtmlPart(21)
invokeTag('remotePaginate','util',150,['update':("listPrixValidesContent"),'controller':("prix"),'action':("list"),'total':(prixInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(22)
invokeTag('formats','export',153,['controller':("prix"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(29)
invokeTag('filterPane','filterpane',164,['controller':("prix"),'associatedProperties':("marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"),'excludeProperties':("periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active"),'dialog':("true"),'update':("listPrixValidesContent"),'domain':("simagri.PriceHolder")],-1)
printHtmlPart(30)
invokeTag('render','g',167,['template':("/saisiePrix/abandonnerPrix")],-1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428774979032L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
