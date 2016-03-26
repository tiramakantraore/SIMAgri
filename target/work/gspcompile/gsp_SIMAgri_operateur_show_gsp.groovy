import simagri.Operateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_operateur_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/operateur/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('render','g',4,['template':("/partials/btnShow")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('display','f',13,['bean':("operateurInstance"),'property':("nom")],-1)
printHtmlPart(5)
invokeTag('display','f',14,['bean':("operateurInstance"),'property':("prefixes")],-1)
printHtmlPart(5)
invokeTag('display','f',15,['bean':("operateurInstance"),'property':("emailContact")],-1)
printHtmlPart(5)
invokeTag('display','f',16,['bean':("operateurInstance"),'property':("telephoneContact")],-1)
printHtmlPart(5)
invokeTag('display','f',17,['bean':("operateurInstance"),'property':("siteWeb")],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',21,['name':("id"),'value':(operateurInstance?.id)],-1)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
invokeTag('message','g',25,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(9)
})
invokeTag('link','g',26,['class':("btn"),'action':("edit"),'id':(operateurInstance?.id)],2)
printHtmlPart(10)
})
invokeTag('form','g',29,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419280149760L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
