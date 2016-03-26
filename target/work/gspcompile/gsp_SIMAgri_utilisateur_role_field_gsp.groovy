import simagri.CategorieProduit
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_role_field_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/role/_field.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(invalid ? 'error' : '')
printHtmlPart(1)
expressionOut.print(property)
printHtmlPart(2)
expressionOut.print(label)
printHtmlPart(3)
invokeTag('select','g',5,['name':("role"),'from':(roleList),'value':(utilisateurInstance?.role),'valueMessagePrefix':("utilisateur.role")],-1)
printHtmlPart(4)
if(true && (invalid)) {
printHtmlPart(5)
expressionOut.print(errors.join('<br>'))
printHtmlPart(6)
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1390872888147L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
