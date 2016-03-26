import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_produit_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/produit/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('display','f',12,['bean':("produitInstance"),'property':("code")],-1)
printHtmlPart(3)
invokeTag('display','f',13,['bean':("produitInstance"),'property':("nom")],-1)
printHtmlPart(3)
invokeTag('display','f',14,['bean':("produitInstance"),'property':("nomScientifique")],-1)
printHtmlPart(3)
invokeTag('display','f',15,['bean':("produitInstance"),'property':("categorie")],-1)
printHtmlPart(3)
invokeTag('display','f',16,['bean':("produitInstance"),'property':("mesure")],-1)
printHtmlPart(3)
invokeTag('display','f',17,['bean':("produitInstance"),'property':("actif")],-1)
printHtmlPart(3)
invokeTag('display','f',18,['bean':("produitInstance"),'property':("commentaire")],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',21,['name':("id"),'value':(produitInstance?.id)],-1)
printHtmlPart(5)
invokeTag('render','g',22,['template':("/partials/btnShow")],-1)
printHtmlPart(2)
})
invokeTag('form','g',23,[:],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278538507L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
