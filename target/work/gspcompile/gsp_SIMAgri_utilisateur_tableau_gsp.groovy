import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_tableau_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_tableau.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('remoteSortableColumn','util',8,['update':("listContent"),'action':("list"),'property':("nomComplet"),'title':(message(code: 'utilisateur.nomComplet.label', default: 'Nom complet'))],-1)
printHtmlPart(1)
invokeTag('remoteSortableColumn','util',11,['update':("listContent"),'action':("list"),'property':("mobilePhone"),'title':(message(code: 'utilisateur.mobilePhone.label', default: 'Téléphone'))],-1)
printHtmlPart(2)
invokeTag('remoteSortableColumn','util',13,['update':("listContent"),'action':("list"),'property':("role"),'title':(message(code: 'utilisateur.role.label', default: 'Rôle'))],-1)
printHtmlPart(2)
invokeTag('remoteSortableColumn','util',15,['update':("listContent"),'action':("list"),'property':("reseauPrincipal"),'title':(message(code: 'utilisateur.reseauPrincipal.label', default: 'Réseau Principal'))],-1)
printHtmlPart(1)
invokeTag('remoteSortableColumn','util',18,['update':("listContent"),'action':("list"),'property':("reseaux"),'title':(message(code: 'utilisateur.reseaux.label', default: 'Réseau Principal'))],-1)
printHtmlPart(3)
for( utilisateurInstance in (utilisateurInstanceList) ) {
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('remoteLink','g',29,['controller':("utilisateur"),'action':("edit"),'params':("{isChange:'false'}"),'method':("GET"),'update':("listContent"),'id':(utilisateurInstance?.id),'onLoading':("showSpinner();"),'onComplete':("hideSpinner()")],2)
printHtmlPart(6)
expressionOut.print(fieldValue(bean: utilisateurInstance, field: "nomComplet"))
printHtmlPart(7)
expressionOut.print(fieldValue(bean: utilisateurInstance, field: "mobilePhone"))
printHtmlPart(7)
expressionOut.print(fieldValue(bean: utilisateurInstance, field: "role"))
printHtmlPart(7)
expressionOut.print(fieldValue(bean: utilisateurInstance, field: "reseauPrincipal"))
printHtmlPart(7)
expressionOut.print(fieldValue(bean: utilisateurInstance, field: "reseaux"))
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('remotePaginate','util',49,['update':("listContent"),'action':("listPaginate"),'params':([userType:userType]),'total':(utilisateurInstanceTotal)],-1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423574506014L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
