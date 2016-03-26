import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_home_accueilML_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/_accueilML.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',4,['template':("/home/mySlider")],-1)
printHtmlPart(1)
createClosureForHtmlPart(2, 1)
invokeTag('link','g',14,['controller':("pageUtilisateur"),'action':("showByDestinationType"),'params':([DestinationType:'Page_Avantages']),'target':("_blank"),'title':("Les avantages")],1)
printHtmlPart(3)
for( videoInstance in (videos) ) {
printHtmlPart(4)
expressionOut.print(videoInstance.titre)
printHtmlPart(5)
invokeTag('video','g',20,['videoKey':(videoInstance?.url?.split('=')?.size()>1?videoInstance?.url?.split('=')[1]:""),'width':("100%"),'height':("250px")],-1)
printHtmlPart(6)
invokeTag('display','prettytime',22,['date':(videoInstance?.date)],-1)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',28,['code':("rejoignez-nous-sur")],-1)
printHtmlPart(9)
invokeTag('render','g',29,['template':("/home/myindexjs")],-1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423865208694L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
