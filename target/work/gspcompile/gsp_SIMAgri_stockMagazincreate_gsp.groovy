import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_stockMagazincreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/stockMagazin/create.gsp" }
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
invokeTag('message','g',8,['code':("create.stockMagazin")],-1)
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
invokeTag('message','g',16,['code':("title.stockMagazin")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',20,['code':("list.stockMagazin")],-1)
printHtmlPart(6)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
invokeTag('message','g',26,['code':("create.stockMagazin")],-1)
printHtmlPart(6)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',36,['code':("create.stockMagazin")],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',40,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(13)
createTagBody(4, {->
printHtmlPart(14)
if(true && (error in FieldError)) {
printHtmlPart(15)
expressionOut.print(error.field)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('message','g',47,['error':(error)],-1)
printHtmlPart(18)
})
invokeTag('eachError','g',48,['bean':(stockMagazinInstance),'var':("error")],4)
printHtmlPart(19)
})
invokeTag('alert','bootstrap',50,['class':("alert-error")],3)
printHtmlPart(11)
})
invokeTag('hasErrors','g',51,['bean':(stockMagazinInstance)],2)
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('render','g',56,['template':("form")],-1)
printHtmlPart(22)
expressionOut.print(createLink(controller:'stockMagazin', action:'create'))
printHtmlPart(23)
invokeTag('message','g',60,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(24)
})
invokeTag('form','g',64,['class':("well small form-horizontal"),'action':("create")],2)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',70,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411808L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
