import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_listeDesStocks_index_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/listeDesStocks/_index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(g.message(code:"stocksAValider.text", default:"Stocks à valider"))
printHtmlPart(1)
expressionOut.print(g.message(code:"stocksAValider.text", default:"Stocks à valider"))
printHtmlPart(2)
expressionOut.print(g.message(code:"stocksValides.text", default:" Stocks validés"))
printHtmlPart(3)
expressionOut.print(g.message(code:"stocksValides.text", default:" Stocks validés"))
printHtmlPart(4)
expressionOut.print(g.message(code:"stocksRejetes.text", default:" Stocks rejétés"))
printHtmlPart(3)
expressionOut.print(g.message(code:"stocksRejetes.text", default:" Stocks rejétés"))
printHtmlPart(5)
invokeTag('message','g',21,['code':("list.stockMagazin")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',25,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',32,['update':("listStocksAValiderContent"),'action':("list"),'property':("date"),'title':(message(code: 'stockMagazin.date.label', default: 'Date'))],-1)
printHtmlPart(10)
invokeTag('message','g',34,['code':("stockMagazin.magazin.label"),'default':("Magazin")],-1)
printHtmlPart(11)
invokeTag('message','g',36,['code':("stockMagazin.produit.label"),'default':("Produit")],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',39,['update':("listStocksAValiderContent"),'action':("list"),'property':("stock"),'title':(message(code: 'stockMagazin.stock.label', default: 'Stock'))],-1)
printHtmlPart(10)
invokeTag('message','g',41,['code':("stockMagazin.mesure.label"),'default':("Mesure")],-1)
printHtmlPart(13)
for( stockMagazinInstance in (stockMagazinInstanceList) ) {
printHtmlPart(14)
createClosureForHtmlPart(15, 2)
invokeTag('remoteLink','g',52,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("stockMagazin"),'action':("edit"),'update':("listStocksAValiderContent"),'method':("GET"),'id':(stockMagazinInstance.id),'params':([update:'listStocksAValiderContent']),'class':("btn-flat  btn-small")],2)
printHtmlPart(16)
invokeTag('formatDate','g',54,['date':(stockMagazinInstance.date)],-1)
printHtmlPart(17)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "magazin"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "produit"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "stock"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "mesure"))
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('remotePaginate','util',72,['update':("listStocksAValiderContent"),'controller':("stockMagazin"),'action':("list"),'total':(stockMagazinInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(21)
invokeTag('formats','export',75,['controller':("stockMagazin"),'action':("list"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(22)
invokeTag('message','g',88,['code':("list.stockMagazinHolderValide")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',93,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',101,['update':("listStocksValidesContent"),'action':("list"),'property':("date"),'title':(message(code: 'stockMagazin.date.label', default: 'Date'))],-1)
printHtmlPart(10)
invokeTag('message','g',102,['code':("stockMagazin.magazin.label"),'default':("Magazin")],-1)
printHtmlPart(11)
invokeTag('message','g',104,['code':("stockMagazin.produit.label"),'default':("Produit")],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',106,['update':("listStocksValidesContent"),'action':("list"),'property':("stock"),'title':(message(code: 'stockMagazin.stock.label', default: 'Stock'))],-1)
printHtmlPart(10)
invokeTag('message','g',109,['code':("stockMagazin.mesure.label"),'default':("Mesure")],-1)
printHtmlPart(13)
for( stockMagazinInstance in (stockMagazinInstanceValideList) ) {
printHtmlPart(14)
createClosureForHtmlPart(15, 2)
invokeTag('remoteLink','g',121,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("stockMagazin"),'action':("edit"),'update':("listStocksValidesContent"),'method':("GET"),'id':(stockMagazinInstance.id),'params':([update:'listStocksValidesContent']),'class':("btn-flat  btn-small")],2)
printHtmlPart(16)
invokeTag('formatDate','g',122,['date':(stockMagazinInstance.date)],-1)
printHtmlPart(17)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "magazin"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "produit"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "stock"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "mesure"))
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('remotePaginate','util',141,['update':("listStocksValidesContent"),'controller':("stockMagazin"),'action':("listValides"),'total':(stockMagazinInstanceValideTotal),'params':(filterParams)],-1)
printHtmlPart(21)
invokeTag('formats','export',142,['controller':("stockMagazin"),'action':("listValides"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(23)
invokeTag('render','g',146,['template':("/stockMagazin/abandonner")],-1)
printHtmlPart(24)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428774729790L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
