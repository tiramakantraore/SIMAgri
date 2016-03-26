import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_stockMagazin_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/stockMagazin/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (update=='listStocksAValiderContent')) {
printHtmlPart(1)
invokeTag('message','g',9,['code':("edit.stockMagazinAValider")],-1)
printHtmlPart(2)
}
else {
printHtmlPart(1)
invokeTag('message','g',12,['code':("edit.stockMagazinValide")],-1)
printHtmlPart(2)
}
printHtmlPart(3)
if(true && (flash.message)) {
printHtmlPart(4)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',19,['class':("alert-info")],2)
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(6)
createTagBody(3, {->
printHtmlPart(7)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(8)
expressionOut.print(error.field)
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('message','g',26,['error':(error)],-1)
printHtmlPart(11)
})
invokeTag('eachError','g',27,['bean':(stockMagazinInstance),'var':("error")],3)
printHtmlPart(12)
})
invokeTag('alert','bootstrap',29,['class':("alert-error")],2)
printHtmlPart(4)
})
invokeTag('hasErrors','g',30,['bean':(stockMagazinInstance)],1)
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(14)
invokeTag('hiddenField','g',33,['name':("version"),'value':(stockMagazinInstance?.version)],-1)
printHtmlPart(15)
invokeTag('hiddenField','g',34,['name':("id"),'value':(stockMagazinInstance?.id)],-1)
printHtmlPart(14)
invokeTag('hiddenField','g',35,['name':("update"),'value':(update)],-1)
printHtmlPart(14)
invokeTag('render','g',36,['template':("/partials/btnEdit"),'model':([update:update])],-1)
printHtmlPart(16)
invokeTag('render','g',37,['template':("form")],-1)
printHtmlPart(14)
invokeTag('render','g',38,['template':("/partials/btnEdit"),'model':([update:update])],-1)
printHtmlPart(17)
})
invokeTag('form','g',39,['class':("well small form-horizontal"),'controller':("stockMagazin"),'action':("edit")],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418962565344L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
