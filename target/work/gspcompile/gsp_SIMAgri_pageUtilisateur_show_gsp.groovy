import simagri.PageUtilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageUtilisateur_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageUtilisateur/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
expressionOut.print(pageUtilisateurInstance?.nom)
printHtmlPart(3)
if(true && ("$pageUtilisateurInstance?.contenu?.trim()")) {
printHtmlPart(4)
invokeTag('imageWithText','bill',14,['texte':(pageUtilisateurInstance?.contenu),'imageURL':(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageUtilisateurInstance?.id])),'imagePosition':(pageUtilisateurInstance?.alignement?.name()),'imageHeight':("25")],-1)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('hiddenField','g',21,['name':("id"),'value':(pageUtilisateurInstance?.id)],-1)
printHtmlPart(7)
invokeTag('render','g',22,['template':("/partials/btnShow")],-1)
printHtmlPart(8)
})
invokeTag('form','g',23,['class':("form-horizontal"),'action':("show")],1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417051751127L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
