import simagri.Utilisateur
import  simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','f',4,['property':("titre"),'input-class':("form-control")],-1)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(2)
createTagBody(3, {->
printHtmlPart(3)
expressionOut.print(it.value)
printHtmlPart(2)
})
invokeTag('editor','ckeditor',8,['name':(property),'height':("300px"),'width':("100%")],3)
printHtmlPart(2)
})
invokeTag('field','f',9,['property':("contenu"),'input-class':("form-control")],2)
printHtmlPart(1)
invokeTag('field','f',10,['property':("longitude"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',11,['property':("latitude"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',12,['property':("dateExpiration"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',13,['property':("auteur"),'input-class':("form-control")],-1)
printHtmlPart(4)
})
invokeTag('with','f',14,['bean':("noteMarcheInstance")],1)
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455374984855L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
