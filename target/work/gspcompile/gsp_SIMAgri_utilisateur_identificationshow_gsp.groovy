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

class gsp_SIMAgri_utilisateur_identificationshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_identificationshow.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('display','f',8,['bean':("utilisateurInstance"),'property':("civilite")],-1)
printHtmlPart(1)
invokeTag('display','f',16,['bean':("utilisateurInstance"),'property':("lastName")],-1)
printHtmlPart(2)
invokeTag('display','f',27,['bean':("utilisateurInstance"),'property':("firstName")],-1)
printHtmlPart(3)
invokeTag('display','f',38,['bean':("utilisateurInstance"),'property':("mobilePhone"),'required':("true")],-1)
printHtmlPart(4)
invokeTag('display','f',46,['bean':("utilisateurInstance"),'input-valueMessagePrefix':("utilisateur.gender"),'property':("gender")],-1)
printHtmlPart(5)
invokeTag('display','f',54,['bean':("utilisateurInstance"),'property':("dateOfBirth")],-1)
printHtmlPart(6)
invokeTag('display','f',62,['bean':("utilisateurInstance"),'property':("role")],-1)
printHtmlPart(7)
if(true && (userType=='enqueteur')) {
printHtmlPart(8)
invokeTag('display','f',72,['bean':("utilisateurInstance"),'property':("performance"),'input-class':("form-control")],-1)
printHtmlPart(9)
invokeTag('display','f',77,['bean':("utilisateurInstance"),'property':("marcheEnqueteur"),'input-class':("form-control")],-1)
printHtmlPart(10)
}
printHtmlPart(11)
if(true && (!isCreation)) {
printHtmlPart(12)
invokeTag('display','f',85,['bean':("utilisateurInstance"),'property':("reseauPrincipal")],-1)
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (photoUrl)) {
printHtmlPart(15)
expressionOut.print(photoUrl)
printHtmlPart(16)
}
else {
printHtmlPart(17)
invokeTag('img','g',101,['class':("Photo"),'dir':("images"),'file':("avatar_homme.jpg"),'width':("157"),'alt':("Avatar homme")],-1)
printHtmlPart(18)
}
printHtmlPart(19)
invokeTag('image','asset',111,['src':("cereal_new.png")],-1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416543590959L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
