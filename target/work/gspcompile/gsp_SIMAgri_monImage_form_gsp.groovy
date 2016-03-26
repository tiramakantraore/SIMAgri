import simagri.TypeAlignement
import  simagri.MonImage
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_monImage_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/monImage/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: monImageInstance, field: 'nom', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("monImage.nom.label"),'default':("Nom")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("nom"),'maxlength':("25"),'required':(""),'value':(monImageInstance?.nom),'class':("form-control")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: monImageInstance, field: 'description', 'error'))
printHtmlPart(4)
invokeTag('message','g',16,['code':("monImage.description.label"),'default':("Description")],-1)
printHtmlPart(2)
invokeTag('textArea','g',19,['name':("description"),'cols':("40"),'rows':("5"),'maxlength':("1000"),'value':(monImageInstance?.description),'class':("form-control")],-1)
printHtmlPart(5)
expressionOut.print(hasErrors(bean: monImageInstance, field: 'photo', 'error'))
printHtmlPart(6)
invokeTag('message','g',24,['code':("monImage.photo.label"),'default':("Photo")],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417045533997L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
