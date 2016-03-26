import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_civilite_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/civilite/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textField','g',7,['name':(property),'value':(it.value),'class':("form-control"),'placeholder':("Code exple: Mr")],-1)
printHtmlPart(2)
})
invokeTag('field','f',7,['bean':("civiliteInstance"),'property':("code"),'required':("true")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textField','g',20,['name':(property),'value':(it.value),'class':("form-control"),'placeholder':("Libellé exple: Monsieur")],-1)
printHtmlPart(2)
})
invokeTag('field','f',20,['bean':("civiliteInstance"),'property':("libelle"),'required':("true")],1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416028652689L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
