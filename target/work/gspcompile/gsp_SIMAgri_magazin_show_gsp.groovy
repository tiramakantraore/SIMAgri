import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_magazin_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/magazin/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([beanName:'magazin'])],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',6,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('display','f',9,['bean':("magazinInstance"),'property':("code")],-1)
printHtmlPart(2)
invokeTag('display','f',10,['bean':("magazinInstance"),'property':("nom")],-1)
printHtmlPart(2)
invokeTag('display','f',11,['bean':("magazinInstance"),'property':("localite")],-1)
printHtmlPart(2)
invokeTag('display','f',12,['bean':("magazinInstance"),'property':("description")],-1)
printHtmlPart(2)
if(true && (magazinInstance?.produits)) {
printHtmlPart(3)
for( p in (magazinInstance.produits) ) {
printHtmlPart(4)
expressionOut.print(p?.encodeAsHTML())
printHtmlPart(5)
}
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('hiddenField','g',22,['name':("id"),'value':(magazinInstance?.id)],-1)
printHtmlPart(8)
invokeTag('render','g',23,['template':("/partials/btnShow")],-1)
printHtmlPart(1)
})
invokeTag('form','g',24,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278555849L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
