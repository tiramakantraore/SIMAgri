import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mettreEnLigne_importUsers_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mettreEnLigne/_importUsers.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',5,['code':("importer.utilisateurs")],-1)
printHtmlPart(1)
invokeTag('message','g',10,['code':("choisir.reseau")],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',29,['code':("ecraserDoublons.label"),'default':("Ecraser doublons (Mobile) ?")],-1)
printHtmlPart(4)
invokeTag('checkBox','g',32,['name':("ecraserDoublons"),'value':(ecraserDoublons)],-1)
printHtmlPart(5)
invokeTag('message','g',36,['code':("selectionnerFichierExcelUtilisateurs.label"),'default':("Veuillez sélectionner le fichier excel des utilisateurs:")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',39,['class':("alert-info")],3)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('hiddenField','g',42,['name':("ReseauxIds")],-1)
printHtmlPart(10)
expressionOut.print(createLink(controller: 'home', action: 'downloadUserTemplate'))
printHtmlPart(11)
invokeTag('message','g',46,['code':("default.texteFormatUser.label"),'default':("")],-1)
printHtmlPart(12)
expressionOut.print(createLink(controller:controllerName, action:'uploadUsers'))
printHtmlPart(13)
invokeTag('message','g',51,['code':("default.button.envoyer.label"),'default':("Click me")],-1)
printHtmlPart(14)
})
invokeTag('form','g',52,['action':("uploadUsers")],1)
printHtmlPart(15)
invokeTag('render','g',1,['template':("/mettreEnLigne/mettre_en_ligne_users_js")],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428773254052L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
