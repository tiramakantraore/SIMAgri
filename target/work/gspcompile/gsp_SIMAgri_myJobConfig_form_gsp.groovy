import simagri.MyJobConfig
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_myJobConfig_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/myJobConfig/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: myJobConfigInstance, field: 'cron', 'error'))
printHtmlPart(1)
expressionOut.print(cronFormat)
printHtmlPart(2)
invokeTag('message','g',6,['code':("myJobConfig.cron.label"),'default':("Cron"),'placeHolder':("0 0/40 7,13,18 * * ?")],-1)
printHtmlPart(3)
invokeTag('textField','g',9,['name':("cron"),'value':(myJobConfigInstance?.cron)],-1)
printHtmlPart(4)
expressionOut.print(new Date())
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1363573895586L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
