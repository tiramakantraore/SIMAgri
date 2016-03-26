import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_stockMagazin_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/stockMagazin/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'stockMagazin.label', default: 'StockMagazin'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("show.stockMagazin")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',16,['code':("show.stockMagazin")],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',20,['class':("alert-info")],3)
printHtmlPart(5)
}
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('display','f',23,['bean':("stockMagazinInstance"),'property':("magazin")],-1)
printHtmlPart(6)
invokeTag('display','f',24,['bean':("stockMagazinInstance"),'property':("produit")],-1)
printHtmlPart(6)
invokeTag('display','f',25,['bean':("stockMagazinInstance"),'property':("date")],-1)
printHtmlPart(6)
invokeTag('display','f',26,['bean':("stockMagazinInstance"),'property':("stock")],-1)
printHtmlPart(6)
invokeTag('display','f',27,['bean':("stockMagazinInstance"),'property':("mesure")],-1)
printHtmlPart(6)
invokeTag('display','f',28,['bean':("stockMagazinInstance"),'property':("auteur")],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',30,['name':("id"),'value':(stockMagazinInstance?.id)],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',31,['name':("update"),'value':(update)],-1)
printHtmlPart(7)
invokeTag('render','g',33,['template':("/partials/btnShow"),'model':(['update':update,'instance':stockMagazinInstance])],-1)
printHtmlPart(5)
})
invokeTag('form','g',34,['class':("well small form-horizontal"),'controller':("stockMagazin"),'action':("show")],2)
printHtmlPart(9)
})
invokeTag('captureBody','sitemesh',39,[:],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419213538202L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
