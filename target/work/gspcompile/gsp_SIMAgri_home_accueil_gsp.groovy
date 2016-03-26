import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_home_accueil_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/_accueil.gsp" }
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
createClosureForHtmlPart(4, 1)
invokeTag('link','g',17,['controller':("pageUtilisateur"),'action':("showByDestinationType"),'params':([DestinationType:'Page_Essaie']),'target':("_blank"),'title':("Essaie gratuit")],1)
printHtmlPart(5)
for( videoInstance in (videos) ) {
printHtmlPart(6)
expressionOut.print(videoInstance.titre)
printHtmlPart(7)
invokeTag('video','g',28,['id':(videos?.id),'videoKey':(videoInstance?.url?.split('=')?.size()>1?videoInstance?.url?.split('=')[1]:""),'width':("100%"),'height':("250px")],-1)
printHtmlPart(8)
invokeTag('display','prettytime',28,['date':(videoInstance?.date)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('message','g',35,['code':("rejoignez-nous-sur")],-1)
printHtmlPart(11)
invokeTag('render','g',37,['template':("/home/myindexjs")],-1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1426011908085L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
