import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configProperty_configProperty_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configProperty/configProperty/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
expressionOut.print(flash.message)
printHtmlPart(3)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('display','f',10,['bean':("configPropertyInstance"),'property':("configKey")],-1)
printHtmlPart(6)
invokeTag('display','f',11,['bean':("configPropertyInstance"),'property':("value")],-1)
printHtmlPart(6)
invokeTag('display','f',13,['bean':("configPropertyInstance"),'property':("description")],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',15,['name':("id"),'value':(configPropertyInstance?.id)],-1)
printHtmlPart(5)
invokeTag('render','g',15,['template':("/partials/btnShow")],-1)
printHtmlPart(7)
})
invokeTag('form','g',16,[:],1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1433075972613L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
