import simagri.Mesure
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_categorieProduit_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/categorieProduit/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','f',4,['property':("nom"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('checkBoxList','bill',5,['referenceCollection':(Mesure.list()),'containerClass':("my2Columns"),'instanceName':("mesures")],-1)
printHtmlPart(2)
invokeTag('field','f',6,['property':("actif"),'input-class':("check")],-1)
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(3)
invokeTag('textArea','g',9,['name':(property),'cols':("50"),'rows':("5"),'value':(it.value)],-1)
printHtmlPart(1)
})
invokeTag('field','f',9,['property':("comment"),'input-class':("form-control")],2)
printHtmlPart(0)
})
invokeTag('with','f',9,['bean':("categorieProduitInstance")],1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418671528365L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
