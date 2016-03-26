import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mettreEnLigne_prix_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mettreEnLigne/_prix.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('resources','ckeditor',9,[:],-1)
printHtmlPart(1)
invokeTag('message','g',10,['code':("miseEnLigne.prix")],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('hiddenField','g',14,['name':("priceData")],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',15,['name':("marketId")],-1)
printHtmlPart(5)
invokeTag('render','g',17,['template':("enteteFirstPage")],-1)
printHtmlPart(4)
invokeTag('render','g',18,['template':("grid_position")],-1)
printHtmlPart(6)
invokeTag('message','g',26,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(7)
})
invokeTag('form','g',29,['class':("form-stacked-vertical myform")],1)
printHtmlPart(8)
invokeTag('render','g',31,['template':("mettre_en_ligne_prix_js")],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410159L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
