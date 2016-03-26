import simagri.PageUtilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageUtilisateurshowPublic_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageUtilisateur/showPublic.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("pageLayout")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(pageUtilisateurInstance?.nom)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(pageUtilisateurInstance?.nom)
printHtmlPart(6)
if(true && ("$pageUtilisateurInstance?.contenu?.trim()")) {
printHtmlPart(7)
invokeTag('imageWithText','bill',18,['texte':(pageUtilisateurInstance?.contenu?.prettify()),'imageURL':(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageUtilisateurInstance?.id])),'imagePosition':(pageUtilisateurInstance?.alignement?.name()),'imageHeight':("50")],-1)
printHtmlPart(8)
}
printHtmlPart(9)
})
invokeTag('captureBody','sitemesh',34,[:],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418311958073L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
