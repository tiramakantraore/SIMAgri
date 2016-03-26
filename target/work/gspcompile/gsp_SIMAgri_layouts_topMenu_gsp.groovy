import simagri.PageUtilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_topMenu_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_topMenu.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print((activeMenu=='Accueil')?'active':'')
printHtmlPart(1)
createClosureForHtmlPart(2, 1)
invokeTag('remoteLink','g',21,['controller':("home"),'action':("accueil"),'update':("simagriIndex"),'method':("GET"),'id':("accueilNotLogged"),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],1)
printHtmlPart(3)
for( pageUtil in (pagesAv) ) {
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(pageUtil.nom)
printHtmlPart(6)
})
invokeTag('remoteLink','g',28,['controller':("pageUtilisateur"),'method':("GET"),'action':("showPage"),'update':("simagriIndex"),'id':(pageUtil.id),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],2)
printHtmlPart(7)
}
printHtmlPart(8)
for( pageUtil in (pagesSIMAgri) ) {
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(pageUtil.nom)
printHtmlPart(6)
})
invokeTag('remoteLink','g',39,['controller':("pageUtilisateur"),'method':("GET"),'action':("showPage"),'update':("simagriIndex"),'id':(pageUtil.id),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],2)
printHtmlPart(7)
}
printHtmlPart(9)
for( pageUtil in (pagesPartenaires) ) {
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
expressionOut.print(pageUtil.nom)
printHtmlPart(6)
})
invokeTag('remoteLink','g',51,['controller':("pageUtilisateur"),'method':("GET"),'action':("showPage"),'update':("simagriIndex"),'id':(pageUtil.id),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],2)
printHtmlPart(7)
}
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411536L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
