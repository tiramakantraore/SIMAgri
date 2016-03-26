import simagri.Utilisateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_sendEmail_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_sendEmail.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',3,['code':("envoyerMail")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('hiddenField','g',7,['name':("ReseauxIdsSMS")],-1)
printHtmlPart(3)
invokeTag('message','g',11,['code':("sendEmail.button.label"),'default':("Envoyer")],-1)
printHtmlPart(4)
invokeTag('message','g',23,['code':("choisir.reseau")],-1)
printHtmlPart(5)
invokeTag('checkBoxList','bill',57,['referenceCollection':(Utilisateur.findAllById(0)),'containerClass':(ctnerTemplate),'instanceName':("destinataires_SMS")],-1)
printHtmlPart(6)
invokeTag('textArea','g',81,['name':("yourTextMessage"),'id':("yourTextMessage"),'cols':("50"),'rows':("10"),'maxlength':("3000"),'class':("col-sm-12 col-md-12"),'required':(""),'placeHolder':("Votre message ici (maximum 3000 caractères)")],-1)
printHtmlPart(7)
invokeTag('message','g',84,['code':("sendEmail.button.label"),'default':("Envoyer")],-1)
printHtmlPart(8)
})
invokeTag('form','g',85,['name':("sendSMSForm"),'class':("form-horizontal")],1)
printHtmlPart(9)
invokeTag('render','g',87,['template':("/applications/emailjs")],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423730658457L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
