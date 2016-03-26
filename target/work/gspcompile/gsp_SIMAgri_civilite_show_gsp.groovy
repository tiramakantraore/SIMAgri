import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_civilite_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/civilite/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([beanName:'civilite'])],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',6,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('display','f',9,['bean':("civiliteInstance"),'property':("code")],-1)
printHtmlPart(2)
invokeTag('display','f',10,['bean':("civiliteInstance"),'property':("libelle")],-1)
printHtmlPart(3)
invokeTag('hiddenField','g',13,['name':("id"),'value':(civiliteInstance?.id)],-1)
printHtmlPart(4)
invokeTag('render','g',15,['template':("/partials/btnShow")],-1)
printHtmlPart(5)
})
invokeTag('form','g',17,['class':("well ")],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419280149781L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
