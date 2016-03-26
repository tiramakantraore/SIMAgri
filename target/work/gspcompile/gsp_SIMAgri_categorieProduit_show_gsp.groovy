import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_categorieProduit_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/categorieProduit/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader"),'model':([beanName:'categorieProduit'])],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',8,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('hiddenField','g',12,['name':("id"),'value':(categorieProduitInstance?.id)],-1)
printHtmlPart(4)
invokeTag('display','f',13,['bean':("categorieProduitInstance"),'property':("nom")],-1)
printHtmlPart(4)
invokeTag('display','f',14,['bean':("categorieProduitInstance"),'property':("actif")],-1)
printHtmlPart(4)
invokeTag('display','f',15,['bean':("categorieProduitInstance"),'property':("produits")],-1)
printHtmlPart(4)
invokeTag('display','f',16,['bean':("categorieProduitInstance"),'property':("mesures")],-1)
printHtmlPart(5)
invokeTag('render','g',17,['template':("/partials/btnShow")],-1)
printHtmlPart(2)
})
invokeTag('form','g',18,[:],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418671570222L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
