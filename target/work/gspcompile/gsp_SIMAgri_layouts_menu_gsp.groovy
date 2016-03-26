import simagri.PageUtilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_menu_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_menu.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(((activeMenu=='Accueil')||(activeMenu==null))?'active':'')
printHtmlPart(2)
createClosureForHtmlPart(3, 2)
invokeTag('link','g',24,['controller':("home"),'action':("accueil")],2)
printHtmlPart(4)
expressionOut.print((activeMenu=='Tableau')?'active':'')
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',30,['controller':("dashboard"),'action':("accueil")],2)
printHtmlPart(7)
expressionOut.print((activeMenu=='MiseEnLigne')?'active':'')
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',32,['mapping':("listeMiseEnLigne")],2)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
expressionOut.print((activeMenu=='Listes')?'active':'')
printHtmlPart(12)
createClosureForHtmlPart(13, 3)
invokeTag('link','g',37,['controller':("listes"),'action':("show")],3)
printHtmlPart(14)
})
invokeTag('ifAdmin','sec',37,[:],2)
printHtmlPart(15)
expressionOut.print((activeMenu=='Applications')?'active':'')
printHtmlPart(16)
createClosureForHtmlPart(17, 2)
invokeTag('link','g',43,['controller':("applications"),'action':("show")],2)
printHtmlPart(18)
createTagBody(2, {->
printHtmlPart(19)
expressionOut.print((activeMenu=='Administration')?'active':'')
printHtmlPart(12)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',48,['controller':("administration"),'action':("show")],3)
printHtmlPart(14)
})
invokeTag('ifAdmin','sec',48,[:],2)
printHtmlPart(21)
expressionOut.print((activeMenu=='Profil')?'active':'')
printHtmlPart(16)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',55,['controller':("utilisateur"),'action':("editProfile"),'params':([isChange:'true'])],2)
printHtmlPart(23)
})
invokeTag('ifLoggedIn','sec',58,[:],1)
printHtmlPart(24)
createTagBody(1, {->
printHtmlPart(25)
expressionOut.print((activeMenu=='Accueil')?'active':'')
printHtmlPart(26)
createClosureForHtmlPart(27, 2)
invokeTag('link','g',61,['controller':("home"),'action':("accueil")],2)
printHtmlPart(28)
for( pageUtil in (pagesAv) ) {
printHtmlPart(29)
createTagBody(3, {->
printHtmlPart(30)
expressionOut.print(pageUtil.nom)
printHtmlPart(31)
})
invokeTag('remoteLink','g',73,['controller':("pageUtilisateur"),'method':("GET"),'action':("showPage"),'update':("simagriIndex"),'id':(pageUtil.id),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],3)
printHtmlPart(14)
}
printHtmlPart(32)
for( pageUtil in (pagesSIMAgri) ) {
printHtmlPart(29)
createTagBody(3, {->
printHtmlPart(30)
expressionOut.print(pageUtil.nom)
printHtmlPart(31)
})
invokeTag('remoteLink','g',83,['controller':("pageUtilisateur"),'method':("GET"),'action':("showPage"),'update':("simagriIndex"),'id':(pageUtil.id),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],3)
printHtmlPart(14)
}
printHtmlPart(33)
for( pageUtil in (pagesPartenaires) ) {
printHtmlPart(29)
createTagBody(3, {->
printHtmlPart(30)
expressionOut.print(pageUtil.nom)
printHtmlPart(31)
})
invokeTag('remoteLink','g',94,['controller':("pageUtilisateur"),'method':("GET"),'action':("showPage"),'update':("simagriIndex"),'id':(pageUtil.id),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],3)
printHtmlPart(14)
}
printHtmlPart(34)
})
invokeTag('ifNotLoggedIn','sec',95,[:],1)
printHtmlPart(35)
createClosureForHtmlPart(36, 1)
invokeTag('form','g',109,['name':("searchForm"),'controller':("search"),'action':("searchAll"),'class':("navbar-form navbar-left form-search form-inline"),'role':("search")],1)
printHtmlPart(37)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442325557698L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
