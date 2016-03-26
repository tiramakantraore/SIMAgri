import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_stockMagazin_saisiestocks_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/stockMagazin/_saisiestocks.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('hiddenField','g',4,['name':("selectedList")],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',5,['name':("magazinId")],-1)
printHtmlPart(3)
invokeTag('render','g',7,['template':("/stockMagazin/enteteFirstPage")],-1)
printHtmlPart(2)
invokeTag('render','g',8,['template':("/stockMagazin/grid_position")],-1)
printHtmlPart(4)
})
invokeTag('form','g',11,['class':("form-horizontal"),'action':("saisie")],1)
printHtmlPart(5)
invokeTag('message','g',15,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411391L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
