import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_role_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/role/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: roleInstance, field: 'authority', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("role.authority.label"),'default':("Authority")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("authority"),'required':(""),'value':(roleInstance?.authority),'autocomplete':("off")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: roleInstance, field: 'description', 'error'))
printHtmlPart(4)
invokeTag('message','g',15,['code':("role.description.label"),'default':("Description")],-1)
printHtmlPart(5)
invokeTag('textArea','g',18,['name':("description"),'cols':("40"),'rows':("5"),'maxlength':("255"),'value':(roleInstance?.description),'autocomplete':("off")],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1345660197467L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
