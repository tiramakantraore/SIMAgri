import simagri.Utilisateur
import  simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_smsToReseaux_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smsToReseaux/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',6,['code':("alerte.destinataires.label"),'default':("Destinataires")],-1)
printHtmlPart(1)
invokeTag('select','g',8,['id':("destinataires"),'name':("destinataires"),'from':(Utilisateur.findAllById(0 as Long)),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'value':(smsToReseauxInstance?.destinataires*.id),'class':("many-to-many col-sm-6 col-md-6")],-1)
printHtmlPart(2)
expressionOut.print(hasErrors(bean: smsToReseauxInstance, field: 'yourTextMessage', 'error'))
printHtmlPart(3)
invokeTag('textArea','g',19,['name':("yourTextMessage"),'id':("yourTextMessage"),'cols':("50"),'rows':("10"),'maxlength':("2000"),'class':("col-sm-12 col-md-12"),'required':(""),'value':(smsToReseauxInstance?.yourTextMessage),'placeHolder':("Votre message ici (maximum 1600 caractères)")],-1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1407035061227L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
