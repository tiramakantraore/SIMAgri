import simagri.Performance
import  simagri.Civilite
import  simagri.Produit
import  simagri.Marche
import  simagri.Reseau
import  simagri.CategorieProduit
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_identification_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_identification.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('field','f',10,['bean':("utilisateurInstance"),'property':("mobilePhone"),'required':("true"),'input-class':("form-control"),'placeholder':(message(code: 'login.placeHolder.label', default: 'Login')),'input-autocomplete':("off")],-1)
printHtmlPart(1)
invokeTag('field','f',22,['bean':("utilisateurInstance"),'property':("civilite"),'input-class':(" many-to-one form-control"),'input-noSelection':(['': ''])],-1)
printHtmlPart(2)
invokeTag('field','f',27,['bean':("utilisateurInstance"),'property':("lastName"),'input-class':(" many-to-one form-control")],-1)
printHtmlPart(3)
invokeTag('field','f',36,['bean':("utilisateurInstance"),'property':("firstName"),'input-class':(" many-to-one form-control")],-1)
printHtmlPart(4)
invokeTag('field','f',48,['bean':("utilisateurInstance"),'property':("gender"),'input-valueMessagePrefix':("utilisateur.gender"),'input-class':("form-control")],-1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(property)
printHtmlPart(7)
expressionOut.print(formatDate(format:'dd/MM/yyyy',date:it.value))
printHtmlPart(8)
})
invokeTag('field','f',76,['bean':("utilisateurInstance"),'property':("dateOfBirth"),'input-class':("form-control")],1)
printHtmlPart(9)
if(true && (isEditing)) {
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('field','f',90,['bean':("utilisateurInstance"),'property':("password")],2)
printHtmlPart(12)
createClosureForHtmlPart(13, 2)
invokeTag('field','f',103,['bean':("utilisateurInstance"),'property':("password"),'label':(g.message(code:"confirmPassword.text")),'input-class':("form-control")],2)
printHtmlPart(14)
}
printHtmlPart(15)
if(true && (userType=='enqueteur')) {
printHtmlPart(16)
invokeTag('field','f',108,['bean':("utilisateurInstance"),'property':("performance"),'input-class':("form-control")],-1)
printHtmlPart(16)
invokeTag('field','f',110,['bean':("utilisateurInstance"),'property':("marcheEnqueteur"),'input-class':("form-control")],-1)
printHtmlPart(17)
}
printHtmlPart(17)
if(true && (!isCreation)) {
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(18)
invokeTag('select','g',119,['name':("reseauPrincipal"),'from':(Reseau.findAllByEstReseau(true)),'optionKey':("id"),'value':(utilisateurInstance?.reseauPrincipal?.id),'class':("many-to-one form-control")],-1)
printHtmlPart(16)
})
invokeTag('field','f',120,['bean':("utilisateurInstance"),'property':("reseauPrincipal")],2)
printHtmlPart(17)
}
printHtmlPart(17)
createTagBody(1, {->
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(18)
invokeTag('select','g',124,['name':("role"),'from':(roleList),'value':(utilisateurInstance?.role),'class':("many-to-one form-control")],-1)
printHtmlPart(16)
})
invokeTag('field','f',124,['bean':("utilisateurInstance"),'property':("role"),'input-class':("form-control")],2)
printHtmlPart(17)
})
invokeTag('ifSuperViseur','sec',124,[:],1)
printHtmlPart(19)
if(true && (photoUrl)) {
printHtmlPart(20)
expressionOut.print(photoUrl)
printHtmlPart(21)
}
else {
printHtmlPart(16)
invokeTag('img','g',136,['class':("Photo"),'dir':("images"),'file':("avatar_homme.jpg"),'width':("157"),'alt':("Avatar homme")],-1)
printHtmlPart(17)
}
printHtmlPart(22)
invokeTag('message','g',140,['code':("select.photo.text"),'default':("Select photo")],-1)
printHtmlPart(23)
invokeTag('message','g',145,['code':("change.image.text"),'default':("change")],-1)
printHtmlPart(24)
invokeTag('message','g',152,['code':("remove.image.text"),'default':("remove")],-1)
printHtmlPart(25)
createTagBody(1, {->
printHtmlPart(26)
invokeTag('message','g',154,['code':("taille.image.text"),'default':("Taille image : 64x64  ")],-1)
printHtmlPart(27)
})
invokeTag('alert','bootstrap',154,['class':("alert-info")],1)
printHtmlPart(28)
invokeTag('image','asset',159,['src':("cereal_new.png")],-1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423435079938L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
