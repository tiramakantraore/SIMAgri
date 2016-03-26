import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sendsms_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sendsms/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: smsToReseauxInstance, field: 'toPhoneNumber', 'error'))
printHtmlPart(1)
invokeTag('textField','g',7,['name':("toPhoneNumber"),'required':(""),'value':(smsToReseauxInstance?.toPhoneNumber),'placeHolder':("N° Téléphone"),'class':("form-control")],-1)
printHtmlPart(2)
expressionOut.print(hasErrors(bean: smsToReseauxInstance, field: 'yourTextMessage', 'error'))
printHtmlPart(3)
invokeTag('textArea','g',15,['name':("yourTextMessage"),'cols':("40"),'rows':("5"),'maxlength':("200"),'class':("form-control"),'required':(""),'value':(smsToReseauxInstance?.yourTextMessage),'placeHolder':("Texte SMS")],-1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1413578483586L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
