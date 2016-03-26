import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_validerOffre_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_validerOffre.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',3,['code':("offerValidation.label")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('render','g',7,['template':("thirdPageNav")],-1)
printHtmlPart(2)
invokeTag('render','g',8,['template':("grid_position")],-1)
printHtmlPart(2)
invokeTag('render','g',9,['template':("thirdPageNav")],-1)
printHtmlPart(3)
expressionOut.print(createLink(controller:'offre', action:'valider'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'offre', action:'populate'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'offre', action:'rejeter'))
printHtmlPart(6)
expressionOut.print(createLink(controller:'offre', action:'populate'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'home', action:'accueil'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'offre', action:'populate'))
printHtmlPart(9)
})
invokeTag('form','g',133,['action':("validerOffre"),'method':("post")],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328650275L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
