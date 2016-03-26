import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_stockMagazin_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/stockMagazin/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',9,['code':("list.stockMagazin")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',13,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',15,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',20,['update':("listStocksAValiderContent"),'action':("list"),'property':("date"),'title':(message(code: 'stockMagazin.date.label', default: 'Date'))],-1)
printHtmlPart(5)
invokeTag('message','g',22,['code':("stockMagazin.magazin.label"),'default':("Magazin")],-1)
printHtmlPart(6)
invokeTag('message','g',24,['code':("stockMagazin.produit.label"),'default':("Produit")],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',27,['update':("listStocksAValiderContent"),'action':("list"),'property':("stock"),'title':(message(code: 'stockMagazin.stock.label', default: 'Stock'))],-1)
printHtmlPart(8)
invokeTag('message','g',29,['code':("stockMagazin.mesure.label"),'default':("Mesure")],-1)
printHtmlPart(9)
for( stockMagazinInstance in (stockMagazinInstanceList) ) {
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('remoteLink','g',40,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("stockMagazin"),'action':("edit"),'update':("listStocksAValiderContent"),'method':("GET"),'id':(stockMagazinInstance.id),'params':([update:'listStocksAValiderContent']),'class':("btn-flat  btn-small")],2)
printHtmlPart(12)
invokeTag('formatDate','g',42,['date':(stockMagazinInstance.date)],-1)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "magazin"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "produit"))
printHtmlPart(15)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "stock"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: stockMagazinInstance, field: "mesure"))
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('remotePaginate','util',60,['update':("listStocksAValiderContent"),'controller':("stockMagazin"),'action':("list"),'total':(stockMagazinInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(18)
invokeTag('formats','export',63,['controller':("stockMagazin"),'formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(19)
invokeTag('filterPane','filterpane',67,['controller':("stockMagazin"),'dialog':("true"),'domain':("simagri.StockMagazin")],-1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419573866259L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
